package Data;

import Classes.PokeStats;
import Classes.Pokemon;
import Classes.PokemonList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class dataSingleton {
    private static dataSingleton data = null;
    private String gameName;
    private Stage stage;private Scene menu;
    public final Pokemon tempPokemon = new Pokemon(0,0,0,0,"Bashful",0,0,"No Pokemon", "",0,0,"",0,false,0,0,0, 0, 0,0,0,0,0,0,0,0);
    public final PokeStats pokeStats = new PokeStats(0, "No Pokemon", 0, "Slow", 0, 0, 0, 0, 0, 0, 0, 0);
    public final PokemonList pokemonList = new PokemonList(0,  "No Pokemon", 0, 0);

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
