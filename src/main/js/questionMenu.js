import QuestionDisplay from './form.js';
import React from 'react';
import StatCard from './statPanel.js';
import request from "request";

class QuestionMenu extends React.Component {

  getQuestion() {
    request('http://philosofound.com/questions', function (error, response, body) {
      console.log('error', error);
      console.log('statusCode', response && response.statusCode);
      console.log('body', body);
    });
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
