import axiosInstance from "./axiosInstance";

export const loginUser = async (email, password) => {
  const response = await axiosInstance.post("/auth/login", { email, password });
  return response.data;
};

export const logoutUser = () => {
  localStorage.removeItem("token");
};

// --- TAMBAHAN BARU UNTUK REGISTRASI ---
export const registerUser = async (nama, email, password) => {
  // Sesuaikan payload JSON ini dengan apa yang diminta oleh Backend Spring Boot kamu
  const response = await axiosInstance.post("/auth/register", {
    nama: nama,
    email: email,
    password: password,
  });
  return response.data;
};
