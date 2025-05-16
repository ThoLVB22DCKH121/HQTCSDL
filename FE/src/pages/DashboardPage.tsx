import React, { useEffect, useState } from 'react';
import Sidebar from '../components/Sidebar';
import axios from 'axios';
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid, Legend } from 'recharts';

type PartStat = {
  partId: string;
  partName: string;
  totalQuantity: number;
  totalCost: number;
};

type TopProduct = {
  productId: string;
  productName: string;
  quantity: number;
};

type ProductRevenue = {
  motorbikeId: string;
  motorbikeName: string;
  totalRevenue: number;
};

const DashboardPage: React.FC = () => {
  const [monthlyRevenue, setMonthlyRevenue] = useState<{ month: string; total: number }[]>([]);
  const [partStats, setPartStats] = useState<PartStat[]>([]);
  const [topProducts, setTopProducts] = useState<TopProduct[]>([]);
  const [productRevenues, setProductRevenues] = useState<ProductRevenue[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [revenuePage, setRevenuePage] = useState(0);
  const [partPage, setPartPage] = useState(0);
  const [topProductsPage, setTopProductsPage] = useState(0);
  const [productRevenuePage, setProductRevenuePage] = useState(0);
  const [selectedYear, setSelectedYear] = useState<number>(new Date().getFullYear());
  const PAGE_SIZE = 6;
  const TOP_PRODUCTS_PAGE_SIZE = 6;
  const PRODUCT_REVENUE_PAGE_SIZE = 6;
  const pagedRevenue = monthlyRevenue.slice(revenuePage * PAGE_SIZE, (revenuePage + 1) * PAGE_SIZE);
  const pagedParts = partStats.slice(partPage * PAGE_SIZE, (partPage + 1) * PAGE_SIZE);
  const pagedTopProducts = topProducts.slice(topProductsPage * TOP_PRODUCTS_PAGE_SIZE, (topProductsPage + 1) * TOP_PRODUCTS_PAGE_SIZE);
  const pagedProductRevenues = productRevenues.slice(productRevenuePage * PRODUCT_REVENUE_PAGE_SIZE, (productRevenuePage + 1) * PRODUCT_REVENUE_PAGE_SIZE);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      setError('');
      try {
        // Lấy doanh thu theo tháng theo năm
        const revenueRes = await axios.get(`http://localhost:8080/api/product-invoices/monthly-revenue?year=${selectedYear}`);
        const revenueData: Record<string, number> = (revenueRes.data || {}) as Record<string, number>;
        const revenueArr = Object.entries(revenueData).map(([month, total]) => ({ month, total: Number(total) }));
        setMonthlyRevenue(revenueArr);
        // Lấy thống kê nhập linh kiện
        const partRes = await axios.get('http://localhost:8080/api/parts-invoices/part-import-stats');
        setPartStats(Array.isArray(partRes.data) ? partRes.data : []);
        // Lấy top sản phẩm bán chạy theo năm
        const topRes = await axios.get(`http://localhost:8080/api/product-invoices/top-selling-products?year=${selectedYear}`);
        setTopProducts(Array.isArray(topRes.data) ? topRes.data.map((arr: any[]) => ({ productId: arr[0], productName: arr[1], quantity: arr[2] })) : []);
        // Lấy doanh thu từng sản phẩm theo năm
        const prodRevRes = await axios.get(`http://localhost:8080/api/product-invoices/product-revenue?year=${selectedYear}`);
        setProductRevenues(Array.isArray(prodRevRes.data) ? prodRevRes.data : []);
      } catch (e: any) {
        setError(e.message || 'Lỗi khi tải dữ liệu');
      }
      setLoading(false);
    };
    fetchData();
  }, [selectedYear]);

  return (
    <div style={{ display: 'flex', background: 'linear-gradient(120deg, #f8fafc 60%, #e0e7ff 100%)', minHeight: '100vh', width: '100vw', overflowX: 'hidden' }}>
      <Sidebar />
      <div style={{ flex: 1, marginLeft: 260, maxWidth: 1400, margin: '32px auto', background: '#fff', borderRadius: 18, boxShadow: '0 6px 32px rgba(30,41,59,0.10)', padding: 40, minHeight: 700, fontFamily: 'Segoe UI, Arial, sans-serif', fontSize: 16 }}>
        <h1 style={{ fontSize: 38, fontWeight: 800, color: '#1e293b', marginBottom: 32, textAlign: 'center', letterSpacing: 1, fontFamily: 'Montserrat, Segoe UI, Arial, sans-serif' }}>Dashboard thống kê</h1>
        {loading ? (
          <p style={{ color: '#64748b', fontStyle: 'italic', fontSize: 18 }}>Đang tải dữ liệu...</p>
        ) : error ? (
          <p style={{ color: '#ef4444', fontWeight: 600, fontSize: 18 }}>{error}</p>
        ) : (
          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(2, 1fr)',
            gridTemplateRows: 'repeat(2, auto)',
            gap: 36,
            width: '100%',
            alignItems: 'stretch',
            minHeight: 'calc(100vh - 120px)'
          }}>
            {/* Doanh thu theo tháng */}
            <section style={{ flex: 1, minWidth: 340, maxWidth: 540, background: 'linear-gradient(120deg, #f9fafb 80%, #e0e7ff 100%)', borderRadius: 14, boxShadow: '0 2px 12px rgba(30,41,59,0.07)', padding: 24, margin: '0 auto', fontFamily: 'Segoe UI, Arial, sans-serif' }}>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: 18 }}>
                <h2 style={{ color: '#2563eb', fontSize: 22, fontWeight: 800, marginBottom: 14, letterSpacing: 0.5, fontFamily: 'Montserrat, Segoe UI, Arial, sans-serif' }}>Doanh thu bán hàng theo tháng</h2>
                <div>
                  <label htmlFor="year-select" style={{ marginRight: 8, color: '#2563eb', fontWeight: 500 }}>Năm:</label>
                  <select id="year-select" value={selectedYear} onChange={e => setSelectedYear(Number(e.target.value))} style={{ padding: '4px 10px', borderRadius: 4, border: '1px solid #2563eb', color: '#2563eb', fontWeight: 600 }}>
                    {Array.from({ length: 6 }).map((_, idx) => {
                      const year = new Date().getFullYear() - idx;
                      return <option key={year} value={year}>{year}</option>;
                    })}
                  </select>
                </div>
              </div>
              <div style={{ width: '100%', height: 220, marginBottom: 16 }}>
                <ResponsiveContainer width="100%" height="100%">
                  <BarChart data={monthlyRevenue} margin={{ top: 10, right: 20, left: 0, bottom: 5 }}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="month" />
                    <YAxis tickFormatter={v => v.toLocaleString('vi-VN')} />
                    <Tooltip formatter={(value: any) => `${Number(value).toLocaleString('vi-VN')} đ`} />
                    <Legend />
                    <Bar dataKey="total" name="Doanh thu" fill="#2563eb" radius={[6, 6, 0, 0]} />
                  </BarChart>
                </ResponsiveContainer>
              </div>
              <table style={{ width: '100%', borderCollapse: 'collapse', background: '#fff', borderRadius: 10, overflow: 'hidden', fontSize: 16, fontFamily: 'Segoe UI, Arial, sans-serif', boxShadow: '0 1px 4px rgba(30,41,59,0.04)' }}>
                <thead style={{ background: '#2563eb' }}>
                  <tr>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#2563eb', border: 'none' }}>Tháng</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#2563eb', border: 'none' }}>Tổng doanh thu</th>
                  </tr>
                </thead>
                <tbody>
                  {pagedRevenue.map(item => (
                    <tr key={item.month} style={{ background: '#fff', borderBottom: '1px solid #e5e7eb' }}>
                      <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.month}</td>
                      <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.total && !isNaN(Number(item.total)) ? Number(item.total).toLocaleString('vi-VN') : 0} đ</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <div style={{ display: 'flex', justifyContent: 'center', marginTop: 8 }}>
                <button onClick={() => setRevenuePage(p => Math.max(0, p - 1))} disabled={revenuePage === 0} style={{ marginRight: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #2563eb', background: revenuePage === 0 ? '#e5e7eb' : '#fff', color: '#2563eb', cursor: revenuePage === 0 ? 'not-allowed' : 'pointer' }}>Trước</button>
                <span style={{ lineHeight: '28px', color: '#2563eb', fontWeight: 600 }}>{revenuePage + 1}/{Math.ceil(monthlyRevenue.length / PAGE_SIZE)}</span>
                <button onClick={() => setRevenuePage(p => p + 1)} disabled={(revenuePage + 1) * PAGE_SIZE >= monthlyRevenue.length} style={{ marginLeft: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #2563eb', background: (revenuePage + 1) * PAGE_SIZE >= monthlyRevenue.length ? '#e5e7eb' : '#fff', color: '#2563eb', cursor: (revenuePage + 1) * PAGE_SIZE >= monthlyRevenue.length ? 'not-allowed' : 'pointer' }}>Sau</button>
              </div>
            </section>
            {/* Thống kê nhập linh kiện */}
            <section style={{ flex: 1, minWidth: 340, maxWidth: 540, background: 'linear-gradient(120deg, #f9fafb 80%, #e0e7ff 100%)', borderRadius: 14, boxShadow: '0 2px 12px rgba(30,41,59,0.07)', padding: 24, margin: '0 auto', fontFamily: 'Segoe UI, Arial, sans-serif' }}>
              <h2 style={{ color: '#2563eb', fontSize: 22, fontWeight: 800, marginBottom: 14, letterSpacing: 0.5, fontFamily: 'Montserrat, Segoe UI, Arial, sans-serif' }}>Thống kê nhập linh kiện</h2>
              <table style={{ width: '100%', borderCollapse: 'collapse', background: '#fff', borderRadius: 10, overflow: 'hidden', fontSize: 16, fontFamily: 'Segoe UI, Arial, sans-serif', boxShadow: '0 1px 4px rgba(30,41,59,0.04)' }}>
                <thead style={{ background: '#2563eb' }}>
                  <tr>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#2563eb', border: 'none' }}>Mã LK</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#2563eb', border: 'none' }}>Tên linh kiện</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#2563eb', border: 'none' }}>Số lượng nhập</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#2563eb', border: 'none' }}>Tổng tiền nhập</th>
                  </tr>
                </thead>
                <tbody>
                  {pagedParts.length > 0 ? (
                    pagedParts.map((item: PartStat, idx: number) => (
                      <tr key={item.partId ?? idx} style={{ background: '#fff', borderBottom: '1px solid #e5e7eb' }}>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.partId}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.partName}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.totalQuantity}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.totalCost.toLocaleString('vi-VN')} đ</td>
                      </tr>
                    ))
                  ) : (
                    <tr><td colSpan={4} style={{ textAlign: 'center', color: '#64748b', padding: 16 }}>Không có dữ liệu</td></tr>
                  )}
                </tbody>
              </table>
              <div style={{ display: 'flex', justifyContent: 'center', marginTop: 8 }}>
                <button onClick={() => setPartPage(p => Math.max(0, p - 1))} disabled={partPage === 0} style={{ marginRight: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #2563eb', background: partPage === 0 ? '#e5e7eb' : '#fff', color: '#2563eb', cursor: partPage === 0 ? 'not-allowed' : 'pointer' }}>Trước</button>
                <span style={{ lineHeight: '28px', color: '#2563eb', fontWeight: 600 }}>{partPage + 1}/{Math.ceil(partStats.length / PAGE_SIZE)}</span>
                <button onClick={() => setPartPage(p => p + 1)} disabled={(partPage + 1) * PAGE_SIZE >= partStats.length} style={{ marginLeft: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #2563eb', background: (partPage + 1) * PAGE_SIZE >= partStats.length ? '#e5e7eb' : '#fff', color: '#2563eb', cursor: (partPage + 1) * PAGE_SIZE >= partStats.length ? 'not-allowed' : 'pointer' }}>Sau</button>
              </div>
            </section>
            {/* Top sản phẩm bán chạy */}
            <section style={{ flex: 1, minWidth: 340, maxWidth: 540, background: 'linear-gradient(120deg, #f9fafb 80%, #e0e7ff 100%)', borderRadius: 14, boxShadow: '0 2px 12px rgba(30,41,59,0.07)', padding: 24, margin: '0 auto', fontFamily: 'Segoe UI, Arial, sans-serif' }}>
              <h2 style={{ color: '#16a34a', fontSize: 22, fontWeight: 800, marginBottom: 14, letterSpacing: 0.5, fontFamily: 'Montserrat, Segoe UI, Arial, sans-serif' }}>Top sản phẩm bán chạy</h2>
              <table style={{ width: '100%', borderCollapse: 'collapse', background: '#fff', borderRadius: 10, overflow: 'hidden', fontSize: 16, fontFamily: 'Segoe UI, Arial, sans-serif', boxShadow: '0 1px 4px rgba(30,41,59,0.04)' }}>
                <thead style={{ background: '#16a34a' }}>
                  <tr>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#16a34a', border: 'none' }}>Mã sản phẩm</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#16a34a', border: 'none' }}>Tên sản phẩm</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#16a34a', border: 'none' }}>Số lượng bán</th>
                  </tr>
                </thead>
                <tbody>
                  {pagedTopProducts.length > 0 ? (
                    pagedTopProducts.map((item, idx) => (
                      <tr key={item.productId + idx} style={{ background: '#fff', borderBottom: '1px solid #e5e7eb' }}>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.productId}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.productName}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.quantity}</td>
                      </tr>
                    ))
                  ) : (
                    <tr><td colSpan={3} style={{ textAlign: 'center', color: '#64748b', padding: 16 }}>Không có dữ liệu</td></tr>
                  )}
                </tbody>
              </table>
              <div style={{ display: 'flex', justifyContent: 'center', marginTop: 8 }}>
                <button onClick={() => setTopProductsPage(p => Math.max(0, p - 1))} disabled={topProductsPage === 0} style={{ marginRight: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #16a34a', background: topProductsPage === 0 ? '#e5e7eb' : '#fff', color: '#16a34a', cursor: topProductsPage === 0 ? 'not-allowed' : 'pointer' }}>Trước</button>
                <span style={{ lineHeight: '28px', color: '#16a34a', fontWeight: 600 }}>{topProductsPage + 1}/{Math.ceil(topProducts.length / TOP_PRODUCTS_PAGE_SIZE)}</span>
                <button onClick={() => setTopProductsPage(p => p + 1)} disabled={(topProductsPage + 1) * TOP_PRODUCTS_PAGE_SIZE >= topProducts.length} style={{ marginLeft: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #16a34a', background: (topProductsPage + 1) * TOP_PRODUCTS_PAGE_SIZE >= topProducts.length ? '#e5e7eb' : '#fff', color: '#16a34a', cursor: (topProductsPage + 1) * TOP_PRODUCTS_PAGE_SIZE >= topProducts.length ? 'not-allowed' : 'pointer' }}>Sau</button>
              </div>
            </section>
            {/* Doanh thu từng sản phẩm */}
            <section style={{ flex: 1, minWidth: 340, maxWidth: 540, background: 'linear-gradient(120deg, #f9fafb 80%, #e0e7ff 100%)', borderRadius: 14, boxShadow: '0 2px 12px rgba(30,41,59,0.07)', padding: 24, margin: '0 auto', fontFamily: 'Segoe UI, Arial, sans-serif' }}>
              <h2 style={{ color: '#f59e42', fontSize: 22, fontWeight: 800, marginBottom: 14, letterSpacing: 0.5, fontFamily: 'Montserrat, Segoe UI, Arial, sans-serif' }}>Doanh thu từng sản phẩm</h2>
              <table style={{ width: '100%', borderCollapse: 'collapse', background: '#fff', borderRadius: 10, overflow: 'hidden', fontSize: 16, fontFamily: 'Segoe UI, Arial, sans-serif', boxShadow: '0 1px 4px rgba(30,41,59,0.04)' }}>
                <thead style={{ background: '#f59e42' }}>
                  <tr>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#f59e42', border: 'none' }}>Mã SP</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#f59e42', border: 'none' }}>Tên sản phẩm</th>
                    <th style={{ padding: 10, color: '#fff', fontWeight: 700, fontSize: 16, letterSpacing: 0.5, background: '#f59e42', border: 'none' }}>Tổng doanh thu</th>
                  </tr>
                </thead>
                <tbody>
                  {pagedProductRevenues.length > 0 ? (
                    pagedProductRevenues.map((item, idx) => (
                      <tr key={item.motorbikeId + idx} style={{ background: '#fff', borderBottom: '1px solid #e5e7eb' }}>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.motorbikeId}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>{item.motorbikeName}</td>
                        <td style={{ padding: 10, color: '#1e293b', fontWeight: 500, fontSize: 15, border: 'none' }}>
                          {typeof item.totalRevenue === 'number' && !isNaN(item.totalRevenue)
                            ? item.totalRevenue.toLocaleString('vi-VN')
                            : '0'} đ
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr><td colSpan={4} style={{ textAlign: 'center', color: '#64748b', padding: 16 }}>Không có dữ liệu</td></tr>
                  )}
                </tbody>
              </table>
              <div style={{ display: 'flex', justifyContent: 'center', marginTop: 8 }}>
                <button onClick={() => setProductRevenuePage(p => Math.max(0, p - 1))} disabled={productRevenuePage === 0} style={{ marginRight: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #f59e42', background: productRevenuePage === 0 ? '#e5e7eb' : '#fff', color: '#f59e42', cursor: productRevenuePage === 0 ? 'not-allowed' : 'pointer' }}>Trước</button>
                <span style={{ lineHeight: '28px', color: '#f59e42', fontWeight: 600 }}>{productRevenuePage + 1}/{Math.ceil(productRevenues.length / PRODUCT_REVENUE_PAGE_SIZE)}</span>
                <button onClick={() => setProductRevenuePage(p => p + 1)} disabled={(productRevenuePage + 1) * PRODUCT_REVENUE_PAGE_SIZE >= productRevenues.length} style={{ marginLeft: 8, padding: '2px 10px', borderRadius: 4, border: '1px solid #f59e42', background: (productRevenuePage + 1) * PRODUCT_REVENUE_PAGE_SIZE >= productRevenues.length ? '#e5e7eb' : '#fff', color: '#f59e42', cursor: (productRevenuePage + 1) * PRODUCT_REVENUE_PAGE_SIZE >= productRevenues.length ? 'not-allowed' : 'pointer' }}>Sau</button>
              </div>
            </section>
          </div>
        )}
      </div>
    </div>
  );
};

export default DashboardPage;
