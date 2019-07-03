
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.epita.quiz.launcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * @author lafik
 *
 */

public class Main_menuController implements Initializable {

    @FXML
    private RadioButton java;
    @FXML
    private RadioButton math;
    @FXML
    private AnchorPane root;
    @FXML
    private ToggleGroup b;
    @FXML
    private Label empty_label_create_question;
    @FXML
    private RadioButton quiz_java;
    @FXML
    private RadioButton quiz_math;
    @FXML
    private Label empty_label_create_quiz;
    @FXML
    private RadioButton radio_1;
    @FXML
    private ToggleGroup c;
    @FXML
    private RadioButton radio_2;
    @FXML
    private RadioButton radio_3;
    @FXML
    private RadioButton radio_4;
    @FXML
    private RadioButton radio_5;
    @FXML
    private RadioButton radio_6;
    public static int diff;
    public static String subject;

    public static int getDiff() {
        return diff;
    }

    public static void setDiff(int diff) {
        Main_menuController.diff = diff;
    }
    @FXML
    private RadioButton Java_delete;
    @FXML
    private RadioButton Math_delete;
    @FXML
    private Label empty_label_delete_update;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void create_question(ActionEvent event) {
        try {
            AnchorPane a;
//            Stage s= new Stage();
//            Scene ss= new Scene(a);
            if (java.isSelected()) {

                AnchorPane A = FXMLLoader.load(getClass().getResource("Create_QuestionJava.fxml"));
                root.getChildren().setAll(A);
            } else if (math.isSelected()) {
                a = FXMLLoader.load(getClass().getResource("Create_Question.fxml"));
                root.getChildren().setAll(a);
            } else {
                empty_label_create_question.setText("choose topic");
            }
        } catch (IOException ex) {
            Logger.getLogger(Main_menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void create_quiz(ActionEvent event) {
        try {
            if (radio_1.isSelected()) {
                diff = 1;
            } else if (radio_2.isSelected()) {
                diff = 2;
            } else if (radio_3.isSelected()) {
                diff = 3;
            } else if (radio_4.isSelected()) {
                diff = 4;
            } else if (radio_5.isSelected()) {
                diff = 5;
            } else if (radio_6.isSelected()) {
                diff = 6;
            } else {
                empty_label_create_quiz.setText("choose diff");
            }
            AnchorPane a;
//            Stage s= new Stage();
//            Scene ss= new Scene(a);
            Stage stage = (Stage) math.getScene().getWindow();
            stage.setMinWidth(1020);
            if (quiz_java.isSelected()) {

                AnchorPane A = FXMLLoader.load(getClass().getResource("Create_QuizJava.fxml"));
                root.getChildren().setAll(A);
            } else if (quiz_math.isSelected()) {
                a = FXMLLoader.load(getClass().getResource("Create_Quiz.fxml"));

                root.getChildren().setAll(a);
            } else {
                empty_label_create_quiz.setText("choose topic");
            }

        } catch (IOException ex) {
            Logger.getLogger(Main_menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete_update(ActionEvent event) throws IOException {
        AnchorPane anchor ;
        
           
       
        
        if (Java_delete.isSelected()) {
            anchor = FXMLLoader.load(getClass().getResource("delete_or_updateJava.fxml"));
            Scene s = new Scene(anchor);
        Stage window = new Stage();
        window.setScene(s);
        window.initModality(Modality.APPLICATION_MODAL);
            subject = "Java";
            window.show();
        } else if (Math_delete.isSelected()) {
             anchor = FXMLLoader.load(getClass().getResource("delete_or_update.fxml"));
             Scene s = new Scene(anchor);
        Stage window = new Stage();
        window.setScene(s);
        window.initModality(Modality.APPLICATION_MODAL);
            subject="Math";
            window.show();
        } else {
            empty_label_delete_update.setText("choose topic");
        }

    }

    @FXML
    private void quit(ActionEvent event) {
        Stage stage = (Stage) math.getScene().getWindow();
        stage.close();
    }
}
