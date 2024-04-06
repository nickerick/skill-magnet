import { useRouteError } from 'react-router-dom';
import { NavBar } from '../../components/navbar/NavBar';

export default function Error() {
  const error = useRouteError();
  console.error(error);

  return (
    <div id="error-page">
      <NavBar />
      {error.status == 404 ? (
        <div>
          <h1>Page not yet implemented!</h1>
        </div>
      ) : (
        <div>
          <p>Sorry, an unexpected error has occurred.</p>
          <p>
            <i>error.statusText || error.message</i>
          </p>
        </div>
      )}
    </div>
  );
}
