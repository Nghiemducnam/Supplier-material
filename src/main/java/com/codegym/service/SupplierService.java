package com.codegym.service;

import com.codegym.model.Material;
import com.codegym.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
    Iterable<Supplier> findAll();

    Supplier findById(Long id);

    void save(Supplier supplier);

    void remove(Long id);

}
