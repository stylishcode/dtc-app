package projeto.dtc.model;

import javax.persistence.Entity;

@Entity
public class Resultado extends GeradorId {
    String descricao_ponto_origem;
    String ponto_origem_x, ponto_origem_y;
    String descricao_p_destino;
    String ponto_destino_x, ponto_destino_y;

    public Resultado(String descricao_ponto_origem, String ponto_origem_x, String ponto_origem_y,
                     String descricao_p_destino, String ponto_destino_x, String ponto_destino_y) {
        this.descricao_ponto_origem = descricao_ponto_origem;
        this.ponto_origem_x = ponto_origem_x;
        this.ponto_origem_y = ponto_origem_y;
        this.descricao_p_destino = descricao_p_destino;
        this.ponto_destino_x = ponto_destino_x;
        this.ponto_destino_y = ponto_destino_y;
    }

    public String getDescricao_ponto_origem() {
        return descricao_ponto_origem;
    }

    public void setDescricao_ponto_origem(String descricao_ponto_origem) {
        this.descricao_ponto_origem = descricao_ponto_origem;
    }

    public String getPonto_origem_x() {
        return ponto_origem_x;
    }

    public void setPonto_origem_x(String ponto_origem_x) {
        this.ponto_origem_x = ponto_origem_x;
    }

    public String getPonto_origem_y() {
        return ponto_origem_y;
    }

    public void setPonto_origem_y(String ponto_origem_y) {
        this.ponto_origem_y = ponto_origem_y;
    }

    public String getDescricao_p_destino() {
        return descricao_p_destino;
    }

    public void setDescricao_p_destino(String descricao_p_destino) {
        this.descricao_p_destino = descricao_p_destino;
    }

    public String getPonto_destino_x() {
        return ponto_destino_x;
    }

    public void setPonto_destino_x(String ponto_destino_x) {
        this.ponto_destino_x = ponto_destino_x;
    }

    public String getPonto_destino_y() {
        return ponto_destino_y;
    }

    public void setPonto_destino_y(String ponto_destino_y) {
        this.ponto_destino_y = ponto_destino_y;
    }
}
