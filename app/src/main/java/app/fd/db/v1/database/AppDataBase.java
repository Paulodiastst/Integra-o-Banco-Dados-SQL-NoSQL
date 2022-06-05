package app.fd.db.v1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "fd_db.sqlite";
    private static final int DB_VERSION = 1;
    private static final String TABELA_ALUNO = "CREATE TABLE aluno\n" +
            " ( id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " nome TEXT,\n" +
            " email TEXT,\n" +
            " status INTEGER,\n" +
            " datainc TEXT,\n" +
            "  dataalt TEXT )";

    Cursor cursor;

    SQLiteDatabase db;

    public AppDataBase(Context ctx){
        super(ctx,DB_NAME, null,DB_VERSION);

        db = getWritableDatabase();

        Log.i("FD_LOG", "App Conectado ao Data Base "+DB_NAME+" Versão "+DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(TABELA_ALUNO);
            Log.i("FD_LOG", "Tabela Aluno criada com sucesso.");

        }catch (SQLException e) {

            Log.e("FD_LOG", "Erro ao criar tabela ALUNO: "+e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insert(String tabela, ContentValues dados){
        boolean retorno = true;

        try{

            retorno = db.insert(tabela, null, dados)>0;

        }catch (SQLException e){

            retorno = false;
            Log.e("FD_LOG", "Erro ao inserir dados na tabela ALUNO: "+e.getMessage());
        }


        return retorno;
    }
}
