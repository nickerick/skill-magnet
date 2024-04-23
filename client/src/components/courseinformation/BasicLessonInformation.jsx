import React, { useState } from 'react';
import './BasicLessonInformation.css';
import SmallHeader from '../SmallHeader';

const BasicLessonInformation = () => {
  const [title, setTitle] = useState('');

  const handleTitleChange = (e) => {
      setTitle(e.target.value);
  };

  return (
        <div className="lesson-form">
            <div className="left-group">
                <div className="input-group">
                    <SmallHeader headerName={"Lesson Title:"} />
                    <input
                        type="text"
                        id="lessonTitle"
                        value={title}
                        onChange={handleTitleChange}
                    />
                </div>

                <div className="input-group">
                    <SmallHeader headerName={"Lesson Video:"} />
                    <div className="upload-container">
                      <label htmlFor="courseThumbnail" className="upload-button">Choose Image</label>
                      <input
                        type="file"
                        id="courseThumbnail"
                        className="file-input"
                      />
                  </div>
                </div>
            </div>
        </div>
    );
};

export default BasicLessonInformation;
