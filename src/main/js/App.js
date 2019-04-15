import React, { Component } from 'react';
import QuestionMenu from './questionMenu.js'
import AppBar from './AppBar.js'
import Login from './Login.js'

class App extends Component {
  state = {
    userId: null,
  }

  renderPages = () => {
    if(this.state.userId == null) {
      Console.log("sets id");
      return <Login setId = {this.setUserId}/>;
      
    } else {
      Console.log("just displays questionmenu");
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
        {this.renderPages()}
      </div>
    );
  }
}

export default App;
