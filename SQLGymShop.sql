-- Tạo database GymShop
create database GymShop;
go
use GymShop;
go
create table TaiKhoan(
	TenDangNhap varchar(20) not null primary key,
	MatKhau varchar(20) not null
);

-- Bảng sản phẩm
CREATE TABLE SanPham (
    MaSanPham char(6) PRIMARY KEY,
    TenSanPham NVARCHAR(100),
    Gia FLOAT,
    SoLuong INT,
    TenDanhMuc nvarchar(50),
    Anh varbinary(max)

);
go
-- Bảng nhân viên
CREATE TABLE NhanVien (
    MaNhanVien char(6) PRIMARY KEY,
    HoTen NVARCHAR(100),
    DiaChi NVARCHAR(200),
    SoDienThoai NVARCHAR(20),
	TenDangNhap varchar(20) foreign key references TaiKhoan(TenDangNhap)
);
go
-- Bảng khách hàng
CREATE TABLE KhachHang (
    MaKhachHang char(6) PRIMARY KEY,
    HoTen NVARCHAR(100),
    DiaChi NVARCHAR(200),
    SoDienThoai NVARCHAR(20),
	TenDangNhap varchar(20) foreign key references TaiKhoan(TenDangNhap)
);
go
-- Bảng hóa đơn
CREATE TABLE HoaDon (
    MaHoaDon char(6) PRIMARY KEY DEFAULT dbo.GenerateMaHoaDon(),
    NgayLap DATETIME,
    TongTien FLOAT default 0.0,
    MaKhachHang char(6),
    MaNhanVien char(6),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);
go

--Bảng chi tiết hóa đơn
CREATE TABLE ChiTietHoaDon (
    SoThuTu INT identity PRIMARY KEY,
    SoLuong INT,
    MaHoaDon char(6) FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    MaSanPham char(6) FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
);
go
CREATE TRIGGER Trg_ChiTietHoaDon ON ChiTietHoaDon
AFTER INSERT
AS
BEGIN
    -- Cập nhật số lượng sản phẩm trong bảng SanPham
    UPDATE SanPham
    SET SanPham.SoLuong = SanPham.SoLuong - inserted.SoLuong
    FROM SanPham
    JOIN inserted ON SanPham.MaSanPham = inserted.MaSanPham;
END;

--Tạo stored kiểm tra TenDangNhap
create PROCEDURE CheckExistingAccount
    @TenDangNhap varchar(20)
AS
BEGIN
    select* from TaiKhoan where @TenDangNhap = TenDangNhap
END
go
-- Tạo stored procedure đăng nhập
create PROCEDURE LoginProcedure
    @TenDangNhap varchar(20),
    @MatKhau varchar(20)
AS
BEGIN
    -- Kiểm tra đăng nhập trong bảng NhanVien
    IF EXISTS (SELECT * FROM NhanVien nv JOIN TaiKhoan tk ON nv.TenDangNhap = tk.TenDangNhap WHERE tk.TenDangNhap = @TenDangNhap AND tk.MatKhau = @MatKhau)
    BEGIN
        SELECT 'NhanVien' AS LoaiTaiKhoan, MaNhanVien as 'Ma', HoTen, DiaChi, SoDienThoai
        FROM NhanVien
        WHERE TenDangNhap = @TenDangNhap
    END
    ELSE
    BEGIN
        -- Kiểm tra đăng nhập trong bảng KhachHang
        IF EXISTS (SELECT * FROM KhachHang kh JOIN TaiKhoan tk ON kh.TenDangNhap = tk.TenDangNhap WHERE tk.TenDangNhap = @TenDangNhap AND tk.MatKhau = @MatKhau)
        BEGIN
            SELECT 'KhachHang' AS LoaiTaiKhoan, MaKhachHang as 'Ma', HoTen, DiaChi, SoDienThoai
            FROM KhachHang
            WHERE TenDangNhap = @TenDangNhap
        END
        ELSE
        BEGIN
            -- Trường hợp không tìm thấy tài khoản đăng nhập
            SELECT NULL AS LoaiTaiKhoan, NULL AS Ma, NULL AS HoTen, NULL AS DiaChi, NULL AS SoDienThoai
        END
    END
END

go

