import useSampleStore from '../hooks/useSampleStore';

export default function Homepage() {
  const sampleStore = useSampleStore();

  if (!sampleStore.bear) {
    return (<p>no bear...</p>);
  }

  return (
    <p>{sampleStore.bear}</p>
  );
}
