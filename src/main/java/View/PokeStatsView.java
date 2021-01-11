package View;

import Classes.Attack;
import Classes.PokeStats;
import Classes.Sprite;
import Controller.PokeStatsController;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PokeStatsView {
    private static PokeStatsController controller;
    private static API.Database dbAPI;

    public void createPokeStatsPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("PokeStats");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("Edit PokeStats");
            stage.setScene(scene);
            stage.show();

            loadDynamicContent();
            selectFirstPokeStats();
        }
    }

    public void selectFirstPokeStats() {
        // set the first thing
        try {
            // nameOfPokemon
            HBox hbox = (HBox) controller.PokemonContainer.getChildren().get(0);
            // Label
            controller.smallSpriteOfPokemon = (Label) hbox.getChildren().get(0);
            controller.dexNrOfPokemon = (Label) hbox.getChildren().get(1);
            controller.nameOfPokemon = (Label) hbox.getChildren().get(2);
            // pokemonStatsId
            controller.pokemonStatsId = controller.pokeStatsList.get(0).pokemonStatsId;
            // spriteId
            controller.spriteId = controller.pokeSpriteList.get(0).spriteId;
            controller.PokemonContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            controller.previousPokeStatsBox = (HBox) controller.PokemonContainer.getChildren().get(0);
            setPokeStats(controller.pokeStatsList.get(0));
            setPokeStatsOption(controller.pokeStatsList.get(0).pokemonStatsId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadDynamicContent() {
        controller = PokeStatsController.getInstance();
        dbAPI = new API.Database();

        loadExistingPokeStats();
        loadImages();
        loadTypes();
        setExpGrowthRate();
    }

    private void setExpGrowthRate() {
        ObservableList<String> expGrowthRate = FXCollections.observableArrayList("Erratic", "Fast", "Medium Fast", "Medium Slow", "Slow", "Fluctuating");
        controller.ExpGrowthRate.setItems(expGrowthRate);
    }

    // --------------- Existing PokeStats ---------------
    private void loadExistingPokeStats() {
        try {
            Data.dataSingleton data = Data.dataSingleton.getInstance();
            // get List with all the PokeStats
            controller.pokeStatsList = FXCollections.observableArrayList();
            controller.pokeStatsList.add(new PokeStats(-1, "New Pokemon", 0, "Medium Slow", 0, 0, 0, 0, 0, 0, 0, 0));
            controller.pokeStatsList.addAll(dbAPI.getPokeStatsFromSpecificGame(data.getGameName()));
            // Create The Labels on the Side for loading in the data
            for (PokeStats pokeStats : controller.pokeStatsList) {
                createPokemonLabel(pokeStats);
            }
        } catch (Exception e) {
            System.out.println("No PokeStats existing or something went wrong.");
            e.printStackTrace();
        }
    }

    // --------------- Types ---------------
    private void loadTypes() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        // load types from db
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("");
        types.addAll(dbAPI.getAllTypeFromSpecificGame(data.getGameName()));
        // set Type
        controller.PokemonType1.setItems(types);
        controller.PokemonType2.setItems(types);
        // select the first thing
        controller.PokemonType1.getSelectionModel().selectFirst();
        controller.PokemonType2.getSelectionModel().selectFirst();
    }

    // --------------- Pokemon Label ---------------
    public void createPokemonLabel(PokeStats pokeStats) {
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        // Label
        Label smallSprite = new Label();
        Label dexNr = new Label(String.format("%03d", pokeStats.dexNr));
        Label btn = new Label(pokeStats.nameOfPokemon);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        if (pokeStats.pokemonStatsId > 0)
            ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificPokemon(pokeStats.pokemonStatsId));
        else {
            smallSprite.getStyleClass().add("createSmallSpriteNextToLabel");
            smallSprite.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
        }
        // dexNr and Name
        dexNr.getStyleClass().add("createPokemonLabel");
        btn.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActivePokeStatsBox(hbox);
            // set Values
            setPokeStats(pokeStats);
            // set Options
            setPokeStatsOption(pokeStats.pokemonStatsId);
            // set pokemonStatsId
            controller.pokemonStatsId = pokeStats.pokemonStatsId;
            controller.smallSpriteOfPokemon = smallSprite;
            controller.nameOfPokemon = btn;
            controller.dexNrOfPokemon = dexNr;
            controller.activePokeStatsBox = hbox;
            controller.doesSpeciesAlreadyExist();
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, dexNr, btn);
        // add To Controller
        controller.PokemonContainer.getChildren().add(hbox);
    }

    private void setCssOfActivePokeStatsBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        if (controller.previousPokeStatsBox != null) {
            controller.previousPokeStatsBox.getStyleClass().clear();
            controller.previousPokeStatsBox.getStyleClass().add("createPokemonHBox");
        }
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        controller.previousPokeStatsBox = hbox;
    }

    private void setPokeStatsOption(int pokemonStatsId) {
        if (pokemonStatsId >= 0) {
            controller.CreatePokeStatsButton.setDisable(true);
            controller.EditPokeStatsButton.setDisable(false);
            controller.DeletePokeStatsButton.setDisable(false);
        } else {
            controller.CreatePokeStatsButton.setDisable(false);
            controller.EditPokeStatsButton.setDisable(true);
            controller.DeletePokeStatsButton.setDisable(true);
        }
    }

    // --------------- Images ---------------
    private void loadImages() {
        try {
            // get List with all the PokeStats
            controller.pokeSpriteList = dbAPI.getAllPokemonSprites();
            // Create The Labels on the Side for loading in the data
            for (Sprite pokeSprite : controller.pokeSpriteList) {
                createSpriteLabel(pokeSprite);
            }

            // setSprite
            controller.previousSpriteBox = (HBox) controller.ImageContainer.getChildren().get(0);
        } catch (Exception e) {
            System.out.println("No Images were found.");
        }
    }

    private void createSpriteLabel(Sprite pokeSprite) {
        HBox hbox = new HBox(5);
        // Label
        Label smallImages = new Label();
        Label nameOfPokemon = new Label(pokeSprite.spriteName);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        setSmallSpriteNextToLabel(smallImages, pokeSprite);
        nameOfPokemon.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveSpriteBox(hbox);
            // set Values
            setSprite(pokeSprite.locationOfSprite);
            // set pokemonStatsId
            controller.spriteId = pokeSprite.spriteId;
        });

        // add to HBox
        hbox.getChildren().addAll(smallImages, nameOfPokemon);
        // add To Controller
        controller.ImageContainer.getChildren().add(hbox);
    }

    public void setSmallSpriteNextToLabel(Label smallImages, Sprite pokeSprite) {
        smallImages.getStyleClass().add("createSmallSpriteNextToLabel");
        smallImages.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSprite.locationOfSprite) + ");");
    }

    private void setCssOfActiveSpriteBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        if (controller.previousSpriteBox != null) {
            controller.previousSpriteBox.getStyleClass().clear();
            controller.previousSpriteBox.getStyleClass().add("createPokemonHBox");
        }
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        controller.previousSpriteBox = hbox;
    }

    public void setSprite(String spritePath) {
        controller.BackgroundSprite.setStyle("-fx-background-image: url(" + this.getClass().getResource(spritePath) + ");");
    }

    // --------------- set Stats ---------------
    private void setPokeStats(PokeStats pokeStats) {
        setMainStats(pokeStats);
        setBaseStats(pokeStats);
        int index = Utilities.findIndexOfSpriteStats(pokeStats, controller.pokeSpriteList);
        selectSpriteStats(index);
        // set previous Sprite
        setCssOfActiveSpriteBox((HBox) controller.ImageContainer.getChildren().get(index));
    }

    private void setMainStats(PokeStats pokeStats) {
        controller.Species.setText(pokeStats.nameOfPokemon);
        controller.DexNr.setText(Integer.toString(pokeStats.dexNr));
        controller.Height.setText(Double.toString(pokeStats.height));
        controller.Weight.setText(Double.toString(pokeStats.weight));
        controller.ExpGrowthRate.setValue(pokeStats.expGrowthRate);

        ArrayList<String> types = dbAPI.getTypesFromPokemonStatsId(pokeStats.pokemonStatsId);
        // Select Type when found
        for (int i = 0; i < types.size(); i++) {
            switch (i) {
                case 0 -> controller.PokemonType1.setValue(types.get(0));
                case 1 -> controller.PokemonType2.setValue(types.get(1));
            }
        }
        // no Type -> select no Type
        for (int i = types.size(); i < 2; i++) {
            switch (i) {
                case 0 -> controller.PokemonType1.getSelectionModel().selectFirst();
                case 1 -> controller.PokemonType2.getSelectionModel().selectFirst();
            }
        }
    }

    private void setBaseStats(PokeStats pokeStats) {
        controller.BaseHp.setText(Integer.toString(pokeStats.baseHp));
        controller.BaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        controller.BaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        controller.BaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        controller.BaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        controller.BaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    private void selectSpriteStats(int index) {
        try {
            controller.ImageContainer.getChildren().get(index).getStyleClass().add("createPokemonHBoxActive");
            controller.spriteId = controller.pokeSpriteList.get(index).spriteId;
            setSprite(controller.pokeSpriteList.get(index).locationOfSprite);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
