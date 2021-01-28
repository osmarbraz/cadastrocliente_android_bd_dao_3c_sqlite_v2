package com.cadastrocliente.dao.cliente;

/**
 * Armazena os metadados para a implementação em SQLite.
 *
 * @author osmarbraz
 * @version 1.0
 * @updated 19-abr-2020 22:00:00
 */
public interface SQLiteClienteMetaDados {

    /**
     * string com o nome da tabela usada no banco
     */
    public static final String TABLE = "CLIENTE";

    /**
     * vetor de string com as chaves da tabela
     */
    public static final String[] PK = {"CLIENTEID"};

    /**
     * string com os campos para serem utilizados com insert ou update
     */
    public static String[] METADADOSUPDATE = {"CLIENTEID", "NOME", "CPF"};

    /**
     * Retorna uma string com os campos para serem utilizados com select
     */
    public static String METADADOSSELECT
            = TABLE + ".CLIENTEID, "
            + TABLE + ".NOME, "
            + TABLE + ".CPF";
    /**
     * Retorna uma string com O SQL para criar a tabela
     */
    public static String METADADOSCREATE
            = "create table IF NOT EXISTS " + TABLE + " " +
            "(" + PK[0] + " integer, " +
            "NOME varchar(100), " +
            "CPF varchar(11), " +
            "CONSTRAINT PK_CLIENTE PRIMARY KEY ("+ PK[0] + "))";
}
