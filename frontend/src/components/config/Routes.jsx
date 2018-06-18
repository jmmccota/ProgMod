import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';
import { Provider } from 'react-redux';
//import ReactDOM from 'react-dom';
import { store, customHistory } from './ReduxConfig';

import { Layout } from '../ui/layout/Layout';
import { Search } from '../ui/search/Search';
import TheScienceTree from '../ui/Main';
import NotFound from '../ui/NotFound';

import injectTapEventPlugin from 'react-tap-event-plugin';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';

import './AxiosConfig';
import ProfessorList from '../ui/professor/ProfessorList';
import AlunoList from '../ui/aluno/AlunoList';
import DisciplinaList from '../ui/disciplina/DisciplinaList';

injectTapEventPlugin();

const newTheme = createMuiTheme();
global.muiTheme = newTheme;

const renderLayout = Componente => (props) => {
  const elemento = (<Componente params={props.match.params} {...props} />);
  return (
    <Layout {...props}>
      {elemento}
    </Layout>
  );
};

const Routes = () => (
  <Provider store={store}>
    <MuiThemeProvider theme={newTheme}>
        <Router history={customHistory}>
          <Switch>
            <Route exact path="/" render={renderLayout(TheScienceTree)} />
            <Route path="/professor" render={renderLayout(ProfessorList)} />
            <Route path="/aluno" render={renderLayout(AlunoList)} />
            <Route path="/disciplina" render={renderLayout(DisciplinaList)} />

            <Route path="/dashboard" render={renderLayout(NotFound)} />{/* Rota para painel geral */}

            <Route render={renderLayout(NotFound)} />
          </Switch>
        </Router>
    </MuiThemeProvider>
  </Provider>
);

export default Routes;
