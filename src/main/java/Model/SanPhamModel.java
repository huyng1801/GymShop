/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author huyng
 */
public class SanPhamModel {
    private String maSanPham;
    private String tenSanPham;
    private double gia;
    private int soLuong;
    private String tenDanhMuc;
    private byte[] anh;
    public SanPhamModel(){}
    public SanPhamModel(String maSanPham, String tenSanPham, double gia, int soLuong, String tenDanhMuc, byte[] anh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
        this.tenDanhMuc = tenDanhMuc;
        this.anh = anh;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public double getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public byte[] getAnh() {
        return anh;
    }
    
    // Các setter và các phương thức khác (nếu cần)
}

