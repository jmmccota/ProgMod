import React from 'react';
import { Grid, Row, Col } from 'react-flexbox-grid';
import { CircularProgress, Button, Card, CardContent, CardActions, CardHeader } from '@material-ui/core';
import axios from 'axios';
import { Link, withRouter } from 'react-router-dom';
import { reduxForm } from 'redux-form';
import { TextFieldRedux } from '../../config/fields/TextFieldRedux';

class CadastrarComponent extends React.Component {

  preventEnter = (e) => {
    if (e.keyCode === 13 && e.shiftKey === false) {
      e.preventDefault();
      e.stopPropagation();
    }
  }

  salvar = (values) => {
    return axios
      .post('/courses/salvar',
        {
          data: values
        }).then(({ data }) => {
          this.setState({ busca: data });
          this.props.callbackCarregar &&
            this.props.callbackCarregar();
        });
  }

  render() {
    const { handleSubmit, pristine, reset, submitting } = this.props;
    return (
      <div>
        <form onSubmit={handleSubmit(this.preventEnter)}>
          <Card>
            <CardHeader
              title="Cadastro, visualização e alteração de disciplinas."
            />
            <CardContent>
              <Grid fluid>
                <Row>
                  <Col xs={12} md={6}>
                    <TextFieldRedux name="name" label="Descrição da disciplina" style={{ width: "100%" }} />
                  </Col>
                  <Col xs={12} md={6}>
                    <TextFieldRedux name="code" label="Digite o codigo institucional" style={{ width: "100%" }} />
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
                        <Button variant="raised" color="primary" style={{ width: "100%" }} disabled={pristine || submitting} onClick={this.salvar}>Salvar</Button>
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
