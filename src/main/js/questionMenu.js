import QuestionDisplay from './form.js';
import React from 'react';
import StatCard from './statPanel.js'
import XMLHttpRequest from 'xhr'



class QuestionMenu extends React.Component {

  getQuestion() {
    XMLHttpRequest.responseType = "json"
    function load('http://philosofound.com/questions', callback) {
  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      callback(xhr.response);
    }
  }

  xhr.open('GET', url, true);
  xhr.send('');
}

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
        <StatCard />
      </div>
    )
  }
}

export default QuestionMenu;
