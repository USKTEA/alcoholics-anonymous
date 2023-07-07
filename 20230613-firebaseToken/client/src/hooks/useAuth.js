/* eslint-disable import/no-extraneous-dependencies */

import { useState } from 'react';
import useFirebase from './useFirebase';

export default function useAuth() {
  const [token, setToken] = useState('');
  const { user } = useFirebase();

  const getIdToken = async () => {
    const firebaseToken = await user.getIdToken();

    setToken(firebaseToken);
  };

  if (user) {
    getIdToken();
  }

  return { token };
}
