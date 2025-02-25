package org.example.Assignment;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterInvoiceTest {
    @Test
    /*creates a database instance and adds testing data, then calls
    FilterInvoice.lowValueInvoices() to test that
    invoice values > 100 are filtered out.
    Asserts that the size of the filtered invoice is 2 and that
    Invoice("Lily", 50) and Invoice("SE333", 75) are the only invoices included
    */
    void filterInvoiceTest() {
        Database database = new Database();
        QueryInvoicesDAO dao = new QueryInvoicesDAO(database);

        dao.save(new Invoice("Lily", 50));
        dao.save(new Invoice("Kerr", 200));
        dao.save(new Invoice("SE333", 75));

        FilterInvoice filter = new FilterInvoice();
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();

        assertEquals(2, lowValuedInvoices.size());
        assertEquals("Lily", lowValuedInvoices.get(0).getCustomer());
        assertEquals("SE333", lowValuedInvoices.get(1).getCustomer());

        database.resetDatabase();
    }
}
