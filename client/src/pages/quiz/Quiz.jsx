import React, { useState } from 'react';
import {
  Container,
  Grid,
  Paper,
  Typography,
  Stepper,
  Step,
  StepLabel,
  StepButton,
} from '@mui/material';
import QuestionCard from '../../components/quiz/QuestionCard';
import QuizContent from './QuizContent2';

export default function Quiz() {
  const steps = ['Q1', 'Q2', 'Q3'];
  const [activeStep, setActiveStep] = React.useState(0);
  const [skipped, setSkipped] = React.useState(new Set());

  const isStepSkipped = step => {
    return skipped.has(step);
  };

  const handleNext = () => {
    let newSkipped = skipped;
    if (isStepSkipped(activeStep)) {
      newSkipped = new Set(newSkipped.values());
      newSkipped.delete(activeStep);
    }

    setActiveStep(prevActiveStep => prevActiveStep + 1);
    setSkipped(newSkipped);
  };

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  };

  const handleSkip = () => {
    setActiveStep(prevActiveStep => prevActiveStep + 1);
    setSkipped(prevSkipped => {
      const newSkipped = new Set(prevSkipped.values());
      newSkipped.add(activeStep);
      return newSkipped;
    });
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  return (
    <>
      <Container maxWidth="xl">
        <Grid container>
          <Grid item xs={12}>
            <Paper
              sx={{ borderRadius: 2, width: '100vh', textAlign: 'center' }}
            >
              <Typography variant="h4">Quiz Title 4</Typography>
              <Typography variant="h6">This is the quiz description</Typography>
              <Typography variant="body1">For: Lesson Title</Typography>
              <Typography variant="body1">created at</Typography>
            </Paper>
          </Grid>
        </Grid>

        <QuizContent />
      </Container>
    </>
  );
}
