package view;

import control.ContactSplitter;
import control.ContactSplitterImpl;
import control.MessageGenerator;
import control.MessageGeneratorImpl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Contact;
import model.Gender;
import model.NobleTitles;
import model.Titles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The UI class defines the GUI which contains all visible elements.
 */
public class UI extends Application {

    /**
     * Contact object for storing the individual name parts.
     */
    private Contact contact = new Contact();

    /**
     * ContactSplitter object for splitting the input string into its individual parts.
     */
    private final ContactSplitter contactSplitter = new ContactSplitterImpl();

    /**
     * MessageGenerator object for generating the formal letter salutation.
     */
    private final MessageGenerator messageGenerator = new MessageGeneratorImpl();

    private TextField inputField;
    private TextField salutationField;
    private ComboBox<Gender> genderComboBox;
    private TextField title1Field, title2Field;
    private TextField firstNameField, secondNameField, nobleTitleField, lastNameField;
    private Label resultText;

    /**
     * Builds the UI and shows it to the user.
     *
     * @param primaryStage - stage for visualizing the UI.
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane outerGrid = new GridPane();
        outerGrid.setAlignment(Pos.CENTER_LEFT);
        outerGrid.setHgap(50);
        outerGrid.setVgap(10);

        GridPane leftInnerGrid = new GridPane();
        leftInnerGrid.setHgap(20);
        leftInnerGrid.setVgap(10);

        GridPane rightInnerGrid = new GridPane();
        rightInnerGrid.setHgap(20);
        rightInnerGrid.setVgap(10);

        Label inputLabel = new Label("Vollständigen Namen eingeben:");
        inputLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        Label editLabel = new Label("Namensbestandteile manuell eingeben oder bearbeiten:");
        editLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        outerGrid.add(inputLabel, 0, 0);
        outerGrid.add(editLabel, 1, 0);

        inputField = new TextField();
        Button parseButton = new Button("Parsen");
        Button generateButton = new Button("Anrede generieren");
        Button doneButton = new Button("Speichern");
        HBox buttonsBox = new HBox(10, parseButton, generateButton, doneButton);
        leftInnerGrid.add(buttonsBox, 0, 1);
        leftInnerGrid.add(inputField, 0, 0);

        salutationField = new TextField();
        genderComboBox = new ComboBox<>();
        genderComboBox.getItems().setAll(Gender.values());
        genderComboBox.setValue(Gender.X);
        title1Field = new TextField();
        title2Field = new TextField();
        firstNameField = new TextField();
        secondNameField = new TextField();
        nobleTitleField = new TextField();
        lastNameField = new TextField();

        rightInnerGrid.add(new Label("Anrede:"), 0, 0);
        rightInnerGrid.add(salutationField, 1, 0);

        rightInnerGrid.add(new Label("Geschlecht:"), 2, 0);
        rightInnerGrid.add(genderComboBox, 3, 0);

        rightInnerGrid.add(new Label("Titel 1:"), 0, 1);
        rightInnerGrid.add(title1Field, 1, 1);
        rightInnerGrid.add(new Label("Titel 2:"), 2, 1);
        rightInnerGrid.add(title2Field, 3, 1);

        rightInnerGrid.add(new Label("1. Vorname:"), 0, 2);
        rightInnerGrid.add(firstNameField, 1, 2);
        rightInnerGrid.add(new Label("2. Vorname:"), 2, 2);
        rightInnerGrid.add(secondNameField, 3, 2);

        rightInnerGrid.add(new Label("Adelstitel:"), 0, 3);
        rightInnerGrid.add(nobleTitleField, 1, 3);
        rightInnerGrid.add(new Label("Nachname:"), 2, 3);
        rightInnerGrid.add(lastNameField, 3, 3);


        outerGrid.add(leftInnerGrid, 0, 1);
        outerGrid.add(rightInnerGrid, 1, 1);

        Label resultLabel = new Label("Ergebnis-Anrede:");
        resultLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        resultText = new Label("");

        VBox vbox = new VBox(outerGrid, resultLabel, resultText);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(vbox, 900, 250);
        primaryStage.setTitle("SplitHorizon");
        primaryStage.setScene(scene);
        primaryStage.show();

        parseButton.setOnAction(event -> parseInput());

        generateButton.setOnAction(event -> generateMessage());

        doneButton.setOnAction(event -> clearFormAndPersistTitles());
    }

    private void parseInput() {
        contact = contactSplitter.parseContactString(inputField.getText());
        updateFieldsBasedOnContact();
    }

    private void generateMessage() {
        updateContactFromFields();
        String message = messageGenerator.generateMessage(contact);
        resultText.setText(message);
    }

    private void clearFormAndPersistTitles() {
        persistTitles();
        resetForm();
    }

    private void updateFieldsBasedOnContact() {
        salutationField.setText(contact.getSalutation());
        genderComboBox.setValue(contact.getGender());
        title1Field.setText(contact.getTitle1());
        title2Field.setText(contact.getTitle2());
        firstNameField.setText(contact.getFirstName());
        secondNameField.setText(contact.getSecondName());
        nobleTitleField.setText(contact.getNobleTitle());
        lastNameField.setText(contact.getLastName());
    }

    private void updateContactFromFields() {
        contact.setSalutation(salutationField.getText());
        contact.setGender(genderComboBox.getValue());
        contact.setTitle1(title1Field.getText());
        contact.setTitle2(title2Field.getText());
        contact.setFirstName(firstNameField.getText());
        contact.setSecondName(secondNameField.getText());
        contact.setNobleTitle(nobleTitleField.getText());
        contact.setLastName(lastNameField.getText());
    }

    private void resetForm() {
        contact = new Contact();
        inputField.setText("");
        salutationField.setText("");
        genderComboBox.setValue(Gender.X);
        title1Field.setText("");
        title2Field.setText("");
        firstNameField.setText("");
        secondNameField.setText("");
        nobleTitleField.setText("");
        lastNameField.setText("");
        resultText.setText("");
    }

    private void persistTitles() {
        Titles.addTitle(title1Field.getText());
        Titles.addTitle(title2Field.getText());
        NobleTitles.addTitle(nobleTitleField.getText());
    }
}
