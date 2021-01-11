package View;

import API.Database;
import Classes.*;
import Controller.PokemonController;
import Data.dataSingleton;
import Utilities.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PokemonView {
    private static HBox previousPokemonBox;
    private static PokemonController controller;
    private static API.Database dbAPI;
    private static Data.dataSingleton data;
    private static ObservableList<Sprite> pokeballSprites;
    private static ObservableList<Item> itemList;
    private static ObservableList<PokeStatsList> species;
    private static ObservableList<Nature> natures;
    private static ObservableList<Ability> abilities;
    private static ObservableList<Route> metLocations;
    private static ObservableList<Attack> attackList;

    public void createPokemonPage(Stage stage) {
        // load fxml file
        Parent fxmlFile = Utilities.loadFxmlFile("Pokemon");

        // Assert that we have a frontend
        if (fxmlFile != null) {
            previousPokemonBox = null;
            Scene scene = new Scene(fxmlFile);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("/CSS/style.css")));
            stage.setTitle("Edit Pokemon");
            stage.setScene(scene);
            stage.show();

            loadDynamicContent();
            selectFirst();
            setTheFirstThing();
        }
    }

    private void loadDynamicContent() {
        try {
            controller = PokemonController.getInstance();
            dbAPI = new Database();
            data = dataSingleton.getInstance();

            setBasicValues(); // Iv's / Ev's ...

            // get List with all the PokeStats
            controller.pokeStatsList = dbAPI.getPokeStatsFromSpecificGame(data.getGameName());
            // Create The Labels on the Side for loading in the data
            for (PokeStats pokeStats : controller.pokeStatsList) {
                createPokemonLabel(pokeStats);
            }

            // get List with all the Pokemons
            controller.pokemonList = dbAPI.getPokemonNicknameWithPokeStatsIdFromSpecificGame(data.getGameName());
            // Create The Labels on the Side for loading in the data
            for (PokemonList pokeList : controller.pokemonList) {
                createPokemonLabel(pokeList);
            }

            loadGlobalContent(); // Nature / Item / Ability ...
        } catch (Exception e) {
            System.out.println("There are no Pokemons in your Game Error: " + e);
//            e.printStackTrace();
        }
    }

    private void selectFirst() {
        controller.Nature.getSelectionModel().selectFirst();
        controller.HeldItem.getSelectionModel().selectFirst();
        controller.Ability.getSelectionModel().selectFirst();
        controller.MetLocation.getSelectionModel().selectFirst();
        selectPokeball();
        controller.Attack1Move.getSelectionModel().selectFirst();
        controller.Attack2Move.getSelectionModel().selectFirst();
        controller.Attack3Move.getSelectionModel().selectFirst();
        controller.Attack4Move.getSelectionModel().selectFirst();
    }

    private void selectPokeball() {
        int index = 0;

        for (Sprite sprite : pokeballSprites) {
            if (sprite.spriteName.equals("Poke Ball")) {
                break;
            }
            index++;
        }

        controller.PokeBall.getSelectionModel().select(index);
    }

    private void setTheFirstThing() {
        try {
            setPokemonStats(controller.pokeStatsList.get(0));
            controller.PokeStatsContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            previousPokemonBox = (HBox) controller.PokeStatsContainer.getChildren().get(0);
            // lock species
            controller.SpeciesName.setDisable(true);
            // options
            controller.AddPokemonButton.setDisable(false);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void selectFirstPokemon() {
        // set the first thing
        try {
            controller.pokemonId = controller.pokemonList.get(0).pokemonId;
            controller.PokemonContainer.getChildren().get(0).getStyleClass().add("createPokemonHBoxActive");
            controller.activePokemonHBox = (HBox) controller.PokemonContainer.getChildren().get(0);
            previousPokemonBox = (HBox) controller.PokemonContainer.getChildren().get(0);

            setPokemonStats(controller.pokemonList.get(0));
        } catch (Exception e) {
            System.out.println("There aren't any Pokemons to select. Error: " + e);
//            e.printStackTrace();
        }
    }

    // PokeStats
    private void setBasicValues() {
        controller.Level.setText("5");
        controller.Friendship.setText("255");
        // Gender
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "None");
        controller.Gender.setItems(genders);
        controller.Gender.getSelectionModel().selectFirst();
        // IV
        controller.IvHp.setText("0");
        controller.IvAttack.setText("0");
        controller.IvDefense.setText("0");
        controller.IvSpecialAttack.setText("0");
        controller.IvSpecialDefense.setText("0");
        controller.IvSpeed.setText("0");
        // EV
        controller.EvHp.setText("0");
        controller.EvAttack.setText("0");
        controller.EvDefense.setText("0");
        controller.EvSpecialAttack.setText("0");
        controller.EvSpecialDefense.setText("0");
        controller.EvSpeed.setText("0");
        // PP Ups
        controller.Attack1PPUps.setValue("0");
        controller.Attack2PPUps.setValue("0");
        controller.Attack3PPUps.setValue("0");
        controller.Attack4PPUps.setValue("0");
    }

    private void createPokemonLabel(PokeStats pokeStats) {
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER_LEFT);
        // Label
        Label smallSprite = new Label();
        // Set small Pokemon Sprite
        Utilities ut = new Utilities();
        ut.setSmallSpriteNextToLabel(smallSprite, dbAPI.getSpriteIdFromSpecificPokemon(pokeStats.pokemonStatsId));
        // dexNr and Name
        Label dexNr = new Label(String.format("%03d", pokeStats.dexNr));
        Label btn = new Label(pokeStats.nameOfPokemon);
        // CSS
        hbox.getStyleClass().add("createPokemonHBox");
        smallSprite.getStyleClass().add("createSmallSpriteNextToLabel");
        dexNr.getStyleClass().add("createPokemonLabel");
        btn.getStyleClass().add("createPokemonLabel");

        // add EventListener
        hbox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            // set CSS
            setCssOfActiveBox(hbox);
            // set Values
            setPokemonStats(pokeStats);
            // lock species
            controller.SpeciesName.setDisable(true);
            // options
            controller.AddPokemonButton.setDisable(false);
            controller.EditPokemonButton.setDisable(true);
            controller.DeletePokemonButton.setDisable(true);
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, dexNr, btn);
        // add To Controller
        controller.PokeStatsContainer.getChildren().add(hbox);
    }

    private void setPokemonStats(PokeStats pokeStats) {
        setMainPokemonStats(pokeStats);
        setStatsPokemonStats(pokeStats);
    }

    private void setMainPokemonStats(PokeStats pokeStats) {
        setSpecies(pokeStats.nameOfPokemon);
        controller.Nickname.setText(pokeStats.nameOfPokemon);
    }

    private void setStatsPokemonStats(PokeStats pokeStats) {
        controller.BaseHp.setText(String.valueOf(pokeStats.baseHp));
        controller.BaseAttack.setText(String.valueOf(pokeStats.baseAttack));
        controller.BaseDefense.setText(String.valueOf(pokeStats.baseDefense));
        controller.BaseSpecialAttack.setText(String.valueOf(pokeStats.baseSpecialAttack));
        controller.BaseSpecialDefense.setText(String.valueOf(pokeStats.baseSpecialDefense));
        controller.BaseSpeed.setText(String.valueOf(pokeStats.baseSpeed));
        controller.calculateBaseIvEv();
        setStats();
    }

    private void setStats() {
        int hp = controller.calculateHpStat();
        int attack = controller.calculateAttackStat(controller.Nature.getValue());
        int defense = controller.calculateDefenseStat(controller.Nature.getValue());
        int specialAttack = controller.calculateSpecialAttackStat(controller.Nature.getValue());
        int specialDefense = controller.calculateSpecialDefenseStat(controller.Nature.getValue());
        int speed = controller.calculateSpeedStat(controller.Nature.getValue());

        controller.StatsHp.setText(Integer.toString(hp));
        controller.StatsAttack.setText(Integer.toString(attack));
        controller.StatsDefense.setText(Integer.toString(defense));
        controller.StatsSpecialAttack.setText(Integer.toString(specialAttack));
        controller.StatsSpecialDefense.setText(Integer.toString(specialDefense));
        controller.StatsSpeed.setText(Integer.toString(speed));
    }

    // Pokemon
    public void createPokemonLabel(PokemonList pokemon) {
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
            // set pokemonStatsId
            controller.pokemonId = pokemon.pokemonId;
            controller.activePokemonHBox = hbox;
            controller.activePokemonSprite = smallSprite;
            controller.activePokemonDexNr = dexNr;
            controller.activePokemonNickname = btn;
            // activate species
            controller.SpeciesName.setDisable(false);
            // options
            controller.AddPokemonButton.setDisable(true);
            controller.EditPokemonButton.setDisable(false);
            controller.DeletePokemonButton.setDisable(false);
        });

        // add to HBox
        hbox.getChildren().addAll(smallSprite, dexNr, btn);
        // add To Controller
        controller.PokemonContainer.getChildren().add(hbox);
    }

    private void setCssOfActiveBox(HBox hbox) {
        // change previousPokemonBox background-color back to original
        previousPokemonBox.getStyleClass().clear();
        previousPokemonBox.getStyleClass().add("createPokemonHBox");
        // change this PokemonBox to active background-color
        hbox.getStyleClass().clear();
        hbox.getStyleClass().add("createPokemonHBoxActive");
        previousPokemonBox = hbox;
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
        pokeballSprites = dbAPI.getAllPokeballs();
        controller.PokeBall.setItems(pokeballSprites);
        // Attacks
        attackList = FXCollections.observableArrayList();
        attackList.add(new Attack(-1, null, "", null, 0,0,null,null,0,null,null,false,false,false,false,false,false));
        attackList.addAll(dbAPI.getAllAttacksFromGame(gameName));
        controller.Attack1Move.setItems(attackList);
        controller.Attack2Move.setItems(attackList);
        controller.Attack3Move.setItems(attackList);
        controller.Attack4Move.setItems(attackList);
    }

    private void setPokemonStats(PokemonList pokeList) {
        Pokemon pokemon = dbAPI.getPokemonFromPokemonId(pokeList.pokemonId);
        PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokeList.pokemonStatsId);

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

    public void setBaseStats(PokeStats pokeStats) {
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
        for (Sprite item : pokeballSprites) {
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
