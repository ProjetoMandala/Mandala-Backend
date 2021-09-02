CREATE TABLE `Usuario` (
	`id_usuario` bigint NOT NULL AUTO_INCREMENT,
	`nome` varchar(255) NOT NULL,
	`data_nascimento` DATE NOT NULL,
	`genero` varchar(50),
	`biografia` varchar(160),
	`email` varchar(100) NOT NULL,
	`senha` varchar(255) NOT NULL,
	PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `post` (
	`id_post` bigint NOT NULL AUTO_INCREMENT,
	`titulo` varchar(100) NOT NULL,
	`texto` varchar(1000),
	`imagem` varchar(255),
	`video` varchar(255),
	`curtida` int(255),
	`fk_usuario` bigint(255) NOT NULL,
	`fk_tema` bigint(255) NOT NULL,
	PRIMARY KEY (`id_post`)
);

CREATE TABLE `tema` (
	`id_tema` bigint NOT NULL AUTO_INCREMENT,
	`titulo` varchar(100) NOT NULL,
	`palavra_chave` varchar(20),
	`imagem` varchar(255),
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id_tema`)
);

ALTER TABLE `post` ADD CONSTRAINT `post_fk0` FOREIGN KEY (`fk_usuario`) REFERENCES `Usuario`(`id_usuario`);

ALTER TABLE `post` ADD CONSTRAINT `post_fk1` FOREIGN KEY (`fk_tema`) REFERENCES `tema`(`id_tema`);




