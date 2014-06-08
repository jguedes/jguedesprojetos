drop database if exists ptaol_bd;
create database if not exists ptaol_bd;
use ptaol_bd;
create table usuario(
id BIGINT auto_increment primary key,
nome varchar(150) not null unique,
senha varchar(32) not null,
email varchar(150) not null unique
);
create table documento(
id BIGINT auto_increment primary key,
id_user_Dono BIGINT not null,
instituicao varchar(250) null default 'INSTITUIÇÃO',
titulo varchar(150) not null,
localdata varchar(150) null default 'LOCAL E DATA',
introducao varchar(250) null default 'INTRODUÇÃO',
desenvolvimento varchar(250) null default 'DESENVOLVIMENTO',
conclusao varchar(250) null default 'CONCLUSÃO',
dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
foreign key(id_user_Dono) references usuario(id) on delete cascade
);
create table compartilhamento(
id BIGINT auto_increment primary key,
id_documento BIGINT not null,
id_user_compartilhado BIGINT not null,
permissao TINYINT DEFAULT 0,
dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
foreign key(id_documento) references documento(id) on delete cascade,
foreign key(id_user_compartilhado) references usuario(id) on delete cascade
);
create table comentario(
id BIGINT auto_increment primary key,
id_compartilhamento BIGINT not null,
elementoDeDocumento varchar(20) not null,
texto varchar(250) not null,
dataResolvido TIMESTAMP null,
dataPostagem TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
foreign key(id_compartilhamento) references compartilhamento(id) on delete cascade
);
