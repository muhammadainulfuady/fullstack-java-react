import axiosInstance from "./axiosInstance";

export const addToCart = async (idProduk, kuantitas) => {
  const response = await axiosInstance.post("/keranjang/add", {
    idProduk,
    kuantitas,
  });
  return response.data;
};

export const getMyCart = async () => {
  const response = await axiosInstance.get("/keranjang/my-cart");
  return response.data;
};
