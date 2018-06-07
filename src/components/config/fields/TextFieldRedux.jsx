import React from 'react';
import { Field } from 'redux-form';
import TextField from 'material-ui-next/TextField'

class CampoTexto extends React.Component {
  render() {
    const {
      id,
      input,
      label,
      meta: { touched, error },
      ...custom
    } = this.props;
    return (
      <TextField
        hintText={label}
        id={id}
        label={label}
        floatingLabelText={label}
        errorText={touched && error}
        {...input}
        {...custom}
      />
    );
  }
}

export class TextFieldRedux extends React.Component {
  render() {
    const { name, label, style } = this.props;
    return (
      <Field
        name={name}
        label={label}
        style={style}
        component={CampoTexto}
      />
    );
  }
}
