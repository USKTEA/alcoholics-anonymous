import { render, screen, waitFor } from '@testing-library/react';
import Homepage from './HomePage';

const context = describe;

describe('HomePage', () => {
  context('WelcomeMessage를 받지 않았다면', () => {
    it('로딩 문구와 환영 문구를 볼 수 있다', () => {
      render(<Homepage />);

      screen.getByText('now loading...');

      waitFor(() => screen.getByText('Hello, world!'));
    });
  });
});
