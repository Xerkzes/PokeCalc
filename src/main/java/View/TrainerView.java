package View;

import Classes.*;
import Controller.TrainerController;
import Intefaces.InterfacePokemonView;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TrainerView implements InterfacePokemonView {
    private static TrainerController controller;
    private static API.Database dbAPI;
    private static Data.dataSingleton data;
    private static HBox previousTrainerSpriteBox;
    public static HBox previousPokemonBox;
    private static HBox previousTrainerBox;
    private static ObservableList<Sprite> trainerSpritesList;
    public static ObservableList<Trainer> trainerList;
    public static ObservableList<PokemonList> pokemonList;

    public void createPokemonPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("Trainer");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("Edit Trainer");
            stage.setScene(scene);
            stage.show();

            loadDynamicContent();
            selectFirstTrainer();
        }
    }

    private void loadDynamicContent() {
        controller = TrainerController.getInstance();
        dbAPI = new API.Database();
        data = Data.dataSingleton.getInstance();

        this.loadGlobalContent(data.getGameName(), controller);
        loadTrainerSprites();
        loadExistingTrainers();
        loadPokemons();
    }

    // Trainer-Sprites
    private void loadTrainerSprites() {
        trainerSpritesList = dbAPI.getAllTrainerSprites();
        for (Sprite trainerSprite : trainerSpritesList) {
            createTrainerSpriteLabel(trainerSprite);
        }
    }

    private void createTrainerSpriteLabel(Sprite trainerSprite) {
        HBox hbox = new HBox(5);
        // Label
        Label smallImages = new Label();
        Label nameOfTrainer = new Label(trainerSprite.spriteName);
        // hidden identifier
        Label identifier = new Label(Integer.toString(trainerSprite.spriteId));
        identifier.setMaxWidth(0);
        identifier.setVisible(false);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        setSmallSpriteNextToLabel(smallImages, trainerSprite);
        nameOfTrainer.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveTrainerSpriteBox(hbox);
            // set Values
            setTrainerSprite(trainerSprite.locationOfSprite);
            // set pokemonStatsId
            controller.trainerSpriteId = trainerSprite.spriteId;
        });

        // add to HBox
        hbox.getChildren().addAll(smallImages, nameOfTrainer, identifier);
        // add To Controller
        controller.TrainerSpriteContainer.getChildren().add(hbox);
    }

    private void setSmallSpriteNextToLabel(Label smallImages, Sprite sprite) {
        smallImages.getStyleClass().add("createSmallSpriteNextToLabel");
        smallImages.setStyle("-fx-background-image: url(" + this.getClass().getResource(sprite.locationOfSprite) + ");");
    }

    private void setCssOfActiveTrainerSpriteBox(HBox hbox) {
        previousTrainerSpriteBox.getStyleClass().clear();
        previousTrainerSpriteBox.getStyleClass().add("createPokemonHBox");
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousTrainerSpriteBox = hbox;
    }

    public void setTrainerSprite(String spritePath) {
        controller.TrainerSpriteBackground.setStyle("-fx-background-image: url(" + this.getClass().getResource(spritePath) + ");");
    }

    // Existing Trainer
    private void loadExistingTrainers() {
        // get List with all the PokeStats
        trainerList = FXCollections.observableArrayList(new Trainer(0, 0, "New Trainer", 0, false));
        trainerList.addAll(dbAPI.getAllTrainerFromSpecificGame(data.getGameName()));

        for (Trainer trainer : trainerList) {
            createTrainerLabel(trainer);
        }
    }

    public void createTrainerLabel(Trainer trainer) {
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        if (trainer.trainerId > 0) ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId));
        // dexNr and Name
        Label dexNr;
        if (trainer.trainerId > 0) dexNr = new Label(Integer.toString(trainer.fightNumber));
        else dexNr = new Label();
        Label btn = new Label(trainer.trainerName);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        if (trainer.trainerId > 0) smallSprite.getStyleClass().add("createSmallSpriteNextToLabel");
        dexNr.getStyleClass().add("createPokemonLabel");
        btn.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveTrainerBox(hbox);
            // set Values
            setTrainerStats(trainer);
            // set the current Trainer Box
            controller.activeTrainerBox = hbox;
            controller.trainerId = trainer.trainerId;
            controller.trainerSpriteLabel = smallSprite;
            controller.fightNumberLabel = dexNr;
            controller.trainerNameLabel = btn;
            // set Trainer Options
            setTrainerOptions(trainer);
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, dexNr, btn);
        // add To Controller
        controller.TrainerContainer.getChildren().add(hbox);
    }

    private void setCssOfActiveTrainerBox(HBox hbox) {
        previousTrainerBox.getStyleClass().clear();
        previousTrainerBox.getStyleClass().add("createPokemonHBox");
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousTrainerBox = hbox;
    }

    private void setTrainerOptions(Trainer trainer) {
        if (trainer.trainerId > 0) {
            controller.CreateTrainerButton.setDisable(true);
            controller.EditTrainerButton.setDisable(false);
            controller.DeleteTrainerButton.setDisable(false);
        } else {
            controller.CreateTrainerButton.setDisable(false);
            controller.EditTrainerButton.setDisable(true);
            controller.DeleteTrainerButton.setDisable(true);
        }
    }

    private void setTrainerStats(Trainer trainer) {
        setMainValues(trainer);
        setSpriteValue(trainer);
        setPokemonValues(trainer);
        updatePokemonSprite();
        selectFirstPokemon();
        setPokemonOptions(controller.pokemon1);
    }

    private void setMainValues(Trainer trainer) {
        controller.TrainerName.setText(trainer.trainerName);
        controller.FightNumber.setText(Integer.toString(trainer.fightNumber));
        // Fought Already
        ObservableList<String> foughtAlready = FXCollections.observableArrayList("True", "False");
        controller.FoughtAlready.setItems(foughtAlready);
        controller.FoughtAlready.setValue(trainer.foughtAlready ? "True" : "False");
        // Route
        if (trainer.routeId > 0) Utilities.selectMetLocation(trainer.routeId, controller.MetLocation.getItems(), controller.Route);
        else controller.Route.getSelectionModel().selectFirst();
    }

    private void setSpriteValue(Trainer trainer) {
        int indexOfSprite;
        if (trainer.trainerId > 0) indexOfSprite = Utilities.findTrainerSprite(trainer, controller.TrainerSpriteContainer);
        else indexOfSprite = 0;

        controller.TrainerSpriteContainer.getChildren().get(indexOfSprite).getStyleClass().add("createPokemonHBoxActive");
        setCssOfActiveTrainerSpriteBox((HBox) controller.TrainerSpriteContainer.getChildren().get(indexOfSprite));
        setTrainerSprite(trainerSpritesList.get(indexOfSprite).locationOfSprite);

        previousTrainerSpriteBox = (HBox) controller.TrainerSpriteContainer.getChildren().get(indexOfSprite);
        controller.trainerSpriteId = trainerSpritesList.get(indexOfSprite).spriteId;
    }

    private void setPokemonValues(Trainer trainer) {
        // set Pokemons
        ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);

        // not working since it doesn't pass by reference :(
