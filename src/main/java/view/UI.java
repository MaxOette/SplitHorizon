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
import javafx.stage.Stage;
import model.Contact;
import model.Gender;
import model.NobleTitles;
import model.Titles;

public class UI extends Application {

    private Contact contact = new Contact();

    private final ContactSplitter contactSplitter = new ContactSplitterImpl();

    private final MessageGenerator messageGenerator = new MessageGeneratorImpl();


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
        Label editLabel = new Label("Namensbestandteile manuell eingeben oder bearbeiten:");
        outerGrid.add(inputLabel, 0, 0);
        outerGrid.add(editLabel, 1, 0);

        TextField inputField = new TextField();
        Button parseButton = new Button("Parsen");
        Button generateButton = new Button("Anrede generieren");
        Button doneButton = new Button("Fertig");
        HBox buttonsBox = new HBox(10, parseButton, generateButton, doneButton);
        leftInnerGrid.add(buttonsBox, 0, 1);
        leftInnerGrid.add(inputField, 0, 0);

        TextField salutationField = new TextField();
        ComboBox<Gender> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().setAll(Gender.values());
        genderComboBox.setValue(Gender.X);
        TextField title1Field = new TextField();
        TextField title2Field = new TextField();
        TextField firstNameField = new TextField();
        TextField secondNameField = new TextField();
        TextField nobleTitleField = new TextField();
        TextField lastNameField = new TextField();

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
        Label resultText = new Label("");

        VBox vbox = new VBox(outerGrid, resultLabel, resultText);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(vbox, 900, 250);
        primaryStage.setTitle("SplitHorizon");
        primaryStage.setScene(scene);
        primaryStage.show();

        parseButton.setOnAction(event ->{
            String input = inputField.getText();
            contact = contactSplitter.parseContactString(input);

            salutationField.setText(contact.getSalutation());
            genderComboBox.setValue(contact.getGender());
            title1Field.setText(contact.getTitle1());
            title2Field.setText(contact.getTitle2());
            firstNameField.setText(contact.getFirstName());
            secondNameField.setText(contact.getSecondName());
            nobleTitleField.setText(contact.getNobleTitle());
            lastNameField.setText(contact.getLastName());
        });

        generateButton.setOnAction(event -> {
            contact.setSalutation(salutationField.getText());
            contact.setGender(genderComboBox.getValue());
            contact.setTitle1(title1Field.getText());
            contact.setTitle2(title2Field.getText());
            contact.setFirstName(firstNameField.getText());
            contact.setSecondName(secondNameField.getText());
            contact.setNobleTitle(nobleTitleField.getText());
            contact.setLastName(lastNameField.getText());

            resultText.setText(messageGenerator.generateMessage(contact));
        });

        doneButton.setOnAction(event -> {
            if (!Titles.titlesList.contains(title1Field.getText())) {
                Titles.titlesList.add(title1Field.getText());
            }
            if (!Titles.titlesList.contains(title2Field.getText())) {
                Titles.titlesList.add(title2Field.getText());
            }
            if (!NobleTitles.titlesList.contains(nobleTitleField.getText())) {
                NobleTitles.titlesList.add(nobleTitleField.getText());
                System.out.println("Added the following to noble Titles: " + nobleTitleField.getText());
            }

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
        });
    }

}
