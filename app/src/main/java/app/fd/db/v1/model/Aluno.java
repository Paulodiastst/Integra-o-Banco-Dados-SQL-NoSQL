package app.fd.db.v1.model;

public class Aluno {

    private int id;
    private String nome;
    private String email;
    private boolean status;

    public Aluno() {}
    public Aluno(int id) {}
    public Aluno(String email){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
