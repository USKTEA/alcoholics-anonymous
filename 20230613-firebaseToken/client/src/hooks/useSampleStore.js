import { sampleStore } from '../stores/SampleStore';
import useStore from './useStore';

export default function useSampleStore() {
  return useStore(sampleStore);
}
