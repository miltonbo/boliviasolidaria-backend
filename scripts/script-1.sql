DROP TABLE IF EXISTS `solicitud`;
DROP TABLE IF EXISTS `punto_acopio`;

CREATE TABLE `solicitud` (
  `id` int(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `necesidad` text DEFAULT NULL,
  `contacto` varchar(20) DEFAULT null,
  `ci` varchar(10) DEFAULT NULL,
  `latitud` decimal(10,6) DEFAULT NULL,
  `longitud` decimal(10,6) DEFAULT NULL,
  `fechahora_solicitud` datetime NOT NULL,
  `estado` tinyint(4) NOT NULL,
  KEY `id` (`id`)
);

CREATE TABLE `punto_acopio` (
  `id` int(20) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `contacto` varchar(20) DEFAULT NULL,
  `latitud` decimal(10,6) DEFAULT NULL,
  `longitud` decimal(10,6) DEFAULT NULL,
  KEY `id` (`id`)
);
