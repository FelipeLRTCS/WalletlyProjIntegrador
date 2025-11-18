create table if not exists seguranca.roles (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

create table if not exists seguranca.usuario_roles (
    usuario_fk BIGINT NOT NULL,
    role_fk BIGINT NOT NULL,
    PRIMARY KEY (usuario_fk, role_fk),
    FOREIGN KEY (usuario_fk) REFERENCES seguranca.usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (role_fk) REFERENCES seguranca.roles(id) ON DELETE CASCADE
);

CREATE TABLE if not exists auditoria.roles_aud (
    id BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    nome VARCHAR(50),
    PRIMARY KEY (id, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

CREATE TABLE if not exists auditoria.usuario_roles_aud (
    usuario_fk BIGINT NOT NULL,
    role_fk BIGINT NOT NULL,
    rev INTEGER NOT NULL,
    revtype SMALLINT,
    PRIMARY KEY (usuario_fk, role_fk, rev),
    FOREIGN KEY (rev) REFERENCES auditoria.revinfo(rev)
);

insert into seguranca.roles (nome) values ('ROLE_USER') on conflict do nothing;
insert into seguranca.roles (nome) values ('ROLE_ADMIN') on conflict do nothing;