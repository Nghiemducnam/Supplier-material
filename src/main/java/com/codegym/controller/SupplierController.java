package com.codegym.controller;

import com.codegym.model.Supplier;
//import com.codegym.service.MaterialService;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SupplierController {
    @Autowired
    SupplierService supplierService;
//    @Autowired
//    MaterialService materialService;

    @GetMapping("/suppliers")
    public ModelAndView List(){
        Iterable<Supplier>suppliers = supplierService.findAll();
        ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/list");
        modelAndView.addObject("suppliers",suppliers);
        return modelAndView;
    }

    @GetMapping("/suppliers-create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/create");
        modelAndView.addObject("supplier", new Supplier());
        return modelAndView;
    }

    @PostMapping("/suppliers-create")
    public ModelAndView saveSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/create");
        modelAndView.addObject("supplier",new Supplier());
        modelAndView.addObject("message", "created a new Supplier");
        return modelAndView;
    }
    @GetMapping("/suppliers-edit/{id}")
    public ModelAndView showEditFOrm(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/edit");
            modelAndView.addObject("suppliers", supplier);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/suppliers-edit")
    public ModelAndView updateSupplier(@ModelAttribute("suppliers") Supplier supplier ){
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/edit");
        modelAndView.addObject("suppliers", new Supplier());
        modelAndView.addObject("message","Edited success");
        return modelAndView;
        }

    @GetMapping("/suppliers-delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/delete");
            modelAndView.addObject("suppliers", supplier);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/suppliers-delete")
    public String delete(@ModelAttribute("suppliers") Supplier supplier){
        supplierService.remove(supplier.getSupplierId());
        return "redirect:suppliers";
    }

    @GetMapping("/suppliers-detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            ModelAndView modelAndView = new ModelAndView("viewsOfSuppliers/detail");
            modelAndView.addObject("suppliers", supplier);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error/error.404");
            return modelAndView;
        }
    }
}