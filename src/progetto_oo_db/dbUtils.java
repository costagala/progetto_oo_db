package progetto_oo_db;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class dbUtils {

    public static void cambioScena(ActionEvent event, String fxmlFile) throws IOException {

        Parent root = null;

        root = FXMLLoader.load(Objects.requireNonNull(dbUtils.class.getResource(fxmlFile)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void registrazioneUtente(ActionEvent event, String username, String password, String email) {

        Connection connection = null;
        PreparedStatement psInserisci = null;
        PreparedStatement psUtenteEsiste = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/progetto_oo_db", "postgres", "admin");
            psUtenteEsiste = connection.prepareStatement("SELECT * FROM public.utente WHERE username = ?");
            psUtenteEsiste.setString(1, username);
            resultSet = psUtenteEsiste.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("L'utente esiste gia'");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Non puoi usare questo username!");
                alert.show();
            } else {
                psInserisci = connection.prepareStatement("INSERT INTO public.utente (username,password,email) VALUES (?, ?, ?)");
                psInserisci.setString(1, username);
                psInserisci.setString(2, password);
                psInserisci.setString(3, email);
                psInserisci.executeUpdate();

                cambioScena(event, "views/loginPage.fxml");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInserisci != null) {
                try {
                    psInserisci.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psUtenteEsiste != null) {
                try {
                    psUtenteEsiste.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loginUtente(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/progetto_oo_db", "postgres", "admin");
            preparedStatement = connection.prepareStatement("SELECT password FROM public.utente WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Utente non trovato nel database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Le credenziali inserite non sono corrette!");
                alert.show();
            } else {
                while(resultSet.next()) {
                    String passwordTrovata = resultSet.getString("password");
                    if (passwordTrovata.equals(password)){
                        cambioScena(event, "views/Dashboard.fxml");
                    } else {
                        System.out.println("La password non e' corretta!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Le credenziali inserite non sono corrette!");
                        alert.show();
                    }
                }
            }
        } catch(SQLException | IOException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

