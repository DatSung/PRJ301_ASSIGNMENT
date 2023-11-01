create database INTERCOM_PRJ301_ASSWEB
--use INTERCOM_PRJ301_ASSWEB

-- 1.khách hàng muốn register | Cstatus: {None, Premium, Gold, Diamond}
create table tblUser (Cid varchar(32) PRIMARY KEY , [address] nvarchar(50) NOT NULL, [Cname] nvarchar(50) NOT NULL, [Cstatus] char(1), email varchar(50), password varchar(50))
-- 1.2.Khách hàng login bằng google

--1.3. Tất cả khách hàng sẽ được lưu trữ ở đây
create table tblCustomer (Cid varchar(32) PRIMARY KEY REFERENCES tblUser(Cid))

-- 2. nhân viên kỹ thuật hoặc admin : Srole (ST):Staff|(AD): admin|(TE)Technicall
create table tblStaff (Sid decimal(18,0) PRIMARY KEY , [email] nvarchar(50), [password] nvarchar(50), Srole varchar(2) )
-- 3. Các loại dịch vụ status (1) dag hoạt động | (0) tạm ngưng cung cấp
create table tblServices (id int PRIMARY KEY , [Sname] nvarchar(50) NOT NULL,[description] nvarchar(50), status bit NOT NULL, CostPerMonth decimal(18,0) )
-- 4. các thiết bị internet
create table tblDevice (No int IDENTITY PRIMARY KEY, Dname nvarchar(50) NOT NULL)
-- 5.  Các yêu cầu hỗ trợ kỹ thuật từ phía khách hàng | Rstatus: (1)đã làm|(2)chưa&dag xử lý | (-1) hủy
create table tblRequest (Rid int PRIMARY KEY IDENTITY ,[RTid] int, [description] nvarchar(50), Rdate date, Rstatus int, Cid varchar(32), Sid decimal(18,0))
-- 5+. Kiểu yêu cầu
create table tblRequestType (RTid int PRIMARY KEY ,[RTname] nvarchar(50))
--6. Các hợp đồng Rstatus: (1)đã thanh toán|(0)chưa&dag xử lý | (-1) hủy hợp đồng
create table tblContract (Cid varchar(32),[Sid] int, StartDate date, ExpDate date, [status] int,PRIMARY KEY (Cid, Sid), price decimal(18,0) )

-- Staff 1-n Request
alter table tblRequest ADD FOREIGN KEY(Sid) references tblStaff(Sid)
-- Cus 1-n Request
alter table tblRequest ADD FOREIGN KEY(Cid) references tblCustomer(Cid)
-- RequestType 1-n Request
alter table tblRequest ADD FOREIGN KEY(RTid) references tblRequestType(RTid)
-- Service 1-n Contract
alter table tblContract ADD FOREIGN KEY(Sid) references tblServices(id)
-- Device n-n Contract
create table tblContracts_Devices(
	Cid varchar(32),
	Sid int,
	FOREIGN KEY (Cid, Sid) REFERENCES tblContract(Cid, Sid),
	Did int REFERENCES tblDevice([No]),
	PRIMARY KEY(Cid,Sid, Did)
)

-- Customer 1-n Contract
alter table tblContract ADD FOREIGN KEY(Cid) references tblCustomer(Cid)
--Thêm trigger khi add User thì UserSumary cũng dc cập nhật
GO
CREATE TRIGGER tr_User_Add_Customer ON tblUser AFTER INSERT
AS
	BEGIN
		declare @id varchar(32) = (select Cid from inserted)

			INSERT INTO tblCustomer VALUES(@id)
	END;
