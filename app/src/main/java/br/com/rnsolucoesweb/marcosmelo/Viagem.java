package br.com.rnsolucoesweb.marcosmelo;

public class Viagem {

    public String id;
    public String data;
    public String origem;
    public String destino;


    public Viagem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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





//    public Viagem(String uID, String data, String nomeUsuario, String origem, String destino) {
//        this.uID = uID;
//        this.data = data;
//        this.nomeUsuario = nomeUsuario;
//        this.origem = origem;
//        this.destino = destino;
//    }
}