package View;

import Classes.PokeStats;
import Classes.Sprite;
import Controller.PokeStatsController;
import Intefaces.InterfacePokeStatsView;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PokeStatsView implements InterfacePokeStatsView {
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
            this.setPokeStats(controller.pokeStatsList.get(0), controller);
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
        loadTypes(controller);
        setExpGrowthRate(controller);
    }

    // --------------- Existing PokeStats ---------------
    private void loadExistingPokeStats() {
        try {
            Data.dataSingleton data = Data.dataSingleton.getInstance();
            API.Database dbAPI = new API.Database();
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
            this.setPokeStats(pokeStats, controller);
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
            setCssOfActiveSpriteBox(hbox, controller);
            // set Values
            setSprite(pokeSprite.locationOfSprite, controller);
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


}
