/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmateus.compasso.repositories;

import com.pmateus.compasso.domains.Product;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Paulo Mateus
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Override
    public Optional<Product> findById(Long id);

    @Override
    public List<Product> findAll();

    @Override
    public boolean existsById(Long id);

    @Override
    public void deleteById(Long id);

    @Query(
            value = "select u.* from products u where (?1 is null or ?1 is not null and (u.description = ?1 or u.name = ?1)) and (?2 is null or ?2 is not null and u.price >= ?2) and (?3 is null or ?3 is not null and u.price <= ?3)",
            countQuery = "select count(1) from products u where (?1 is null or ?1 is not null and (u.description = ?1 or u.name = ?1)) and (?2 is null or ?2 is not null and u.price >= ?2) and (?3 is null or ?3 is not null and u.price <= ?3)",
            nativeQuery = true
    )
    public List<Product> findByQuery(String text, Double min, Double max);

}
