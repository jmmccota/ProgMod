import React from 'react';
import { Field } from 'redux-form';
import {
  Select,
} from 'redux-form-material-ui'
import { MenuItem } from '@material-ui/core';

export class SelectivityRedux extends React.Component {
  render() {
    return (
      <div className="form-group">
        {this.props.rotulo && <span className="control-label">{this.props.rotulo}</span>}
        <br/>
        <Field
          {...this.props}
          component={Select}
          style={{
            width:'100%',
          }}
        >
        {this.props.items.map(i => <MenuItem value={i.id}>{i.text}</MenuItem>)}
        </Field>
      </div>
    );
  }
}
