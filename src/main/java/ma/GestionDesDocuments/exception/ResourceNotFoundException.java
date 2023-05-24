package ma.GestionDesDocuments.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg + "not found exception");
    }
}
