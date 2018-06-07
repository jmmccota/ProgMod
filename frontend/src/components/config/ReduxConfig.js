import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
// import createHistory from 'history/createBrowserHistory';
//import createBrowserHistory from 'history/createBrowserHistory';
import createHashHistory from 'history/createHashHistory';
import { routerMiddleware } from 'react-router-redux';
// import { createLogger } from 'redux-logger';

import reducers from '../reducers';

// const loggerMiddleware = createLogger();
// Create a history of your choosing (we're using a browser history in this case)
// export const customHistory = createBrowserHistory({
//  forceRefresh: true,
// });

export const customHistory = createHashHistory({
  basename: '/', // The base URL of the app (see below)
  hashType: 'slash', // The hash type to use (see below)
});

const middlewares = applyMiddleware(thunk, routerMiddleware(customHistory));

let enhancer;
if (window.__REDUX_DEVTOOLS_EXTENSION__) {
  enhancer = compose(
    middlewares,
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(),
  );
} else {
  enhancer = compose(middlewares);
}

export const store = createStore(reducers, enhancer);
