import { render, screen } from '@testing-library/react';
import Homepage from './HomePage';

describe('HomePage', () => {
  it('기본 문구를 볼 수 있다', () => {
    render(<Homepage />);

    screen.getByText('You do not have bear...');
  });
});
