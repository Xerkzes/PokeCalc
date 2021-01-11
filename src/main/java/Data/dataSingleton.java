package Data;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class dataSingleton {
    private static dataSingleton data = null;
    private String gameName;
    private Stage stage;private Scene menu;

    dataSingleton() {}

    public static dataSingleton getInstance() {
        if (data == null) data = new dataSingleton();
        return data;
    }

    // Setter & Getter
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getMenu() {
        return menu;
    }

    public void setMenu(Scene menu) {
        this.menu = menu;
    }
}
