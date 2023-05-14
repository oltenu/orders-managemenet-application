package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ClientPanel extends JPanel {
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel editPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel createPanel;
    private CardLayout cardLayout;
    private JTable clientTable;
    private DefaultTableModel model;
    private JTextField firstNameCreate;
    private JTextField lastNameCreate;
    private JTextField ageCreate;
    private JTextField addressCreate;
    private JTextField cnpCreate;
    private JTextField idEdit;
    private JTextField firstNameEdit;
    private JTextField lastNameEdit;
    private JTextField ageEdit;
    private JTextField addressEdit;
    private JTextField cnpEdit;
    private JButton createPanelButton;
    private JButton editPanelButton;
    private JButton createClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;

    public ClientPanel(){
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

        clientTable = new JTable();

        clientTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                String selectedId = (String) clientTable.getValueAt(clientTable.getSelectedRow() , 0);
                String selectedFirstName = (String) clientTable.getValueAt(clientTable.getSelectedRow() , 1);
                String selectedLastName = (String) clientTable.getValueAt(clientTable.getSelectedRow() , 2);
                String selectedAge = (String) clientTable.getValueAt(clientTable.getSelectedRow() , 3);
                String selectedAddress = (String) clientTable.getValueAt(clientTable.getSelectedRow() , 4);
                String selectedCnp = (String) clientTable.getValueAt(clientTable.getSelectedRow() , 5);
                setIdEdit(selectedId);
                setFirstNameEdit(selectedFirstName);
                setLastNameEdit(selectedLastName);
                setAgeEdit(selectedAge);
                setAddressEdit(selectedAddress);
                setCnpEdit(selectedCnp);
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

        clientTable.setModel(model);
        leftPanel.add(new JScrollPane(clientTable));
    }

    private void createCreatePanel(){
        createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(6, 2, 0, 15));

        createClientButton = new JButton("CREATE");
        createClientButton.setFocusable(false);

        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel cnpLabel = new JLabel("CNP:");

        firstNameCreate = new JTextField();
        lastNameCreate = new JTextField();
        ageCreate = new JTextField();
        addressCreate = new JTextField();
        cnpCreate = new JTextField();

        createPanel.add(firstNameLabel);
        createPanel.add(firstNameCreate);
        createPanel.add(lastNameLabel);
        createPanel.add(lastNameCreate);

        createPanel.add(ageLabel);
        createPanel.add(ageCreate);
        createPanel.add(addressLabel);
        createPanel.add(addressCreate);
        createPanel.add(cnpLabel);
        createPanel.add(cnpCreate);
        createPanel.add(new JLabel());
        createPanel.add(createClientButton);
    }

    private void createEditPanel(){
        editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(7, 2, 0, 15));

        editClientButton = new JButton("EDIT");
        editClientButton.setFocusable(false);
        deleteClientButton = new JButton("DELETE");
        deleteClientButton.setFocusable(false);

        JLabel idLabel = new JLabel("ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel cnpLabel = new JLabel("CNP:");

        idEdit = new JTextField();
        idEdit.setEditable(false);
        firstNameEdit = new JTextField();
        lastNameEdit = new JTextField();
        ageEdit = new JTextField();
        addressEdit = new JTextField();
        cnpEdit = new JTextField();

        editPanel.add(idLabel);
        editPanel.add(idEdit);
        editPanel.add(firstNameLabel);
        editPanel.add(firstNameEdit);
        editPanel.add(lastNameLabel);
        editPanel.add(lastNameEdit);
        editPanel.add(ageLabel);
        editPanel.add(ageEdit);
        editPanel.add(addressLabel);
        editPanel.add(addressEdit);
        editPanel.add(cnpLabel);
        editPanel.add(cnpEdit);
        editPanel.add(deleteClientButton);
        editPanel.add(editClientButton);
    }

    private void createTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4, 15, 15));

        createPanelButton = new JButton("Add Client");
        createPanelButton.setFocusable(false);
        editPanelButton = new JButton("Edit Client");
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

    public String getFirstNameCreate(){
        return firstNameCreate.getText();
    }

    public String getLastNameCreate(){
        return lastNameCreate.getText();
    }

    public String getAgeCreate(){
        return ageCreate.getText();
    }

    public String getAddressCreate(){
        return addressCreate.getText();
    }

    public String getCnpCreate(){
        return cnpCreate.getText();
    }

    public String getIdEdit(){
        return idEdit.getText();
    }

    public String getFirstNameEdit(){
        return firstNameEdit.getText();
    }

    public String getLastNameEdit(){
        return lastNameEdit.getText();
    }

    public String getAgeEdit(){
        return ageEdit.getText();
    }

    public String getAddressEdit(){
        return addressEdit.getText();
    }

    public String getCnpEdit(){
        return cnpEdit.getText();
    }

    public void setIdEdit(String text){
        idEdit.setText(text);
    }

    public void setFirstNameEdit(String text){
        firstNameEdit.setText(text);
    }

    public void setLastNameEdit(String text){
        lastNameEdit.setText(text);
    }

    public void setAgeEdit(String text){
        ageEdit.setText(text);
    }

    public void setAddressEdit(String text){
        addressEdit.setText(text);
    }

    public void setCnpEdit(String text){
        cnpEdit.setText(text);
    }

    public void clearCreateFields(){
        firstNameCreate.setText("");
        lastNameCreate.setText("");
        ageCreate.setText("");
        addressCreate.setText("");
        cnpCreate.setText("");
    }

    public void clearEditFields(){
        idEdit.setText("");
        firstNameEdit.setText("");
        lastNameEdit.setText("");
        ageEdit.setText("");
        addressEdit.setText("");
        cnpEdit.setText("");
    }

    //listeners
    public void addCreatePanelButtonListener(ActionListener a){
        createPanelButton.addActionListener(a);
    }

    public void addEditPanelButtonListener(ActionListener a){
        editPanelButton.addActionListener(a);
    }

    public void addCreateClientButtonListener(ActionListener a){
        createClientButton.addActionListener(a);
    }

    public void addEditClientButtonListener(ActionListener a){
        editClientButton.addActionListener(a);
    }

    public void addDeleteClientButtonListener(ActionListener a){
        deleteClientButton.addActionListener(a);
    }
}
