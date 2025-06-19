package org.example.quanlysanxuatxemay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/admin")
    public ModelAndView ShowPageDashboard(){
        return new ModelAndView("dashboard");
    }
    @GetMapping("/admin/customers")
    public ModelAndView ShowPageCustomers(){
        return new ModelAndView("customer");
    }
    @GetMapping("/admin/employees")
    public ModelAndView ShowPageEmployees(){
        return new ModelAndView("employee");
    }
    @GetMapping("/admin/suppliers")
    public ModelAndView ShowPageSuppliers(){
        return new ModelAndView("supplier");
    }
    @GetMapping("/admin/partsinvoices")
    public ModelAndView ShowPagePartsInvoices(){
        return new ModelAndView("parts-invoice");
    }
    @GetMapping("/admin/productinvoices")
    public ModelAndView ShowPageProductInvoices(){
        return new ModelAndView("product-invoice");
    }
    @GetMapping("/admin/productionstages")
    public ModelAndView ShowPageProductionStages(){
        return new ModelAndView("production-stage");
    }
}
