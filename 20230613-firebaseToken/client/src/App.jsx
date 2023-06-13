import { onAuthStateChanged, signInAnonymously } from 'firebase/auth';
import { auth, db } from './firebase';

import Homepage from './pages/HomePage';
import useSampleStore from './hooks/useSampleStore';

export default function App() {
  const sampleStore = useSampleStore();

  const handleClickButton = async (bearType) => {
    await sampleStore.getBear(bearType);
  };

  signInAnonymously(auth)
    .then(() => {
    })
    .catch((error) => {
      console.log(error);
      const errorCode = error.code;
      const errorMessage = error.message;
    // ...
    });

  return (
    <div>
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
