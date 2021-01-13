package Controller;

import API.Database;
import Classes.*;
import Classes.Abstract.AbstractPokemonController;
import Data.dataSingleton;
import Utilities.Utilities;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class UserPokemonController extends AbstractPokemonController {
    private static UserPokemonController controller = null;
    public static UserPokemonController getInstance() {
        return controller;
    }
    public void initialize() {
        controller = this;
    }

    public VBox PokemonContainer;
    public VBox UserPokemonContainer;
    // Options
    public Button CreatePokemonButton;
    public Button AddToUserButton;
    public Button EditPokemonButton;
    public Button RemoveFromUserButton;
    public Button DeletePokemonButton;
    // own variables
    public int pokemonId;
    public int pokemonStatsId;
    public HBox activePokemon;
    public Label activePokemonSprite;
    public Label activePokemonDexNr;
    public Label activePokemonNickname;
    public ObservableList<PokemonList> pokeStatsList;
    public ArrayList<PokemonList> userPokemonList;
    // -------------------------- Menu --------------------------
    public void backToMenu() {
        Utilities.backToMenu();
    }

    // -------------------------- Options --------------------------
    public void createPokemonAndAddToUser() {
        int result = 0;
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        API.Database dbAPI = new API.Database();

        try {
            // get Values
            String gameName = data.getGameName();
            int pokemonStatsId = SpeciesName.getValue().pokemonStatsId;
            String nickName = Nickname.getText();
            String gender = Gender.getValue();
            int level = Integer.parseInt(Level.getText());
            String natureName = Nature.getValue().natureName;
            int itemId = HeldItem.getValue().itemId;
            int abilityId = Ability.getValue().abilityId;
            int friendship = Integer.parseInt(Friendship.getText());
            int pokeball = PokeBall.getValue().spriteId;
            int metLocation = MetLocation.getValue().routeId;
            boolean isShiny = false;
            int ivHp = Integer.parseInt(IvHp.getText());
            int ivAttack = Integer.parseInt(IvAttack.getText());
            int ivDefense = Integer.parseInt(IvDefense.getText());
            int ivSpecialAttack = Integer.parseInt(IvSpecialAttack.getText());
            int ivSpecialDefense = Integer.parseInt(IvSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(IvSpeed.getText());
            int evHp = Integer.parseInt(EvHp.getText());
            int evAttack = Integer.parseInt(EvAttack.getText());
            int evDefense = Integer.parseInt(EvDefense.getText());
            int evSpecialAttack = Integer.parseInt(EvSpecialAttack.getText());
            int evSpecialDefense = Integer.parseInt(EvSpecialDefense.getText());
            int evSpeed = Integer.parseInt(EvSpeed.getText());
            int attack1 = Attack1Move.getValue().attackId;
            int attack2 = Attack2Move.getValue().attackId;
            int attack3 = Attack3Move.getValue().attackId;
            int attack4 = Attack4Move.getValue().attackId;

            result = dbAPI.addPokemon(gameName, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result > 0) {
            int trainerId = dbAPI.getUserIdFromSpecificGame(data.getGameName());
            dbAPI.addPokemonToUser(data.getGameName(), trainerId, result, userPokemonList.size()+1);

            addPokemonToUserList(result, SpeciesName.getValue().pokemonStatsId);
        }
    }

    public void addPokemonToTheUser() {
        if (pokemonId > 0) {
            Database dbAPI = new Database();
            dataSingleton data = dataSingleton.getInstance();

            boolean result = false;
            int trainerId = dbAPI.getUserIdFromSpecificGame(data.getGameName());

            result = dbAPI.addPokemonToUser(data.getGameName(), trainerId, pokemonId, userPokemonList.size()+1);

            if (result) {
                addPokemonToUserList(pokemonId, pokemonStatsId);
                removePokemonFromPokeList();
                selectNewFirstPokemon();
            }
        }
    }

    public void editPokemonToTheUser() {
        boolean result = false;

        try {
            API.Database dbAPI = new API.Database();

            // get Values
            int pokemonStatsId = SpeciesName.getValue().pokemonStatsId;
            String nickName = Nickname.getText();
            String gender = Gender.getValue();
            int level = Integer.parseInt(Level.getText());
            String natureName = Nature.getValue().natureName;
            int itemId = HeldItem.getValue().itemId;
            int abilityId = Ability.getValue().abilityId;
            int friendship = Integer.parseInt(Friendship.getText());
            int pokeball = PokeBall.getValue().spriteId;
            int metLocation = MetLocation.getValue().routeId;
            boolean isShiny = false;
            int ivHp = Integer.parseInt(IvHp.getText());
            int ivAttack = Integer.parseInt(IvAttack.getText());
            int ivDefense = Integer.parseInt(IvDefense.getText());
            int ivSpecialAttack = Integer.parseInt(IvSpecialAttack.getText());
            int ivSpecialDefense = Integer.parseInt(IvSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(IvSpeed.getText());
            int evHp = Integer.parseInt(EvHp.getText());
            int evAttack = Integer.parseInt(EvAttack.getText());
            int evDefense = Integer.parseInt(EvDefense.getText());
            int evSpecialAttack = Integer.parseInt(EvSpecialAttack.getText());
            int evSpecialDefense = Integer.parseInt(EvSpecialDefense.getText());
            int evSpeed = Integer.parseInt(EvSpeed.getText());
            int attack1 = Attack1Move.getValue().attackId;
            int attack2 = Attack2Move.getValue().attackId;
            int attack3 = Attack3Move.getValue().attackId;
            int attack4 = Attack4Move.getValue().attackId;

            result = dbAPI.updatePokemon(pokemonId, pokemonStatsId, itemId, abilityId, natureName, pokeball, metLocation, nickName, level,
                    gender, friendship, isShiny, ivHp, ivAttack, ivDefense, ivSpecialAttack, ivSpecialDefense, ivSpeed,
                    evHp, evAttack, evDefense, evSpecialAttack, evSpecialDefense, evSpeed, attack1, attack2, attack3, attack4);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result) {
            updateLabel();
        }
    }

    public void removePokemonFromUser() {
        if (pokeStatsList.size() > 0) {
            boolean result = false;

            try {
                API.Database dbAPI = new API.Database();
                Data.dataSingleton data = Data.dataSingleton.getInstance();
                int trainerId = dbAPI.getUserIdFromSpecificGame(data.getGameName());

                result = dbAPI.removePokemonFromUser(trainerId, pokemonId);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (result) {
                removePokemonFromLists();
                addPokemonToPokemonList();
                selectNewFirstPokemon();
            }
        }
    }

    public void deletePokemon() {
        boolean result = false;

        try {
            API.Database dbAPI = new API.Database();
            result = dbAPI.deletePokemon(pokemonId);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result) {
            removePokemonFromPokeList();
            selectNewFirstPokemon();
        }
    }

    // Add Pokemon To User
    private void addPokemonToUserList(int pokemonId, int pokemonStatsId) {
        // Add To List
        API.Database dbAPI = new API.Database();
        PokeStats pokeStats =  dbAPI.getPokeStatsFromPokemonStatsId(pokemonStatsId);

        int dexNr = pokeStats.dexNr;

        PokemonList poke = new PokemonList(pokemonId, Nickname.getText(), pokemonStatsId, dexNr);
        userPokemonList.add(poke);
        // Add to Box
        View.UserPokemonView uv = new View.UserPokemonView();
        uv.createPokemonLabelForUser(poke);
    }

    private void removePokemonFromPokeList() {
        int index = 0;
        for (PokemonList pokemonList : pokeStatsList) {
            if (pokemonList.pokemonId == pokemonId) {
                break;
            }
            index++;
        }
        pokeStatsList.remove(index);
        PokemonContainer.getChildren().remove(index);
    }

    private void selectNewFirstPokemon() {
        View.UserPokemonView pv = new View.UserPokemonView();
        pv.selectFirstPokemon();
    }

    // Edit Pokemon
    private void updateLabel() {
        // Sprite
        API.Database dbAPI = new API.Database();
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(SpeciesName.getValue().pokemonStatsId);
        String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        activePokemonSprite.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
        // DexNr
        activePokemonDexNr.setText(String.format("%03d", SpeciesName.getValue().dexNr));
        // Nickname
        activePokemonNickname.setText(Nickname.getText());
    }

    // Remove Pokemon
    private void removePokemonFromLists() {
        deletePokeStatsFromPokeStatsList();
        UserPokemonContainer.getChildren().remove(activePokemon);
    }

    private void deletePokeStatsFromPokeStatsList() {
        int index = 0;
        for (PokemonList pokeStats : userPokemonList) {
            if (pokeStats.pokemonId == pokemonId) {
                break;
            }
            index++;
        }
        userPokemonList.remove(index);
    }

    // Add Pokemon to PokemonList
    private void addPokemonToPokemonList() {
        PokemonList poke = new PokemonList(pokemonId, Nickname.getText(), pokemonStatsId, Integer.parseInt(activePokemonDexNr.getText()));
        pokeStatsList.add(poke);
        // Add to Box
        View.UserPokemonView upv = new View.UserPokemonView();
        upv.createPokemonLabel(poke);
    }
}
