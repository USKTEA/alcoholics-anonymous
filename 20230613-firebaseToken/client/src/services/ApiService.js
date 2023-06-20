/* eslint-disable no-param-reassign */
/* eslint-disable import/no-extraneous-dependencies */

import axios from 'axios';

const baseUrl = 'http://localhost:8080/';

export default class ApiService {
  constructor() {
    this.instance = axios.create({
      baseURL: baseUrl,
    });

    this.instance.interceptors.request.use((config) => {
      if (!config.headers) {
        return config;
      }

      const accessToken = JSON.parse(localStorage.getItem('accessToken'));

      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }

      return config;
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

  async getAnonymousBear() {
    const { data } = await this.instance.get('/anonymous');

    return data;
  }
}

export const apiService = new ApiService();
