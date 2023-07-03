/* eslint-disable class-methods-use-this */
import { apiService } from '../services/ApiService';
import Store from './Store';

export default class PaymentStore extends Store {
  constructor() {
    super();

    this.data = null;
    this.total = 0;
  }

  async getPaymentsExcel() {
    const data = await apiService.getPaymentsExcel();

    return data;
  }

  async addPayments() {
    const data = await apiService.addPayments();

    this.total = data.total;

    this.publish();
  }
}

export const paymentStore = new PaymentStore();