GO
-- Thêm trigger cho các status (optional)
/*
GO
CREATE TRIGGER tr_tblRequest_status ON tblRequest AFTER INSERT
AS
	BEGIN
		declare @status int = (select Rstatus from inserted)
		IF (@status <> 1 AND @status <> 0 AND @status <> -1)
		BEGIN
			RAISERROR('Chỉ được phép insert giá trị 1, 0 hoặc -1 cho cột status', 16, 1);
			ROLLBACK TRANSACTION;
		END;
	END;
GO

GO
CREATE TRIGGER tr_tblContract_status ON tblContract AFTER INSERT
AS
	BEGIN
		declare @status int = (select status from inserted)
		IF (@status <> 1 AND @status <> 0 AND @status <> -1)
		BEGIN
			RAISERROR('Chỉ được phép insert giá trị 1, 0 hoặc -1 cho cột status', 16, 1);
			ROLLBACK TRANSACTION;
		END;
	END;
GO

GO
CREATE TRIGGER tr_tblCustomer_status ON tblCustomer AFTER INSERT,UPDATE
AS
	BEGIN
		declare @status char(1) = (select Cstatus from inserted)
		IF (@status <> 'N' AND @status <> 'P' AND @status <> 'G' AND @status <> 'D')
		BEGIN
			RAISERROR('Chỉ được phép insert giá trị N,P,G,D cho cột status', 16, 1);
			ROLLBACK TRANSACTION;
		END;
	END;
GO
*/
--INSERT DỮ LIỆU
-- bảng User
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('c4ca4238a0b923820dcc509a6f75849b', '123 Main Street', N'Nguyễn Xuân Đạt', 'D');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('c81e728d9d4c2f636f067f89cc14862c', '456 Elm Street', N'Nguyễn Thành Đạt', 'N');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('eccbc87e4b5ce2fe28308fd9f2a7baf3', '789 Oak Street', 'Peter Smith', 'N');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('a87ff679a2f3e71d9181a67b7542122c', '1011 Maple Street', 'Mike Jones', 'N');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('e4da3b7fbbce2345d7772b0674a318d5', '1213 Pine Street', 'Sarah Johnson', 'P');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('1679091c5a880faf6fb5e6087eb1b2dc', '1415 Birch Street', 'David Williams', 'G');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('8f14e45fceea167a5a36dedd4bea2543', '1617 Willow Street', 'Emily Brown', 'G');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('c9f0f895fb98ab9159f51fd0297e236d', '1819 Oak Street', 'Michael Taylor', 'D');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('45c48cce2e2d7fbdea1afc51c7c6ad26', '2021 Pine Street', 'Jennifer Anderson', 'N');
INSERT INTO tblUser (Cid, address, Cname, Cstatus)VALUES ('d3d9446802a44259755d38e6d163e820', 'Vinhome S305', N'Lê Tố Vân', 'D');
--bảng customer
INSERT INTO tblCustomer VALUES ('c4ca4238a0b923820dcc509a6f75849b')
INSERT INTO tblCustomer VALUES ('c81e728d9d4c2f636f067f89cc14862c')
INSERT INTO tblCustomer VALUES ('eccbc87e4b5ce2fe28308fd9f2a7baf3')
INSERT INTO tblCustomer VALUES ('a87ff679a2f3e71d9181a67b7542122c')
INSERT INTO tblCustomer VALUES ('e4da3b7fbbce2345d7772b0674a318d5')
INSERT INTO tblCustomer VALUES ('1679091c5a880faf6fb5e6087eb1b2dc')
INSERT INTO tblCustomer VALUES ('8f14e45fceea167a5a36dedd4bea2543')
INSERT INTO tblCustomer VALUES ('c9f0f895fb98ab9159f51fd0297e236d')
INSERT INTO tblCustomer VALUES ('45c48cce2e2d7fbdea1afc51c7c6ad26')
INSERT INTO  tblCustomer VALUES ('d3d9446802a44259755d38e6d163e820')
-- bảng nhân viên
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (1, 'john.doe@fpt.edu.vn', 'password123', 'TE');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (2, 'admin', '12345', 'AD');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (3, 'peter.smith@fpt.edu.vn', 'password789', 'TE');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (4, 'mike.jones@fpt.edu.vn', 'password1011', 'TE');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (5, 'sarah.johnson@fpt.edu.vn', 'password1213', 'TE');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (6, 'david.williams@fpt.edu.vn', 'password1415', 'ST');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (7, 'emily.brown@fpt.edu.vn', 'password1617', 'ST');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (8, 'michael.taylor@fpt.edu.vn', 'password1819', 'TE');
INSERT INTO tblStaff (Sid, email, password, Srole)VALUES (9, 'jennifer.anderson@fpt.edu.vn', 'password2021', 'TE');
-- bảng service
INSERT INTO tblServices VALUES (1, 'Giga',  'Download 150Mbps and Upload 150Mbps',1, 225000);
INSERT INTO tblServices VALUES (2, 'Giga+', 'Download 150Mbps and Upload 150Mbps + 1 box Tivi', 1,235000 );
INSERT INTO tblServices VALUES (3, 'Sky',  'Download 300Mbps and Upload 150Mbps', 1, 255000 );
INSERT INTO tblServices VALUES (4, 'Sky+',  'Download 300Mbps and Upload 150Mbps + 1 box Tivi',1, 265000 );
INSERT INTO tblServices VALUES (5, 'Meta', 'Download 300Mbps and Upload 300Mbps', 1, 355000 );
INSERT INTO tblServices VALUES (6, 'Meta+', 'Download 300Mbps and Upload 300Mbps + 1 box Tivi',1, 365000 );
INSERT INTO tblServices VALUES (7, 'LUX',  'Download 300Mbps and Upload 300Mbps',1, 800000 );
INSERT INTO tblServices VALUES (8, 'LUX+',  'Download 800Mbps and Upload 800Mbps + 1 box Tivi',1, 1000000 );
-- bảng device
--delete from tblDevice

