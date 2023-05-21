/* eslint-disable import/no-extraneous-dependencies */
import { rest } from 'msw';
import { setupServer } from 'msw/node';

const baseUrl = 'http://localhost:8080';

const server = setupServer(
  rest.get(`${baseUrl}/`, async (req, res, ctx) => res(
    ctx.json(
      { message: 'Hello, world!' },
    ),
  )),
);

export default server;
