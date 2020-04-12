import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class View extends JFrame {
    JLabel judul = new JLabel("Biodata Mahasiswa");
    JLabel lnim = new JLabel("NIM");
    JLabel lnama = new JLabel("Nama");
    JLabel lalamat = new JLabel("Alamat");
    JLabel jenis = new JLabel("Jenis Kelamin");
    JLabel agama = new JLabel("Agama");

    JTextField tfnim = new JTextField();
    JTextField tfnama = new JTextField();
    JTextField tfalamat = new JTextField();
    JRadioButton lk = new JRadioButton("Laki-laki");
    JRadioButton pr = new JRadioButton("Perempuan");
    ButtonGroup jekel = new ButtonGroup();

    String[] agamanya = {"Islam","Kristen","Katolik","Hindu","Budha","Konghucu"};
    JComboBox<String> pAgama = new JComboBox<>(agamanya);

    JButton btnAdd = new JButton("Tambah");
    JButton btnCancel = new JButton("Batal");
    JButton btnEdit = new JButton("Edit");
    JButton btnDelete = new JButton("Hapus");
    JButton btnResetAll = new JButton("Reset All");

    JTable table;
    DefaultTableModel defaultTableModel;
    JScrollPane jScrollPane;
    Object kolom[] = {"Nim", "Nama","Jenis Kelamin", "Agama", "Alamat"};

    public View(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("UPN V Yogyakarta");
        setVisible(true);
        setLayout(null);
        setSize(800,1000);
        setResizable(true);
        setLocationRelativeTo(null);

        add(judul);
        judul.setBounds(200,10,200,20);
        add(lnim);
        lnim.setBounds(50,40,50,20);
        add(tfnim);
        tfnim.setBounds(150,40,100,20);
        add(lnama);
        lnama.setBounds(50,70,50,20);
        add(tfnama);
        tfnama.setBounds(150,70,100,20);

        add(jenis);
        jenis.setBounds(50,100,100,20);
        add(lk);
        lk.setBounds(150,100,100,20);
        add(pr);
        pr.setBounds(260,100,100,20);
        lk.setActionCommand("Laki - Laki");
        pr.setActionCommand("Perempuan");
        jekel.add(lk);
        jekel.add(pr);


        add(agama);
        agama.setBounds(50,130,100,20);
        add(pAgama);
        pAgama.setBounds(150,130,150,20);
        pAgama.setSelectedItem("");
        pAgama.addActionListener(pAgama);

        add(lalamat);
        lalamat.setBounds(50,160,50,20);
        add(tfalamat);
        tfalamat.setBounds(150,160,100,60);

        add(btnAdd);
        btnAdd.setBounds(50,230,100,20);
        add(btnCancel);
        btnCancel.setBounds(175,230,100,20);
        add(btnEdit);
        btnEdit.setBounds(300,230,100,20);
        add(btnDelete);
        btnDelete.setBounds(425,230,100,20);
        add(btnResetAll);
        btnResetAll.setBounds(550,230,100,20);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);

        defaultTableModel= new DefaultTableModel(kolom, 0);
        table = new JTable(defaultTableModel);
        jScrollPane = new JScrollPane(table);
        add(jScrollPane);
        jScrollPane.setBounds(20, 290, 600, 350);
    }
    public String getNim() {
        return tfnim.getText();
    }
    public String getNama() {
        return tfnama.getText();
    }
    public String getAlamat() {
        return tfalamat.getText();
    }
    public String getJenisKelamin(){
        return jekel.getSelection().getActionCommand();
    }
    public String getAgama(){
        return (String) pAgama.getSelectedItem();
    }

}
