package app.fd.db.v1.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.List;

import app.fd.db.v1.database.AppDataBase;
import app.fd.db.v1.model.Aluno;

public class AlunoController extends AppDataBase {

    ContentValues dados;

    List<Aluno> alunos;

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

    public boolean deletar(Aluno obj) {

        dados = new ContentValues();

        dados.put("id",obj.getId());

        return delete("aluno",dados);
    }

    public List<Aluno> listar() {

        // Implementação
        alunos = getAllAlunos();

        return alunos;
    }


    public void filtrar(Aluno obj) {}

    public void alterarStatus(Aluno obj) {}

}