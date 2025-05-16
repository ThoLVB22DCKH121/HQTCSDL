import React, { useState } from 'react';
import EmployeeList from '../components/EmployeeList';
import EmployeeForm from '../components/EmployeeForm';
import Sidebar from '../components/Sidebar';
import { createEmployee, updateEmployee } from '../services/api';
import { EmployeeDTO } from '../types';

const EmployeesPage: React.FC = () => {
  const [editing, setEditing] = useState<EmployeeDTO | null>(null);
  const [showForm, setShowForm] = useState(false);
  const [reload, setReload] = useState(0);

  const handleEdit = (employee: EmployeeDTO) => {
    setEditing(employee);
    setShowForm(true);
  };

  const handleAdd = () => {
    setEditing(null);
    setShowForm(true);
  };

  const handleSubmit = async (data: Omit<EmployeeDTO, 'employeeId'>, employeeId?: string) => {
    if (employeeId) {
      await updateEmployee(employeeId, data);
    } else {
      await createEmployee(data);
    }
    setShowForm(false);
    setEditing(null);
    setReload(r => r + 1);
  };

  return (
    <div style={{ display: 'flex', background: '#f8fafc', minHeight: '100vh' }}>
      <Sidebar />
      <div
        style={{
          flex: 1,
          marginLeft: 220,
          maxWidth: 900,
          margin: '32px auto',
          background: '#fff',
          borderRadius: 12,
          boxShadow: '0 4px 24px rgba(0,0,0,0.08)',
          padding: 32,
          minHeight: 600,
        }}
      >
        <h1 style={{ fontSize: 32, fontWeight: 700, color: '#1e293b', marginBottom: 24, textAlign: 'center' }}>
          Quản trị nhân viên
        </h1>
        <div style={{ display: 'flex', justifyContent: 'flex-end', marginBottom: 20 }}>
          <button
            onClick={handleAdd}
            style={{
              background: '#2563eb',
              color: '#fff',
              border: 'none',
              borderRadius: 6,
              padding: '10px 20px',
              fontWeight: 600,
              fontSize: 16,
              cursor: 'pointer',
              boxShadow: '0 2px 8px rgba(37,99,235,0.08)',
              transition: 'background 0.2s',
            }}
            onMouseOver={e => (e.currentTarget.style.background = '#1d4ed8')}
            onMouseOut={e => (e.currentTarget.style.background = '#2563eb')}
          >
            Thêm nhân viên
          </button>
        </div>
        {showForm && (
          <EmployeeForm
            initialData={editing}
            onSubmit={handleSubmit}
            onCancel={() => {
              setShowForm(false);
              setEditing(null);
            }}
          />
        )}
        <div style={{ marginTop: 24 }}>
          {/* Truyền reload để khi thêm/sửa/xóa sẽ load lại danh sách */}
          <EmployeeList key={reload} onEdit={handleEdit} pageSize={5} />
        </div>
      </div>
    </div>
  );
};

export default EmployeesPage;
