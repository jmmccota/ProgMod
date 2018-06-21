import React from 'react';
import axios from 'axios';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import { Card, CardContent, Grid, Divider, Button } from '@material-ui/core';
import Tabela from '../utils/Tabela';
import { Cadastrar } from './Cadastrar';
import DeleteIcon from '@material-ui/icons/Delete';


export default class ProfessorList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    }
    this.carregar();
  }
  carregar = () => {
    return axios
      .get('/professors/obterTodos')
      .then(({data}) => {
        this.setState({ data });
      });
  }
  excluir = (val) => {
    console.log(val);
  }
  formatAcoes = (cell, row) => {
    return (
      <div>
        <Button variant="fab" aria-label="delete" onClick={() => this.excluir(cell)}>
          <DeleteIcon />
        </Button>
      </div>
    );
  }
  render() {
    const columns = [{
      dataField: 'id',
      text: 'Código do professor',
      sort: true,
    }, {
      dataField: 'firstName',
      text: 'Nome',
      filter: textFilter()
    }, {
      dataField: 'lastName',
      text: 'Sobrenome',
      filter: textFilter()
    }, {
      dataField: 'id',
      text: 'Ações',
      formatter: this.formatAcoes,
    }];
    return (
      <div>
        <Grid fluid>
          <Cadastrar
            callbackCarregar={this.carregar}
          />
          <Divider />
          <Card>
            <CardContent>
              <Tabela
                keyField="id"
                data={this.state.data}
                columns={columns}
                filter={filterFactory()}
                noDataIndication={'Não existem professores cadastrados'}
              />
            </CardContent>
          </Card>
        </Grid >
      </div>
    );
  }
}

