package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderPanel extends JPanel {
    private JPanel rightPanel;
    private JPanel leftPanel;
    private DefaultTableModel model;
    private JTextField amount;
    private JComboBox<String> clientsId;
    private JComboBox<String> productsId;
    private JButton placeOrderButton;

    public OrderPanel() {
        createRightPanel();
        createLeftPanel();

        add(leftPanel);
        add(rightPanel);
    }

    //util methods
    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 3, 20, 20));

        clientsId = new JComboBox<>();
        productsId = new JComboBox<>();
        placeOrderButton = new JButton("Place Order!");
        amount = new JTextField(3);

        JPanel amountPanel = new JPanel();
        JLabel amountLabel = new JLabel("Amount:");
        amountPanel.add(amountLabel);
        amountPanel.add(amount);

        JPanel clientsIdPanel = new JPanel();
        JLabel clientsIdLabel = new JLabel("Clients:");
        clientsIdPanel.add(clientsIdLabel);
        clientsIdPanel.add(clientsId);

        JPanel productsIdPanel = new JPanel();
        JLabel productsIdLabel = new JLabel("Products:");
        productsIdPanel.add(productsIdLabel);
        productsIdPanel.add(productsId);

        rightPanel.add(clientsIdPanel);
        rightPanel.add(productsIdPanel);
        rightPanel.add(amountPanel);
        rightPanel.add(new JLabel());
        rightPanel.add(placeOrderButton);
        rightPanel.add(new JLabel());
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();

        JTable orderTable = new JTable();

        model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        orderTable.setModel(model);
        leftPanel.add(new JScrollPane(orderTable));
    }

    public void showErrorDialog() {
        JOptionPane.showMessageDialog(new JButton("OK!"), "Under-Stock! Please insert a lower amount!");
    }

    //setters & getters
    public void setHeader(String[] columns) {
        model.setColumnIdentifiers(columns);
    }

    public void setRows(List<String[]> rows) {
        for (String[] row : rows) {
            model.addRow(row);
        }
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public String getAmount() {
        return amount.getText();
    }

    public void setClientsId(List<String> ids) {
        clientsId.removeAllItems();
        for (String id : ids) {
            clientsId.addItem(id);
        }
    }

    public String getClientId() {
        return (String) clientsId.getSelectedItem();
    }

    public void setProductsId(List<String> ids) {
        productsId.removeAllItems();
        for (String id : ids) {
            productsId.addItem(id);
        }
    }

    public String getProductId() {
        return (String) productsId.getSelectedItem();
    }

    public void clearFields() {
        amount.setText("");
    }

    //listeners
    public void addPlaceOrderButtonListener(ActionListener a) {
        placeOrderButton.addActionListener(a);
    }
}
