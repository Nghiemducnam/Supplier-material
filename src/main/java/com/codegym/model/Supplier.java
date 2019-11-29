package com.codegym.model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Supplier")
public class Supplier {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long supplierId;
        private String supplierName;
        private String supDescription;
        private String address;

        @OneToMany(targetEntity = Material.class)
        private List<Material> materials;

    public Supplier() {
    }

    public Supplier(String supplierName, String supDescription, String address, List<Material> materials) {
        this.supplierName = supplierName;
        this.supDescription = supDescription;
        this.address = address;
        this.materials = materials;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupDescription() {
        return supDescription;
    }

    public void setSupDescription(String supDescription) {
        this.supDescription = supDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}