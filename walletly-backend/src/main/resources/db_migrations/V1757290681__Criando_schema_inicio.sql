CREATE SCHEMA SEGURANCA;
CREATE SCHEMA GERAL;

CREATE TABLE segurança.usuario (
    id BIGSERIAL PRIMARY KEY,
    nome varchar(100) NOT NULL,
    username varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL
);

CREATE TABLE geral.instituicao_financeira (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    logo_url TEXT NOT NULL
);

CREATE TABLE geral.conta (
    id BIGSERIAL PRIMARY KEY,
    fk_usuario BIGINT NOT NULL,
    fk_instituicao BIGINT NOT NULL,
    apelido VARCHAR(100) NOT NULL,
    tipo_conta VARCHAR(50) NOT NULL,
    saldo_atual DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    data_ultima_sincronizacao TIMESTAMP,
    FOREIGN KEY (fk_usuario) REFERENCES seguranca.usuario(id),
    FOREIGN KEY (fk_instituicao) REFERENCES geral.instituicao_financeira(id)
);

CREATE TABLE geral.categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    url_imagem_categoria TEXT NOT NULL
);

CREATE TABLE geral.transacao (
    id SERIAL PRIMARY KEY,
    fk_conta BIGINT NOT NULL,
    fk_categoria BIGINT,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    tipo_transacao VARCHAR(10) NOT NULL,
    data_transacao TIMESTAMP NOT NULL, 

    FOREIGN KEY (fk_conta) REFERENCES geral.conta(id),
    FOREIGN KEY (fk_categoria) REFERENCES geral.categoria(id)
);

CREATE TABLE geral.orcamento (
    id SERIAL PRIMARY KEY,
    fk_usuario INTEGER NOT NULL,
    fk_categoria INTEGER NOT NULL,
    valor_maximo DECIMAL(15, 2) NOT NULL,
    mes INTEGER NOT NULL,
    ano INTEGER NOT NULL,

    FOREIGN KEY (fk_usuario) REFERENCES seguranca.usuario(id),
    FOREIGN KEY (fk_categoria) REFERENCES Categorias(id),
    UNIQUE (id_usuario, id_categoria, mes, ano)
);
