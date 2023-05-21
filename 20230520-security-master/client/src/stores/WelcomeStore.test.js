import WelcomeStore from './WelcomeStore';

describe('WelcomeStore', () => {
  let welcomeStore;

  beforeEach(() => {
    welcomeStore = new WelcomeStore();
  });

  describe('GetWelcomeMessage', () => {
    it('서버로 부터 환영문구를 받아온다', async () => {
      expect(welcomeStore.message).toBeFalsy();

      await welcomeStore.getWelcomeMessage();

      expect(welcomeStore.message).toBeTruthy();
    });
  });
});
