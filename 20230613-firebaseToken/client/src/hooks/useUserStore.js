/* eslint-disable import/no-unresolved */
import { userStore } from '../stores/userStore';
import useStore from './useStore';

export default function useUserStore() {
  return useStore(userStore);
}
