/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Controller.*;
import Model.SanPhamModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 *
 * @author huyng
 */
 class CircularImageLabel extends JLabel {
    
    private ImageIcon imageIcon;
    
    public CircularImageLabel() {
        super();
    }
    
    public void setCircularImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Vẽ hình ảnh theo hình tròn
        if (imageIcon != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            
            // Vẽ hình tròn
            int width = getWidth();
            int height = getHeight();
            Ellipse2D circle = new Ellipse2D.Double(0, 0, width, height);
            g2d.setClip(circle);
            
            // Vẽ hình ảnh trong hình tròn
            imageIcon.paintIcon(this, g2d, 0, 0);
            
            g2d.dispose();
        }
    }
}
class CustomMenuBarUI extends BasicMenuBarUI {
    @Override
    public void installDefaults() {
        super.installDefaults();
        menuBar.setBackground(new Color(68, 71, 91)); // Đặt màu nền cho JMenuBar
    }
}
public class GiaoDienChinh extends javax.swing.JFrame {

    /**
     * Creates new form GiaoDienChinh
     */
    public String loaiTaiKhoan, ma, hoTen, diaChi, soDienThoai, tenDangNhap;
    
    public void setForground(){
        Color textColor = new Color(248, 248, 242); // Màu chữ
        this.jMenu1.setForeground(textColor);
        this.jMenu2.setForeground(textColor);
        this.jMenu3.setForeground(textColor);
    }
    public GiaoDienChinh() {
        initComponents();
        CanGiua();
        datThongTin();
        this.jMenuBar1.setUI(new CustomMenuBarUI());
        setForground();
    }
    
    public GiaoDienChinh(String loaiTaiKhoan, String ma, String hoTen, String diaChi, String soDienThoai, String tenDangNhap) {
        initComponents();
        this.loaiTaiKhoan = loaiTaiKhoan;
        if("KhachHang".equals(loaiTaiKhoan))
            this.jMenu2.setVisible(false);
        else if("NhanVien".equals(loaiTaiKhoan))
            this.jMenu3.setVisible(false);
        this.ma = ma;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.tenDangNhap = tenDangNhap;
        CanGiua();
        datThongTin();
        setForground();
        this.jMenuBar1.setUI(new CustomMenuBarUI());
    }
    
    public void datThongTin(){
        this.jLLoaiTaiKhoan.setText(loaiTaiKhoan);
        this.jLHoTen.setText(hoTen);
         CircularImageLabel label = new CircularImageLabel();
        label.setBounds(50, 50, 100, 100); // Đặt vị trí và kích thước của JLabel
        this.jPThongTin.add(label);
        ImageIcon imageIcon = new ImageIcon("D:\\Project\\Java\\GymShop\\src\\main\\java\\Images\\huynguyen.jpg");
        Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
          ImageIcon imageIcon2 = new ImageIcon("D:\\Project\\Java\\GymShop\\src\\main\\java\\Images\\trangchu.jpg");
        Image scaledImage2 = imageIcon2.getImage().getScaledInstance(740, 380, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon2 = new ImageIcon(scaledImage2);
        this.jLTrangChu.setIcon(scaledImageIcon2);
        label.setCircularImageIcon(scaledImageIcon);
    }
        public void CanGiua(){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPThongTin = new javax.swing.JPanel();
        jLHoTen = new javax.swing.JLabel();
        jLLoaiTaiKhoan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLTrangChu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLTitle = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemTrangChu = new javax.swing.JMenuItem();
        jMenuItemDangXuat = new javax.swing.JMenuItem();
        jMenuItemDoiMatKhau = new javax.swing.JMenuItem();
        jMenuItemThoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMISanPham = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMIMuaHang = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem10.setText("jMenuItem10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(248, 248, 242));

        jPThongTin.setBackground(new java.awt.Color(98, 114, 164));

        jLHoTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLHoTen.setForeground(new java.awt.Color(248, 248, 242));
        jLHoTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLHoTen.setText("Họ tên");
        jLHoTen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLLoaiTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLLoaiTaiKhoan.setForeground(new java.awt.Color(248, 248, 242));
        jLLoaiTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLLoaiTaiKhoan.setText("Loại tài khoản");
        jLLoaiTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPThongTinLayout = new javax.swing.GroupLayout(jPThongTin);
        jPThongTin.setLayout(jPThongTinLayout);
        jPThongTinLayout.setHorizontalGroup(
            jPThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
            .addGroup(jPThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLLoaiTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
        );
        jPThongTinLayout.setVerticalGroup(
            jPThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPThongTinLayout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLHoTen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPThongTinLayout.createSequentialGroup()
                    .addGap(162, 162, 162)
                    .addComponent(jLLoaiTaiKhoan)
                    .addContainerGap(326, Short.MAX_VALUE)))
        );

        jLTrangChu.setBackground(new java.awt.Color(98, 114, 164));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(139, 233, 253));

        jLTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLTitle.setText("TRANG CHỦ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLTitle)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jPThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(40, 42, 54));

        jMenu1.setText("Tùy chọn");

        jMenuItemTrangChu.setText("Trang chủ");
        jMenuItemTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTrangChuActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemTrangChu);

        jMenuItemDangXuat.setText("Đăng xuất");
        jMenuItemDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDangXuat);

