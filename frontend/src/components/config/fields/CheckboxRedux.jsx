import React from 'react';
import { Field } from 'redux-form';
import { FormControl, Checkbox } from '@material-ui/core';

class CampoCheckbox extends React.Component {
  render() {
    const {
      id,
      input,
      label,
      checked,
      onChange,
      meta: { touched, error },
      ...custom
    } = this.props;
    return (
      <FormControlLabel
        control={
          <Checkbox
            checked={checked}
            onChange={onChange}
          />
        }
        label={label}
      />
    );
  }
}

export class CheckboxRedux extends React.Component {
  render() {
    const { name, label, style } = this.props;
    return (
      <Field
        name={name}
        label={label}
        style={style}
        component={CampoCheckbox}
      />
    );
  }
}
