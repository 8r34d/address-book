package spencer.dean.addressbook;

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
    

    public String getOldestPerson() {
        return "";
    }
    
    public int getDaysOldForPerson() {
        return 0;
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
