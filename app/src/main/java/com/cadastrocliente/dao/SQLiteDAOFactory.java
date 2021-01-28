package com.cadastrocliente.dao;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cadastrocliente.dao.cliente.ClienteDAO;
import com.cadastrocliente.dao.cliente.SQLiteClienteDAO;

/**
 * Implementa a fonte de dado para persistência em arquivo utilizando SGBD
 * SQLite.
 *
 * @author osmarbraz
 * @version 1.0
 * @updated 19-abr-2020 22:00:00
 */
public class SQLiteDAOFactory extends DAOFactory implements SQLiteDadosBanco {

     /**
     * Retorna uma Cliente DAO.
     *
     * @return ClienteDAO Um DAO para cliente.
     */
    public ClienteDAO getClienteDAO() {
        return new SQLiteClienteDAO();
    }

    /**
     * Operação para prepara a string que será enviada ao banco de dados Se ela.
     * possui uma ' será duplicada para anular o efeito de sql injetado.
     *
     * @return String texto com ' duplicado.
     * @param valor string a ser preparada para envio ao banco de dados.
     */
    protected String preparaSQL(String valor) {
        if (valor != null) {
            return valor.replaceAll("\'", "''");
        } else {
            return "";
        }
    }

    /**
     * Concatena String baseado nos valores Strings de uma Collection
     *
     * @return String Com os literais conctatenados.
     *
     * @param separator Separador dos campos.
     * @param collection Coleção com os campos.
     */
    public String implode(String separator, Collection collection) {
        StringBuffer textBufferReturn = new StringBuffer();
        @SuppressWarnings("rawtypes")
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String text = (String) it.next();
            textBufferReturn.append(text);
            if (it.hasNext()) {
                textBufferReturn.append(separator);
            }
        }
        return textBufferReturn.toString();
    }

    /**
     * Monta a string do filtro.
     * @param lista Lista dos campos do filtro.
     * @param separador Separador os dados do filtro.
     * @return Retorna uma string com os dados do filtro.
     */
    public String montaFiltro(List<String> lista, String separador) {
        StringBuffer filtro = new StringBuffer();
        Iterator<String> filtroIt = lista.iterator();
        while(filtroIt.hasNext()) {
            String texto = filtroIt.next();
            filtro.append(texto);
            if(filtroIt.hasNext()) {
                filtro.append(" " + separador + " ");
            }
        }
        return filtro.toString();
    }
}
