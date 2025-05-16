import React, { useEffect, useState } from 'react';
import { fetchEmployees, deleteEmployee } from '../services/api';
import { EmployeeDTO } from '../types';

interface EmployeeListProps {
  onEdit: (employee: EmployeeDTO) => void;
  pageSize?: number;
}

const EmployeeList: React.FC<EmployeeListProps> = ({ onEdit, pageSize = 5 }) => {
  const [employees, setEmployees] = useState<EmployeeDTO[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [keyword, setKeyword] = useState('');
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);

  const loadEmployees = async () => {
    setLoading(true);
    setError('');
    try {
      const data = await fetchEmployees(page, pageSize, keyword);
      setEmployees(data.content);
      setTotalPages(data.totalPages);
    } catch (e: any) {
      setError(e.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadEmployees();
    // eslint-disable-next-line
  }, [page, keyword]);

  const handleDelete = async (id: string) => {
    if (window.confirm('Bạn có chắc muốn xóa nhân viên này?')) {
      await deleteEmployee(id);
      loadEmployees();
    }
  };

  return (
    <div style={{
      background: '#fff',
      borderRadius: 10,
      boxShadow: '0 2px 12px rgba(0,0,0,0.07)',
      padding: 24,
      marginTop: 16,
    }}>
      <div style={{ display: 'flex', alignItems: 'center', marginBottom: 18 }}>
        <h2 style={{ flex: 1, fontSize: 24, color: '#2563eb', margin: 0 }}>Danh sách nhân viên</h2>
        <input
          type="text"
          placeholder="Tìm kiếm theo tên, SĐT, chức vụ..."
          value={keyword}
          onChange={e => setKeyword(e.target.value)}
          style={{
            padding: '8px 14px',
            borderRadius: 6,
            border: '1px solid #cbd5e1',
            fontSize: 15,
            outline: 'none',
            width: 260,
            marginLeft: 12,
            background: '#f1f5f9',
            transition: 'border 0.2s',
          }}
        />
      </div>
      {loading ? (
        <p style={{ color: '#64748b', fontStyle: 'italic' }}>Đang tải...</p>
      ) : error ? (
        <p style={{ color: 'red' }}>{error}</p>
      ) : (
        <div style={{ overflowX: 'auto' }}>
          <table style={{ width: '100%', marginTop: 10, borderCollapse: 'collapse', background: '#f9fafb', borderRadius: 8, overflow: 'hidden', boxShadow: '0 1px 4px rgba(0,0,0,0.03)' }}>
            <thead style={{ background: '#2563eb' }}>
              <tr>
                <th style={{ padding: 10, color: '#fff', fontWeight: 600 }}>ID</th>
                <th style={{ padding: 10, color: '#fff', fontWeight: 600 }}>Tên</th>
                <th style={{ padding: 10, color: '#fff', fontWeight: 600 }}>Điện thoại</th>
                <th style={{ padding: 10, color: '#fff', fontWeight: 600 }}>Chức vụ</th>
                <th style={{ padding: 10, color: '#fff', fontWeight: 600 }}>Hành động</th>
              </tr>
            </thead>
            <tbody>
              {employees.length === 0 ? (
                <tr>
                  <td colSpan={5} style={{ textAlign: 'center', padding: 24, color: '#64748b' }}>Không có dữ liệu</td>
                </tr>
              ) : employees.map(emp => (
                <tr key={emp.employeeId} style={{ background: '#fff', borderBottom: '1px solid #e5e7eb' }}>
                  <td style={{ padding: 10 }}>{emp.employeeId}</td>
                  <td style={{ padding: 10 }}>{emp.employeeName}</td>
                  <td style={{ padding: 10 }}>{emp.phone}</td>
                  <td style={{ padding: 10 }}>{emp.position}</td>
                  <td style={{ padding: 10 }}>
                    <button
                      onClick={() => onEdit(emp)}
                      style={{
                        background: '#22c55e',
                        color: '#fff',
                        border: 'none',
                        borderRadius: 5,
                        padding: '6px 14px',
                        fontWeight: 500,
                        cursor: 'pointer',
                        marginRight: 8,
                        transition: 'background 0.2s',
                      }}
                      onMouseOver={e => (e.currentTarget.style.background = '#16a34a')}
                      onMouseOut={e => (e.currentTarget.style.background = '#22c55e')}
                    >
                      Sửa
                    </button>
                    <button
                      onClick={() => handleDelete(emp.employeeId)}
                      style={{
                        background: '#ef4444',
                        color: '#fff',
                        border: 'none',
                        borderRadius: 5,
                        padding: '6px 14px',
                        fontWeight: 500,
                        cursor: 'pointer',
                        transition: 'background 0.2s',
                      }}
                      onMouseOver={e => (e.currentTarget.style.background = '#b91c1c')}
                      onMouseOut={e => (e.currentTarget.style.background = '#ef4444')}
                    >
                      Xóa
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
      <div style={{ marginTop: 18, display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
        <button
          onClick={() => setPage(p => Math.max(0, p - 1))}
          disabled={page === 0}
          style={{
            background: page === 0 ? '#e5e7eb' : '#2563eb',
            color: page === 0 ? '#64748b' : '#fff',
            border: 'none',
            borderRadius: 5,
            padding: '7px 16px',
            fontWeight: 600,
            marginRight: 10,
            cursor: page === 0 ? 'not-allowed' : 'pointer',
            transition: 'background 0.2s',
          }}
        >
          Trước
        </button>
        <span style={{ fontWeight: 500, color: '#334155', fontSize: 16 }}>
          Trang {page + 1} / {totalPages}
        </span>
        <button
          onClick={() => setPage(p => Math.min(totalPages - 1, p + 1))}
          disabled={page + 1 >= totalPages}
          style={{
            background: page + 1 >= totalPages ? '#e5e7eb' : '#2563eb',
            color: page + 1 >= totalPages ? '#64748b' : '#fff',
            border: 'none',
            borderRadius: 5,
            padding: '7px 16px',
            fontWeight: 600,
            marginLeft: 10,
            cursor: page + 1 >= totalPages ? 'not-allowed' : 'pointer',
            transition: 'background 0.2s',
          }}
        >
          Sau
        </button>
      </div>
    </div>
  );
};

export default EmployeeList;
