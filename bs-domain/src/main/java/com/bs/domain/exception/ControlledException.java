package com.bs.domain.exception;

/**
 *
 * @author J. Milton Chambi M.
 */
public class ControlledException extends Exception {
    
    public ControlledException(String message) {
        super(message);
    }
    
    public ControlledException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
