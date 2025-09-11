-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 11-09-2025 a las 00:05:00
-- Versión del servidor: 8.0.43
-- Versión de PHP: 8.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tarea1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `id` int NOT NULL,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `categoria` varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `cupos` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`id`, `nombre`, `descripcion`, `fecha`, `hora`, `categoria`, `precio`, `cupos`) VALUES
(2, 'Batallon de enanos', 'batalla epica de enanos la cual se realizara en formato boxeo', '2025-10-25', '11:20:00', 'Show', 12000, 22),
(3, 'Epicas batallas del rap del frikismo', 'batallas de rap las cuales se enfrentan 2 interpretadores los cuales basan su personaje en un dibujo animado', '2025-09-09', '10:21:00', 'Charla', 1231, 1),
(4, 'Torneo PvP', 'torneo minecraft Pvp', '2025-09-10', '00:01:00', 'Show', 10000, 3),
(5, 'Como ganarle a un gorila', 'Charla para conversar sobre como 100 hombres pueden derrotar a un gorilla', '2025-09-10', '00:16:00', 'Charla', 123, 0),
(6, 'Taller de desarrollo de pie', 'Taller para poder lograr crecer el pie', '2025-09-10', '00:18:00', 'Taller', 100000, 123),
(7, 'Aprende a como robar', 'charla la cual intenta demostrar las cualidades de los robos', '2026-10-10', '10:11:00', 'Taller', 1000, 5),
(8, 'Taller de como cortar jamon serrano', 'Aprende a cortar jamon serrano en 5 simples pasos', '2025-12-10', '10:22:00', 'Taller', 10213, 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `user` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`user`, `password`) VALUES
('carlos', 'carlos987'),
('juan', 'pass123'),
('laura', 'laura321'),
('maria', 'maria456'),
('pedro', 'pedro789');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
