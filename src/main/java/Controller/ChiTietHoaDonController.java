/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHoaDonModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author huyng
 */
public class ChiTietHoaDonController {
    public void themChiTietHoaDon(ChiTietHoaDonModel chiTietHoaDon) {
    KetNoi data = new KetNoi();
    try {
        Connection conn = data.getConnect();

        // Tạo câu truy vấn INSERT
        String query = "INSERT INTO ChiTietHoaDon (SoLuong, MaHoaDon, MaSanPham) VALUES (?, ?, ?)";

        // Chuẩn bị câu lệnh PreparedStatement
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, chiTietHoaDon.getSoLuong());
        statement.setString(2, chiTietHoaDon.getMaHoaDon());
        statement.setString(3, chiTietHoaDon.getMaSanPham());

        // Thực hiện truy vấn INSERT
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Thêm chi tiết hóa đơn thành công");
        } else {
            System.out.println("Thêm chi tiết hóa đơn thất bại");
        }

        // Đóng kết nối và các đối tượng liên quan
        statement.close();
        data.getClose();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
