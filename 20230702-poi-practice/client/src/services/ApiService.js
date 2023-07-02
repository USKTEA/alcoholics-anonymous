/* eslint-disable no-param-reassign */
/* eslint-disable import/no-extraneous-dependencies */

import axios from 'axios';

const baseUrl = 'http://localhost:8080/';

export default class ApiService {
  constructor() {
    this.instance = axios.create({
      baseURL: baseUrl,
    });
  }

  async getPaymentData() {
    const { data } = await this.instance.get('/payments/excel');

    return data;
  }
}

export const apiService = new ApiService();
