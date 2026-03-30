import axios from "axios";

// Konfigurasi dasar untuk menembak Spring Boot
const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
});

// Interceptor: Menyelipkan Token JWT ke Header secara otomatis
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

export default axiosInstance;
