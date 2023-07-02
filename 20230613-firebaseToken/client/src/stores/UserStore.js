import Store from './Store';

export default class UserStore extends Store {
  constructor() {
    super();

    this.user = null;
  }

  login() {

  }
}

export const userStore = new UserStore();
