create schema if not exists auditoria;

-- Create sequence for revision numbers
CREATE SEQUENCE if not exists auditoria.revinfo_seq;

CREATE TABLE if not exists auditoria.revinfo (
    rev INTEGER NOT NULL,
    revtstmp BIGINT,
    PRIMARY KEY (rev)
);

CREATE TABLE if not exists auditoria.usuario_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    nome VARCHAR(100),
    username VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

CREATE TABLE if not exists auditoria.categoria_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    nome VARCHAR(100),
    url_imagem_categoria TEXT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

CREATE TABLE if not exists auditoria.instituicao_financeira_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    nome VARCHAR(100),
    logo_url TEXT,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

CREATE TABLE if not exists auditoria.conta_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    fk_usuario BIGINT,
    fk_instituicao BIGINT,
    apelido VARCHAR(100),
    tipo_conta VARCHAR(50),
    saldo_atual DECIMAL(15, 2),
    data_ultima_sincronizacao TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

CREATE TABLE if not exists auditoria.transacao_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    fk_conta BIGINT,
    fk_categoria BIGINT,
    descricao VARCHAR(255),
    valor DECIMAL(15, 2),
    tipo_transacao VARCHAR(10),
    data_transacao DATE,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

CREATE TABLE if not exists auditoria.orcamento_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    fk_usuario BIGINT,
    fk_categoria BIGINT,
    valor_maximo DECIMAL(15, 2),
    mes INTEGER,
    ano INTEGER,
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);