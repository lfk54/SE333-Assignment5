package org.example.Assignment;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class TestInvoicesSent {

    /* Tests that sending low invoices to SAP_BasedInvoiceSender by creating a mock FilterInvoice
     and a mock instance of SAP. Then the mocked FilterInvoice.lowValueInvoices() is stubbed
     to return a list of predefined low invoices. Then a SAP_BasedInvoiceSender is created
     with the mocked FilterInvoice and SAP, and sendLowValuedInvoices() is called. Then verify
     is used to check that send() was called for both invoices and exactly twice
     */
    @Test
    void testWhenLowInvoicesSent() {
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);
        List<Invoice> lowInvoices = Arrays.asList(
                new Invoice("Lily", 50),
                new Invoice("Kerr", 75)
        );

        when(filter.lowValueInvoices()).thenReturn(lowInvoices);

        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);
        sender.sendLowValuedInvoices();

        verify(sap, times(1)).send(new Invoice("Lily", 50));
        verify(sap, times(1)).send(new Invoice("Kerr", 75));
        verify(sap, times(2)).send(any(Invoice.class));
    }

   /* Tests that sap.send() is not called when there are no invoices
   by creating mocks for FilterInvoice and SAP, then stubbing lowValueInvoices() to
   return an empty array. Then a SAP_BasedInvoiceSender is created with the mocked
   filter and sap, and sendLowValuesInvoices() is called. Then verify is used to
   confirm that sap.send() wasn't called*/
    @Test
    void testWhenNoInvoices() {
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);

        when(filter.lowValueInvoices()).thenReturn(Arrays.asList());

        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);
        sender.sendLowValuedInvoices();

        verify(sap, never()).send(any(Invoice.class));
    }
}
