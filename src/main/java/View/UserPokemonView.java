package View;

import Classes.*;
import Controller.UserPokemonController;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserPokemonView {
    private static HBox previousPokemonBox;
    private static UserPokemonController controller;
    private static API.Database dbAPI;
    private static Data.dataSingleton data;
    public final Pokemon tempPokemon = new Pokemon(0,0,0,0,"Bashful",0,0,"No Pokemon", "",0,0,"",0,false,0,0,0, 0, 0,0,0,0,0,0,0,0);
    private static ObservableList<PokeStatsList> species;
    private static ObservableList<Item> itemList;
    private static ObservableList<Nature> natures;
    private static ObservableList<Ability> abilities;
    private static ObservableList<Route> metLocations;
    private static ObservableList<Attack> attackList;

    public void createPokemonPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("UserPokemon");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            previousPokemonBox = null;
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("Add Pokemon To the User");
            stage.setScene(scene);
            stage.show();

            controller = UserPokemonController.getInstance();
            dbAPI = new API.Database();
            data = Data.dataSingleton.getInstance();

            loadUserPokemons();
            loadDynamicContent();
            selectFirstPokemon();
        }
    }

    public void selectFirstPokemon() {
        // set the first thing
        try {
            controller.pokemonStatsId = controller.pokeStatsList.get(0).pokemonStatsId;
            controller.pokemonId = controller.pokeStatsList.get(0).pokemonId;
            controller.PokemonContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            controller.activePokemon = (HBox) controller.PokemonContainer.getChildren().get(0);
            previousPokemonBox = (HBox) controller.PokemonContainer.getChildren().get(0);

            setPokemonStats(controller.pokeStatsList.get(0));
            setPokemonOptions(controller.pokeStatsList.get(0));
        } catch (Exception e) {
            System.out.println("You don't have any Pokemons. Error: " + e);
//            e.printStackTrace();
        }
    }

    // Dynamic Content
    private void loadDynamicContent() {
        try {
            // get List with all the PokeStats
            controller.pokeStatsList = FXCollections.observableArrayList(new PokemonList(0,  "No Pokemon", 0, 0));
            controller.pokeStatsList.addAll(dbAPI.getPokemonNicknameWithPokeStatsIdFromSpecificGame(data.getGameName()));

            removePokemonThatTheUserAlreadyHas();

            // Create The Labels on the Side for loading in the data
            for (PokemonList pokeList : controller.pokeStatsList) {
                createPokemonLabel(pokeList);
            }

            loadGlobalContent(); // Nature / Item / Ability ...
        } catch (Exception e) {
            System.out.println("There are no Pokemons in your Game Error: " + e);
//            e.printStackTrace();
        }
    }

    private void removePokemonThatTheUserAlreadyHas() {
        ArrayList<PokemonList> pokemonToRemove = new ArrayList<PokemonList>();

        for (PokemonList pokeList : controller.pokeStatsList) {
            for (PokemonList userPokeList : controller.userPokemonList) {
                if (pokeList.pokemonId == userPokeList.pokemonId) {
                    pokemonToRemove.add(pokeList);
                }
            }
        }

        for (PokemonList pk : pokemonToRemove) {
            controller.pokeStatsList.remove(pk);
        }
    }

    private void loadGlobalContent() {
        String gameName = data.getGameName();
        // Species
        species = dbAPI.getPokeStatsListFromSpecificGame(data.getGameName());
        controller.SpeciesName.setItems(species);
        // Gender
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "None");
        controller.Gender.setItems(genders);
        controller.Gender.getSelectionModel().selectFirst();
        // Nature
        natures = dbAPI.getAllNature();
        controller.Nature.setItems(natures);
        // Held Item
        itemList = FXCollections.observableArrayList();
        itemList.add(new Item(-1, -1, null, null, 0, 0, 0));
        itemList.addAll(dbAPI.getAllItemsFromGame(gameName));
        controller.HeldItem.setItems(itemList);
        // Ability
        abilities = dbAPI.getAllAbilitiesFromGame(gameName);
        controller.Ability.setItems(abilities);
        // Met Location
        metLocations = dbAPI.getALLMetLocationsFromGame(gameName);
        controller.MetLocation.setItems(metLocations);
        // Pokeballs
        controller.pokeballSprites = dbAPI.getAllPokeballs();
        controller.PokeBall.setItems(controller.pokeballSprites);
        // Attacks
        attackList = FXCollections.observableArrayList();
        attackList.add(new Attack(-1, null, "", null, 0,0,null,null,0,null,null,false,false,false,false,false,false));
        attackList.addAll(dbAPI.getAllAttacksFromGame(gameName));
        controller.Attack1Move.setItems(attackList);
        controller.Attack2Move.setItems(attackList);
        controller.Attack3Move.setItems(attackList);
        controller.Attack4Move.setItems(attackList);
    }

    // Create Pokemon Label
    public void createPokemonLabel(PokemonList pokemon) {
        HBox hbox = new HBox(5);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        if (pokemon.pokemonStatsId > 0) ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificPokemon(pokemon.pokemonStatsId));
        // dexNr and Name
        Label dexNr = new Label();
        if (pokemon.pokemonStatsId > 0) dexNr = new Label(String.format("%03d", pokemon.dexNr));
        Label btn = new Label(pokemon.nickname);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        dexNr.getStyleClass().add("createPokemonLabel");
        btn.getStyleClass().add("createPokemonLabel");

        // add EventListener
        Label finalDexNr = dexNr;
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            setPokemonStats(pokemon);
            // set Pokemon Options
            setPokemonOptions(pokemon);
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.pokemonStatsId = pokemon.pokemonStatsId;
            // set activePokemon
            controller.activePokemon = hbox;
            controller.activePokemonSprite = smallSprite;
            controller.activePokemonDexNr = finalDexNr;
            controller.activePokemonNickname = btn;
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, dexNr, btn);
        // add To Controller
        controller.PokemonContainer.getChildren().add(hbox);
    }

    private void setPokemonOptions(PokemonList pokemon) {
        if (pokemon.pokemonId > 0) {
            controller.CreatePokemonButton.setDisable(true);
            controller.AddToUserButton.setDisable(false);
            controller.EditPokemonButton.setDisable(false);
            controller.DeletePokemonButton.setDisable(false);
        } else {
            controller.CreatePokemonButton.setDisable(false);
            controller.AddToUserButton.setDisable(true);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);

        }
        controller.RemoveFromUserButton.setDisable(true);

    }

    // load User Pokemons
    private void loadUserPokemons() {
        controller.userPokemonList = dbAPI.getAllPokemonFromUser(data.getGameName());

        for (PokemonList pokeList : controller.userPokemonList) {
            createPokemonLabelForUser(pokeList);
        }
    }

    public void createPokemonLabelForUser(PokemonList pokemon) {
        HBox hbox = new HBox(5);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificPokemon(pokemon.pokemonStatsId));
        // dexNr and Name
        Label dexNr = new Label(String.format("%03d", pokemon.dexNr));
        Label btn = new Label(pokemon.nickname);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        dexNr.getStyleClass().add("createPokemonLabel");
        btn.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            setPokemonStats(pokemon);
            // set Pokemon Options
            setTrainerPokemonOptions();
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.pokemonStatsId = pokemon.pokemonStatsId;
            // set activePokemon
            controller.activePokemon = hbox;
            controller.activePokemonSprite = smallSprite;
            controller.activePokemonDexNr = dexNr;
            controller.activePokemonNickname = btn;
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, dexNr, btn);
        // add To Controller
        controller.UserPokemonContainer.getChildren().add(hbox);
    }

    private void setTrainerPokemonOptions() {
        controller.CreatePokemonButton.setDisable(true);
        controller.AddToUserButton.setDisable(true);
        controller.EditPokemonButton.setDisable(false);
        controller.RemoveFromUserButton.setDisable(false);
        controller.DeletePokemonButton.setDisable(true);
    }


    // Active Pokemon
    private void setCssOfActiveBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        previousPokemonBox.getStyleClass().clear();
        previousPokemonBox.getStyleClass().add("createPokemonHBox");
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousPokemonBox = hbox;
    }

    // set Pokemon
    private void setPokemonStats(PokemonList pokeList) {
        Pokemon pokemon = tempPokemon;
        PokeStats pokeStats = new PokeStats(0, "No Pokemon", 0, "Slow", 0, 0, 0, 0, 0, 0, 0, 0);

        if (pokeList.pokemonId > 0) pokemon = dbAPI.getPokemonFromPokemonId(pokeList.pokemonId);
        if (pokeList.pokemonStatsId > 0) pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokeList.pokemonStatsId);

        setMainStats(pokemon, pokeStats);
        setMetStats(pokemon);
        setStatsStats(pokemon, pokeStats);
        setMoveStats(pokemon);
    }

    private void setMainStats(Pokemon pokemon, PokeStats pokeStats) {
        setSpecies(pokeStats.nameOfPokemon);
        controller.Nickname.setText(pokemon.nickname);
        controller.Gender.setValue(pokemon.gender);
        controller.Level.setText(Integer.toString(pokemon.level));
        selectNature(pokemon.natureName);
        selectHeldItem(pokemon.itemId);
        selectAbility(pokemon.abilityId);
        controller.Friendship.setText(Integer.toString(pokemon.friendship));
    }

    private void setMetStats(Pokemon pokemon) {
        selectMetLocation(pokemon.metLocation);
        selectPokeball(pokemon.pokeball);
    }

    private void setStatsStats(Pokemon pokemon, PokeStats pokeStats) {
        setBaseStats(pokeStats);
        setIvStats(pokemon);
        setEvStats(pokemon);
        controller.calculateBaseIvEv();
        controller.calculateAndSetStats();
    }

    private void setBaseStats(PokeStats pokeStats) {
        controller.BaseHp.setText(Integer.toString(pokeStats.baseHp));
        controller.BaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        controller.BaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        controller.BaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        controller.BaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        controller.BaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    private void setIvStats(Pokemon pokemon) {
        controller.IvHp.setText(Integer.toString(pokemon.ivHp));
        controller.IvAttack.setText(Integer.toString(pokemon.ivAttack));
        controller.IvDefense.setText(Integer.toString(pokemon.ivDefense));
        controller.IvSpecialAttack.setText(Integer.toString(pokemon.ivSpecialAttack));
        controller.IvSpecialDefense.setText(Integer.toString(pokemon.ivSpecialDefense));
        controller.IvSpeed.setText(Integer.toString(pokemon.ivSpeed));
    }

    private void setEvStats(Pokemon pokemon) {
        controller.EvHp.setText(Integer.toString(pokemon.evHp));
        controller.EvAttack.setText(Integer.toString(pokemon.evAttack));
        controller.EvDefense.setText(Integer.toString(pokemon.evDefense));
        controller.EvSpecialAttack.setText(Integer.toString(pokemon.evSpecialAttack));
        controller.EvSpecialDefense.setText(Integer.toString(pokemon.evSpecialDefense));
        controller.EvSpeed.setText(Integer.toString(pokemon.evSpeed));
    }

    private void setMoveStats(Pokemon pokemon) {
        ObservableList<PokemonAttack> pokemonAttackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);

        // set Default No Attack
        ComboBox[] pokemonAttacks = {controller.Attack1Move, controller.Attack2Move, controller.Attack3Move, controller.Attack4Move};
        for (ComboBox attack : pokemonAttacks) { attack.getSelectionModel().selectFirst(); }

        // set Attack
        for (PokemonAttack pokemonAttack : pokemonAttackList) {
            switch(pokemonAttack.attackPosition) {
                case 1 -> controller.Attack1Move.getSelectionModel().select(getMove(pokemonAttack.attackId));
                case 2 -> controller.Attack2Move.getSelectionModel().select(getMove(pokemonAttack.attackId));
                case 3 -> controller.Attack3Move.getSelectionModel().select(getMove(pokemonAttack.attackId));
                case 4 -> controller.Attack4Move.getSelectionModel().select(getMove(pokemonAttack.attackId));
            }
        }

    }


    // -------------------- Select --------------------
    private void setSpecies(String nameOfPokemon) {
        int index = 0;
        for (PokeStatsList item : species) {
            if (item.nameOfPokemon.equals(nameOfPokemon)) {
                break;
            }
            index++;
        }
        controller.SpeciesName.getSelectionModel().select(index);
    }

    private void selectNature(String nature) {
        int index = 0;
        for (Nature item : natures) {
            if (item.natureName.equals(nature)) {
                break;
            }
            index++;
        }
        controller.Nature.getSelectionModel().select(index);
    }

    private void selectHeldItem(int item) {
        if (item > 0) {
            int index = 0;
            for (Item item2 : itemList) {
                if (item2.itemId == item) {
                    break;
                }
                index++;
            }
            controller.HeldItem.getSelectionModel().select(index);
        } else {
            controller.HeldItem.getSelectionModel().selectFirst();
        }

    }

    private void selectAbility(int ability) {
        int index = 0;
        for (Ability item : abilities) {
            if (item.abilityId == ability) {
                break;
            }
            index++;
        }
        controller.Ability.getSelectionModel().select(index);
    }

    private void selectMetLocation(int metLocation) {
        int index = 0;
        for (Route item : metLocations) {
            if (item.routeId == metLocation) {
                break;
            }
            index++;
        }
        controller.MetLocation.getSelectionModel().select(index);
    }

    private void selectPokeball(int pokeball) {
        int index = 0;
        for (Sprite item : controller.pokeballSprites) {
            if (item.spriteId == pokeball) {
                break;
            }
            index++;
        }
        controller.PokeBall.getSelectionModel().select(index);
    }

    private int getMove(int moveId) {
        int index = 0;
        for (Attack item : attackList) {
            if (item.attackId == moveId) {
                return index;
            }
            index++;
        }
        return 0;
    }


}
