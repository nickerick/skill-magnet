import * as React from 'react';
import Box from '@mui/material/Box';
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
      { optionId: 1, optionText: '2001', isCorrect: false },
      { optionId: 2, optionText: '1990', isCorrect: false },
      { optionId: 3, optionText: '1998', isCorrect: false },
      { optionId: 4, optionText: '1991', isCorrect: true },
    ],
  },
];

console.log();
export default function QuizContent() {
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

  const handleComplete = () => {
    setCompleted(prevCompleted => ({
      ...prevCompleted,
      [activeQuestionIndex]: true,
    }));
    handleNext();
  };

  const handleReset = () => {
    setActiveQuestionIndex(0);
    setCompleted({});
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
    Object.keys(completed).length === questions.length;

  //   console.log(
  //     userAnswers.find(
  //       answer => answer.questionId === questions[activeQuestionIndex].questionId,
  //     ),
  //   );
  return (
    <Box sx={{ width: '100%' }}>
      {/* Question Display */}
      <React.Fragment>
        <QuestionCard
          question={questions[activeQuestionIndex]}
          updateAnswer={updateAnswer}
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
          {isLastQuestion() && allQuestionsCompleted() ? (
            <Typography sx={{ mt: 2, mb: 1 }}>
              All questions answered - you&apos;re finished
            </Typography>
          ) : (
            <Button onClick={handleNext} sx={{ mr: 1 }}>
              Next
            </Button>
          )}
          {activeQuestionIndex !== questions.length - 1 &&
            !completed[activeQuestionIndex] && (
              <Button onClick={handleComplete}>Submit</Button>
            )}
        </Box>
      </React.Fragment>

      {/* Reset Quiz */}
      {allQuestionsCompleted() && (
        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
          <Button onClick={handleReset}>Reset Quiz</Button>
        </Box>
      )}
    </Box>
  );
}
