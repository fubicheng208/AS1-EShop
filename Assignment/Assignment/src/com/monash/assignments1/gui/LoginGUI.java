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
public class LoginGUI extends JFrame {

    private JFrame loginFrame;
    private boolean isLogin = false;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGUI window = new LoginGUI();
                    window.loginFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginGUI() throws Exception {
        initialize();
    }

    private void initialize() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentPU");
        UserJpaController userController = new UserJpaController(emf);

        loginFrame = new JFrame();
        loginFrame.setBounds(100, 100, 667, 453);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setLayout(null);

        Label label_1 = new Label("Email:");
        label_1.setAlignment(Label.LEFT);
        label_1.setBounds(116, 49, 70, 23);
        loginFrame.getContentPane().add(label_1);

        Label label_2 = new Label("Password");
        label_2.setAlignment(Label.LEFT);
        label_2.setBounds(116, 85, 70, 23);
        loginFrame.getContentPane().add(label_2);

        Label label_3 = new Label("status");
        label_3.setBounds(433, 49, 60, 23);
        loginFrame.getContentPane().add(label_3);

        Label label_4 = new Label("not logined");
        label_4.setForeground(new Color(255, 0, 0));
        label_4.setBounds(499, 49, 70, 23);
        loginFrame.getContentPane().add(label_4);

        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(200, 49, 200, 23);
        loginFrame.getContentPane().add(emailTextField);

        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(200, 85, 200, 23);
        loginFrame.getContentPane().add(passwordTextField);

        JButton loginButton = new JButton("login");
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setBounds(125, 221, 80, 23);
        loginFrame.getContentPane().add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String getEmailAddress = emailTextField.getText();
                String getPassword = passwordTextField.getText();
                User user = userController.findByEmail(getEmailAddress);
                if(userController.findByEmail(getEmailAddress) == null) {
                    isLogin = false;
                }
                
                if (getPassword.equals(user.getPassword())) {
                    isLogin = true;
                } else {
                    isLogin = false;
                }
                if (isLogin) {
                    JOptionPane.showMessageDialog(null, "login succeed!", "message", JOptionPane.PLAIN_MESSAGE);
                    label_4.setText("logined");
                    label_4.setForeground(Color.BLUE);
                    try {
                        //turn page
                        new UserGUI(user.getEmailAddress());
                        loginFrame.dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "login failed", "message", JOptionPane.PLAIN_MESSAGE);
                    label_4.setText("not logined");
                    label_4.setForeground(Color.RED);
                }
            }
        });
        
        JButton registerButton = new JButton("register");
        registerButton.setBackground(new Color(255, 255, 255));
        registerButton.setBounds(260, 221, 80, 23);
        loginFrame.getContentPane().add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new RegisterGUI();
                    loginFrame.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton shopButton = new JButton("shop");
        shopButton.setBackground(new Color(255, 255, 255));
        shopButton.setBounds(400, 221, 80, 23);
        loginFrame.getContentPane().add(shopButton);
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    new EShop("");
                    loginFrame.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        loginFrame.setVisible(true);
    }

}
