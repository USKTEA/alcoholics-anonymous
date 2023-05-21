import { render, screen, waitFor } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import App from './App';

describe('App', () => {
  it('App이 랜더링 된다', () => {
    render(
      <MemoryRouter>
        <App />
      </MemoryRouter>,
    );

    waitFor(() => screen.getByText('Hello, world!'));
  });
});
