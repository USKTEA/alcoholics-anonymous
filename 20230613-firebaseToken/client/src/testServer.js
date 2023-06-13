/* eslint-disable import/no-extraneous-dependencies */
import { rest } from 'msw';
import { setupServer } from 'msw/node';

const baseUrl = 'http://localhost:8080';

const server = setupServer(
  rest.get(`${baseUrl}/`, async (req, res, ctx) => res(
    ctx.json(
      { bear: 'homeBear' },
    ),
  )),

  rest.get(`${baseUrl}/users`, async (req, res, ctx) => res(
    ctx.json(
      { bear: 'userBear' },
    ),
  )),

  rest.get(`${baseUrl}/admins`, async (req, res, ctx) => res(
    ctx.json(
      { bear: 'adminBear' },
    ),
  )),
);

export default server;
