import React from 'react';
import axios from 'axios';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import { Card, CardContent } from '@material-ui/core';
import Tabela from '../utils/Tabela';


export default class ProfessorList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    }
    this.carregar();
  }
  carregar = () => {
    return axios.get('/professors/obterTodos').then(resp => {
      console.log(resp);
      this.setState({ data: resp });
    });
  }
  render() {
    const columns = [{
      dataField: 'id',
      text: 'Código do professor',
    }, {
      dataField: 'firstName',
      text: 'Nome',
      filter: textFilter()
    }, {
      dataField: 'lastName',
      text: 'Sobrenome',
      filter: textFilter()
    }];
    return (
      <div>
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
      </div>
    );
  }
}