-- Trigger xóa tài khoản khi xóa khách hàng
CREATE TRIGGER trg_XoaTaiKhoan_KhachHang
ON KhachHang
AFTER DELETE
AS
BEGIN
    DELETE FROM TaiKhoan
    WHERE TenDangNhap IN (SELECT TenDangNhap FROM deleted);
END;
GO

-- Trigger xóa tài khoản khi xóa nhân viên
CREATE TRIGGER trg_XoaTaiKhoan_NhanVien
ON NhanVien
AFTER DELETE
AS
BEGIN
    DELETE FROM TaiKhoan
    WHERE TenDangNhap IN (SELECT TenDangNhap FROM deleted);
END;
GO

-- Tạo trigger để tính tổng tiền hóa đơn
CREATE TRIGGER tr_TinhTongTienHoaDon
ON ChiTietHoaDon
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Cập nhật tổng tiền cho các hóa đơn bị ảnh hưởng
    UPDATE HoaDon
    SET TongTien = COALESCE((SELECT SUM(sp.Gia * cthd.SoLuong)
                            FROM ChiTietHoaDon cthd
                            INNER JOIN SanPham sp ON cthd.MaSanPham = sp.MaSanPham
                            WHERE cthd.MaHoaDon = HoaDon.MaHoaDon), 0)
    WHERE MaHoaDon IN (SELECT DISTINCT MaHoaDon FROM inserted) OR
          MaHoaDon IN (SELECT DISTINCT MaHoaDon FROM deleted);
END;
go

-- Tạo trigger để cập nhật số lượng sản phẩm
create TRIGGER tr_CapNhatSoLuongSanPham
ON ChiTietHoaDon
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Cập nhật số lượng sản phẩm trong bảng SanPham
    UPDATE SanPham
    SET SoLuong = SoLuong + (
        SELECT SUM(IIF(INS.SoThuTu IS NOT NULL, INS.SoLuong, -DEL.SoLuong))
        FROM inserted INS
        FULL OUTER JOIN deleted DEL ON INS.SoThuTu = DEL.SoThuTu
        WHERE SanPham.MaSanPham = INS.MaSanPham OR SanPham.MaSanPham = DEL.MaSanPham
    )
    WHERE MaSanPham IN (SELECT MaSanPham FROM inserted) OR
          MaSanPham IN (SELECT MaSanPham FROM deleted)
    OR (SELECT COUNT(*) FROM deleted) = 0; -- Xử lý thao tác DELETE khi bảng deleted trống
END;
GO
--Tạo stored lấy đơn giá
CREATE FUNCTION dbo.GetDonGiaByMaSanPham
    (@MaSanPham CHAR(6))
RETURNS FLOAT
AS
BEGIN
    DECLARE @DonGia FLOAT;

    SELECT @DonGia = Gia
    FROM SanPham
    WHERE MaSanPham = @MaSanPham;

    RETURN @DonGia;
END


-- Tạo function để sinh mã sản phẩm tự động
CREATE FUNCTION dbo.GenerateMaSanPham()
RETURNS CHAR(6)
AS
BEGIN
    DECLARE @SoThuTu INT;
    DECLARE @MaSanPham CHAR(6);

    -- Lấy số thứ tự hiện tại
    SELECT @SoThuTu = COALESCE(MAX(CAST(RIGHT(MaSanPham, 4) AS INT)), 0) + 1
    FROM SanPham;

    -- Tạo mã sản phẩm mới
    SET @MaSanPham = 'SP' + RIGHT('0000' + CAST(@SoThuTu AS VARCHAR(4)), 4);

    RETURN @MaSanPham;
END;
GO
-- Tạo function để sinh mã nhân viên tự động
CREATE FUNCTION dbo.GenerateMaNhanVien()
RETURNS CHAR(6)
AS
BEGIN
    DECLARE @SoThuTu INT;
    DECLARE @MaNhanVien CHAR(6);

    -- Lấy số thứ tự hiện tại
    SELECT @SoThuTu = COALESCE(MAX(CAST(RIGHT(MaNhanVien, 4) AS INT)), 0) + 1
    FROM NhanVien;

    -- Tạo mã nhân viên mới
    SET @MaNhanVien = 'NV' + RIGHT('0000' + CAST(@SoThuTu AS VARCHAR(4)), 4);

    RETURN @MaNhanVien;
