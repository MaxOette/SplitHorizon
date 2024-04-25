package model;

public class Contact {
    private String salutation = "";
    private Gender gender = Gender.X;
    private String title1 = "";
    private String title2 = "";
    private String firstName = "";
    private String secondName = "";
    private String nobleTitle ="";
    private String lastName = "";

    public Contact() {

    }

    public Contact(String salutation, Gender gender, String title1, String title2, String firstName, String secondName, String nobleTitle, String lastName) {
        this.salutation = salutation;
        this.gender = gender;
        this.title1 = title1;
        this.title2 = title2;
        this.firstName = firstName;
        this.secondName = secondName;
        this.nobleTitle = nobleTitle;
        this.lastName = lastName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNobleTitle() {
        return nobleTitle;
    }

    public void setNobleTitle(String nobleTitle) {
        this.nobleTitle = nobleTitle;
    }
}
