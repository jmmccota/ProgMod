import axios from 'axios';
import Fuse from 'fuse.js';
import isArray from 'lodash/isArray';
import omit from 'lodash/omit';
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import Selectivity from 'selectivity/react';
import '../../../../../node_modules/selectivity/styles/selectivity.css';

const fuseOptions = {
  shouldSort: true,
  threshold: 0.6,
  location: 0,
  distance: 100,
  maxPatternLength: 32,
  minMatchCharLength: 1,
  keys: [
    'text',
  ],
};

/**
 * Componente que implementa o componenete SelectivityConfig.js, localizado
 * em: https://github.com/arendjr/selectivity
 */
export default class extends Component {
  static propTypes = {
    //   // eslint-disable-next-line
    onSelect: PropTypes.func,
    //   // eslint-disable-next-line
    ajax: PropTypes.object,
    items: PropTypes.arrayOf((array, key) => {
      const element = array[key];

      if (element.id === undefined || element.descricao === undefined) {
        return new Error(`${'As propriedades {id, descricao} sao obrigatorias para o componente SynergiaSelectivity! ' +
          'O elemento analisado contem as seguintes propriedades: '}${JSON.stringify(element)}`);
      }

      return undefined;
    }),
  };

  constructor(props) {
    super(props);
    this.query = null;
    this.state = {
      limiteItens: this.props.limiteItens ? this.props.limiteItens : Infinity,
      dropDown: true,
      errorLimit: false,
    };
    const { ajax, items, query } = this.props;

    // Se não existe uma função ajax, DEVE existir items ou query
    if (!ajax && !items && !query) {
      console.error('As propriedades {items, ajax, query} sao mutuamente exclusivas e uma delas é' +
        ' obrigatória!');
    }

    if (props.items) {
      this.createFuse(props.items);
    }
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.items !== nextProps.items) {
      if (nextProps.items) {
        this.createFuse(nextProps.items);
      } else {
        this.fuse = undefined;
      }
    }
    if (nextProps.multiple === true && nextProps.limiteItens) {
      if (nextProps.value === '' || nextProps.value.length < nextProps.limiteItens) {
        this.setState({ dropDown: true, errorLimit: false });
      }
    }
  }

  createFuse(items) {
    this.items = items.map(item => ({ id: item.id, text: item.descricao }));
    this.fuse = new Fuse(this.items, fuseOptions);
    // eslint-disable-next-line
    this.query = ({ callback, error, offset, term }) => {
      let results;
      if (term) {
        results = this.fuse.search(term.trim());
      } else {
        results = this.items;
      }
      callback({ results, more: false });
    };
  }

  transformaChaveEmItem(value) {
    const { items } = this.props;

    if (isArray(value)) {
      const mapaPorId = {};
      for (const item of items) {
        if (value.includes(item.id)) {
          mapaPorId[item.id] = item;
        }
      }
      const itensSelecionados = [];
      for (const chave of value) {
        const item = mapaPorId[chave];
        const { id, descricao, rest } = item;
        const itemSelecionado = {
          id,
          text: descricao,
          rest,
        };
        if (itensSelecionados.length < this.state.limiteItens) {
          itensSelecionados.push(itemSelecionado);
          if (itensSelecionados.length === this.state.limiteItens) {
            this.setState({
              dropDown: false,
              errorLimit: true,
            });
          } else {
            this.setState({ dropDown: true, errorLimit: false });
          }
        }
      }
      return itensSelecionados;
    }
    return value;
  }

  handleChange(event) {
    const {
      onChange, onFocus, onBlur, multiple,
    } = this.props;

    onFocus();

    // Verificação para campo single
    if (!event.value && !multiple) {
      // se event.item for indefinido, retorna null (reseta campo)
      onBlur();
      onChange(event.item || null);
    } else if (multiple) {
      onBlur();
      onChange(this.transformaChaveEmItem(event.value));
    }
  }

  render() {
    let { value } = this.props;
    const {
      multiple, active, valid, touched, errorText,
    } = this.props;
    if (!value || value === '') {
      value = null;
    } else if (!isArray(value) && value.text === undefined) {
      value = {
        value,
        text: value.descricao,
      };
    }

    return (
      <div>
        <Selectivity.React
          allowClear={this.props.allowClear ? this.props.allowClear : true}
          //          style={{ outlineColor: 'white' }}
          {...omit(this.props, 'meta', 'items', 'textoAjuda')}
          value={undefined}
          data={value}
          // onChange={this.handleChange}
          showDropdown={this.state.dropDown}
          // onSelect={(data) => {
          //   // Chama o método somente se for single
          //   if (!multiple) {
          //     this.handleChange(data);
          //   }
          // }}
          query={this.query}
          readOnly={this.props.disabled || false}
        />
        {((!touched && !active) || valid) && <hr className="selectivity-hr" />}
        {(this.state.errorLimit) &&
          <span
            style={{
              color: '#ffa500', // precisa ser laranjado, pois é apenas um aviso
              fontSize: 12,
              margin: '0 0 10px',
            }}
          >
            <hr className="selectivity-hr_aviso" style={{ marginTop: 0 }} />
            {`É possível inserir até ${this.state.limiteItens} itens`}
          </span>
        }

        {(!valid && touched) &&
          <span
            style={{
              color: '#f44336',
              fontSize: 12,
              margin: '0 0 10px',
            }}
          >
            {errorText}
          </span>}
      </div>
    );
  }
}
/**
 * Faz a requisição get() para obter dados de uma url.
 * Aussume que a resposta de requisição trará dados, os quais serão
 * acessíveis a partir do acesso de data.dados
 * @param url
 * @param map Função que é aplicada para cada elemento retornado na propriedade data.dados
 * @returns {Promise}
 */
export const selectivityFetch = (url, map) => new Promise((resolve, reject) => {
  axios.get(url)
    .then(({ data: { dados } }) => {
      // Aplica a transformação passada pela chamada do Componente
      let resultado = dados.map(map);

      // Aplica a transformação para converter {text} para {descricao}
      resultado = resultado.map((item) => {
        const { id, descricao, rest } = item;
        return { id, text: descricao, rest };
      });

      resolve({
        results: resultado,
        more: resultado.length > 0,
      });
    })
    .catch(error => reject(error));
});
