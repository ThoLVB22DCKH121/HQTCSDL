import { EmployeeDTO } from '../types';

const API_BASE = 'http://localhost:8080/api/employee';

export async function fetchEmployees(page = 0, size = 5, keyword = ''): Promise<{ content: EmployeeDTO[]; totalPages: number }> {
    const params = new URLSearchParams({ page: String(page), size: String(size), keyword });
    const res = await fetch(`${API_BASE}?${params}`);
    if (!res.ok) throw new Error('Failed to fetch employees');
    return res.json();
}

export async function createEmployee(employee: Omit<EmployeeDTO, 'employeeId'>): Promise<EmployeeDTO> {
    const res = await fetch(API_BASE, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(employee),
    });
    if (!res.ok) throw new Error('Failed to create employee');
    return res.json();
}

export async function updateEmployee(employeeId: string, employee: Omit<EmployeeDTO, 'employeeId'>): Promise<EmployeeDTO> {
    const res = await fetch(`${API_BASE}/${employeeId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(employee),
    });
    if (!res.ok) throw new Error('Failed to update employee');
    return res.json();
}

export async function deleteEmployee(employeeId: string): Promise<void> {
    const res = await fetch(`${API_BASE}/${employeeId}`, {
        method: 'DELETE',
    });
    if (!res.ok) throw new Error('Failed to delete employee');
}