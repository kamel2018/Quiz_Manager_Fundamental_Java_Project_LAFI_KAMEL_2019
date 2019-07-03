/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 */

 
package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.Connector;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;


/**
 * @author lafik
 *
 */



public class Create_QuizJavaController implements Initializable {

    @FXML
    private Label tf_Question_number;
    @FXML
    private RadioButton Radio_1;
    @FXML
    private ToggleGroup a;
    @FXML
    private RadioButton Radio_2;
    @FXML
    private RadioButton Radio_3;
    private Label lbl_question;
    
    @FXML
    private AnchorPane anchor;
    int number = -1;
    Connection con = Connector.connect();
    ResultSet rs;
    ArrayList<Question> questions = new ArrayList<Question>();
    Question[] used_Questions = new Question[10];
    String[] answers = new String[10];
    @FXML
    private Label empty_label;
    int diff = Main_menuController.diff;
    ArrayList<Question> Fresh_list= new ArrayList<>();
    Question[] Questions_list = new Question[10];
    
    @FXML
    private AnchorPane root;
    @FXML
    private Label label_question;
    @FXML
    private Button btn_Main_menu;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            rs = con.createStatement().executeQuery("select * from questions where topic ='Java' and diff='" + diff + "'");

            while (rs.next()) {
                questions.add(new Question(rs.getString("CONTENT"), rs.getString("ANS_CORRECT"), rs.getString("ANS_WRONG1"), rs.getString("ANS_WRONG2")));
            }
            Fresh_list=getRandomElements(questions, 10);
            setQ();
//                System.out.println(Q_picker().getContent());
        } catch (SQLException ex) {
            empty_label.setText("");
        }

    }
    
    /**
     * this method checks if the number of answered questions is less than 10 it changes the question in the UI and if it's more than 10 it calls the method that calculates the score of the student
     * also this method has the ability to set the correct answer in a random radio button every time 
     *
     */
    
 
    private void setQ() {
        number++;
        if (number >= 10) {
            Correct_Answers();
            return;
        }
        Question Q = Fresh_list.get(number);
        label_question.setText(Q.getContent());
        tf_Question_number.setText("Question " + (number+1));

        Random rand = new Random();
        int n = rand.nextInt(6);
       
        Radio_1.setText(null);
        Radio_2.setText(null);
        Radio_3.setText(null);
        if (n == 0) {
            Radio_1.setText(Q.getAns());
            Radio_2.setText(Q.getWrong1());
            Radio_3.setText(Q.getWrong2());
        } else if (n == 1) {
            Radio_2.setText(Q.getAns());
            Radio_1.setText(Q.getWrong1());
            Radio_3.setText(Q.getWrong2());
        } else if (n == 2) {
            
            Radio_3.setText(Q.getAns());
            Radio_1.setText(Q.getWrong1());
            Radio_2.setText(Q.getWrong2());
        } else if (n == 3) {
            Radio_3.setText(Q.getAns());
            Radio_2.setText(Q.getWrong1());
            Radio_1.setText(Q.getWrong2());
        } else if (n == 4) {
            Radio_2.setText(Q.getAns());
            Radio_3.setText(Q.getWrong1());
            Radio_1.setText(Q.getWrong2());
        } else if (n == 5) {
            Radio_1.setText(Q.getAns());
            Radio_3.setText(Q.getWrong1());
            Radio_2.setText(Q.getWrong2());
        }
      //  System.out.println(number+ "ans "+Q.getAns()+" wrong 1 "+Q.getWrong1()+" wrong 2 "+Q.getWrong2());

    }

    private void Correct_Answers() {

        {
            int correct = 0;
            for (int i = 0; i < 10; i++) {
                if (Fresh_list.get(i).getAns().equals(answers[i])) {
                    correct++;
                }
            }
            empty_label.setText("Correct " + correct + "0/100");
            btn_Main_menu.setVisible(true);
        }
    }
 
    

    /**
     * 
     * this method saves the answer of the student and changes the question using method "setQ()"
     */
    @FXML
    private void submit(ActionEvent event) {
        
        if (Radio_1.isSelected()) {
            answers[number] = Radio_1.getText();
            Radio_1.setSelected(false);
            setQ();
        } else if (Radio_2.isSelected()) {
            answers[number] = Radio_2.getText();
            Radio_2.setSelected(false);
            setQ();
        } else if (Radio_3.isSelected()) {
            answers[number] = Radio_3.getText();
            Radio_3.setSelected(false);
            setQ();
        } else {
            empty_label.setText("choose an answer");
        }
        

    }

    @FXML
    private void go_to_main_menu(ActionEvent event) {
        try {
            AnchorPane A = FXMLLoader.load(getClass().getResource("Main_menu.fxml"));
            root.getChildren().setAll(A);
        } catch (IOException ex) {
            empty_label.setText("sth went wrong");
        }
    }
    
    /**
     * this method takes in parameters all the questions in  the database of desired subject and diff
     * and takes the number of elements (questions) desired to be used in quiz which is a constant (10)
     * and the return is the random set of questions that will be used in the quiz
     *
     */
  
    
 
    

    public ArrayList<Question> getRandomElements( ArrayList<Question> list, 
                                          int totalItems) 
    {
        
        Random rand = new Random(); 
  
        
        
        /**
         * 
         * create a temporary list for storing    
         *  selected element
         * 
         */
        
        ArrayList<Question> newList = new ArrayList<>(); 
        for (int i = 0; i < totalItems; i++) { 
                
        	   /**
             *
             *  take a random index between 0 to size of given List  
             * 
             */
             
            int randomIndex = rand.nextInt(list.size()); 
            /**
             * 
             *  add element in temporary list
             * 
             */
              
            newList.add(list.get(randomIndex)); 
            /**
             *
             * Remove selected element from original list
             *
             */
            
            list.remove(randomIndex); 
        } 
        return newList; 
    } 
} 

