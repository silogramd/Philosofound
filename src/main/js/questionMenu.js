import QuestionDisplay from './form.js';
import React from 'react';
import StatCard from './statPanel.js'
import axios from 'axios'

const axiosConfig = {
  baseURL: "http://philosofound.com",
  timeout: 30000
}

class QuestionMenu extends React.Component {

  getQuestion() {
  axios({
    method: 'get',
    url: '/questions'
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
