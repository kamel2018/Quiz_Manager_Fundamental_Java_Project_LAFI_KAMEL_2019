/**
 *  To change this license header, choose License Headers in Project Properties.
 *  To change this template file, choose Tools | Templates
 *  and open the template in the editor.
 */

package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.Connector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.sound.midi.ControllerEventListener;


/**
 * @author lafik
 *
 */

public class Delete_or_updateController implements Initializable {

    @FXML
    private TableColumn<?, ?> c1_no;
    @FXML
    private TableColumn<?, ?> c2_diff;
    @FXML
    private TableColumn<?, ?> c3_Ques;
    @FXML
    private TableColumn<?, ?> c4_Ans;
   
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Question> tv;
    ResultSet rs = null;
    Connection con = Connector.connect();
    String Subject = null;
    int diff = 0;
//    ArrayList<Question> Questions = new ArrayList<>();
    ObservableList<Question> list = FXCollections.observableArrayList();
    @FXML
    private RadioButton radio_question;
    @FXML
    private RadioButton radio_answer;
    @FXML
    private RadioButton radio_diff;
    @FXML
    private TextArea Area_upd;
    @FXML
    private ToggleGroup bbbbb;
    @FXML
    private Label empty_label;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1226 and  1692
        
        try {
            c1_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            c2_diff.setCellValueFactory(new PropertyValueFactory<>("diff"));
            c3_Ques.setCellValueFactory(new PropertyValueFactory<>("content"));
            c4_Ans.setCellValueFactory(new PropertyValueFactory<>("Ans"));
         
            rs = con.createStatement().executeQuery("select * from questions where topic ='Math'");
            int i = 0;
            while (rs.next()) {
                i++;
                list.add(new Question(rs.getString("content"), rs.getInt("diff"), rs.getString("ANS_CORRECT"), i));
               
                    //                    Questions.add(new Question(rs.getString("content"), rs.getInt("diff"), rs.getString("ANS_CORRECT"), rs.getString("ANS_WRONG1"), rs.getString("ANS_WRONG2"),i));
                    
                 //(String content, Integer diff, String Ans, String wrong1, String wrong2) 
                
//                    Questions.add(new Question(rs.getString("content"), rs.getInt("diff"), rs.getString("ANS_CORRECT"), i));
                 // diff represents " the difficulty"   
               
               
            }
            
            select();
        } catch (SQLException e) {

        }
    }

    @FXML
    private void Remove(ActionEvent event) throws SQLException {
        String s = tv.getSelectionModel().getSelectedItem().getContent();
        PreparedStatement p = con.prepareStatement("delete from questions where topic='Math' and content='" + s + "'");
        p.execute();
        select();
    }

    void select() throws SQLException {
       rs = con.createStatement().executeQuery("select * from questions where topic ='Java'");
        int i = 0;
        list.removeAll(list);
        while (rs.next()) {
            
            i++;
            list.add(new Question(rs.getString("content"), rs.getInt("diff"), rs.getString("ANS_CORRECT"), rs.getString("ANS_WRONG1"), rs.getString("ANS_WRONG2"), i));
        }
        tv.setItems(list);
        
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
      try{
             if(radio_question.isSelected()){
            PreparedStatement p = con.prepareStatement("update questions set content=? where ANS_CORRECT=? and diff=?");
           p.setInt(3, tv.getSelectionModel().getSelectedItem().getDifficulty());
            p.setString(1, Area_upd.getText());
             p.setString(2, tv.getSelectionModel().getSelectedItem().getAns());
             p.execute();
        }
        else if(radio_answer.isSelected()){
            PreparedStatement p = con.prepareStatement("update questions set  ANS_CORRECT=? where content=? and diff=?");
             p.setString(2, tv.getSelectionModel().getSelectedItem().getContent());
             p.setInt(3, tv.getSelectionModel().getSelectedItem().getDifficulty());
             p.setString(1, Area_upd.getText());
             p.execute();
        }
        else if(radio_diff.isSelected()){
            PreparedStatement p = con.prepareStatement("update questions set  diff=? where content=? and ANS_CORRECT=?");
             p.setString(2, tv.getSelectionModel().getSelectedItem().getContent());
             p.setString(3, tv.getSelectionModel().getSelectedItem().getAns());
             p.setInt(1, Integer.parseInt(Area_upd.getText()));
             p.execute();
        }
       
        else empty_label.setText("choose something to update");
        
      }
      catch(Exception e) {
         empty_label.setText("Try again");
      }
        select();
        
    }

    @FXML
    private void Quit(ActionEvent event) throws SQLException {
        con.close();
        Stage stage = (Stage) tv.getScene().getWindow();
        stage.close();
    }

}
