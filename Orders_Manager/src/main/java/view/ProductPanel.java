package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ProductPanel extends JPanel {
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel createPanel;
    private JPanel editPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JTable productTable;
    private DefaultTableModel model;
    private JTextField productNameCreate;
    private JTextField amountCreate;
    private JTextField priceCreate;
    private JTextField manufacturerCreate;
    private JTextField idEdit;
    private JTextField productNameEdit;
    private JTextField amountEdit;
    private JTextField priceEdit;
    private JTextField manufacturerEdit;
    private JButton createPanelButton;
    private JButton editPanelButton;
    private JButton createProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;

    public ProductPanel(){
        createRightPanel();
        createLeftPanel();

        add(leftPanel);
        add(rightPanel);
    }

    //util methods
    private void createRightPanel(){
        rightPanel = new JPanel();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();

        rightPanel.setLayout(new BorderLayout(30, 30));
        contentPanel.setLayout(cardLayout);

        createTopPanel();
        createCreatePanel();
        createEditPanel();

        contentPanel.add(createPanel, "0");
        contentPanel.add(editPanel, "1");

        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(contentPanel);
    }

    private void createLeftPanel(){
        leftPanel = new JPanel();

        productTable = new JTable();

        productTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                String selectedId = (String) productTable.getValueAt(productTable.getSelectedRow() , 0);
                String selectedProductName = (String) productTable.getValueAt(productTable.getSelectedRow() , 1);
                String selectedAmount = (String) productTable.getValueAt(productTable.getSelectedRow() , 2);
                String selectedPrice = (String) productTable.getValueAt(productTable.getSelectedRow() , 3);
                String selectedManufacturer = (String) productTable.getValueAt(productTable.getSelectedRow() , 4);
                setIdEdit(selectedId);
                setProductNameEdit(selectedProductName);
                setAmountEdit(selectedAmount);
                setPriceEdit(selectedPrice);
                setManufacturerEdit(selectedManufacturer);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        productTable.setModel(model);
        leftPanel.add(new JScrollPane(productTable));
    }

    private void createCreatePanel(){
        createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(5, 2, 0, 15));

        createProductButton = new JButton("CREATE");
        createProductButton.setFocusable(false);

        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel manufacturerLabel = new JLabel("Manufacturer:");

        productNameCreate = new JTextField();
        amountCreate = new JTextField();
        priceCreate = new JTextField();
        manufacturerCreate = new JTextField();

        createPanel.add(productNameLabel);
        createPanel.add(productNameCreate);
        createPanel.add(amountLabel);
        createPanel.add(amountCreate);
        createPanel.add(priceLabel);
        createPanel.add(priceCreate);
        createPanel.add(manufacturerLabel);
        createPanel.add(manufacturerCreate);
        createPanel.add(new JLabel());
        createPanel.add(createProductButton);
    }

    private void createEditPanel(){
        editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(6, 2, 0, 15));

        editProductButton = new JButton("EDIT");
        editProductButton.setFocusable(false);
        deleteProductButton = new JButton("DELETE");
        deleteProductButton.setFocusable(false);

        JLabel idLabel = new JLabel("ID:");
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel manufacturerLabel = new JLabel("Manufacturer:");

        idEdit = new JTextField();
        idEdit.setEditable(false);
        productNameEdit = new JTextField();
        amountEdit = new JTextField();
        priceEdit = new JTextField();
        manufacturerEdit = new JTextField();

        editPanel.add(idLabel);
        editPanel.add(idEdit);
        editPanel.add(productNameLabel);
        editPanel.add(productNameEdit);
        editPanel.add(amountLabel);
        editPanel.add(amountEdit);
        editPanel.add(priceLabel);
        editPanel.add(priceEdit);
        editPanel.add(manufacturerLabel);
        editPanel.add(manufacturerEdit);
        editPanel.add(deleteProductButton);
        editPanel.add(editProductButton);
    }

    private void createTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4, 15, 15));

        createPanelButton = new JButton("Add Product");
        createPanelButton.setFocusable(false);
        editPanelButton = new JButton("Edit Product");
        editPanelButton.setFocusable(false);

        topPanel.add(createPanelButton);
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(editPanelButton);
    }

    public void setPanel(int flag){
        if(flag == 0)
            cardLayout.show(contentPanel, "0");
        else
            cardLayout.show(contentPanel, "1");
    }

    public void showErrorDialog(){
        JOptionPane.showMessageDialog(new JButton("OK!"), "Invalid Input!");
    }

    //setters & getters
    public void setHeader(String[] columns){
        model.setColumnIdentifiers(columns);
    }

    public void setRows(List<String[]> rows){
        for(String[] row : rows){
            model.addRow(row);
        }
    }

    public void clearTable(){
        model.setRowCount(0);
    }

    public String getProductNameCreate(){
        return productNameCreate.getText();
    }

    public String getAmountCreate(){
        return amountCreate.getText();
    }

    public String getPriceCreate(){
        return priceCreate.getText();
    }

    public String getManufacturerCreate(){
        return manufacturerCreate.getText();
    }

    public String getIdEdit(){
        return idEdit.getText();
    }

    public String getProductNameEdit(){
        return productNameEdit.getText();
    }

    public String getAmountEdit(){
        return amountEdit.getText();
    }

    public String getPriceEdit(){
        return priceEdit.getText();
    }

    public String getManufacturerEdit(){
        return manufacturerEdit.getText();
    }

    public void setIdEdit(String text){
        idEdit.setText(text);
    }

    public void setProductNameEdit(String text){
        productNameEdit.setText(text);
    }

    public void setAmountEdit(String text){
        amountEdit.setText(text);
    }

    public void setPriceEdit(String text){
        priceEdit.setText(text);
    }

    public void setManufacturerEdit(String text){
        manufacturerEdit.setText(text);
    }

    public void clearCreateFields(){
        productNameCreate.setText("");
        amountCreate.setText("");
        priceCreate.setText("");
        manufacturerCreate.setText("");
    }

    public void clearEditFields(){
        idEdit.setText("");
        productNameEdit.setText("");
        amountEdit.setText("");
        priceEdit.setText("");
        manufacturerEdit.setText("");
    }

    //listeners
    public void addCreatePanelButtonListener(ActionListener a){
        createPanelButton.addActionListener(a);
    }

    public void addEditPanelButtonListener(ActionListener a){
        editPanelButton.addActionListener(a);
    }

    public void addCreateProductButtonListener(ActionListener a){
        createProductButton.addActionListener(a);
    }

    public void addEditProductButtonListener(ActionListener a){
        editProductButton.addActionListener(a);
    }
    public void addDeleteProductButtonListener(ActionListener a){
        deleteProductButton.addActionListener(a);
    }
}
