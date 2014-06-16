package spencer.dean.addressbook;

import java.text.ParseException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddressBookTest {

    private static String addressBookData;
    private static List<String[]> data;
    private AddressBook addressBook;

    @BeforeTest
    public void setup() {
        addressBookData = "src/test/resources/address_book.csv";
        data = Data.getDataAsList(addressBookData);
        addressBook = new AddressBook(data);
    }

    @AfterTest
    public void teardown() {
        //
    }

    @Test()
    public void getMalesCount() {
        Assert.assertEquals( addressBook.getMalesCount(), 3 );
    }

    @Test() 
    public void getOldestPerson() throws ParseException {
        Assert.assertEquals(addressBook.getOldestPerson(), "Wes Jackson");
    }

    @Test 
    public void getNumberOfDaysBillIsOlderThanPaul() throws Exception {
        Assert.assertEquals(addressBook.getDaysOlder("Bill McKnight", "Paul Robinson"), 2862);
    }
}
