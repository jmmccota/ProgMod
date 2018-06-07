import React, { Component } from 'react';
import axios from 'axios';
import { Link, withRouter } from 'react-router-dom';
import { reduxForm } from 'redux-form';
import Button from 'material-ui-next/Button';
import { Grid, Row, Col } from 'react-flexbox-grid';
import Card, { CardActions, CardContent } from 'material-ui-next/Card';

import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import overlayFactory from 'react-bootstrap-table2-overlay';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css'

import { Chip } from 'material-ui-next';
import CircularProgress from 'material-ui/CircularProgress';
import { TextFieldRedux } from '../../config/fields/TextFieldRedux';
import Divider from 'material-ui-next/Divider';

class SearchForm extends Component {
  state = {
    busca: [],
  };

  pesquisar = (values) => {
    return axios
      .post('Search/ByName',
        {
          Search: values.search
        }).then(({ data }) =>
          this.setState({ busca: data })
        );
  }
  collumnFormatter = (cell, row) => {
    return (
      <Link to={`/person/${row.personID}`}>
        <Chip label={cell} />
      </Link>
    );
  }

  render() {
    const { handleSubmit, pristine, reset, submitting } = this.props;
    const columns = [{
      dataField: 'personID',
      text: 'Ver Arvore',
      hidden: true
    }, {
      dataField: 'name',
      text: 'Nome',
      formatter: this.collumnFormatter
    },];
    const options = {
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
        text: 'All', value: this.state.busca.length
      }] // A numeric array is also available. the purpose of above example is custom the text
    };

    return (
      <div>
        <Grid fluid>
          <Card>
            <CardContent>
              <Row>
                <TextFieldRedux name="search" label="Digite sua busca" style={{ width: "100%" }} />
              </Row>
            </CardContent>
            <CardActions>
              <Grid fluid>
                <Col mdOffset={8} xs={12} md={4}>
                  <Row>
                    <Col xs={12} md={2}>
                      {submitting && <CircularProgress thickness={7} />}
                    </Col>
                    <Col xs={12} md={5}>
                      <Button variant="raised" color="primary" style={{ width: "100%" }} disabled={pristine || submitting} onTouchTap={handleSubmit(this.pesquisar)}>Pesquisar</Button>
                    </Col>
                    <Col xs={12} md={5}>
                      <Button variant="raised" color="secondary" style={{ width: "100%" }} disabled={pristine || submitting} onClick={reset}>Limpar</Button>
                    </Col>
                  </Row>
                </Col>
              </Grid>
            </CardActions>
          </Card>
          <Divider />
          {this.state.busca.length > 0 &&
            <Card>
              <CardContent>
                <BootstrapTable
                  keyField='personID'
                  data={this.state.busca}
                  columns={columns}
                  pagination={paginationFactory(options)}
                  overlay={overlayFactory({ spinner: true, background: 'rgba(192,192,192,0.3)' })}
                />
              </CardContent>
            </Card>
          }
        </Grid>


      </div>
    );
  }
}

const SearchRedux = reduxForm({
  form: 'SearchForm', // a unique identifier for this form
  //validate,
})(SearchForm);

export const Search = withRouter(SearchRedux);
