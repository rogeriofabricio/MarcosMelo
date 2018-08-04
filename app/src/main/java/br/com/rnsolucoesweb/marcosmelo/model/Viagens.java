package br.com.rnsolucoesweb.marcosmelo.model;

public class Viagens {

    public String nomeUsuario;
    public String uID;
    public String data;
    public String origem;
    public String destino;

    public Viagens() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getUID() {
        return uID;
    }

    public void setUID(String uID) {
        this.uID = uID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Viagens(String uID, String data, String nomeUsuario, String origem, String destino) {
        this.uID = uID;
        this.data = data;
        this.nomeUsuario = nomeUsuario;
        this.origem = origem;
        this.destino = destino;
    }
}