//        Pokemon[] trainerPokemons = {controller.pokemon1, controller.pokemon2, controller.pokemon3, controller.pokemon4,controller.pokemon5, controller.pokemon6};

        // insert Pokemon until there are no more in the List
        for (int i = 0; i < pokemonList.size(); i++) {
            switch (i) {
                case 0 -> controller.pokemon1 = pokemonList.get(i);
                case 1 -> controller.pokemon2 = pokemonList.get(i);
                case 2 -> controller.pokemon3 = pokemonList.get(i);
                case 3 -> controller.pokemon4 = pokemonList.get(i);
                case 4 -> controller.pokemon5 = pokemonList.get(i);
                case 5 -> controller.pokemon6 = pokemonList.get(i);
            }
        }
        // fill the rest with no placeholder
        for (int i = pokemonList.size(); i < 6; i++) {
            switch (i) {
                case 0 -> controller.pokemon1 = data.tempPokemon;
                case 1 -> controller.pokemon2 = data.tempPokemon;
                case 2 -> controller.pokemon3 = data.tempPokemon;
                case 3 -> controller.pokemon4 = data.tempPokemon;
                case 4 -> controller.pokemon5 = data.tempPokemon;
                case 5 -> controller.pokemon6 = data.tempPokemon;
            }
        }
    }

    private void updatePokemonSprite() {
        // set Sprite and Nickname
        Utilities.setPokemonSpriteAndNickName(controller.pokemon1, controller.Pokemon1Name, controller.Pokemon1Image);
        Utilities.setPokemonSpriteAndNickName(controller.pokemon2, controller.Pokemon2Name, controller.Pokemon2Image);
        Utilities.setPokemonSpriteAndNickName(controller.pokemon3, controller.Pokemon3Name, controller.Pokemon3Image);
        Utilities.setPokemonSpriteAndNickName(controller.pokemon4, controller.Pokemon4Name, controller.Pokemon4Image);
        Utilities.setPokemonSpriteAndNickName(controller.pokemon5, controller.Pokemon5Name, controller.Pokemon5Image);
        Utilities.setPokemonSpriteAndNickName(controller.pokemon6, controller.Pokemon6Name, controller.Pokemon6Image);
    }

    public void selectFirstPokemon() {
        controller.previousSelectedPokemon.getStyleClass().clear();
        controller.previousSelectedPokemon.getStyleClass().add("passivePokemonBox");
        controller.previousSelectedPokemon = controller.Pokemon1Box;

        controller.Pokemon1Box.getStyleClass().clear();
        controller.Pokemon1Box.getStyleClass().add("activePokemonBox");
        controller.whichPokemonIsOnTheLine = 1;

        controller.selectPokemonOnTheMenu(controller.pokemon1);

        // set PokemonStats
        Pokemon pokemon = controller.pokemon1;
        int dexNr = 0;
        PokemonList poke = new PokemonList(pokemon.pokemonId,pokemon.nickname,pokemon.pokemonStatsId, dexNr);
        this.setPokemonStats(poke, controller);
    }

    // Pokemon
    private void loadPokemons() {
        // get List with all the PokeStats
        pokemonList = FXCollections.observableArrayList(new PokemonList(0,  "No Pokemon", 0, 0));
        pokemonList.addAll(dbAPI.getPokemonNicknameWithPokeStatsIdFromSpecificGame(data.getGameName()));

        // Create The Labels on the Side for loading in the data
        for (PokemonList pokeList : pokemonList) {
            createPokemonLabel(pokeList);
        }
    }

    public void createPokemonLabel(PokemonList pokemon) {
        HBox hbox = new HBox(5);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        if (pokemon.pokemonStatsId > 0) ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificPokemon(pokemon.pokemonStatsId));
        // Name
        Label btn = new Label(pokemon.nickname);
        // hidden pokemonId for identification
        Label identifier = new Label(Integer.toString(pokemon.pokemonId));
        identifier.setMaxWidth(0);
        identifier.setVisible(false);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        btn.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActivePokemonBox(hbox);
            // set Values
            this.setPokemonStats(pokemon, controller);
            // set Pokemon
            setPokemon(pokemon);
            // set Pokemon Options
            setPokemonOptions(pokemon);
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.activePokemonHBox = hbox;
            controller.activePokemonSprite = smallSprite;
            controller.activePokemonNickname = btn;
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, btn, identifier);
        // add To Controller
        controller.PokemonContainer.getChildren().add(hbox);
    }

    private void setCssOfActivePokemonBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        previousPokemonBox.getStyleClass().clear();
        previousPokemonBox.getStyleClass().add("createPokemonHBox");
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousPokemonBox = hbox;
    }

    private void setPokemonOptions(PokemonList pokemon) {
        if (pokemon.pokemonId > 0) {
            controller.AddPokemonButton.setDisable(true);
            controller.EditPokemonButton.setDisable(false);
            controller.DeletePokemonButton.setDisable(false);
        } else {
            controller.AddPokemonButton.setDisable(false);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);
        }
    }

    public void setPokemon(PokemonList poke) {
        String pokemonImagePath = "/Img/Pokeballs/000.png";
        Pokemon pokemon;
        // get Pokemon
        if (poke.pokemonId > 0) pokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
        else pokemon = data.tempPokemon;
        // get Image Path
        if (poke.pokemonId > 0) {
            int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(poke.pokemonStatsId);
            pokemonImagePath = dbAPI.getImagePathFromSpriteID(spriteId);
        }
        // set Stats
        switch (controller.whichPokemonIsOnTheLine) {
            case 1 -> {
                controller.pokemon1 = pokemon;
                controller.Pokemon1Name.setText(pokemon.nickname);
                controller.Pokemon1Image.setImage(new Image(pokemonImagePath));
            }
            case 2 ->{
                controller.pokemon2 = pokemon;
                controller.Pokemon2Name.setText(pokemon.nickname);
                controller.Pokemon2Image.setImage(new Image(pokemonImagePath));
            }
            case 3 -> {
                controller.pokemon3 = pokemon;
                controller.Pokemon3Name.setText(pokemon.nickname);
                controller.Pokemon3Image.setImage(new Image(pokemonImagePath));
            }
            case 4 -> {
                controller.pokemon4 = pokemon;
                controller.Pokemon4Name.setText(pokemon.nickname);
                controller.Pokemon4Image.setImage(new Image(pokemonImagePath));
            }
            case 5 -> {
                controller.pokemon5 = pokemon;
                controller.Pokemon5Name.setText(pokemon.nickname);
                controller.Pokemon5Image.setImage(new Image(pokemonImagePath));
            }
            case 6 -> {
                controller.pokemon6 = pokemon;
                controller.Pokemon6Name.setText(pokemon.nickname);
                controller.Pokemon6Image.setImage(new Image(pokemonImagePath));
            }
        }
    }

    public void setPokemonOptions(Pokemon pokemon) {
        if (pokemon.pokemonId > 0) {
            controller.AddPokemonButton.setDisable(true);
            controller.EditPokemonButton.setDisable(false);
            controller.DeletePokemonButton.setDisable(false);
        } else {
            controller.AddPokemonButton.setDisable(false);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);
        }
    }

    // Select First Trainer
    public void selectFirstTrainer() {
        try {
            // select first Trainer
            controller.TrainerContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            previousTrainerBox = (HBox) controller.TrainerContainer.getChildren().get(0);
            controller.trainerId = trainerList.get(0).trainerId;
            // nameOfPokemon
            HBox hbox = (HBox) controller.TrainerContainer.getChildren().get(0);
            // Label
            controller.trainerSpriteLabel = (Label) hbox.getChildren().get(0);
            controller.fightNumberLabel = (Label) hbox.getChildren().get(1);
            controller.trainerNameLabel = (Label) hbox.getChildren().get(2);
            // Sprite of Trainer
            int indexOfSprite = Utilities.findTrainerSprite(trainerList.get(0), controller.TrainerSpriteContainer);
            previousTrainerSpriteBox = (HBox) controller.TrainerSpriteContainer.getChildren().get(indexOfSprite);

            // Set first Pokemon Css that is in the first Trainer
            ArrayList<Pokemon> trainerPokemonList = dbAPI.getAllPokemonFromTrainer(trainerList.get(0).trainerId);
            controller.previousSelectedPokemon = controller.Pokemon1Box;
            try {
                controller.pokemon1 = trainerPokemonList.get(0);
            } catch (Exception e) {
                controller.pokemon1 = data.tempPokemon;
            }
            selectFirstPokemon();

            int index = Utilities.findPokemonInPokemonList(controller.pokemon1, pokemonList);
            previousPokemonBox = (HBox) controller.PokemonContainer.getChildren().get(index);

            // Set Options
            setTrainerOptions(trainerList.get(0));

            // set the first Trainer in the list
            setTrainerStats(trainerList.get(0));
        } catch (Exception e) {
            System.out.println("There is no Trainer to select. Error: " + e);
        }
    }
}
