package br.com.codigolivre.projetoapi.domain.services.exception;

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	// repassa a mensagem para a super classe RunTimeException.
	public DataIntegrityException(String msg) {
		super(msg);
	}

	// recebe a messagem e uma execessão,e uma outra excessao
	// que seria a causa de alguma coisa que aconteceu antes.
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
