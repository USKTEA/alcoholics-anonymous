import { useEffect } from 'react';
import useWelcomeStore from '../hooks/useWelcomeStore';

export default function Homepage() {
  const welcomeStore = useWelcomeStore();

  useEffect(() => {
    welcomeStore.getWelcomeMessage();
  }, []);

  if (!welcomeStore.message) {
    return (<p>now loading...</p>);
  }

  return (
    <p>{welcomeStore.message}</p>
  );
}
