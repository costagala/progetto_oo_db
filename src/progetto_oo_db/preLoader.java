package progetto_oo_db;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class preLoader extends Preloader {

    private Stage preLoaderStage;
    private Scene scene;

    public preLoader() {

    }

    @Override
    public void init() throws Exception{
        Parent root1 = FXMLLoader.load((getClass().getResource("views/splashScreen.fxml")));
        scene = new Scene(root1);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.preLoaderStage = stage;

        //Settiamo il preloader e mostra lo stage
        preLoaderStage.setScene(scene);
        preLoaderStage.initStyle(StageStyle.UNDECORATED);
        Image iconaBook = new Image("progetto_oo_db/icons/book.png");
        preLoaderStage.getIcons().add(iconaBook);
        preLoaderStage.show();
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info){
        if(info instanceof ProgressNotification)
            FXMLDocumentController.label.setText("Loading"+((ProgressNotification) info).getProgress()+"%");
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info){

        StateChangeNotification.Type type = info.getType();
        switch (type)
        {
            case BEFORE_START:
                System.out.println("BEFORE START");
                preLoaderStage.hide();
                break;
        }
    }
}
