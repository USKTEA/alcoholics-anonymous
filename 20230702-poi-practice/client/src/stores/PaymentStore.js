import { apiService } from '../services/ApiService';
import Store from './Store';

export default class PaymentStore extends Store {
  constructor() {
    super();

    const data = null;
  }

  async getPaymentData() {
    const { data } = await apiService.getPaymentData();

    this.data = data;

    this.publish();
  }
}

export const paymentStore = new PaymentStore();
