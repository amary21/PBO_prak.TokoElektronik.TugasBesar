/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.TableModel;

/**
 *
 * @author ErwinSetyaWibowo
 */
public class Form_MenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form tampilan
     */
    String jeniskelamin = "";
    Pembeli user = new Pembeli();
    Barang brng = new Barang();
    int ID = 0;
    
    public Form_MenuUtama() {        
        initComponents();
        showDate();
    }
    
    public void showDate(){
        GregorianCalendar tanggal = new GregorianCalendar();
        int hari = tanggal.get(Calendar.DAY_OF_MONTH);
        int bulan = tanggal.get(Calendar.MONTH);
        int tahun = tanggal.get(Calendar.YEAR);
        isi_tgl.setText(hari+"-"+(bulan+1)+"-"+tahun);
    }
        
    public void go_formDataUser(){
        //mengkosongkan panel form menu
        Form_Menu.removeAll();
        Form_Menu.repaint();
        Form_Menu.revalidate();
        
        //mengisi panel form menu dengan panel Datauser
        Form_Menu.add(Form_DataUser);
        Form_Menu.repaint();
        Form_Menu.revalidate();
        
    }
    
    public void go_menuBarang(){
        //mengkosongkan panel form menu
        Form_Menu.removeAll();
        Form_Menu.repaint();
        Form_Menu.revalidate();
        
        //mengisi panel form menu dengan panel menubarang
        Form_Menu.add(Form_MenuBarang);
        Form_Menu.repaint();
        Form_Menu.revalidate();
    }
    
    public void go_formdataBarang(){
        //mengkosongkan panel form menu
        Form_Menu.removeAll();
        Form_Menu.repaint();
        Form_Menu.revalidate();
        
        //mengisi panel form menu dengan panel databarang
        Form_Menu.add(Form_DataBarang);
        Form_Menu.repaint();
        Form_Menu.revalidate();
    }
    
    public void go_NotaBayar(){
        //mengkosongkan panel form menu
        Form_Menu.removeAll();
        Form_Menu.repaint();
        Form_Menu.revalidate();
        
        //mengisi panel form menu dengan panel notabayar
        Form_Menu.add(Form_NotaBayar);
        Form_Menu.repaint();
        Form_Menu.revalidate();
    }
    
    public void go_editUser(){
        //mengkosongkan panel form menu
        Form_Menu.removeAll();
        Form_Menu.repaint();
        Form_Menu.revalidate();
        
        //mengisi panel form menu dengan panel notabayar
        Form_Menu.add(Form_EditDataUser);
        Form_Menu.repaint();
        Form_Menu.revalidate();
    }
    
    public void printNota(Component nota){
        PrinterJob pj = PrinterJob.getPrinterJob();
        //pj.setJobName(" Print Nota Bayar ");
        pj.setPrintable (new Printable() {    
            @Override
            public int print(Graphics pg, PageFormat pf, int pageNum){
                if (pageNum > 0){
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D g2 = (Graphics2D) pg;
            g2.translate(pf.getImageableX(), pf.getImageableY());
            nota.paint(g2);
            return Printable.PAGE_EXISTS;
        }
        });
        if (pj.printDialog() == false)
        return;

        try {
            pj.print();
        } catch (PrinterException ex) {

        }
    }
    
    public void InsertDataPembeli(){
                
        try {
            if (rb_laki.isSelected()) {
                jeniskelamin = rb_laki.getText();
            }else if (rb_perempuan.isSelected()) {
                jeniskelamin = rb_perempuan.getText();
            }
            txt_noAntriBrng.setText(txt_noAntri.getText());
            txt_noAntri_Edit.setText(txt_noAntri.getText());
            txt_nama_edit.setText(txt_namauser.getText());
            
            txt_telepon_edit.setText(txt_teleponuser.getText());
            txt_alamat_edit.setText(txt_alamatuser.getText());
            int idUser = Integer.parseInt(txt_noAntri.getText());
            String NamaUser = txt_namauser.getText();
            String TeleponUser = txt_teleponuser.getText();
            String AlamatUser = txt_alamatuser.getText();
            
            user = new Pembeli(idUser, NamaUser, jeniskelamin, TeleponUser, AlamatUser);
            user.insertPembeli();
            System.out.println("mengirim data ke class pembeli");
            
            go_menuBarang();
        } catch (NumberFormatException e) {
            System.out.println("gagal kirim ke class pembeli");
        }
    }
    
    public void validasiPembeli(){
        if (txt_noAntri.getText().equals("")||
            txt_noAntri.getText().equals("No Antri")||
            txt_namauser.getText().equals("")||
            txt_namauser.getText().equals("Nama")||
            txt_teleponuser.getText().equals("")||
            txt_teleponuser.getText().equals("Telepon")||
            txt_alamatuser.getText().equals("")||
            txt_alamatuser.getText().equals("Alamat")) {
            
                jlbl_ShowMassageusr.setText("Data masih ada yang kosong, harap isi terlebih dahulu");
                
        } else {
            InsertDataPembeli();
        }
    }
        
    public void viewPembeli(){
        isi_noAntri.setText(String.valueOf(user.getIdUser()));
        isi_Nama.setText(user.getNamaUser());
        isi_JenisKelamin.setText(user.getJkUser());
        isi_Telepon.setText(user.getTeleponUser());
        isi_Alamat.setText(user.getAlamatUser());
    }
    
    public void updatePembeli(){
        try {
            if (rb_laki.isSelected()) {
                jeniskelamin = rb_laki.getText();
            }else if (rb_perempuan.isSelected()) {
                jeniskelamin = rb_perempuan.getText();
            }
            
            int idUser = Integer.parseInt(txt_noAntri_Edit.getText());
            String NamaUser = txt_nama_edit.getText();
            String TeleponUser = txt_telepon_edit.getText();
            String AlamatUser = txt_alamat_edit.getText();
            
            user = new Pembeli(idUser, NamaUser, jeniskelamin, TeleponUser, AlamatUser);
            user.updatePembeli();
            System.out.println("berhasil kirim ke class update pembeli");
            
            go_NotaBayar();
        } catch (NumberFormatException e) {
            System.out.println("gagal kirim update pembeli");
        }
    }
    
    public void validasiEditPembeli(){
        if (txt_nama_edit.getText().equals("Nama")||
            txt_nama_edit.getText().equals("")||
            txt_telepon_edit.getText().equals("Telepon")||
            txt_telepon_edit.getText().equals("")||
            txt_alamat_edit.getText().equals("Alamat")||
            txt_alamat_edit.getText().equals("")) {
            jlbl_ShowMassage_edit.setText("Data masih ada yang kosong, harap isi terlebih dahulu");
        } else {
            updatePembeli();
        }
    }
    
    public void InsertDataBarang(){
       try {
            int idUserBrng = Integer.parseInt(txt_noAntriBrng.getText());
            String namaBarang = txt_namaBarang.getText();
            int jumbarang = Integer.parseInt(txt_jumlahbarang.getText());
            int hargabarang = Integer.parseInt(txt_hargabarang.getText());
            int diskon = Integer.parseInt(txt_diskonBarang.getText());
            int totharga = Integer.parseInt(txt_totalharga.getText());
            
            brng = new Barang(namaBarang, jumbarang, hargabarang, diskon, totharga, idUserBrng);
            brng.insertBarang();
            
            System.out.println("kirim ke class barang");
            go_NotaBayar();
        } catch (NumberFormatException e) {
            System.out.println("gagal kirim ke class barang");
        }
    }
        
    public void viewBarang(){
        DefaultTableModel model_tblBarangBeli = new DefaultTableModel();
        model_tblBarangBeli.addColumn("No");
        model_tblBarangBeli.addColumn("Nama Barang");
        model_tblBarangBeli.addColumn("Jumlah");
        model_tblBarangBeli.addColumn("Harga");
        model_tblBarangBeli.addColumn("Diskon");
        model_tblBarangBeli.addColumn("Total");
        tbl_BarangBeli.setModel(model_tblBarangBeli);
        String querySelect = "SELECT id_barang, nama_barang, jumlah_barang, harga_barang, diskon_barang, total_harga FROM tbl_barang";
           try {
            brng.res = brng.stat.executeQuery(querySelect);
            while(brng.res.next()){ 
                model_tblBarangBeli.addRow(new Object[]{
                        brng.res.getInt(1),
                        brng.res.getString(2),
                        brng.res.getInt(3),
                        brng.res.getInt(4),
                        brng.res.getInt(5),
                        brng.res.getInt(6)
                    });
                System.out.println("berhasil select");
                tbl_BarangBeli.setModel(model_tblBarangBeli);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void validasiBarang(){
        if (txt_jumlahbarang.getText().equals("")) {
            jlbl_ShowMasaagebrng.setText("Jumlah barang belum di tentukan, silahkan isi terlebih dahulu");
        } else if (txt_totalharga.getText().equals("")) {
            jlbl_ShowMasaagebrng.setText("Total Harag belum di hitung, silahkan hitung terlebih dahulu");
        } else {
            InsertDataBarang();
        }
    }
    
    public void hitungTotarbayar(){
        int jumBaris =  tbl_BarangBeli.getRowCount();
        int totalBayar = 0;
        int total;
        TableModel tabelNota;
        tabelNota = tbl_BarangBeli.getModel();
        for (int i = 0; i < jumBaris; i++) {
            total = Integer.parseInt(tabelNota.getValueAt(i, 5).toString());
            totalBayar = totalBayar + total;
        }
        
        isi_TotBayar.setText(String.valueOf(totalBayar));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbg_jeniskelamin = new javax.swing.ButtonGroup();
        Form_Menu = new javax.swing.JPanel();
        Form_DataUser = new javax.swing.JPanel();
        Form_dataUser = new javax.swing.JPanel();
        jlbl_iconuser = new javax.swing.JLabel();
        txt_namauser = new javax.swing.JTextField();
        js_namauser = new javax.swing.JSeparator();
        jlbl_Jklamin = new javax.swing.JLabel();
        rb_laki = new javax.swing.JRadioButton();
        rb_perempuan = new javax.swing.JRadioButton();
        txt_teleponuser = new javax.swing.JTextField();
        js_teleponuser = new javax.swing.JSeparator();
        txt_alamatuser = new javax.swing.JTextField();
        js_alamatuser = new javax.swing.JSeparator();
        txt_noAntri = new javax.swing.JTextField();
        js_noAntri = new javax.swing.JSeparator();
        jlbl_salam = new javax.swing.JLabel();
        jlbl_silInputdata = new javax.swing.JLabel();
        btn_submitusr = new javax.swing.JButton();
        btn_resetusr = new javax.swing.JButton();
        jlbl_ShowMassageusr = new javax.swing.JLabel();
        Form_MenuBarang = new javax.swing.JPanel();
        jlb_dvd = new javax.swing.JLabel();
        jbl_merek = new javax.swing.JLabel();
        jbl_harga = new javax.swing.JLabel();
        bt_dvd = new javax.swing.JButton();
        jlb_tv = new javax.swing.JLabel();
        jlb_merektv = new javax.swing.JLabel();
        jlb_hargatv = new javax.swing.JLabel();
        bt_tv = new javax.swing.JButton();
        jlb_speaker = new javax.swing.JLabel();
        jlb_hargaspeaker = new javax.swing.JLabel();
        jlb_merekspeaker = new javax.swing.JLabel();
        bt_speaker = new javax.swing.JButton();
        jlb_kipas = new javax.swing.JLabel();
        bt_kipas = new javax.swing.JButton();
        jbl_merekkipas = new javax.swing.JLabel();
        jlb_hargakipas = new javax.swing.JLabel();
        jlb_dispenser = new javax.swing.JLabel();
        jlb_merekdispenser = new javax.swing.JLabel();
        jlb_hargadispenser = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bt_dispenser = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlb_hagalaptop = new javax.swing.JLabel();
        bt_laptop = new javax.swing.JButton();
        jlb_ricecooker = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlb_merekrice = new javax.swing.JLabel();
        jlb_hargarice = new javax.swing.JLabel();
        bt_rice = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jlb_laptop = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Form_DataBarang = new javax.swing.JPanel();
        Form_DataJumBarang = new javax.swing.JPanel();
        jlbl_iconbarang1 = new javax.swing.JLabel();
        jlbl_iconbarang2 = new javax.swing.JLabel();
        jlbl_iconbarang3 = new javax.swing.JLabel();
        txt_namaBarang = new javax.swing.JTextField();
        js_namabarang = new javax.swing.JSeparator();
        txt_jumlahbarang = new javax.swing.JTextField();
        js_jumlahbarang = new javax.swing.JSeparator();
        txt_hargabarang = new javax.swing.JTextField();
        js_hargabarang = new javax.swing.JSeparator();
        txt_totalharga = new javax.swing.JTextField();
        js_totalharga = new javax.swing.JSeparator();
        txt_diskonBarang = new javax.swing.JTextField();
        js_diskonBarang = new javax.swing.JSeparator();
        jlbl_namaBarang = new javax.swing.JLabel();
        jlbl_jumlahBarang = new javax.swing.JLabel();
        jlbl_hargaBarang = new javax.swing.JLabel();
        jlbl_diskonBarang = new javax.swing.JLabel();
        btn_total = new javax.swing.JButton();
        txt_noAntriBrng = new javax.swing.JTextField();
        js_noAntri1 = new javax.swing.JSeparator();
        jlbl_noAntriBarang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_submitbrng = new javax.swing.JButton();
        jlbl_ShowMasaagebrng = new javax.swing.JLabel();
        btn_resetbrng = new javax.swing.JButton();
        Form_EditDataUser = new javax.swing.JPanel();
        Form_EditdataUser = new javax.swing.JPanel();
        jlbl_icon_edit = new javax.swing.JLabel();
        txt_nama_edit = new javax.swing.JTextField();
        js_nama_edit = new javax.swing.JSeparator();
        jlbl_Jklamin_edit = new javax.swing.JLabel();
        rb_laki_edit = new javax.swing.JRadioButton();
        rb_perempuan_edit = new javax.swing.JRadioButton();
        txt_telepon_edit = new javax.swing.JTextField();
        js_telepon_edit = new javax.swing.JSeparator();
        txt_alamat_edit = new javax.swing.JTextField();
        js_alamat_edit = new javax.swing.JSeparator();
        txt_noAntri_Edit = new javax.swing.JTextField();
        js_noAntri_Edit = new javax.swing.JSeparator();
        btn_submit_edit = new javax.swing.JButton();
        btn_reset_edit = new javax.swing.JButton();
        jlbl_ShowMassage_edit = new javax.swing.JLabel();
        Form_NotaBayar = new javax.swing.JPanel();
        btn_TambahBrang = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        jpn_PrintNota = new javax.swing.JPanel();
        ket_tgl = new javax.swing.JLabel();
        isi_tgl = new javax.swing.JLabel();
        ket_noAntri = new javax.swing.JLabel();
        isi_noAntri = new javax.swing.JLabel();
        ket_Nama = new javax.swing.JLabel();
        isi_Nama = new javax.swing.JLabel();
        Ket_JenisKelamin = new javax.swing.JLabel();
        isi_JenisKelamin = new javax.swing.JLabel();
        Ket_Telepon = new javax.swing.JLabel();
        isi_Telepon = new javax.swing.JLabel();
        Ket_Telepon1 = new javax.swing.JLabel();
        isi_Alamat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_BarangBeli = new javax.swing.JTable();
        isi_TotBayar = new javax.swing.JLabel();
        Ket_totbayar = new javax.swing.JLabel();
        ket_Rp = new javax.swing.JLabel();
        Form_Notice = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Toko Elektronik");
        setBackground(new java.awt.Color(18, 79, 123));
        setResizable(false);

        Form_Menu.setLayout(new java.awt.CardLayout());

        Form_DataUser.setBackground(new java.awt.Color(255, 255, 255));

        Form_dataUser.setBackground(new java.awt.Color(18, 79, 123));

        jlbl_iconuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8_Male_User_150px.png"))); // NOI18N

        txt_namauser.setBackground(new java.awt.Color(18, 79, 123));
        txt_namauser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_namauser.setForeground(new java.awt.Color(255, 255, 255));
        txt_namauser.setText("Nama");
        txt_namauser.setBorder(null);
        txt_namauser.setCaretColor(null);
        txt_namauser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_namauserMouseClicked(evt);
            }
        });

        js_namauser.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_Jklamin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_Jklamin.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_Jklamin.setText("Jenis Kelamin");

        rb_laki.setBackground(new java.awt.Color(18, 79, 123));
        rbg_jeniskelamin.add(rb_laki);
        rb_laki.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb_laki.setForeground(new java.awt.Color(255, 255, 255));
        rb_laki.setText("Laki-Laki");

        rb_perempuan.setBackground(new java.awt.Color(18, 79, 123));
        rbg_jeniskelamin.add(rb_perempuan);
        rb_perempuan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb_perempuan.setForeground(new java.awt.Color(255, 255, 255));
        rb_perempuan.setText("Perempuan");
        rb_perempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_lakiActionPerforme(evt);
            }
        });

        txt_teleponuser.setBackground(new java.awt.Color(18, 79, 123));
        txt_teleponuser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_teleponuser.setForeground(new java.awt.Color(255, 255, 255));
        txt_teleponuser.setText("Telepon");
        txt_teleponuser.setBorder(null);
        txt_teleponuser.setCaretColor(null);
        txt_teleponuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_teleponuserMouseClicked(evt);
            }
        });

        js_teleponuser.setForeground(new java.awt.Color(255, 255, 255));

        txt_alamatuser.setBackground(new java.awt.Color(18, 79, 123));
        txt_alamatuser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamatuser.setForeground(new java.awt.Color(255, 255, 255));
        txt_alamatuser.setText("Alamat");
        txt_alamatuser.setBorder(null);
        txt_alamatuser.setCaretColor(null);
        txt_alamatuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_alamatuserMouseClicked(evt);
            }
        });

        js_alamatuser.setForeground(new java.awt.Color(255, 255, 255));

        txt_noAntri.setBackground(new java.awt.Color(18, 79, 123));
        txt_noAntri.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noAntri.setForeground(new java.awt.Color(255, 255, 255));
        txt_noAntri.setText("No Antri");
        txt_noAntri.setBorder(null);
        txt_noAntri.setCaretColor(null);
        txt_noAntri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_noAntriMouseClicked(evt);
            }
        });

        js_noAntri.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_salam.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbl_salam.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_salam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbl_salam.setText("Selamat Datang");

        jlbl_silInputdata.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbl_silInputdata.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_silInputdata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbl_silInputdata.setText("Silahkan Masukan Data Anda");

        javax.swing.GroupLayout Form_dataUserLayout = new javax.swing.GroupLayout(Form_dataUser);
        Form_dataUser.setLayout(Form_dataUserLayout);
        Form_dataUserLayout.setHorizontalGroup(
            Form_dataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_dataUserLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(Form_dataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_dataUserLayout.createSequentialGroup()
                        .addComponent(jlbl_iconuser)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_dataUserLayout.createSequentialGroup()
                        .addGroup(Form_dataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_alamatuser)
                            .addComponent(js_alamatuser, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                            .addComponent(txt_teleponuser)
                            .addComponent(js_teleponuser, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                            .addComponent(txt_namauser)
                            .addComponent(js_namauser)
                            .addGroup(Form_dataUserLayout.createSequentialGroup()
                                .addComponent(jlbl_Jklamin, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rb_laki)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rb_perempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(js_noAntri)
                            .addComponent(txt_noAntri)
                            .addComponent(jlbl_salam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbl_silInputdata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47))))
        );
        Form_dataUserLayout.setVerticalGroup(
            Form_dataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_dataUserLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jlbl_iconuser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbl_salam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbl_silInputdata, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_noAntri, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_noAntri, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_namauser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_namauser, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_dataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbl_Jklamin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_laki)
                    .addComponent(rb_perempuan))
                .addGap(7, 7, 7)
                .addComponent(txt_teleponuser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_teleponuser, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txt_alamatuser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_alamatuser, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        btn_submitusr.setBackground(new java.awt.Color(18, 79, 123));
        btn_submitusr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_submitusr.setForeground(new java.awt.Color(255, 255, 255));
        btn_submitusr.setText("Submit");
        btn_submitusr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitusrActionPerformed(evt);
            }
        });

        btn_resetusr.setBackground(new java.awt.Color(18, 79, 123));
        btn_resetusr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_resetusr.setForeground(new java.awt.Color(255, 255, 255));
        btn_resetusr.setText("Reset");
        btn_resetusr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetusrActionPerformed(evt);
            }
        });

        jlbl_ShowMassageusr.setBackground(new java.awt.Color(255, 255, 255));
        jlbl_ShowMassageusr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbl_ShowMassageusr.setForeground(new java.awt.Color(18, 79, 123));
        jlbl_ShowMassageusr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout Form_DataUserLayout = new javax.swing.GroupLayout(Form_DataUser);
        Form_DataUser.setLayout(Form_DataUserLayout);
        Form_DataUserLayout.setHorizontalGroup(
            Form_DataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataUserLayout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addGroup(Form_DataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbl_ShowMassageusr, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Form_DataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataUserLayout.createSequentialGroup()
                            .addComponent(Form_dataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(182, 182, 182))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataUserLayout.createSequentialGroup()
                            .addComponent(btn_submitusr, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(btn_resetusr, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(245, 245, 245)))))
        );
        Form_DataUserLayout.setVerticalGroup(
            Form_DataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataUserLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(Form_dataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_ShowMassageusr, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Form_DataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_submitusr, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resetusr, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        Form_Menu.add(Form_DataUser, "card1");

        Form_MenuBarang.setBackground(new java.awt.Color(255, 255, 255));

        jlb_dvd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/dvd player(asli).jpg"))); // NOI18N

        jbl_merek.setText("DVD Player Sony S-r370");

        jbl_harga.setText("Rp 704.000");

        bt_dvd.setText("Beli");
        bt_dvd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dvdActionPerformed(evt);
            }
        });

        jlb_tv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/tvledku.jpg"))); // NOI18N

        jlb_merektv.setText("SHRAP LC-LE1701 TV LED");

        jlb_hargatv.setText("Rp 1.335.000");

        bt_tv.setText("Beli");
        bt_tv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tvActionPerformed(evt);
            }
        });

        jlb_speaker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/spekeraktif3.png"))); // NOI18N

        jlb_hargaspeaker.setText("Rp 675.000");

        jlb_merekspeaker.setText("SPEAKER POLYTRON 9051");

        bt_speaker.setText("Beli");
        bt_speaker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_speakerActionPerformed(evt);
            }
        });

        jlb_kipas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/kipasangin1.jpg"))); // NOI18N

        bt_kipas.setText("Beli");
        bt_kipas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_kipasActionPerformed(evt);
            }
        });

        jbl_merekkipas.setText("KIPAS ANGIN TORNADO");

        jlb_hargakipas.setText("Rp 120.000");

        jlb_dispenser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/dispenser1.jpg"))); // NOI18N

        jlb_merekdispenser.setText("DISPENSER CHANGHONG CMD X5");

        jlb_hargadispenser.setText("Rp 1.401.000");

        bt_dispenser.setText("Beli");
        bt_dispenser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dispenserActionPerformed(evt);
            }
        });

        jlb_hagalaptop.setText("Rp 23.450.000");

        bt_laptop.setText("Beli");
        bt_laptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_laptopActionPerformed(evt);
            }
        });

        jlb_ricecooker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/racecooker4.jpg"))); // NOI18N

        jlb_merekrice.setText("SHARP RICE COOKER");

        jlb_hargarice.setText("Rp 975.000");

        bt_rice.setText("Beli");
        bt_rice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_riceActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/mesin cuci2.jpg"))); // NOI18N

        jLabel5.setText("MESIN CUCI SANKEN T87");

        jLabel6.setText("Rp 1.129.000");

        jButton1.setText("Beli");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jlb_laptop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/laptop1.jpg"))); // NOI18N

        jLabel7.setText("ASUS ROG GL702VS");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("SILAHKAN PILIH BARANG YANG INGIN ANDA BELI");

        javax.swing.GroupLayout Form_MenuBarangLayout = new javax.swing.GroupLayout(Form_MenuBarang);
        Form_MenuBarang.setLayout(Form_MenuBarangLayout);
        Form_MenuBarangLayout.setHorizontalGroup(
            Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_MenuBarangLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_hargadispenser)
                    .addComponent(bt_dispenser, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_hagalaptop)
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(bt_laptop, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(146, 146, 146)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_hargarice)
                    .addComponent(bt_rice, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
            .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(bt_dvd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addComponent(bt_tv, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(bt_speaker, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jlb_merekdispenser)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel7)
                        .addGap(95, 95, 95)
                        .addComponent(jlb_merekrice))
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbl_merek)
                            .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jbl_harga)))
                        .addGap(72, 72, 72)
                        .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jlb_hargatv))
                            .addComponent(jlb_merektv))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_MenuBarangLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                            .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(jlb_hargaspeaker))
                                .addComponent(jlb_merekspeaker))
                            .addGap(207, 207, 207))
                        .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                            .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                    .addComponent(jlb_dispenser, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlb_laptop, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                    .addComponent(jlb_dvd, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(38, 38, 38)
                                    .addComponent(jlb_tv, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(jlb_ricecooker, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbl_merekkipas)
                                        .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                            .addGap(49, 49, 49)
                                            .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(bt_kipas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jlb_hargakipas)))))
                                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jlb_speaker, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(jlb_kipas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(44, 44, 44))
            .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 803, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Form_MenuBarangLayout.setVerticalGroup(
            Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_tv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_dvd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_speaker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_kipas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jbl_merek, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbl_harga))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jlb_merektv)
                        .addGap(18, 18, 18)
                        .addComponent(jlb_hargatv))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jlb_merekspeaker)
                        .addGap(18, 18, 18)
                        .addComponent(jlb_hargaspeaker))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jbl_merekkipas)
                        .addGap(18, 18, 18)
                        .addComponent(jlb_hargakipas, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_dvd)
                    .addComponent(bt_tv)
                    .addComponent(bt_speaker)
                    .addComponent(bt_kipas))
                .addGap(26, 26, 26)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_laptop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_dispenser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_ricecooker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlb_merekdispenser)
                    .addComponent(jLabel7)
                    .addComponent(jlb_merekrice)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jlb_hagalaptop)
                        .addGap(18, 18, 18)
                        .addComponent(bt_laptop))
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jlb_hargarice)
                        .addGap(18, 18, 18)
                        .addComponent(bt_rice))
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jlb_hargadispenser)
                        .addGap(18, 18, 18)
                        .addComponent(bt_dispenser))
                    .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(40, 40, 40))
            .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Form_MenuBarangLayout.createSequentialGroup()
                    .addGap(460, 460, 460)
                    .addGroup(Form_MenuBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addGap(84, 84, 84)
                    .addComponent(jLabel4)
                    .addContainerGap(156, Short.MAX_VALUE)))
        );

        Form_Menu.add(Form_MenuBarang, "card2");

        Form_DataBarang.setBackground(new java.awt.Color(255, 255, 255));
        Form_DataBarang.setPreferredSize(new java.awt.Dimension(870, 700));

        Form_DataJumBarang.setBackground(new java.awt.Color(18, 79, 123));

        jlbl_iconbarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8_Radio_Station_50px.png"))); // NOI18N

        jlbl_iconbarang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8_Telephone_50px.png"))); // NOI18N

        jlbl_iconbarang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8_Retro_TV_150px.png"))); // NOI18N

        txt_namaBarang.setBackground(new java.awt.Color(18, 79, 123));
        txt_namaBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_namaBarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_namaBarang.setBorder(null);
        txt_namaBarang.setEnabled(false);

        js_namabarang.setForeground(new java.awt.Color(255, 255, 255));

        txt_jumlahbarang.setBackground(new java.awt.Color(18, 79, 123));
        txt_jumlahbarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_jumlahbarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_jumlahbarang.setBorder(null);
        txt_jumlahbarang.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_jumlahbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_jumlahbarangMouseClicked(evt);
            }
        });

        js_jumlahbarang.setForeground(new java.awt.Color(255, 255, 255));

        txt_hargabarang.setBackground(new java.awt.Color(18, 79, 123));
        txt_hargabarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_hargabarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_hargabarang.setBorder(null);
        txt_hargabarang.setEnabled(false);

        js_hargabarang.setForeground(new java.awt.Color(255, 255, 255));

        txt_totalharga.setBackground(new java.awt.Color(18, 79, 123));
        txt_totalharga.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_totalharga.setForeground(new java.awt.Color(255, 255, 255));
        txt_totalharga.setBorder(null);
        txt_totalharga.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_totalharga.setEnabled(false);

        js_totalharga.setForeground(new java.awt.Color(255, 255, 255));

        txt_diskonBarang.setBackground(new java.awt.Color(18, 79, 123));
        txt_diskonBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_diskonBarang.setForeground(new java.awt.Color(255, 255, 255));
        txt_diskonBarang.setBorder(null);
        txt_diskonBarang.setEnabled(false);

        js_diskonBarang.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_namaBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_namaBarang.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_namaBarang.setText("Nama Barang");

        jlbl_jumlahBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_jumlahBarang.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_jumlahBarang.setText("Jumlah");

        jlbl_hargaBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_hargaBarang.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_hargaBarang.setText("Harga");

        jlbl_diskonBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_diskonBarang.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_diskonBarang.setText("Diskon");

        btn_total.setBackground(new java.awt.Color(18, 79, 123));
        btn_total.setForeground(new java.awt.Color(255, 255, 255));
        btn_total.setText("Total");
        btn_total.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        btn_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_totalActionPerformed(evt);
            }
        });

        txt_noAntriBrng.setBackground(new java.awt.Color(18, 79, 123));
        txt_noAntriBrng.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noAntriBrng.setForeground(new java.awt.Color(255, 255, 255));
        txt_noAntriBrng.setBorder(null);
        txt_noAntriBrng.setEnabled(false);
        txt_noAntriBrng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_noAntriBrngMouseClicked(evt);
            }
        });

        js_noAntri1.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_noAntriBarang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_noAntriBarang.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_noAntriBarang.setText("No Antri");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Detail Belanja");

        javax.swing.GroupLayout Form_DataJumBarangLayout = new javax.swing.GroupLayout(Form_DataJumBarang);
        Form_DataJumBarang.setLayout(Form_DataJumBarangLayout);
        Form_DataJumBarangLayout.setHorizontalGroup(
            Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataJumBarangLayout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(jlbl_iconbarang1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_iconbarang3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_iconbarang2)
                .addGap(93, 93, 93))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataJumBarangLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(js_noAntri1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_DataJumBarangLayout.createSequentialGroup()
                        .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbl_diskonBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_total, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbl_hargaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_diskonBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(js_totalharga, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(js_hargabarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(js_namabarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(js_jumlahbarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(js_diskonBarang, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Form_DataJumBarangLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jlbl_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_namaBarang))
                    .addGroup(Form_DataJumBarangLayout.createSequentialGroup()
                        .addComponent(jlbl_jumlahBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_jumlahbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Form_DataJumBarangLayout.createSequentialGroup()
                        .addComponent(jlbl_noAntriBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_noAntriBrng, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        Form_DataJumBarangLayout.setVerticalGroup(
            Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataJumBarangLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbl_iconbarang3)
                    .addComponent(jlbl_iconbarang1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbl_iconbarang2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_noAntriBrng, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbl_noAntriBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(js_noAntri1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbl_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(js_namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jumlahbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbl_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(js_jumlahbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbl_hargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(js_hargabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_diskonBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbl_diskonBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(js_diskonBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Form_DataJumBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(js_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        btn_submitbrng.setBackground(new java.awt.Color(18, 79, 123));
        btn_submitbrng.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_submitbrng.setForeground(new java.awt.Color(255, 255, 255));
        btn_submitbrng.setText("Submit");
        btn_submitbrng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitbrngActionPerformed(evt);
            }
        });

        jlbl_ShowMasaagebrng.setBackground(new java.awt.Color(255, 255, 255));
        jlbl_ShowMasaagebrng.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbl_ShowMasaagebrng.setForeground(new java.awt.Color(18, 79, 123));
        jlbl_ShowMasaagebrng.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_resetbrng.setBackground(new java.awt.Color(18, 79, 123));
        btn_resetbrng.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_resetbrng.setForeground(new java.awt.Color(255, 255, 255));
        btn_resetbrng.setText("Reset");
        btn_resetbrng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetbrngActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Form_DataBarangLayout = new javax.swing.GroupLayout(Form_DataBarang);
        Form_DataBarang.setLayout(Form_DataBarangLayout);
        Form_DataBarangLayout.setHorizontalGroup(
            Form_DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_DataBarangLayout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addGroup(Form_DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataBarangLayout.createSequentialGroup()
                        .addGroup(Form_DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Form_DataJumBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbl_ShowMasaagebrng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(180, 180, 180))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_DataBarangLayout.createSequentialGroup()
                        .addComponent(btn_submitbrng, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btn_resetbrng, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(243, 243, 243))))
        );
        Form_DataBarangLayout.setVerticalGroup(
            Form_DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_DataBarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Form_DataJumBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_ShowMasaagebrng, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Form_DataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_submitbrng, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resetbrng, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        Form_Menu.add(Form_DataBarang, "card3");

        Form_EditDataUser.setBackground(new java.awt.Color(255, 255, 255));
        Form_EditDataUser.setPreferredSize(new java.awt.Dimension(870, 700));

        Form_EditdataUser.setBackground(new java.awt.Color(18, 79, 123));

        jlbl_icon_edit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbl_icon_edit.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_icon_edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbl_icon_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icons8_Male_User_150px.png"))); // NOI18N
        jlbl_icon_edit.setText("EDIT DATA PEMBELI");

        txt_nama_edit.setBackground(new java.awt.Color(18, 79, 123));
        txt_nama_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nama_edit.setForeground(new java.awt.Color(255, 255, 255));
        txt_nama_edit.setText("Nama");
        txt_nama_edit.setBorder(null);
        txt_nama_edit.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_nama_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nama_editMouseClicked(evt);
            }
        });

        js_nama_edit.setForeground(new java.awt.Color(255, 255, 255));

        jlbl_Jklamin_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbl_Jklamin_edit.setForeground(new java.awt.Color(255, 255, 255));
        jlbl_Jklamin_edit.setText("Jenis Kelamin");

        rb_laki_edit.setBackground(new java.awt.Color(18, 79, 123));
        rbg_jeniskelamin.add(rb_laki_edit);
        rb_laki_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb_laki_edit.setForeground(new java.awt.Color(255, 255, 255));
        rb_laki_edit.setText("Laki-Laki");

        rb_perempuan_edit.setBackground(new java.awt.Color(18, 79, 123));
        rbg_jeniskelamin.add(rb_perempuan_edit);
        rb_perempuan_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rb_perempuan_edit.setForeground(new java.awt.Color(255, 255, 255));
        rb_perempuan_edit.setText("Perempuan");
        rb_perempuan_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_perempuan_editrb_lakiActionPerforme(evt);
            }
        });

        txt_telepon_edit.setBackground(new java.awt.Color(18, 79, 123));
        txt_telepon_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_telepon_edit.setForeground(new java.awt.Color(255, 255, 255));
        txt_telepon_edit.setText("Telepon");
        txt_telepon_edit.setBorder(null);
        txt_telepon_edit.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_telepon_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_telepon_editMouseClicked(evt);
            }
        });
        txt_telepon_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telepon_editActionPerformed(evt);
            }
        });

        js_telepon_edit.setForeground(new java.awt.Color(255, 255, 255));

        txt_alamat_edit.setBackground(new java.awt.Color(18, 79, 123));
        txt_alamat_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_alamat_edit.setForeground(new java.awt.Color(255, 255, 255));
        txt_alamat_edit.setText("Alamat");
        txt_alamat_edit.setBorder(null);
        txt_alamat_edit.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_alamat_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_alamat_editMouseClicked(evt);
            }
        });

        js_alamat_edit.setForeground(new java.awt.Color(255, 255, 255));

        txt_noAntri_Edit.setBackground(new java.awt.Color(18, 79, 123));
        txt_noAntri_Edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_noAntri_Edit.setForeground(new java.awt.Color(255, 255, 255));
        txt_noAntri_Edit.setText("No Antri");
        txt_noAntri_Edit.setBorder(null);
        txt_noAntri_Edit.setCaretColor(null);
        txt_noAntri_Edit.setEnabled(false);

        js_noAntri_Edit.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Form_EditdataUserLayout = new javax.swing.GroupLayout(Form_EditdataUser);
        Form_EditdataUser.setLayout(Form_EditdataUserLayout);
        Form_EditdataUserLayout.setHorizontalGroup(
            Form_EditdataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbl_icon_edit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_EditdataUserLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(Form_EditdataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_alamat_edit)
                    .addComponent(js_alamat_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(txt_telepon_edit)
                    .addComponent(js_telepon_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(txt_nama_edit)
                    .addComponent(js_nama_edit)
                    .addGroup(Form_EditdataUserLayout.createSequentialGroup()
                        .addComponent(jlbl_Jklamin_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rb_laki_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb_perempuan_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(js_noAntri_Edit)
                    .addComponent(txt_noAntri_Edit))
                .addGap(47, 47, 47))
        );
        Form_EditdataUserLayout.setVerticalGroup(
            Form_EditdataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_EditdataUserLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jlbl_icon_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_noAntri_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_noAntri_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nama_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_nama_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(Form_EditdataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbl_Jklamin_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_laki_edit)
                    .addComponent(rb_perempuan_edit))
                .addGap(7, 7, 7)
                .addComponent(txt_telepon_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_telepon_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txt_alamat_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(js_alamat_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        btn_submit_edit.setBackground(new java.awt.Color(18, 79, 123));
        btn_submit_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_submit_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_submit_edit.setText("Submit");
        btn_submit_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submit_editActionPerformed(evt);
            }
        });

        btn_reset_edit.setBackground(new java.awt.Color(18, 79, 123));
        btn_reset_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_reset_edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset_edit.setText("Reset");
        btn_reset_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reset_editActionPerformed(evt);
            }
        });

        jlbl_ShowMassage_edit.setBackground(new java.awt.Color(255, 255, 255));
        jlbl_ShowMassage_edit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlbl_ShowMassage_edit.setForeground(new java.awt.Color(18, 79, 123));
        jlbl_ShowMassage_edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout Form_EditDataUserLayout = new javax.swing.GroupLayout(Form_EditDataUser);
        Form_EditDataUser.setLayout(Form_EditDataUserLayout);
        Form_EditDataUserLayout.setHorizontalGroup(
            Form_EditDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_EditDataUserLayout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addGroup(Form_EditDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_EditDataUserLayout.createSequentialGroup()
                        .addGroup(Form_EditDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbl_ShowMassage_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Form_EditdataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_EditDataUserLayout.createSequentialGroup()
                        .addComponent(btn_submit_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btn_reset_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))))
        );
        Form_EditDataUserLayout.setVerticalGroup(
            Form_EditDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_EditDataUserLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(Form_EditdataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbl_ShowMassage_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Form_EditDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_submit_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reset_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        Form_Menu.add(Form_EditDataUser, "card4");

        Form_NotaBayar.setBackground(new java.awt.Color(255, 255, 255));
        Form_NotaBayar.setPreferredSize(new java.awt.Dimension(870, 700));

        btn_TambahBrang.setBackground(new java.awt.Color(18, 79, 123));
        btn_TambahBrang.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        btn_TambahBrang.setForeground(new java.awt.Color(255, 255, 255));
        btn_TambahBrang.setText("TAMBAH BARANG");
        btn_TambahBrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TambahBrangActionPerformed(evt);
            }
        });

        btn_Edit.setBackground(new java.awt.Color(18, 79, 123));
        btn_Edit.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        btn_Edit.setForeground(new java.awt.Color(255, 255, 255));
        btn_Edit.setText("EDIT USER");
        btn_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditActionPerformed(evt);
            }
        });

        btn_Delete.setBackground(new java.awt.Color(18, 79, 123));
        btn_Delete.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        btn_Delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_Delete.setText("DELETE");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Print.setBackground(new java.awt.Color(18, 79, 123));
        btn_Print.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        btn_Print.setForeground(new java.awt.Color(255, 255, 255));
        btn_Print.setText("PRINT");
        btn_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PrintActionPerformed(evt);
            }
        });

        jpn_PrintNota.setBackground(new java.awt.Color(255, 255, 255));

        ket_tgl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ket_tgl.setText("Tanggal    :");

        isi_tgl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ket_noAntri.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ket_noAntri.setText("No Antri          : ");

        isi_noAntri.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ket_Nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ket_Nama.setText("Nama               :");

        isi_Nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Ket_JenisKelamin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ket_JenisKelamin.setText("Jenis Kelamin  :");

        isi_JenisKelamin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Ket_Telepon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ket_Telepon.setText("Telepon           :");

        isi_Telepon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Ket_Telepon1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ket_Telepon1.setText("Alamat             :");

        isi_Alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tbl_BarangBeli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama Barang", "Harga", "Jumlah", "Diskon", "Total"
            }
        ));
        tbl_BarangBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_BarangBeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_BarangBeli);

        isi_TotBayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        Ket_totbayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ket_totbayar.setText("Total Bayar");

        ket_Rp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ket_Rp.setText("Rp :");

        javax.swing.GroupLayout jpn_PrintNotaLayout = new javax.swing.GroupLayout(jpn_PrintNota);
        jpn_PrintNota.setLayout(jpn_PrintNotaLayout);
        jpn_PrintNotaLayout.setHorizontalGroup(
            jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_PrintNotaLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpn_PrintNotaLayout.createSequentialGroup()
                        .addComponent(Ket_totbayar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ket_Rp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(isi_TotBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpn_PrintNotaLayout.createSequentialGroup()
                            .addComponent(ket_tgl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(isi_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Ket_Telepon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Ket_JenisKelamin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ket_Nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Ket_Telepon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ket_noAntri))
                            .addGap(2, 2, 2)
                            .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(isi_noAntri, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(isi_Nama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(isi_JenisKelamin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(isi_Telepon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(isi_Alamat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(20, 20, 20))
        );
        jpn_PrintNotaLayout.setVerticalGroup(
            jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpn_PrintNotaLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ket_noAntri, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isi_noAntri, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(isi_tgl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ket_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ket_Nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(isi_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ket_JenisKelamin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isi_JenisKelamin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Ket_Telepon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isi_Telepon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Ket_Telepon1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isi_Alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpn_PrintNotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ket_Rp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Ket_totbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(isi_TotBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout Form_NotaBayarLayout = new javax.swing.GroupLayout(Form_NotaBayar);
        Form_NotaBayar.setLayout(Form_NotaBayarLayout);
        Form_NotaBayarLayout.setHorizontalGroup(
            Form_NotaBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form_NotaBayarLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(Form_NotaBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_TambahBrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jpn_PrintNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Form_NotaBayarLayout.setVerticalGroup(
            Form_NotaBayarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Form_NotaBayarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_TambahBrang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btn_Print, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
            .addGroup(Form_NotaBayarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpn_PrintNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Form_Menu.add(Form_NotaBayar, "card5");

        Form_Notice.setBackground(new java.awt.Color(18, 79, 123));
        Form_Notice.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Form_NoticeLayout = new javax.swing.GroupLayout(Form_Notice);
        Form_Notice.setLayout(Form_NoticeLayout);
        Form_NoticeLayout.setHorizontalGroup(
            Form_NoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        Form_NoticeLayout.setVerticalGroup(
            Form_NoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Form_Notice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Form_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Form_Notice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Form_Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_dispenserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dispenserActionPerformed
       txt_namaBarang.setText("DISPENSER CHANGHONG CMD X5");
       txt_hargabarang.setText("1401000");
       txt_diskonBarang.setText("101000");
       
        go_formdataBarang();
    }//GEN-LAST:event_bt_dispenserActionPerformed

    private void bt_dvdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dvdActionPerformed
       txt_namaBarang.setText("DVD Player Sony S-r370");
       txt_hargabarang.setText("704000");
       txt_diskonBarang.setText("100000");
       go_formdataBarang();
    }//GEN-LAST:event_bt_dvdActionPerformed

    private void rb_lakiActionPerforme(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_lakiActionPerforme
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_lakiActionPerforme

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txt_namaBarang.setText("MESIN CUCI SANKEN T87");
       txt_hargabarang.setText("1129000");
       txt_diskonBarang.setText("120000");
        go_formdataBarang();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_tvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tvActionPerformed
       txt_namaBarang.setText("SHRAP LC-LE1701 TV LED");
       txt_hargabarang.setText("1335000");
       txt_diskonBarang.setText("35000");
       go_formdataBarang();
    }//GEN-LAST:event_bt_tvActionPerformed

    private void bt_speakerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_speakerActionPerformed
       txt_namaBarang.setText("SPEAKER POLYTRON 9051");
       txt_hargabarang.setText("675000");
       txt_diskonBarang.setText("5000");
        go_formdataBarang();
    }//GEN-LAST:event_bt_speakerActionPerformed

    private void bt_kipasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_kipasActionPerformed
       txt_namaBarang.setText("KIPAS ANGIN TORNADO");
       txt_hargabarang.setText("120000");
       txt_diskonBarang.setText("0");
        go_formdataBarang();
    }//GEN-LAST:event_bt_kipasActionPerformed

    private void bt_laptopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_laptopActionPerformed
        txt_namaBarang.setText("ASUS ROG GL702VS");
       txt_hargabarang.setText("23450000");
       txt_diskonBarang.setText("450000");
        go_formdataBarang();
    }//GEN-LAST:event_bt_laptopActionPerformed

    private void bt_riceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_riceActionPerformed
       txt_namaBarang.setText("SHARP RICE COOKER");
       txt_hargabarang.setText("975000");
       txt_diskonBarang.setText("65000");
        go_formdataBarang();
    }//GEN-LAST:event_bt_riceActionPerformed

    private void btn_submitusrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitusrActionPerformed
        validasiPembeli();
        viewPembeli();
    }//GEN-LAST:event_btn_submitusrActionPerformed

    private void btn_submitbrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitbrngActionPerformed
        validasiBarang();
        viewBarang();
        hitungTotarbayar();
    }//GEN-LAST:event_btn_submitbrngActionPerformed

    private void rb_perempuan_editrb_lakiActionPerforme(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_perempuan_editrb_lakiActionPerforme
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_perempuan_editrb_lakiActionPerforme

    private void btn_submit_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submit_editActionPerformed
        validasiEditPembeli();
        viewPembeli();
    }//GEN-LAST:event_btn_submit_editActionPerformed

    private void txt_telepon_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telepon_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telepon_editActionPerformed

    private void btn_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PrintActionPerformed
        printNota(jpn_PrintNota);        
    }//GEN-LAST:event_btn_PrintActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        brng.DeleteBarang(ID);
        viewBarang();
        hitungTotarbayar();
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditActionPerformed
        go_editUser();
    }//GEN-LAST:event_btn_EditActionPerformed

    private void btn_TambahBrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TambahBrangActionPerformed
        go_menuBarang();
    }//GEN-LAST:event_btn_TambahBrangActionPerformed

    private void btn_resetbrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetbrngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_resetbrngActionPerformed

    private void btn_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_totalActionPerformed
              
        if (txt_jumlahbarang.getText().equals("")) {
            jlbl_ShowMasaagebrng.setText("Jumlah barang belum di tentukan, silahkan isi terlebih dahulu");
        } else {
            int harga = Integer.parseInt(txt_hargabarang.getText());
            int diskon = Integer.parseInt(txt_diskonBarang.getText());
            int jumlah = Integer.parseInt(txt_jumlahbarang.getText());  
            int totalharga = (harga - diskon) * jumlah;
            txt_totalharga.setText(String.valueOf(totalharga));
        }
    }//GEN-LAST:event_btn_totalActionPerformed

    private void txt_noAntriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_noAntriMouseClicked
        txt_noAntri.setText("");
    }//GEN-LAST:event_txt_noAntriMouseClicked

    private void txt_namauserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_namauserMouseClicked
        txt_namauser.setText("");
    }//GEN-LAST:event_txt_namauserMouseClicked

    private void txt_teleponuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_teleponuserMouseClicked
        txt_teleponuser.setText("");
    }//GEN-LAST:event_txt_teleponuserMouseClicked

    private void txt_alamatuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_alamatuserMouseClicked
        txt_alamatuser.setText("");
    }//GEN-LAST:event_txt_alamatuserMouseClicked

    private void txt_noAntriBrngMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_noAntriBrngMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noAntriBrngMouseClicked

    private void txt_nama_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nama_editMouseClicked
        txt_nama_edit.setText("");
    }//GEN-LAST:event_txt_nama_editMouseClicked

    private void txt_telepon_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_telepon_editMouseClicked
        txt_telepon_edit.setText("");
    }//GEN-LAST:event_txt_telepon_editMouseClicked

    private void txt_alamat_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_alamat_editMouseClicked
        txt_alamat_edit.setText("");
    }//GEN-LAST:event_txt_alamat_editMouseClicked

    private void tbl_BarangBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BarangBeliMouseClicked
        int row = tbl_BarangBeli.getSelectedRow();
        ID = Integer.parseInt(tbl_BarangBeli.getModel().getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbl_BarangBeliMouseClicked

    private void txt_jumlahbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_jumlahbarangMouseClicked
        txt_jumlahbarang.setText("");
    }//GEN-LAST:event_txt_jumlahbarangMouseClicked

    private void btn_resetusrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetusrActionPerformed
        txt_noAntri.setText("No Antri");
        txt_namauser.setText("Nama");
        txt_teleponuser.setText("Telepon");
        txt_alamatuser.setText("Alamat");
    }//GEN-LAST:event_btn_resetusrActionPerformed

    private void btn_reset_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reset_editActionPerformed
        txt_nama_edit.setText("Nama");
        txt_telepon_edit.setText("Telepon");
        txt_alamat_edit.setText("Alamat");
    }//GEN-LAST:event_btn_reset_editActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_MenuUtama().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Form_DataBarang;
    private javax.swing.JPanel Form_DataJumBarang;
    private javax.swing.JPanel Form_DataUser;
    private javax.swing.JPanel Form_EditDataUser;
    private javax.swing.JPanel Form_EditdataUser;
    private javax.swing.JPanel Form_Menu;
    private javax.swing.JPanel Form_MenuBarang;
    public javax.swing.JPanel Form_NotaBayar;
    private javax.swing.JPanel Form_Notice;
    private javax.swing.JPanel Form_dataUser;
    private javax.swing.JLabel Ket_JenisKelamin;
    private javax.swing.JLabel Ket_Telepon;
    private javax.swing.JLabel Ket_Telepon1;
    private javax.swing.JLabel Ket_totbayar;
    private javax.swing.JButton bt_dispenser;
    private javax.swing.JButton bt_dvd;
    private javax.swing.JButton bt_kipas;
    private javax.swing.JButton bt_laptop;
    private javax.swing.JButton bt_rice;
    private javax.swing.JButton bt_speaker;
    private javax.swing.JButton bt_tv;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_TambahBrang;
    private javax.swing.JButton btn_reset_edit;
    private javax.swing.JButton btn_resetbrng;
    private javax.swing.JButton btn_resetusr;
    private javax.swing.JButton btn_submit_edit;
    private javax.swing.JButton btn_submitbrng;
    private javax.swing.JButton btn_submitusr;
    private javax.swing.JButton btn_total;
    private javax.swing.JLabel isi_Alamat;
    private javax.swing.JLabel isi_JenisKelamin;
    private javax.swing.JLabel isi_Nama;
    private javax.swing.JLabel isi_Telepon;
    private javax.swing.JLabel isi_TotBayar;
    private javax.swing.JLabel isi_noAntri;
    private javax.swing.JLabel isi_tgl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jbl_harga;
    private javax.swing.JLabel jbl_merek;
    private javax.swing.JLabel jbl_merekkipas;
    private javax.swing.JLabel jlb_dispenser;
    private javax.swing.JLabel jlb_dvd;
    private javax.swing.JLabel jlb_hagalaptop;
    private javax.swing.JLabel jlb_hargadispenser;
    private javax.swing.JLabel jlb_hargakipas;
    private javax.swing.JLabel jlb_hargarice;
    private javax.swing.JLabel jlb_hargaspeaker;
    private javax.swing.JLabel jlb_hargatv;
    private javax.swing.JLabel jlb_kipas;
    private javax.swing.JLabel jlb_laptop;
    private javax.swing.JLabel jlb_merekdispenser;
    private javax.swing.JLabel jlb_merekrice;
    private javax.swing.JLabel jlb_merekspeaker;
    private javax.swing.JLabel jlb_merektv;
    private javax.swing.JLabel jlb_ricecooker;
    private javax.swing.JLabel jlb_speaker;
    private javax.swing.JLabel jlb_tv;
    private javax.swing.JLabel jlbl_Jklamin;
    private javax.swing.JLabel jlbl_Jklamin_edit;
    private javax.swing.JLabel jlbl_ShowMasaagebrng;
    private javax.swing.JLabel jlbl_ShowMassage_edit;
    private javax.swing.JLabel jlbl_ShowMassageusr;
    private javax.swing.JLabel jlbl_diskonBarang;
    private javax.swing.JLabel jlbl_hargaBarang;
    private javax.swing.JLabel jlbl_icon_edit;
    private javax.swing.JLabel jlbl_iconbarang1;
    private javax.swing.JLabel jlbl_iconbarang2;
    private javax.swing.JLabel jlbl_iconbarang3;
    private javax.swing.JLabel jlbl_iconuser;
    private javax.swing.JLabel jlbl_jumlahBarang;
    private javax.swing.JLabel jlbl_namaBarang;
    private javax.swing.JLabel jlbl_noAntriBarang;
    private javax.swing.JLabel jlbl_salam;
    private javax.swing.JLabel jlbl_silInputdata;
    private javax.swing.JPanel jpn_PrintNota;
    private javax.swing.JSeparator js_alamat_edit;
    private javax.swing.JSeparator js_alamatuser;
    private javax.swing.JSeparator js_diskonBarang;
    private javax.swing.JSeparator js_hargabarang;
    private javax.swing.JSeparator js_jumlahbarang;
    private javax.swing.JSeparator js_nama_edit;
    private javax.swing.JSeparator js_namabarang;
    private javax.swing.JSeparator js_namauser;
    private javax.swing.JSeparator js_noAntri;
    private javax.swing.JSeparator js_noAntri1;
    private javax.swing.JSeparator js_noAntri_Edit;
    private javax.swing.JSeparator js_telepon_edit;
    private javax.swing.JSeparator js_teleponuser;
    private javax.swing.JSeparator js_totalharga;
    private javax.swing.JLabel ket_Nama;
    private javax.swing.JLabel ket_Rp;
    private javax.swing.JLabel ket_noAntri;
    private javax.swing.JLabel ket_tgl;
    private javax.swing.JRadioButton rb_laki;
    private javax.swing.JRadioButton rb_laki_edit;
    private javax.swing.JRadioButton rb_perempuan;
    private javax.swing.JRadioButton rb_perempuan_edit;
    public javax.swing.ButtonGroup rbg_jeniskelamin;
    public javax.swing.JTable tbl_BarangBeli;
    private javax.swing.JTextField txt_alamat_edit;
    private javax.swing.JTextField txt_alamatuser;
    private javax.swing.JTextField txt_diskonBarang;
    private javax.swing.JTextField txt_hargabarang;
    private javax.swing.JTextField txt_jumlahbarang;
    private javax.swing.JTextField txt_namaBarang;
    private javax.swing.JTextField txt_nama_edit;
    private javax.swing.JTextField txt_namauser;
    private javax.swing.JTextField txt_noAntri;
    private javax.swing.JTextField txt_noAntriBrng;
    private javax.swing.JTextField txt_noAntri_Edit;
    private javax.swing.JTextField txt_telepon_edit;
    private javax.swing.JTextField txt_teleponuser;
    private javax.swing.JTextField txt_totalharga;
    // End of variables declaration//GEN-END:variables
}
