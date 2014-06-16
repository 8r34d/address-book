package spencer.dean.addressbook;

public enum Gender {

    MALE("Male"),
    FEMALE("Female");
    
    private String value;
    
    Gender (String value) {
        this.value = value;
    }
    
    public String value() { return value; }

}
