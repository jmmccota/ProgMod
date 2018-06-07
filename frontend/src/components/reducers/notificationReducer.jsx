const ADD_NOTIFICATION = 'ADD_NOTIFICATION';
const ADD_DEFAULT_SUCCESS_NOTIFICATION = 'ADD_DEFAULT_SUCCESS_NOTIFICATION';
const CLEAR_NOTIFICATIONS = 'CLEAR_NOTIFICATIONS';

export const notification = (state = {}, action) => {
  switch (action.type) {
    case ADD_NOTIFICATION:
      return {
        uid: action.payload.uid,
        message: action.payload.message,
        level: action.payload.level,
        action: action.payload.action,
        onRemove: action.payload.onRemove,
        clearAll: action.payload.clearAll,
      };
    case ADD_DEFAULT_SUCCESS_NOTIFICATION:
      return {
        uid: action.payload.uid,
        message: action.payload.message,
        level: action.payload.level,
        clearAll: action.payload.clearAll,
      };
    case CLEAR_NOTIFICATIONS:
      return { clearAll: action.payload.clearAll };
    default:
      return state;
  }
};
