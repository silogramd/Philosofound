import QuestionDisplay from './form.js';
import React from 'react';
import StatCard from './statPanel.js'
import axios from 'axios'

class QuestionMenu extends React.Component {

  getQuestion() {
    axios.get('philosofound.com/questions').then(response => console.log(response[0].content.question))
  }

  render() {
    return (
      <div>
        <br />
        <QuestionDisplay />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <br />
        <StatCard />
      </div>
    )
  }
}

export default QuestionMenu;
