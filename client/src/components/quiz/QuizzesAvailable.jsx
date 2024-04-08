import React, { useState, useEffect } from 'react';
import QuizService from '../../services/QuizService';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import {
  AccordionDetails,
  AccordionSummary,
  Box,
  Accordion,
  Typography,
  Stack,
} from '@mui/material';
import ElectricBoltIcon from '@mui/icons-material/ElectricBolt';

export default function QuizzesAvailable({ lessonId }) {
  const [quizzes, setQuizzes] = useState([]);

  useEffect(() => {
    const fetchQuizzes = async () => {
      try {
        const quizData = await QuizService.getQuizzesByLesson(lessonId);
        setQuizzes(quizData);
      } catch (error) {
        alert('ERROR: failed to fetch quizzes for lesson: ' + lessonId);
      }
    };

    fetchQuizzes();
  }, [lessonId]);

  return (
    <>
      <Box ml={'35%'} mr="300px">
        <Accordion defaultExpanded>
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel3-content"
            id="panel3-header"
          >
            <Typography variant="h6">
              Available Quizzes for this Lesson
            </Typography>
          </AccordionSummary>
          <AccordionDetails>
            {quizzes.map(quiz => (
              <Stack direction={'row'} alignContent={'center'}>
                <ElectricBoltIcon />
                <a href={'/quiz/' + quiz.quizId}>
                  <Typography>{quiz.title}</Typography>
                </a>
              </Stack>
            ))}
          </AccordionDetails>
        </Accordion>
      </Box>
    </>
  );
}
