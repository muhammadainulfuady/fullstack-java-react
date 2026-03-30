import axiosInstance from "./axiosInstance";

export const getAllProducts = async () => {
  const response = await axiosInstance.get("/produk");
  return response.data;
};

export const getProductById = async (id) => {
  const response = await axiosInstance.get(`/produk/${id}`);
  return response.data;
};
