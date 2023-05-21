import { apiService } from '../services/ApiService';
import Store from './Store';

export default class WelcomeStore extends Store {
  constructor() {
    super();

    this.message = null;
  }

  async getWelcomeMessage() {
    const data = await apiService.getWelcomeMessage();

    this.message = data;

    this.publish();
  }
}

export const welcomeStore = new WelcomeStore();
