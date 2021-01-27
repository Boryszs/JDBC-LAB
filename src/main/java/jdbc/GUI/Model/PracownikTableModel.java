package jdbc.GUI.Model;

import jdbc.model.Pracownik;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PracownikTableModel extends AbstractTableModel{

    List<Pracownik> pracownikList;

    private final String[] columnNames = new String[]{
            "IdPracownika", "Pensja", "Rola", "IdOsoby"
    };

    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class, Integer.class
    };

    public List<Pracownik> getPracownikList() {
        return pracownikList;
    }

    public void setPracownikList(List<Pracownik> pracownikList) {
        this.pracownikList = pracownikList;
    }

    public PracownikTableModel(List<Pracownik> pracownikList) {
        this.pracownikList = pracownikList;
    }

    @Override
    public int getRowCount() {
        return pracownikList.size();
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
        Pracownik row = pracownikList.get(rowIndex);

        if (columnIndex == 0) {
            return row.getIdPracownika();
        } else if (columnIndex == 1) {
            return row.getPensja();
        } else if (columnIndex == 2) {
            return row.getRola();
        } else if (columnIndex == 3) {
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
        Pracownik row = pracownikList.get(rowIndex);

        if (columnIndex == 1) {
            Double storyValue = row.getPensja();
            row.setPensja(Double.parseDouble((String) aValue));
            Integer uValue = row.updatePensja();
            if(uValue == -1){
                row.setPensja(storyValue);
            }
        } else if (columnIndex == 2) {
            row.setRola((String) aValue);
            row.updateRola();
        }
    }
}
