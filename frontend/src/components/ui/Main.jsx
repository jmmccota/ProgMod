import React from 'react';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

export default class Main extends React.Component {
  render(){
    return (
      <Paper>
        <Typography variant="headline" component="h1">
        Sistema Acadêmico
        </Typography>
        <Typography component="p">
        Projeto de programação modular
        </Typography>
      </Paper>
    );
  }
}
