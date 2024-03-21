/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Controller.NhanVienController;
import Controller.SanPhamController;
import Model.NhanVienModel;
import Model.SanPhamModel;
import java.awt.Component;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author huyng
 */

public class NhanVien extends javax.swing.JPanel {

    /**
     * Creates new form SanPham
     */
    public NhanVien() {
        initComponents();
        hienThiNhanVien();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTNhanVien = new javax.swing.JTable();
        jBSua = new javax.swing.JButton();
        jBThem = new javax.swing.JButton();
        jBXoa = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        jTNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Mã danh mục"
            }
        ));
        jScrollPane1.setViewportView(jTNhanVien);

        jBSua.setText("Sửa");
        jBSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSuaActionPerformed(evt);
            }
        });

        jBThem.setText("Thêm");
        jBThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBThemActionPerformed(evt);
            }
        });

        jBXoa.setText("Xóa");
        jBXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBThem)
                        .addGap(18, 18, 18)
                        .addComponent(jBSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBXoa))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBThem)
                    .addComponent(jBSua)
                    .addComponent(jBXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private ThemSuaNhanVien tsnv;
    private void jBThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBThemActionPerformed
        // TODO add your handling code here:
        tsnv = new ThemSuaNhanVien(this);
        tsnv.setVisible(true);
        
    }//GEN-LAST:event_jBThemActionPerformed

    private void jBSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSuaActionPerformed
        // TODO add your handling code here:
        int selectedRow = this.jTNhanVien.getSelectedRow();

        // Kiểm tra nếu có hàng được chọn
        if (selectedRow != -1) {
            // Lấy dữ liệu ảnh từ hàng đã chọn
            String maNhanVien =  jTNhanVien.getValueAt(selectedRow, 0).toString(); 
            String tenNhanVien =  jTNhanVien.getValueAt(selectedRow, 1).toString(); 
            String diaChi= jTNhanVien.getValueAt(selectedRow, 2).toString(); 
            String soDienThoai = jTNhanVien.getValueAt(selectedRow, 3).toString(); 
            String tenDangNhap =  jTNhanVien.getValueAt(selectedRow, 4).toString(); 
            NhanVienModel nv = new NhanVienModel(maNhanVien, tenNhanVien, diaChi, soDienThoai, tenDangNhap);
            // Kiểm tra nếu dữ liệu ảnh không null
                            tsnv = new ThemSuaNhanVien(nv, this);
tsnv.setVisible(true);
            
        }
        else{
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!");
        }
    }//GEN-LAST:event_jBSuaActionPerformed
NhanVienController nvctl;
    private void jBXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBXoaActionPerformed
        // TODO add your handling code here:
int selectedRow = this.jTNhanVien.getSelectedRow();

// Kiểm tra nếu có hàng được chọn
if (selectedRow != -1) {
    // Lấy dữ liệu ảnh từ hàng đã chọn
    String maNhanVien = jTNhanVien.getValueAt(selectedRow, 0).toString();

    // Hiển thị JOptionPane xác nhận xóa
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
    
    // Kiểm tra lựa chọn của người dùng
    if (confirm == JOptionPane.YES_OPTION) {
        nvctl = new NhanVienController();
        nvctl.xoaNhanVien(maNhanVien);
        JOptionPane.showMessageDialog(this, "Xóa thành công!");
        this.hienThiNhanVien();
    }
} else {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
}

    }//GEN-LAST:event_jBXoaActionPerformed

    public void hienThiNhanVien(){
    // Tạo mô hình dữ liệu cho jTSanPham
        DefaultTableModel tableModel = (DefaultTableModel) this.jTNhanVien.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Mã nhân viên");
        tableModel.addColumn("Tên nhân viên");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Tên đăng nhập");
        
         nvctl = new NhanVienController();
        // Gọi phương thức getListSanPham() để lấy danh sách sản phẩm
        List<NhanVienModel> nhanVienList = nvctl.getListNhanVien();

        // Duyệt qua danh sách sản phẩm và thêm từng sản phẩm vào mô hình dữ liệu
        for (NhanVienModel nhanVien : nhanVienList) {
            Object[] rowData = {
                nhanVien.getMaNhanVien(),
                nhanVien.getHoTen(),
                nhanVien.getDiaChi(),
                nhanVien.getSoDienThoai(),
                nhanVien.getTenDangNhap(),
            };
            tableModel.addRow(rowData);
            
}
// Đặt mô hình dữ liệu cho jTSanPham
jTNhanVien.setModel(tableModel);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSua;
    private javax.swing.JButton jBThem;
    private javax.swing.JButton jBXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTNhanVien;
    // End of variables declaration//GEN-END:variables
}