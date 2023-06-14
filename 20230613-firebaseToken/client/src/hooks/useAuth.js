import { onAuthStateChanged, signInAnonymously } from 'firebase/auth';
import { useEffect, useState } from 'react';
import { auth, db } from '../firebase';

export default function useAuth() {
  const [user, setUser] = useState(auth.currentUser);

  const signInAnonymousUser = async () => {
    const userCredential = await signInAnonymously(auth);

    setUser(userCredential.user);
  };

  useEffect(() => {
    if (!user) {
      signInAnonymousUser();
    }
  }, []);

  return { user };
}
