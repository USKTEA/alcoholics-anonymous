import {
  fireEvent, render, screen, waitFor,
} from '@testing-library/react';
import App from './App';

const context = describe;

describe('App', () => {
  it('App이 랜더링 된다', () => {
    render(<App />);
  });

  context('Home 버튼을 클릭했을 경우', () => {
    it('HomeBear를 볼 수 있다', async () => {
      render(<App />);

      fireEvent.click(screen.getByRole('button', { name: 'Home' }));

      await waitFor(() => {
        screen.getByText('homeBear');
      });
    });
  });
});
