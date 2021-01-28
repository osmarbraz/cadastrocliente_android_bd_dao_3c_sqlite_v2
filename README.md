# Sistema de Cadastro de Clientes(CRUD) para Android em Banco de Dados SQLite em 3 camadas utilizando o padrão Abstract Factory.
- Todas as funcionalidades estão em uma tela;
	- Inclusão sem gerar o Id do cliente;  
	- Exclusão;
	- Alteração;
	- Consulta;
	- Listagem dos dados;
	- Quantidade de registros;
	- Zerar banco de dados.
 - O projeto é uma versão para o Android Studio.<br> 
 - O projeto no Android Studio deve ser chamado cadastrocliente.<br>
 - Este programa possui diversas classes organizada nos pacotes visão, controle, modelo e dao.<br>
 - Utiliza o padrão abstract factory para abstrair o armazenamento em SQLite.
 - Toda iteração com banco de dados é tratada diretamente pelo DAO(Data Access Object) utilizando SQLiteOpenHelper.<br>
 - Os dados de configuração ( Database) da integração do java com o banco de dados estão no arquivo src/dao/SQLiteDadosBanco.java.<br>
 


