package org.example.Assignment;

public class FailToSendSAPInvoiceException extends RuntimeException {
    public FailToSendSAPInvoiceException(String message) {
        super(message);
    }
}
