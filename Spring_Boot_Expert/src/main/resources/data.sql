Create table Cliente (
	id integer primary key auto_increment,
    nome varchar(100)
);

Create table Produto (
	id integer primary key auto_increment,
    Descricao varchar(100),
	preco_unitario numeric (20, 2)
);

Create table Pedido (
	id integer primary key auto_increment,
    Cliente_id integer references cliente(id),
    Data_Pedido timestamp,
    total numeric (20, 2)
);

Create table Item_Pedido (
	id integer primary key auto_increment,
    Pedido_id integer references pedido(id),
    Produto_id integer references produto(id),
    quantidade integer
);