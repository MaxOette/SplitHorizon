package control;

import model.Contact;
import model.Gender;

public class MessageGeneratorImpl implements MessageGenerator {

    @Override
    public String generateMessage(Contact contact) {
        if(contact.getGender() == Gender.M) {
            return "Sehr geehrter Herr " +
                    contact.getTitle1() + " " +
                    contact.getTitle2() + " " +
                    contact.getFirstName() +  " " +
                    contact.getSecondName() + " " +
                    contact.getNobleTitle() + " " +
                    contact.getLastName();
        } else if (contact.getGender() == Gender.F) {
            return "Sehr geehrte Frau " +
                    contact.getTitle1() + " " +
                    contact.getTitle2() + " " +
                    contact.getFirstName() +  " " +
                    contact.getSecondName() + " " +
                    contact.getNobleTitle() + " " +
                    contact.getLastName();
        } else {
            return "Hallo " +
                    contact.getTitle1() + " " +
                    contact.getTitle2() + " " +
                    contact.getFirstName() +  " " +
                    contact.getSecondName() + " " +
                    contact.getNobleTitle() + " " +
                    contact.getLastName();
        }
    }


}
