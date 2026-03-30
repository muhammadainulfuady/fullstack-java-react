import axiosInstance from "./axiosInstance";

export const loginUser = async (email, password) => {
  const response = await axiosInstance.post("/auth/login", { email, password });
  return response.data;
};

export const logoutUser = () => {
  localStorage.removeItem("token");
};

// Fungsi baru untuk mengambil daftar kota untuk Dropdown
export const getAllKota = async () => {
  const response = await axiosInstance.get("/kota");
  return response.data;
};

// Fungsi register diperbarui untuk membawa data Kota
export const registerUser = async (nama, email, password, idKota) => {
  // Bentuk JSON ini sangat penting agar Spring Boot paham relasi kotanya
  const payload = {
    nama: nama,
    email: email,
    password: password,
    kota: {
      idKota: parseInt(idKota), // Menyisipkan ID Kota sebagai objek relasi
    },
  };
  const response = await axiosInstance.post("/auth/register", payload);
  return response.data;
};
