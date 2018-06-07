import React, { Component } from 'react';
//import ReactDOMServer from 'react-dom/server';
import vis from "vis";
import axios from 'axios';
import Card, { CardContent } from 'material-ui-next/Card';
import { Grid, Row } from 'react-flexbox-grid';
import '../../../../node_modules/vis/dist/vis.css';

/*const teste =
  <div>
    <h3>Nome</h3>
    <p>Descrição curta</p>
    <p>Metrica: 12 Metrica: 12</p>
    <p>Metrica: 12 Metrica: 12</p>
    <p>Metrica: 12 Metrica: 12</p>
  </div>;
*/

class Arvore extends Component {
  constructor(props) {
    super(props);

    this.state = {
      nos: [],
      arestas: [],
      orientacao: "UD",
    };
  };
  componentDidMount() {
    return axios
      .post('/Search/ById',
        {
          id: this.props.match.params.id,
        }).then(({ data }) => {
          this.setState({ nos: data.nodes, arestas: data.edges })
          this.mostrarTudo();
        });
  }
  removeDuplicates = (myArr, prop) => {
    return myArr.filter((obj, pos, arr) => {
      return arr.map(mapObj => mapObj[prop]).indexOf(obj[prop]) === pos;
    });
  }
  buscarPesquisador = (id) => {
    axios.post('/Search/ById',
      {
        id: id,
      }).then(({ data }) => {
        this.state.nos.push(...data.nodes)
        this.state.arestas.push(...data.edges)
        this.setState({ nos: this.removeDuplicates(this.state.nos, 'id') });
        this.desenhar(this.state.nos, this.state.arestas);
      });
  }
  desenhar = (nos, arestas) => {
    console.log(nos, arestas);
    let nodes = new vis.DataSet(nos);
    let edges = new vis.DataSet(arestas);
    let data = {
      nodes: nodes,
      edges: edges
    };

    let options = {

    };
    if (this.state.orientacao) {
      options = {
        layout: {
          improvedLayout: false,
          hierarchical: {
            direction: this.state.orientacao,
            sortMethod: "directed",
            nodeSpacing: (nos.length % 3) * 110,
            enabled: true,
          }
        },
        //interaction: {dragNodes :false},
        physics: {
          enabled: false
        },
        //  configure: {
        //    filter: function (option, path) {
        //      if (path.indexOf('hierarchical') !== -1) {
        //        return true;
        //      }
        //      return false;
        //    },
        //    showButton: false
        //  }
      }
    }
    let network = new vis.Network(this.container, data, options);
    let exibe = this.exibe;
    let mostrar = this.mostrarInfo;
    network.on("doubleClick",
      function (params, ott) {
        let selecionado = this.body.data.nodes._data[params.nodes[0]];
        //params.event = "[original event]";
        //document.getElementById('eventSpan').innerHTML =
        //    '<h2>Click event:</h2>' + JSON.stringify(params, null, 4);
        //params.nodes; // para ser buscado
        console.log(selecionado);
        if (selecionado)
          exibe(selecionado.id);
      });

    network.on("click",
      function (params, ott) {
        let selecionado = this.body.data.nodes._data[params.nodes[0]]
        console.log(selecionado)
        mostrar(JSON.stringify(params, null, 4));
      });
    /*  let popupMenu;
    
    this.container.addEventListener('contextmenu', function (e) {

      if (popupMenu !== undefined) {
        popupMenu.parentNode.removeChild(popupMenu);
        popupMenu = undefined;
      }

      if (network.getSelection().nodes.length > 0) {
        var offsetLeft = 100; //this.container.offsetLeft;
        var offsetTop = 100; //this.container.offsetTop;

        popupMenu = document.createElement("div");
        popupMenu.className = 'popupMenu';
        popupMenu.style.left = e.clientX - offsetLeft + 'px';
        popupMenu.style.top = e.clientY - offsetTop + 'px';
        this.container.appendChild(popupMenu);
      }
      e.preventDefault()

    }, false);
    */

  }

  mostrarInfo = (info) => {
    this.setState({ dados: info });
  }

  exibe = (id) => {
    this.buscarPesquisador(id);
  };
  mostrarTudo = () => {
    const { nos, arestas } = this.state;
    this.desenhar(nos, arestas);
  }
  iniciar = (idInit) => {
    if (idInit) {
      this.desenhar(this.state.nos.filter(x => x.id === idInit), undefined);
    } else {
      this.desenhar([...this.state.nos[0]], undefined);
    }
    this.setState({ arestasSelecionadas: [] });
    this.setState({ nosSelecionados: [] });
  };

  render() {
    return (
      <Grid fluid>
        <Card>
          <CardContent>
            <Row>
              <div
                className="mynetwork"
                style={{ width: "80%", height: "500px", marginLeft: "auto", marginRight: "auto", border: "1px solid black" }}
                ref={(component) => { this.container = component; }} />
            </Row>
            <Row>
              <pre><code>{this.state.dados}</code></pre>
            </Row>
          </CardContent>
        </Card>
      </Grid>

    );
  }
}

export default Arvore;
