package org.example.Assignment;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FilterInvoiceStubbedTest {
    /* creates a mock called dao for QueryInvoicesDao.class and stubs daoMock.all() to return
    the list of predefined invoices called mockInvoices. dao then gets passed to FilterInvoice.
    Then, the test asserts that the size of the filtered invoice is 2 and that
    Invoice("Lily", 50) and Invoice("SE333", 75) are the only invoices included
    */
    @Test
    void filterInvoiceStubbedTest() {
        QueryInvoicesDAO dao = mock(QueryInvoicesDAO.class);

        List<Invoice> mockInvoices = Arrays.asList(
                new Invoice("Lily", 50),
                new Invoice("Kerr", 200),
                new Invoice("SE333", 75)
        );
        when(dao.all()).thenReturn(mockInvoices);
        FilterInvoice filter = new FilterInvoice(dao);
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();

        assertEquals(2, lowValuedInvoices.size());
        assertEquals("Lily", lowValuedInvoices.get(0).getCustomer());
        assertEquals("SE333", lowValuedInvoices.get(1).getCustomer());
    }
}
