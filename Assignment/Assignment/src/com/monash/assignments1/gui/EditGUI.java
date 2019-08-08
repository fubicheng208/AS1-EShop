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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lin
 */
public class EditGUI extends JFrame{
    private JFrame editFrame;
    private String email_address;
    public EditGUI(String email_address) throws Exception {
        this.email_address = email_address;
        initialize();
    }
    
    private void initialize() {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentPU");
        UserJpaController userController = new UserJpaController(emf);
        
        Integer user_id = userController.getUserID()+1;
        Integer membership_level = 1;
        
        editFrame = new JFrame();
        editFrame.setBounds(100, 100, 667, 453);
        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.getContentPane().setLayout(null);
        
        Label label_1 = new Label("Email Address:");
        label_1.setAlignment(Label.LEFT);
        label_1.setBounds(30, 50, 120, 30);
        editFrame.getContentPane().add(label_1);
        
        Label label_2 = new Label("Password:");
        label_2.setAlignment(Label.LEFT);
        label_2.setBounds(30, 80, 120, 30);
        editFrame.getContentPane().add(label_2);
        
        Label label_3 = new Label("UserID:");
        label_3.setAlignment(Label.LEFT);
        label_3.setBounds(30, 110, 120, 30);
        editFrame.getContentPane().add(label_3);
        
        Label label_4 = new Label("Membership Level:");
        label_4.setAlignment(Label.LEFT);
        label_4.setBounds(30, 140, 120, 30);
        editFrame.getContentPane().add(label_4);
        
        Label label_5 = new Label("illegal email address!");
        label_5.setAlignment(Label.LEFT);
       
        Label label_6 = new Label("illegal password!");
        label_6.setAlignment(Label.LEFT);
        
        Label label_7 = new Label("email_address has been registed!");
        label_7.setAlignment(Label.LEFT);
        
        
        Label emailLabel = new Label(email_address);
        emailLabel.setBounds(150, 55, 300, 20);
        editFrame.getContentPane().add(emailLabel);

        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(150, 85, 300, 20);
        editFrame.getContentPane().add(passwordTextField);
        
        Label userIDLabel = new Label(user_id.toString());
        userIDLabel.setBounds(150, 115, 300, 20);
        editFrame.getContentPane().add(userIDLabel);
        
        Label membershipLevelLabel = new Label(membership_level.toString());
        membershipLevelLabel.setBounds(150, 145, 300, 20);
        editFrame.getContentPane().add(membershipLevelLabel);
        
        
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(255, 255, 255));
        submitButton.setBounds(125, 221, 100, 23);
        editFrame.getContentPane().add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //judge the requirements
                    // password
                    String password = passwordTextField.getText();
                    if(UserJpaController.isPassword(password) == false) {
                        label_6.setBounds(460,80, 150, 20);
                        label_6.setForeground(Color.red);
                        editFrame.getContentPane().add(label_6);
                        JOptionPane.showMessageDialog(null, "edit failed!", "message", JOptionPane.PLAIN_MESSAGE);
                        passwordTextField.setText("");
                        new EditGUI(email_address);
                        editFrame.dispose();
                    }
                    else {
                        User user = new User(email_address, password, user_id, membership_level);
                        userController.update(user);
                        JOptionPane.showMessageDialog(null, "edit succeed!", "message", JOptionPane.PLAIN_MESSAGE);
                        new UserGUI(email_address);
                        editFrame.dispose();
                    } 
                } catch (Exception ex) {
                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        editFrame.setVisible(true);
    }
}
