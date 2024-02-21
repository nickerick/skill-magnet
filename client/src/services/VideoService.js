import AWS from 'aws-sdk';

const S3_PUBLIC_KEY = import.meta.env.VITE_AWS_S3_PUBLIC_KEY ?? '';
const S3_SECRET_KEY = import.meta.env.VITE_AWS_S3_SECRET_KEY ?? '';
const S3_BUCKET = 'skillmagnet';
const REGION = 'us-east-1';

// Establish AWS Connection Info
AWS.config.update({
  region: REGION,
  maxRetries: 3,
  httpOptions: { timeout: 30000, connectTimeout: 5000 },
  accessKeyId: S3_PUBLIC_KEY,
  secretAccessKey: S3_SECRET_KEY,
});

// Establish connection to the Videos S3 bucket
const videoStorageBucket = new AWS.S3({
  params: { Bucket: S3_BUCKET },
  region: REGION,
});

class VideoService {
  /**
   * Generates a signed AWS URL from given videoName.
   *
   * The generated URL gives you access to retrieve the video from S3
   * as well as returns the video in a streamable mp4 format.
   *
   * @param {String} videoName - The name of the requested video
   * @returns {String} The generated URL
   */
  getVideoUrl(videoName) {
    return videoStorageBucket.getSignedUrl('getObject', {
      Bucket: S3_BUCKET,
      Key: `videos/${videoName}`,
      ResponseContentType: 'video/mp4',
    });
  }

  /**
   * Uploads a video to S3.
   *
   * @param {File} file - The video file to be uploaded
   * @param {String} videoName - The name of the file once uploaded to S3
   * @returns {Promise} Returns Promise containing: null on success, error otherwise
   */
  async uploadVideo(file, videoName) {
    return new Promise((resolve, reject) => {
      if (file == null || videoName == null) reject();

      const params = {
        ACL: 'private',
        Body: file,
        Bucket: S3_BUCKET,
        Key: `videos/${videoName}`,
      };

      videoStorageBucket.putObject(params, (err, data) => {
        if (err) {
          reject(err);
        } else {
          resolve();
        }
      });
    });
  }
}

export default new VideoService();
