import React, { useState } from 'react';
import './BasicCourseInformation.css';
import SmallHeader from '../SmallHeader';

const BasicCourseInformation = () => {
  const [title, setTitle] = useState('');
  const [information, setInformation] = useState('');
  const [category, setCategory] = useState('');

  const handleTitleChange = (e) => {
      setTitle(e.target.value);
  };

  const handleInformationChange = (e) => {
      setInformation(e.target.value);
  };

  const handleCategoryChange = (e) => {
      setCategory(e.target.value);
  };

    return (
        <div className="course-form">
            <div className="left-group">
                <div className="input-group">
                    <SmallHeader headerName={"Course Title:"} />
                    <input
                        type="text"
                        id="courseTitle"
                        value={title}
                        onChange={handleTitleChange}
                    />
                </div>
                <div className="input-group">
                    <SmallHeader headerName={"Course Description:"} />
                    <textarea
                        id="courseInformation"
                        value={information}
                        onChange={handleInformationChange}
                    />
                </div>
            </div>
            <div className="right-group">
                <div className="input-group">
                    <SmallHeader headerName={"Course Thumbnail:"} />
                    <div className="upload-container">
                      <label htmlFor="courseThumbnail" className="upload-button">Choose Image</label>
                      <input
                        type="file"
                        id="courseThumbnail"
                        className="file-input"
                      />
                  </div>
                </div>
                <div className="input-group">
                    <SmallHeader headerName={"Category:"} />
                    <select
                        id="courseCategory"
                        value={category}
                        onChange={handleCategoryChange}
                    >
                        <option value="">Topics </option>
                        <option value="programming">Programming</option>
                        <option value="design">Design</option>
                        <option value="business">Business</option>
                        {/* Add more options as needed */}
                    </select>
                </div>
            </div>
        </div>
    );
};

export default BasicCourseInformation;
