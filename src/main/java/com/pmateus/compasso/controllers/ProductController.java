/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmateus.compasso.controllers;

import com.pmateus.compasso.domains.Product;
import com.pmateus.compasso.interfaces.ProductDTO;
import com.pmateus.compasso.repositories.ProductRepository;
import com.pmateus.compasso.util.ErrorMessage;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Paulo Mateus
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("")
    public ResponseEntity createProduct(@RequestBody ProductDTO item) {
        if (item == null || item.isValid()) {
            return new ResponseEntity<>(ErrorMessage.notValidData(), HttpStatus.BAD_REQUEST);
        }

        Product product = Product.parse(item);
        product = productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity getAllProducs() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable(required = true) Long id) {
        if (productRepository.existsById(id)) {
            return new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductDTO item, @PathVariable(required = true) Long id) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (item == null || item.isValid()) {
            return new ResponseEntity<>(ErrorMessage.notValidData(), HttpStatus.BAD_REQUEST);
        }

        Product product = Product.parse(item);
        product.setId(id);

        product = productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    private ResponseEntity getItensByQuery(
            @RequestParam(name = "q", required = false) String text,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice
    ) {
        List<Product> products = productRepository.findByQuery(text, minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
