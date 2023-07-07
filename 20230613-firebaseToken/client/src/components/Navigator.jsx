import useSampleStore from '../hooks/useSampleStore';

export default function Navigator() {
  const sampleStore = useSampleStore();

  const handleClickButton = async (bearType) => {
    await sampleStore.getBear(bearType);
  };

  return (
    <nav>
      <ul>
        <li>
          <button type="button" onClick={() => handleClickButton('home')}>Home</button>
        </li>
        <li>
          <button type="button" onClick={() => handleClickButton('user')}>User</button>
        </li>
        <li>
          <button type="button" onClick={() => handleClickButton('admin')}>Admin</button>
        </li>
        <li>
          <button type="button" onClick={() => handleClickButton('anonymous')}>Anonymous</button>
        </li>
      </ul>
    </nav>
  );
}
