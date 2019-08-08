/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignments1.gui;

import com.monash.assignment.repository.UserJpaController;
import com.monash.assignment1.repository.entities.User;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lin
 */
public class RegisterGUI extends JFrame{
    private JFrame registerFrame;
    
    public RegisterGUI() throws Exception {
        initialize();
    }
    
    private void initialize() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentPU");
        UserJpaController userController = new UserJpaController(emf);
        
        Integer user_id = userController.getUserID()+1;
        Integer membership_level = 1;
        
        registerFrame = new JFrame();
        registerFrame.setBounds(100, 100, 667, 453);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.getContentPane().setLayout(null);
        
        Label label_1 = new Label("Email Address:");
        label_1.setAlignment(Label.LEFT);
        label_1.setBounds(30, 50, 120, 30);
        registerFrame.getContentPane().add(label_1);
        
        Label label_2 = new Label("Password:");
        label_2.setAlignment(Label.LEFT);
        label_2.setBounds(30, 80, 120, 30);
        registerFrame.getContentPane().add(label_2);
        
        Label label_3 = new Label("UserID:");
        label_3.setAlignment(Label.LEFT);
        label_3.setBounds(30, 110, 120, 30);
        registerFrame.getContentPane().add(label_3);
        
        Label label_4 = new Label("Membership Level:");
        label_4.setAlignment(Label.LEFT);
        label_4.setBounds(30, 140, 120, 30);
        registerFrame.getContentPane().add(label_4);
        
        Label label_5 = new Label("illegal email address!");
        label_5.setAlignment(Label.LEFT);
       
        Label label_6 = new Label("illegal password!");
        label_6.setAlignment(Label.LEFT);
        
        Label label_7 = new Label("email_address has been registed!");
        label_7.setAlignment(Label.LEFT);
        
        
        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(150, 55, 300, 20);
        registerFrame.getContentPane().add(emailTextField);

        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(150, 85, 300, 20);
        registerFrame.getContentPane().add(passwordTextField);
        
        Label userIDLabel = new Label(user_id.toString());
        userIDLabel.setBounds(150, 115, 300, 20);
        registerFrame.getContentPane().add(userIDLabel);
        
        Label membershipLevelLabel = new Label(membership_level.toString());
        membershipLevelLabel.setBounds(150, 145, 300, 20);
        registerFrame.getContentPane().add(membershipLevelLabel);
        
        
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 255, 255));
        registerButton.setBounds(125, 221, 100, 23);
        registerFrame.getContentPane().add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email_address = emailTextField.getText();
                    String password = passwordTextField.getText();
                    
                    //judge the requirements
                    //email_address
                    if(userController.findByEmail(email_address) != null) {
                        label_7.setBounds(460, 55, 200, 20);
                        label_7.setForeground(Color.red);
                        registerFrame.getContentPane().add(label_7);
                    } 
                    if(userController.isEmail(email_address) == false) {
                        label_5.setBounds(460, 55, 150, 20);
                        label_5.setForeground(Color.red);
                        registerFrame.getContentPane().add(label_5);
                    }
                    // password
                    if(userController.isPassword(password) == false) {
                        label_6.setBounds(460,80, 150, 20);
                        label_6.setForeground(Color.red);
                        registerFrame.getContentPane().add(label_6);
                    }
                    if(userController.findByEmail(email_address) == null && userController.isEmail(email_address) == true && UserJpaController.isPassword(password) == true) {
                        User user = new User(email_address, password, user_id, membership_level);
                        userController.register(user);
                        JOptionPane.showMessageDialog(null, "register succeed!", "message", JOptionPane.PLAIN_MESSAGE);
                        new LoginGUI();
                        registerFrame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "register failed!", "message", JOptionPane.PLAIN_MESSAGE);
                        emailTextField.setText("");
                        passwordTextField.setText("");    
                        new RegisterGUI();
                        registerFrame.dispose();
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 255, 255));
        backButton.setBounds(250, 221, 100, 23);
        registerFrame.getContentPane().add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoginGUI();
                    registerFrame.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        registerFrame.setVisible(true);

    }
}
