import { apiService } from '../services/ApiService';
import Store from './Store';

const bearApi = {
  home: () => apiService.getHomeBear(),
  user: () => apiService.getUserBear(),
  admin: () => apiService.getAdminBear(),
};

export default class SampleStore extends Store {
  constructor() {
    super();

    this.bear = null;
  }

  async getBear(bearType) {
    try {
      const { bear } = await bearApi[bearType]();

      this.bear = bear;
    } catch (error) {
      this.bear = null;
    } finally {
      this.publish();
    }
  }
}

export const sampleStore = new SampleStore();
