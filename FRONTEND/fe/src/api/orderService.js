import axiosInstance from "./axiosInstance";

export const checkoutCart = async () => {
  // Body kita kosongkan ({}) karena backend hanya butuh token untuk tahu siapa yang checkout
  const response = await axiosInstance.post("/orders/checkout", {});
  return response.data;
};

export const getMyOrders = async () => {
  const response = await axiosInstance.get("/orders/my-orders");
  return response.data;
};
