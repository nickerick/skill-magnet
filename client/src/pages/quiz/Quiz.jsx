import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
  Container,
  Grid,
  Paper,
  Typography,
  Chip,
  Stepper,
  Step,
  StepLabel,
  StepButton,
  Collapse,
} from '@mui/material';
import QuizContent from './QuizContent2';
import QuizService from '../../services/QuizService';
import GradeReport from '../../components/quiz/GradeReport';

export default function Quiz() {
  let { quizId } = useParams();
  const [quiz, setQuiz] = useState({});
  const [questions, setQuestions] = useState([]);
  const [gradeReport, setGradeReport] = useState({});
  const [hasSubmitted, setHasSubmitted] = useState(false);

  const formatDateString = dateString => {
    const date = new Date(dateString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based
    const year = date.getFullYear();

    return `${month}-${day}-${year}`;
  };

  useEffect(() => {
    const fetchQuiz = async () => {
      try {
        const quizData = await QuizService.getQuiz(quizId);
        setQuiz(quizData);
        setQuestions(quizData.questions);
      } catch (error) {
        alert('ERROR: failed to fetch quiz');
      }
    };

    fetchQuiz();
  }, []);

  const submitAnswers = async userAnswers => {
    try {
      const answerBody = {
        userId: 1, // hardcoded user id
        quizId: quiz.quizId,
        answers: userAnswers,
      };
      console.log(answerBody);
      const gr = await QuizService.submitQuizResponses(answerBody);
      setGradeReport(gr);
      setHasSubmitted(true);
    } catch (error) {
      alert('ERROR: failed to submit answers');
    }
  };

  return (
    <>
      <Container maxWidth="xl">
        <Grid container>
          <Grid item xs={12}>
            <Paper
              sx={{
                m: 1,
                borderRadius: 2,
                width: '100vh',
                textAlign: 'center',
              }}
            >
              <Typography variant="h4">{quiz.title} </Typography>
              <Typography variant="h6">{quiz.description}</Typography>

              <Chip
                variant="filled"
                label={'Created at ' + formatDateString(quiz.createdAt)}
                sx={{ m: 1 }}
              />
            </Paper>
          </Grid>
        </Grid>

        <Collapse in={!hasSubmitted}>
          {Object.keys(quiz).length > 0 && (
            <QuizContent questions={questions} submitAnswers={submitAnswers} />
          )}
        </Collapse>

        {hasSubmitted && <GradeReport gr={gradeReport} />}
      </Container>
    </>
  );
}
