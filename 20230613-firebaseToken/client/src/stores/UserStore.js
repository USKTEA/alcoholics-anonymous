import Store from './Store';

export default class UserStore extends Store {
  constructor() {
    super();

    this.user = null;
  }
}

export const userStore = new UserStore();
