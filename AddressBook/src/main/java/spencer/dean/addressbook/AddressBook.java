package spencer.dean.addressbook;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddressBook {
    
    private List<String[]> data;
    private static final int nameIndex = 0;
    private static final int genderIndex = 1;
    private static final int dobIndex = 2;

    public AddressBook(List<String[]> addressBookData) {
        data = addressBookData;
    }

    public int getFemalesCount() {
        return getGenderCount(Gender.FEMALE);
    }

    public int getMalesCount() {
        return getGenderCount(Gender.MALE);
    }

    public String getOldestPerson() throws ParseException {
        String name = "";
        long age = 0;
        for (String[] item : data) {
            long ageInSeconds = getAgeInSeconds(item[dobIndex].trim());
            if (ageInSeconds > age) {
                age = ageInSeconds;
                name = item[nameIndex].trim();
            }
        }
        return name;
    }

    public long getTimeToDateInSeconds(Date date) {
        return date.getTime();
    }

    public long getTimeToTodayInSeconds() {
        return new Date().getTime();
    }

    public long getAgeInSeconds(String dateAsString) throws ParseException {
        Date date = getDateFromString(dateAsString);
        return ( getTimeToTodayInSeconds() - getTimeToDateInSeconds(date) );
    }

    public Date getDateFromString(String dateAsString) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date date = df.parse(String.valueOf(dateAsString));
        return date;
    }

    private int getGenderCount(Gender gender) {
        int count = 0;
        for (String[] item : data) {
            if ( item[genderIndex].trim().equals(gender.value()) ) {
                count++;
            }
        }
        return count;
    }

}
