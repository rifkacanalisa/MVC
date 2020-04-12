import javax.swing.*;
import java.sql.*;

public class Model {
    static final String driver = "com.mysql.jdbc.Driver";
    static final String dbURL = "jdbc:mysql://localhost/dbmvc";
    static final String user = "root";
    static final String pass = "";

    Connection connection;
    Statement statement;

    public Model(){
        try{
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(dbURL,user,pass);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Gagal Koneksi Database!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Driver Tidak Ditemukan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void InsertData(String Nim, String Nama, String JenKel ,String Agama ,String Alamat){
        try {
            String query = "INSERT INTO `mahasiswa` VALUES ('"+Nim+"','"+Nama+"','"+JenKel+"','"+Agama
                    +"','"+Alamat+"')";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String[][] ReadData(){
        try{
            int jmlhData = 0;
            String data[][] = new String[getBanyakData()][5];
            String query = "select * from `mahasiswa`";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlhData][0] = resultSet.getString("nim");
                data[jmlhData][1] = resultSet.getString("nama");
                data[jmlhData][2] = resultSet.getString("jekel");
                data[jmlhData][3] = resultSet.getString("agama");
                data[jmlhData][4] = resultSet.getString("alamat");
                jmlhData++;
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Ditayangkan!",
                    "Hasil", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public int getBanyakData(){
        int jmlhData = 0;
        try{
            statement = connection.createStatement();
            String query = "select * from `mahasiswa`";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlhData++;
            }
            return jmlhData;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Data Gagal Dihitung!",
                    "Hasil", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void deleteData (String nim) {
        try{
            String query = "DELETE FROM `mahasiswa` WHERE `nim` = '"+nim+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
        }catch(SQLException sql) {
            JOptionPane.showMessageDialog(null,"Data Gagal Dihapus!",
                    "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void UpdateData(String NimA,String Nim, String Nama, String JenKel ,String Agama ,String Alamat){
        try {
            String query = "update `mahasiswa` set `nim` = '"+Nim+"', `nama` = '"+Nama+"', `jekel` = '"
                    +JenKel+"', `agama` = '"+Agama +"', `alamat` = '"+Alamat+"' where `nim` = '"+NimA+"'";
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan!", "Hasil",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetData() {
        try{
            String query = "DELETE FROM `mahasiswa`";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
        }catch(SQLException sql) {
            JOptionPane.showMessageDialog(null,"Data Gagal Dihapus!",
                    "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
