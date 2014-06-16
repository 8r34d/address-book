package spencer.dean.addressbook;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddressBook {

    private List<String[]> data;
    private static final int NAME_INDEX = 0;
    private static final int GENDER_INDEX = 1;
    private static final int DOB_INDEX = 2;
    private static final int SECONDS_IN_A_DAY = 86400;
    private static final String DATE_FORMAT = "dd/MM/yy";

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
            long ageInSeconds = getAgeInSeconds(item[DOB_INDEX].trim());
            if (ageInSeconds > age) {
                age = ageInSeconds;
                name = item[NAME_INDEX].trim();
            }
        }
        return name;
    }

    public long getDaysOlder(String personA, String personB) throws Exception {
        String[] personDataA = getPersonData(personA);
        String[] personDataB = getPersonData(personB);
        long agePersonA = getDaysFromSeconds(getAgeInSeconds(personDataA[DOB_INDEX]));
        long agePersonB = getDaysFromSeconds(getAgeInSeconds(personDataB[DOB_INDEX]));
        return agePersonA - agePersonB;
    }

    private String[] getPersonData(String name) throws Exception {
        for (String[] item : data) {
            if (item[NAME_INDEX].trim().equals(name)) {
                return item;
            }
        }
        throw new Exception("Data for " + name + " not found!");
    }

    private long getDaysFromSeconds(long seconds) {
        return seconds / SECONDS_IN_A_DAY;
    }

    private long getTimeToDateInSeconds(Date date) {
        return date.getTime() / 1000;
    }

    private long getTimeToTodayInSeconds() {
        return new Date().getTime() / 1000;
    }

    private long getAgeInSeconds(String dateAsString) throws ParseException {
        Date date = getDateFromString(dateAsString);
        return getTimeToTodayInSeconds() - getTimeToDateInSeconds(date);
    }

    private Date getDateFromString(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.parse(String.valueOf(date));
    }

    private int getGenderCount(Gender gender) {
        int count = 0;
        for (String[] item : data) {
            if (item[GENDER_INDEX].trim().equals(gender.value())) {
                count++;
            }
        }
        return count;
    }
}
