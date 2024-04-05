import * as React from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepButton from '@mui/material/StepButton';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import QuestionCard from '../../components/quiz/QuestionCard';

const questions = [
  {
    questionId: 1,
    questionText: 'What language will we be studying?',
    questionType: 'SA',
    correctShortAnswer: 'python',
    options: [],
  },
  {
    questionId: 2,
    questionText: 'What year was python invented?',
    questionType: 'MCQ',
    correctShortAnswer: 'N/A',
    options: [
      {
        optionId: 1,
        optionText: '2001',
        isCorrect: false,
      },
      {
        optionId: 2,
        optionText: '1990',
        isCorrect: false,
      },
      {
        optionId: 3,
        optionText: '1998',
        isCorrect: false,
      },
      {
        optionId: 4,
        optionText: '1991',
        isCorrect: true,
      },
    ],
  },
];
export default function QuizContent() {
  const [activeStep, setActiveStep] = React.useState(0);
  const [completed, setCompleted] = React.useState({});
  const [numQuestions, setNumQuestions] = React.useState(questions.length);
  const steps = questions.map((question, index) => `Q${index + 1}`);

  // const [steps, setSteps] = React.useState(questions.map((q, index) => ))
  const totalSteps = () => {
    return questions.length;
  };

  const completedSteps = () => {
    return Object.keys(completed).length;
  };

  const isLastStep = () => {
    return activeStep === totalSteps() - 1;
  };

  const allStepsCompleted = () => {
    return completedSteps() === totalSteps();
  };

  const handleNext = () => {
    const newActiveStep =
      isLastStep() && !allStepsCompleted()
        ? // It's the last step, but not all steps have been completed,
          // find the first step that has been completed
          steps.findIndex((step, i) => !(i in completed))
        : activeStep + 1;
    setActiveStep(newActiveStep);
  };

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  };

  const handleStep = step => () => {
    setActiveStep(step);
  };

  const handleComplete = () => {
    const newCompleted = completed;
    newCompleted[activeStep] = true;
    setCompleted(newCompleted);
    handleNext();
  };

  const handleReset = () => {
    setActiveStep(0);
    setCompleted({});
  };

  return (
    <Box sx={{ width: '100%' }}>
      <Stepper sx={{ m: 1 }} nonLinear activeStep={activeStep}>
        {steps.map((label, index) => (
          <Step key={label} completed={completed[index]}>
            <StepButton
              color="inherit"
              disableRipple
              onClick={handleStep(index)}
            >
              {label}
            </StepButton>
          </Step>
        ))}
      </Stepper>
      <div>
        {/* Finished Quiz */}
        {allStepsCompleted() ? (
          <React.Fragment>
            <Typography sx={{ mt: 2, mb: 1 }}>
              All steps completed - you&apos;re finished
            </Typography>
            <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
              <Box sx={{ flex: '1 1 auto' }} />
              <Button onClick={handleReset}>Reset</Button>
            </Box>
          </React.Fragment>
        ) : (
          <React.Fragment>
            <QuestionCard />

            <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
              <Button
                color="inherit"
                disabled={activeStep === 0}
                onClick={handleBack}
                sx={{ mr: 1 }}
              >
                Back
              </Button>
              <Box sx={{ flex: '1 1 auto' }} />
              <Button onClick={handleNext} sx={{ mr: 1 }}>
                Next
              </Button>
              {/* {activeStep !== steps.length &&
                (completed[activeStep] ? (
                  <Typography
                    variant="caption"
                    sx={{ display: 'inline-block' }}
                  >
                    Step {activeStep + 1} already completed
                  </Typography>
                ) : (
                  <Button onClick={handleComplete}>{'Submit'}</Button>
                ))} */}
            </Box>
          </React.Fragment>
        )}
      </div>
    </Box>
  );
}
