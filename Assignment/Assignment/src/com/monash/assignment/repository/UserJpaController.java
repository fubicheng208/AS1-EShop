/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignment.repository;

import com.monash.assignment.repository.exceptions.NonexistentEntityException;
import com.monash.assignment.repository.exceptions.PreexistingEntityException;
import com.monash.assignment1.repository.entities.User;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lin
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void register(User user) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findByEmail(user.getEmailAddress()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void update(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user = em.merge(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = user.getEmailAddress();
                if (findByEmail(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void delete(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getEmailAddress();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

//    public List<User> findUserEntities() {
//        return findUserEntities(true, -1, -1);
//    }

//    public List<User> findUserEntities(int maxResults, int firstResult) {
//        return findUserEntities(false, maxResults, firstResult);
//    }

//    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(User.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }

    public User findByEmail(String email_address) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, email_address);
        } finally {
            em.close();
        }
    }

    public Integer getUserID() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public static boolean isEmail(String email_address)
    {
        String regex = "^[A-Za-z]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
        return email_address.matches(regex);
    }
   
    
    public static boolean isPassword(String password)
    {
        boolean hasLowerLetter = false;
        boolean hasUpperLetter = false;
        boolean hasNumLetter = false;
        boolean hasSpecialLetter = false;
        
        if(password == null)
            return false;
        
        if(password.length() <8 || password.length() > 20)
            return false;
        
        char[] p = password.toCharArray();
        for(int i = 0; i < password.length(); i++){
            if(p[i] >=48 && p[i] <= 57)
                hasNumLetter = true;
            else if(p[i] >= 65 && p[i] <= 90)
                hasUpperLetter = true;
            else if(p[i] >= 97 && p[i] <= 122)
                hasLowerLetter = true;
            else
                hasSpecialLetter = true;
        }
        
        if(hasLowerLetter && hasUpperLetter && hasNumLetter && hasSpecialLetter)
            return true;
        else 
            return false;
    }
    
    
}
