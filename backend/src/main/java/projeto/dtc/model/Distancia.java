package projeto.dtc.model;

import javax.persistence.Entity;

@Entity
public class Distancia extends GeradorId {
    private String origem;
    private String destino;
    private String resultado;

    public Distancia() {
    }

    public Distancia(String origem, String destino, String resultado) {
        this.origem = origem;
        this.resultado = resultado;
        this.destino = destino;
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
}
