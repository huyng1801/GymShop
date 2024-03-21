/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author huyng
 */
public class SanPhamController {
    public List<SanPhamModel> getListSanPham() {
        List<SanPhamModel> sanPhamList = new ArrayList<>();
        KetNoi data=new KetNoi();
        try {
            Connection conn = data.getConnect();
            // Tạo câu truy vấn SQL để lấy danh sách sản phẩm
            String query = "SELECT * FROM SanPham";

            // Thực hiện truy vấn
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Duyệt qua từng dòng dữ liệu và tạo đối tượng SanPham
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                double gia = resultSet.getDouble("Gia");
                int soLuong = resultSet.getInt("SoLuong");
                String maDanhMuc = resultSet.getString("TenDanhMuc");
                byte[] anh = resultSet.getBytes("Anh");

                SanPhamModel sanPham = new SanPhamModel(maSanPham, tenSanPham, gia, soLuong, maDanhMuc, anh);
                sanPhamList.add(sanPham);
            }

            // Đóng kết nối và các đối tượng liên quan
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }

        return sanPhamList;
    }
     public void xoaSanPham(String maSanPham) {
        KetNoi data = new KetNoi();
        try {
            Connection conn = data.getConnect();
            // Tạo câu truy vấn SQL để xóa sản phẩm với mã sản phẩm tương ứng
            String query = "DELETE FROM SanPham WHERE MaSanPham = ?";

            // Tạo prepared statement và thiết lập giá trị cho tham số
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, maSanPham);

            // Thực hiện truy vấn
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Xóa sản phẩm thành công");
            } else {
                System.out.println("Không tìm thấy sản phẩm để xóa");
            }

            // Đóng kết nối và các đối tượng liên quan
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }
    }
    public void suaSanPham(SanPhamModel sp) {
    // Kết nối đến cơ sở dữ liệu
    KetNoi data = new KetNoi();
    Connection conn = data.getConnect();

    try {
        // Tạo câu lệnh SQL để cập nhật thông tin sản phẩm
        String query = "UPDATE SanPham SET TenSanPham = ?, Gia = ?, SoLuong = ?, TenDanhMuc = ?, Anh = ? WHERE MaSanPham = ?";

        // Tạo PreparedStatement và thiết lập các giá trị tham số
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, sp.getTenSanPham());
        statement.setDouble(2, sp.getGia());
        statement.setInt(3, sp.getSoLuong());
        statement.setString(4, sp.getTenDanhMuc());
        statement.setBytes(5, sp.getAnh());
        statement.setString(6, sp.getMaSanPham());

        // Thực hiện câu lệnh SQL
        statement.executeUpdate();

        // Đóng kết nối và các đối tượng liên quan
        statement.close();
        data.getClose();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public String getMaSanPham() {
    String maSanPham = null;
    KetNoi data = new KetNoi();
    try {
        // Kết nối database và tạo câu truy vấn
        Connection conn = data.getConnect();
        String query = "SELECT dbo.GenerateMaSanPham() AS MaSanPham";

        // Thực hiện truy vấn
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Lấy mã sản phẩm từ kết quả truy vấn
        if (resultSet.next()) {
            maSanPham = resultSet.getString("MaSanPham");
        }

        // Đóng kết nối và các đối tượng liên quan
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        data.getClose();
    }

    return maSanPham;
}

    public boolean themSanPham(SanPhamModel sanPham) {
        KetNoi data = new KetNoi();
        try {
            Connection conn = data.getConnect();

            // Tạo câu truy vấn INSERT
            String query = "INSERT INTO SanPham (MaSanPham, TenSanPham, Gia, SoLuong, TenDanhMuc, Anh) VALUES (?, ?, ?, ?, ?, ?)";

            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, sanPham.getMaSanPham());
            statement.setString(2, sanPham.getTenSanPham());
            statement.setDouble(3, sanPham.getGia());
            statement.setInt(4, sanPham.getSoLuong());
            statement.setString(5, sanPham.getTenDanhMuc());
            statement.setBytes(6, sanPham.getAnh());

            // Thực hiện truy vấn INSERT
            int rowsAffected = statement.executeUpdate();

            // Đóng kết nối và các đối tượng liên quan
            statement.close();
            data.getClose();

            // Kiểm tra số dòng bị ảnh hưởng để xác định thành công hay không
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        finally{
        data.getClose();
        }
        return false;
    }
    public double getDonGiaByMaSanPham(String maSanPham) {
    KetNoi data = new KetNoi();
    double donGia = 0;
    try {
        Connection conn = data.getConnect();

        // Tạo câu truy vấn SQL để lấy đơn giá từ mã sản phẩm
        String query = "SELECT dbo.GetDonGiaByMaSanPham(?) AS DonGia";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, maSanPham);

        // Thực thi stored procedure
        ResultSet resultSet = statement.executeQuery();

        // Xử lý kết quả trả về
        if (resultSet.next()) {
            donGia = resultSet.getFloat(1);
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }

        // Đóng kết nối và các đối tượng liên quan
        resultSet.close();
        statement.close();
        data.getClose();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return donGia;
}

}
