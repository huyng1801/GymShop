/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HoaDonModel;
import java.sql.Connection;
import java.sql.Date;
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
public class HoaDonController {
    public String getMaHoaDon() {
    String maHoaDon = null;
    KetNoi data = new KetNoi();
    try {
        // Kết nối database và tạo câu truy vấn
        Connection conn = data.getConnect();
        String query = "SELECT dbo.GenerateMaHoaDon() AS MaHoaDon";

        // Thực hiện truy vấn
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Lấy mã sản phẩm từ kết quả truy vấn
        if (resultSet.next()) {
            maHoaDon = resultSet.getString("MaHoaDon");
        }

        // Đóng kết nối và các đối tượng liên quan
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        data.getClose();
    }

    return maHoaDon;
}
        public void themHoaDon(HoaDonModel hd) {
        KetNoi data = new KetNoi();
        try {
            // Kết nối database và tạo câu truy vấn
            Connection conn = data.getConnect();
            String query = "INSERT INTO HoaDon (MaHoaDon, NgayLap, MaKhachHang) VALUES (?, ?, ?)";
java.util.Date utilDate = hd.getNgayLap();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Tạo prepared statement và thiết lập giá trị tham số
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hd.getMaHoaDon());
            statement.setDate(2, sqlDate);
            statement.setString(3, hd.getMaKhachHang());

            // Thực hiện câu truy vấn INSERT
            statement.executeUpdate();

            // Đóng kết nối và các đối tượng liên quan
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }
    }
         public List<HoaDonModel> getDanhSachHoaDon() {
        List<HoaDonModel> danhSachHoaDon = new ArrayList<>();
        KetNoi data = new KetNoi();
        try {
            // Kết nối database và tạo câu truy vấn
            Connection conn = data.getConnect();
            String query = "SELECT * FROM HoaDon";

            // Tạo prepared statement
            PreparedStatement statement = conn.prepareStatement(query);

            // Thực hiện truy vấn SELECT
            ResultSet resultSet = statement.executeQuery();

            // Lặp qua kết quả truy vấn và tạo đối tượng HoaDonModel từ mỗi hàng
            while (resultSet.next()) {
                HoaDonModel hoaDon = new HoaDonModel();
                hoaDon.setMaHoaDon(resultSet.getString("MaHoaDon"));
                hoaDon.setNgayLap(resultSet.getDate("NgayLap"));
                hoaDon.setTongTien(resultSet.getFloat("TongTien"));
                hoaDon.setMaKhachHang(resultSet.getString("MaKhachHang"));
                hoaDon.setMaNhanVien(resultSet.getString("MaNhanVien"));
                danhSachHoaDon.add(hoaDon);
            }

            // Đóng kết nối và các đối tượng liên quan
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }

        return danhSachHoaDon;
    }
         public void suaHoaDon(HoaDonModel hoaDon) {
        KetNoi data = new KetNoi();
        try {
            // Kết nối database và tạo câu truy vấn UPDATE
            Connection conn = data.getConnect();
            String query = "UPDATE HoaDon SET MaNhanVien = ? WHERE MaHoaDon = ?";

            // Tạo prepared statement và thiết lập giá trị tham số
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, hoaDon.getMaNhanVien());
            statement.setString(2, hoaDon.getMaHoaDon());

            // Thực hiện câu truy vấn UPDATE
            statement.executeUpdate();

            // Đóng kết nối và các đối tượng liên quan
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }
    }
         public void xoaHoaDon(String maHoaDon) {
        KetNoi data = new KetNoi();
        try {
            // Kết nối database và tạo câu truy vấn DELETE
            Connection conn = data.getConnect();
            String query = "DELETE FROM HoaDon WHERE MaHoaDon = ?";

            // Tạo prepared statement và thiết lập giá trị tham số
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, maHoaDon);

            // Thực hiện câu truy vấn DELETE
            statement.executeUpdate();

            // Đóng kết nối và các đối tượng liên quan
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }
    }
}







