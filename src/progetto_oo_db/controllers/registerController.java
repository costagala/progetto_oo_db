package progetto_oo_db.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import progetto_oo_db.dbUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class registerController implements Initializable {

    @FXML
    private JFXButton btnBackToAccedi;

    @FXML
    private JFXButton btnCancella;

    @FXML
    private JFXButton btnRegistrato;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void chiudiApplicazione(ActionEvent event) {
        Stage stage = (Stage) btnCancella.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnBackToAccedi.setOnAction(actionEvent -> {
            try{
                dbUtils.cambioScena(actionEvent, "views/loginPage.fxml");
            }catch(IOException e){
                e.printStackTrace();
            }
        });

        btnRegistrato.setOnAction(actionEvent -> {
            dbUtils.registrazioneUtente(actionEvent, txtUsername.getText(), txtPassword.getText(), txtEmail.getText());
        });
    }
}