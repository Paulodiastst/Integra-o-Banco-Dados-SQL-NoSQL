package app.fd.db.v1.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import app.fd.db.v1.database.AppDataBase;
import app.fd.db.v1.model.Aluno;

public class AlunoController extends AppDataBase {

    ContentValues dados;

    public AlunoController(Context ctx) {
        super(ctx);
    }

    // Métodos básicos e genéricos para um CRUD.

    public boolean  salvar(Aluno obj) {

        dados = new ContentValues();

        dados.put("nome", obj.getNome());
        dados.put("email", obj.getEmail());
        dados.put("status", obj.isStatus());


        Log.i("FD_LOG", "Controller--> Nome: "+
                obj.getNome() + " - Email: "+
                obj.getEmail()+
                "- Status: "+obj.isStatus());

        return insert("aluno", dados);
    }

    public boolean alterar(Aluno obj){

        dados = new ContentValues();

        dados.put("id",obj.getId());
        dados.put("nome",obj.getNome());
        dados.put("email",obj.getEmail());
        dados.put("status",obj.isStatus());

        Log.i("FD_LOG","Controller--> Nome: "+
                obj.getNome()+" - Email: "+
                obj.getEmail()+
                " - Status: "+obj.isStatus());

        return update("aluno",dados);

    }



    public void deletar(Aluno obj) {}
    public void mudar(Aluno obj) {}
    public void listar(Aluno obj) {}
    public void filtrar(Aluno obj) {}

    public void alterarStatus(Aluno obj) {}

}


