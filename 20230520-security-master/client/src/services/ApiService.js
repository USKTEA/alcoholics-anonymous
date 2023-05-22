/* eslint-disable import/no-extraneous-dependencies */

import axios from "axios";

const baseUrl = "http://localhost:9000/";

export default class ApiService {
  constructor() {
    this.instance = axios.create({
      baseURL: baseUrl,
      headers: { Authorization: "Bearer 111111" },
    });
  }

  async getWelcomeMessage() {
    const { data } = await this.instance.get("/");

    return data;
  }
}

export const apiService = new ApiService();
