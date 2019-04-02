-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 05 Mei 2018 pada 02.58
-- Versi Server: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentaldvd`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data`
--

CREATE TABLE `data` (
  `id_data` varchar(5) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `kode_dvd` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dvd`
--

CREATE TABLE `dvd` (
  `kode_dvd` varchar(5) NOT NULL,
  `jenis_film` varchar(10) NOT NULL,
  `judul_film` varchar(20) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga_sewa` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `dvd`
--

INSERT INTO `dvd` (`kode_dvd`, `jenis_film`, `judul_film`, `stok`, `harga_sewa`) VALUES
('D105', 'Aksi', 'DeadPool', 29, 2000),
('D106', 'Aksi', 'Thor Ragnarok', 14, 2000),
('D200', 'Horor', 'Pengabdi Kosan', 69, 2000),
('D205', 'Komedi', 'Single', 5, 2000),
('D300', 'Komedi', 'Malam Mingu Miko', 15, 2500),
('D405', 'Aksi', 'Wonder Woman', 5, 2000),
('R041', 'Drama', 'Sendiri', 20, 2000),
('R105', 'Drama', 'DOTS', 10, 2000),
('R110', 'Aksi', 'Kungfu Kids', 29, 2000),
('R201', 'Drama', 'Cinta Putih Abu', 25, 2000),
('SU040', 'Komedi', 'Kambing Jantan', 10, 2000),
('SU205', 'Komedi', 'Teletubies', 5, 2000),
('SU500', 'Aksi', 'Power Ranger', 97, 2000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `no_telp` varchar(25) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_antre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `no_telp`, `alamat`, `no_antre`) VALUES
(101, 'Titin', '082311223345', 'Jl.Ryacudu', 201),
(105, 'Aan', '098765789', 'Sukabumi', 205),
(106, 'Imam Prabowo', '08976352618', 'Kosan Pakde Bengkel', 206),
(107, 'Alvijar', '0858765439', 'Sukarame', 207);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_transaksi`
--

CREATE TABLE `tb_transaksi` (
  `id_transaksi` int(4) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `kode_dvd` varchar(5) CHARACTER SET latin1 NOT NULL,
  `tgl_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data untuk tabel `tb_transaksi`
--

INSERT INTO `tb_transaksi` (`id_transaksi`, `id_pelanggan`, `kode_dvd`, `tgl_sewa`, `tgl_kembali`, `jumlah`, `total`) VALUES
(2, 101, 'D200', '2018-05-02', '2018-05-09', 1, '2000'),
(3, 105, 'SU500', '2018-05-03', '2018-05-05', 3, '6000'),
(4, 106, 'D105', '2018-05-01', '2018-05-08', 1, '2000'),
(5, 107, 'D106', '2018-05-01', '2018-05-08', 1, '2000');

--
-- Trigger `tb_transaksi`
--
DELIMITER $$
CREATE TRIGGER `stok_jual` AFTER INSERT ON `tb_transaksi` FOR EACH ROW begin
update dvd set 	stok = 	stok - new.jumlah
where kode_dvd = new.kode_dvd;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `no_antre` int(11) NOT NULL,
  `tgl_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`no_antre`, `tgl_sewa`, `tgl_kembali`) VALUES
(200, '2018-01-28', '2018-02-04'),
(201, '2018-02-04', '2018-02-11'),
(202, '2018-02-15', '2018-02-22'),
(203, '2018-02-25', '2018-03-04'),
(204, '2018-03-01', '2018-03-08'),
(205, '2018-11-28', '2018-12-04'),
(206, '2018-01-01', '2018-01-08'),
(207, '2018-03-07', '2018-03-14'),
(208, '2018-03-07', '2018-03-10'),
(209, '2018-04-01', '2018-04-02'),
(210, '2018-04-01', '2018-04-08'),
(211, '2018-04-02', '2018-04-03'),
(212, '2018-05-01', '2018-05-08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id_data`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `kode_dvd` (`kode_dvd`);

--
-- Indexes for table `dvd`
--
ALTER TABLE `dvd`
  ADD PRIMARY KEY (`kode_dvd`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`),
  ADD KEY `no_antre` (`no_antre`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `tb_transaksi_ibfk_2` (`kode_dvd`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_antre`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `id_transaksi` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD CONSTRAINT `pelanggan_ibfk_1` FOREIGN KEY (`no_antre`) REFERENCES `transaksi` (`no_antre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD CONSTRAINT `tb_transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_transaksi_ibfk_2` FOREIGN KEY (`kode_dvd`) REFERENCES `dvd` (`kode_dvd`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
