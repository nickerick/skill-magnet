import React from 'react';
import {
  Card,
  CardContent,
  CardActions,
  Button,
  Typography,
  FormControl,
  FormControlLabel,
  FormLabel,
  RadioGroup,
  Radio,
  Chip,
  TextField,
} from '@mui/material';

// Assuming props.question is the question object passed from QuizContent
export default function QuestionCard({
  question,
  currentAnswer,
  updateAnswer,
}) {
  // Determine if the question is of type 'MCQ' (Multiple Choice Question)
  const isMCQ = question.questionType === 'MCQ';
  // This function gets called when an option is selected
  const handleOptionSelect = optionId => {
    const answer = {
      questionId: question.questionId,
      shortAnswer: null, // since it's MCQ
      questionOptionId: optionId,
    };
    // Call a method passed down from QuizContent to update the answer
    updateAnswer(answer);
  };

  const handleTextInput = text => {
    const answer = {
      questionId: question.questionId,
      shortAnswer: text,
      questionOptionId: null,
    };

    updateAnswer(answer);
  };

  return (
    <Card elevation={3}>
      <CardContent>
        <Chip
          color="info"
          label={'Question ' + question.questionId}
          sx={{ mb: 1 }}
        />
        <Typography variant="h5" component="div">
          {question.questionText}
        </Typography>
        {/* Conditional rendering based on question type */}
        {isMCQ ? (
          <Typography sx={{ mb: 1.5 }} color="text.secondary">
            Multiple Choice
          </Typography>
        ) : (
          <Typography sx={{ mb: 1.5 }} color="text.secondary">
            Short Answer
          </Typography>
        )}

        {/* Render options if the question is a Multiple Choice Question */}
        {isMCQ ? (
          <FormControl>
            <FormLabel id="demo-radio-buttons-group-label" sx={{ my: 1 }}>
              Answer Group
            </FormLabel>
            <RadioGroup
              aria-labelledby="demo-radio-buttons-group-label"
              defaultValue="female"
              name="radio-buttons-group"
              sx={{ ml: 1 }}
            >
              {question.options.map(choice => (
                <FormControlLabel
                  control={
                    <Radio
                      checked={
                        currentAnswer?.questionOptionId === choice.optionId
                      }
                      onChange={() => handleOptionSelect(choice.optionId)}
                      value={choice.optionId}
                    />
                  }
                  label={choice.optionText}
                  key={choice.optionId}
                />
              ))}
            </RadioGroup>
          </FormControl>
        ) : (
          <>
            <TextField
              id="outlined-basic"
              label="Outlined"
              variant="outlined"
              value={currentAnswer?.shortAnswer || ''}
              onChange={event => {
                handleTextInput(event.target.value);
              }}
            />
          </>
        )}
      </CardContent>
      <CardActions></CardActions>
    </Card>
  );
}
