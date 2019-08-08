/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignment.bean;

import com.monash.assignment1.repository.entities.ProductsServices;
import java.util.List;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.taglibs.standard.Version;

/**
 *
 * @author fubic
 */
@Named(value = "detailController")
@RequestScoped
public class DetailController {
    private int productId;
    private ProductsServices product;
    
    /**
     * Creates a new instance of DetailController
     */
    public DetailController() {
        productId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId"));
        product = getProduct();        
    }
    
     public ProductsServices getProduct() {
        if (product == null) {	
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            ShopBean app
                    = (ShopBean) FacesContext.getCurrentInstance().getApplication()
                            .getELResolver().getValue(context, null, "shopBean");
            List<ProductsServices> products = app.getProducts();
            for(int i=0; i<products.size(); i++){
                ProductsServices tmp = products.get(i);
                if(tmp.getProductId()==productId)
                    return tmp;
            }
            
        }
        return product;
    }
    
}
