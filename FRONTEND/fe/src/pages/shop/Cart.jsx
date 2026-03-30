import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getMyCart } from "../../api/cartService";
import { checkoutCart } from "../../api/orderService";
import { formatRupiah } from "../../utils/formatCurrency";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isCheckout, setIsCheckout] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCart();
  }, []);

  const fetchCart = async () => {
    try {
      const res = await getMyCart();
      // Menyimpan array KeranjangItem dari Spring Boot
      setCartItems(res.data || []);
    } catch (error) {
      console.error("Gagal mengambil keranjang", error);
    } finally {
      setLoading(false);
    }
  };

  const handleCheckout = async () => {
    if (window.confirm("Apakah Anda yakin ingin membayar pesanan ini?")) {
      setIsCheckout(true);
      try {
        await checkoutCart();
        alert("Hore! Checkout Berhasil! 🎉");
        navigate("/orders"); // Lempar ke halaman riwayat pesanan
      } catch (error) {
        alert(
          error.response?.data?.message ||
            "Checkout gagal. Mungkin stok habis.",
        );
      } finally {
        setIsCheckout(false);
      }
    }
  };

  // Menghitung Total Harga di Frontend
  const totalBelanja = cartItems.reduce((total, item) => {
    return total + item.produk.harga * item.kuantitas;
  }, 0);

  if (loading)
    return (
      <div className="text-center py-20 font-semibold text-gray-500">
        Memuat Keranjang...
      </div>
    );

  return (
    <div className="max-w-4xl mx-auto px-4 py-10">
      <h1 className="text-3xl font-bold text-gray-900 mb-8">
        Keranjang Belanja 🛒
      </h1>

      {cartItems.length === 0 ? (
        <div className="bg-white p-10 text-center rounded-2xl shadow-sm border border-gray-100">
          <p className="text-gray-500 mb-4">Keranjang Anda masih kosong.</p>
          <button
            onClick={() => navigate("/")}
            className="text-blue-600 font-semibold hover:underline"
          >
            Mulai Belanja
          </button>
        </div>
      ) : (
        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden">
          <ul className="divide-y divide-gray-200">
            {cartItems.map((item) => (
              <li
                key={item.idItem}
                className="p-6 flex items-center justify-between hover:bg-gray-50 transition-colors"
              >
                <div className="flex items-center gap-4">
                  <div className="w-16 h-16 bg-gray-100 rounded-lg flex items-center justify-center text-2xl">
                    🛍️
                  </div>
                  <div>
                    <h3 className="text-lg font-bold text-gray-900">
                      {item.produk.namaProduk}
                    </h3>
                    <p className="text-sm text-gray-500">
                      Harga: {formatRupiah(item.produk.harga)}
                    </p>
                  </div>
                </div>
                <div className="text-right">
                  <p className="text-sm font-medium text-gray-700 mb-1">
                    Qty: {item.kuantitas}
                  </p>
                  <p className="text-lg font-bold text-blue-600">
                    {formatRupiah(item.produk.harga * item.kuantitas)}
                  </p>
                </div>
              </li>
            ))}
          </ul>

          {/* Bagian Bawah: Total & Tombol Bayar */}
          <div className="bg-gray-50 p-6 border-t border-gray-200 flex justify-between items-center">
            <div>
              <p className="text-sm text-gray-500">Total Pembayaran</p>
              <p className="text-2xl font-extrabold text-gray-900">
                {formatRupiah(totalBelanja)}
              </p>
            </div>
            <button
              onClick={handleCheckout}
              disabled={isCheckout}
              className="bg-blue-600 hover:bg-blue-700 disabled:bg-gray-400 text-white px-8 py-3 rounded-xl font-bold transition-colors shadow-sm"
            >
              {isCheckout ? "Memproses..." : "Bayar Sekarang 💸"}
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Cart;
