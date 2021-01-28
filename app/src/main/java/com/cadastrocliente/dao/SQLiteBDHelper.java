package com.cadastrocliente.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cadastrocliente.dao.cliente.SQLiteClienteMetaDados;

public class SQLiteBDHelper extends SQLiteOpenHelper implements SQLiteDadosBanco, SQLiteClienteMetaDados {

    /**
     * Construtor do banco de dados.
     *
     * @param context Contexto do aplicativo
     */
    public SQLiteBDHelper(Context context) {
        super(context, SQLiteDadosBanco.DATABASE, null, SQLiteDadosBanco.VERSAO);
    }

    /**
     * Cria o banco de dados.
     *
     * @param db Banco de dados a ser utilizado.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cria a tabela cliente senão existir
        db.execSQL(SQLiteClienteMetaDados.METADADOSCREATE);

        //Criação de outras tabelas vão aqui
    }

    /**
     * Atualiza o banco de dados.
     *
     * @param db         Banco de dados a ser utilizado.
     * @param oldVersion Versão antiga.
     * @param newVersion Nova versão.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String updateVersao2 = "ALTER TABLE " + SQLiteClienteMetaDados.TABLE + " ADD COLUMN EMAIL TEXT";
        String updateVersao3 = "ALTER TABLE " + SQLiteClienteMetaDados.TABLE + " ADD COLUMN FONE TEXT";
        if (oldVersion == 1) {
            //lógica de atualização da v1 para v2
            //db.execSQL(updateVersao2);
            //db.execSQL(updateVersao3);
        } else {
            if (oldVersion == 2) {
                //lógica de atualização da v2 para v3
                //db.execSQL(updateVersao3);
            }
        }
    }
}
