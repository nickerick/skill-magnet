import { Link } from 'react-router-dom';
import './Course.css';
import Header from '../../components/Header';
import plussign from '../../assets/plus-sign.svg'

export const NewCourse = () => {
    return (
        <div className='new-course'>
            <div className='container'>
            <Link to={'createnewcourse'} className="custom-button">
                <div className='button'>
                    <img src={plussign} className="plus-sign" alt="plus sign" />
                </div>
            </Link>
                <Link to={`createnewcourse`} style={{ textDecoration: 'none', textTransform: 'none' }}>
                    <Header headerName="Create New Course" />
                </Link>
            </div>
        </div>
    );
};

