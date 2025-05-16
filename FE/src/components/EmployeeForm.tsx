import React, { useState, useEffect } from 'react';
import { EmployeeDTO } from '../types';

interface EmployeeFormProps {
  initialData?: EmployeeDTO | null;
  onSubmit: (data: Omit<EmployeeDTO, 'employeeId'>, employeeId?: string) => void;
  onCancel: () => void;
}

const EmployeeForm: React.FC<EmployeeFormProps> = ({ initialData, onSubmit, onCancel }) => {
  const [employeeName, setEmployeeName] = useState('');
  const [phone, setPhone] = useState('');
  const [position, setPosition] = useState('');

  useEffect(() => {
    if (initialData) {
      setEmployeeName(initialData.employeeName);
      setPhone(initialData.phone);
      setPosition(initialData.position);
    } else {
      setEmployeeName('');
      setPhone('');
      setPosition('');
    }
  }, [initialData]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ employeeName, phone, position }, initialData?.employeeId);
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{
        background: '#f1f5f9',
        borderRadius: 10,
        boxShadow: '0 2px 8px rgba(0,0,0,0.06)',
        padding: 28,
        margin: '24px 0',
        maxWidth: 500,
        marginLeft: 'auto',
        marginRight: 'auto',
      }}
    >
      <h3 style={{ textAlign: 'center', color: '#2563eb', fontSize: 22, fontWeight: 700, marginBottom: 24 }}>
        {initialData ? 'Sửa nhân viên' : 'Thêm nhân viên'}
      </h3>
      <div style={{ marginBottom: 18 }}>
        <label style={{ display: 'block', fontWeight: 600, marginBottom: 6 }}>Tên</label>
        <input
          value={employeeName}
          onChange={e => setEmployeeName(e.target.value)}
          required
          style={{
            width: '100%',
            padding: '10px 14px',
            borderRadius: 6,
            border: '1px solid #cbd5e1',
            fontSize: 16,
            background: '#fff',
            outline: 'none',
            transition: 'border 0.2s',
          }}
        />
      </div>
      <div style={{ marginBottom: 18 }}>
        <label style={{ display: 'block', fontWeight: 600, marginBottom: 6 }}>Điện thoại</label>
        <input
          value={phone}
          onChange={e => setPhone(e.target.value)}
          required
          style={{
            width: '100%',
            padding: '10px 14px',
            borderRadius: 6,
            border: '1px solid #cbd5e1',
            fontSize: 16,
            background: '#fff',
            outline: 'none',
            transition: 'border 0.2s',
          }}
        />
      </div>
      <div style={{ marginBottom: 24 }}>
        <label style={{ display: 'block', fontWeight: 600, marginBottom: 6 }}>Chức vụ</label>
        <input
          value={position}
          onChange={e => setPosition(e.target.value)}
          required
          style={{
            width: '100%',
            padding: '10px 14px',
            borderRadius: 6,
            border: '1px solid #cbd5e1',
            fontSize: 16,
            background: '#fff',
            outline: 'none',
            transition: 'border 0.2s',
          }}
        />
      </div>
      <div style={{ display: 'flex', justifyContent: 'center', gap: 16 }}>
        <button
          type="submit"
          style={{
            background: '#2563eb',
            color: '#fff',
            border: 'none',
            borderRadius: 6,
            padding: '10px 28px',
            fontWeight: 600,
            fontSize: 16,
            cursor: 'pointer',
            boxShadow: '0 2px 8px rgba(37,99,235,0.08)',
            transition: 'background 0.2s',
          }}
          onMouseOver={e => (e.currentTarget.style.background = '#1d4ed8')}
          onMouseOut={e => (e.currentTarget.style.background = '#2563eb')}
        >
          {initialData ? 'Cập nhật' : 'Thêm mới'}
        </button>
        <button
          type="button"
          onClick={onCancel}
          style={{
            background: '#e5e7eb',
            color: '#334155',
            border: 'none',
            borderRadius: 6,
            padding: '10px 28px',
            fontWeight: 600,
            fontSize: 16,
            cursor: 'pointer',
            transition: 'background 0.2s',
          }}
          onMouseOver={e => (e.currentTarget.style.background = '#cbd5e1')}
          onMouseOut={e => (e.currentTarget.style.background = '#e5e7eb')}
        >
          Hủy
        </button>
      </div>
    </form>
  );
};

export default EmployeeForm;
