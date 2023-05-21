import { Link, Route, Routes } from 'react-router-dom';
import Homepage from './pages/HomePage';

export default function App() {
  return (
    <div>
      <header>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="login">Login</Link>
            </li>
          </ul>
        </nav>
      </header>
      <Routes>
        <Route path="/" element={<Homepage />} />
      </Routes>
    </div>
  );
}
