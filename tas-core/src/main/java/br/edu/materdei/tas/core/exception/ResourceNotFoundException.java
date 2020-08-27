package br.edu.materdei.tas.core.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Integer resourceId) {
        super(resourceId != null ? resourceId.toString() : null);
    }
    
}
