package org.example.quanlysanxuatxemay.service;

import org.example.quanlysanxuatxemay.entity.ProductInvoice;
import org.example.quanlysanxuatxemay.entity.Customer;
import org.example.quanlysanxuatxemay.repository.ProductInvoiceRepository;
import org.example.quanlysanxuatxemay.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private ProductInvoiceRepository productInvoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Page<ProductInvoice> getProductInvoicesByCustomer(String customerId, Pageable pageable) {
        return productInvoiceRepository.findByCustomer_CustomerId(customerId, pageable);
    }

    public Page<Customer> getCustomers(Pageable pageable, String keyword) {
        return customerRepository.findCustomers(pageable, keyword);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String customerId, Customer update) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) throw new IllegalArgumentException("Customer not found");
        customer.setCustomerName(update.getCustomerName());
        customer.setPhone(update.getPhone());
        customer.setAddress(update.getAddress());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
