import React from 'react';
import Paper from 'material-ui-next/Paper';
import Typography from 'material-ui-next/Typography';

export default class TheScienceTree extends React.Component {
  render(){
    return (
      <Paper>
        <Typography variant="headline" component="h1">
        The Science Tree Project
        </Typography>
        <Typography component="p">
        Along the history, many researchers have provided remarkable contributions to science, not only advancing knowledge but also in terms of mentoring new scientists. Currently, identifying and studying the formation of researchers over the years is a challenging task as current repositories of theses and dissertations are cataloged in a decentralized way through many local digital libraries. In this project, we give a first step towards building a large repository that records the academic genealogy of researchers across fields and countries. We crawled data from the Lattes Platform and developed a framework to extract academic genealogy trees from this data, providing a series of analyses that describe the main properties of the academic genealogy trees. Our effort identified interesting findings related to the structure of academic formation, which highlight the importance of cataloging academic genealogy trees. We hope our initial framework will be the basis of a much larger crowdsourcing system. 
        </Typography>
      </Paper>
    );
  }
}
