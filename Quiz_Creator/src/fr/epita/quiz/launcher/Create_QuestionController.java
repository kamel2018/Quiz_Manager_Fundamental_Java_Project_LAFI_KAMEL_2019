
 

/**
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template in the editor.
 *
 */
package fr.epita.quiz.launcher;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.quiz.services.data.Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * @author lafik
 *
 */

public class Create_QuestionController implements Initializable {

    @FXML
    private Label empty_label;
    @FXML
    private TextArea Area_field;
    @FXML
    private TextArea Area_pwd;
    @FXML
    private RadioButton radio_1;
    @FXML
    private ToggleGroup a;
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
    @FXML
    private AnchorPane root;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
    }   
    
    /**
     * this method will insert the question in to the database 
     *
     */
 
    @FXML
    private void submit(ActionEvent event) {
         try {
            Connection con = Connector.connect();
            PreparedStatement p = con.prepareStatement("INSERT INTO questions  VALUES (?,?,?,?,'','')");
            p.setString(2, Area_field.getText()); p.setString(3, Area_pwd.getText()); p.setString(4, "Math");
            int a=0;
            
            if (radio_1.isSelected())
                a=1;
            else if(radio_2.isSelected())
                a=2;
            else if(radio_3.isSelected())
                a=3;
            else if(radio_4.isSelected())
                a=4;
            else if(radio_5.isSelected())
                a=5;
            else if(radio_6.isSelected())
                a=6;
            else empty_label.setText("choose diff");
            p.setInt(1, a);
           
          
            p.execute();
            empty_label.setText("done");
           
        } catch (SQLException ex) {
              empty_label.setText("Try again."); 
        }
    }
    /**
     * this method is on the button back to main menu which goes back to the main menu scene 
     *
     */

    @FXML
    private void main_menu(ActionEvent event) {
          try {
            AnchorPane A = FXMLLoader.load(getClass().getResource("Main_menu.fxml"));
            root.getChildren().setAll(A);
                    } catch (IOException ex) {
            empty_label.setText("sth went wrong");
        }
    }
    
}