INSERT INTO tblDevice  VALUES ( 'Modem');
INSERT INTO tblDevice  VALUES ( 'Router');
INSERT INTO tblDevice  VALUES ( 'Mesh');
INSERT INTO tblDevice  VALUES ('Access Point');
INSERT INTO tblDevice  VALUES ( 'box TV');
-- bảng RequestType
INSERT INTO tblRequestType (RTid, RTname)VALUES (1, N'Hỗ trợ kỹ thuật');
INSERT INTO tblRequestType (RTid, RTname)VALUES (2, N'Vấn đề với nhân viên');
INSERT INTO tblRequestType (RTid, RTname)VALUES (3, N'Hỗ trợ các gói dịch vụ');
-- bảng request
INSERT INTO tblRequest ( RTid, description, Rdate, Rstatus, Cid, Sid)VALUES ( 1, N'Mạng internet bị gián đoạn', '2023-07-20', 0, 'c4ca4238a0b923820dcc509a6f75849b', 1);
INSERT INTO tblRequest ( RTid, description, Rdate, Rstatus, Cid, Sid)VALUES ( 1, N'Máy tính không khởi động được', '2023-07-21', 0, 'c81e728d9d4c2f636f067f89cc14862c', 2);
INSERT INTO tblRequest ( RTid, description, Rdate, Rstatus, Cid, Sid)VALUES ( 3, N'Cần hỗ trợ các gói cước mới', '2023-07-22', 0, 'd3d9446802a44259755d38e6d163e820', 3);
-- bảng Contract có cộng phí hòa mạng 250k
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status, price) VALUES ('c4ca4238a0b923820dcc509a6f75849b', 8, '2023-08-04', '2024-03-04', 1,  6250000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price)VALUES ('c81e728d9d4c2f636f067f89cc14862c', 1, '2023-07-05', '2024-02-05', 1,  1600000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price)VALUES ( 'eccbc87e4b5ce2fe28308fd9f2a7baf3',1, '2023-07-06', '2024-02-06', 1, 1600000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price) VALUES ('a87ff679a2f3e71d9181a67b7542122c', 1, '2023-07-04', '2024-02-04', 1,  1600000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price)VALUES ('45c48cce2e2d7fbdea1afc51c7c6ad26', 2, '2023-07-03', '2024-02-03', 1,  1600000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price)VALUES ('c9f0f895fb98ab9159f51fd0297e236d', 8, '2023-01-06', '2024-08-06', 1,  6250000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price)VALUES ('e4da3b7fbbce2345d7772b0674a318d5', 3, '2023-08-07', '2024-07-25', 1,  1780000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status, price)VALUES ( '1679091c5a880faf6fb5e6087eb1b2dc', 5, '2023-08-08', '2024-07-24', 1, 2380000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status,  price)VALUES ( '8f14e45fceea167a5a36dedd4bea2543', 5, '2023-08-09', '2024-07-23', 1, 2380000.00);
INSERT INTO tblContract (Cid, Sid, StartDate, ExpDate, status, price)VALUES ( 'd3d9446802a44259755d38e6d163e820', 8, '2023-08-03', '2024-03-03', 1, 6250000.00);
-- bange Contract_devices
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('c4ca4238a0b923820dcc509a6f75849b', 8, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('c4ca4238a0b923820dcc509a6f75849b', 8, 1);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('c81e728d9d4c2f636f067f89cc14862c', 1, 2);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('eccbc87e4b5ce2fe28308fd9f2a7baf3', 1, 2);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('a87ff679a2f3e71d9181a67b7542122c', 1, 2);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('45c48cce2e2d7fbdea1afc51c7c6ad26', 2, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('45c48cce2e2d7fbdea1afc51c7c6ad26', 2, 5);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('c9f0f895fb98ab9159f51fd0297e236d', 8, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('c9f0f895fb98ab9159f51fd0297e236d', 8, 5);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('e4da3b7fbbce2345d7772b0674a318d5', 3, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('e4da3b7fbbce2345d7772b0674a318d5', 3, 5);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('c9f0f895fb98ab9159f51fd0297e236d', 5, 8);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('8f14e45fceea167a5a36dedd4bea2543', 3, 7);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('1679091c5a880faf6fb5e6087eb1b2dc', 5, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('1679091c5a880faf6fb5e6087eb1b2dc', 5, 5);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('8f14e45fceea167a5a36dedd4bea2543', 5, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('8f14e45fceea167a5a36dedd4bea2543', 5, 5);

INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('d3d9446802a44259755d38e6d163e820', 8, 2);
INSERT INTO tbContracts_lDevices (Cid, Sid, Did)VALUES ('d3d9446802a44259755d38e6d163e820', 8, 5);

