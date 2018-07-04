import React from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import withMobileDialog from '@material-ui/core/withMobileDialog';

class ModalMonitor extends React.Component {
  render() {
    const { fullScreen, data } = this.props;    
    return (
      <div>
        <Dialog
          fullScreen={fullScreen}
          open={this.props.open}
          onClose={() => this.props.handleToggle(data.id)}
          scroll="paper"
          maxWidth="md"
          aria-labelledby="responsive-dialog-title"
        >
          <DialogTitle id="responsive-dialog-title">{"Use Google's location service?"}</DialogTitle>
          <DialogContent>
            <DialogContentText>
              <pre>
                <code>
                  {JSON.stringify(data, undefined, 2)}
                </code>
              </pre>
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button onClick={() => this.props.handleToggle(data.id)} color="primary">
              Disagree
            </Button>
            <Button onClick={() => this.props.handleToggle(data.id)} color="primary" autoFocus>
              Agree
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

ModalMonitor.propTypes = {
  fullScreen: PropTypes.bool.isRequired,
};

export default withMobileDialog()(ModalMonitor);
