package jdbc.GUI.Model;

import jdbc.model.Adres;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AdresTableModel extends AbstractTableModel {
    List<Adres> adresy;

    private final String[] columnNames = new String[]{
            "IdAdresu", "Miejscowosc", "Ulica", "nrDomu", "kodPocztowy"
    };

    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class, String.class, String.class
    };

    public List<Adres> getAdresy() {
        return adresy;
    }

    public void setAdresy(List<Adres> adresy) {
        this.adresy = adresy;
    }

    public AdresTableModel() {
    }

    public AdresTableModel(List<Adres> adresy) {
        this.adresy = adresy;
    }

    @Override
    public int getRowCount() {
        return adresy.size();
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
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Adres row = adresy.get(rowIndex);

        if (columnIndex == 0) {
            return row.getIdAdresu();
        } else if (columnIndex == 1) {
            return row.getMiejscowosc();
        } else if (columnIndex == 2) {
            return row.getUlica();
        } else if (columnIndex == 3) {
            return row.getNrDomu();
        } else if (columnIndex == 4) {
            return row.getKodPocztowy();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Adres row = adresy.get(rowIndex);

        if (columnIndex == 1) {
            row.setMiejscowosc((String) aValue);
        } else if (columnIndex == 2) {
            row.setUlica((String) aValue);
        } else if (columnIndex == 3) {
            row.setNrDomu((String) aValue);
        } else if (columnIndex == 4) {
            row.setKodPocztowy((String) aValue);
        }
    }
}
