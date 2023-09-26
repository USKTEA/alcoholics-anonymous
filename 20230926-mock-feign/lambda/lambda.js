/* eslint-disable no-mixed-operators */
/* eslint-disable no-bitwise */
/* eslint-disable import/no-extraneous-dependencies */
/* eslint-disable no-unused-vars */
import jwt from 'jsonwebtoken';
import JwksRsa from 'jwks-rsa';
import axios from 'axios';

const JWKS_URI = 'https://cognito-idp.ap-northeast-2.amazonaws.com/ap-northeast-2_Si5ddus6A/.well-known/jwks.json';
const HOME_EXTENSION_ENDPOINT = 'https://plaindold-api.shop/clova/devices';

function uuidv4() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
    const r = Math.random() * 16 | 0; const
      v = c === 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}

const errorResponse = {
  header: {
    messageId: uuidv4(),
    namespace: 'ClovaHome',
    name: 'InvalidAccessTokenError',
    payloadVersion: '1.0',
  },
  payload: {},
};

const getSigningKey = async (apiId, token) => {
  const jwksClient = JwksRsa({ jwksUri: JWKS_URI });
  const decodedToken = jwt.decode(token, { complete: true });
  const signingKey = await jwksClient.getSigningKey(decodedToken.header.kid);

  return signingKey;
};

const getJwtToken = (event) => {
  const pattern = /"accessToken":"([^"]+)"/;
  const match = event.body.match(pattern);
  const jwtToken = match ? match[1] : null;

  return jwtToken;
};

const getResponse = async (jwtToken, body) => {
  const headers = {
    'Content-Type': 'application/json',
    Authorization: `Bearer ${jwtToken}`,
  };

  const response = await axios.post(HOME_EXTENSION_ENDPOINT, body, { headers });

  return response;
};

const handler = async (event) => {
  const jwtToken = getJwtToken(event);
  const { requestId } = event.requestContext;
  const signingKey = await getSigningKey(requestId, jwtToken);
  const { body } = event;

  if (jwtToken === null) {
    return errorResponse;
  }

  if (signingKey === null) {
    return errorResponse;
  }

  try {
    jwt.verify(
      jwtToken,
      signingKey.getPublicKey(),
      {
        algorithms: [signingKey.alg],
      },
    );
  } catch (exception) {
    return errorResponse;
  }

  const { data } = await getResponse(jwtToken, body);

  return data;
};

export default handler;
