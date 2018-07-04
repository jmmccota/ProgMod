import React from 'react';
import { Grid, Row, Col } from 'react-flexbox-grid';
import { CircularProgress, Button, Card, CardContent, CardActions, CardHeader } from '@material-ui/core';
import axios from 'axios';
import { withRouter } from 'react-router-dom';
import { reduxForm } from 'redux-form';
import { SelectivityRedux } from '../../config/fields/selectivity/SelectivityRedux';

class CadastrarComponent extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      students: [],
      coursesOffering: [],
    }
    this.carregar();
  }

  carregar = () => {
    axios
      .get('/students/obterTodos')
      .then(({ data }) =>
        this.setState({
          students: data.map(d => ({
            id: d.id,
            text: `${d.registerNumber} - ${d.firstName}`,
          }))
        })
      );

    axios
      .get('/offerings/obterTodos')
      .then(({ data }) =>
        this.setState({
          coursesOffering: data.map(d => ({
            id: d.id,
            text: `${d.semester.semester}/${d.semester.year} - ${d.professor.firstName} - ${d.course.name}`,
          }))
        })
      );
  }

  preventEnter = (e) => {
    if (e.keyCode === 13 && e.shiftKey === false) {
      e.preventDefault();
      e.stopPropagation();
    }
  }

  salvar = (values) => {
    return axios
      .post('/enrollments', values)
      .then(({ data }) => {
        this.setState({ busca: data });
        this.props.callbackCarregar &&
          this.props.callbackCarregar();
      });
  }

  render() {
    const { handleSubmit, pristine, reset, submitting } = this.props;
    const { students, coursesOffering } = this.state;

    return (
      <div>
        <form onSubmit={handleSubmit(this.preventEnter)}>
          <Card>
            <CardHeader
              title="Cadastro, visualização e alteração de Alunos em Turmas."
            />
            <CardContent>
              <Grid fluid>
                <Row>
                  <Col xs={12} md={6}>
                    <SelectivityRedux
                      {...this.props}
                      name="student"
                      rotulo="Aluno"
                      inputProps={{
                        name: 'student',
                        id: 'student-simple',
                      }}
                      placeholder="Selecione o aluno"
                      items={students}
                    />
                  </Col>
                  <Col xs={12} md={6}>
                    <SelectivityRedux
                      {...this.props}
                      name="courseOffering"
                      rotulo="Oferta de Turma"
                      inputProps={{
                        name: 'courseOffering',
                        id: 'courseOffering-simple',
                      }}
                      placeholder="Selecione a oferta de turma"
                      items={coursesOffering}
                    />
                  </Col>
                </Row>
              </Grid>
            </CardContent>
            <CardActions>
              <Grid fluid>
                <Row>
                  <Col mdOffset={8} xs={12} md={4}>
                    <Row>
                      <Col xs={12} md={2}>
                        {submitting && <CircularProgress thickness={7} />}
                      </Col>
                      <Col xs={12} md={5}>
                        <Button variant="raised" color="primary" style={{ width: "100%" }} disabled={pristine || submitting} onClick={handleSubmit(this.salvar)}>Salvar</Button>
                      </Col>
                      <Col xs={12} md={5}>
                        <Button variant="raised" color="secondary" style={{ width: "100%" }} disabled={pristine || submitting} onClick={reset}>Limpar</Button>
                      </Col>
                    </Row>
                  </Col>
                </Row>
              </Grid>
            </CardActions>
          </Card>
        </form>
      </div>
    );
  }
}

const CadastrarRedux = reduxForm({
  form: 'CadastrarForm', // a unique identifier for this form
  //validate,
})(CadastrarComponent);

export const Cadastrar = withRouter(CadastrarRedux);
