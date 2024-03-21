/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KhachHangModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyng
 */
public class KhachHangController {
         public List<KhachHangModel> getListKhachHang() {
        List<KhachHangModel> khachHangList = new ArrayList<>();
        KetNoi data = new KetNoi();
        try {
            Connection connection = data.getConnect();
            // Tạo câu truy vấn SQL để lấy danh sách nhân viên
            String query = "SELECT * FROM KhachHang";

            // Thực hiện truy vấn
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Duyệt qua từng dòng dữ liệu và tạo đối tượng KhachHangModel
            while (resultSet.next()) {
                String maKhachHang = resultSet.getString("MaKhachHang");
                String hoTen = resultSet.getString("HoTen");
                String diaChi = resultSet.getString("DiaChi");
                String soDienThoai = resultSet.getString("SoDienThoai");
                String tenDangNhap = resultSet.getString("TenDangNhap");
                KhachHangModel khachHang = new KhachHangModel(maKhachHang, hoTen, diaChi, soDienThoai, tenDangNhap);
                khachHangList.add(khachHang);
            }

            // Đóng kết nối và các đối tượng liên quan
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
        return khachHangList;
    }
public String getMaKhachHang() {
    String maKhachHang = null;
    KetNoi data = new KetNoi();
    try {
        // Kết nối database và tạo câu truy vấn
        Connection conn = data.getConnect();
        String query = "SELECT dbo.GenerateMaKhachHang() AS MaKhachHang";

        // Thực hiện truy vấn
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Lấy mã sản phẩm từ kết quả truy vấn
        if (resultSet.next()) {
            maKhachHang = resultSet.getString("MaKhachHang");
        }

        // Đóng kết nối và các đối tượng liên quan
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        data.getClose();
    }

    return maKhachHang;
}
    public void themKhachHang(KhachHangModel khachHang) {
         KetNoi data = new KetNoi();
        try {
            // Tạo câu truy vấn SQL để thêm nhân viên
            String query = "INSERT INTO KhachHang (MaKhachHang, HoTen, DiaChi, SoDienThoai, TenDangNhap) VALUES (?, ?, ?, ?, ?)";

            // Chuẩn bị câu lệnh PreparedStatement
            Connection connection = data.getConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, khachHang.getMaKhachHang());
            statement.setString(2, khachHang.getHoTen());
            statement.setString(3, khachHang.getDiaChi());
            statement.setString(4, khachHang.getSoDienThoai());
            statement.setString(5, khachHang.getTenDangNhap());

            // Thực thi câu truy vấn
            statement.executeUpdate();

            // Đóng kết nối và câu lệnh PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
    }

    public void suaKhachHang(KhachHangModel khachHang) {
         KetNoi data = new KetNoi();
        try {
            Connection connection = data.getConnect();
            // Tạo câu truy vấn SQL để sửa thông tin nhân viên
            String query = "UPDATE KhachHang SET HoTen = ?, DiaChi = ?, SoDienThoai = ?, TenDangNhap = ? WHERE MaKhachHang = ?";

            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, khachHang.getHoTen());
            statement.setString(2, khachHang.getDiaChi());
            statement.setString(3, khachHang.getSoDienThoai());
            statement.setString(4, khachHang.getTenDangNhap());
            statement.setString(5, khachHang.getMaKhachHang());

            // Thực thi câu truy vấn
            statement.executeUpdate();

            // Đóng kết nối và câu lệnh PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
    }

    public void xoaKhachHang(String maKhachHang) {
         KetNoi data = new KetNoi();
        try {
            Connection connection = data.getConnect();
            // Tạo câu truy vấn SQL để xóa nhân viên
            String query = "DELETE FROM KhachHang WHERE MaKhachHang = ?";

            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maKhachHang);

            // Thực thi câu truy vấn
            statement.executeUpdate();

            // Đóng kết nối và câu lệnh PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
    }
}
