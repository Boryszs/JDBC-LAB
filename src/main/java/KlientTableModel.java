import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KlientTableModel extends AbstractTableModel {

    List<Klient> klients;

    public List<Klient> getKlients() {
        return klients;
    }

    private final String[] columnNames = new String[] {
            "IdKlient", "Login", "Haslo", "IdOsoby"
    };

    private final Class[] columnClass = new Class[]{
    Integer.class,String.class,String.class,Integer.class
    };

    public KlientTableModel(List<Klient> klients) {
        this.klients = klients;
    }

    @Override
    public int getRowCount() {
        return klients.size();
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
        Klient row = klients.get(rowIndex);

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
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Klient row = klients.get(rowIndex);

        if(columnIndex == 0){
            row.setIdKlient((Integer) aValue);
        }else if(columnIndex == 1){
            row.setLogin((String) aValue);
        }else if(columnIndex == 2){
            row.setHaslo((String) aValue);
        }else if(columnIndex == 3){
            row.setIdOsoby((Integer) aValue);
        }
    }
}
