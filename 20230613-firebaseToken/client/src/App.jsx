import Homepage from './pages/HomePage';
import useSampleStore from './hooks/useSampleStore';
import AuthComponent from './components/AuthComponent';

export default function App() {
  const sampleStore = useSampleStore();

  const handleClickButton = async (bearType) => {
    await sampleStore.getBear(bearType);
  };

  return (
    <div>
      <AuthComponent />
      <header>
        <nav>
          <ul>
            <li>
              <button type="button" onClick={() => handleClickButton('home')}>Home</button>
            </li>
            <li>
              <button type="button" onClick={() => handleClickButton('user')}>User</button>
            </li>
            <li>
              <button type="button" onClick={() => handleClickButton('admin')}>Admin</button>
            </li>
          </ul>
        </nav>
      </header>
      <Homepage />
    </div>
  );
}
