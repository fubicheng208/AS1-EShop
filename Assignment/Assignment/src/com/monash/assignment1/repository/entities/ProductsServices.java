/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignment1.repository.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author Lin
 */
@Entity
@Table(name = "PRODUCTS_SERVICES_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductsServices.findAll", query = "SELECT p FROM ProductsServices p")
    , @NamedQuery(name = "ProductsServices.findByProductId", query = "SELECT p FROM ProductsServices p WHERE p.productId = :productId")
    , @NamedQuery(name = "ProductsServices.findByTitle", query = "SELECT p FROM ProductsServices p WHERE p.title = :title")
    
    , @NamedQuery(name = "ProductsServices.findByLabel", query = "SELECT p FROM ProductsServices p WHERE p.label = :label")
    
    , @NamedQuery(name = "ProductsServices.findByPrice", query = "SELECT p FROM ProductsServices p WHERE p.price >= :min AND p.price <= :max")
   })
public class ProductsServices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
 
    @Basic(optional = false)
    @Column(name = "LABEL")
    private String label;
   
    @Basic(optional = false)
    @Column(name = "PRICE")
    private double price;
    
    @Basic(optional = false)
    @Column(name = "DETAIL")
    private String detail;
    
    @Basic(optional = false)
    @Column(name = "HASSALED")
    private int hasSaled;
    
    @Basic(optional = false)
    @Column(name = "AVERAGEMARK")
    private double averageMark;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public ProductsServices() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductsServices(Integer productId) {
        this.productId = productId;
    }

    public ProductsServices(Integer productId, String title, String label, double price) {
        this.productId = productId;
        this.title = title;
        this.label = label;
        this.price = price;
    }
    
    public ProductsServices(Integer productId, String title, String label, double price, String detail) {
        this.productId = productId;
        this.title = title;
        this.label = label;
        this.price = price;
        this.detail = detail;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductsServices)) {
            return false;
        }
        ProductsServices other = (ProductsServices) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monash.assignment1.repository.entities.ProductsServices[ productId=" + productId + " ]";
    }
    
}
