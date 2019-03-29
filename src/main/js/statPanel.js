import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const styles = {
  card: {
    minWidth: 100,
    maxWidth: 500
    ,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 20,
  },
  pos: {
    marginBottom: 12,
  },
};

class StatCard extends React.Component {
  state = {
    answer: null,
    questionText: "This is a Question."
  }

  render () {
    return (
      <Card className={styles.card}>
        <CardContent>
          <Typography className={styles.title} color="textSecondary" gutterBottom>
            About the Answer You Chose
          </Typography>
          <Typography variant="h5" component="h2">
            {this.state.answer}
          </Typography>
          <Typography className={styles.pos} color="textSecondary">
            <ul>
              <li> 73% of people agree with you </li>
              <li> Other statistics here </li>
            </ul>
          </Typography>
        </CardContent>
        <CardActions>
          <Button size="small">Learn More</Button>
        </CardActions>
      </Card>
    );
  }
}


export default withStyles(styles)(StatCard);
