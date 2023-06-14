import useAuth from '../hooks/useAuth';

export default function AuthComponent() {
  const { user } = useAuth();

  console.log(user);

  const getIdToken = async () => {
    const token = await user.getIdToken();

    console.log(token);
  };

  if (user) {
    console.log(user.accessToken);
    getIdToken();
  }

  return (<>aa</>);
}
