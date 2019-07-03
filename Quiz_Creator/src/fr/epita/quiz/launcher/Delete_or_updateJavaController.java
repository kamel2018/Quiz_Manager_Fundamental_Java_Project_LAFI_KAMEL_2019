/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.Connector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


/**
 * @author lafik
 *
 */

public class Delete_or_updateJavaController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Question> tv;
    @FXML
    private TableColumn<?, ?> c1_no;
    @FXML
    private TableColumn<?, ?> c2_diff;
    @FXML
    private TableColumn<?, ?> c3_Ques;
    @FXML
    private TableColumn<?, ?> c4_Ans;
    @FXML
    private TableColumn<?, ?> c5_wrong1;
    @FXML
    private TableColumn<?, ?> c6_wrong2;
    @FXML
    private RadioButton radio_question;
    @FXML
    private ToggleGroup bbba;
    @FXML
    private RadioButton radio_answer;
    @FXML
    private RadioButton radio_diff;
    @FXML
    private RadioButton radio_wrong1;
    @FXML
    private RadioButton radio_wrong2;
    @FXML
    private TextArea Area_upd;
    ResultSet rs = null;
    Connection con = Connector.connect();
    String Subject = null;
    int diff = 0;
    ObservableList<Question> list = FXCollections.observableArrayList();
    @FXML
    private Label empty_label;

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            c1_no.setCellValueFactory(new PropertyValueFactory<>("no"));
            c2_diff.setCellValueFactory(new PropertyValueFactory<>("diff"));
            c3_Ques.setCellValueFactory(new PropertyValueFactory<>("content"));
            c4_Ans.setCellValueFactory(new PropertyValueFactory<>("Ans"));
            c5_wrong1.setCellValueFactory(new PropertyValueFactory<>("wrong1"));
            c6_wrong2.setCellValueFactory(new PropertyValueFactory<>("wrong2"));
            rs = con.createStatement().executeQuery("select * from questions where topic ='Java'");
            int i = 0;
            while (rs.next()) {
                i++;
                list.add(new Question(rs.getString("content"), rs.getInt("diff"), rs.getString("ANS_CORRECT"), rs.getString("ANS_WRONG1"), rs.getString("ANS_WRONG2"), i));

                //                    Questions.add(new Question(rs.getString("content"), rs.getInt("diff"), rs.getString("ANS_CORRECT"), rs.getString("ANS_WRONG1"), rs.getString("ANS_WRONG2"),i));
                //(String content, Integer diff(the difficulty), String Ans(the answer), String wrong1, String wrong2) 
            }

            select();
        } catch (SQLException e) {

        }
    }

    @FXML
    private void Remove(ActionEvent event) throws SQLException {
        String s = tv.getSelectionModel().getSelectedItem().getContent();
        PreparedStatement p = con.prepareStatement("delete from questions where topic ='Java' and content='" + s + "'");
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
        try {
            if (radio_question.isSelected()) {
                PreparedStatement p = con.prepareStatement("update questions set content=? where ANS_CORRECT=? and diff=?");
                p.setInt(3, tv.getSelectionModel().getSelectedItem().getDifficulty());
                p.setString(1, Area_upd.getText());
                p.setString(2, tv.getSelectionModel().getSelectedItem().getAns());
                p.execute();
            } else if (radio_answer.isSelected()) {
                PreparedStatement p = con.prepareStatement("update questions set  ANS_CORRECT=? where content=? and ANS_WRONG1=?");
                p.setString(2, tv.getSelectionModel().getSelectedItem().getContent());
                p.setString(3, tv.getSelectionModel().getSelectedItem().getWrong1());
                p.setString(1, Area_upd.getText());
                p.execute();
            } else if (radio_diff.isSelected()) {
                PreparedStatement p = con.prepareStatement("update questions set  diff=? where content=? and ANS_WRONG1=?");
                p.setString(2, tv.getSelectionModel().getSelectedItem().getContent());
                p.setString(3, tv.getSelectionModel().getSelectedItem().getWrong1());
                p.setInt(1, Integer.parseInt(Area_upd.getText()));
                p.execute();
            } else if (radio_wrong1.isSelected()) {
                PreparedStatement p = con.prepareStatement("update questions set  ANS_WRONG1=? where content=? and ANS_CORRECT=?");
                p.setString(2, tv.getSelectionModel().getSelectedItem().getContent());
                p.setString(3, tv.getSelectionModel().getSelectedItem().getAns());
                p.setString(1, Area_upd.getText());
                p.execute();
            } else if (radio_wrong2.isSelected()) {
                PreparedStatement p = con.prepareStatement("update questions set  ANS_WRONG2=? where content=? and ANS_WRONG1=?");
                p.setString(2, tv.getSelectionModel().getSelectedItem().getContent());
                p.setString(3, tv.getSelectionModel().getSelectedItem().getWrong1());
                p.setString(1, Area_upd.getText());
                p.execute();
            } else {
                empty_label.setText("choose something to update");
            }
        } catch (Exception e) {
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
