USE [master]
GO
/****** Object:  Database [SWP391]    Script Date: 11/11/2023 4:33:38 PM ******/
CREATE DATABASE [SWP391]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SWP391', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SWP391.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SWP391_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SWP391_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SWP391] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SWP391].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SWP391] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SWP391] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SWP391] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SWP391] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SWP391] SET ARITHABORT OFF 
GO
ALTER DATABASE [SWP391] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SWP391] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SWP391] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SWP391] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SWP391] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SWP391] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SWP391] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SWP391] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SWP391] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SWP391] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SWP391] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SWP391] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SWP391] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SWP391] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SWP391] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SWP391] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SWP391] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SWP391] SET RECOVERY FULL 
GO
ALTER DATABASE [SWP391] SET  MULTI_USER 
GO
ALTER DATABASE [SWP391] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SWP391] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SWP391] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SWP391] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SWP391] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SWP391] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SWP391', N'ON'
GO
ALTER DATABASE [SWP391] SET QUERY_STORE = OFF
GO
USE [SWP391]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[id] [int] NOT NULL,
	[first_name] [nvarchar](20) NULL,
	[last_name] [nvarchar](20) NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Landlord]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Landlord](
	[id] [int] NOT NULL,
	[first_name] [nvarchar](20) NULL,
	[last_name] [nvarchar](20) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [varchar](15) NULL,
	[civil_id] [nvarchar](200) NULL,
	[account_points] [int] NULL,
 CONSTRAINT [PK_Landlord] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[tenant_id] [int] NULL,
	[landlord_id] [int] NULL,
	[post_id] [int] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[price] [int] NULL,
	[type] [int] NULL,
	[area] [int] NULL,
	[NumOfBedrooms] [int] NULL,
	[address] [nvarchar](255) NULL,
	[description] [nvarchar](2000) NULL,
	[landlord_id] [int] NULL,
	[status] [int] NULL,
	[promotion_id] [int] NULL,
	[post_start_date] [date] NULL,
	[post_end_date] [date] NULL,
 CONSTRAINT [PK_Post] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post_Image]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post_Image](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[post_id] [int] NULL,
	[img_url] [nvarchar](300) NULL,
	[img_type] [int] NULL,
 CONSTRAINT [PK_Post_image] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Promotions]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Promotions](
	[promotion_id] [int] IDENTITY(1,1) NOT NULL,
	[discount] [int] NULL,
	[descriptions] [nvarchar](255) NULL,
	[promotion_start_date] [date] NULL,
	[promotion_end_date] [date] NULL,
 CONSTRAINT [PK_Promotions] PRIMARY KEY CLUSTERED 
(
	[promotion_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Property_type]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Property_type](
	[type_id] [int] IDENTITY(1,1) NOT NULL,
	[type_name] [nvarchar](50) NULL,
 CONSTRAINT [PK_typeID] PRIMARY KEY CLUSTERED 
(
	[type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Report]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Report](
	[report_id] [int] IDENTITY(1,1) NOT NULL,
	[reporter_id] [int] NULL,
	[property_id] [int] NULL,
	[reported_id] [int] NULL,
	[categories] [nvarchar](100) NULL,
	[description] [nvarchar](300) NULL,
	[status] [nvarchar](20) NULL,
 CONSTRAINT [PK_Report] PRIMARY KEY CLUSTERED 
(
	[report_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Review](
	[review_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[property_id] [int] NULL,
	[rating] [smallint] NULL,
	[review] [nvarchar](300) NULL,
 CONSTRAINT [PK_Review] PRIMARY KEY CLUSTERED 
(
	[review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tenant]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tenant](
	[id] [int] NOT NULL,
	[first_name] [nvarchar](20) NULL,
	[last_name] [nvarchar](20) NULL,
	[address] [nvarchar](255) NULL,
	[phone] [varchar](15) NULL,
 CONSTRAINT [PK_Tenant] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Token]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Token](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[token] [nvarchar](255) NULL,
	[expired_date] [datetime] NULL,
	[type] [int] NULL,
 CONSTRAINT [PK_Token] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transactions]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transactions](
	[transaction_id] [int] IDENTITY(1,1) NOT NULL,
	[amount] [decimal](19, 0) NULL,
	[payer_id] [int] NULL,
	[type] [nvarchar](20) NULL,
	[transaction_date] [date] NULL,
	[post_id] [int] NULL,
	[receiver_id] [int] NULL,
 CONSTRAINT [PK_Transaction] PRIMARY KEY CLUSTERED 
(
	[transaction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User_banned]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_banned](
	[id] [int] NOT NULL,
	[ban_start_date] [datetime] NULL,
	[ban_end_date] [datetime] NULL,
 CONSTRAINT [PK_User_banned] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](100) NULL,
	[hashed_password] [varchar](80) NULL,
	[role_id] [int] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Wishlist]    Script Date: 11/11/2023 4:33:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Wishlist](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[property_id] [int] NULL,
 CONSTRAINT [PK_Wishlist] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Admin] ([id], [first_name], [last_name]) VALUES (1, N'Test', N'Admin')
INSERT [dbo].[Admin] ([id], [first_name], [last_name]) VALUES (11, N'Thinh', N'Le')
GO
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (3, N'Test', N'Landlord', N'FPT University', N'0989302381', N'0268315749', 1730)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (4, N'Dinh', N'Tri', N'FPT University', N'0978321456', N'0973456182', 3000)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (5, N'Hai', N'Thinh', N'FPT University', N'0913897654', N'0413965287', 1000)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (6, N'Tien', N'Thinh', N'FPT University', N'0932198756', N'0539126478', 1000)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (7, N'Ngoc', N'Duc', N'FPT University', N'0951346798', N'0652438719', 1000)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (8, N'Duc', N'Hieu', N'FPT University', N'0385428319', N'0218975364', 1000)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (9, N'Phi', N'Long', N'FPT University', N'0383883590', N'0432157896', 1000)
INSERT [dbo].[Landlord] ([id], [first_name], [last_name], [address], [phone], [civil_id], [account_points]) VALUES (10, N'Tran', N'Minh', N'FPT University', N'0932178645', N'0931284765', 1000)
GO
SET IDENTITY_INSERT [dbo].[Post] ON 

INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (1, N'Nha Tro Ngoc Duc', 1000000, 1, 15, 1, N'XYZ', N'This is the description', 3, 2, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (3, N'Nha Tro Phi Long', 2000000, 1, 20, 2, N'270 Duong Lang, Dong Da, Ha Noi', N'Very suitable for couples or small families looking for convenience and comfort. With 2 bedrooms, it offers ample space, and the stunning views from the windows create a peaceful atmosphere. Located in the heart of Hanoi, you will have easy access to various amenities, making your daily life easier.', 6, 1, 5, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (4, N'Nha Tro Duc Hieu', 1800000, 1, 10, 1, N'27 Duong Song Da, Long Bien, Ha Noi', N'This apartment is perfect for those looking for peace and savings. With 1 bedroom, it offers a comfortable living space with all the necessary amenities. The surrounding scenery creates a peaceful atmosphere, making it an ideal place to escape the noise of the city. Located in Long Bien, it offers a convenient and peaceful lifestyle.', 6, 1, 4, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (5, N'Nha Tro Hai Thinh', 1000000, 1, 15, 2, N'210 Duong Hai Ba Trung, Cau Giay, Ha Noi', N'Nha Tro Hai Thinh is a suitable choice for families looking for spacious living space. With 2 bedrooms, it provides adequate space for your needs. Modern amenities and convenient location make it an excellent choice for urban living. Located in Cau Giay, Hanoi.', 7, 1, 5, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (6, N'Nha Tro Tien Thinh', 1200000, 1, 15, 1, N'120 Duong Ho Chi Minh, Cau Giay, Ha Noi', N'Nha Tro Tien Thinh is a convenient apartment for rent in a unique location. This apartment is perfect for singles looking for comfort and savings. With 1 bedroom, it offers a comfortable living space with all the necessary amenities. Located in Cau Giay, Hanoi.', 7, 1, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (7, N'Nha Tro Tran Minh', 1800000, 1, 20, 2, N'20 Duong Thang Long, Dong Da, Ha Noi', N'a great rental opportunity in a bustling neighborhood. This property is perfect for urban dwellers seeking convenience and comfort. With 2 bedrooms, it offers a spacious living environment with all the modern amenities you need. The bustling neighborhood provides access to restaurants, shopping centers, and entertainment options. Located in Dong Da, Ha Noi, you will be in the heart of the action, making your daily life enjoyable and hassle-free.', 7, 1, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (8, N'Chung Cu Hien Dai', 1500000, 2, 18, 1, N'75 Duong Giai Phong, Thanh Xuan, Ha Noi', N'Chung Cu Hien Dai is a luxury condo for rent with top-notch facilities. This property is ideal for those who desire luxury living and a prestigious address. With 1 bedroom, it offers a comfortable and elegant living space. The condo building boasts amenities such as a rooftop pool, fitness center, and 24/7 security. Located in Thanh Xuan, Ha Noi.', 8, 1, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (9, N'Chung Cu Binh Minh', 1200000, 2, 14, 1, N'25 Duong Le Hong Phong, Hoan Kiem, Ha Noi', N'Chung Cu Binh Minh is a downtown apartment available for urban living. This property is perfect for professionals looking for a central location. With 1 bedroom, it provides a cozy living space in the heart of Ha Noi. The convenience of downtown living awaits you in this apartment located in Hoan Kiem, Ha Noi.', 8, 1, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (10, N'Chung Cu Hoa Binh', 1500000, 2, 20, 1, N'234 Yen Lang, Dong Da, Ha Noi', N'offers a comfortable living space in a convenient location. This property is suitable for singles or small families looking for accessibility. With 1 bedroom, it provides a cozy living environment with all the essential amenities. The convenient location in Dong Da, Ha Noi, ensures easy access to public transportation, shops, and dining options. Experience comfortable city living in this well-located apartment.', 6, 2, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
INSERT [dbo].[Post] ([id], [name], [price], [type], [area], [NumOfBedrooms], [address], [description], [landlord_id], [status], [promotion_id], [post_start_date], [post_end_date]) VALUES (11, N'Chung Cu Tu Do', 2100000, 2, 20, 3, N'250 Yen Lang, Dong Da, Ha Noi', N'Chung Cu Tu Do is a spacious apartment for rent with great views. This property is ideal for families seeking a comfortable and spacious living environment. With 3 bedrooms. The large windows provide scenic views, creating a pleasant atmosphere. Located in Dong Da, Ha Noi, you will have access to parks, schools, and shopping centers, making it a convenient choice for families.', 6, 1, NULL, CAST(N'2023-09-30' AS Date), CAST(N'2023-10-30' AS Date))
SET IDENTITY_INSERT [dbo].[Post] OFF
GO
SET IDENTITY_INSERT [dbo].[Post_Image] ON 

INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (1, 1, N'assets\property_1\1.jpg', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (2, 1, N'assets\property_1\2.jpg', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (3, 1, N'assets\property_1\3.jpg', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (4, 1, N'assets\property_1\4.jpg', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (5, 1, N'assets\property_1\5.jpg', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (6, 1, N'assets\property_1\6.jpg', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (15, 3, N'assets\property_3\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (16, 3, N'assets\property_3\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (17, 3, N'assets\property_3\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (18, 3, N'assets\property_3\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (19, 4, N'assets\property_4\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (20, 4, N'assets\property_4\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (21, 4, N'assets\property_4\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (22, 4, N'assets\property_4\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (23, 5, N'assets\property_5\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (24, 5, N'assets\property_5\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (25, 5, N'assets\property_5\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (26, 5, N'assets\property_5\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (27, 5, N'assets\property_5\5.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (28, 6, N'assets\property_6\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (29, 6, N'assets\property_6\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (30, 6, N'assets\property_6\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (31, 6, N'assets\property_6\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (32, 7, N'assets\property_7\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (33, 7, N'assets\property_7\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (34, 7, N'assets\property_7\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (35, 7, N'assets\property_7\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (36, 8, N'assets\property_8\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (37, 8, N'assets\property_8\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (38, 8, N'assets\property_8\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (39, 8, N'assets\property_8\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (40, 8, N'assets\property_8\5.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (41, 8, N'assets\property_8\6.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (43, 9, N'assets\property_9\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (44, 9, N'assets\property_9\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (45, 9, N'assets\property_9\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (46, 9, N'assets\property_9\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (47, 9, N'assets\property_9\5.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (48, 9, N'assets\property_9\6.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (51, 10, N'assets\property_10\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (52, 10, N'assets\property_10\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (53, 10, N'assets\property_10\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (54, 10, N'assets\property_10\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (55, 11, N'assets\property_11\1.webp', N'1')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (56, 11, N'assets\property_11\2.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (57, 11, N'assets\property_11\3.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (58, 11, N'assets\property_11\4.webp', N'0')
INSERT [dbo].[Post_Image] ([id], [post_id], [img_url], [img_type]) VALUES (59, 11, N'assets\property_11\5.webp', N'0')
SET IDENTITY_INSERT [dbo].[Post_Image] OFF
GO
SET IDENTITY_INSERT [dbo].[Promotions] ON 

INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (1, 20, N'20% discount for the first 3 months of rent', CAST(N'2023-06-21' AS Date), CAST(N'2023-07-21' AS Date))
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (2, 10, N'10% discount for the first month of rent', CAST(N'2023-06-21' AS Date), CAST(N'2023-07-21' AS Date))
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (3, 50, N'50% for the first month if the length of contract is more than 3 months', CAST(N'2023-06-21' AS Date), CAST(N'2023-07-21' AS Date))
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (4, 11, N'88', CAST(N'2023-10-27' AS Date), CAST(N'2023-11-04' AS Date))
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (5, 48, N'abc', CAST(N'2023-10-27' AS Date), NULL)
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (6, 12, N'313123123123', CAST(N'2023-10-28' AS Date), CAST(N'2023-10-29' AS Date))
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (7, 23, N'2313123', CAST(N'2023-10-28' AS Date), CAST(N'2023-10-29' AS Date))
INSERT [dbo].[Promotions] ([promotion_id], [discount], [descriptions], [promotion_start_date], [promotion_end_date]) VALUES (8, 23, N'2313123', CAST(N'2023-10-28' AS Date), CAST(N'2023-10-29' AS Date))
SET IDENTITY_INSERT [dbo].[Promotions] OFF
GO
SET IDENTITY_INSERT [dbo].[Property_type] ON 

INSERT [dbo].[Property_type] ([type_id], [type_name]) VALUES (1, N'Nha Tro')
INSERT [dbo].[Property_type] ([type_id], [type_name]) VALUES (2, N'Chung cu')
SET IDENTITY_INSERT [dbo].[Property_type] OFF
GO
SET IDENTITY_INSERT [dbo].[Review] ON 

INSERT [dbo].[Review] ([review_id], [user_id], [property_id], [rating], [review]) VALUES (1, 2, 1, 5, N'Very good')
SET IDENTITY_INSERT [dbo].[Review] OFF
GO
INSERT [dbo].[Tenant] ([id], [first_name], [last_name], [address], [phone]) VALUES (2, N'Test', N'Tenant', N'FPT University', N'0123456789')
GO
SET IDENTITY_INSERT [dbo].[Token] ON 

INSERT [dbo].[Token] ([id], [user_id], [token], [expired_date], [type]) VALUES (1, 11, N'z4znPBn205LpIwDZ1qlyqGhbL0c', CAST(N'2023-10-27T12:12:08.000' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Token] OFF
GO
SET IDENTITY_INSERT [dbo].[Transactions] ON 

INSERT [dbo].[Transactions] ([transaction_id], [amount], [payer_id], [type], [transaction_date], [post_id], [receiver_id]) VALUES (1, CAST(1000 AS Decimal(19, 0)), 1, N'2', CAST(N'2023-09-09' AS Date), NULL, 3)
INSERT [dbo].[Transactions] ([transaction_id], [amount], [payer_id], [type], [transaction_date], [post_id], [receiver_id]) VALUES (3, CAST(1000 AS Decimal(19, 0)), 1, N'2', CAST(N'2023-09-09' AS Date), NULL, 3)
INSERT [dbo].[Transactions] ([transaction_id], [amount], [payer_id], [type], [transaction_date], [post_id], [receiver_id]) VALUES (4, CAST(480 AS Decimal(19, 0)), 3, N'1', CAST(N'2023-10-11' AS Date), 1, NULL)
INSERT [dbo].[Transactions] ([transaction_id], [amount], [payer_id], [type], [transaction_date], [post_id], [receiver_id]) VALUES (5, CAST(1000 AS Decimal(19, 0)), 3, N'1', CAST(N'2023-08-09' AS Date), 3, NULL)
INSERT [dbo].[Transactions] ([transaction_id], [amount], [payer_id], [type], [transaction_date], [post_id], [receiver_id]) VALUES (6, CAST(1000 AS Decimal(19, 0)), 1, N'2', CAST(N'2023-09-10' AS Date), NULL, 3)
INSERT [dbo].[Transactions] ([transaction_id], [amount], [payer_id], [type], [transaction_date], [post_id], [receiver_id]) VALUES (7, CAST(1000 AS Decimal(19, 0)), 1, N'2', CAST(N'2023-10-10' AS Date), NULL, 8)
SET IDENTITY_INSERT [dbo].[Transactions] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (1, N'testAdmin@gmail.com', N'25d55ad283aa400af464c76d713c07ad', 3, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (2, N'testTenant@gmail.com', N'25d55ad283aa400af464c76d713c07ad', 1, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (3, N'testLandlord@gmail.com', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (4, N'trandinhtri@fe.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (5, N'thinhlhhe170034@fpt.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (6, N'thinhpt172284@fpt.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (7, N'ducnnhe176024@fpt.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (8, N'hieundhe170151@fpt.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (9, N'longnphe170854@fpt.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (10, N'minhnthe176001@fpt.edu.vn', N'25d55ad283aa400af464c76d713c07ad', 2, 3)
INSERT [dbo].[Users] ([id], [email], [hashed_password], [role_id], [status]) VALUES (11, N'nguyenphilong4123@gmail.com', N'5B23517E3F38EAC73C3E503F5FCFFAA8', 3, 3)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET IDENTITY_INSERT [dbo].[Wishlist] ON 

INSERT [dbo].[Wishlist] ([id], [user_id], [property_id]) VALUES (12, 2, 5)
INSERT [dbo].[Wishlist] ([id], [user_id], [property_id]) VALUES (13, 2, 4)
INSERT [dbo].[Wishlist] ([id], [user_id], [property_id]) VALUES (14, 2, 6)
SET IDENTITY_INSERT [dbo].[Wishlist] OFF
GO
ALTER TABLE [dbo].[Admin]  WITH CHECK ADD  CONSTRAINT [FK_Admin_User] FOREIGN KEY([id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Admin] CHECK CONSTRAINT [FK_Admin_User]
GO
ALTER TABLE [dbo].[Landlord]  WITH CHECK ADD  CONSTRAINT [FK_Landlord_User] FOREIGN KEY([id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Landlord] CHECK CONSTRAINT [FK_Landlord_User]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Landlord] FOREIGN KEY([landlord_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Landlord]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Post] FOREIGN KEY([post_id])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Post]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Tenant] FOREIGN KEY([tenant_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Tenant]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [FK_Post_Landlord] FOREIGN KEY([landlord_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [FK_Post_Landlord]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [FK_Post_PropertyType] FOREIGN KEY([type])
REFERENCES [dbo].[Property_type] ([type_id])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [FK_Post_PropertyType]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [FK_Promotions_Post] FOREIGN KEY([promotion_id])
REFERENCES [dbo].[Promotions] ([promotion_id])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [FK_Promotions_Post]
GO
ALTER TABLE [dbo].[Post_Image]  WITH CHECK ADD  CONSTRAINT [FK_Post_image_Post] FOREIGN KEY([post_id])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Post_Image] CHECK CONSTRAINT [FK_Post_image_Post]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [FK_Report_Post] FOREIGN KEY([property_id])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [FK_Report_Post]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [FK_Report_Tenant1] FOREIGN KEY([reporter_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [FK_Report_Tenant1]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [FK_Report_Tenant2] FOREIGN KEY([reported_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [FK_Report_Tenant2]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK_Review_Post] FOREIGN KEY([property_id])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK_Review_Post]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK_Review_Tenant] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK_Review_Tenant]
GO
ALTER TABLE [dbo].[Tenant]  WITH CHECK ADD  CONSTRAINT [FK_Tenant_User] FOREIGN KEY([id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Tenant] CHECK CONSTRAINT [FK_Tenant_User]
GO
ALTER TABLE [dbo].[Token]  WITH CHECK ADD  CONSTRAINT [FK_Token_id] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Token] CHECK CONSTRAINT [FK_Token_id]
GO
ALTER TABLE [dbo].[Transactions]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_Landlord] FOREIGN KEY([payer_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Transactions] CHECK CONSTRAINT [FK_Transaction_Landlord]
GO
ALTER TABLE [dbo].[Transactions]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_Post] FOREIGN KEY([post_id])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Transactions] CHECK CONSTRAINT [FK_Transaction_Post]
GO
ALTER TABLE [dbo].[User_banned]  WITH CHECK ADD  CONSTRAINT [FK_User_banned_id] FOREIGN KEY([id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[User_banned] CHECK CONSTRAINT [FK_User_banned_id]
GO
ALTER TABLE [dbo].[Wishlist]  WITH CHECK ADD  CONSTRAINT [FK_Wishlist_Post] FOREIGN KEY([property_id])
REFERENCES [dbo].[Post] ([id])
GO
ALTER TABLE [dbo].[Wishlist] CHECK CONSTRAINT [FK_Wishlist_Post]
GO
ALTER TABLE [dbo].[Wishlist]  WITH CHECK ADD  CONSTRAINT [FK_Wishlist_Tenant] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Wishlist] CHECK CONSTRAINT [FK_Wishlist_Tenant]
GO
USE [master]
GO
ALTER DATABASE [SWP391] SET  READ_WRITE 
GO
