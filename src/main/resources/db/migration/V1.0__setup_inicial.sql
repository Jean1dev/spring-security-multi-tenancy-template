CREATE TABLE public.tenant
(
    id    serial  NOT NULL,
    nome  varchar NOT NULL,
    ativo bool NULL DEFAULT true,
    CONSTRAINT "PK_TENANT" PRIMARY KEY (id)
);

CREATE TABLE public.usuario_acesso
(
    id       serial  NOT NULL,
    login     varchar NOT NULL,
    password varchar NOT NULL,
    tenant   int4    NOT NULL,
    CONSTRAINT "PK_USUARIO_ACESSO" PRIMARY KEY (id),
    CONSTRAINT "UNIQUE_LOGIN" UNIQUE (login)
);

ALTER TABLE public.usuario_acesso
    ADD CONSTRAINT usuario_acesso_tenant FOREIGN KEY (tenant) REFERENCES tenant (id) ON UPDATE CASCADE ON DELETE SET NULL;