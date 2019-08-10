/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monash.assignments1.gui;

import com.monash.assignment.repository.OrderJpaController;
import com.monash.assignment.repository.ProductsServicesJpaController;
import com.monash.assignment.repository.UserJpaController;
import com.monash.assignment1.repository.entities.Order;
import com.monash.assignment1.repository.entities.ProductsServices;
import com.monash.assignment1.repository.entities.User;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sound.midi.SysexMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author fubic
 */
public class EShop extends JFrame implements ActionListener, ListSelectionListener {

    private static final String[] TABLE_COLUMNS = {"ID", "Title", "Label", "Price"};
    private static final String TAG_SEPARATOR = ",";
    private String username;

    EntityManagerFactory emf;
    ProductsServicesJpaController productController;
    OrderJpaController orderController;
    UserJpaController userController;

    private JPanel inputPanel;
    private JPanel decisionPanel;
    private JPanel detailPanel;

    //
    private JLabel idLabel;
    private JLabel titleLabel;
    private JLabel labelLabel;
    private JLabel priceLabel;
   

    private JButton searchButton;
    private JButton buyButton;
    private JButton backButton;
    

    private JComboBox searchLabelComboBox;
    //private JTextField searchIdField;
    //private final JTextField searchLabelField;
    private JTextField searchMinField;
    private JTextField searchMaxField;
    private JTextField showIdField;
    private JTextField showTitleField;
    private JTextField showLabelField;
    //private JTextField showDetailField;
    private JTextField showPriceField;
    private JTextField quantityField;
    
    
    private JTextArea showDetailFiledArea;
    private JTable listTable;
    //
    private final JPanel infoPanal;
    private final JLabel quantityLabel;

