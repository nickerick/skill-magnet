import { Link } from 'react-router-dom';
import './Course.css';
import Header from '../../components/Header';
import plussign from '../../assets/plus-sign.svg'

export const EditExistingCourse = () => {
    return (
        <div className='new-course'>
            <div className='container'>
                <Link to={'/editexistingcourse'} className="custom-button">
                    <div className='button'>
                        <img src={plussign} className="plus-sign" alt="plus sign" />
                    </div>
                </Link>
                <Link to={`/editexistingcourse`} style={{ textDecoration: 'none', textTransform: 'none' }}>
                    <Header headerName="Edit Existing Course" />
                </Link>
            </div>
        </div>
    );
};
