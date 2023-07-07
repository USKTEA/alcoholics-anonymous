import { useState } from 'react';

export default function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const change = {
  };

  const handleChangeInput = (event) => {
    console.log(event.target.name);
  };
  return (
    <>
      <div>
        <label htmlFor="username">아이디: </label>
        <input id="username" type="text" value={username} name="username" onChange={handleChangeInput} />
      </div>
      <div>
        <label htmlFor="password">비밀번호: </label>
        <input id="password" type="text" value={password} name="password" onChange={handleChangeInput} />
      </div>
      <button type="button">로그인</button>
    </>
  );
}
