package Controller;

import Classes.*;
import Classes.Abstract.AbstractPokemonController;
import Utilities.Utilities;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PokemonController extends AbstractPokemonController {
    // static method to create instance of Singleton class
    private static PokemonController controller = null;
    public static PokemonController getInstance() {
        return controller;
    }
    @FXML
    public void initialize() {
        controller = this;
    }

    // PokeStats
    public VBox PokeStatsContainer;
    public VBox PokemonContainer;
    // Options
    public Button AddPokemonButton;
    public Button EditPokemonButton;
    public Button DeletePokemonButton;
    // own variables
    public ObservableList<PokemonList> pokemonList;
    public int pokemonId;
    public HBox activePokemonHBox;
    public Label activePokemonSprite;
    public Label activePokemonDexNr;
    public Label activePokemonNickname;

    public void searchForPokemon() {
    }

    // -------------------------- Menu --------------------------
    public void backToMenu() {
        Utilities.backToMenu();
    }

    // -------------------------- Options --------------------------
    public void addPokemon() {
        int result = 0;

        try {
            Data.dataSingleton data = Data.dataSingleton.getInstance();
            API.Database dbAPI = new API.Database();

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
            addPokemonToList(result);
        }
    }

    public void editPokemon() {
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

    public void deletePokemon() {
        boolean result = false;

        try {
            API.Database dbAPI = new API.Database();
            result = dbAPI.deletePokemon(pokemonId);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (result) {
            removePokemonFromLists();
            selectNewFirstPokemon();
        }
    }

    // Add Pokemon
    private void addPokemonToList(int pokemonId) {
        String nickname = Nickname.getText().substring(0, 1).toUpperCase() + Nickname.getText().substring(1);
        PokemonList newPokeStat = new PokemonList(pokemonId, nickname, SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr);

        View.PokemonView pv = new View.PokemonView();
        pv.createPokemonLabel(newPokeStat); // adds To ViewList
        pokemonList.add(newPokeStat);       // adds To PokemonList
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

    // Delete Pokemon
    private void removePokemonFromLists() {
        deletePokemonFromPokemonList();
        PokemonContainer.getChildren().remove(activePokemonHBox);
    }

    private void selectNewFirstPokemon() {
        View.PokemonView pv = new View.PokemonView();
        pv.selectFirstPokemon();
    }

    private void deletePokemonFromPokemonList() {
        int index = 0;
        for (PokemonList pokemon : pokemonList) {
            if (pokemon.pokemonId == pokemonId) {
                break;
            }
            index++;
        }
        pokemonList.remove(index);
    }

}
