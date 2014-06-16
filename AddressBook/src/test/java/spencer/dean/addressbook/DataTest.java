package spencer.dean.addressbook;

import java.util.Iterator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import spencer.dean.addressbook.Data;

public class DataTest {

    private static String addressBook;

    @BeforeTest
    public void setup() {
        addressBook = "src/test/resources/address_book.csv";
    }

    @AfterTest
    public void teardown() {
        //
    }

    @DataProvider(name = "addressBookDataProviderAsIterator")
    public static Iterator<String[]> addressBookDataProviderAsIterator() {
        return Data.getDataAsIterator(addressBook);
    }

    @Test(dataProvider = "addressBookDataProviderAsIterator")
    public void addressBookDataNotEmpty(String first, String second, String third) {
        Assert.assertFalse(first.isEmpty());
        Assert.assertFalse(second.isEmpty());
        Assert.assertFalse(third.isEmpty());
    }
}