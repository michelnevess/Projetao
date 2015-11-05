-- Coloquei a tabela no Banco postgres (user: postgres e password: postgres - vide database/Conexao.java)

CREATE TABLE pessoa
(
  id serial NOT NULL,
  nome character varying(60),
  sobrenome character varying(150),
  CONSTRAINT pesssoa_pk PRIMARY KEY (id)
)
