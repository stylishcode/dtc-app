package projeto.dtc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleError {
	
	@ExceptionHandler(BusinessValidation.class)
	public ResponseEntity<?> handleBusinessException(BusinessValidation bv){
		return new ResponseEntity(bv.getErros(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
