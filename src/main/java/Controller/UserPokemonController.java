package Controller;

import API.Database;
import Classes.*;
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

public class UserPokemonController {
    // static method to create instance of Singleton class
    private static UserPokemonController controller = null;
    public static UserPokemonController getInstance() {
        return controller;
    }
    @FXML
    public void initialize() {
        controller = this;
    }

    public VBox PokemonContainer;
    public VBox UserPokemonContainer;
    // Main
    public ComboBox<PokeStatsList> SpeciesName;
    public TextField Nickname;
    public TextField Level;
    public ComboBox<String> Gender;
    public ComboBox<Classes.Nature> Nature;
    public ImageView HeldItemImage;
    public ComboBox<Classes.Item> HeldItem;
    public ComboBox<Classes.Ability> Ability;
    public TextField Friendship;
    // Met
    public ComboBox<Classes.Route> MetLocation;
    public ImageView PokeballImage;
    public ComboBox<Sprite> PokeBall;
    public TextField IvHp;
    // Stats
    public Label BaseHp;
    public Label BaseAttack;
    public Label BaseDefense;
    public Label BaseSpecialAttack;
    public Label BaseSpecialDefense;
    public Label BaseSpeed;
    public Label BaseTotal;
    public TextField IvAttack;
    public TextField IvDefense;
    public TextField IvSpecialAttack;
    public TextField IvSpecialDefense;
    public TextField IvSpeed;
    public Label IvTotal;
    public TextField EvHp;
    public TextField EvAttack;
    public TextField EvDefense;
    public TextField EvSpecialAttack;
    public TextField EvSpecialDefense;
    public TextField EvSpeed;
    public Label EvTotal;
    public Label StatsHp;
    public Label StatsAttack;
    public Label StatsDefense;
    public Label StatsSpecialAttack;
    public Label StatsSpecialDefense;
    public Label StatsSpeed;
    // Attack
    public ComboBox<Classes.Attack> Attack1Move;
    public ComboBox<Classes.Attack> Attack2Move;
    public ComboBox<Classes.Attack> Attack3Move;
    public ComboBox<Classes.Attack> Attack4Move;
    public Label Attack1PP;
    public Label Attack2PP;
    public Label Attack3PP;
    public Label Attack4PP;
    public ComboBox<String> Attack1PPUps;
    public ComboBox<String> Attack2PPUps;
    public ComboBox<String> Attack3PPUps;
    public ComboBox<String> Attack4PPUps;
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
    public ObservableList<Sprite> pokeballSprites;

    public void backToMenu() {
        Utilities.backToMenu();
    }

    public void changedSpecies() {
    }

    // ------------------ Set Images ------------------
    public void changeHeldItemImage() {
        API.Database dbAPI = new API.Database();
        int spriteId = HeldItem.getValue().spriteId;
        String imagePath = dbAPI.getImagePathFromSpriteID(spriteId);
        if (spriteId > 0) HeldItemImage.setImage(new Image(imagePath));
        else HeldItemImage.setImage(null);
    }

    public void setPokeballImage() {
        API.Database dbAPI = new API.Database();
        String imagePath = dbAPI.getImagePathFromSpriteID(PokeBall.getValue().spriteId);
        PokeballImage.setImage(new Image(imagePath));
    }

    // ------------------ Attack ------------------
    public void setPPForMove1() {
        Attack attack = Attack1Move.getValue();
        Attack1PP.setText(Integer.toString(attack.pp));
    }

    public void setPPForMove2() {
        Attack attack = Attack2Move.getValue();
        Attack2PP.setText(Integer.toString(attack.pp));
    }

    public void setPPForMove3() {
        Attack attack = Attack3Move.getValue();
        Attack3PP.setText(Integer.toString(attack.pp));
    }

    public void setPPForMove4() {
        Attack attack = Attack3Move.getValue();
        Attack4PP.setText(Integer.toString(attack.pp));
    }

    // -------------------------- Calculation --------------------------
    public void calculateBaseIvEv() {
        calculateBaseTotal();
        calculateIvTotal();
        calculateEvTotal();
    }

    public void calculateAndSetStats() {
        int hp = calculateHpStat();
        int attack = calculateAttackStat(Nature.getValue());
        int defense = calculateDefenseStat(Nature.getValue());
        int specialAttack = calculateSpecialAttackStat(Nature.getValue());
        int specialDefense = calculateSpecialDefenseStat(Nature.getValue());
        int speed = calculateSpeedStat(Nature.getValue());

        StatsHp.setText(Integer.toString(hp));
        StatsAttack.setText(Integer.toString(attack));
        StatsDefense.setText(Integer.toString(defense));
        StatsSpecialAttack.setText(Integer.toString(specialAttack));
        StatsSpecialDefense.setText(Integer.toString(specialDefense));
        StatsSpeed.setText(Integer.toString(speed));
    }

    public void calculateBaseTotal() {
        int baseHP = Integer.parseInt(controller.BaseHp.getText());
        int baseAttack = Integer.parseInt(controller.BaseAttack.getText());
        int baseDefense = Integer.parseInt(controller.BaseDefense.getText());
        int baseSpAttack = Integer.parseInt(controller.BaseSpecialAttack.getText());
        int baseSpDefense = Integer.parseInt(controller.BaseSpecialDefense.getText());
        int baseSpeed = Integer.parseInt(controller.BaseSpeed.getText());
        int baseTotal = baseHP + baseAttack + baseDefense + baseSpAttack + baseSpDefense + baseSpeed;
        controller.BaseTotal.setText(Integer.toString(baseTotal));
    }

