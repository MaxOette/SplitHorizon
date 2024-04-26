package control;

import model.Contact;

/**
 * The ContactSplitter Interface defines methods to be implemented by all classes that are responsible for splitting
 * the input into its individual parts.
 */
public interface ContactSplitter {

    /**
     * Implementations of this method are splitting the input into its individual parts.
     * @param contactString - string containing the full name from the input
     * @return Contact - object which contains information about all individual parts of the name
     */
    Contact parseContactString(String contactString);

}
