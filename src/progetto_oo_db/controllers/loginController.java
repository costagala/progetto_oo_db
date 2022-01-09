package progetto_oo_db.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import progetto_oo_db.dbUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private JFXButton btnAccedi;

    @FXML
    private JFXButton btnChiudi;

    @FXML
    private JFXButton btnGoToRegistrati;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private JFXButton btnCancel;


    @FXML
    void chiudiApplicazione(ActionEvent event) {
        Stage stage = (Stage) btnChiudi.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnGoToRegistrati.setOnAction(actionEvent -> {
            try {
                dbUtils.cambioScena(actionEvent, "views/registerPage.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnAccedi.setOnAction(ActionEvent -> {
            dbUtils.loginUtente(ActionEvent, txtUsername.getText(), txtPassword.getText());
        });
    }
}