    public void calculateIvTotal() {
        try {
            int ivHP = Integer.parseInt(controller.IvHp.getText());
            int ivAttack = Integer.parseInt(controller.IvAttack.getText());
            int ivDefense = Integer.parseInt(controller.IvDefense.getText());
            int ivSpAttack = Integer.parseInt(controller.IvSpecialAttack.getText());
            int ivSpDefense = Integer.parseInt(controller.IvSpecialDefense.getText());
            int ivSpeed = Integer.parseInt(controller.IvSpeed.getText());
            int ivTotal = ivHP + ivAttack + ivDefense + ivSpAttack + ivSpDefense + ivSpeed;
            controller.IvTotal.setText(Integer.toString((ivTotal)));
        } catch (Exception e) {
            System.out.println(e);
            controller.IvTotal.setText("0");
        }
    }

    public void calculateEvTotal() {
        int evHP = Integer.parseInt(controller.EvHp.getText());
        int evAttack = Integer.parseInt(controller.EvAttack.getText());
        int evDefense = Integer.parseInt(controller.EvDefense.getText());
        int evSpAttack = Integer.parseInt(controller.EvSpecialAttack.getText());
        int evSpDefense = Integer.parseInt(controller.EvSpecialDefense.getText());
        int evSpeed = Integer.parseInt(controller.EvSpeed.getText());
        int evTotal = evHP + evAttack + evDefense + evSpAttack + evSpDefense + evSpeed;
        controller.EvTotal.setText(Integer.toString((evTotal)));
    }

    public int calculateHpStat() {
        try {
            int baseHp = Integer.parseInt(BaseHp.getText());
            int ivHp = Integer.parseInt(IvHp.getText());
            int evHp = Integer.parseInt(EvHp.getText());
            int level = Integer.parseInt(Level.getText());

            return (((2 * baseHp + ivHp + (evHp / 4)) * level) / 100) + level + 10;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int calculateAttackStat(Classes.Nature nature) {
        try {
            int baseAttack = Integer.parseInt(BaseAttack.getText());
            int ivAttack = Integer.parseInt(IvAttack.getText());
            int evAttack = Integer.parseInt(EvAttack.getText());
            int level = Integer.parseInt(Level.getText());
            double natureMultiplier = (nature.StatUp.equals("Attack")) ? 1.1 : (nature.StatDown.equals("Attack")) ? 0.9 : 1;

            double tempAttack = ((((2 * baseAttack + ivAttack + (evAttack / 4)) * level) / 100) + 5) * natureMultiplier;
            return (int) tempAttack;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int calculateDefenseStat(Classes.Nature nature) {
        try {
            int baseDefense = Integer.parseInt(BaseDefense.getText());
            int ivDefense = Integer.parseInt(IvDefense.getText());
            int evDefense = Integer.parseInt(EvDefense.getText());
            int level = Integer.parseInt(Level.getText());
            double natureMultiplier = (nature.StatUp.equals("Defense")) ? 1.1 : (nature.StatDown.equals("Defense")) ? 0.9 : 1;

            double tempDefense = ((((2 * baseDefense + ivDefense + (evDefense / 4)) * level) / 100) + 5) * natureMultiplier;
            return (int) tempDefense;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int calculateSpecialAttackStat(Classes.Nature nature) {
        try {
            int baseSpecialAttack = Integer.parseInt(BaseSpecialAttack.getText());
            int ivSpecialAttack = Integer.parseInt(IvSpecialAttack.getText());
            int evSpecialAttack = Integer.parseInt(EvSpecialAttack.getText());
            int level = Integer.parseInt(Level.getText());
            double natureMultiplier = (nature.StatUp.equals("SpecialAttack")) ? 1.1 : (nature.StatDown.equals("SpecialAttack")) ? 0.9 : 1;

            double tempSpecialAttack = ((((2 * baseSpecialAttack + ivSpecialAttack + (evSpecialAttack / 4)) * level) / 100) + 5) * natureMultiplier;
            return (int) tempSpecialAttack;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int calculateSpecialDefenseStat(Classes.Nature nature) {
        try {
            int baseSpecialDefense = Integer.parseInt(BaseSpecialDefense.getText());
            int ivSpecialDefense = Integer.parseInt(IvSpecialDefense.getText());
            int evSpecialDefense = Integer.parseInt(EvSpecialDefense.getText());
            int level = Integer.parseInt(Level.getText());
            double natureMultiplier = (nature.StatUp.equals("SpecialDefense")) ? 1.1 : (nature.StatDown.equals("SpecialDefense")) ? 0.9 : 1;

            double tempSpecialDefense = ((((2 * baseSpecialDefense + ivSpecialDefense + (evSpecialDefense / 4)) * level) / 100) + 5) * natureMultiplier;
            return (int) tempSpecialDefense;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    public int calculateSpeedStat(Classes.Nature nature) {
        try {
            int baseSpeed = Integer.parseInt(BaseSpeed.getText());
            int ivSpeed = Integer.parseInt(IvSpeed.getText());
            int evSpeed = Integer.parseInt(EvSpeed.getText());
            int level = Integer.parseInt(Level.getText());
            double natureMultiplier = (nature.StatUp.equals("Speed")) ? 1.1 : (nature.StatDown.equals("Speed")) ? 0.9 : 1;

            double tempSpeed = ((((2 * baseSpeed + ivSpeed + (evSpeed / 4)) * level) / 100) + 5) * natureMultiplier;
            return (int) tempSpeed;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    // ------------------ Options ------------------
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
