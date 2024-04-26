package control;

import model.Contact;
import model.Gender;

/**
 * The class MessageGeneratorImpl implements the MessageGenerator interface. Its provides functionality for
 * generating a letter salutation based on the gender of the contact.
 */
public class MessageGeneratorImpl implements MessageGenerator {

    /**
     * Method for generating the letter salutation based on the passed gender.
     * @param contact - Contact object containing information about the name parts.
     * @return String - message representing the formal letter salutation.
     */
    @Override
    public String generateMessage(Contact contact) {
        String message = "";
        if(contact.getGender() == Gender.M) {
            message = "Sehr geehrter Herr " +
                    contact.getTitle1() + " " +
                    contact.getTitle2() + " " +
                    contact.getFirstName() +  " " +
                    contact.getSecondName() + " " +
                    contact.getNobleTitle() + " " +
                    contact.getLastName();
        } else if (contact.getGender() == Gender.F) {
            message = "Sehr geehrte Frau " +
                    contact.getTitle1() + " " +
                    contact.getTitle2() + " " +
                    contact.getFirstName() +  " " +
                    contact.getSecondName() + " " +
                    contact.getNobleTitle() + " " +
                    contact.getLastName();
        } else {
            message = "Hallo " +
                    contact.getTitle1() + " " +
                    contact.getTitle2() + " " +
                    contact.getFirstName() +  " " +
                    contact.getSecondName() + " " +
                    contact.getNobleTitle() + " " +
                    contact.getLastName();
        }
        return message.trim().replaceAll("\\s+", " ");
    }


}
