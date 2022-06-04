package app.fd.db.v1.controller;

import android.content.Context;
import android.util.Log;

import app.fd.db.v1.database.AppDataBase;
import app.fd.db.v1.model.Aluno;

public class AlunoController extends AppDataBase {

    public AlunoController(Context ctx) {
        super(ctx);
    }

    // Métodos básicos e genéricos para um CRUD.

    public void salvar(Aluno obj) {
        Log.i("FD_LOG", "Controller--> Nome: " +obj.getNome() + " - Email: "+obj.getEmail());
    }

    public void deletar(Aluno obj) {}
    public void alterar(Aluno obj) {}
    public void listar(Aluno obj) {}
    public void filtrar(Aluno obj) {}

    public void alterarStatus(Aluno obj) {}

}
