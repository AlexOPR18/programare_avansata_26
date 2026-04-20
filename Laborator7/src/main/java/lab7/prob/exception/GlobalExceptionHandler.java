package lab7.prob.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> handleSQLException(SQLException ex) {
		return new ResponseEntity<>("Eroare la baza de date: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("A aparut o eroare neasteptata pe server: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
