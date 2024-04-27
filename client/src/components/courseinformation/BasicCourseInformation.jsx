import React, { useState } from 'react';
import './BasicCourseInformation.css';
import SmallHeader from '../SmallHeader';

const BasicCourseInformation = ({ setCourseInfo, courseInfo }) => {
  const [file, setFile] = useState(null);
  const [fileName, setFileName] = useState('');

  const handleTitleChange = (e) => {
    setCourseInfo(prevState => ({
      ...prevState,
      title: e.target.value
    }));
  };

  const handleDescriptionChange = (e) => {
    setCourseInfo(prevState => ({
      ...prevState,
      description: e.target.value
    }));
  };

  const handleCategoryChange = (e) => {
    setCourseInfo(prevState => ({
      ...prevState,
      category: e.target.value
    }));
  };

  const handleFileInputChange = (e) => {
    const selectedFile = e.target.files[0];
    if (selectedFile) {
      setFile(selectedFile);
      setFileName(selectedFile.name);
    }
  };

  return (
    <div className="course-form">
      <div className="left-group">
        <div className="input-group">
          <SmallHeader headerName={"Course Title:"} />
          <input
            type="text"
            id="courseTitle"
            value={courseInfo.title}
            onChange={handleTitleChange}
          />
        </div>
        <div className="input-group">
          <SmallHeader headerName={"Course Description:"} />
          <textarea
            id="courseDescription"
            value={courseInfo.description}
            onChange={handleDescriptionChange}
          />
        </div>
      </div>
      <div className="right-group">
        <div className="input-group">
          <SmallHeader headerName={"Course Thumbnail:"} />
          <div className="upload-container">
            <label htmlFor="courseThumbnail" className="course-image-upload-button">
              {fileName ? fileName : 'Choose Image'}
            </label>
            <input
              type="file"
              id="courseThumbnail"
              className="file-input"
              onChange={handleFileInputChange}
            />
          </div>
        </div>
        <div className="input-group">
          <SmallHeader headerName={"Category:"} />
          <select
            id="courseCategory"
            value={courseInfo.category}
            onChange={handleCategoryChange}
          >
            <option value="">Topics </option>
            <option value="programming">Programming</option>
            <option value="design">Design</option>
            <option value="business">Business</option>
          </select>
        </div>
      </div>
    </div>
  );
};

export default BasicCourseInformation;
