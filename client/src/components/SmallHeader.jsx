import './SmallHeader.css';

const SmallHeader = ({ headerName }) => {
  return (
    <div className="small-header-name">
      {headerName}
    </div>
  );
};

export default SmallHeader;