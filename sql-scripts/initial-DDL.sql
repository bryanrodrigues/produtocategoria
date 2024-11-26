-- Criando sequences
CREATE SEQUENCE seq_categoria_id;
CREATE SEQUENCE seq_produto_id;

-- Criando  tabela categoria
CREATE TABLE categoria (
                           id INTEGER PRIMARY KEY DEFAULT nextval('seq_categoria_id'),
                           nome VARCHAR(255) NOT NULL UNIQUE
);

-- Criando tabela produto
CREATE TABLE produto (
                         id INTEGER PRIMARY KEY DEFAULT nextval('seq_produto_id'),
                         nome VARCHAR(255) NOT NULL,
                         valor NUMERIC(10, 2) NOT NULL CHECK (valor > 0),
                         descricao TEXT,
                         ativo BOOLEAN DEFAULT TRUE,
                         categoria_id INTEGER REFERENCES categoria(id) ON DELETE SET NULL
);

-- Criando índices
CREATE INDEX idx_produto_categoria_id ON produto(categoria_id);
CREATE INDEX idx_categoria_nome ON categoria(nome);