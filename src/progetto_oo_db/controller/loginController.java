package progetto_oo_db.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private JFXButton btnAccedi;

    @FXML
    private JFXButton btnChiudi;

    @FXML
    private JFXButton btnRegistrati;

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

}

