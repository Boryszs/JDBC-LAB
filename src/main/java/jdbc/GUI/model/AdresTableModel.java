package jdbc.GUI.model;

import jdbc.model.Adres;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AdresTableModel extends AbstractTableModel {

    List<Adres> adresList;

    private final String[] columnNames = new String[]{
            "IdAdresu", "Miejscowosc", "Ulica", "nrDomu", "kodPocztowy"
    };

    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class, String.class, String.class
    };

    public List<Adres> getAdresList() {
        return adresList;
    }

    public void setAdresList(List<Adres> adresList) {
        this.adresList = adresList;
    }

    public AdresTableModel() {
    }

    public AdresTableModel(List<Adres> adresy) {
        this.adresList = adresy;
    }

    @Override
    public int getRowCount() {
        return adresList.size();
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
        Adres row = adresList.get(rowIndex);

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
        Adres row = adresList.get(rowIndex);

        if (columnIndex == 1 && !row.getMiejscowosc().equals(aValue)) {
            row.setMiejscowosc((String) aValue);
            row.updateMiejscowosc();
        } else if (columnIndex == 2 && !row.getUlica().equals(aValue)) {
            row.setUlica((String) aValue);
            row.updateUlica();
        } else if (columnIndex == 3 && !row.getNrDomu().equals(aValue)) {
            row.setNrDomu((String) aValue);
            row.updateNrDomu();
        } else if (columnIndex == 4 && !row.getKodPocztowy().equals(aValue)) {
            row.setKodPocztowy((String) aValue);
            row.updateKodPocztowy();
        }
    }
}
