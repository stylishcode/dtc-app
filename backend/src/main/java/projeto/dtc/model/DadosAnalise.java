package projeto.dtc.model;

import javax.persistence.Entity;

@Entity
public class DadosAnalise extends GeradorId {
    private String chaveGoogle;
    private String modal;
    private String pontoOrigem, pontoDestino;
    private String resultado;

    public DadosAnalise(String chaveGoogle, String modal, String pontoOrigem,
                        String pontoDestino, String resultado) {
        this.chaveGoogle = chaveGoogle;
        this.modal = modal;
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.resultado = resultado;
    }

    public DadosAnalise() {
    }

    public String getChaveGoogle() {
        return chaveGoogle;
    }
    public void setChaveGoogle(String chaveGoogle) {
        this.chaveGoogle =  chaveGoogle; 
    }

    public String getModal() {
        return modal;
    }
    public void setModal(String modal) {
        this.modal =  modal; 
    }

    public String getPontoOrigem() {
        return pontoOrigem;
    }
    public void setPontoOrigem(String pontoOrigem) {
        this.pontoOrigem =  pontoOrigem; 
    }

    public String getPontoDestino() {
        return pontoDestino;
    }
    public void setPontoDestino(String pontoDestino) {
        this.pontoDestino =  pontoDestino; 
    }

    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado =  resultado; 
    }
}