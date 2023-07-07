import { render, screen, waitFor } from '@testing-library/react';
import App from './App';

const getIdToken = jest.fn();

jest.mock('../src/hooks/useAuth', () => () => ({
  user: {
    accessToken: 'ACCESS_TOKEN',
    email: 'tjrxo1234@gmail.com',
    getIdToken,
  },
}));

describe('App', () => {
  it('App이 랜더링 된다', () => {
    getIdToken.mockReturnValue('ACCESS_TOKEN');

    render(<App />);
  });
});
