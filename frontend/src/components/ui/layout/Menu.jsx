import React from 'react';
import { Link } from 'react-router-dom';
import { ListItem, ListItemIcon, ListItemText, List, Drawer, Divider } from '@material-ui/core';
import EditIcon from '@material-ui/icons/Edit';
import ListIcon from '@material-ui/icons/List';

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
        <ListItem component="label">
          <ListItemIcon>
            <EditIcon />
          </ListItemIcon>
          <ListItemText primary="Cadastros" />
        </ListItem>
        <ListItem component={Link} to="/professor">
          <ListItemText primary="Professor" />
        </ListItem>
        <ListItem component={Link} to="/disciplina">
          <ListItemText primary="Disciplina" />
        </ListItem>
        <ListItem component={Link} to="/aluno">
          <ListItemText primary="Aluno" />
        </ListItem>
        <ListItem component={Link} to="/semestre">
          <ListItemText primary="Semestre" />
        </ListItem>
        <ListItem component={Link} to="/turma">
          <ListItemText primary="Curso" />
        </ListItem>
      </List>
      <Divider />
      <ListItem component="label">
        <ListItemIcon>
          <ListIcon />
        </ListItemIcon>
        <ListItemText primary="Relatórios" />
      </ListItem>
      <List>
        <ListItem component={Link} to="/relatorio">
          <ListItemText primary="Relatorio" />
        </ListItem>
      </List>
    </div>
  </Drawer>
  ;
