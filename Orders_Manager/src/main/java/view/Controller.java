package view;

import logic.*;
import model.*;

import java.awt.event.*;
import java.lang.reflect.Field;
import java.util.*;

public class Controller {
    private final BillBLL billBLL;
    private final ClientBLL clientBLL;
    private final OrderBLL orderBLL;
    private final ProductBLL productBLL;
    private final View view;

    public Controller(BillBLL billBLL, ClientBLL clientBLL, OrderBLL orderBLL, ProductBLL productBLL, View view) {
        this.billBLL = billBLL;
        this.clientBLL = clientBLL;
        this.orderBLL = orderBLL;
        this.productBLL = productBLL;
        this.view = view;

        view.addHomeMenuListener(new HomeMenuListener());
        view.addProductMenuListener(new ProductMenuListener());
        view.addClientMenuListener(new ClientMenuListener());
        view.addOrderMenuItemListener(new OrderMenuListener());
        view.addBillMenuItemListener(new BillMenuListener());
        view.clientPanel.addCreatePanelButtonListener(new CreateClientPanelButtonListener());
        view.clientPanel.addEditPanelButtonListener(new EditClientPanelButtonListener());
        view.clientPanel.addCreateClientButtonListener(new CreateClientButtonListener());
        view.clientPanel.addEditClientButtonListener(new EditClientButtonListener());
        view.clientPanel.addDeleteClientButtonListener(new DeleteClientButtonListener());
        view.productPanel.addCreatePanelButtonListener(new CreateProductPanelButtonListener());
        view.productPanel.addEditPanelButtonListener(new EditProductPanelButtonListener());
        view.productPanel.addCreateProductButtonListener(new CreateProductButtonListener());
        view.productPanel.addEditProductButtonListener(new EditProductButtonListener());
        view.productPanel.addDeleteProductButtonListener(new DeleteProductButtonListener());
        view.orderPanel.addPlaceOrderButtonListener(new PlaceOrderButtonListener());
    }

