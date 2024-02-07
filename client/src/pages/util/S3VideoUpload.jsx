import React, { useState } from 'react';
import AWS from 'aws-sdk';
import { GetObjectCommand, S3Client } from 'aws-sdk';
// import { fromIni } from '@aws-sdk/credential-providers';
// import { HttpRequest } from '@smithy/protocol-http';
import { getSignedUrl, S3RequestPresigner } from 'aws-sdk';

const S3_PUBLIC_KEY = import.meta.env.VITE_AWS_S3_PUBLIC_KEY;
const S3_SECRET_KEY = import.meta.env.VITE_AWS_S3_SECRET_KEY;
const S3_BUCKET = 'skillmagnet';
const REGION = 'us-east-1';

AWS.config.update({
  region: REGION,
  maxRetries: 3,
  httpOptions: { timeout: 30000, connectTimeout: 5000 },
  accessKeyId: S3_PUBLIC_KEY,
  secretAccessKey: S3_SECRET_KEY,
});

const myBucket = new AWS.S3({
  params: { Bucket: S3_BUCKET },
  region: REGION,
});

export default function S3VideoUpload() {
  const [progress, setProgress] = useState(0);
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileInput = e => {
    setSelectedFile(e.target.files[0]);
  };

  const uploadFile = file => {
    const params = {
      ACL: 'private',
      Body: file,
      Bucket: S3_BUCKET,
      Key: `videos/${file.name}`,
    };

    myBucket
      .putObject(params)
      .on('httpUploadProgress', evt => {
        setProgress(Math.round((evt.loaded / evt.total) * 100));
      })
      .send(err => {
        if (err) console.log(err);
      });
  };

  const createPresignedUrlWithClient = () => {
    var url = myBucket.getSignedUrl('getObject', {
      Bucket: S3_BUCKET,
      Key: 'videos/test-video-1.mp4',
      ResponseContentType: 'video/mp4',
    });

    console.log(url);
  };

  return (
    <div>
      <video
        src={
          'https://skillmagnet.s3.amazonaws.com/videos/test-video-1.mp4?AWSAccessKeyId=AKIASUOH5PTDZ4XRNFHI&Expires=1707288352&Signature=H9LvrF%2Bvu%2BmcvNPVGsvFbnJ6HeQ%3D&response-content-type=video%2Fmp4'
        }
        width="750"
        height="500"
        controls
      ></video>
      <div>Native SDK File Upload Progress is {progress}%</div>

      <input type="file" onChange={handleFileInput} />
      <button onClick={() => uploadFile(selectedFile)}> Upload to S3</button>
      <button onClick={createPresignedUrlWithClient}> URL</button>
    </div>
  );
}