        jMenuItemDoiMatKhau.setText("Đổi mật khẩu");
        jMenuItemDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDoiMatKhauActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDoiMatKhau);

        jMenuItemThoat.setText("Thoát");
        jMenuItemThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemThoatActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemThoat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý hệ thống");

        jMISanPham.setText("Sản phẩm");
        jMISanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMISanPhamActionPerformed(evt);
            }
        });
        jMenu2.add(jMISanPham);

        jMenuItem7.setText("Nhân viên");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Khách hàng");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Hóa đơn");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Giao dịch");

        jMIMuaHang.setText("Mua hàng");
        jMIMuaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIMuaHangActionPerformed(evt);
            }
        });
        jMenu3.add(jMIMuaHang);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private MuaHang muaHangPanel;
    private void jMIMuaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIMuaHangActionPerformed
        // TODO add your handling code here:
        this.jLTitle.setText("MUA HÀNG");
muaHangPanel = new MuaHang(ma);
        this.muaHangPanel.setVisible(true);
        this.jPanel2.setLayout(new FlowLayout());
        this.jPanel2.removeAll();
        this.jPanel2.add(muaHangPanel, BorderLayout.CENTER);
        this.jPanel2.updateUI();

        // Cập nhật lại giao diện
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jMIMuaHangActionPerformed
private SanPham sanPhamPanel;
    private void jMISanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMISanPhamActionPerformed
        // TODO add your handling code here:
        this.jLTitle.setText("SẢN PHẨM");
        sanPhamPanel = new SanPham();
        this.sanPhamPanel.setVisible(true);
        this.jPanel2.setLayout(new FlowLayout());
        this.jPanel2.removeAll();
        this.jPanel2.add(sanPhamPanel, BorderLayout.CENTER);
        this.jPanel2.updateUI();

        // Cập nhật lại giao diện
        jPanel2.revalidate();
        jPanel2.repaint();

    }//GEN-LAST:event_jMISanPhamActionPerformed
private NhanVien nhanvienPanel;
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        this.jLTitle.setText("NHÂN VIÊN");
        nhanvienPanel = new NhanVien();
        this.nhanvienPanel.setVisible(true);
        this.jPanel2.setLayout(new FlowLayout());
        this.jPanel2.removeAll();
        this.jPanel2.add(nhanvienPanel, BorderLayout.CENTER);
        this.jPanel2.updateUI();

        // Cập nhật lại giao diện
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jMenuItem7ActionPerformed
private KhachHang khachhangPanel;
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        this.jLTitle.setText("NHÂN VIÊN");
                khachhangPanel = new KhachHang();
        this.khachhangPanel.setVisible(true);
        this.jPanel2.setLayout(new FlowLayout());
        this.jPanel2.removeAll();
        this.jPanel2.add(khachhangPanel, BorderLayout.CENTER);
        this.jPanel2.updateUI();

        // Cập nhật lại giao diện
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItemThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemThoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItemThoatActionPerformed

    private void jMenuItemDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDangXuatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_jMenuItemDangXuatActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        this.jLTitle.setText("HÓA ĐƠN");
        jPanel2.removeAll();
        hoadonPanel = new HoaDon(ma);
        this.hoadonPanel.setVisible(true);
        this.jPanel2.setLayout(new FlowLayout());
          this.jPanel2.add(hoadonPanel, BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jMenuItem9ActionPerformed
HoaDon hoadonPanel;
    private void jMenuItemTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTrangChuActionPerformed
        // TODO add your handling code here:
        this.jLTitle.setText("TRANG CHỦ");
        jPanel2.removeAll();
        this.jPanel2.setLayout(new FlowLayout());
        ImageIcon imageIcon2 = new ImageIcon("D:\\Project\\Java\\GymShop\\src\\main\\java\\Images\\trangchu.jpg");
        Image scaledImage2 = imageIcon2.getImage().getScaledInstance(740, 380, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon2 = new ImageIcon(scaledImage2);
        JLabel lb = new JLabel(scaledImageIcon2);
        jPanel2.add(lb);
        lb.setBounds(0, 0, 740, 380);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_jMenuItemTrangChuActionPerformed

    private void jMenuItemDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDoiMatKhauActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new  DoiMatKhau(tenDangNhap).setVisible(true);
    }//GEN-LAST:event_jMenuItemDoiMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLHoTen;
    private javax.swing.JLabel jLLoaiTaiKhoan;
    private javax.swing.JLabel jLTitle;
    private javax.swing.JLabel jLTrangChu;
    private javax.swing.JMenuItem jMIMuaHang;
    private javax.swing.JMenuItem jMISanPham;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemDangXuat;
    private javax.swing.JMenuItem jMenuItemDoiMatKhau;
    private javax.swing.JMenuItem jMenuItemThoat;
    private javax.swing.JMenuItem jMenuItemTrangChu;
    private javax.swing.JPanel jPThongTin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
