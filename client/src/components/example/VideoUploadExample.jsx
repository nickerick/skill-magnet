import React, { useState } from 'react';
import VideoService from '../../services/VideoService';

/**
 * Example component demonstrating a POC on uploading/streaming videos to/from S3
 *
 * Currently component is hardcoded to one specific S3 video, however this should be dynamic once properly implemented
 */
export default function VideoUploadExample() {
  const [videoUrl, setVideoUrl] = useState('');
  const [videoUploadStatus, setVideoUploadStatus] = useState('default');
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileInput = e => {
    setSelectedFile(e.target.files[0]);
  };

  const uploadFile = async (file, videoName) => {
    VideoService.uploadVideo(file, videoName)
      .then(() => {
        setVideoUploadStatus('success');
      })
      .catch(() => {
        setVideoUploadStatus('failure');
      });
  };

  // This functionality would most likely occur in onLoad().
  //
  // The page would load, the server would supply a Lesson object, and you'd grab the videoname from there.
  // Then pass the videoName into the getVideoUrl() to get a final url that would be thrown into a <video> component.
  const loadVideo = videoName => {
    var videoUrl = VideoService.getVideoUrl(videoName);
    setVideoUrl(videoUrl);
  };

  function VideoUploadStatus() {
    var status = videoUploadStatus;

    if (status == 'failure') {
      return <div>Failed to upload video.</div>;
    } else if (status == 'success') {
      return <div>Uploaded video successfully!</div>;
    } else {
      return null;
    }
  }

  return (
    <div>
      <button onClick={() => loadVideo('test-video-1.mp4')}>Load Video</button>
      <div />
      <video src={videoUrl} width="750" height="500" controls></video>
      <div />
      <input type="file" onChange={handleFileInput} />
      <button onClick={() => uploadFile(selectedFile, 'test-video-1.mp4')}>
        Upload to S3
      </button>
      <VideoUploadStatus />
    </div>
  );
}
