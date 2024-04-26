package model;

/**
 * The Contact class represents a contact which contains information about all the individual parts of a name
 * as well as the gender.
 */
public class Contact {

    /**
     * The salutation of the contact.
     */
    private String salutation = "";

    /**
     * The gender of the contact.
     * Possible genders are defined in {@link Gender}
     */
    private Gender gender = Gender.X;

    /**
     * The first scientific title of the contact.
     */
    private String title1 = "";

    /**
     * The second scientific title of the contact.
     */
    private String title2 = "";

    /**
     * The first name of the contact.
     */
    private String firstName = "";

    /**
     * The second name of the contact.
     */
    private String secondName = "";

    /**
     * The title of nobility of the contact.
     */
    private String nobleTitle ="";

    /**
     * The last name of the contact.
     */
    private String lastName = "";

    /**
     * empty Constructor - responsible for instantiation of a Contact object.
     */
    public Contact() {

    }

    /**
     * Returns the salutation.
     * @return the salutation
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * Sets the salutation.
     * @param salutation - the new salutation
     */
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    /**
     * Returns the gender.
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     * @param gender - the new gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns the first title.
     * @return the first title
     */
    public String getTitle1() {
        return title1;
    }

    /**
     * Sets the first title.
     * @param title1 - the new first title
     */
    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    /**
     * Returns the second title.
     * @return the second title
     */
    public String getTitle2() {
        return title2;
    }

    /**
     * Sets the second title.
     * @param title2 - the new second title
     */
    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    /**
     * Returns the first name.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     * @param firstName - the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the second name.
     * @return the second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Sets the second name.
     * @param secondName - the new second name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Returns the last name.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     * @param lastName - the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the noble title.
     * @return the noble title
     */
    public String getNobleTitle() {
        return nobleTitle;
    }

    /**
     * Sets the noble title.
     * @param nobleTitle - the new noble title
     */
    public void setNobleTitle(String nobleTitle) {
        this.nobleTitle = nobleTitle;
    }
}
