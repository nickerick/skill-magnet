/** @type { import('@storybook/react-vite').StorybookConfig } */
import './CourseCard.css';

const config = {
  stories: ['../src/**/*.mdx', '../src/**/*.stories.@(js|jsx|mjs|ts|tsx)'],
  addons: [
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    '@storybook/addon-onboarding',
    '@storybook/addon-interactions',
  ],
  framework: {
    name: '@storybook/react-vite',
    options: {},
  },
  docs: {
    autodocs: 'tag',
  },
};

export default function CourseCard({courseTitle, imageUrl, instructorName}) {
  return (
    <div className="course-card">
      <div className="course-image">
        <img src={imageUrl} />
      </div>
      <div className="course-title">
        {courseTitle}
      </div>
      <div className="course-instructor">
        {instructorName}
      </div>
      <button className="enroll-button">Enroll Now</button>
    </div>
  );}