import omit from 'lodash/omit';
import PropTypes from 'prop-types';
import React from 'react';
import Selectivity from './Selectivity';

const SelectivityLabel = props => (
  <div style={{ whiteSpace: 'pre-wrap' }}>
    {props.rotulo && <span>{props.rotulo}</span>}

    <Selectivity
      id={props.id}
      name={props.name}
      placeholder={props.placeholder}
      {...omit(props, 'textoAjuda')}
    />
  </div>
);

SelectivityLabel.propTypes = {
  id: PropTypes.string,
  name: PropTypes.string,
  placeholder: PropTypes.string,
};

SelectivityLabel.defaultProps = {
  id: 'cidade',
  name: 'cidade',
  rotulo: 'Cidade (Selectivity)',
  placeholder: '',
};

export { SelectivityLabel };
