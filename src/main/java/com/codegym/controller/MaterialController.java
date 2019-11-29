package com.codegym.controller;

import com.codegym.model.Material;
import com.codegym.model.Supplier;
import com.codegym.service.MaterialService;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
   private SupplierService supplierService;

    @ModelAttribute("suppliers")
    public Iterable<Supplier> suppliers() {
        return supplierService.findAll();
    }
//
//    @GetMapping("/materials")
//    public ModelAndView MaterialList(Pageable pageable){
//        Page<Material> materials = materialService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/list");
//        modelAndView.addObject("materials", materials);
//        return modelAndView;
//    }
    @GetMapping("/materials-create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/create");
        modelAndView.addObject("materials",new Material());
        return modelAndView;
    }
    @PostMapping("/materials-create")
    public ModelAndView saveMaterial(@ModelAttribute("materials") Material material){
        materialService.save(material);
        ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/create");
        modelAndView.addObject("materials", new Material());
        modelAndView.addObject("message","created a new material");
        return modelAndView;
    }

    @GetMapping("/materials-edit/{id}")
    public ModelAndView showEditFOrm(@PathVariable Long id) {
        Material material = materialService.findById(id);
        if (material != null) {
            ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/edit");
            modelAndView.addObject("materials", material);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/materials-edit")
    public ModelAndView updateSupplier(@ModelAttribute("materials") Material material ){
        materialService.save(material);
        ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/edit");
        modelAndView.addObject("materials", new Material());
        modelAndView.addObject("message","Edited success");
        return modelAndView;
    }

    @GetMapping("/materials-delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id) {
        Material material = materialService.findById(id);
        if (material != null) {
            ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/delete");
            modelAndView.addObject("materials", material);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/materials-delete")
    public String delete(@ModelAttribute("materials") Material material){
        materialService.remove(material.getMaterialId());
        return "redirect:materials";
    }

    @GetMapping("/materials-detail/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        Material material = materialService.findById(id);
        if (material != null) {
            ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/detail");
            modelAndView.addObject("materials", material);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error/error.404");
            return modelAndView;
        }
    }

    @GetMapping("/materials")
    public ModelAndView listMaterials(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Material> materials;
        if(s.isPresent()){
            materials = materialService.findAllByName(s.get(), pageable);
        } else {
            materials = materialService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("viewsOfMaterial/list");
        modelAndView.addObject("materials", materials);
        return modelAndView;
    }

}