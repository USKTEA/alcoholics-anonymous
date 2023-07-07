/* eslint-disable import/no-extraneous-dependencies */

import { useEffect } from 'react';
import { useLocalStorage } from 'usehooks-ts';
import Homepage from './pages/HomePage';
import Navigator from './components/Navigator';
import useUserStore from './hooks/useUserStore';
import LoginForm from './components/LoginForm';
import useAuth from './hooks/useAuth';

export default function App() {
  const [accessToken, setAccessToken] = useLocalStorage('accessToken');
  const userStore = useUserStore();
  const { user } = userStore;
  const { token } = useAuth();

  useEffect(() => {
    setAccessToken(token);
  }, [token]);

  return (
    <>
      <header>
        <Navigator />
      </header>
      <Homepage />
      {user ? null : <LoginForm /> }
    </>
  );
}
