insert into tb_pedido(nome, cpf, data, status, valor_total) values ('Jon Snow', '12345678963', '2025-11-25', 'CRIADO', 790.0);
insert into tb_pedido(nome, cpf, data, status, valor_total) values ('JAyra Stark', '789583963', '2026-10-25', 'CRIADO', 3599.0);

insert into tb_item_do_pedido(quantidade, descricao, preco_unitario, pedido_id) values (2, 'Mouse sem fio Microsoft', 250.0, 1);
insert into tb_item_do_pedido(quantidade, descricao, preco_unitario, pedido_id) values (1, 'Teclado sem fio Microsoft', 290.0, 1); --continua com o mesmo id pq é a msm compra
insert into tb_item_do_pedido(quantidade, descricao, preco_unitario, pedido_id) values (1, 'Smart TV LED', 3599.0, 2); --pedido 2
