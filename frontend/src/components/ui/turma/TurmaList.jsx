import React from 'react';
import axios from 'axios';
import filterFactory, { textFilter } from 'react-bootstrap-table2-filter';
import { Card, CardContent, Grid, Divider, Button } from '@material-ui/core';
import Tabela from '../utils/Tabela';
import { Cadastrar } from './Cadastrar';
import DeleteIcon from '@material-ui/icons/Delete';
import GroupIcon from '@material-ui/icons/GroupAdd';
import ModalMonitor from './ModalMonitor';

export default class TurmaList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      alunos: [],
      value: 3,
      open: false,
      modalData: undefined,
    }
    this.carregar();
  }
  carregar = () => {
    axios
      .get('/students/obterTodos')
      .then(({ data }) =>
        this.setState({
          alunos: data.map(d => ({
            id: d.id,
            text: `${d.firstName} ${d.lastName}`,
          }))
        })
      );

    if (this.state.alunos.length > 0) {
      console.log(this.state.value);
      this.setState({ value: this.state.alunos.first.id });
    }
    return axios
      .get('/offerings/obterTodos')
      .then(({ data }) => {
        this.setState({ data });
      });

  }
  excluir = (val) => {
    return axios.delete('/offerings/' + val)
      .then(() => {
        this.carregar();
      });
  }

  formatCourse = (cell, row) => {
    return (
      `${cell.code} - ${cell.name}`
    );
  }

  formatSemester = (cell, row) => {
    return (
      `${cell.semester}/${cell.year}`
    );
  }

  formatProfessor = (cell, row) => {
    return (
      `${cell.firstName} ${cell.lastName}`
    );

  }

  handleToggleModal = (cell, row) => {
    console.log(this.state.alunos);
    this.setState({
      open: !this.state.open,
      modalData: this.state.data.find(d => d.id === cell),
      alunosData: this.state.alunos,
      valueFirst: this.state.value
    });
  };



  formatAcoes = (cell, row) => {
    return (
      <div>
        <Button variant="fab" aria-label="delete" onClick={() => this.excluir(cell)}>
          <DeleteIcon />
        </Button>
        {/* <Button variant="fab" aria-label="edit" onClick={() => this.handleToggleModal(cell)}>
          <GroupIcon />
        </Button> */}
      </div>
    );
  }

  render() {
    const columns = [{
      dataField: 'id',
      text: 'Código do Curso',
      sort: true,
    }, {
      dataField: 'course',
      text: 'Disciplina',
      filter: textFilter(),
      formatter: this.formatCourse,
    }, {
      dataField: 'semester',
      text: 'Semestre',
      filter: textFilter(),
      formatter: this.formatSemester,
    }, {
      dataField: 'professor',
      text: 'Professor',
      filter: textFilter(),
      formatter: this.formatProfessor,
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
              />
            </CardContent>
          </Card>
        </Grid >
        <ModalMonitor
          open={this.state.open}
          data={this.state.modalData}
          alunos={this.state.alunos}
          value={this.state.value}
          handleToggle={this.handleToggleModal}
        />
      </div>
    );
  }
}

