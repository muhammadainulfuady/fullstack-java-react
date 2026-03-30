import { useState, useEffect } from "react";
import { getMyOrders } from "../../api/orderService";
import { formatRupiah } from "../../utils/formatCurrency";

const OrderHistory = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const res = await getMyOrders();
        setOrders(res.data || []);
      } catch (error) {
        console.error("Gagal mengambil riwayat pesanan", error);
      } finally {
        setLoading(false);
      }
    };
    fetchOrders();
  }, []);

  if (loading)
    return (
      <div className="text-center py-20 font-semibold text-gray-500">
        Memuat Riwayat...
      </div>
    );

  return (
    <div className="max-w-5xl mx-auto px-4 py-10">
      <h1 className="text-3xl font-bold text-gray-900 mb-8">
        Riwayat Pesanan 🧾
      </h1>

      {orders.length === 0 ? (
        <div className="bg-white p-10 text-center rounded-2xl shadow-sm border border-gray-100">
          <p className="text-gray-500">
            Anda belum pernah melakukan pemesanan.
          </p>
        </div>
      ) : (
        <div className="grid gap-6">
          {orders.map((order) => (
            <div
              key={order.idOrder}
              className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6 flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 hover:shadow-md transition-shadow"
            >
              <div>
                <p className="text-xs font-bold text-gray-400 uppercase tracking-wider mb-1">
                  Order ID: #{order.idOrder}
                </p>
                <p className="text-sm text-gray-600 mb-2">
                  Tanggal:{" "}
                  {new Date(order.tanggalOrder).toLocaleDateString("id-ID", {
                    weekday: "long",
                    year: "numeric",
                    month: "long",
                    day: "numeric",
                    hour: "2-digit",
                    minute: "2-digit",
                  })}
                </p>
                <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
                  Berhasil Dipesan
                </span>
              </div>
              <div className="text-left sm:text-right">
                <p className="text-sm text-gray-500 mb-1">Total Belanja</p>
                <p className="text-2xl font-extrabold text-blue-600">
                  {formatRupiah(order.total)}
                </p>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default OrderHistory;
