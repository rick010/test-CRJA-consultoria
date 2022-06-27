INSERT INTO TB_DEPARTAMENTO  (titulo) VALUES ('Financeiro');
INSERT INTO TB_DEPARTAMENTO  (titulo) VALUES ('Comercial');
INSERT INTO TB_DEPARTAMENTO  (titulo) VALUES ('Desenvolvimento');

INSERT INTO TB_PESSOA  (nome, id_departamento) VALUES ('Camila', 1);
INSERT INTO TB_PESSOA  (nome, id_departamento) VALUES ('Pedro', 2);
INSERT INTO TB_PESSOA  (nome, id_departamento) VALUES ('Fabiano', 3);
INSERT INTO TB_PESSOA  (nome, id_departamento) VALUES ('Raquel', 3);
INSERT INTO TB_PESSOA  (nome, id_departamento) VALUES ('Patricia', 3);
INSERT INTO TB_PESSOA  (nome, id_departamento) VALUES ('Joaquim', 1);

INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', 14, '2022-02-15', 'true', 1, 1);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa ) VALUES ('Bug 352', 'Corrigir bug 352 na versão 1.25', 25, '2022-05-10', 'false', 1, null);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Liberação da versão 1.24', 'Disponibilizar pacote para testes', 2, '2022-02-02', 'false', 3, 3);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa ) VALUES ('Reunião A', 'Reunião com cliente A para apresentação do produto', 5, '2022-02-05', 'false', 2, null);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa ) VALUES ('Reunião final', 'Fechamento contrato', 6, '2022-03-28', 'false', 2, null);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Pagamento 01/2022', 'Realizar pagamento dos fornecedores', 6, '2022-01-31', 'true', 1, 1);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Bug 401', 'Corrigir bug 401 na versão 1.20', 2, '2022-02-01', 'true', 3, 4);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Bug 399', 'Corrigir bug 399 na versão 1.20', 6, '2022-01-28', 'true', 3, 5);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Reunião B', 'Reunião com cliente B para apresentação do produto', 5, '2022-01-31', 'true', 2, 2);
INSERT INTO TB_TAREFA (titulo, descricao, duracao, prazo, finalizado, id_departamento, id_pessoa) VALUES ('Validar NF Fevereiro', 'Validar notas recebidas no mês de Fevereiro', 14, '2022-03-15', 'false', 1, 6);
