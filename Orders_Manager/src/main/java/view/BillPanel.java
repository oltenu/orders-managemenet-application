package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BillPanel extends JPanel {
    private final DefaultTableModel model;
    public BillPanel(){
        JTable billTable = new JTable();
        model = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        billTable.setModel(model);
        add(new JScrollPane(billTable));
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
}
