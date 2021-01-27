package com.cadastrocliente.dao.cliente;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.cadastrocliente.dao.SQLiteDAOFactory;
import com.cadastrocliente.entidade.Cliente;

/**
 * Implementa a persistência de cliente utilizando SQLite.
 *
 * @author osmarbraz
 * @version 1.0
 * @updated 19-abr-2020 22:00:00
 */
public class SQLiteClienteDAO extends SQLiteDAOFactory implements ClienteDAO, SQLiteClienteMetaDados {

    private List select(List<String> filtros, String ordem) {
        List lista = new LinkedList();
        String[] colunas = METADADOSSELECT.split(",");
        SQLiteDatabase con = null;
        Cursor cursor = null;
        try {
            con = getConnection();
            cursor = con.query(TABLE, colunas, montaFiltro(filtros, " and "), null, null, null, ordem , null);
            while (cursor.moveToNext()) {
                Cliente cliente = new Cliente();
                //Recupera o valor do campo pelo indice do nome da coluna
                cliente.setClienteId(cursor.getString(cursor.getColumnIndex(PK[0])));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                cliente.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
                lista.add(cliente);
            }
            cursor.close();
            cursor = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {;
                }
                cursor = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {;
                }
                con = null;
            }
        }
        return lista;
    }

    public boolean inserir(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            SQLiteDatabase con = null;
            boolean res = false;
            StringBuilder sql = new StringBuilder();
            try {
                sql.append("insert into " + TABLE + "(");
                sql.append(METADADOSINSERT + " ) ");

                sql.append("values ('" + preparaSQL(cliente.getClienteId()));
                sql.append("','" + preparaSQL(cliente.getNome()));
                sql.append("','" + preparaSQL(cliente.getCpf()) + "')");

                con = getConnection();
                con.execSQL(sql.toString());

                con.close();
                con = null;
                res = true;
            } catch (Exception e) {
                System.out.println(e);
                res = false;
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {;
                    }
                    con = null;
                }
            }
            return res;
        }
        return false;
    }

    public int alterar(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            SQLiteDatabase con = null;
            int res = 0;
            StringBuilder sql = new StringBuilder();
            try {
                sql.append("update " + TABLE);
                sql.append(" set NOME='" + cliente.getNome() + "',");
                sql.append(" CPF='" + cliente.getCpf() + "'");
                sql.append(" where " + TABLE + "." + PK[0] + "='" + preparaSQL(cliente.getClienteId()) + "'");

                con = getConnection();
                con.execSQL(sql.toString());
                con.close();
                con = null;
                res = 1;

            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {;
                    }
                    con = null;
                }
            }
            return res;
        }
        return 0;
    }

    public int excluir(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            SQLiteDatabase con = null;
            StringBuilder sql = new StringBuilder();
            int res = 0;
            try {
                sql.append("delete from " + TABLE + " where " + TABLE + "." + PK[0] + " = '" + preparaSQL(cliente.getClienteId()) + "'");
                con = getConnection();

                con.execSQL(sql.toString());
                con.close();
                con = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {;
                    }
                    con = null;
                }
            }
            return res;
        }
        return 0;
    }

    public List aplicarFiltro(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;

            List<String> filtros = new ArrayList<String>();

            if(cliente.getClienteId() != ""){
                filtros.add(TABLE+"." + PK[0] + "= '" + cliente.getClienteId() + "'");
            }

            if(!cliente.getNome().equals("")){
                filtros.add(TABLE + ".NOME like '" + cliente.getNome() + "'");
            }

            if(!cliente.getCpf().equals("")){
                filtros.add(TABLE + ".NOME like '" + cliente.getCpf() + "'");
            }

            return select(filtros, PK[0]);
        } else {
            return null;
        }
    }

    public void criar() {
        SQLiteDatabase con = null;
        try {
            con = getConnection();
            //Cria a tabela senão existir
            con.execSQL("create table IF NOT EXISTS CLIENTE (CLIENTEID integer, NOME varchar(100), CPF varchar(11), CONSTRAINT PK_CLIENTE PRIMARY KEY (CLIENTEID));");
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {;
                }
                con = null;
            }
        }
    }

    public void esvaziarTabela() {
        SQLiteDatabase con = null;
        try {
            con = getConnection();
            con.delete(TABLE, null, null);
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {;
                }
                con = null;
            }
        }
    }
    public long getNumeroRegistros() {
        SQLiteDatabase db = getConnection();
        return DatabaseUtils.queryNumEntries(db, TABLE);
    }
}
