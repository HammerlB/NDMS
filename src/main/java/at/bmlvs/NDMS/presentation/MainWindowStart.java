package at.bmlvs.NDMS.presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowStart extends Application
{
    public void start(Stage stage) throws Exception {
        MainWindowControl mainWindowControl = new MainWindowControl();        
        stage.setScene(new Scene(mainWindowControl));
        stage.setTitle("Custom Control");
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
