import axiosInstance from "./axiosInstance";

// Admin melihat SEMUA pesanan dari semua orang
export const getAllOrdersAdmin = async () => {
  const response = await axiosInstance.get("/orders");
  return response.data;
};

// Admin melihat SEMUA keranjang yang ada di sistem (CRUD bawaanmu)
export const getAllCartsAdmin = async () => {
  const response = await axiosInstance.get("/keranjang");
  return response.data;
};
