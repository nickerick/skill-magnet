import {
  Paper,
  Typography,
  Table,
  TableHead,
  TableRow,
  TableCell,
  Stack,
  TableBody,
  TableContainer,
  Chip,
  Button,
} from '@mui/material';
import CheckIcon from '@mui/icons-material/Check';
import ClearIcon from '@mui/icons-material/Clear';

export default function GradeReport({ gr }) {
  return (
    <>
      <Typography variant="h4" gutterBottom>
        Grade Report
      </Typography>
      <Paper elevation={2} sx={{ borderRadius: 2 }}>
        <Typography variant="h6" m={1}>
          Score:
          <Chip
            variant="filled"
            color="info"
            size="medium"
            label={
              <Typography variant="body2">
                {gr.score} / {gr.scorePossible}
              </Typography>
            }
            sx={{ m: 1 }}
          />
        </Typography>
      </Paper>

      <TableContainer component={Paper} sx={{ marginTop: 4 }}>
        <Table aria-label="grade report table">
          <TableHead>
            <TableRow>
              <TableCell>Question</TableCell>
              <TableCell>Answer</TableCell>
              <TableCell>Correct</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {gr.results.map((result, index) => (
              <TableRow key={result.resultId}>
                <TableCell>{index + 1}</TableCell>
                <TableCell>
                  {result.shortAnswer === 'N/A'
                    ? result.questionOptionText
                    : result.shortAnswer}
                </TableCell>
                <TableCell>
                  <Stack direction={'row'} alignItems={'center'}>
                    {result.correct ? (
                      <CheckIcon color="success" />
                    ) : (
                      <ClearIcon color="error" />
                    )}
                  </Stack>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {/* forcing reload just resets page */}
      <Button onClick={() => location.reload()}>Take Quiz Again</Button>
    </>
  );
}
