package mjv.sistemabiblioteca.exception;

public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1097708395652202154L;

	public ValidationException(String message) {
		super(message);
	}

}
