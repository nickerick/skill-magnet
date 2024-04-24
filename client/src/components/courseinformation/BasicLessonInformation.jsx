import React, { useState } from 'react';
import './BasicLessonInformation.css';
import SmallHeader from '../SmallHeader';

const BasicLessonInformation = ({ lessonId, handleLessonInfoChange, handleFileInputChange }) => {
  const [title, setTitle] = useState('');
  const [file, setFile] = useState(null);
  const [fileName, setFileName] = useState('');

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
    handleLessonInfoChange(lessonId, { title: e.target.value });
  };

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    if (selectedFile) {
      setFile(selectedFile);
      setFileName(selectedFile.name);
      handleFileInputChange(e, lessonId);
    }
  };

  return (
    <div className="lesson-form">
      <div className="left-group">
        <div className="input-group">
          <SmallHeader headerName={"Lesson Title:"} />
          <input
            type="text"
            id={`lessonTitle${lessonId}`}
            value={title}
            onChange={(e) => handleTitleChange(e)}
            className="lesson-title-input"
          />
        </div>

        <div className="input-group">
          <SmallHeader headerName={'Lesson Video:'} />
          <div className="upload-container">
            <label htmlFor={`lessonVideo${lessonId}`} className="lesson-upload-button">
              {fileName ? fileName : 'Choose File'}
            </label>
            <input
              type="file"
              id={`lessonVideo${lessonId}`}
              className="file-input"
              onChange={handleFileChange}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default BasicLessonInformation;
