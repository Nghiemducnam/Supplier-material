package com.codegym.service;

import com.codegym.model.Material;
import com.codegym.model.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaterialService {
    //    Iterable<Material> findAll();
    Page<Material> findAll(Pageable pageable);

    Material findById(Long id);

    void save(Material material);

    void remove(Long id);

    Iterable<Material> findAllBySupplier(Supplier supplier);

    Page<Material> findAllByName(String name, Pageable pageable);
}
