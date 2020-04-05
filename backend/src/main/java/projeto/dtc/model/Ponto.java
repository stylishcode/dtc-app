package projeto.dtc.model;

public class Ponto {
	public Integer index;
	public String descricao;
	public String latitude;
	public String longitude;
	public String utmX;
	public String utmY;
	public String detalhe;
	
	public Ponto(int index, String descricao, String latitude, String longitude, String utmX, String utmY, String detalhe) {
		this.index = index;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;		
		this.utmX = utmX;
		this.utmY = utmY;
		this.detalhe = detalhe;
	}

	@Override
	public String toString() {
		return "Ponto [index=" + index + ", descricao=" + descricao + ", latitude=" + latitude + ", longitude="
				+ longitude + ", utmX=" + utmX + ", utmY=" + utmY + ", detalhe=" + detalhe + "]";
	}

}
