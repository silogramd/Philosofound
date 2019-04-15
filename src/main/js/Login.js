import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import TextField from '@material-ui/core/TextField';
import axios from 'axios';

function rand() {
  return Math.round(Math.random() * 20) - 10;
}

function getModalStyle() {
  const top = 50 + rand();
  const left = 50 + rand();

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}

const styles = theme => ({
  paper: {
    position: 'absolute',
    width: theme.spacing.unit * 50,
    backgroundColor: theme.palette.background.paper,
    boxShadow: theme.shadows[5],
    padding: theme.spacing.unit * 4,
    outline: 'none',
  },
});

class SimpleModal extends React.Component {

    state = {
        username: null,
        password: null,
        noUsernameError: false,
        wrongPasswordError: false
    }

    handleUsernameChange = username => event => {
        this.setState({
            [username]: event.target.value
        });
    }

    handlePasswordChange = password => event => {
        this.setState({
            [password]: event.target.value
        });
    }

    handleLogin = () => {
        axios.get('/api/questions/1/').then(response => 
              console.log(response));
          }
    }

    hasError = () => {
        if(this.state.noUsernameError || this.state.wrongPasswordError) {
            return <div>Incorrect Login Information</div>;
        } 
    }

    
  render() {
    const { classes } = this.props;

    return (
      <div>
        <Modal
          aria-labelledby="simple-modal-title"
          aria-describedby="simple-modal-description"
          open={true}
        >
          <div style={getModalStyle()} className={classes.paper}>
            {this.hasError}
            <TextField
                id = "outlined-required"
                label = "Username"
                value = {this.state.username}
                required = {true}
                autoFocus = {true}
                error = {this.noUsernameError || this.wrongPasswordError}
                onChange = {this.handleUsernameChange('username')}
            />
            <TextField
                id = "outlined-required"
                label = "Password"
                value = {this.state.password}
                required = {true}
                error = {this.noUsernameError || this.wrongPasswordError}
                onChange = {this.handlePasswordChange('password')}
            />
             <Button variant="outlined" color="primary" handleChange = {this.handleLogin}>
                Log In
            </Button>
            <SimpleModalWrapped />
          </div>
        </Modal>
      </div>
    );
  }
}

const SimpleModalWrapped = withStyles(styles)(SimpleModal);

export default SimpleModalWrapped;