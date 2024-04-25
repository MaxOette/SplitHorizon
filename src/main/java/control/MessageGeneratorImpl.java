package control;

import model.Contact;
import model.Gender;

public class MessageGeneratorImpl implements MessageGenerator {

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
