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
    private static final int secondsInDay = 86400;

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

    public long getDaysOlder(String personA, String personB) throws Exception {
        String[] a = getPersonData(personA);
        String[] b = getPersonData(personB);
        long agePersonA = getDaysFromSeconds(getAgeInSeconds(a[dobIndex]));
        long agePersonB = getDaysFromSeconds(getAgeInSeconds(b[dobIndex]));
        return agePersonA - agePersonB;
    }

    private String[] getPersonData(String name) throws Exception {
        String[] result = null;
        for (String[] item : data) {
            if (item[nameIndex].trim().equals(name)) {
                result = item;
                return result;
            }
        }
        throw new Exception("Data for " + name + " not found!");
    }

    private long getDaysFromSeconds(long seconds) {
        return seconds / secondsInDay;
    }

    private long getTimeToDateInSeconds(Date date) {
        return date.getTime() / 1000;
    }

    private long getTimeToTodayInSeconds() {
        return new Date().getTime() / 1000;
    }

    private long getAgeInSeconds(String dateAsString) throws ParseException {
        Date date = getDateFromString(dateAsString);
        return ( getTimeToTodayInSeconds() - getTimeToDateInSeconds(date) );
    }

    private Date getDateFromString(String dateAsString) throws ParseException {
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
