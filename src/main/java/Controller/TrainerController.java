package Controller;

import Classes.*;
import Classes.Abstract.AbstractPokemonController;
import Utilities.Utilities;
import View.TrainerView;
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

public class TrainerController extends AbstractPokemonController {
    private static TrainerController controller = null;
    public static TrainerController getInstance() {
        return controller;
    }
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
            tv.setPokemonStats(View.TrainerView.pokemonList.get(index), controller);
        } else tv.setPokemonStats(View.TrainerView.pokemonList.get(0), controller);
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
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        TrainerView tv = new TrainerView();
        tv.setPokemon(data.pokemonList);
        selectPokemonOnTheMenu(data.tempPokemon);
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
