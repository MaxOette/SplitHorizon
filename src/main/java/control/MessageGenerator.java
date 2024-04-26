package control;

import model.Contact;

/**
 * The MessageGenerator Interface defines methods to be implemented by all classes that are responsible for
 * generating a formal letter salutation.
 */
public interface MessageGenerator {

    /**
     * Implementations of this method are generating a letter salutation.
     * @param contact - Contact object containing information about the name parts.
     * @return String - contains the letter salutation.
     */
    String generateMessage (Contact contact);
}
