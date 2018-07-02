import React from 'react';
import { Grid, Row, Col } from 'react-flexbox-grid';
import { CircularProgress, Button, Card, CardContent, CardActions, CardHeader } from '@material-ui/core';
import axios from 'axios';
import { withRouter } from 'react-router-dom';
import { reduxForm } from 'redux-form';
import { SelectivityRedux } from '../../config/fields/selectivity/SelectivityRedux';

class CadastrarComponent extends React.Component {

  preventEnter = (e) => {
    if (e.keyCode === 13 && e.shiftKey === false) {
      e.preventDefault();
      e.stopPropagation();
    }
  }

  salvar = (values) => {
    return axios
      .post('/offerings', values)
      .then(({ data }) => {
        this.setState({ busca: data });
        this.props.callbackCarregar &&
          this.props.callbackCarregar();
      });
  }

  render() {
    const { handleSubmit, pristine, reset, submitting } = this.props;
    const semestres = [];
    const courses = [];
    const professors = [];

    axios
      .get('/semesters/obterTodos')
      .then(({ data }) => {
        data.forEach(
          function addItem(objSemestre) {
            semestres.push({ id: objSemestre.id, text: objSemestre.year + '/' + objSemestre.semester });
          }
        );
      });

    axios
      .get('/courses/obterTodos')
      .then(({ data }) => {
        data.forEach(
          function addItem(objCourse) {
            courses.push({ id: objCourse.id, text: objCourse.name });
          }
        );
      });


    axios
      .get('/professors/obterTodos')
      .then(({ data }) => {
        data.forEach(
          function addItem(objProfessor) {
            professors.push({ id: objProfessor.id, text: objProfessor.firstName + " " + objProfessor.lastName });
          }
        );
      });

      console.log(professors);
      console.log(courses);
      console.log(semestres);

    return (
      <div>
        <form onSubmit={handleSubmit(this.preventEnter)}>
          <Card>
            <CardHeader
              title="Cadastro, visualização e alteração de Cursos."
            />
            <CardContent>
              <Grid fluid>
                <Row>
                  <Col xs={12} md={4}>
                    <SelectivityRedux
                      {...this.props}
                      name="course"
                      rotulo="Disciplina"
                      inputProps={{
                        name: 'course',
                        id: 'course-simple',
                      }}
                      placeholder="Selecione a disciplina desejada"
                      items={courses}
                    />
                  </Col>
                  <Col xs={12} md={4}>
                    <SelectivityRedux
                      {...this.props}
                      name="semester"
                      rotulo="Semestre"
                      inputProps={{
                        name: 'semester',
                        id: 'semester-simple',
                      }}
                      placeholder="Selecione o semestre desejado"
                      items={semestres}
                    />
                  </Col>
                  <Col xs={12} md={4}>
                    <SelectivityRedux
                      {...this.props}
                      name="professor"
                      rotulo="Professor"
                      inputProps={{
                        name: 'professor',
                        id: 'professor-simple',
                      }}
                      placeholder="Selecione o professor desejado"
                      items={professors}
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
