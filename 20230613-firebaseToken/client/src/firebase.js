import { initializeApp } from 'firebase/app';
import { getAuth } from 'firebase/auth';
import { getFirestore } from 'firebase/firestore';

const firebaseConfig = {
  apiKey: 'AIzaSyDZobZU6doc29ZeyQxDthsrGJrKePfFSxU',
  authDomain: 'fir-token-72365.firebaseapp.com',
  projectId: 'fir-token-72365',
  storageBucket: 'fir-token-72365.appspot.com',
  messagingSenderId: '297903145778',
  appId: '1:297903145778:web:bb5d79195d84c6e18b0d2b',
  measurementId: 'G-CK8FGH8S2J',
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore(app);

export { auth, db };
