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
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label inputLabel = new Label("Vollständigen Namen eingeben:");
        TextField inputField = new TextField();
        Button parseButton = new Button("Parsen");
        Button generateButton = new Button("Anrede generieren");
        Button doneButton = new Button("Fertig");
        Label resultLabel = new Label("Ergebnis-Anrede");
        Label resultText = new Label("");
        ComboBox<Gender> genderComboBox = new ComboBox<>();

        TextField salutationField = new TextField();
        genderComboBox.getItems().setAll(Gender.values());
        genderComboBox.setValue(Gender.X);
        TextField title1Field = new TextField();
        TextField title2Field = new TextField();
        TextField firstNameField = new TextField();
        TextField secondNameField = new TextField();
        TextField nobleTitleField = new TextField();
        TextField lastNameField = new TextField();

        grid.add(inputLabel, 0, 0);
        grid.add(inputField, 0, 1);

        HBox buttonsBox = new HBox(10, parseButton, generateButton, doneButton);
        grid.add(buttonsBox, 0, 2);

        grid.add(new Label("Anrede:"), 1, 1);
        grid.add(salutationField, 2, 1);

        grid.add(new Label("Geschlecht:"), 1, 2);
        grid.add(genderComboBox, 2, 2);

        grid.add(new Label("Titel 1"), 1, 3);
        grid.add(title1Field, 2, 3);
        grid.add(new Label("Titel 2"), 3, 3);
        grid.add(title2Field, 4, 3);

        grid.add(new Label("Vorname"), 1, 4);
        grid.add(firstNameField, 2, 4);
        grid.add(new Label("2. Vorname"), 3, 4);
        grid.add(secondNameField, 4, 4);

        grid.add(new Label("Adelstitel"), 1, 5);
        grid.add(nobleTitleField, 2, 5);
        grid.add(new Label("Nachname"), 3, 5);
        grid.add(lastNameField, 4, 5);

        grid.add(resultLabel, 0, 6);
        grid.add(resultText, 0, 7);

        Scene scene = new Scene(grid, 900, 600);
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
