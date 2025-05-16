import React from 'react';
import { Link } from 'react-router-dom';
import { FaTachometerAlt, FaUsers } from 'react-icons/fa';

const Sidebar: React.FC = () => {
  return (
    <div
      style={{
        width: 220,
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #2563eb 60%, #60a5fa 100%)',
        padding: '32px 0',
        boxShadow: '2px 0 12px #e0e7ef',
        position: 'fixed',
        left: 0,
        top: 0,
        zIndex: 100,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      <h2 style={{ color: '#fff', textAlign: 'center', marginBottom: 36, fontWeight: 800, letterSpacing: 1, fontSize: 26 }}>
        <span style={{ fontSize: 22, verticalAlign: 'middle', marginRight: 8 }}>🚀</span>Quản lý
      </h2>
      <ul style={{ listStyle: 'none', padding: 0, width: '100%' }}>
        <li style={{ margin: '18px 0' }}>
          <Link
            to="/dashboard"
            style={{
              display: 'flex',
              alignItems: 'center',
              color: '#fff',
              textDecoration: 'none',
              fontWeight: 600,
              fontSize: 17,
              padding: '10px 32px',
              borderRadius: 8,
              transition: 'background 0.18s',
            }}
            onMouseOver={e => (e.currentTarget.style.background = '#1d4ed8')}
            onMouseOut={e => (e.currentTarget.style.background = 'transparent')}
          >
            <FaTachometerAlt style={{ marginRight: 12, fontSize: 20 }} /> Dashboard
          </Link>
        </li>
        <li style={{ margin: '18px 0' }}>
          <Link
            to="/employees"
            style={{
              display: 'flex',
              alignItems: 'center',
              color: '#fff',
              textDecoration: 'none',
              fontWeight: 600,
              fontSize: 17,
              padding: '10px 32px',
              borderRadius: 8,
              transition: 'background 0.18s',
            }}
            onMouseOver={e => (e.currentTarget.style.background = '#1d4ed8')}
            onMouseOut={e => (e.currentTarget.style.background = 'transparent')}
          >
            <FaUsers style={{ marginRight: 12, fontSize: 20 }} /> Quản lý nhân viên
          </Link>
        </li>
        {/* Thêm các trang quản lý khác tại đây, có thể dùng icon khác từ react-icons */}
      </ul>
    </div>
  );
};

export default Sidebar;
