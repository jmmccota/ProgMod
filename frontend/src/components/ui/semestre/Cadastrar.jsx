import React from 'react';
import { Grid, Row, Col } from 'react-flexbox-grid';
import { CircularProgress, Button, Card, CardContent, CardActions, CardHeader } from '@material-ui/core';
import axios from 'axios';
import { withRouter } from 'react-router-dom';
import { reduxForm } from 'redux-form';
import { TextFieldRedux } from '../../config/fields/TextFieldRedux';
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
      .post('/semesters/salvar', values)
      .then(({ data }) => {
        this.setState({ busca: data });
        this.props.callbackCarregar &&
          this.props.callbackCarregar();
      });
  }

  render() {
    const { handleSubmit, pristine, reset, submitting } = this.props;
    const years = [];
    for (let i = 2010; i <= 2050; i++) {
      years.push({ id: i, text: `${i}` });
    }

    return (
      <div>
        <form onSubmit={handleSubmit(this.preventEnter)}>
          <Card>
            <CardHeader
              title="Cadastro, visualização e alteração de semestres."
            />
            <CardContent>
              <Grid fluid>
                <Row>
                  <Col xs={12} md={6}>
                    <SelectivityRedux
                      {...this.props}
                      name="semester"
                      rotulo="Semestre"
                      inputProps={{
                        name: 'semester',
                        id: 'semester-simple',
                      }}
                      placeholder="Selecione o semestre desejado"
                      items={[{ id: 1, text: '1o' }, { id: 2, text: '2o' }]}
                    />
                  </Col>
                  <Col xs={12} md={6}>
                    <SelectivityRedux
                      {...this.props}
                      name="year"
                      rotulo="Ano"
                      inputProps={{
                        name: 'year',
                        id: 'year-simple',
                      }}
                      placeholder="Selecione o ano desejado"
                      items={years}
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
