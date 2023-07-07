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

  async getPaymentsExcel() {
    try {
      const response = await this.instance.get('/payments/excel', {
        responseType: 'arraybuffer',
      });

      return {
        body: response,
      };
    } catch (error) {
      return error.response;
    }
  }

  async addPayments() {
    try {
      const { data } = await this.instance.get('/payments');

      return data;
    } catch (error) {
      return error.response;
    }
  }
}

export const apiService = new ApiService();
