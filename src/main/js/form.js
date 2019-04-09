import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import axios from 'axios'

const styles = theme => ({
  root: {
    display: 'flex',
  },
  formControl: {
    margin: theme.spacing.unit * 3,
  },
  group: {
    margin: `${theme.spacing.unit}px 0`,
  },
});

class QuestionDisplay extends React.Component {
  state = {
    value: 0,

  };

  constructor(props) {
    super(props);

    this.questionText = "What is the meaning of life?";
    this.answers = ["Up to each person to decide for themselvees.",
    "There is no meaning.","Happiness.","Success."];
  }

  getQuestion() {
    axios.get('./api/questions').then(response => console.log(response))
  }

  handleChange = event => {
    this.getQuestion()
    this.setState({ value: event.target.value });
  };

  makeItems() {
    var components = [];
    for(var i = 0; i < this.answers.length; i++) {
      components[i] = <FormControlLabel value={String(i)} control={<Radio />} label={this.answers[i]} />;
    }
    return components;
  }

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.root}>
        <FormControl component="fieldset" className={classes.formControl}>
          <FormLabel component="legend">{this.questionText}</FormLabel>
          <RadioGroup
            aria-label="Popular Answers To This Question"
            name="answers"
            className={classes.group}
            value={this.state.value}
            onChange={this.handleChange}
          >
            {this.makeItems()}
          </RadioGroup>
        </FormControl>
      </div>
    );
  }
}

QuestionDisplay.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(QuestionDisplay);
