import { welcomeStore } from '../stores/WelcomeStore';
import useStore from './useStore';

export default function useWelcomeStore() {
  return useStore(welcomeStore);
}
