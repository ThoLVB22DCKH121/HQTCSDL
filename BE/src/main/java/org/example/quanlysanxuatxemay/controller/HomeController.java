package org.example.quanlysanxuatxemay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("")
    public ModelAndView ShowPageDashboard(){
        return new ModelAndView("dashboard");
    }
    @GetMapping("/customers")
    public ModelAndView ShowPageCustomers(){
        return new ModelAndView("Customer");
    }
    @GetMapping("/employees")
    public ModelAndView ShowPageEmployees(){
        return new ModelAndView("Employee");
    }
    @GetMapping("/suppliers")
    public ModelAndView ShowPageSuppliers(){
        return new ModelAndView("Supplier");
    }
    @GetMapping("/parts_invoices")
    public ModelAndView ShowPagePartsInvoices(){
        return new ModelAndView("PartsInvoice");
    }
    @GetMapping("/product_invoices")
    public ModelAndView ShowPageProductInvoices(){
        return new ModelAndView("ProductInvoice");
    }
    @GetMapping("/production_stages")
    public ModelAndView ShowPageProductionStages(){
        return new ModelAndView("ProductionStage");
    }
}
