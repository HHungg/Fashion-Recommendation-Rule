-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2017 at 04:56 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `saleweb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `UserName` char(30) COLLATE utf8_vietnamese_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `Name` varchar(30) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`UserName`, `Password`, `Name`) VALUES
('admin', 'admin', 'Linh Phan');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `BillId` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `TotalMoney` int(11) NOT NULL,
  `ReceiverName` varchar(40) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PayOption` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Check` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BillId`, `UserID`, `TotalMoney`, `ReceiverName`, `Address`, `PayOption`, `Date`, `Check`) VALUES
(45, 10, 8140000, 'Đào Thu Trang', 'Vĩnh Phúc', 'Tiền mặt', '2017-05-02 02:01:20', NULL),
(46, 10, 550000, 'Lan Phương', 'Xóm Tam Giáp - Nam Kim - Nam Đàn - Nghệ An', 'Tiền mặt', '2017-05-07 03:26:29', NULL),
(47, 10, 629000, 'Đặng Văn Đình', 'Bách Khoa Hà Nội', 'Chuyển khoản', '2017-05-07 03:27:13', NULL),
(48, 10, 1557000, 'Đào Thu Trang', 'Hà Nội', 'Tiền mặt', '2017-05-07 03:27:56', NULL),
(49, 10, 1496000, 'Đào Thu Trang', 'B1 - Bách Khoa Hà Nội', 'Chuyển khoản', '2017-05-07 03:28:30', NULL),
(50, 10, 629000, 'Phan Thúy Hằng', 'Nam Đàn Nghệ An', 'Chuyển khoản', '2017-05-09 07:36:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `billdetail`
--

CREATE TABLE `billdetail` (
  `BillDetailId` int(11) NOT NULL,
  `BillId` int(11) NOT NULL,
  `PID` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantify` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `billdetail`
--

INSERT INTO `billdetail` (`BillDetailId`, `BillId`, `PID`, `Price`, `Quantify`) VALUES
(1, 46, '101', 230000, 1),
(2, 46, '105', 320000, 1),
(3, 47, '101', 230000, 1),
(4, 47, '107', 399000, 1),
(5, 48, '101', 230000, 2),
(6, 48, '107', 399000, 2),
(7, 48, '106', 299000, 1),
(8, 49, '107', 399000, 1),
(9, 49, '103', 399000, 1),
(10, 49, '102', 399000, 1),
(11, 49, '106', 299000, 1),
(12, 50, '101', 230000, 1),
(13, 50, '107', 399000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryId` char(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `CategoryName` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`) VALUES
('aophongnam', 'Áo Phông Nam'),
('aophongnu', 'Áo Phông Nữ'),
('aosomi', 'Áo Sơ Mi'),
('giaynam', 'GIày Nam'),
('giaynu', 'Giầy Nữ'),
('quanjean', 'Quần Jean');

-- --------------------------------------------------------

--
-- Table structure for table `luat`
--

CREATE TABLE `luat` (
  `ID` int(11) NOT NULL,
  `Ve_trai` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `Ve_Phai` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `Giai_thich` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `Do_tin_cay` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci ROW_FORMAT=COMPACT;

--
-- Dumping data for table `luat`
--

INSERT INTO `luat` (`ID`, `Ve_trai`, `Ve_Phai`, `Giai_thich`, `Do_tin_cay`) VALUES
(1, '[25]', '29', NULL, 9),
(2, '[25, 28]', '27', NULL, 4),
(3, '[11, 22]', '32', NULL, 9),
(4, '[11, 24]', '33', NULL, 12),
(5, '[36]', '31', NULL, 35),
(6, '[17]', '36', NULL, 11),
(7, '[18]', '36', NULL, 25),
(8, '[15]', '35', NULL, 25),
(9, '[12, 34]', '36', NULL, 6),
(10, '[16]', '34', NULL, 7),
(11, '[25]', '34', NULL, 12),
(12, '[26]', '37', NULL, 7),
(13, '[37]', '40', NULL, 8),
(14, '[15, 28]', '38', NULL, 3),
(15, '[23]', '28', NULL, 12),
(16, '[36]', '38', NULL, 36),
(17, '[13]', '39', NULL, 14),
(18, '[11, 30]', '41', NULL, 2),
(19, '[18, 28]', '42', NULL, 9),
(20, '[28, 14]', '43', NULL, 8),
(21, '[25]', '28', NULL, 9),
(22, '[21]', '28', NULL, 6),
(23, '[20]', '44', NULL, 3),
(24, '[11]', '27', NULL, 31),
(25, '[12]', '27', NULL, 8),
(26, '18', '45', NULL, 20),
(27, '19', '45', NULL, 13),
(28, '20', '45', NULL, 4),
(29, '22', '45', NULL, 8),
(30, '24,32', '45', NULL, 9);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `PID` char(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `InputPrice` int(11) NOT NULL,
  `PPrice` int(11) NOT NULL,
  `Img` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SID` int(11) NOT NULL,
  `CategoryId` char(20) COLLATE utf8_vietnamese_ci NOT NULL,
  `PDescription` varchar(500) CHARACTER SET utf32 COLLATE utf32_vietnamese_ci DEFAULT NULL,
  `Mo_ta` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`PID`, `PName`, `InputPrice`, `PPrice`, `Img`, `SID`, `CategoryId`, `PDescription`, `Mo_ta`) VALUES
('101', 'Áo Phông Nam HQ', 180000, 230000, 'apn-1.jpg', 4, 'aophongnam', '<ul><li>Màu sắc: Xanh da trời<br></li><li>Chất Liệu: Cotton </li><li>Size: M,L,XXL</li></ul>', '11,27,28'),
('102', 'Áo Phông Gucci', 300000, 399000, 'apn-2.jpg', 5, 'aophongnam', '<ul><li>Màu sắc: Da cam<br></li><li>Chất Liệu: Cotton </li><li>Size: M,L,XXL</li></ul>', '27,28'),
('103', 'Áo Phông Nam Viettien', 320000, 399000, 'apn-3.jpeg', 1, 'aophongnam', '<ul><li>Màu sắc: Trắng</li><li>Chất liệu: Nilon</li><li>Size: M,X,XL,XXL<br></li></ul>', '11,30'),
('104', 'Áo Phông Coach', 300000, 355999, 'apn-6.jpg', 8, 'aophongnam', '<ul><li>Màu sắc: Đen<br></li><li>Chất Liệu: Cotton </li><li>Size: M,L,XXL</li></ul>', '27,28,39'),
('105', 'Áo Phông Nữ Louboutin', 230000, 320000, 'apnu-1.jpg', 5, 'aophongnu', '<ul><li>Màu sắc: Trắng<br></li><li>Chất Liệu: Cotton </li><li>Size: M,L</li></ul>', '12,28'),
('106', 'Áo Phông Nữ ECCO', 221000, 299000, 'apnu-4.jpg', 1, 'aophongnu', '<ul><li>Màu sắc: Xanh da trời<br></li><li>Chất Liệu: Cotton </li><li>Size: M,L,XXL</li></ul>', '12,28'),
('107', 'Áo Phông Nữ bla', 233000, 399000, 'apnu-3.jpg', 4, 'aophongnu', '<ul><li>Màu sắc: Trắng<br></li></ul>', '12,28'),
('108', 'Giày Da Cao Cấp', 400000, 540000, 'giaynam-4.jpg', 6, 'giaynam', '<p>Giày Nam thể thao</p><p>Chất liệu bền đẹp</p><p>size: 37-43<br></p>', '34,35'),
('109', 'Giày Nữ Cao cấp', 430000, 560000, 'giaynu-2.jpg', 1, 'giaynu', '<p>Giày nữ thể thao</p><p>Dáng trẻ trung<br></p>', '34,36'),
('110', 'Quần Jean 1ST', 200000, 290000, 'quan-jean-nam-1.jpg', 3, 'quanjean', '<p>Quần Jean cao cấp</p><p>Đẹp mê li<br></p><p><br></p>', '37,40'),
('111', 'Áo Polo Fred', 700000, 799000, 'Capture.PNG', 1, 'aophongnam', '<p><br></p><ul><li>Màu sắc: xanh</li><li>Chất liệu: 100% Cotton</li><li>Giới tính: nam</li></ul><p><br></p>', '11,26'),
('112', 'Áo Sơ Mi Regular Fit', 230000, 299000, 'Capture3.PNG', 1, 'aosomi', '<p>Áo sơ mi Owen </p><p>Màu trắng có hoa văn chấm bi đỏ chất liệu Bamboo </p><p>Chất liệu 50% Bamboo, 50% polyspun </p><p>Ngấn Tay có túi </p><p>Nút tròn màu đỏ<br></p><p><br></p>', '31,32,33'),
('113', 'Áo Kiểu Không Tay Cra', 300000, 349000, 'Capture1.PNG', 3, 'aophongnu', '<p>Chất liệu: polyeste pha</p><p>Cổ tròn</p><p>Xẻ vạt hai bên hông&nbsp;</p><p>Không may lót</p>', '12,18'),
('114', 'Áo Blouse Cách Điệu', 200000, 300000, 'Capture1.PNG', 6, 'aophongnu', '<p>Chất liệu viscose pha<br>Áo cổ lật<br>Có phần dây thắt phía trước cổ áo<br>Không tay<br>Vạt trước áo ngắn hơn vạt sau<br>Không có lót</p>', '12,19'),
('115', 'Áo Sơ Mi Jean Nam Dài Tay', 700000, 800000, 'Capture4.PNG', 1, 'aosomi', '<p>Chất liệu vải: 100% Cotton.<br>Công nghệ xử lý màu Ô-zôn không qua hóa chất độc hại.<br>Sản phẩm được wash + ozone.<br>Form dáng theo thông số nghiên cứu chuẩn size người Việt.</p><p>Thiết kế hiện đại trẻ trung từ các nhà thiết kế trẻ.</p><p>Sản phẩm được duyệt theo tiêu chuẩn xuất khẩu quốc tế.</p>', '11,17'),
('116', 'Giày Thể Thao Hunter Nam', 500000, 600000, 'Capture6.PNG', 5, 'giaynam', '<p>Công nghệ quai dệt Liteknit hiện đại mang đến sự thông thoáng tối đa cho đôi chân trong mọi hoạt động.<br>Đế phylon cao cấp siêu nhẹ, đàn hồi tốt đem lại cảm giác êm ái khi di chuyển.&nbsp;<br>Đế tiếp đất từ chất liệu cao su cao cấp cùng thiết kế rãnh ngang tăng độ bám khi di chuyển trên mọi địa hình.<br></p>', '11,21'),
('117', 'Giày Mọi Slip On Vải', 200000, 299000, 'Capture7.PNG', 8, 'giaynam', '<p>Chất liệu vải bố cao cấp<br>Mũi tròn<br>Thiết kế kiểu slip on<br>Thiết kế gọn nhẹ, màu sắc thời trang<br>Mặt lót giày êm ái, bảo vệ đôi chân tối đa<br>Đế giày có thiết kế chống trượt </p><p>Cỡ 38-43 có 2 màu ghi và xanh</p>', '11,22'),
('118', 'Áo Sơ Mi Lụa', 850000, 949000, 'Capture8.PNG', 2, 'aosomi', '<p>Sản phẩm nằm trong Bộ sưu tập xuân 2017 của Mimi với tên gọi NEW WAVE STYLE.<br>Điểm nhấn của sản phẩm là phần cổ áo đính kèm dải nơ (có thể tháo dời) tạo sư trẻ trung, thanh lịch.<br>Chất liệu:&nbsp;100% Polyester (vải satin crepe)<br></p>', '12,26'),
('119', 'Sơ Mi Kẻ Sát Nách', 400000, 550000, 'Capture12.PNG', 8, 'aosomi', '<div class=\"box mtl fss clearfix\" id=\"productDesc\" itemprop=\"description\" style=\"margin-top: 20px; position: relative; zoom: 1; height: 141px; overflow: hidden; z-index: 1;\">Thô là chất liệu của chiếc áo thấm hút tốt, đem đến sự thoải mái, mát mẻ.<br>Sơ mi kẻ sát nách gấu tôm sẽ mang đến sự năng động, khỏe khoắn cho người mặc.&nbsp;<br>Màu sắc kẻ mang đến nhiều sự lựa chọn, biến tấu trong cách phối đồ.</div><div><br></div>', '12,23'),
('120', 'Bộ Vest Đen', 3000000, 3500000, 'Capture9.PNG', 1, 'aosomi', '<p>Chất liệu tuytsi cao cấp, mềm mịn<br>Áo vest 2 lớp, cổ bẻ, tay dài, 1 khuy trẻ trung<br>Quần ống đứng, có ly, túi chéo<br>Đường may tỉ mỉ, chắc chắn<br></p>', '11,45');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `SID` int(11) NOT NULL,
  `SName` varchar(30) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `SAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SID`, `SName`, `SAddress`) VALUES
(1, 'ECCO', 'Hoa Kỳ'),
(2, 'VERSACE', 'Trung Quốc'),
(3, 'ACOST', 'Hoa Kỳ'),
(4, 'Louboutin', 'Hàn Quốc'),
(5, 'Gucci', 'Hoa Kỳ'),
(6, 'Louis Vuitton', 'Việt Nam'),
(8, 'Coach', 'Nhật Bản');

-- --------------------------------------------------------

--
-- Table structure for table `su_kien`
--

CREATE TABLE `su_kien` (
  `Id` int(255) NOT NULL,
  `Ten_su_kien` varchar(255) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `su_kien`
--

INSERT INTO `su_kien` (`Id`, `Ten_su_kien`) VALUES
(11, 'Nam'),
(12, 'Nữ'),
(13, 'Mạnh mẽ'),
(14, 'Nhút nhát'),
(15, 'Thích nổi bật'),
(16, 'Năng động'),
(17, '15-20'),
(18, '20-30'),
(19, '30-40'),
(20, '>40'),
(21, 'Đi phượt'),
(22, 'Dự tiệc'),
(23, 'Ở nhà'),
(24, 'Đi làm'),
(25, 'Chơi thể thao'),
(26, 'Hẹn hò'),
(27, 'Cotton'),
(28, 'Áo phông'),
(29, 'Adidas'),
(30, 'Nilon'),
(31, 'Kẻ sọc'),
(32, 'Đẳng cấp phái mạnh'),
(33, 'Áo sơ mi'),
(34, 'Giày'),
(35, 'Chất liệu da'),
(36, 'Dáng trẻ trung'),
(37, 'Quần Jean'),
(38, 'Thương hiệu Gucci'),
(39, 'Thương hiệu Coach'),
(40, 'Thương hiệu 1ST'),
(41, 'Thương hiệu Vietien'),
(42, 'Thương hiệu Louboutin'),
(43, 'Thương hiệu Hàn Quốc'),
(44, 'Thương hiệu ECCO'),
(45, 'Bộ vest');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `ID` int(255) NOT NULL,
  `ProductIDs` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`ID`, `ProductIDs`) VALUES
(1000, '101,102,103,105'),
(1001, '101,102,105,107,109'),
(1002, '101,102,104,105,106,107'),
(1003, '101,102,103,105,107,108'),
(1004, '101,102,105,112'),
(1005, '105,110,112'),
(1006, '108,109,111,112'),
(1007, '101,102,103,104,106,107'),
(1008, '101,102,106,108,109'),
(1009, '101,102,110,112');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(30) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `PassWord` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `FullName` varchar(40) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Email` varchar(40) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Address` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Phone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `UserName`, `PassWord`, `FullName`, `Email`, `Address`, `Phone`) VALUES
(1, 'tranluuly', '202cb962ac59075b964b07152d234b70', 'Trần Lưu Ly', 'linhphan@gmail.com', 'Nghệ An', 964976895),
(3, 'linhphanhust', '4297f44b13955235245b2497399d7a93', 'Lĩnh Phan', 'user@gmail.com', 'Hà Nội', 974345095),
(4, 'daothutrang', '202cb962ac59075b964b07152d234b70', 'Đào Thu Trang', 'trangmit28@gmail.com', 'Nam Định', 912799546),
(5, 'doanvietdung', '7c880b352b74b3ddd49f67891ad717c9', 'Đoàn VIệt Dũng', 'aido@gmail.com', 'Vĩnh Phúc', 912334332),
(6, 'u19vietnam', '900150983cd24fb0d6963f7d28e17f72', 'Trần Thành U19', 'u19vietnam@gmail.com', 'Viet Nam', 979497460),
(7, 'test123', '202cb962ac59075b964b07152d234b70', 'Đỗ Thị Thúy Trang', 'trangtrang@yahoo.com', 'United State', 931313232),
(9, 'test1233', 'e034fb6b66aacc1d48f445ddfb08da98', 'Sơn Tùng ATM', 'asd@mail', 'Viet Nam', 123456789),
(10, 'LinhPhan', '81dc9bdb52d04dc20036dbd8313ed055', 'Lĩnh Phan', 'linhphan@gmail.com       ', 'Nam Đàn', 1636764159),
(11, 'ngovanlinh', '79ec16df80b57696a03bb364410061f3', 'Ngô Văn Linh', 'linhvn@gmail.com', 'Ha Noi', 1642214431),
(12, 'project1', '827ccb0eea8a706c4c34a16891f84e7b', 'Phan Hồng Lĩnh', 'linhphanhust@gmail.com', 'Việt Nam', 987654323),
(14, 'dangnguyenanh', '202cb962ac59075b964b07152d234b70', 'Đặng Nguyễn Ánh', '123@gmail.com', 'Đống Đa - Hà Nội', 123445552),
(17, 'admin', 'c561ed8f4cf2d3f2e19c4f1c898cfbcb', 'Lĩnh Phan', 'admin@yahoo.com', 'Bách Khoa', 123435323),
(20, 'nguyenhongquan', '4297f44b13955235245b2497399d7a93', 'Nguyễn Hồng Quân', 'nguyenhongquan@gmail.com', 'TP. HCM', 989213322),
(21, 'namnhi', '3fe2f302d6f395252254e304a0a1fd36', 'Nam Nhi', 'namnhi@gmail.com', 'Phú Yên', 932323231),
(24, 'ngovanlinhbk', '81dc9bdb52d04dc20036dbd8313ed055', 'Linh VN', 'linhphanhust@gmail.com', 'Bách Khoa Hà Nội', 938373823),
(28, 'honglinhc4', '202cb962ac59075b964b07152d234b70', 'Linh Phan', 'linhphanhust@gmail.com', 'Nghệ An', 2342342),
(29, 'linhphan32', '21232f297a57a5a743894a0e4a801fc3', 'Lĩnh Phan', 'linhphanhust@gmail.com', 'Nam Đàn', 34242432),
(30, '141', '21232f297a57a5a743894a0e4a801fc3', 'Lĩnh Phan', 'linhphanhust@gmail.com', 'Nghệ An', 32423523);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`UserName`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`BillId`),
  ADD KEY `Key2_bill` (`UserID`);

--
-- Indexes for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`BillDetailId`),
  ADD KEY `Key3_BillDetail` (`PID`),
  ADD KEY `KEY2_BillDetail` (`BillId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Indexes for table `luat`
--
ALTER TABLE `luat`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`PID`),
  ADD KEY `KEY2_Product` (`SID`),
  ADD KEY `KEY3_Product` (`CategoryId`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SID`);

--
-- Indexes for table `su_kien`
--
ALTER TABLE `su_kien`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `UserName` (`UserName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `BillId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT for table `billdetail`
--
ALTER TABLE `billdetail`
  MODIFY `BillDetailId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `luat`
--
ALTER TABLE `luat`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=329;
--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `Key2_bill` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD CONSTRAINT `KEY2_BillDetail` FOREIGN KEY (`BillId`) REFERENCES `bill` (`BillId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Key3_BillDetail` FOREIGN KEY (`PID`) REFERENCES `products` (`PID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `KEY2_Product` FOREIGN KEY (`SID`) REFERENCES `suppliers` (`SID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Key3_Product` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
