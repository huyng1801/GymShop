/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.NhanVienModel;
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
public class NhanVienController {
     public List<NhanVienModel> getListNhanVien() {
        List<NhanVienModel> nhanVienList = new ArrayList<>();
        KetNoi data = new KetNoi();
        try {
            Connection connection = data.getConnect();
            // Tạo câu truy vấn SQL để lấy danh sách nhân viên
            String query = "SELECT * FROM NhanVien";

            // Thực hiện truy vấn
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Duyệt qua từng dòng dữ liệu và tạo đối tượng NhanVienModel
            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("MaNhanVien");
                String hoTen = resultSet.getString("HoTen");
                String diaChi = resultSet.getString("DiaChi");
                String soDienThoai = resultSet.getString("SoDienThoai");
                String tenDangNhap = resultSet.getString("TenDangNhap");
                NhanVienModel nhanVien = new NhanVienModel(maNhanVien, hoTen, diaChi, soDienThoai, tenDangNhap);
                nhanVienList.add(nhanVien);
            }

            // Đóng kết nối và các đối tượng liên quan
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
        return nhanVienList;
    }

    public String getMaNhanVien() {
    String maNhanVien = null;
    KetNoi data = new KetNoi();
    try {
        // Kết nối database và tạo câu truy vấn
        Connection conn = data.getConnect();
        String query = "SELECT dbo.GenerateMaNhanVien() AS MaNhanVien";

        // Thực hiện truy vấn
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Lấy mã sản phẩm từ kết quả truy vấn
        if (resultSet.next()) {
            maNhanVien = resultSet.getString("MaNhanVien");
        }

        // Đóng kết nối và các đối tượng liên quan
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        data.getClose();
    }

    return maNhanVien;
}
    public void themNhanVien(NhanVienModel nhanVien) {
         KetNoi data = new KetNoi();
        try {
            // Tạo câu truy vấn SQL để thêm nhân viên
            String query = "INSERT INTO NhanVien (MaNhanVien, HoTen, DiaChi, SoDienThoai, TenDangNhap) VALUES (?, ?, ?, ?, ?)";

            // Chuẩn bị câu lệnh PreparedStatement
            Connection connection = data.getConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nhanVien.getMaNhanVien());
            statement.setString(2, nhanVien.getHoTen());
            statement.setString(3, nhanVien.getDiaChi());
            statement.setString(4, nhanVien.getSoDienThoai());
            statement.setString(5, nhanVien.getTenDangNhap());

            // Thực thi câu truy vấn
            statement.executeUpdate();

            // Đóng kết nối và câu lệnh PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
    }

    public void suaNhanVien(NhanVienModel nhanVien) {
         KetNoi data = new KetNoi();
        try {
            Connection connection = data.getConnect();
            // Tạo câu truy vấn SQL để sửa thông tin nhân viên
            String query = "UPDATE NhanVien SET HoTen = ?, DiaChi = ?, SoDienThoai = ?, TenDangNhap = ? WHERE MaNhanVien = ?";

            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nhanVien.getHoTen());
            statement.setString(2, nhanVien.getDiaChi());
            statement.setString(3, nhanVien.getSoDienThoai());
            statement.setString(4, nhanVien.getTenDangNhap());
            statement.setString(5, nhanVien.getMaNhanVien());

            // Thực thi câu truy vấn
            statement.executeUpdate();

            // Đóng kết nối và câu lệnh PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
    }

    public void xoaNhanVien(String maNhanVien) {
         KetNoi data = new KetNoi();
        try {
            Connection connection = data.getConnect();
            // Tạo câu truy vấn SQL để xóa nhân viên
            String query = "DELETE FROM NhanVien WHERE MaNhanVien = ?";

            // Chuẩn bị câu lệnh PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maNhanVien);

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