    //class listeners
    class HomeMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            view.setPanel(0);
        }
    }

    class ProductMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshProductPanel();
            view.setPanel(1);
        }
    }

    class ClientMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshClientPanel();
            view.setPanel(2);
        }
    }

    class OrderMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshOrderPanel();
            view.setPanel(3);
        }
    }

    class BillMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshBillPanel();
            view.setPanel(4);
        }
    }

    class CreateClientPanelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshClientPanel();
            view.clientPanel.setPanel(0);
        }
    }

    class EditClientPanelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshClientPanel();
            view.clientPanel.setPanel(1);
        }
    }

    class CreateClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String firstName = view.clientPanel.getFirstNameCreate();
            String lastName = view.clientPanel.getLastNameCreate();
            String ageString = view.clientPanel.getAgeCreate();
            String address = view.clientPanel.getAddressCreate();
            String cnp = view.clientPanel.getCnpCreate();

            if (!firstName.equals("") && !lastName.equals("") && !ageString.equals("") && !address.equals("") && !cnp.equals("")) {
                int age = Integer.parseInt(ageString);

                if (clientBLL.insertClient(firstName, lastName, age, address, cnp)) {
                    refreshClientPanel();
                } else {
                    view.clientPanel.showErrorDialog();
                }
            } else {
                view.clientPanel.showErrorDialog();
            }
        }
    }

    class EditClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String idString = view.clientPanel.getIdEdit();
            String firstName = view.clientPanel.getFirstNameEdit();
            String lastName = view.clientPanel.getLastNameEdit();
            String ageString = view.clientPanel.getAgeEdit();
            String address = view.clientPanel.getAddressEdit();
            String cnp = view.clientPanel.getCnpEdit();

            if (!idString.equals("") && !firstName.equals("") && !lastName.equals("") && !ageString.equals("") && !address.equals("") && !cnp.equals("")) {
                long id = Long.parseLong(idString);
                int age = Integer.parseInt(ageString);

                if (clientBLL.updateClient(id, firstName, lastName, age, address, cnp)) {
                    refreshClientPanel();
                } else {
                    view.clientPanel.showErrorDialog();
                }
            } else {
                view.clientPanel.showErrorDialog();
            }
        }
    }

    class DeleteClientButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String idString = view.clientPanel.getIdEdit();
            if (!idString.equals("")) {
                long id = Long.parseLong(idString);

                clientBLL.deleteClient(id);

                refreshClientPanel();
            } else {
                view.clientPanel.showErrorDialog();
            }
        }
    }

    class CreateProductPanelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshProductPanel();
            view.productPanel.setPanel(0);
        }
    }

    class EditProductPanelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            refreshProductPanel();
            view.productPanel.setPanel(1);
        }
    }

    class CreateProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String productName = view.productPanel.getProductNameCreate();
            String amountString = view.productPanel.getAmountCreate();
            String priceString = view.productPanel.getPriceCreate();
            String manufacturer = view.productPanel.getManufacturerCreate();

            if (!productName.equals("") && !amountString.equals("") && !priceString.equals("") && !manufacturer.equals("")) {
                int amount = Integer.parseInt(amountString);
                double price = Double.parseDouble(priceString);

                if (productBLL.insertProduct(productName, amount, price, manufacturer)) {
                    refreshProductPanel();
                } else {
                    view.productPanel.showErrorDialog();
                }
            } else {
                view.productPanel.showErrorDialog();
            }
        }
    }

    class EditProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String idString = view.productPanel.getIdEdit();
            String productName = view.productPanel.getProductNameEdit();
            String amountString = view.productPanel.getAmountEdit();
            String priceString = view.productPanel.getPriceEdit();
            String manufacturer = view.productPanel.getManufacturerEdit();

            if (!idString.equals("") && !productName.equals("") && !amountString.equals("") && !priceString.equals("") && !manufacturer.equals("")) {
                long id = Long.parseLong(idString);
                int amount = Integer.parseInt(amountString);
                double price = Double.parseDouble(priceString);

                if (productBLL.updateProduct(id, productName, amount, price, manufacturer)) {
                    refreshProductPanel();
                } else {
                    view.productPanel.showErrorDialog();
                }
            } else {
                view.productPanel.showErrorDialog();
            }
        }
    }

    class DeleteProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String idString = view.productPanel.getIdEdit();
            if (!idString.equals("")) {
                long id = Long.parseLong(idString);
                productBLL.deleteProduct(id);
                refreshProductPanel();
            } else {
                view.productPanel.showErrorDialog();
            }
        }
    }

    class PlaceOrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            String clientIdString = view.orderPanel.getClientId();
            String productIdString = view.orderPanel.getProductId();
            String amountString = view.orderPanel.getAmount();
            if (!amountString.equals("") && clientIdString != null && productIdString != null) {
                long clientId = Long.parseLong(clientIdString);
                long productId = Long.parseLong(productIdString);
                int amount = Integer.parseInt(amountString);

                ClientM clientM = clientBLL.findClient(clientId);
                Product product = productBLL.findProduct(productId);

                if (orderBLL.insertOrder(product, clientM, billBLL, productBLL, amount)) {
                    refreshOrderPanel();
                } else {
                    view.orderPanel.showErrorDialog();
                }
            } else {
                view.clientPanel.showErrorDialog();
            }
        }
    }

    //util methods
    private String[] retrieveTableHeader(List<?> objects) {
        Field[] fields = objects.get(0).getClass().getDeclaredFields();
        String[] tableHeader = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            tableHeader[i] = fields[i].getName();
        }

        return tableHeader;
    }

    private List<String[]> retrieveTableData(List<?> objects) {
        List<String[]> tableData = new ArrayList<>();
        for (Object object : objects) {
            Field[] fields = object.getClass().getDeclaredFields();
            String[] d = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);

                try {
                    d[i] = String.valueOf(fields[i].get(object));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            tableData.add(d);
        }

        return tableData;
    }

    private void refreshOrderPanel() {
        List<OrderM> orders = orderBLL.findAllOrders();
        List<ClientM> clients = clientBLL.findAllClients();
        List<Product> products = productBLL.findAllProducts();

        List<String> clientsId = new ArrayList<>();
        for (ClientM client : clients) {
            clientsId.add(String.valueOf(client.getId()));
        }

        List<String> productsId = new ArrayList<>();
        for (Product product : products) {
            productsId.add(String.valueOf(product.getId()));
        }

        view.orderPanel.setClientsId(clientsId);
        view.orderPanel.setProductsId(productsId);
        view.orderPanel.clearFields();
        view.orderPanel.clearTable();

        if (orders.isEmpty()) {
            view.orderPanel.setHeader(new String[]{"NO DATA"});
        } else {
            String[] tableHeader = retrieveTableHeader(orders);
            List<String[]> tableData = retrieveTableData(orders);
            view.orderPanel.setHeader(tableHeader);
            view.orderPanel.setRows(tableData);
        }
    }

    private void refreshClientPanel() {
        List<ClientM> clients = clientBLL.findAllClients();

        view.clientPanel.clearCreateFields();
        view.clientPanel.clearEditFields();
        view.clientPanel.clearTable();

        if (clients.isEmpty()) {
            view.clientPanel.setHeader(new String[]{"NO DATA"});
        } else {
            String[] tableHeader = retrieveTableHeader(clients);
            List<String[]> tableData = retrieveTableData(clients);
            view.clientPanel.setHeader(tableHeader);
            view.clientPanel.setRows(tableData);
        }
    }

    private void refreshProductPanel() {
        List<Product> products = productBLL.findAllProducts();

        view.productPanel.clearTable();
        view.productPanel.clearEditFields();
        view.productPanel.clearCreateFields();

        if (products.isEmpty()) {
            view.productPanel.setHeader(new String[]{"NO DATA"});
        } else {
            String[] tableHeader = retrieveTableHeader(products);
            List<String[]> tableData = retrieveTableData(products);
            view.productPanel.setHeader(tableHeader);
            view.productPanel.setRows(tableData);
        }
    }

    private void refreshBillPanel() {
        List<Bill> bills = billBLL.findAllBills();

        view.billPanel.clearTable();

        if (bills.isEmpty()) {
            view.billPanel.setHeader(new String[]{"NO DATA"});
        } else {
            String[] tableHeader = retrieveTableHeader(bills);
            List<String[]> tableData = retrieveTableData(bills);
            view.billPanel.setHeader(tableHeader);
            view.billPanel.setRows(tableData);
        }
    }
}
