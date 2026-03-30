import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import { registerUser, getAllKota } from "../../api/authService";

const Register = () => {
  const [nama, setNama] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [idKota, setIdKota] = useState(""); // State baru untuk pilihan kota

  const [kotaList, setKotaList] = useState([]); // Menyimpan daftar kota dari backend
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  // Mengambil daftar kota saat halaman pertama kali dibuka
  useEffect(() => {
    const fetchKota = async () => {
      try {
        const res = await getAllKota();
        // Sesuaikan 'res.data' dengan format ApiResponse dari Spring Boot kamu
        setKotaList(res.data || []);
      } catch (err) {
        console.error("Gagal memuat data kota:", err);
      }
    };
    fetchKota();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validasi: Pastikan kota sudah dipilih
    if (!idKota) {
      setError("Silakan pilih kota domisili Anda.");
      return;
    }

    setLoading(true);
    setError("");
    try {
      await registerUser(nama, email, password, idKota);
      alert("Registrasi berhasil! Silakan login.");
      navigate("/login");
    } catch (err) {
      setError("Registrasi gagal. Email mungkin sudah terdaftar.");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col justify-center py-12 px-4 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
          Daftar Akun Baru
        </h2>
        <p className="mt-2 text-center text-sm text-gray-600">
          Atau{" "}
          <Link
            to="/login"
            className="font-medium text-blue-600 hover:text-blue-500"
          >
            login ke akun yang sudah ada
          </Link>
        </p>
      </div>

      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-white py-8 px-4 shadow-xl shadow-gray-200/50 sm:rounded-xl sm:px-10 border border-gray-100">
          <form className="space-y-6" onSubmit={handleSubmit}>
            {error && (
              <div className="bg-red-50 border-l-4 border-red-500 p-4 text-sm text-red-700">
                {error}
              </div>
            )}

            <div>
              <label className="block text-sm font-medium text-gray-700">
                Nama Lengkap
              </label>
              <input
                type="text"
                required
                value={nama}
                onChange={(e) => setNama(e.target.value)}
                className="mt-1 appearance-none block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700">
                Email Address
              </label>
              <input
                type="email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="mt-1 appearance-none block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
              />
            </div>

            {/* INI ADALAH DROPDOWN KOTA */}
            <div>
              <label className="block text-sm font-medium text-gray-700">
                Kota Domisili
              </label>
              <select
                required
                value={idKota}
                onChange={(e) => setIdKota(e.target.value)}
                className="mt-1 block w-full pl-3 pr-10 py-2 text-base border border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm rounded-lg"
              >
                <option value="" disabled>
                  -- Pilih Kota --
                </option>
                {kotaList.map((kota) => (
                  <option key={kota.idKota} value={kota.idKota}>
                    {kota.namaKota}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700">
                Password
              </label>
              <input
                type="password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="mt-1 appearance-none block w-full px-3 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full flex justify-center py-2.5 px-4 border border-transparent rounded-lg shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition-colors disabled:opacity-50"
            >
              {loading ? "Memproses..." : "Daftar Sekarang"}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