    public EShop(String username) {
        
        this.username = username;
        emf = Persistence.createEntityManagerFactory("AssignmentPU");
        productController = new ProductsServicesJpaController(emf);
        orderController = new OrderJpaController(emf);
        userController = new UserJpaController(emf);

        this.searchButton = new JButton("Search");
        this.buyButton = new JButton("   Buy It Now   ");
        this.backButton = new JButton("Back");
        
        

        // create container
        Container container = this.getContentPane();

        //my
        this.idLabel = new JLabel("ID");
        this.titleLabel = new JLabel("Title");
        this.labelLabel = new JLabel("Label");
        this.priceLabel = new JLabel("Price");
        this.quantityLabel = new JLabel("Quantity");

        //my
        searchLabelComboBox = new JComboBox();
        searchLabelComboBox.addItem("Tasteless");
        searchLabelComboBox.addItem("Sweet");
        searchLabelComboBox.addItem("Salty");
        searchLabelComboBox.addItem("Soul");
        searchLabelComboBox.addItem("Bitter");

        //this.searchIdField  = new JTextField();
        //searchIdField.setText("ID");
        //private final JTextField searchLabelField;
        searchMinField = new JTextField();
        searchMinField.setText("Min");

        searchMaxField = new JTextField();
        searchMaxField.setText("Max");
        
        showIdField = new JTextField();

        showTitleField = new JTextField();

        showLabelField = new JTextField();

        //showDetailField  = new JTextField();
        showPriceField = new JTextField();
        
        quantityField = new JTextField();
        
        showDetailFiledArea = new JTextArea();
        showDetailFiledArea.setColumns(20);
        showDetailFiledArea.setRows(4);
       
        
        //my
        this.listTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));

        this.listTable.getSelectionModel().addListSelectionListener(this);
        this.listTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel propertyTableColumnModel = this.listTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(200);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(200);
        propertyTableColumnModel.getColumn(3).setPreferredWidth(200);

        // create panels
        this.inputPanel = new JPanel();

        //this.buttonPanel = new JPanel();
        this.detailPanel = new JPanel();
        
        this.infoPanal = new JPanel();
        
        this.decisionPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(1, 5));
        this.infoPanal.setLayout(new GridLayout(2, 5));
        this.detailPanel.setLayout(new BorderLayout());
        this.decisionPanel.setLayout(new GridLayout(1,3));
        
        

        searchButton.addActionListener(this);
        backButton.addActionListener(this);
        buyButton.addActionListener(this);

        //inputPanel.add(searchIdField);
        inputPanel.add(searchLabelComboBox);
        inputPanel.add(searchMinField);
        inputPanel.add(searchMaxField);
        inputPanel.add(searchButton);
        inputPanel.add(backButton);
        
        infoPanal.add(idLabel);
        infoPanal.add(showIdField);
        infoPanal.add(titleLabel);
        infoPanal.add(showTitleField);
        infoPanal.add(labelLabel);
        infoPanal.add(showLabelField);
        infoPanal.add(priceLabel);
        infoPanal.add(showPriceField);
        
        decisionPanel.add(quantityLabel);
        decisionPanel.add(quantityField);
        decisionPanel.add(buyButton);
        
        detailPanel.add(infoPanal,BorderLayout.NORTH );
        detailPanel.add(showDetailFiledArea, BorderLayout.SOUTH);
        detailPanel.add(decisionPanel, BorderLayout.SOUTH);
        
        // add panels to content pane
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(new JScrollPane(this.listTable), BorderLayout.CENTER);
        container.add(detailPanel, BorderLayout.SOUTH);
       
        

        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(750, 650);
        this.setVisible(true);
        List<ProductsServices> res = productController.findAll();
        displayProducts(res);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            //displayMessageInDialog("点击了search");
            search();
        }else if(e.getSource() == backButton){
            //haven't logged in
            if(username.equals("")){
                try {
                    new LoginGUI();
                    this.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(UserGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    new UserGUI(username);
                    this.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(UserGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(e.getSource() == buyButton){
            if(placeOrder()){
                displayMessageInDialog("You have bought it successfully");
            }else{
                displayMessageInDialog("Failed");
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if ((e.getSource() == listTable.getSelectionModel())
                && (!e.getValueIsAdjusting())) {
            try {
                if (this.isProductSelected()) {
                    int productId = this.getSelectedProductId();
                    ProductsServices p = productController.findProductsServices(productId);
                    displayProductDetails(p);
                }
            } catch (Exception ex) {
                displayMessageInDialog(ex.getMessage());
            }
        }
    }
    
    public boolean placeOrder() {
        boolean res = false;
        if(username.equals("")){
            displayMessageInDialog("You haven't logged in");
            return res;
        }else{
            try{
                int productId = this.getSelectedProductId();
                ProductsServices product = productController.findProductsServices(productId);
                User user = userController.findByEmail(username);
                Order order = new Order(new Date(), Integer.parseInt(quantityField.getText()), user, product);
                orderController.create(order);
                res = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return res;
    }

    public boolean isProductSelected() {
        return (this.listTable.getSelectedRow() >= 0);
    }

    public int getSelectedProductId() throws Exception {
        int rowIndex = this.listTable.getSelectedRow();

        String productId = this.listTable.getValueAt(rowIndex, 0).toString();
        return Integer.parseInt(productId);
    }

    public void displayProductDetails(ProductsServices p) {
        this.showIdField.setText(p.getProductId() + "");
        this.showTitleField.setText(p.getTitle() + "");
        this.showLabelField.setText(p.getLabel() + "");
        this.showPriceField.setText(p.getPrice() + "");
        this.showDetailFiledArea.setText(p.getDetail());
    }

    public void search() {
        List<ProductsServices> res = null;

        String labelTmp = (String) searchLabelComboBox.getSelectedItem();
        if (labelTmp != "") {
            res = productController.findByLabel(labelTmp);
            displayProducts(res);
        } else if (searchMinField.getText() != "" &&  searchMaxField.getText() != "") {
            double minTmp = Double.parseDouble(searchMinField.getText());
            System.out.println("min=" + minTmp + "");
            double maxTmp = Double.parseDouble(searchMaxField.getText());
            res = productController.findByPrice(minTmp, maxTmp);
            System.out.println("max=" + maxTmp + "");
            displayProducts(res);
        } else{
            res = productController.findAll();
            displayProducts(res);
        }
    }

    public void displayProducts(List<ProductsServices> products) {
        this.clearListTable();
        this.clearInput();

        for (ProductsServices product : products) {
            ((DefaultTableModel) this.listTable.getModel()).addRow(new Object[]{product.getProductId(),
                product.getTitle(),
                product.getLabel(),
                product.getPrice()});
        }
    }

    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearListTable() {
        int numberOfRow = this.listTable.getModel().getRowCount();

        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.listTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index--) {
                tableModel.removeRow(index);
            }
        }
    }

    public void clearInput() {
        this.clearTextFields();
        this.clearComboBoxes();
    }

    public void clearTextFields() {
        searchMaxField.setText("");
        searchMinField.setText("");
        showIdField.setText("");
        showLabelField.setText("");
        showTitleField.setText("");
        showPriceField.setText("");
    }

    public void clearComboBoxes() {
        if (this.searchLabelComboBox.getItemCount() > 0) {
            this.searchLabelComboBox.setSelectedIndex(0);
        }
    }
}
