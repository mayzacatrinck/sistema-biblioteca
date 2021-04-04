CREATE DATABASE sistema_biblioteca;

use sistema_biblioteca;

CREATE TABLE cadastro (
id INT PRIMARY KEY auto_increment,
cpf VARCHAR(11) NOT NULL,
nome VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
telefone VARCHAR(255) NOT NULL,
usuario VARCHAR(20) NOT NULL,
senha VARCHAR(255) NOT NULL,
endereco_id INT NOT NULL
);


CREATE TABLE endereco (
id INT PRIMARY KEY auto_increment,
cep VARCHAR(255) NOT NULL,
logradouro VARCHAR(255) NOT NULL,
bairro VARCHAR(255) NOT NULL,
localidade VARCHAR(255) NOT NULL,
uf VARCHAR(255) NOT NULL,
ibge INT 
);


CREATE TABLE locacao (
id INT PRIMARY KEY auto_increment,
data_agendamento DATE NOT NULL,
data_retirada DATE,
data_finalizacao DATE,
valor_total DOUBLE,
status ENUM ('RESERVADA', 'EFETIVADA', 'FINALIZADA'),
cadastro_id INT NOT NULL
);


CREATE TABLE locacao_item (
id INT PRIMARY KEY auto_increment,
data_previsao_entrega DATE,
data_entrega DATE,
diarias INT,
valor_diaria DOUBLE,
valor_locacao DOUBLE,
livro_id INT NOT NULL,
locacao_id INT NOT NULL
);

CREATE TABLE livro(
id INT PRIMARY KEY auto_increment,
isbn VARCHAR(255) NOT NULL,
titulo VARCHAR(255) NOT NULL,
valor_diaria DOUBLE NOT NULL,
exemplares INT NOT NULL,
reservados INT NOT NULL
);


ALTER TABLE cadastro
ADD CONSTRAINT fk_id_end
FOREIGN KEY (endereco_id) REFERENCES endereco(id);

ALTER TABLE cadastro
ADD CONSTRAINT un_cpf
UNIQUE (cpf);

ALTER TABLE cadastro
ADD CONSTRAINT un_usuario
UNIQUE (usuario);

ALTER TABLE locacao
ADD CONSTRAINT fk_id_cad
FOREIGN KEY (cadastro_id) REFERENCES cadastro(id);

ALTER TABLE locacao_item
ADD CONSTRAINT fk_id_loc
FOREIGN KEY (locacao_id) REFERENCES locacao(id);

ALTER TABLE locacao_item
ADD CONSTRAINT fk_id_liv
FOREIGN KEY (livro_id) REFERENCES livro(id);











