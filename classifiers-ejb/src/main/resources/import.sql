-- You can use this file to load seed data into the database using SQL statements

-- scope
insert into scope (id, code, description, remarks) values (1001, '0001', 'Ambito 0001', null);
insert into scope (id, code, description, remarks) values (1002, '0002', 'Ambito 0002', null);

-- territory-classifier
insert into territory (id, code, description, remarks) values (2001, '0000000001', 'Territorio 0000000001', null);
insert into territory (id, code, description, remarks) values (2002, '0000000002', 'Territorio 0000000002', null);

--resource-source
insert into resource_source (id, code, description, remarks) values (3001, '101000000000', 'Fonte de Recurso 101000000000', 'FR 101000000000');
insert into resource_source (id, code, description, remarks) values (3002, '102000000000', 'Fonte de Recurso 102000000000', null);

--financial-fashion
--insert into classifier(id, type, code, description, remarks) values (4001, 'financial-fashion', 'G', 'Fonte de Financiamento G', null);

-- organic-classifier
--insert into classifier(id, type, code, description, remarks) values (5001, 'organic-classifier', '000000001', 'Organico 000000001', null);
--insert into classifier(id, type, code, description, remarks) values (5002, 'organic-classifier', '000000002', 'Organico 000000002', null);

-- management
insert into management (id, code, description, remarks) values (80012, '1000110000G000000000', 'Gestao Maputo 1000110000G000000000', 'Gestao');

