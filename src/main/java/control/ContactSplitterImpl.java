package control;

import model.Contact;
import model.Gender;
import model.NobleTitles;
import model.Titles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactSplitterImpl implements ContactSplitter {
    private Pattern titlePattern;
    private Pattern nobiliaryPattern;
    private Pattern salutationPattern;

    public ContactSplitterImpl() {
        salutationPattern = Pattern.compile("Herr|Frau");
    }

    @Override
    public Contact parseContactString(String input) {
        Contact contact = new Contact();
        Matcher matcher;

        matcher = salutationPattern.matcher(input);
        if (matcher.find()) {
            contact.setSalutation(matcher.group());
            contact.setGender("Herr".equals(matcher.group()) ? Gender.M : Gender.F);
            input = input.substring(matcher.end()).trim();
        }

        titlePattern = Pattern.compile(String.join("|", Titles.titlesList));
        for (int i = 0; i < 2; i++) {
            matcher = titlePattern.matcher(input);

            if (matcher.find()) {
                if (i == 0) contact.setTitle1(matcher.group());
                if (i == 1) contact.setTitle2(matcher.group());
                String subString = input.substring(matcher.start(), matcher.end()).trim();
                input = input.replace(subString, "");
            }

        }

        nobiliaryPattern = Pattern.compile(String.join("|", NobleTitles.titlesList));
        matcher = nobiliaryPattern.matcher(input);
        if (matcher.find()) {
            contact.setNobleTitle(matcher.group());
            String subString = input.substring(matcher.start(), matcher.end()).trim();
            input = input.replace(subString, "");
        }

        input = input.trim();
        if (input.contains(",")) {
            String[] nameParts = input.split(",");
            String[] lastNames = nameParts[0].trim().split("\\s+");
            if (lastNames.length == 1) {
                contact.setLastName(lastNames[0]);
            } else {
                contact.setLastName(String.join("-", Arrays.copyOfRange(nameParts[0].split("\\s+"), 0, nameParts.length)));
            }

            String[] firstNames = nameParts[1].trim().split("\\s+");

            contact.setFirstName(firstNames[0]);
            if (firstNames.length > 1) {
                contact.setSecondName(firstNames[1]);
            }

        } else {
            String[] nameParts = input.split("\\s+");
            if (nameParts.length == 1) {
                contact.setLastName(nameParts[0]);
            }
            if (nameParts.length == 2) {
                contact.setFirstName(nameParts[0]);
                contact.setLastName(nameParts[nameParts.length - 1]);
            }
            if (nameParts.length == 3) {
                contact.setFirstName(nameParts[0]);
                contact.setSecondName(nameParts[1]);
                contact.setLastName(nameParts[2]);

            }
            if (nameParts.length > 3) {
                contact.setFirstName(nameParts[0]);
                contact.setSecondName(nameParts[1]);
                contact.setLastName(String.join("-", Arrays.copyOfRange(nameParts, 2, nameParts.length)));
            }

        }

        return contact;
    }
}