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
        StringBuilder sb = new StringBuilder();

        if (contact.getGender() == Gender.M) {
            sb.append("Sehr geehrter Herr ");
        } else if (contact.getGender() == Gender.F) {
            sb.append("Sehr geehrte Frau ");
        } else {
            sb.append("Hallo ");
        }

        appendIfPresent(sb, contact.getTitle1());
        appendIfPresent(sb, contact.getTitle2());
        appendIfPresent(sb, contact.getFirstName());
        appendIfPresent(sb, contact.getSecondName());
        appendIfPresent(sb, contact.getNobleTitle());
        appendIfPresent(sb, contact.getLastName());

        return sb.toString().trim().replaceAll("\\s+", " ");
    }

    /**
     * Appends the provided value to the StringBuilder if it is not null or empty.
     * Adds a space after the value for proper spacing in the final string.
     */
    private void appendIfPresent(StringBuilder sb, String value) {
        if (value != null && !value.isEmpty()) {
            sb.append(value).append(" ");
        }
    }
}
