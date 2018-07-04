import React from 'react';
import axios from 'axios';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import { Card, CardContent, Grid, Divider, Button } from '@material-ui/core';
import Tabela from '../utils/Tabela';
import { Cadastrar } from './Cadastrar';
import DeleteIcon from '@material-ui/icons/Delete';


export default class AlunoTurmaList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    }
    this.carregar();
  }
  carregar = () => {
    return axios
      .get('/enrollments/obterTodos')
      .then(({ data }) => {
        this.setState({ data });
      });
  }
  excluir = (val) => {
    return axios.delete('/enrollments/'+ val)
      .then(() => {
        this.carregar();
      });
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

  formatStudent = (cell, row) => {
    return (
      `${cell.registerNumber} - ${cell.firstName}`
    );
  }

  formatCourseOffering = (cell, row) => {
    return (
      `${cell.semester.semester}/${cell.semester.year} - ${cell.professor.firstName} - ${cell.course.name}`
    );
  }

  render() {
    const columns = [{
      dataField: 'id',
      text: 'Código do Curso',
      sort: true,
    }, {
      dataField: 'student',
      text: 'Aluno',
      filter: textFilter(),
      formatter: this.formatStudent,
    }, {
      dataField: 'courseOffering',
      text: 'Oferta de curso',
      filter: textFilter(),
      formatter: this.formatCourseOffering,
    },];
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
              />
            </CardContent>
          </Card>
        </Grid >
      </div>
    );
  }
}