END;
GO
-- Tạo function để sinh mã khách hàng tự động
CREATE FUNCTION dbo.GenerateMaKhachHang()
RETURNS CHAR(6)
AS
BEGIN
    DECLARE @SoThuTu INT;
    DECLARE @MaKhachHang CHAR(6);

    -- Lấy số thứ tự hiện tại
    SELECT @SoThuTu = COALESCE(MAX(CAST(RIGHT(MaKhachHang, 4) AS INT)), 0) + 1
    FROM KhachHang;

    -- Tạo mã khách hàng mới
    SET @MaKhachHang = 'KH' + RIGHT('0000' + CAST(@SoThuTu AS VARCHAR(4)), 4);

    RETURN @MaKhachHang;
END;
GO
-- Tạo function để sinh mã hóa đơn tự động
CREATE FUNCTION dbo.GenerateMaHoaDon()
RETURNS CHAR(6)
AS
BEGIN
    DECLARE @SoThuTu INT;
    DECLARE @MaHoaDon CHAR(6);

    -- Lấy số thứ tự hiện tại
    SELECT @SoThuTu = COALESCE(MAX(CAST(RIGHT(MaHoaDon, 4) AS INT)), 0) + 1
    FROM HoaDon;

    -- Tạo mã hóa đơn mới
    SET @MaHoaDon = 'HD' + RIGHT('0000' + CAST(@SoThuTu AS VARCHAR(4)), 4);

    RETURN @MaHoaDon;
END;
GO


--Chèn dữ liệu vào bảng TaiKhoan
insert into TaiKhoan(TenDangNhap, MatKhau) values
('giacathuy','giacathuy');
go
insert into TaiKhoan(TenDangNhap, MatKhau) values
('huynguyen','huynguyen');
go
insert into TaiKhoan(TenDangNhap, MatKhau) values
('huyvippro','huyvippro');
go

/*-- Chèn dữ liệu vào bảng SanPham
INSERT INTO SanPham (MaSanPham,TenSanPham, Gia, SoLuong, MaDanhMuc, Anh)
SELECT 'SP0001',N'Áo thun nam BBT cho Gymer yêu nước', 500000, 1801, 'DMSP01', BulkColumn
FROM OPENROWSET(BULK 'D:\Project\Java\GymShop\Images\ThoiTrangTHOL\a4e9b5c96665983bc174-300x300.jpg', SINGLE_BLOB) AS img;
INSERT INTO SanPham (MaSanPham,TenSanPham, Gia, SoLuong, MaDanhMuc, Anh)
SELECT 'SP0002',N'Áo khoác jeans nam không tay MJ003', 1200000, 1801, 'DMSP01', BulkColumn
FROM OPENROWSET(BULK 'D:\Project\Java\GymShop\Images\ThoiTrangTHOL\aokhoacjean_04-300x300.jpg', SINGLE_BLOB) AS img;*/
-- Chèn dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNhanVien,HoTen, DiaChi, SoDienThoai, TenDangNhap)
VALUES ('NV0001',N'Nguyễn Tấn Huy', N'Đồng Tháp', '0788859370', 'giacathuy');

-- Chèn dữ liệu vào bảng KhachHang
INSERT INTO KhachHang (MaKhachHang,HoTen, DiaChi, SoDienThoai, TenDangNhap)
VALUES ('KH0001',N'Nguyễn Văn A', N'Hà Nội', '0123456789', 'huynguyen');
go
INSERT INTO KhachHang (MaKhachHang,HoTen, DiaChi, SoDienThoai, TenDangNhap)
values ('KH0002',N'Trần Thị B', N'Hồ Chí Minh', '0987654321', 'huyvippro');
go
select * from SanPham
-- Chèn dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (NgayLap, MaKhachHang, MaNhanVien)
VALUES ('2023-06-01 10:30:00', 'KH0001', 'NV0001');
go
INSERT INTO HoaDon (MaHoaDon,NgayLap, MaKhachHang, MaNhanVien)
VALUES('2023-06-02 14:45:00', 'KH0002', 'NV0001');
go
-- Chèn dữ liệu vào bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (SoLuong, MaHoaDon, MaSanPham)
VALUES (5, 'HD0001', 'SP0001'),
       (3, 'HD0001', 'SP0001'),
       (2, 'HD0001', 'SP0002'),
       (4, 'HD0002', 'SP0002'),
       (6, 'HD0002', 'SP0001');
go
