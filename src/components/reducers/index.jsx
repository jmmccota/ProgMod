import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';
import { routerReducer } from 'react-router-redux';
import { notification } from './notificationReducer';

const rootReducer = combineReducers({
  form: formReducer,
  router: routerReducer,
  notification,
});

export default rootReducer;
