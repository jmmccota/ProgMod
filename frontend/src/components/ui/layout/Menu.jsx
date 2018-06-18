import React from 'react';
import { Link } from 'react-router-dom';
import { ListItem, ListItemIcon, ListItemText, List, Drawer, Divider } from '@material-ui/core';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import DraftsIcon from '@material-ui/icons/Drafts';
import StarIcon from '@material-ui/icons/Star';
import SendIcon from '@material-ui/icons/Send';
import MailIcon from '@material-ui/icons/Mail';
import DeleteIcon from '@material-ui/icons/Delete';
import ReportIcon from '@material-ui/icons/Report';

export const Menu = (props) =>
  <Drawer
    open={props.drawerOpen}
    onClose={props.handleToggle}
  >
    <div
      tabIndex={0}
      role="button"
      onTouchTap={props.handleToggle}
      onKeyDown={props.handleToggle}
    >
      <List>
        <ListItem component={Link} to="/professor">
          <ListItemText primary="Professor" />
        </ListItem>
        <ListItem component={Link} to="/turma">
          {/* <ListItemIcon>
            <StarIcon />
          </ListItemIcon> */}
          <ListItemText primary="Turma" />
        </ListItem>
        <ListItem component={Link} to="/aluno">
          <ListItemText primary="Aluno" />
        </ListItem>
      </List>
      <Divider />
      <List>
        <ListItem component={Link} to="/relatorio">
          <ListItemText primary="Relatorio" />
        </ListItem>
      </List>
    </div>
  </Drawer>
  ;
