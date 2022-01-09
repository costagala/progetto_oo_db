package progetto_oo_db.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class splashScreenController implements Initializable {

    @FXML
    private Label progress;

    public static Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label = progress;
    }
}
