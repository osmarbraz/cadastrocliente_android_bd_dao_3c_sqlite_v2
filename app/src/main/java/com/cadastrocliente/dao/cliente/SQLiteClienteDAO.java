package com.cadastrocliente.dao.cliente;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.cadastrocliente.dao.SQLiteBDHelper;
import com.cadastrocliente.dao.SQLiteDAOFactory;
import com.cadastrocliente.entidade.Cliente;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementa a persistência de cliente utilizando SQLite.
 *
 * @author osmarbraz
 * @version 1.0
 * @updated 19-abr-2020 22:00:00
 */
public class SQLiteClienteDAO extends SQLiteDAOFactory implements ClienteDAO, SQLiteClienteMetaDados {

    private SQLiteBDHelper dbHelper;

    /**
     * Connstrutor da classe
     */
    public SQLiteClienteDAO() {
        dbHelper = new SQLiteBDHelper(getContext());
    }

    /**
     * Retorna uma lista com os objetos segundo os critérios do filtro e ordem.
     *
     * @param filtros Lista dos campos a serem utilizados no filtro.
     * @param ordem   Ordem dos dados na consulta.
     * @return Lista com os objetos.
     */
    private List select(List<String> filtros, String ordem) {
        List lista = new LinkedList();
        String[] colunas = METADADOSSELECT.split(",");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(TABLE, colunas, montaFiltro(filtros, " and "), null, null, null, ordem, null);
            while (cursor.moveToNext()) {
                Cliente cliente = new Cliente();
                //Recupera o valor do campo pelo índice do nome da coluna
                cliente.setClienteId(cursor.getString(cursor.getColumnIndex(PK[0])));
                cliente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                cliente.setCpf(cursor.getString(cursor.getColumnIndex("CPF")));
                lista.add(cliente);
            }
            cursor.close();
            cursor = null;
            db.close();
            db = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    ;
                }
                cursor = null;
            }
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    ;
                }
                db = null;
            }
        }
        return lista;
    }

    public boolean inserir(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            SQLiteDatabase db = null;
            boolean res = false;
            try {
                db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(METADADOSUPDATE[0], preparaSQL(cliente.getClienteId()));
                valores.put(METADADOSUPDATE[1], preparaSQL(cliente.getNome()));
                valores.put(METADADOSUPDATE[2], preparaSQL(cliente.getCpf()));
                db.insert(TABLE, null, valores);
                db.close();
                db = null;
                res = true;
            } catch (Exception e) {
                System.out.println(e);
                res = false;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return false;
    }

    public int alterar(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            SQLiteDatabase db = null;
            int res = 0;
            try {
                db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(METADADOSUPDATE[0], preparaSQL(cliente.getClienteId()));
                valores.put(METADADOSUPDATE[1], preparaSQL(cliente.getNome()));
                valores.put(METADADOSUPDATE[2], preparaSQL(cliente.getCpf()));
                String selecao = PK[0] + " = ? ";
                String[] selecaoArgumentos = {preparaSQL(cliente.getClienteId())};
                db.update(TABLE, valores, selecao, selecaoArgumentos);
                db.close();
                db = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return 0;
    }

    public int excluir(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;
            SQLiteDatabase db = null;
            StringBuilder sql = new StringBuilder();
            int res = 0;
            try {
                db = dbHelper.getWritableDatabase();
                String selecao = PK[0] + " = ? ";
                String[] selecaoArgumentos = {preparaSQL(cliente.getClienteId())};
                db.delete(TABLE, selecao, selecaoArgumentos);
                db.close();
                db = null;
                res = 1;
            } catch (Exception e) {
                System.out.println(e);
                res = 0;
            } finally {
                if (db != null) {
                    try {
                        db.close();
                    } catch (Exception e) {
                        ;
                    }
                    db = null;
                }
            }
            return res;
        }
        return 0;
    }

    /**
     * Monta uma lista com os filtros para consulta de acordo como preenchimento dos atributos do objeto
     *
     * @param obj Objeto que possui os dados do filtro.
     * @return Uma lista com os dados do filtro.
     */
    public List aplicarFiltro(Object obj) {
        if (obj != null) {
            Cliente cliente = (Cliente) obj;

            List<String> filtros = new ArrayList<String>();

            if (cliente.getClienteId() != "") {
                filtros.add(TABLE + "." + PK[0] + "= '" + cliente.getClienteId() + "'");
            }

            if (!cliente.getNome().equals("")) {
                filtros.add(TABLE + ".NOME like '" + cliente.getNome() + "'");
            }

            if (!cliente.getCpf().equals("")) {
                filtros.add(TABLE + ".NOME like '" + cliente.getCpf() + "'");
            }

            return select(filtros, PK[0]);
        } else {
            return null;
        }
    }

    public void apagarTabela() {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.delete(TABLE, null, null);
            db.close();
            db = null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (Exception e) {
                    ;
                }
                db = null;
            }
        }
    }

    public long getNumeroRegistros() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE);
    }
}
