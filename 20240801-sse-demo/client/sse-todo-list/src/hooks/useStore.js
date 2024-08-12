import { useEffect } from 'react';
import useForceUpdate from './useForceUpdate';

export default function useStore(store) {
  const forceUpdate = useForceUpdate();

  useEffect(() => {
    const unsubscribe = store.subscribe(forceUpdate);
    return unsubscribe;
  }, [store, forceUpdate]);

  return store;
}
