import { NewCourse } from '../../components/buttons/newCourse';
import { EditExistingCourse } from '../../components/buttons/EditExistingCourse';
import './CreatorStudio.css';
import { Link } from 'react-router-dom';
import { Button } from '@mui/material';

export default function CreatorStudio(){
    return (
        <div className="new-course-button">
            <NewCourse />
            <EditExistingCourse />
        </div>
    );
}