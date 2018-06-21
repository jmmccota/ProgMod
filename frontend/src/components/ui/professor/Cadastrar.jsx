import React from 'react';
import { Grid, Row, Col } from 'react-flexbox-grid';
import { CircularProgress, Button, Card, CardContent, CardActions, CardHeader } from '@material-ui/core';
import axios from 'axios';
import { withRouter } from 'react-router-dom';
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
      .post('/professors/salvar', values)
      .then(({ data }) => {
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
              title="Cadastro, visualização e alteração de professores."
            />
            <CardContent>
              <Grid fluid>
                <Row>
                  <Col xs={12} md={6}>
                    <TextFieldRedux name="firstName" label="Digite o nome" style={{ width: "100%" }} />
                  </Col>
                  <Col xs={12} md={6}>
                    <TextFieldRedux name="lastName" label="Digite o sobrenome" style={{ width: "100%" }} />
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
