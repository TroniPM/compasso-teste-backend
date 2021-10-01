/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmateus.compasso.interfaces;

/**
 *
 * @author Paulo Mateus
 */
public class ProductDTO {

    private String name;

    private String description;

    private Double price;

    public boolean isValid() {
        return this.name == null || this.name.isEmpty()
                || this.description == null || this.description.isEmpty()
                || this.price == null || this.price.longValue() < 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
