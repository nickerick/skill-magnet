import './Header.css';

const Header = ({ headerName }) => {
  return (
    <h1 className="header-name">
      {headerName}
    </h1>
  );
};

export default Header;