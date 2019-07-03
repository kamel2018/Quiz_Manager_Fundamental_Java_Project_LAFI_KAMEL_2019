/**
  * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */

package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.Connector;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.TextField;


/**
 * @author lafik
 *
 */
public class Create_QuizController implements Initializable {

    @FXML
    private AnchorPane anchor;
    private Label label_question;
    @FXML
    private Label tf_Question_number;
    @FXML
    private Label empty_label;
    @FXML
    private Button btn_Main_menu;
    ResultSet rs = null;
    Connection con = Connector.connect();
    int diff = Main_menuController.diff;
    ArrayList<Question> Fresh_list = new ArrayList<>();
    Question[] used_Questions = new Question[10];
    String[] answers = new String[10];
    int number = -1;
    ArrayList<Question> questions = new ArrayList<Question>();
    @FXML
    private AnchorPane root;
    @FXML
    private TextArea tf_answer;
    @FXML
    private Label label_question3123123123;
    @FXML
    private TextField tf_name;

    /**
     * Initialises the controller class.
     */
    
    /**
     * the scene initialise when we have at least 10 questions of the desired subject with this difficulty
     * for example 10 questions (example: level 2 in math ) pre-inserted in the database
     */
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        	 /**
             * search the database for the questions of subject math and difficulty chosen by user
             */
        	
            rs = con.createStatement().executeQuery("select * from questions where topic ='Math' and diff='" + diff + "'");

            while (rs.next()) {
                questions.add(new Question(rs.getString("CONTENT"), rs.getString("ANS_CORRECT")));
            }
            
            Fresh_list = getRandomElements(questions, 10);
            setQ();
//                System.out.println(Q_picker().getContent());
        } catch (SQLException ex) {
            empty_label.setText("rs mafhash 7aga");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Create_QuizController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Create_QuizController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * this method saves the answer of the student and changes the question using method "setQ()"
     */
 
    @FXML
    private void submit(ActionEvent event) throws FileNotFoundException, DocumentException {
             
        if(!tf_answer.getText().equals(null)){
            answers[number]=tf_answer.getText();
            setQ();
        } else {
            empty_label.setText("Type an answer");
        }
        
    }
    /**
     * this method goes back to the main menu and its button only appears upon finishing the quiz (answering the 10 Questions)
     */

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
    and takes the number of elements (questions) desired to be used in quiz which is a constant (10)
    and the return is the random set of questions that will be used in teh quiz
     */

    public ArrayList<Question> getRandomElements(ArrayList<Question> list,
            int totalItems) {

        Random rand = new Random();
        /**
         * create a temporary list for storing selected element 
         */

       
        ArrayList<Question> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
        	 /**
             * take a random index between 0 to size  of given List 
             */
            
            int randomIndex = rand.nextInt(list.size());
            /**
             *  add element in temporary list 
             */
           
            newList.add(list.get(randomIndex));
            /**
             *  Remove selected element from original list 
             */
           
            
            list.remove(randomIndex);
        }
        return newList;
    }
    /**
     * this method checks if the number of answered questions is less than 10 
     * it changes the question in the UI and if it's more than 10 
     * it calls the method that saves the data in a PDF
     */
   
    private void setQ() throws FileNotFoundException, DocumentException {
        number++;
        if (number >= 10) {
//            Correct_Answers();
             make_pdf();
            return;
        }
        Question Q = Fresh_list.get(number);
        label_question3123123123.setText(Q.getContent());
        tf_Question_number.setText("Question " + (number + 1));
       
    }
    /**
     * the method which makes the PDF using the student's name ,the questions in the quiz ,their answers
     * and also the student's answer so that the professor can compare the answers for the evaluation
     */
    
    private void make_pdf() throws FileNotFoundException, DocumentException{
        
     Document doc = new Document();
     Random random=new Random();
     double n =random.nextInt(999999999);
     
     /**
      * change the destination of the PDF
      */
     
     
        PdfWriter.getInstance(doc, new FileOutputStream("d:/PDFs OF THE PROJECT "+n+"    "+tf_name.getText()+".pdf"));
        doc.open();
        doc.add(new Paragraph("Student's Name : "+tf_name.getText()));
        for (int i = 0; i < 10; i++) {
            doc.add(new Paragraph("\n\n\n\n\n"+(i+1)+"- "+Fresh_list.get(i).getContent()+"\n\nCorrect Answer : "+Fresh_list.get(i).getAns()+"\n\nStudent's Answer : "+answers[i]));
        }
        doc.close();
        empty_label.setText("done");
        btn_Main_menu.setVisible(true);

        
    }
    
}
