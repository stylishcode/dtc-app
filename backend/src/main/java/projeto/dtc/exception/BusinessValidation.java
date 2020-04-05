package projeto.dtc.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessValidation extends RuntimeException{
	
	List<String> erros = new ArrayList<String>();

	private static final long serialVersionUID = 8077237646955767947L;
	
	public void addErro(String error) {
		erros.add(error);
	}
	
	public boolean hasError() {
		return erros.size() > 0 ? true : false;
	}
	
	public List<String> getErros(){
		return this.erros;
	}

}
