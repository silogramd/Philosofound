import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import QuestionMenu from './questionMenu.js'
import AppBar from './AppBar.js'

class App extends Component {
  render() {
    return (
      <div className="App">
        <AppBar />
        <QuestionMenu />
      </div>
    );
  }
}

export default App;
