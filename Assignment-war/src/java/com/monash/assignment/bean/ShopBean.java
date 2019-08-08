/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignment.bean;

import com.monash.assignment.repository.ProductsServicesJpaController;
import com.monash.assignment1.repository.entities.ProductsServices;
import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fubic
 */
@Named(value = "shopBean")
@ConversationScoped
public class ShopBean implements Serializable{

    private ProductsServicesJpaController productsController;
    private List<ProductsServices> products;
    EntityManagerFactory emf;
    private String label;
    double min;
    double max;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    

    /**
     * Creates a new instance of IndexBean
     */
    public ShopBean() {
        emf = Persistence.createEntityManagerFactory("AssignmentPU");
        productsController = new ProductsServicesJpaController(emf);
        products = getProducts();
        System.out.println("init:" + products.toString());
        label = "";
        min = 0;
        max = 0;
    }

    public List<ProductsServices> getProducts() {
        products = productsController.findAll();
        return products;
    }
    
    public String getProductsToString() {
        
        return products.toString();
    }

    public List<ProductsServices> searchProducts() { 
       
        products = null;
        if (!label.equals("")) {
            this.products = productsController.findByLabel(label);
            System.out.println("label: " + products.toString());
        } else if (min > 0 && max >  0 && min<max){
            this.products = productsController.findByPrice(min, max);
            System.out.println("price:" + products.toString());
        } else {
            this.products = productsController.findAll();
            System.out.println("all:" +  products.toString());
        }
        return products;
    }
}
