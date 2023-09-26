import handler from './lambda';

const event = {
  version: '2.0',
  routeKey: 'POST /clova/devices',
  rawPath: '/clova/devices',
  rawQueryString: '',
  headers: {
    'accept-encoding': 'gzip',
    'content-length': '1208',
    'content-type': 'application/json; charset=utf-8',
    host: 'qyo8f8s3h8.execute-api.ap-northeast-2.amazonaws.com',
    signaturecek: 'WaUpZp6VpSRfDx6IM+JJazIlhWopMoanK6PZCEjKDxgLWQG+1bVAiOsb0cJGId9kHk3dD6Ety2r9iRerxEP6s7qFyrwpNgE2V24z4dR/iAwsc3jp1YE4h0xB8HymrnDn29+TcsDOx1mKegTQ17XRnUfqFvtFnCDC2DSqwSDzRRJj/twIn6w35XQ2aKwRx+OSqu4KR12W1RMzR03Za3DGGzW9sMxmNjlDsJVUIZtKiNKHQm05M5Pvp7A6WsQkr/0IuNzJZSaeuLiY9Zl31YuNTd6yFgoyc5aEkDtuJTVZEpRfzUgL6kT+MXr3rRWiCCfvWgHfB3xPL3Uijh+YXpYIjw==',
    'user-agent': 'Go-http-client/2.0',
    'x-amzn-trace-id': 'Root=1-6505c018-6663565003d3a0ef150dea7a',
    'x-forwarded-for': '220.230.168.9',
    'x-forwarded-port': '443',
    'x-forwarded-proto': 'https',
  },
  requestContext: {
    accountId: '846693504836',
    apiId: 'qyo8f8s3h8',
    domainName: 'qyo8f8s3h8.execute-api.ap-northeast-2.amazonaws.com',
    domainPrefix: 'qyo8f8s3h8',
    http: {
      method: 'POST',
      path: '/clova/devices',
      protocol: 'HTTP/1.1',
      sourceIp: '220.230.168.9',
      userAgent: 'Go-http-client/2.0',
    },
    requestId: 'LWrz5gZNoE0EMuw=',
    routeKey: 'POST /clova/devices',
    stage: '$default',
    time: '16/Sep/2023:14:47:52 +0000',
    timeEpoch: 1694875672696,
  },
  body: '{"header":{"messageId":"c61169c3-d556-4498-954f-562025919397","name":"DiscoverAppliancesRequest","namespace":"ClovaHome","payloadVersion":"1.0"},"payload":{"accessToken":"eyJraWQiOiIyeDBBUlhRZ2hxaVJnYTh0eVpUaUozRFEzeGhQNlpUOG9oNUNRajdNZWZvPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIxNDE4Y2RjYy0zMGMxLTcwZmEtMmFmOS0wN2Y1NmM0MjM0MDMiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtbm9ydGhlYXN0LTIuYW1hem9uYXdzLmNvbVwvYXAtbm9ydGhlYXN0LTJfU2k1ZGR1czZBIiwidmVyc2lvbiI6MiwiY2xpZW50X2lkIjoiNWNkNmhoMnR0bGg2dnF0Zm0xOW9qNG05NWkiLCJvcmlnaW5fanRpIjoiOTc2Y2U3ZjQtYzNmNy00NWQxLWJjYWMtNGU2ZjZkZGI0MmI3IiwiZXZlbnRfaWQiOiI1MGZhYjYzZC0xNGFkLTQyOTgtOGNmNy1mYjcxYzY5OWJiYTAiLCJ0b2tlbl91c2UiOiJhY2Nlc3MiLCJzY29wZSI6Im9wZW5pZCIsImF1dGhfdGltZSI6MTY5NDg3NTY3MCwiZXhwIjoxNjk0ODc1OTcwLCJpYXQiOjE2OTQ4NzU2NzAsImp0aSI6ImI1ZTc4YWI3LTA3ZjItNDE3My04ZWVkLWZiMjFhZDRlM2NhMyIsInVzZXJuYW1lIjoia2ltc3VrdGFlIn0.YilDw1Aewh78Angphl1LTVgM9fAk7PwRXAlS2e_vvttzbrku6KwB17Jqx4mnED8MrSkMR7koYcW9BJHOkY1yzDtzTBbxoKUdl-UD6xHF6Nt40Jvqn-oB3aaMOqafdHIhCSE35MgaZG-HL0AnU9gUSPlzOJEBdBHBF7zzq4HN29wbgZm7wx7xJS-w3Lfm5QukPm0KEuk3R62IbdIp2RLHm_QbaTVI4QI9WMChzepJsTVHy9gKq4WWMiyZxh5XcYbAz6IeQBhBGxEKoDXJbSayoqXtZYRmPFzCZRr9c9PhBG0p-XJrB76Qn1CP9N5l8oO-Od_QcaFEVmBlB6sPSABAGg"}}',
  isBase64Encoded: false,
};

test('handler', async () => {
  expect(await handler(event)).toBe(1);
});
