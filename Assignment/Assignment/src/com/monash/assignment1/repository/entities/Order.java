/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignment1.repository.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fubicheng
 */
@Entity
@Table(name= "ORDERS_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
    ,@NamedQuery(name="Order.findByUserId", query="SELECT o FROM Order o WHERE o.userId = :userId" )
})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    private Date time;
    
    @Column(name="QUANTITY")
    @Basic(optional = false)
    private int quantity;
    
    @Column(name = "MARK")
    @Basic(optional = false)
    private double mark;
    
    @JoinColumn(name = "EMAIL_ADDRESS", referencedColumnName = "EMAIL_ADDRESS")
    @ManyToOne
    private User userId;
    
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne
    private ProductsServices productId;
    
    public Order(){
    }

    public Order(Date time, int quantity, User userId, ProductsServices productId) {
        this.time = time;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public ProductsServices getProductId() {
        return productId;
    }

    public void setProductId(ProductsServices productId) {
        this.productId = productId;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
     public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monash.assignment1.repository.entities.Order[ id=" + id + " ]";
    }
    
}
