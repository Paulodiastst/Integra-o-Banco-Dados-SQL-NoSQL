package app.fd.db.v1.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.fd.db.v1.model.Aluno;

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

    private String dataHora;
    private String SQL;

    Cursor cursor;

    SQLiteDatabase db;

    // CREATE DATABASE
    public AppDataBase(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

        Log.i("FD_LOG", "App Conectado ao Data Base " + DB_NAME + " Versão " + DB_VERSION);
    }

    // CREATE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(TABELA_ALUNO);
            Log.i("FD_LOG", "Tabela Aluno criada com sucesso.");

        } catch (SQLException e) {

            Log.e("FD_LOG", "Erro ao criar tabela ALUNO: " + e.getMessage());
        }

    }

    // ALTER TABLE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // INSERT into TABLE
    public boolean insert(String tabela, ContentValues dados) {

        boolean retorno = true;
        dataHora = getDateTime();

        try {

            dados.put("datainc", dataHora);
            dados.put("dataalt", dataHora);

            retorno = db.insert(tabela, null, dados) > 0;

        } catch (SQLException e) {

            retorno = false;
            Log.e("FD_LOG", "Erro ao inserir dados na tabela ALUNO: " + e.getMessage());
        }


        return retorno;
    }

    // UPDATE TABLE
    public boolean update(String tabela, ContentValues dados) {

        boolean retorno = true;
        dataHora = getDateTime();

        try {

            dados.put("dataalt", dataHora);

            int id = dados.getAsInteger("id");

            retorno = db.update(tabela,
                    dados,
                    "id=?",
                    new String[]{Integer.toString(id)}) > 0;

        } catch (SQLException e) {

            retorno = false;
            Log.e("FD_LOG", "Erro ao alterar dados na tabela ALUNO: " + e.getMessage());

        }

        return retorno;

    }

    // DELETE FROM
    public boolean delete(String tabela, ContentValues dados) {

        boolean retorno = true;

        try {

            int id = dados.getAsInteger("id");

            retorno = db.delete(tabela,
                    "id=?",
                    new String[]{Integer.toString(id)}) > 0;

        } catch (SQLException e) {

            retorno = false;
            Log.e("FD_LOG", "Erro ao deletar dados na tabela ALUNO: " + e.getMessage());

        }

        return retorno;

    }

   // SELECT FROM TABLE
    @SuppressLint("Range")
    public List<Aluno> getAllAlunos(){

        List<Aluno> lista = new ArrayList<>();
        Aluno obj;
        boolean status = false;

        SQL = "SELECT * FROM aluno ORDER by nome";

        try {
            cursor = db.rawQuery(SQL, null);

            if (cursor.moveToFirst()){

                do {
                    obj = new Aluno();

                    obj.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    obj.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                    obj.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                    status = cursor.getInt(cursor.getColumnIndex("status")) !=0;

                    obj.setStatus(status);

                    lista.add(obj);

                }while (cursor.moveToNext());
                // popular a lista
            }

        }catch (SQLException e){
            Log.e("FD_LOG", "Erro ao listar dados na tabela ALUNO: " +e.getMessage());
        }

        return lista;
    }

    private String getDateTime() {

        String dia;
        String mes;
        String ano;

        String hora;
        String minuto;
        String segundo;

        try {

            Calendar calendar = Calendar.getInstance();

            int calDia = calendar.get(Calendar.DAY_OF_MONTH);
            int calMes = calendar.get(Calendar.MONTH) + 1;

            dia = (calDia <= 9) ? "0" + calendar.get(Calendar.DAY_OF_MONTH) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            mes = (calMes <= 9) ? "0" + calMes : Integer.toString(calMes);

            ano = Integer.toString(calendar.get(Calendar.YEAR));

            int iHora = calendar.get(Calendar.HOUR_OF_DAY);
            int iMinuto = calendar.get(Calendar.MINUTE);
            int iSegundo = calendar.get(Calendar.SECOND);

            hora = (iHora <= 9) ? "0" + calendar.get(Calendar.HOUR_OF_DAY) : Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            minuto = (iMinuto <= 9) ? "0" + calendar.get(Calendar.MINUTE) : Integer.toString(calendar.get(Calendar.MINUTE));
            segundo = (iSegundo <= 9) ? "0" + calendar.get(Calendar.SECOND) : Integer.toString(calendar.get(Calendar.SECOND));

            return dia + "/" + mes + "/" + ano + " - " + hora + ":" + minuto + ":" + segundo;

        } catch (Exception e) {

            return "00/00/00 - 00:00:00";
        }

    }
}