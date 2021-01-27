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
     * string com os campos para serem utilizados com insert
     */
    public static String METADADOSINSERT = "CLIENTEID, NOME, CPF ";

    /**
     * Retorna uma string com os campos para serem utilizados com select
     */
    public static String METADADOSSELECT
            = TABLE + ".CLIENTEID, "
            + TABLE + ".NOME, "
            + TABLE + ".CPF";
}
