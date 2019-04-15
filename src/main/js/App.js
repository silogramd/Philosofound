import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import QuestionMenu from './questionMenu.js'
import AppBar from './AppBar.js'

class App extends Component {
  state = {
    userId: null,
  }

  renderPages = () => {
    if(this.state.userId !== null) {
      return <QuestionMenu />;
    } else {
      return <Login setId = {this.setUserId}/>;
    }
  }

  setUserId = (userId) => {
    this.setState({
      userId
    });
  }
  


  render() {
    return (
      <div className="App">
        <AppBar />
        {this.renderPages}
      </div>
    );
  }
}

export default App;
