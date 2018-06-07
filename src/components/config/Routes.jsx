import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';
import { Provider } from 'react-redux';
//import ReactDOM from 'react-dom';
import { store, customHistory } from './ReduxConfig';

import { Layout } from '../ui/layout/Layout';
import { Search } from '../ui/search/Search';
import TheScienceTree from '../ui/TheScienceTree';
import NotFound from '../ui/NotFound';

import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProviderOld from 'material-ui/styles/MuiThemeProvider';
import { MuiThemeProvider } from 'material-ui-next/styles';
import createMuiTheme from 'material-ui-next/styles/createMuiTheme';
import getMuiTheme from 'material-ui/styles/getMuiTheme';

import './AxiosConfig';

injectTapEventPlugin();

const newTheme = createMuiTheme();
const oldTheme = getMuiTheme();
global.muiTheme = oldTheme;

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
      <MuiThemeProviderOld muiTheme={oldTheme}>
        <Router history={customHistory}>
          <Switch>
            <Route exact path="/" render={renderLayout(TheScienceTree)} />
            <Route path="/search" render={renderLayout(Search)} />

            <Route path="/dashboard" render={renderLayout(NotFound)} />{/* Rota para painel geral */}

            <Route render={renderLayout(NotFound)} />
          </Switch>
        </Router>
      </MuiThemeProviderOld>
    </MuiThemeProvider>
  </Provider>
);

export default Routes;
