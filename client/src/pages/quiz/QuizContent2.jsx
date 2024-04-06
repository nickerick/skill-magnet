import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import QuestionCard from '../../components/quiz/QuestionCard';

export default function QuizContent({ questions, submitAnswers }) {
  const [activeQuestionIndex, setActiveQuestionIndex] = React.useState(0);
  const [completed, setCompleted] = React.useState({});
  const [userAnswers, setUserAnswers] = React.useState([]);

  const handleNext = () => {
    setActiveQuestionIndex(prevIndex =>
      Math.min(prevIndex + 1, questions.length - 1),
    );
  };

  const handleBack = () => {
    setActiveQuestionIndex(prevIndex => Math.max(prevIndex - 1, 0));
  };

  const updateAnswer = newAnswer => {
    setUserAnswers(prevAnswers => {
      // Check if an answer for the current question already exists
      const existingAnswerIndex = prevAnswers.findIndex(
        answer => answer.questionId === newAnswer.questionId,
      );

      if (existingAnswerIndex >= 0) {
        // Answer exists, update it
        const updatedAnswers = [...prevAnswers];
        updatedAnswers[existingAnswerIndex] = newAnswer;
        return updatedAnswers;
      } else {
        // Answer doesn't exist, add it
        return [...prevAnswers, newAnswer];
      }
    });
  };

  const isLastQuestion = () => activeQuestionIndex === questions.length - 1;
  const allQuestionsCompleted = () =>
    Object.keys(userAnswers).length === questions.length;

  return (
    <Box sx={{ width: '100%' }}>
      {/* Question Display */}
      <>
        <QuestionCard
          question={questions[activeQuestionIndex]}
          updateAnswer={updateAnswer}
          currentQuestionNum={activeQuestionIndex}
          currentAnswer={userAnswers.find(
            answer =>
              answer.questionId === questions[activeQuestionIndex].questionId,
          )}
        />

        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
          <Button
            color="inherit"
            disabled={activeQuestionIndex === 0}
            onClick={handleBack}
            sx={{ mr: 1 }}
          >
            Back
          </Button>
          <Box sx={{ flex: '1 1 auto' }} />

          <Button
            disabled={isLastQuestion()}
            onClick={handleNext}
            sx={{ mr: 1 }}
          >
            Next
          </Button>
          {allQuestionsCompleted() && (
            <Button onClick={() => submitAnswers(userAnswers)}>
              Submit Quiz
            </Button>
          )}
        </Box>
      </>
    </Box>
  );
}
