package progetto_oo_db;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static final int COUNT_LIMIT = 50000;
    private double xOffset=0;
    private double yOffset=0;

    public void init() throws Exception{
        for(int i=0; i<COUNT_LIMIT; i++){
            double progress = (100*i) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, preLoader.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/loginPage.fxml")));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });

            //Connessione al DB
            dbConnection conn = new dbConnection();
            conn.connessioneDB();
    }
}
