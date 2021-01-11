package Controller;

import Classes.*;
import Utilities.Utilities;
import View.TrainerView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TrainerController {
    // static method to create instance of Singleton class
    private static TrainerController controller = null;
    public static TrainerController getInstance() {
        return controller;
    }
    @FXML
    public void initialize() {
        controller = this;
    }

    // Stats
    public VBox TrainerContainer;
    // Main
    public TextField TrainerName;
    public TextField FightNumber;
    public ComboBox<String> FoughtAlready;
    public ComboBox<Classes.Route> Route;
    // Trainer
    public VBox TrainerSpriteContainer;
    public Label TrainerSpriteBackground;
    // Pokemon Stats
    public VBox PokemonContainer;
    // ---------- Pokemon ----------
    // Box
    public HBox Pokemon1Box;
    public HBox Pokemon3Box;
    public HBox Pokemon5Box;
    public HBox Pokemon2Box;
    public HBox Pokemon4Box;
    public HBox Pokemon6Box;
    public HBox previousSelectedPokemon;
    // Label
    public Label Pokemon1Name;
    public Label Pokemon2Name;
    public Label Pokemon3Name;
    public Label Pokemon4Name;
    public Label Pokemon5Name;
    public Label Pokemon6Name;
    // Image
    public ImageView Pokemon1Image;
    public ImageView Pokemon2Image;
    public ImageView Pokemon3Image;
    public ImageView Pokemon4Image;
    public ImageView Pokemon5Image;
    public ImageView Pokemon6Image;
    // Main
    public ComboBox<PokeStatsList> SpeciesName;
    public TextField Nickname;
    public ComboBox<String> Gender;
    public TextField Level;
    public ComboBox<Nature> Nature;
    public ImageView HeldItemImage;
    public ComboBox<Item> HeldItem;
    public ComboBox<Ability> Ability;
    public TextField Friendship;
    // Met
    public ImageView PokeballImage;
    public ComboBox<Route> MetLocation;
    public ComboBox<Sprite> PokeBall;
    // Stats
    public TextField IvHp;
    public TextField IvAttack;
    public TextField IvDefense;
    public TextField IvSpecialAttack;
    public TextField IvSpecialDefense;
    public TextField IvSpeed;
    public TextField EvHp;
    public TextField EvAttack;
    public TextField EvDefense;
    public TextField EvSpecialAttack;
    public TextField EvSpecialDefense;
    public TextField EvSpeed;
    public Label BaseHp;
    public Label BaseAttack;
    public Label BaseDefense;
    public Label BaseSpecialAttack;
    public Label BaseSpecialDefense;
    public Label BaseSpeed;
    public Label BaseTotal;
    public Label IvTotal;
    public Label EvTotal;
    public Label StatsHp;
    public Label StatsAttack;
    public Label StatsDefense;
    public Label StatsSpecialAttack;
    public Label StatsSpecialDefense;
    public Label StatsSpeed;
    // Attack
    public ComboBox<Attack> Attack1Move;
    public ComboBox<Attack> Attack2Move;
    public ComboBox<Attack> Attack3Move;
    public ComboBox<Attack> Attack4Move;
    public Label Attack1PP;
    public Label Attack2PP;
    public Label Attack3PP;
    public Label Attack4PP;
    public ComboBox<String> Attack1PPUps;
    public ComboBox<String> Attack2PPUps;
    public ComboBox<String> Attack3PPUps;
    public ComboBox<String> Attack4PPUps;
    // Options
    public Button AddPokemonButton;
    public Button EditPokemonButton;
    public Button DeletePokemonButton;
    // Options Trainer
    public Button CreateTrainerButton;
    public Button EditTrainerButton;
    public Button DeleteTrainerButton;
    // ------- own variables -------
    public int pokemonId;
    public HBox activePokemonHBox;
    public Label activePokemonSprite;
    public Label activePokemonNickname;
    // Trainer
    public HBox activeTrainerBox;
    public int trainerSpriteId;
    public int whichPokemonIsOnTheLine;
    public int trainerId;
    // Labels for Update
    public Label trainerSpriteLabel;
    public Label fightNumberLabel;
    public Label trainerNameLabel;
    // Pokemons
    public Pokemon pokemon1;
    public Pokemon pokemon2;
    public Pokemon pokemon3;
    public Pokemon pokemon4;
    public Pokemon pokemon5;
    public Pokemon pokemon6;


    // ---------------- Back To Menu ----------------
    public void backToMenu() {
        Utilities.backToMenu();
    }

    // ---------------- Search Pokemon ----------------
    public void searchForPokemon() {
    }

    public void searchForTrainer() {
    }
    // ---------------- Pokemon----------------
    public void loadPokemon1() {
        whichPokemonIsOnTheLine = 1;
        setPreviousSelectedPokemonCss();
        Pokemon1Box.getStyleClass().clear();
        Pokemon1Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon1Box;

        updatePokemon(pokemon1);
    }

    public void loadPokemon2() {
        whichPokemonIsOnTheLine = 2;
        setPreviousSelectedPokemonCss();
        Pokemon2Box.getStyleClass().clear();
        Pokemon2Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon2Box;

        updatePokemon(pokemon2);
    }

    public void loadPokemon3() {
        whichPokemonIsOnTheLine = 3;
        setPreviousSelectedPokemonCss();
        Pokemon3Box.getStyleClass().clear();
        Pokemon3Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon3Box;

        updatePokemon(pokemon3);
    }

    public void loadPokemon4() {
        whichPokemonIsOnTheLine = 4;
        setPreviousSelectedPokemonCss();
        Pokemon4Box.getStyleClass().clear();
        Pokemon4Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon4Box;

        updatePokemon(pokemon4);
    }

    public void loadPokemon5() {
        whichPokemonIsOnTheLine = 5;
        setPreviousSelectedPokemonCss();
        Pokemon5Box.getStyleClass().clear();
        Pokemon5Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon5Box;

        updatePokemon(pokemon5);
    }

    public void loadPokemon6() {
        whichPokemonIsOnTheLine = 6;
        setPreviousSelectedPokemonCss();
        Pokemon6Box.getStyleClass().clear();
        Pokemon6Box.getStyleClass().add("activePokemonBox");
        previousSelectedPokemon = Pokemon6Box;

        updatePokemon(pokemon6);
    }

    private void setPreviousSelectedPokemonCss() {
        previousSelectedPokemon.getStyleClass().clear();
        previousSelectedPokemon.getStyleClass().add("passivePokemonBox");
    }

    private void updatePokemon(Pokemon pokemon) {
        pokemonId = pokemon.pokemonId;
        updatePokemonStats(pokemon);
        selectPokemonOnTheMenu(pokemon);

        TrainerView tv = new TrainerView();
        tv.setPokemonOptions(pokemon);
    }

    private void updatePokemonStats(Pokemon pokemon) {
        View.TrainerView tv = new View.TrainerView();
        if (pokemon != null) {
            int index = Utilities.findPokemonInPokemonList(pokemon, View.TrainerView.pokemonList);
            tv.setPokemonStats(View.TrainerView.pokemonList.get(index));
        } else tv.setPokemonStats(View.TrainerView.pokemonList.get(0));
    }

    public void selectPokemonOnTheMenu(Pokemon pokemon) {
        if (TrainerView.previousPokemonBox != null) {
            View.TrainerView.previousPokemonBox.getStyleClass().clear();
            View.TrainerView.previousPokemonBox.getStyleClass().add("createPokemonHBox");
        }

        for (Node node : PokemonContainer.getChildren()) {
            HBox hBox = (HBox) node;
            Label pokemonIdLabel = (Label) hBox.getChildren().get(2);
            int pokemonId = Integer.parseInt(pokemonIdLabel.getText());

            if (pokemonId == pokemon.pokemonId) {
                hBox.getStyleClass().clear();
                hBox.getStyleClass().add("createPokemonHBoxActive");
                View.TrainerView.previousPokemonBox = hBox;
                activePokemonHBox = hBox;
            }
        }
    }

    // -------------------------- Main --------------------------
    public void changedSpecies() {
        View.TrainerView pv = new View.TrainerView();
        int pokemonStatsId = SpeciesName.getValue().pokemonStatsId;
        PokeStats tempPokeStats;

        if (pokemonStatsId > 0) {
            API.Database dbAPI = new API.Database();
            tempPokeStats =  dbAPI.getPokeStatsFromPokemonStatsId(SpeciesName.getValue().pokemonStatsId);
        } else tempPokeStats = new PokeStats(0, "No Pokemon", 0, "Slow", 0, 0, 0, 0, 0, 0, 0, 0);

        pv.setBaseStats(tempPokeStats);
        calculateBaseTotal();
        calculateAndSetStats();
    }

    public void changedPokemonLevel() {
        calculateAndSetStats();
    }

    public void changedNature() {
        calculateAndSetStats();
    }

    public void changeHeldItemImage() {
        API.Database dbAPI = new API.Database();
        int spriteId = HeldItem.getValue().spriteId;

        if (spriteId > 0) {
            String imagePath = dbAPI.getImagePathFromSpriteID(spriteId);
            HeldItemImage.setImage(new Image(imagePath));
        }
        else HeldItemImage.setImage(null);
    }

    // -------------------------- Met --------------------------
    public void setPokeballImage() {
        API.Database dbAPI = new API.Database();
        String imagePath = dbAPI.getImagePathFromSpriteID(PokeBall.getValue().spriteId);
        PokeballImage.setImage(new Image(imagePath));
    }

    // -------------------------- Stats --------------------------
    // ---- IV ----
    public void changeIvHp() {
        calculateIvTotal();
        int hp = calculateHpStat();
        StatsHp.setText(Integer.toString(hp));
    }

    public void changeIvAttack() {
        calculateIvTotal();
        int attack = calculateAttackStat(Nature.getValue());
        StatsAttack.setText(Integer.toString(attack));
    }

    public void changeIvDefense() {
        calculateIvTotal();
        int defense = calculateDefenseStat(Nature.getValue());
        StatsDefense.setText(Integer.toString(defense));
    }

    public void changeIvSpecialAttack() {
        calculateIvTotal();
        int specialAttack = calculateSpecialAttackStat(Nature.getValue());
        StatsSpecialAttack.setText(Integer.toString(specialAttack));
    }

    public void changeIvSpecialDefense() {
        calculateIvTotal();
        int specialDefense = calculateSpecialDefenseStat(Nature.getValue());
        StatsSpecialDefense.setText(Integer.toString(specialDefense));
    }

    public void changeIvSpeed() {
        calculateIvTotal();
        int speed = calculateSpeedStat(Nature.getValue());
        StatsSpeed.setText(Integer.toString(speed));
    }

    // ---- EV ----
    public void changeEvHp() {
        calculateEvTotal();
        int hp = calculateHpStat();
        StatsHp.setText(Integer.toString(hp));
    }

    public void changeEvAttack() {
        calculateEvTotal();
        int attack = calculateAttackStat(Nature.getValue());
        StatsAttack.setText(Integer.toString(attack));
    }

    public void changeEvDefense() {
        calculateEvTotal();
        int defense = calculateDefenseStat(Nature.getValue());
        StatsDefense.setText(Integer.toString(defense));
    }

    public void changeEvSpecialAttack() {
        calculateEvTotal();
        int specialAttack = calculateSpecialAttackStat(Nature.getValue());
        StatsSpecialAttack.setText(Integer.toString(specialAttack));
    }

    public void changeEvSpecialDefense() {
        calculateEvTotal();
        int specialDefense = calculateSpecialDefenseStat(Nature.getValue());
        StatsSpecialDefense.setText(Integer.toString(specialDefense));
    }

    public void changeEvSpeed() {
        calculateEvTotal();
        int speed = calculateSpeedStat(Nature.getValue());
        StatsSpeed.setText(Integer.toString(speed));
    }

    // -------------------------- Attack --------------------------

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
    } // Base | Iv | Ev

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
        try {
            int evHP = Integer.parseInt(controller.EvHp.getText());
            int evAttack = Integer.parseInt(controller.EvAttack.getText());
            int evDefense = Integer.parseInt(controller.EvDefense.getText());
            int evSpAttack = Integer.parseInt(controller.EvSpecialAttack.getText());
            int evSpDefense = Integer.parseInt(controller.EvSpecialDefense.getText());
            int evSpeed = Integer.parseInt(controller.EvSpeed.getText());
            int evTotal = evHP + evAttack + evDefense + evSpAttack + evSpDefense + evSpeed;
            controller.EvTotal.setText(Integer.toString((evTotal)));
        } catch (Exception e) {
            System.out.println(e);
            controller.EvTotal.setText("0");
        }
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
            double natureMultiplier = (nature.StatUp.equals("Attack") && !nature.StatDown.equals("Attack")) ? 1.1 : (nature.StatDown.equals("Attack") && !nature.StatUp.equals("Attack")) ? 0.9 : 1;

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
            double natureMultiplier = (nature.StatUp.equals("Defense") && !nature.StatDown.equals("Defense")) ? 1.1 : (nature.StatDown.equals("Defense") && !nature.StatUp.equals("Defense")) ? 0.9 : 1;

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
            double natureMultiplier = (nature.StatUp.equals("SpecialAttack") && !nature.StatDown.equals("SpecialAttack")) ? 1.1 : (nature.StatDown.equals("SpecialAttack") && !nature.StatUp.equals("SpecialAttack")) ? 0.9 : 1;

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
            double natureMultiplier = (nature.StatUp.equals("SpecialDefense") && !nature.StatDown.equals("SpecialDefense")) ? 1.1 : (nature.StatDown.equals("SpecialDefense") && !nature.StatUp.equals("SpecialDefense")) ? 0.9 : 1;

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
            double natureMultiplier = (nature.StatUp.equals("Speed") && !nature.StatDown.equals("Speed")) ? 1.1 : (nature.StatDown.equals("Speed") && !nature.StatUp.equals("Speed")) ? 0.9 : 1;

            double tempSpeed = ((((2 * baseSpeed + ivSpeed + (evSpeed / 4)) * level) / 100) + 5) * natureMultiplier;
            return (int) tempSpeed;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    // -------------------------- Options-Pokemon --------------------------
    public void addPokemon() {
        if (SpeciesName.getValue().pokemonStatsId > 0) {
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

                TrainerView tv = new TrainerView();
                tv.setPokemon(new PokemonList(result, Nickname.getText(), SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr));

                API.Database dbAPI = new API.Database();
                Pokemon pokemon = dbAPI.getPokemonFromPokemonId(result);
                selectPokemonOnTheMenu(pokemon);
                tv.setPokemonOptions(pokemon);
            }
        }
    }

    public void editPokemon() {
        if (SpeciesName.getValue().pokemonStatsId > 0) {
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
        PokemonList newPokeStat = new PokemonList(pokemonId, Nickname.getText(), SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr);

        View.TrainerView pv = new View.TrainerView();
        pv.createPokemonLabel(newPokeStat);             // adds To ViewList
        TrainerView.pokemonList.add(newPokeStat);       // adds To PokemonList
    }

    // Edit Pokemon
    private void updateLabel() {
        // Sprite
        API.Database dbAPI = new API.Database();
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(SpeciesName.getValue().pokemonStatsId);
        String pokeSpritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        // Trainer-Pokemon
        TrainerView tv = new TrainerView();
        tv.setPokemon(new PokemonList(pokemonId, Nickname.getText(), SpeciesName.getValue().pokemonStatsId, SpeciesName.getValue().dexNr));
        // PokemonBox
        activePokemonSprite.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
        activePokemonNickname.setText(Nickname.getText());
    }

    // Delete Pokemon
    private void removePokemonFromLists() {
        deletePokemonFromPokemonList();
        PokemonContainer.getChildren().remove(activePokemonHBox);
    }

    private void selectNewFirstPokemon() {
        TrainerView tv = new TrainerView();
        tv.setPokemon(new PokemonList(0,  "No Pokemon", 0, 0));
        selectPokemonOnTheMenu(tv.tempPokemon);
    }

    private void deletePokemonFromPokemonList() {
        int index = 0;
        for (PokemonList pokemon : TrainerView.pokemonList) {
            if (pokemon.pokemonId == pokemonId) {
                break;
            }
            index++;
        }
        TrainerView.pokemonList.remove(index);
    }

    // -------------------------- Options-Trainer --------------------------
    public void createTrainer() {
        String nameOfTrainer = TrainerName.getText();

        if (nameOfTrainer.length() > 0) {
            Data.dataSingleton data = Data.dataSingleton.getInstance();
            API.Database dbAPI = new API.Database();

            // Trainer
            int routeId = Route.getValue().routeId;
            int fightNumber = Integer.parseInt(FightNumber.getText());
            boolean foughtAlready = FoughtAlready.getValue().equals("True");

            int trainerId = dbAPI.addTrainer(data.getGameName(), routeId, nameOfTrainer, fightNumber, foughtAlready, trainerSpriteId,
                    pokemon1.pokemonId, pokemon2.pokemonId, pokemon3.pokemonId, pokemon4.pokemonId, pokemon5.pokemonId, pokemon6.pokemonId);

            if (trainerId > 0) {
                addTrainerToList(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);
            }
        }
    }

    public void editTrainer() {
        String nameOfTrainer = TrainerName.getText();

        if (nameOfTrainer.length() > 0) {
            API.Database dbAPI = new API.Database();

            // Trainer
            int routeId = Route.getValue().routeId;
            int fightNumber = Integer.parseInt(FightNumber.getText());
            boolean foughtAlready = FoughtAlready.getValue().equals("True");

            boolean result = dbAPI.updateTrainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready, trainerSpriteId,
                    pokemon1.pokemonId, pokemon2.pokemonId, pokemon3.pokemonId, pokemon4.pokemonId, pokemon5.pokemonId, pokemon6.pokemonId);

            if (result) {
                // Update Label
                String pokeSpritePath = dbAPI.getImagePathFromSpriteID(trainerSpriteId);
                trainerSpriteLabel.setStyle("-fx-background-image: url(" + this.getClass().getResource(pokeSpritePath) + ")");
                fightNumberLabel.setText(Integer.toString(fightNumber));
                trainerNameLabel.setText(nameOfTrainer);
            }
        }
    }

    public void deleteTrainer() {
        if(View.TrainerView.trainerList.size() > 0) {
            API.Database dbAPI = new API.Database();

            ArrayList<Pokemon> trainerPokemons = dbAPI.getAllPokemonFromTrainer(trainerId);

            boolean result = dbAPI.deleteOnlyTrainer(trainerId);

            if (result) {
                removeTrainerFromLists();
                selectNewFirstPokeStats();
                // try to delete all the Pokemons the Trainer had
                for (Pokemon pokemon : trainerPokemons) {
                    result = dbAPI.deletePokemon(pokemon.pokemonId);
                    if (result) {
                        selectPokemonOnTheMenu(pokemon);
                        pokemonId = pokemon.pokemonId;
                        removePokemonFromLists();
                    }
                }
            }
        }
    }

    // Add Trainer
    private void addTrainerToList(int trainerId, int routeId, String nameOfTrainer, int fightNumber, boolean foughtAlready) {
        View.TrainerView tv = new View.TrainerView();
        Trainer tempTrainer = new Trainer(trainerId, routeId, nameOfTrainer, fightNumber, foughtAlready);

        tv.createTrainerLabel(tempTrainer);
        TrainerView.trainerList.add(tempTrainer);
    }

    // Delete Trainer
    private void removeTrainerFromLists() {
        deleteTrainerInTrainerList();
        TrainerContainer.getChildren().remove(activeTrainerBox);
    }

    private void selectNewFirstPokeStats() {
        View.TrainerView pv = new View.TrainerView();
        pv.selectFirstTrainer();
    }

    private void deleteTrainerInTrainerList() {
        int index = 0;
        for (Trainer trainer : View.TrainerView.trainerList) {
            if (trainer.trainerId == trainerId) {
                break;
            }
            index++;
        }
        View.TrainerView.trainerList.remove(index);
    }
}
