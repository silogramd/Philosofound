import React, { Component } from 'react';
import QuestionMenu from './questionMenu.js'
import AppBar from './AppBar.js'

class App extends Component {
  state = {
    userId: null,
  }

  renderPages = () => {
    if(this.state.userId === null) {
      return <Login setId = {this.setUserId}/>;
    } else {
      return <QuestionMenu userId = {this.state.userId}/>;
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
