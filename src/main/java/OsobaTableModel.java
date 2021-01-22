import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OsobaTableModel extends AbstractTableModel {

    List<Osoba> osoby;

    public List<Osoba> getOsoby() {
        return osoby;
    }

    private final String[] columnNames = new String[] {
            "IdOsoby", "Imie", "Nazwisko", "Pesel","Data Urodzenia","Email","Telefon","idAdresu"
    };

    private final Class[] columnClass = new Class[]{
            Integer.class,String.class,String.class,String.class, Date.class,String.class,String.class,Integer.class
    };

    public OsobaTableModel(List<Osoba> osoby) {
        this.osoby = osoby;
    }

    @Override
    public int getRowCount() {
        return osoby.size();
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
        Osoba row = osoby.get(rowIndex);
        if(columnIndex == 0){
            return row.getIdOsoby();
        }else if(columnIndex == 1){
            return row.getImie();
        }else if(columnIndex == 2){
            return row.getNazwisko();
        }else if(columnIndex == 3){
            return row.getPesel();
        }else if(columnIndex == 4){
            return row.getDataUrodzenia();
        }else if(columnIndex == 5){
            return row.getEmail();
        }else if(columnIndex == 6){
            return row.getTelefon();
        }else if(columnIndex == 7){
            return row.getIdAdresu();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Osoba row = osoby.get(rowIndex);

        if(columnIndex == 1){
            row.setImie((String) aValue);
            row.updateImie();
            row.selectImie();
        }else if(columnIndex == 2){
            row.setNazwisko((String) aValue);
            row.updateNazwisko();
            row.selectNazwisko();
        }else if(columnIndex == 3){
            row.setPesel((String) aValue);
            row.updatePesel();
            row.selectPesel();
        }else if(columnIndex == 4){
            //TODO NOT WORKING CHANGE DATE
            System.out.println((Date)aValue);
            row.setDataUrodzenia((Date) aValue);
            row.updateDataUrodzenia();
            row.selectDataUrodzenia();
        }else if(columnIndex == 5){
            row.setEmail((String) aValue);
            row.updateEmail();
            row.selectEmail();
        }else if(columnIndex == 6){
            row.setTelefon((String) aValue);
            row.updateTelefon();
            row.selectTelefon();
        }
    }

}
