import QuestionDisplay from './form.js';
import React from 'react';
import StatCard from './statPanel.js'
import axios from 'axios'



class QuestionMenu extends React.Component {

  getQuestion() {
  axios({
    method: 'get',
    url: '/questions',
    baseURL: ""
  }).then(function(response) {
    console.log(response);
  })
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
