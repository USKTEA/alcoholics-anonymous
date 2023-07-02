import { fireEvent, render, screen } from '@testing-library/react';
import LoginForm from './LoginForm';

const context = describe;

const login = jest.fn();

jest.mock('../hooks/useUserStore', () => () => ({
  login,
}));

describe('LoginForm', () => {
  it('로그인폼을 볼 수 있다', () => {
    render(<LoginForm />);

    screen.getByText(/아이디/);
    screen.getByText(/비밀번호/);
    screen.getByText(/로그인/);
  });

  describe('로그인', () => {
    context('아이디와 비밀번호를 모두 입력했을 경우', () => {
      it('로그인 요청을 수행한다', () => {
        render(<LoginForm />);

        fireEvent.change(screen.getByLabelText(/아이디/), {
          target: {
            value: 'tjrxo1234',
          },
        });

        fireEvent.change(screen.getByLabelText(/비밀번호/), {
          target: {
            value: 'password1234',
          },
        });

        fireEvent.click(screen.getByText('로그인'));

        expect(login).toBeCalledWith({ id: 'tjrxo1234', password: 'password1234' });
      });
    });
  });
});
