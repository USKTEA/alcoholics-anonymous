/* eslint-disable import/no-extraneous-dependencies */

import axios from 'axios';

const baseUrl = 'http://localhost:8080/';

export default class ApiService {
  constructor() {
    this.instance = axios.create({
      baseURL: baseUrl,
      headers: { Authorization: 'Bearer AdminBearToken' },
    });
  }

  async getHomeBear() {
    const { data } = await this.instance.get('/');

    return data;
  }

  async getUserBear() {
    const { data } = await this.instance.get('/users');

    return data;
  }

  async getAdminBear() {
    const { data } = await this.instance.get('/admins');

    return data;
  }
}

export const apiService = new ApiService();
