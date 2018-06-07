import React from 'react';
import { white } from 'material-ui/styles/colors';
import { withStyles } from 'material-ui-next/styles';
import AppBar from 'material-ui-next/AppBar';
import Toolbar from 'material-ui-next/Toolbar';
import Typography from 'material-ui-next/Typography';
import IconButton from 'material-ui-next/IconButton';
import MenuIcon from 'material-ui-icons/Menu';

const styles = theme => ({
  root: {
    width: '100%',
  },
  appBar: {
    backgroundColor: theme.palette.primary[800],
    boxShadow: '0px 0px 0px 0px rgba(0, 0, 0, 0.2), 0px 0px 0px 0px rgba(0, 0, 0, 0.14), 0px 0px 0px 0px rgba(0, 0, 0, 0.12)',
  },
  flex: {
    flex: 1,
  },
  menuButton: {
    marginLeft: -12,
    marginRight: 20,
  }
});

function Header(props) {
  const { classes } = props;
  return (
    <div className={classes.root}>
      <AppBar
        className={classes.appBar}
        /*style={{ backgroundColor: 'transparent' }}*/
        position="static">
        <Toolbar>
          {props.iconLeft === true ?
            <IconButton onClick={props.handleToggle} className={classes.menuButton} aria-label="Menu">
              <MenuIcon color={white} />
            </IconButton> : <div />}
          <Typography type="title" color="inherit" className={classes.flex}>
            {props.title}
          </Typography>
          <div className="userInfoNew">
            Informações do usuario
          </div>
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default withStyles(styles)(Header);
