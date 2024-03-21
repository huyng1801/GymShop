package Controller;

import Model.TaiKhoanModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanController {
    public void doiMatKhau(TaiKhoanModel tk) {
        KetNoi data = new KetNoi();
        try {
            // Kết nối database và tạo câu truy vấn
            Connection conn = data.getConnect();
            String query = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenDangNhap = ?";
            
            // Tạo prepared statement và thiết lập giá trị tham số
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, tk.getMatKhau());
            statement.setString(2, tk.getTenDangNhap());
            
            // Thực hiện câu truy vấn UPDATE
            statement.executeUpdate();
            
            // Đóng kết nối và các đối tượng liên quan
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            data.getClose();
        }
    }
    public void themTaiKhoan(TaiKhoanModel tk) {
        KetNoi data = new KetNoi();
        try {
            // Tạo câu truy vấn SQL để thêm tài khoản
            String query = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau) VALUES (?, ?)";

            // Chuẩn bị câu lệnh PreparedStatement
            Connection connection = data.getConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tk.getTenDangNhap());
            statement.setString(2, tk.getMatKhau());

            // Thực thi câu truy vấn
            statement.executeUpdate();

            // Đóng kết nối và câu lệnh PreparedStatement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data.getClose();
    }
    public TaiKhoanController(){
   }
    public List<String> login(TaiKhoanModel taikhoan) {
    List<String> userInfo = new ArrayList<>();
    KetNoi data=new KetNoi();
    try {
            String sql = "{call LoginProcedure(?, ?)}";
            Connection connection = data.getConnect();
            CallableStatement statement = connection.prepareCall(sql);

            // Thiết lập tham số đầu vào cho stored procedure
            statement.setString(1, taikhoan.getTenDangNhap());
            statement.setString(2, taikhoan.getMatKhau());

            // Thực thi stored procedure
            ResultSet resultSet = statement.executeQuery();

        // Kiểm tra kết quả trả về
        if (resultSet.next()) {
            
            String loaiTaiKhoan = resultSet.getString("LoaiTaiKhoan");
            if (loaiTaiKhoan != null) {
                userInfo.add(loaiTaiKhoan);
                userInfo.add(resultSet.getString("Ma"));
                userInfo.add(resultSet.getString("HoTen"));
                userInfo.add(resultSet.getString("DiaChi"));
                userInfo.add(resultSet.getString("SoDienThoai"));
            }
        }

        // ...
         resultSet.close();
         statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally{
    data.getClose();
    }
    return userInfo;
}
public boolean checkTaiKhoan(String tenDangNhap) {
    KetNoi data = new KetNoi();
    boolean result = false;
    try {
        String sql = "{call CheckExistingAccount(?)}";
        Connection connection = data.getConnect();
        CallableStatement statement = connection.prepareCall(sql);

        // Thiết lập tham số đầu vào cho stored procedure
        statement.setString(1, tenDangNhap);

        // Thực thi stored procedure
        ResultSet resultSet = statement.executeQuery();

        // Kiểm tra kết quả trả về
        if (resultSet.next()) {
            // Tài khoản đã tồn tại
            result = true;
        }

        // Đóng ResultSet và statement
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        data.getClose();
    }
    return result;
}

}




   