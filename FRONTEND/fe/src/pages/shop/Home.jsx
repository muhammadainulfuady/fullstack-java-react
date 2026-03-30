import { useState, useEffect } from "react";
import { getAllProducts } from "../../api/productService";
import { addToCart } from "../../api/cartService";
import { formatRupiah } from "../../utils/formatCurrency";

const Home = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        // Sesuai dengan DTO ApiResponse kita di Spring Boot
        const res = await getAllProducts();
        setProducts(res.data);
      } catch (error) {
        console.error("Gagal mengambil produk:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  const handleAddToCart = async (idProduk) => {
    try {
      await addToCart(idProduk, 1);
      alert("Sukses! Produk ditambahkan ke keranjang. 🛒");
    } catch (error) {
      alert("Gagal! Pastikan kamu sudah Login.");
    }
  };

  if (loading)
    return (
      <div className="min-h-screen flex justify-center items-center text-xl font-semibold text-gray-500">
        Memuat Etalase...
      </div>
    );

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-10">
      <h1 className="text-3xl font-bold text-gray-900 mb-8">Produk Terbaru</h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {products.map((p) => (
          <div
            key={p.idProduk}
            className="bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden hover:shadow-lg transition-all duration-300 flex flex-col"
          >
            {/* Placeholder Gambar Produk */}
            <div className="h-48 bg-gray-100 flex items-center justify-center">
              <span className="text-5xl">🛍️</span>
            </div>

            <div className="p-5 flex flex-col flex-grow">
              <div className="text-xs text-blue-600 font-bold uppercase tracking-wider mb-1">
                {p.kategori?.namaKategori || "Umum"}
              </div>
              <h3 className="text-lg font-bold text-gray-900 mb-2 line-clamp-2">
                {p.namaProduk}
              </h3>
              <div className="mt-auto">
                <div className="flex justify-between items-center mb-4">
                  <span className="text-xl font-extrabold text-gray-900">
                    {formatRupiah(p.harga)}
                  </span>
                  <span className="text-xs font-medium bg-gray-100 text-gray-600 px-2 py-1 rounded-md">
                    Stok: {p.stok}
                  </span>
                </div>
                <button
                  onClick={() => handleAddToCart(p.idProduk)}
                  className="w-full bg-blue-50 text-blue-700 border border-transparent hover:bg-blue-600 hover:text-white font-semibold py-2.5 px-4 rounded-xl transition-colors duration-200"
                >
                  + Keranjang
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
