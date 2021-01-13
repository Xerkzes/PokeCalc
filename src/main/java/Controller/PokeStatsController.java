package Controller;

import Classes.Abstract.AbstractPokeStatsController;
import Classes.PokeStats;
import Classes.Sprite;
import Utilities.Utilities;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PokeStatsController extends AbstractPokeStatsController {
    // static method to create instance of Singleton class
    private static PokeStatsController controller = null;
    public static PokeStatsController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    public VBox PokemonContainer;
    public Button CreatePokeStatsButton;
    public Button EditPokeStatsButton;
    public Button DeletePokeStatsButton;
    // Variables
    public HBox previousPokeStatsBox;
    public HBox activePokeStatsBox;
    public int pokemonStatsId;
    public Label smallSpriteOfPokemon;
    public Label dexNrOfPokemon;


    public void backToMenu() {
        Utilities.backToMenu();
    }

    // --------------- Search ---------------
    public void searchForPokeStats() {
    }

    public void searchForSprite() {
    }

    // --------------- Options ---------------
    public void createPokeStats() {
        try {
            if (Species.getText().length() > 0) {
                // get Values
                String nameOfPokemon = Species.getText();
                int dexNr = Integer.parseInt(DexNr.getText());
                String expGrowthRate = ExpGrowthRate.getValue();
                double height = Double.parseDouble(Height.getText());
                double weight = Double.parseDouble(Weight.getText());
                int baseHp = Integer.parseInt(BaseHp.getText());
                int baseAttack = Integer.parseInt(BaseAttack.getText());
                int baseDefense = Integer.parseInt(BaseDefense.getText());
                int baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
                int baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
                int baseSpeed = Integer.parseInt(BaseSpeed.getText());
                String type1 = PokemonType1.getValue();
                String type2 = PokemonType2.getValue();

                API.Database dbAPI = new API.Database();
                Data.dataSingleton data = Data.dataSingleton.getInstance();

                //  check if Species already in PokeStats
                boolean noError = Utilities.findPokeStatsInList(nameOfPokemon, pokeStatsList);

                // create Species
                if (!noError) {
                    int pokeStatsId = dbAPI.addPokeStatsToGame(data.getGameName(), nameOfPokemon, dexNr, expGrowthRate, height,
                            weight, baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed, spriteId, type1, type2);
                    if (pokeStatsId > 0) {
                        addPokeStatsToList(pokeStatsId, nameOfPokemon, dexNr, expGrowthRate, height, weight, baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed);
                        doesSpeciesAlreadyExist();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editPokeStats() {
        boolean result = false;

        try {
            API.Database dbAPI = new API.Database();

            String species = Species.getText();
            int dexNr = Integer.parseInt(DexNr.getText());
            double height = Double.parseDouble(Height.getText());
            double weight = Double.parseDouble(Weight.getText());
            String expGrowthRate = ExpGrowthRate.getValue();
            int baseHp = Integer.parseInt(BaseHp.getText());
            int baseAttack = Integer.parseInt(BaseAttack.getText());
            int baseDefense = Integer.parseInt(BaseDefense.getText());
            int baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
            int baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
            int baseSpeed = Integer.parseInt(BaseSpeed.getText());
            String type1 = PokemonType1.getValue();
            String type2 = PokemonType2.getValue();

            //  check if Species already in PokeStats
            boolean noError = doesSpeciesAlreadyExist();

            if (!noError) result = dbAPI.updatePokeStats(pokemonStatsId, species, dexNr, expGrowthRate, height, weight,
                    baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed, spriteId, type1, type2);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result) {
            setPokeStatsAndImage();
            doesSpeciesAlreadyExist();
        }
    }

    public void deletePokeStats() {
        if (pokeStatsList.size() > 0) {
            API.Database dbAPI = new API.Database();
            boolean result = dbAPI.deletePokeStats(pokemonStatsId);

            if (result) {
                removePokemonFromLists();
                selectNewFirstPokeStats();
            }
        }
    }

    // Add PokeStats
    private void addPokeStatsToList(int pokeStatsId, String nameOfPokemon, int dexNr, String expGrowthRate, double height, double weight, int baseHp, int baseAttack, int baseDefense, int baseSpecialAttack, int baseSpecialDefense, int baseSpeed) {
        nameOfPokemon = nameOfPokemon.substring(0, 1).toUpperCase() + nameOfPokemon.substring(1);
        PokeStats newPokeStat = new PokeStats(pokeStatsId, nameOfPokemon, dexNr, expGrowthRate, height, weight, baseHp, baseAttack, baseDefense, baseSpecialAttack, baseSpecialDefense, baseSpeed);

        View.PokeStatsView pv = new View.PokeStatsView();
        pv.createPokemonLabel(newPokeStat); // adds To ViewList
        pokeStatsList.add(newPokeStat);     // adds To PokeStatsList
    }

    // Edit PokeStats
    private void setPokeStatsAndImage() {
        setPokeStats();
        setSmallSpriteNextToLabel();
    }

    private void setPokeStats() {
        String species = Species.getText();
        species = species.substring(0, 1).toUpperCase() + species.substring(1);
        int dexNr = Integer.parseInt(DexNr.getText());
        double height = Double.parseDouble(Height.getText());
        double weight = Double.parseDouble(Weight.getText());
        String expGrowthRate = ExpGrowthRate.getValue();
        int baseHp = Integer.parseInt(BaseHp.getText());
        int baseAttack = Integer.parseInt(BaseAttack.getText());
        int baseDefense = Integer.parseInt(BaseDefense.getText());
        int baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
        int baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
        int baseSpeed = Integer.parseInt(BaseSpeed.getText());

        int index = Utilities.findIndexOfPokeStats(nameOfPokemon.getText(), pokeStatsList);
        if (index >= 0) {
            pokeStatsList.get(index).nameOfPokemon = species;
            pokeStatsList.get(index).dexNr = dexNr;
            pokeStatsList.get(index).height = height;
            pokeStatsList.get(index).weight = weight;
            pokeStatsList.get(index).expGrowthRate = expGrowthRate;
            pokeStatsList.get(index).baseHp = baseHp;
            pokeStatsList.get(index).baseAttack = baseAttack;
            pokeStatsList.get(index).baseDefense = baseDefense;
            pokeStatsList.get(index).baseSpecialAttack = baseSpecialAttack;
            pokeStatsList.get(index).baseSpecialDefense = baseSpecialDefense;
            pokeStatsList.get(index).baseSpeed = baseSpeed;
        }

        nameOfPokemon.setText(species); // works since the Label is a reference to the Object holding the name
        dexNrOfPokemon.setText(String.format("%03d", dexNr));
    }

    private void setSmallSpriteNextToLabel() {
        API.Database dbAPI = new API.Database();
        String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        smallSpriteOfPokemon.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
    }

    // Delete PokeStats
    private void removePokemonFromLists() {
        deletePokeStatsFromPokeStatsList();
        PokemonContainer.getChildren().remove(activePokeStatsBox);
    }

    private void selectNewFirstPokeStats() {
        View.PokeStatsView pv = new View.PokeStatsView();
        pv.selectFirstPokeStats();
    }

    private void deletePokeStatsFromPokeStatsList() {
        int index = 0;
        for (PokeStats pokeStats : pokeStatsList) {
            if (pokeStats.pokemonStatsId == pokemonStatsId) {
                break;
            }
            index++;
        }
        pokeStatsList.remove(index);
    }
}
