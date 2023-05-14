package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class View extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JMenuBar menuBar;
    private JMenuItem homeMenuItem;
    private JMenuItem productMenuItem;
    private JMenuItem clientMenuItem;
    private JMenuItem orderMenuItem;
    private JMenuItem billMenuItem;
    private CardLayout cardLayout;
    public ProductPanel productPanel;
    public ClientPanel clientPanel;
    public OrderPanel orderPanel;
    public BillPanel billPanel;

    public View() {
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Order Management Application");

        ImageIcon appIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/add.png")));
        setIconImage(appIcon.getImage());

        createMainPanel();

        add(mainPanel);
    }

    //util methods
    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createMenuBar();
        createContentPanel();

        mainPanel.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(contentPanel);
    }

    private void createContentPanel() {
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        HomePanel homePanel = new HomePanel();
        productPanel = new ProductPanel();
        clientPanel = new ClientPanel();
        orderPanel = new OrderPanel();
        billPanel = new BillPanel();

        contentPanel.setLayout(cardLayout);

        contentPanel.add(homePanel, "0");
        contentPanel.add(productPanel, "1");
        contentPanel.add(clientPanel, "2");
        contentPanel.add(orderPanel, "3");
        contentPanel.add(billPanel, "4");
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        JMenu homeMenu = new JMenu();
        JMenu productMenu = new JMenu("Products");
        JMenu clientMenu = new JMenu("Clients");
        JMenu orderMenu = new JMenu("Orders");
        homeMenuItem = new JMenuItem();
        productMenuItem = new JMenuItem("Products");
        clientMenuItem = new JMenuItem("Clients");
        orderMenuItem = new JMenuItem("View Orders");
        billMenuItem = new JMenuItem("View Bills");

        ImageIcon homeIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/home.png")));
        ImageIcon productIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/package.png")));
        ImageIcon clientIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/customer.png")));
        ImageIcon orderMenuIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/list.png")));
        ImageIcon orderIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/order.png")));
        ImageIcon billIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/bill.png")));

        homeMenu.setIcon(homeIcon);
        homeMenuItem.setIcon(homeIcon);
        productMenu.setIcon(productIcon);
        productMenuItem.setIcon(productIcon);
        clientMenu.setIcon(clientIcon);
        clientMenuItem.setIcon(clientIcon);
        orderMenu.setIcon(orderMenuIcon);
        orderMenuItem.setIcon(orderIcon);
        billMenuItem.setIcon(billIcon);

        homeMenu.add(homeMenuItem);
        productMenu.add(productMenuItem);
        clientMenu.add(clientMenuItem);
        orderMenu.add(orderMenuItem);
        orderMenu.add(billMenuItem);

        menuBar.add(homeMenu);
        menuBar.add(productMenu);
        menuBar.add(clientMenu);
        menuBar.add(orderMenu);
    }

    public void setPanel(int flag) {
        switch (flag) {
            case 0 -> cardLayout.show(contentPanel, "0");
            case 1 -> cardLayout.show(contentPanel, "1");
            case 2 -> cardLayout.show(contentPanel, "2");
            case 3 -> cardLayout.show(contentPanel, "3");
            case 4 -> cardLayout.show(contentPanel, "4");
        }
    }

    //listeners
    public void addHomeMenuListener(ActionListener a) {
        homeMenuItem.addActionListener(a);
    }

    public void addProductMenuListener(ActionListener a) {
        productMenuItem.addActionListener(a);
    }

    public void addClientMenuListener(ActionListener a) {
        clientMenuItem.addActionListener(a);
    }

    public void addOrderMenuItemListener(ActionListener a) {
        orderMenuItem.addActionListener(a);
    }

    public void addBillMenuItemListener(ActionListener a) {
        billMenuItem.addActionListener(a);
    }
}
