drop database if exists bd1;
create database if not exists bd1;
use bd1;
create table usuario(
id BIGINT auto_increment primary key,
nome varchar(150) not null unique,
senha varchar(32) not null
);
create table trabalho(
id BIGINT auto_increment primary key,
id_usr_autor BIGINT not null,
id_usr_orientador BIGINT not null,
introducao MEDIUMTEXT null,
contextualizacao MEDIUMTEXT null,
definicaodoproblema MEDIUMTEXT null,
motivacao MEDIUMTEXT null,
justificativa MEDIUMTEXT null,
contribuicao MEDIUMTEXT null,
objetivo MEDIUMTEXT null,
hipotese MEDIUMTEXT null,
delimitacoes MEDIUMTEXT null,
relevancia MEDIUMTEXT null,
trabalhosrelacionados MEDIUMTEXT null,
cronograma MEDIUMTEXT null,
metodologiadapesquisa MEDIUMTEXT null,
naturezaetipodepesquisa MEDIUMTEXT null,
localizacaoeperiododapesquisa MEDIUMTEXT null,
populacaoeamostra MEDIUMTEXT null,
variaveisdeestudo MEDIUMTEXT null,
tiposefontesdeinformacao MEDIUMTEXT null,
metodosinstrumentosetecnicasdecoletadedados MEDIUMTEXT null,
preTeste MEDIUMTEXT null,
tecnicasdeanaliseesistemasutilizados MEDIUMTEXT null,
consideracoesfinais MEDIUMTEXT null,
objetivosalcancados MEDIUMTEXT null,
hipotesesvalidadas MEDIUMTEXT null,
conclusaogeral MEDIUMTEXT null,
referenciasbibliograficas MEDIUMTEXT null,
foreign key(id_usr_autor) references usuario(id),
foreign key(id_usr_orientador) references usuario(id)
);