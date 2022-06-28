import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.LoginBO;


public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/Hostel_Management_System/view/LoginForm.fxml"))));
        primaryStage.show();
    }
}
