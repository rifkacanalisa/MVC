import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.sql.*;

public class Controller {
    Model model;
    View view;
    ViewEdit viewEdit;
    String dataedit = null;
    int baris, kolom;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

        if(model.getBanyakData() != 0 ){
            String data[][] = model.ReadData();
            view.table.setModel((new JTable(data, view.kolom)).getModel());
        } else {
            view.btnResetAll.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        view.btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getNim().equals("")){
                    JOptionPane.showMessageDialog(null, "NIM masih kosong");
                }
                else{
                    String nim = view.getNim();
                    String nama = view.getNama();
                    String alamat = view.getAlamat();
                    String jenkel = view.getJenisKelamin();
                    String agama = view.getAgama();
                    model.InsertData(nim,nama,jenkel,agama,alamat);
                    view.tfnim.setText("");
                    view.tfnama.setText("");
                    view.pAgama.setSelectedItem("");
                    view.jekel.clearSelection();
                    view.tfalamat.setText("");
                    update();
                }
            }
        });

        view.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    view.tfnim.setText("");
                    view.tfnama.setText("");
                    view.pAgama.setSelectedItem("");
                    view.jekel.clearSelection();
                    view.tfalamat.setText("");
                }catch (Exception ex){
                    System.out.println("Error");
                }
            }
        });

        view.table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                baris = view.table.getSelectedRow();
                kolom = view.table.getSelectedColumn();

                dataedit = view.table.getValueAt(baris,0).toString();
                if (dataedit != null){
                    view.btnEdit.setEnabled(true);
                    view.btnDelete.setEnabled(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(dataedit != null){
                        model.deleteData(dataedit);
                        update();
                        view.btnDelete.setEnabled(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Data Gagal Dihapus!",
                            "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dataedit != null) {
                        String nimAwal = view.table.getValueAt(baris, 0).toString();
                        String namaAwal = view.table.getValueAt(baris, 1).toString();
                        String jenkelAwal = view.table.getValueAt(baris, 2).toString();
                        String agamaAwal = view.table.getValueAt(baris, 3).toString();
                        String alamatAwal = view.table.getValueAt(baris, 4).toString();

                        viewEdit = new ViewEdit();
                        view.dispose();
                        viewEdit.tfnim.setText(nimAwal);
                        viewEdit.tfnama.setText(namaAwal);
                        if (jenkelAwal.equals("Laki - Laki")) {
                            viewEdit.lk.setSelected(true);
                        } else {
                            viewEdit.pr.setSelected(true);
                        }
                        viewEdit.tfalamat.setText(alamatAwal);
                        viewEdit.btnSave.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (viewEdit.tfnim != null) {
                                    String nimEdit = viewEdit.getNim();
                                    String namaEdit = viewEdit.getNama();
                                    String alamatEdit = viewEdit.getAlamat();
                                    String jenkelEdit = viewEdit.getJenisKelamin();
                                    String agamaEdit = viewEdit.getAgama();
                                    model.UpdateData(nimAwal, nimEdit, namaEdit, jenkelEdit, agamaEdit, alamatEdit);

                                    viewEdit.dispose();
                                    MVC mvc = new MVC();
                                } else {
                                    JOptionPane.showMessageDialog(null, "NIM masih kosong");
                                }
                            }

                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Diubah!",
                            "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.btnResetAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(model.getBanyakData() != 0){
                        model.resetData();
                        update();
                        view.btnResetAll.setEnabled(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Data Gagal Dihapus!",
                            "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void update(){
        String data1[][] = model.ReadData();
        view.table.setModel((new JTable(data1, view.kolom)).getModel());
    }

}
