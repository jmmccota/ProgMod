import React, { Component } from 'react';

import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import overlayFactory from 'react-bootstrap-table2-overlay';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css'

export default class Tabela extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      options: {
        paginationSize: 4,
        pageStartIndex: 0,
        // alwaysShowAllBtns: true, // Always show next and previous button
        // withFirstAndLast: false, // Hide the going to First and Last page button
        // hideSizePerPage: true, // Hide the sizePerPage dropdown always
        // hidePageListOnlyOnePage: true, // Hide the pagination list when only one page
        firstPageText: 'Primeira',
        prePageText: 'Voltar',
        nextPageText: 'Avançar',
        lastPageText: 'Ultima',
        nextPageTitle: 'Primeira pagina',
        prePageTitle: 'Pre page',
        firstPageTitle: 'Proxima pagina',
        lastPageTitle: 'Ultima pagina',
        sizePerPageList: [{
          text: '15', value: 15
        }, {
          text: '5', value: 5
        }, {
          text: '10', value: 10
        }, {
          text: 'All', value: props.data.length
        }], // A numeric array is also available. the purpose of above example is custom the text
        ...props.options,
      }
    }
  }
  render() {
    const { keyField, data, columns } = this.props;
    return (
      <BootstrapTable
        {...this.props}
        keyField={keyField}
        data={data}
        columns={columns}
        striped
        pagination={paginationFactory(this.state.options)}
        overlay={overlayFactory({ spinner: true, background: 'rgba(192,192,192,0.3)' })}
      />
    );
  }
}
