/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignment.repository;

import com.monash.assignment.repository.exceptions.NonexistentEntityException;
import com.monash.assignment.repository.exceptions.PreexistingEntityException;
import com.monash.assignment1.repository.entities.ProductsServices;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lin
 */
public class ProductsServicesJpaController implements Serializable {

    public ProductsServicesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductsServices productsServices) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productsServices);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductsServices(productsServices.getProductId()) != null) {
                throw new PreexistingEntityException("ProductsServices " + productsServices + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductsServices productsServices) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productsServices = em.merge(productsServices);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productsServices.getProductId();
                if (findProductsServices(id) == null) {
                    throw new NonexistentEntityException("The productsServices with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductsServices productsServices;
            try {
                productsServices = em.getReference(ProductsServices.class, id);
                productsServices.getProductId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productsServices with id " + id + " no longer exists.", enfe);
            }
            em.remove(productsServices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<ProductsServices> findById(Integer id){
        EntityManager em = null;
        List<ProductsServices> res = null;
        try{
            em = getEntityManager();
            Query query = em.createNamedQuery("ProductsServices.findByProductId");
            query.setParameter("productId", id);
            res = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (em != null) {
                em.close();
            }
         }
        return res;
    }
    
    public List<ProductsServices> findAll(){
        EntityManager em = null;
        List<ProductsServices> res = null;
        try{
            em = getEntityManager();
            Query query = em.createNamedQuery("ProductsServices.findAll");
            res = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (em != null) {
                em.close();
            }
         }
        return res;
    }
    
    public List<ProductsServices> findByTitle(String title){
        EntityManager em = null;
        List<ProductsServices> res = null;
        try{
            em = getEntityManager();
            Query query = em.createNamedQuery("ProductsServices.findByTitle");
            query.setParameter("title", title);
            res = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (em != null) {
                em.close();
            }
         }
        return res;
    }
    
    public List<ProductsServices> findByLabel(String label){
        EntityManager em = null;
        List<ProductsServices> res = null;
        try{
            em = getEntityManager();
            Query query = em.createNamedQuery("ProductsServices.findByLabel");
            query.setParameter("label", label);
            res = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (em != null) {
                em.close();
            }
         }
        return res;
    }
    
    public List<ProductsServices> findByPrice(double min, double max){
        EntityManager em = null;
        List<ProductsServices> res = null;
        try{
            em = getEntityManager();
            Query query = em.createNamedQuery("ProductsServices.findByPrice");
            query.setParameter("min", min);
            query.setParameter("max", max);
            res = query.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (em != null) {
                em.close();
            }
         }
        return res;
    }

    public List<ProductsServices> findProductsServicesEntities() {
        return findProductsServicesEntities(true, -1, -1);
    }

    public List<ProductsServices> findProductsServicesEntities(int maxResults, int firstResult) {
        return findProductsServicesEntities(false, maxResults, firstResult);
    }

    private List<ProductsServices> findProductsServicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductsServices.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductsServices findProductsServices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductsServices.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductsServicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductsServices> rt = cq.from(ProductsServices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
