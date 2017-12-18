package compromissoapp.util.error;

public class UtilException extends Throwable {

	private static final long serialVersionUID = 1L;

	public UtilException(String message) {
		super(message);
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}
}
