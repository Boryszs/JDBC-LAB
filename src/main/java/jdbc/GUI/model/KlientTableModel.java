package jdbc.GUI.model;

import jdbc.model.Klient;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KlientTableModel extends AbstractTableModel {

    List<Klient> klienci;

    private final String[] columnNames = new String[] {
            "IdKlient", "Login", "Haslo", "IdOsoby"
    };

    private final Class[] columnClass = new Class[]{
            Integer.class,String.class,String.class,Integer.class
    };

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public KlientTableModel(List<Klient> klients) {
        this.klienci = klients;
    }

    @Override
    public int getRowCount() {
        return klienci.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klient row = klienci.get(rowIndex);

        if(columnIndex == 0){
            return row.getIdKlient();
        }else if(columnIndex == 1){
            return row.getLogin();
        }else if(columnIndex == 2){
            return row.getHaslo();
        }else if(columnIndex == 3){
            return row.getIdOsoby();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Klient row = klienci.get(rowIndex);

        if(columnIndex == 1 && !row.getLogin().equals(aValue)){
            row.setLogin((String) aValue);
            row.updateLogin();
        }else if(columnIndex == 2){
            row.setHaslo((String) aValue);
            row.updateHaslo();
        }
    }
}