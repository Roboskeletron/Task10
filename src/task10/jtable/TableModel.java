package task10.jtable;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }
}
