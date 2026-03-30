import axiosInstance from "./axiosInstance";

// Mengambil data profil user yang sedang login
export const getUserProfile = async () => {
  // Asumsi endpoint backend kamu nanti ada di /api/users/me
  const response = await axiosInstance.get("/users/me");
  return response.data;
};

// Mengupdate data profil
export const updateUserProfile = async (data) => {
  const response = await axiosInstance.put("/users/me", data);
  return response.data;
};
