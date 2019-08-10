/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignments1.gui;

import com.monash.assignment.repository.UserJpaController;
import com.monash.assignment1.repository.entities.User;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Lin
 */
public class UserGUI extends JFrame{
    private JFrame userFrame;
    private String email_address;
    
    public UserGUI(String email_address) throws Exception {
        this.email_address = email_address;
        initialize();
        
    }
    
    private void initialize() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentPU");
        UserJpaController userController  = new UserJpaController(emf);
        
        userFrame = new JFrame();
        userFrame.setBounds(100, 100, 667, 453);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.getContentPane().setLayout(null);
        
        Label label_1 = new Label("Email Address:");
        label_1.setAlignment(Label.LEFT);
        label_1.setBounds(30, 50, 100, 30);
        userFrame.getContentPane().add(label_1);
        
        Label label_2 = new Label("Password:");
        label_2.setAlignment(Label.LEFT);
        label_2.setBounds(30, 80, 100, 30);
        userFrame.getContentPane().add(label_2);
        
        Label label_3 = new Label("UserID:");
        label_3.setAlignment(Label.LEFT);
        label_3.setBounds(30, 110, 100, 30);
        userFrame.getContentPane().add(label_3);
        
        Label label_4 = new Label("Membership Level:");
        label_4.setAlignment(Label.LEFT);
        label_4.setBounds(30, 140, 100, 30);
        userFrame.getContentPane().add(label_4);
        
        User user = userController.findByEmail(email_address);
        String password = user.getPassword();
        Integer user_id = user.getUserId();
        Integer membership_level = user.getMembershipLevel();
        
        Label emailLabel = new Label(email_address);
        emailLabel.setBounds(150, 55, 200, 20);
        userFrame.getContentPane().add(emailLabel);

        Label passwordLabel = new Label(password);
        passwordLabel.setBounds(150, 85, 200, 20);
        userFrame.getContentPane().add(passwordLabel);
        
        Label userIDLabel = new Label(user_id.toString());
        userIDLabel.setBounds(150, 115, 200, 20);
        userFrame.getContentPane().add(userIDLabel);
        
        Label membershipLevelLabel = new Label(membership_level.toString());
        membershipLevelLabel.setBounds(150, 145, 200, 20);
        userFrame.getContentPane().add(membershipLevelLabel);
        
        JButton eidtButton = new JButton("Edit");
        eidtButton.setBackground(new Color(255, 255, 255));
        eidtButton.setBounds(70, 221, 80, 23);
        userFrame.getContentPane().add(eidtButton);
        eidtButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditGUI(email_address);
                    userFrame.dispose();
                    
                } catch (Exception ex) {
                    Logger.getLogger(UserGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        JButton searchButton = new JButton("search");
        searchButton.setBackground(new Color(255, 255, 255));
        searchButton.setBounds(240, 221, 80, 23);
        userFrame.getContentPane().add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //add functions in ProductsServiceJpaController.java
                
            } 
        });
        
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 255, 255));
        backButton.setBounds(350, 221, 80, 23);
        userFrame.getContentPane().add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoginGUI();
                    userFrame.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(UserGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton shopButton = new JButton("Shop");
        shopButton.setBackground(new Color(255, 255, 255));
        shopButton.setBounds(460, 221, 80, 23);
        userFrame.getContentPane().add(shopButton);
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EShop(email_address);
                    userFrame.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(UserGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton ordersButton = new JButton("Orders");
        shopButton.setBackground(new Color(255, 255, 255));
        shopButton.setBounds(460, 221, 80, 23);
        userFrame.getContentPane().add(shopButton);
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EShop(email_address);
                    userFrame.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(UserGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        userFrame.setVisible(true);
    }
}
