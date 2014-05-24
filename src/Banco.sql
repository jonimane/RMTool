--
-- Table structure for table `participa`
--

CREATE TABLE IF NOT EXISTS `participa` (
  `usuario_id` int(11) NOT NULL,
  `projeto_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`projeto_id`),
  KEY `FK94B158AD2A77D0BC` (`usuario_id`),
  KEY `FK94B158AD1D7C909C` (`projeto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `participa`
--

INSERT INTO `participa` (`usuario_id`, `projeto_id`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `projeto`
--

CREATE TABLE IF NOT EXISTS `projeto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` longtext,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `projeto`
--

INSERT INTO `projeto` (`id`, `descricao`, `nome`) VALUES
(1, 'Descrição do Projeto', 'RMTool'),
(2, 'Descrição do Projeto SuperRO', 'SuperRO');

-- --------------------------------------------------------

--
-- Table structure for table `requisito`
--

CREATE TABLE IF NOT EXISTS `requisito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `criacao` datetime DEFAULT NULL,
  `descricao` longtext,
  `nome` varchar(255) DEFAULT NULL,
  `prioridade` bit(1) DEFAULT NULL,
  `situacao` bit(1) DEFAULT NULL,
  `tipo` bit(1) DEFAULT NULL,
  `versao` varchar(255) DEFAULT NULL,
  `projeto_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2994CE032A77D0BC` (`usuario_id`),
  KEY `FK2994CE031D7C909C` (`projeto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `login`, `nome`, `senha`) VALUES
(1, 'jonimane@gmail.com', 'jonimane', 'José Nicodemos Maia Neto', '11802'),
(2, 'niquitasv@gmail.com', 'niquitasv', 'Monique dos Santos', '1180202');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `participa`
--
ALTER TABLE `participa`
  ADD CONSTRAINT `FK94B158AD1D7C909C` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`),
  ADD CONSTRAINT `FK94B158AD2A77D0BC` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Constraints for table `requisito`
--
ALTER TABLE `requisito`
  ADD CONSTRAINT `FK2994CE031D7C909C` FOREIGN KEY (`projeto_id`) REFERENCES `projeto` (`id`),
  ADD CONSTRAINT `FK2994CE032A77D0BC` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);