package Controller;

import Classes.*;
import Utilities.Utilities;
import View.MenuView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class oldCalcController {
    // static method to create instance of Singleton class
    private static oldCalcController controller = null;
    public static oldCalcController getInstance() {
        return controller;
    }

    API.Database dbAPI;
    Data.dataSingleton data;

    // My Variables
    public int activeUserPokemonIndex;
    public Label previousUserPokemonBox;
    public Pokemon activeUserPokemon;
    public Pokemon pokemon1;
    public Pokemon pokemon2;
    public Pokemon pokemon3;
    public Pokemon pokemon4;
    public Pokemon pokemon5;
    public Pokemon pokemon6;
    ObservableList<Trainer> trainerList;
    public int trainerIndex;
    public int activeTrainerPokemonIndex;
    public Label previousTrainerPokemonBox;
    public Pokemon activeTrainerPokemon;
    public Pokemon trainerPokemon1;
    public Pokemon trainerPokemon2;
    public Pokemon trainerPokemon3;
    public Pokemon trainerPokemon4;
    public Pokemon trainerPokemon5;
    public Pokemon trainerPokemon6;
    // flatDamage
    public boolean showFlatDamage;
    // Badges
    public Badge attackBadge;
    public boolean attackBadgeBoolean;
    public Badge defenseBadge;
    public boolean defenseBadgeBoolean;
    public Badge specialBadge;
    public boolean specialBadgeBoolean;
    public Badge speedBadge;
    public boolean speedBadgeBoolean;
    // Lists
    public ObservableList<Nature> natureList;
    public ObservableList<Ability> abilityList;
    public ObservableList<Item> itemList;
    public ObservableList<Attack> attackList;
    // User Move
    public Label userMove1Attack;
    public Label userMove1Damage;
    public Label userMove2Attack;
    public Label userMove2Damage;
    public Label userMove3Attack;
    public Label userMove3Damage;
    public Label userMove4Attack;
    public Label userMove4Damage;
    // Foe Move
    public Label foeMove1Attack;
    public Label foeMove1Damage;
    public Label foeMove2Damage;
    public Label foeMove2Attack;
    public Label foeMove3Damage;
    public Label foeMove3Attack;
    public Label foeMove4Damage;
    public Label foeMove4Attack;
    // User Pokemon
    public Label userPokemon1;
    public Label userPokemon2;
    public Label userPokemon3;
    public Label userPokemon4;
    public Label userPokemon5;
    public Label userPokemon6;
    public VBox UserPokemonVBox;
    public FlowPane UserPokemons;
    public VBox UserVBoxStats;
    public ComboBox<String> UserPokemonType1;
    public ComboBox<String> UserPokemonType2;
    public ComboBox<String> UserPokemonForm;
    public ComboBox<String> UserPokemonGender;
    public TextField UserPokemonLevel;
    public TextField UserPokemonBaseHP;
    public TextField UserPokemonIVHP;
    public TextField UserPokemonEVHP;
    public Label UserPokemonHP;
    public TextField UserPokemonBaseAttack;
    public TextField UserPokemonIVAttack;
    public TextField UserPokemonEVAttack;
    public Label UserPokemonAttack;
    public ComboBox<String> UserPokemonAttackBoost;
    public TextField UserPokemonBaseDefense;
    public TextField UserPokemonIVDefense;
    public TextField UserPokemonEVDefense;
    public Label UserPokemonDefense;
    public ComboBox<String> UserPokemonDefenseBoost;
    public TextField UserPokemonBaseSpecialAttack;
    public TextField UserPokemonIVSpecialAttack;
    public TextField UserPokemonEVSpecialAttack;
    public Label UserPokemonSpecialAttack;
    public ComboBox<String> UserPokemonSpecialAttackBoost;
    public TextField UserPokemonBaseSpecialDefense;
    public TextField UserPokemonIVSpecialDefense;
    public TextField UserPokemonEVSpecialDefense;
    public Label UserPokemonSpecialDefense;
    public ComboBox<String> UserPokemonSpecialDefenseBoost;
    public TextField UserPokemonBaseSpeed;
    public TextField UserPokemonIVSpeed;
    public TextField UserPokemonEVSpeed;
    public Label UserPokemonSpeed;
    public ComboBox<String> UserPokemonSpeedBoost;
    public ComboBox<Nature> UserPokemonNature;
    public ComboBox<Ability> UserPokemonAbility;
    public ComboBox<Item> UserPokemonItem;
    public ComboBox<String> UserPokemonStatus;
    public TextField UserPokemonCurrentHPFlat;
    public Label UserPokemonMaxHp;
    public TextField UserPokemonCurrentHPPercentage;
    public Button UserPokemonDynamax;
    public ComboBox<Attack> UserPokemonAttack1;
    public TextField UserPokemonAttack1Damage;
    public ComboBox<String> UserPokemonAttack1Type;
    public ComboBox<String> UserPokemonAttack1Category;
    public ComboBox<Attack> UserPokemonAttack2;
    public TextField UserPokemonAttack2Damage;
    public ComboBox<String> UserPokemonAttack2Type;
    public ComboBox<String> UserPokemonAttack2Category;
    public ComboBox<Attack> UserPokemonAttack3;
    public TextField UserPokemonAttack3Damage;
    public ComboBox<String> UserPokemonAttack3Type;
    public ComboBox<String> UserPokemonAttack3Category;
    public ComboBox<Attack> UserPokemonAttack4;
    public TextField UserPokemonAttack4Damage;
    public ComboBox<String> UserPokemonAttack4Type;
    public ComboBox<String> UserPokemonAttack4Category;
    // Center
    public ImageView Badge1;
    public ImageView Badge2;
    public ImageView Badge3;
    public ImageView Badge4;
    public ImageView Badge5;
    public ImageView Badge6;
    public ImageView Badge7;
    public ImageView Badge8;
    // Battel Mode
    public String activeBattleMode;
    public Button Singles;
    public Button Doubles;
    // Terrain
    public Button ElectricTerrain;
    public Button GrassyTerrain;
    public Button MistyTerrain;
    public Button PsychicTerrain;
    // Weather
    public String activeWeather;
    public Button None;
    public Button Sun;
    public Button Rain;
    public Button Sand;
    public Button Hail;
    public Button HarshSunshine;
    public Button HeavyRain;
    public Button StrongWinds;
    // Gravity
    public Button Gravity;
    // Other things
    public Button UserStealthRock;
    public Button FoeStealthRock;
    public Button UserSteelsurge;
    public Button FoeSteelsurge;
    public Button UserVineLash;
    public Button FoeVineLash;
    public Button UserCannonade;
    public Button UserSpike0;
    public Button UserReflect;
    public Button UserProtect;
    public Button UserLeechSeed;
    public Button UserForesight;
    public Button UserHelpingHand;
    public Button UserTailwind;
    public Button UserFriendGuard;
    public Button UserAuroraVeil;
    public Button UserBattery;
    public Button UserSwitchingOut;
    public Button UserWildfire;
    public Button UserVolcalith;
    public Button UserSpike1;
    public Button UserSpike2;
    public Button UserSpike3;
    public Button UserLightScreen;
    public Button FoeCannonade;
    public Button FoeSpike0;
    public Button FoeReflect;
    public Button FoeProtect;
    public Button FoeLeechSeed;
    public Button FoeForesight;
    public Button FoeHelpingHand;
    public Button FoeTailwind;
    public Button FoeFriendGuard;
    public Button FoeAuroraVeil;
    public Button FoeBattery;
    public Button FoeSwitchingOut;
    public Button FoeWildfire;
    public Button FoeVolcalith;
    public Button FoeSpike1;
    public Button FoeSpike2;
    public Button FoeSpike3;
    public Button FoeLightScreen;
    // Foe
    public ImageView FoeTrainerSprite;
    public Label foePokemon1;
    public Label foePokemon2;
    public Label foePokemon3;
    public Label foePokemon4;
    public Label foePokemon5;
    public Label foePokemon6;
    public VBox TrainerVBox;
    public FlowPane Trainers;
    public VBox TrainerVBoxStats;
    public ComboBox<String> FoePokemonType1;
    public ComboBox<String> FoePokemonType2;
    public ComboBox<String> FoePokemonForm;
    public ComboBox<String> FoePokemonGender;
    public TextField FoePokemonLevel;
    public TextField FoePokemonBaseHP;
    public TextField FoePokemonIVHP;
    public TextField FoePokemonEVHP;
    public Label FoePokemonHP;
    public TextField FoePokemonBaseAttack;
    public TextField FoePokemonIVAttack;
    public TextField FoePokemonEVAttack;
    public Label FoePokemonAttack;
    public ComboBox<String> FoePokemonAttackBoost;
    public TextField FoePokemonBaseDefense;
    public TextField FoePokemonIVDefense;
    public TextField FoePokemonEVDefense;
    public Label FoePokemonDefense;
    public ComboBox<String> FoePokemonDefenseBoost;
    public TextField FoePokemonBaseSpecialAttack;
    public TextField FoePokemonIVSpecialAttack;
    public TextField FoePokemonEVSpecialAttack;
    public Label FoePokemonSpecialAttack;
    public ComboBox<String> FoePokemonSpecialAttackBoost;
    public TextField FoePokemonBaseSpecialDefense;
    public TextField FoePokemonIVSpecialDefense;
    public TextField FoePokemonEVSpecialDefense;
    public Label FoePokemonSpecialDefense;
    public ComboBox<String> FoePokemonSpecialDefenseBoost;
    public TextField FoePokemonBaseSpeed;
    public TextField FoePokemonIVSpeed;
    public TextField FoePokemonEVSpeed;
    public Label FoePokemonSpeed;
    public ComboBox<String> FoePokemonSpeedBoost;
    public ComboBox<Nature> FoePokemonNature;
    public ComboBox<Ability> FoePokemonAbility;
    public ComboBox<Item> FoePokemonItem;
    public ComboBox<String> FoePokemonStatus;
    public TextField FoePokemonCurrentHPFlat;
    public Label FoePokemonMaxHp;
    public TextField FoePokemonCurrentHPPercentage;
    public Button FoePokemonDynamax;
    public ComboBox<Attack> FoePokemonAttack1;
    public TextField FoePokemonAttack1Damage;
    public ComboBox<String> FoePokemonAttack1Type;
    public ComboBox<String> FoePokemonAttack1Category;
    public ComboBox<Attack> FoePokemonAttack2;
    public TextField FoePokemonAttack2Damage;
    public ComboBox<String> FoePokemonAttack2Type;
    public ComboBox<String> FoePokemonAttack2Category;
    public ComboBox<Attack> FoePokemonAttack3;
    public TextField FoePokemonAttack3Damage;
    public ComboBox<String> FoePokemonAttack3Type;
    public ComboBox<String> FoePokemonAttack3Category;
    public ComboBox<Attack> FoePokemonAttack4;
    public TextField FoePokemonAttack4Damage;
    public ComboBox<String> FoePokemonAttack4Type;
    public ComboBox<String> FoePokemonAttack4Category;

    // -------------- Booleans for the middle part --------------
    // Badges
    public boolean badge1;
    public boolean badge2;
    public boolean badge3;
    public boolean badge4;
    public boolean badge5;
    public boolean badge6;
    public boolean badge7;
    public boolean badge8;
    // Mode
    public boolean singleBattle;
    public boolean doubleBattle;
    // Terrain
    public boolean electricTerrain;
    public boolean grassyTerrain;
    public boolean mistyTerain;
    public boolean psychicTerrain;
    // Weather
    public boolean noWeather;
    public boolean sun;
    public boolean rain;
    public boolean sand;
    public boolean hail;
    public boolean harshSunshine;
    public boolean heavyRain;
    public boolean strongWinds;
    // Gravity
    public boolean gravity;
    // other things - User
    public boolean userStealthRock;
    public boolean userSteelsurge;
    public boolean userVineLash, userWildfire;
    public boolean userCannonade, userVolcalith;
    public boolean userSpikes0, userSpikes1, userSpikes2, userSpikes3;
    public boolean userReflect, userLightScreen;
    public boolean userProtect;
    public boolean userLeechSeed;
    public boolean userForesight;
    public boolean userHelpingHand;
    public boolean userTailwind;
    public boolean userFriendGuard;
    public boolean userAuroraVeil;
    public boolean userBattery;
    public boolean userSwitchingOut;
    // other things - Foe
    public boolean foeStealthRock;
    public boolean foeSteelsurge;
    public boolean foeVineLash, foeWildfire;
    public boolean foeCannonade, foeVolcalith;
    public boolean foeSpikes0, foeSpikes1, foeSpikes2, foeSpikes3;
    public boolean foeReflect, foeLightScreen;
    public boolean foeProtect;
    public boolean foeLeechSeed;
    public boolean foeForesight;
    public boolean foeHelpingHand;
    public boolean foeTailwind;
    public boolean foeFriendGuard;
    public boolean foeAuroraVeil;
    public boolean foeBattery;
    public boolean foeSwitchingOut;


    // setUp Variables
    @FXML
    public void initialize() {
        controller = this;
        showRightWindowsWhenCalculatorIsOpened();
        setupBooleans();
        loadContent();
    }

    private void showRightWindowsWhenCalculatorIsOpened() {
        UserPokemonVBox.setVisible(true);
        UserVBoxStats.setVisible(false);
        TrainerVBox.setVisible(true);
        TrainerVBoxStats.setVisible(false);
    }

    // -------------------------------------------- Events --------------------------------------------
    public void backToMenu() {
        Data.dataSingleton data = Data.dataSingleton.getInstance();
        MenuView pv = new MenuView();
        pv.createMenuPage(data.getStage());
    }

    public void damagePercentage() {
        showFlatDamage = false;
        calculateDamage();
    }

    public void damageFlat() {
        showFlatDamage = true;
        calculateDamage();
    }

    // -------------------------------------------- User --------------------------------------------
    public void clickedUserPokemon1() {
        activeUserPokemon = pokemon1;
        setActiveUserPokemon(userPokemon1, 1);
        if (pokemon1 != null) setUserPokemonStats(pokemon1);
        calculateDamage();
    }

    public void clickedUserPokemon2() {
        activeUserPokemon = pokemon2;
        setActiveUserPokemon(userPokemon2, 2);
        if (pokemon2 != null) setUserPokemonStats(pokemon2);
        calculateDamage();
    }

    public void clickedUserPokemon3() {
        activeUserPokemon = pokemon3;
        setActiveUserPokemon(userPokemon3, 3);
        if (pokemon3 != null) setUserPokemonStats(pokemon3);
        calculateDamage();
    }

    public void clickedUserPokemon4() {
        activeUserPokemon = pokemon4;
        setActiveUserPokemon(userPokemon4, 4);
        if (pokemon4 != null) setUserPokemonStats(pokemon4);
        calculateDamage();
    }

    public void clickedUserPokemon5() {
        activeUserPokemon = pokemon5;
        setActiveUserPokemon(userPokemon5, 5);
        if (pokemon5 != null) setUserPokemonStats(pokemon5);
        calculateDamage();
    }

    public void clickedUserPokemon6() {
        activeUserPokemon = pokemon6;
        setActiveUserPokemon(userPokemon6, 6);
        if (pokemon6 != null) setUserPokemonStats(pokemon6);
        calculateDamage();
    }

    public void getUserBox() {
        UserPokemonVBox.setVisible(true);
        UserVBoxStats.setVisible(false);
    }

    public void getUserPokemonStats() {
        UserPokemonVBox.setVisible(false);
        UserVBoxStats.setVisible(true);
    }

    public void addPokemon() {
    }

    public void clickedUserPokemonType1() {
        calculateDamage();
    }

    public void clickedUserPokemonType2() {
        calculateDamage();
    }

    public void clickedUserPokemonForm() {
        calculateDamage();
    }

    public void clickedUserPokemonGender() {
        calculateDamage();
    }

    public void changeUserPokemonLevel() {
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void changedUserPokemonBaseHP() {
        calcUserPokemonHp();
        calculateDamage();
    }

    public void changedUserPokemonIVHP() {
        calcUserPokemonHp();
        calculateDamage();
    }

    public void changedUserPokemonEVHP() {
        calcUserPokemonHp();
        calculateDamage();
    }

    public void changedUserPokemonBaseAttack() {
        calcUserPokemonAttack();
        calculateDamage();
    }

    public void changedUserPokemonIVAttack() {
        calcUserPokemonAttack();
        calculateDamage();
    }

    public void changedUserPokemonEVAttack() {
        calcUserPokemonAttack();
        calculateDamage();
    }

    public void changeUserPokemonAttackBoost() {
        calculateDamage();
    }

    public void changedUserPokemonBaseDefense() {
        calcUserPokemonDefense();
        calculateDamage();
    }

    public void changedUserPokemonIVDefense() {
        calcUserPokemonDefense();
        calculateDamage();
    }

    public void changedUserPokemonEVDefense() {
        calcUserPokemonDefense();
        calculateDamage();
    }

    public void changedUserPokemonDefenseBoost() {
        calculateDamage();
    }

    public void changedUserPokemonBaseSpecialAttack() {
        calcUserPokemonSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonIVSpecialAttack() {
        calcUserPokemonSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonEVSpecialAttack() {
        calcUserPokemonSpecialAttack();
        calculateDamage();
    }

    public void changedUserPokemonEVSpecialAttackBoost() {
        calculateDamage();
    }

    public void changedUserPokemonBaseSpecialDefense() {
        calcUserPokemonSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonIVSpecialDefense() {
        calcUserPokemonSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonEVSpecialDefense() {
        calcUserPokemonSpecialDefense();
        calculateDamage();
    }

    public void changedUserPokemonSpecialDefenseBoost() {
        calculateDamage();
    }

    public void changedUserPokemonBaseSpeed() {
        calcUserPokemonSpeed();
        calculateDamage();
    }

    public void changedUserPokemonIVSpeed() {
        calcUserPokemonSpeed();
        calculateDamage();
    }

    public void changedUserPokemonEVSpeed() {
        calcUserPokemonSpeed();
        calculateDamage();
    }

    public void changedUserPokemonSpeedBoost() {
        calculateDamage();
    }

    public void changeUserPokemonNature() {
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void changeUserPokemonAbility() {
        calculateDamage();
    }

    public void changeUserPokemonItem() {
        calculateDamage();
    }

    public void changeUserPokemonStatus() {
        calculateDamage();
    }

    public void changedUserPokemonCurrentHPFlat() {
        calculateDamage();
    }

    public void changedUserPokemonCurrentHPPercentage() {
        calculateDamage();
    }

    public void clickedUserPokemonDynamax() {
        calculateDamage();
    }

    public void changedUserPokemonAttack1() {
        Attack attack = UserPokemonAttack1.getValue();
        setUserAttackValues(1,  attack);
        if (attack.attackId > 0) userMove1Attack.setText(attack.attackName);
        else userMove1Attack.setText("No Move");
        calculateDamage();
    }

    public void changedUserPokemonAttack1Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack1Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack1Category() {
        calculateDamage();
    }

    public void changedUserPokemonAttack2() {
        Attack attack = UserPokemonAttack2.getValue();
        setUserAttackValues(2,  attack);
        if (attack.attackId > 0) userMove2Attack.setText(attack.attackName);
        else userMove2Attack.setText("No Move");
        calculateDamage();
    }

    public void changedUserPokemonAttack2Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack2Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack2Category() {
        calculateDamage();
    }

    public void changedUserPokemonAttack3() {
        Attack attack = UserPokemonAttack3.getValue();
        setUserAttackValues(3,  attack);
        if (attack.attackId > 0) userMove3Attack.setText(attack.attackName);
        else userMove3Attack.setText("No Move");
        calculateDamage();
    }

    public void changedUserPokemonAttack3Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack3Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack3Category() {
        calculateDamage();
    }

    public void changedUserPokemonAttack4() {
        Attack attack = UserPokemonAttack4.getValue();
        setUserAttackValues(4,  attack);
        if (attack.attackId > 0) userMove4Attack.setText(attack.attackName);
        else userMove4Attack.setText("No Move");
        calculateDamage();
    }

    public void changedUserPokemonAttack4Damage() {
        calculateDamage();
    }

    public void changedUserPokemonAttack4Type() {
        calculateDamage();
    }

    public void changedUserPokemonAttack4Category() {
        calculateDamage();
    }

    // -------------------------------------------- Center --------------------------------------------

    public void clickedBadge1() {
        badge1 = switchBadgeToActiveOrNot(badge1, Badge1);
        setBadgeBoolean(0, badge1);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge2() {
        badge2 = switchBadgeToActiveOrNot(badge2, Badge2);
        setBadgeBoolean(1, badge2);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge3() {
        badge3 = switchBadgeToActiveOrNot(badge3, Badge3);
        setBadgeBoolean(2, badge3);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge4() {
        badge4 = switchBadgeToActiveOrNot(badge4, Badge4);
        setBadgeBoolean(3, badge4);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge5() {
        badge5 = switchBadgeToActiveOrNot(badge5, Badge5);
        setBadgeBoolean(4, badge5);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge6() {
        badge6 = switchBadgeToActiveOrNot(badge6, Badge6);
        setBadgeBoolean(5, badge6);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge7() {
        badge7 = switchBadgeToActiveOrNot(badge7, Badge7);
        setBadgeBoolean(6, badge7);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedBadge8() {
        badge8 = switchBadgeToActiveOrNot(badge8, Badge8);
        setBadgeBoolean(7, badge8);
        calcAndSetUserPokemonStats();
        calculateDamage();
    }

    public void clickedSingles() {
        if (!singleBattle) {
            activeBattleMode = "Single";

            singleBattle = true;
            Singles.getStyleClass().clear();
            Singles.getStyleClass().add("calcButtonInUsage");

            doubleBattle = false;
            Doubles.getStyleClass().clear();
            Doubles.getStyleClass().add("calcButtonsNotUsed");
        }
        calculateDamage();
    }

    public void clickedDoubles() {
        if (!doubleBattle) {
            activeBattleMode = "Double";

            doubleBattle = true;
            Doubles.getStyleClass().clear();
            Doubles.getStyleClass().add("calcButtonInUsage");

            singleBattle = false;
            Singles.getStyleClass().clear();
            Singles.getStyleClass().add("calcButtonsNotUsed");
        }
        calculateDamage();
    }

    public void clickedElectricTerrain() {
        electricTerrain = !electricTerrain;

        if (electricTerrain) {
            grassyTerrain = false; mistyTerain = false; psychicTerrain = false;
        }

        setTerrainCSS();
    }

    public void clickedGrassyTerrain() {
        grassyTerrain = !grassyTerrain;

        if (grassyTerrain) {
            electricTerrain = false; mistyTerain = false; psychicTerrain = false;
        }

        setTerrainCSS();
    }

    public void clickedMistyTerrain() {
        mistyTerain = !mistyTerain;

        if (mistyTerain) {
            electricTerrain = false; grassyTerrain = false; psychicTerrain = false;
        }

        setTerrainCSS();
    }

    public void clickedPsychicTerrain() {
        psychicTerrain = !psychicTerrain;

        if (psychicTerrain) {
            electricTerrain = false; grassyTerrain = false; mistyTerain = false;
        }

        setTerrainCSS();
    }

    public void clickedNone() {
        if (!noWeather) {
            activeWeather = "None";

            noWeather = true;
            sun = false; rain = false; sand = false; hail = false;
            harshSunshine = false; heavyRain = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedSun() {
        if (!sun) {
            activeWeather = "Sun";

            sun = true;
            noWeather = false; rain = false; sand = false; hail = false;
            harshSunshine = false; heavyRain = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedRain() {
        if (!rain) {
            activeWeather = "Rain";

            rain = true;
            noWeather = false; sun = false; sand = false; hail = false;
            harshSunshine = false; heavyRain = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedSand() {
        if (!sand) {
            activeWeather = "Sand";

            sand = true;
            noWeather = false; sun = false; rain = false; hail = false;
            harshSunshine = false; heavyRain = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedHail() {
        if (!hail) {
            activeWeather = "Hail";

            hail = true;
            noWeather = false; sun = false; rain = false; sand = false;
            harshSunshine = false; heavyRain = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedHarshSunshine() {
        if (!harshSunshine) {
            activeWeather = "Sun";

            harshSunshine = true;
            noWeather = false; sun = false; rain = false; sand = false;
            hail = false; heavyRain = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedHeavyRain() {
        if (!heavyRain) {
            activeWeather = "Rain";

            heavyRain = true;
            noWeather = false; sun = false; rain = false; sand = false;
            hail = false; harshSunshine = false; strongWinds = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedStrongWinds() {
        if (!strongWinds) {
            activeWeather = "None";

            strongWinds = true;
            noWeather = false; sun = false; rain = false; sand = false;
            hail = false; harshSunshine = false; heavyRain = false;
        }

        setWeatherCss();
        calculateDamage();
    }

    public void clickedGravity() {
        gravity = !gravity;

        Gravity.getStyleClass().clear();
        if (gravity) Gravity.getStyleClass().add("calcButtonInUsage");
        else Gravity.getStyleClass().add("calcButtonsNotUsed");
    }

    public void clickedUserStealthRock() {
        userStealthRock = switchButtonOnActiveOrNot(userStealthRock, UserStealthRock);
    }

    public void clickedFoeStealthRock() {
        foeStealthRock = switchButtonOnActiveOrNot(foeStealthRock, FoeStealthRock);
    }

    public void clickedUserSteelsurge() {
        userSteelsurge = switchButtonOnActiveOrNot(userSteelsurge, UserSteelsurge);
    }

    public void clickedFoeSteelsurge() {
        foeSteelsurge = switchButtonOnActiveOrNot(foeSteelsurge, FoeSteelsurge);
    }

    public void clickedUserVineLash() {
        userVineLash = switchButtonOnActiveOrNot(userVineLash, UserVineLash);
    }

    public void clickedFoeVineLash() {
        foeVineLash = switchButtonOnActiveOrNot(foeVineLash, FoeVineLash);
    }

    public void clickedUserWildfire() {
        userWildfire = switchButtonOnActiveOrNot(userWildfire, UserWildfire);
    }

    public void clickedFoeWildfire() {
        foeWildfire = switchButtonOnActiveOrNot(foeWildfire, FoeWildfire);
    }

    public void clickedUserCannonade() {
        userCannonade = switchButtonOnActiveOrNot(userCannonade, UserCannonade);
    }

    public void clickedFoeCannonade() {
        foeCannonade = switchButtonOnActiveOrNot(foeCannonade, FoeCannonade);
    }

    public void clickedUserVolcalith() {
        userVolcalith = switchButtonOnActiveOrNot(userVolcalith, UserVolcalith);
    }

    public void clickedFoeVolcalith() {
        foeVolcalith = switchButtonOnActiveOrNot(foeVolcalith, FoeVolcalith);
    }

    public void clickedUserSpike0() {
        if (!userSpikes0) {
            userSpikes0 = true;
            UserSpike0.getStyleClass().clear();
            UserSpike0.getStyleClass().add("calcButtonInUsage");

            userSpikes1 = setSpikeToFalse(UserSpike1);
            userSpikes2 = setSpikeToFalse(UserSpike2);
            userSpikes3 = setSpikeToFalse(UserSpike3);
        }
    }

    public void clickedUserSpike1() {
        if (!userSpikes1) {
            userSpikes1 = true;
            UserSpike1.getStyleClass().clear();
            UserSpike1.getStyleClass().add("calcButtonInUsage");

            userSpikes0 = setSpikeToFalse(UserSpike0);
            userSpikes2 = setSpikeToFalse(UserSpike2);
            userSpikes3 = setSpikeToFalse(UserSpike3);
        }
    }

    public void clickedUserSpike2() {
        if (!userSpikes2) {
            userSpikes2 = true;
            UserSpike2.getStyleClass().clear();
            UserSpike2.getStyleClass().add("calcButtonInUsage");

            userSpikes0 = setSpikeToFalse(UserSpike0);
            userSpikes1 = setSpikeToFalse(UserSpike1);
            userSpikes3 = setSpikeToFalse(UserSpike3);
        }
    }

    public void clickedUserSpike3() {
        if (!userSpikes3) {
            userSpikes3 = true;
            UserSpike3.getStyleClass().clear();
            UserSpike3.getStyleClass().add("calcButtonInUsage");

            userSpikes0 = setSpikeToFalse(UserSpike0);
            userSpikes1 = setSpikeToFalse(UserSpike1);
            userSpikes2 = setSpikeToFalse(UserSpike2);
        }
    }

    public void clickedFoeSpike0() {
        if (!foeSpikes0) {
            foeSpikes0 = true;
            FoeSpike0.getStyleClass().clear();
            FoeSpike0.getStyleClass().add("calcButtonInUsage");

            foeSpikes1 = setSpikeToFalse(FoeSpike1);
            foeSpikes2 = setSpikeToFalse(FoeSpike2);
            foeSpikes3 = setSpikeToFalse(FoeSpike3);
        }
    }

    public void clickedFoeSpike1() {
        if (!foeSpikes1) {
            foeSpikes1 = true;
            FoeSpike1.getStyleClass().clear();
            FoeSpike1.getStyleClass().add("calcButtonInUsage");

            foeSpikes0 = setSpikeToFalse(FoeSpike0);
            foeSpikes2 = setSpikeToFalse(FoeSpike2);
            foeSpikes3 = setSpikeToFalse(FoeSpike3);
        }
    }

    public void clickedFoeSpike2() {
        if (!foeSpikes2) {
            foeSpikes2 = true;
            FoeSpike2.getStyleClass().clear();
            FoeSpike2.getStyleClass().add("calcButtonInUsage");

            foeSpikes0 = setSpikeToFalse(FoeSpike0);
            foeSpikes1 = setSpikeToFalse(FoeSpike1);
            foeSpikes3 = setSpikeToFalse(FoeSpike3);
        }
    }

    public void clickedFoeSpike3() {
        if (!foeSpikes3) {
            foeSpikes3 = true;
            FoeSpike3.getStyleClass().clear();
            FoeSpike3.getStyleClass().add("calcButtonInUsage");

            foeSpikes0 = setSpikeToFalse(FoeSpike0);
            foeSpikes1 = setSpikeToFalse(FoeSpike1);
            foeSpikes2 = setSpikeToFalse(FoeSpike2);
        }
    }

    public void clickedUserReflect() {
        userReflect = switchButtonOnActiveOrNot(userReflect, UserReflect);
        calculateDamage();
    }

    public void clickedFoeReflect() {
        foeReflect = switchButtonOnActiveOrNot(foeReflect, FoeReflect);
        calculateDamage();
    }

    public void clickedUserLightScreen() {
        userLightScreen = switchButtonOnActiveOrNot(userLightScreen, UserLightScreen);
        calculateDamage();
    }

    public void clickedFoeLightScreen() {
        foeLightScreen = switchButtonOnActiveOrNot(foeLightScreen, FoeLightScreen);
        calculateDamage();
    }

    public void clickedUserProtect() {
        userProtect = switchButtonOnActiveOrNot(userProtect, UserProtect);
    }

    public void clickedFoeProtect() {
        foeProtect = switchButtonOnActiveOrNot(foeProtect, FoeProtect);
    }

    public void clickedUserLeechSeed() {
        userLeechSeed = switchButtonOnActiveOrNot(userLeechSeed, UserLeechSeed);
    }

    public void clickedFoeLeechSeed() {
        foeLeechSeed = switchButtonOnActiveOrNot(foeLeechSeed, FoeLeechSeed);
    }

    public void clickedUserForesight() {
        userForesight = switchButtonOnActiveOrNot(userForesight, UserForesight);
    }

    public void clickedFoeForesight() {
        foeForesight = switchButtonOnActiveOrNot(foeForesight, FoeForesight);
    }

    public void clickedUserHelpingHand() {
        userHelpingHand = switchButtonOnActiveOrNot(userHelpingHand, UserHelpingHand);
    }

    public void clickedFoeHelpingHand() {
        foeHelpingHand = switchButtonOnActiveOrNot(foeHelpingHand, FoeHelpingHand);
    }

    public void clickedUserTailwind() {
        userTailwind = switchButtonOnActiveOrNot(userTailwind, UserTailwind);
    }

    public void clickedFoeTailwind() {
        foeTailwind = switchButtonOnActiveOrNot(foeTailwind, FoeTailwind);
    }

    public void clickedUserFriendGuard() {
        userFriendGuard = switchButtonOnActiveOrNot(userFriendGuard, UserFriendGuard);
    }

    public void clickedFoeFriendGuard() {
        foeFriendGuard = switchButtonOnActiveOrNot(foeFriendGuard, FoeFriendGuard);
    }

    public void clickedUserAuroraVail() {
        userAuroraVeil = switchButtonOnActiveOrNot(userAuroraVeil, UserAuroraVeil);
    }

    public void clickedFoeAuroraVeil() {
        foeAuroraVeil = switchButtonOnActiveOrNot(foeAuroraVeil, FoeAuroraVeil);
    }

    public void clickedUserBattery() {
        userBattery = switchButtonOnActiveOrNot(userBattery, UserBattery);
    }

    public void clickedFoeBattery() {
        foeBattery = switchButtonOnActiveOrNot(foeBattery, FoeBattery);
    }

    public void clickedUserSwitchingOut() {
        userSwitchingOut = switchButtonOnActiveOrNot(userSwitchingOut, UserSwitchingOut);
    }

    public void clickedFoeSwitchingOut() {
        foeSwitchingOut = switchButtonOnActiveOrNot(foeSwitchingOut, FoeSwitchingOut);
    }

    // -------------------------------------------- Trainer --------------------------------------------
    public void clickedFoePokemon1() {
        activeTrainerPokemon = trainerPokemon1;
        setActiveTrainerPokemon(foePokemon1, 1);
        if (trainerPokemon1 != null) setTrainerPokemonStats(trainerPokemon1);
        calculateDamage();
    }

    public void clickedFoePokemon2() {
        activeTrainerPokemon = trainerPokemon2;
        setActiveTrainerPokemon(foePokemon2, 2);
        if (trainerPokemon2 != null) setTrainerPokemonStats(trainerPokemon2);
        calculateDamage();
    }

    public void clickedFoePokemon3() {
        activeTrainerPokemon = trainerPokemon3;
        setActiveTrainerPokemon(foePokemon3, 3);
        if (trainerPokemon3 != null) setTrainerPokemonStats(trainerPokemon3);
        calculateDamage();
    }

    public void clickedFoePokemon4() {
        activeTrainerPokemon = trainerPokemon4;
        setActiveTrainerPokemon(foePokemon4, 4);
        if (trainerPokemon4 != null) setTrainerPokemonStats(trainerPokemon4);
        calculateDamage();
    }

    public void clickedFoePokemon5() {
        activeTrainerPokemon = trainerPokemon5;
        setActiveTrainerPokemon(foePokemon5, 5);
        if (trainerPokemon5 != null) setTrainerPokemonStats(trainerPokemon5);
        calculateDamage();
    }

    public void clickedFoePokemon6() {
        activeTrainerPokemon = trainerPokemon6;
        setActiveTrainerPokemon(foePokemon6, 6);
        if (trainerPokemon6 != null) setTrainerPokemonStats(trainerPokemon6);
        calculateDamage();
    }

    public void getTrainers() {
        TrainerVBox.setVisible(true);
        TrainerVBoxStats.setVisible(false);
    }

    public void getFoePokemonStats() {
        TrainerVBox.setVisible(false);
        TrainerVBoxStats.setVisible(true);
    }

    public void previousTrainer() {
        if (trainerIndex - 1 >= 0) {
            trainerIndex--;
            selectTrainer(trainerIndex);
        }
    }

    public void nextTrainer() {
        if (trainerIndex + 1 < trainerList.size()) {
            trainerIndex++;
            selectTrainer(trainerIndex);
        }
    }

    public void addTrainer() {
    }

    public void changeFoePokemonType1() {
        calculateDamage();
    }

    public void changeFoePokemonType2() {
        calculateDamage();
    }

    public void changeFoePokemonForm() {
    }

    public void changeFoePokemonGender() {
        calculateDamage();
    }

    public void changeFoePokemonLevel() {
        calcAndSetTrainerPokemonStats();
        calculateDamage();
    }

    public void changeFoePokemonBaseHP() {
        calcTrainerPokemonHp();
        calculateDamage();
    }

    public void changeFoePokemonIVHP() {
        calcTrainerPokemonHp();
        calculateDamage();
    }

    public void changeFoePokemonEVHP() {
        calcTrainerPokemonHp();
        calculateDamage();
    }

    public void changeFoePokemonBaseAttack() {
        calcTrainerPokemonAttack();
        calculateDamage();
    }

    public void changeFoePokemonIVAttack() {
        calcTrainerPokemonAttack();
        calculateDamage();
    }

    public void changeFoePokemonEVAttack() {
        calcTrainerPokemonAttack();
        calculateDamage();
    }

    public void changeFoePokemonAttackBoost() {
        calculateDamage();
    }

    public void changeFoePokemonBaseDefense() {
        calcTrainerPokemonDefense();
        calculateDamage();
    }

    public void changeFoePokemonIVDefense() {
        calcTrainerPokemonDefense();
        calculateDamage();
    }

    public void changeFoePokemonEVDefense() {
        calcTrainerPokemonDefense();
        calculateDamage();
    }

    public void changeFoePokemonDefenseBoost() {
        calculateDamage();
    }

    public void changeFoePokemonBaseSpecialAttack() {
        calcTrainerPokemonSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonIVSpecialAttack() {
        calcTrainerPokemonSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonEVSpecialAttack() {
        calcTrainerPokemonSpecialAttack();
        calculateDamage();
    }

    public void changeFoePokemonSpecialAttackBoost() {
        calculateDamage();
    }

    public void changeFoePokemonBaseSpecialDefense() {
        calcTrainerPokemonSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonIVSpecialDefense() {
        calcTrainerPokemonSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonEVSpecialDefense() {
        calcTrainerPokemonSpecialDefense();
        calculateDamage();
    }

    public void changeFoePokemonSpecialDefenseBoost() {
        calculateDamage();
    }

    public void changeFoePokemonBaseSpeed() {
        calcTrainerPokemonSpeed();
        calculateDamage();
    }

    public void changeFoePokemonIVSpeed() {
        calcTrainerPokemonSpeed();
        calculateDamage();
    }

    public void changeFoePokemonEVSpeed() {
        calcTrainerPokemonSpeed();
        calculateDamage();
    }

    public void changeFoePokemonSpeedBoost() {
        calculateDamage();
    }

    public void changeFoePokemonNature() {
        calcAndSetTrainerPokemonStats();
        calculateDamage();
    }

    public void changeFoePokemonAbility() {
        calculateDamage();
    }

    public void changeFoePokemonItem() {
        calculateDamage();
    }

    public void changeFoePokemonStatus() {
        calculateDamage();
    }

    public void changeFoePokemonCurrentHPFlat() {
        calculateDamage();
    }

    public void changeFoePokemonCurrentHPPercentage() {
        calculateDamage();
    }

    public void clickFoePokemonDynamax() {
    }

    public void changeFoePokemonAttack1() {
        Attack attack = FoePokemonAttack1.getValue();
        setTrainerAttackValues(1,  attack);
        if (attack.attackId > 0) foeMove1Attack.setText(attack.attackName);
        else foeMove1Attack.setText("No Move");
        calculateDamage();
    }

    public void changeFoePokemonAttack1Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack1Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack1Category() {
        calculateDamage();
    }

    public void changeFoePokemonAttack2() {
        Attack attack = FoePokemonAttack2.getValue();
        setTrainerAttackValues(2,  attack);
        if (attack.attackId > 0) foeMove2Attack.setText(attack.attackName);
        else foeMove2Attack.setText("No Move");
        calculateDamage();
    }

    public void changeFoePokemonAttack2Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack2Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack2Category() {
        calculateDamage();
    }

    public void changeFoePokemonAttack3() {
        Attack attack = FoePokemonAttack3.getValue();
        setTrainerAttackValues(3,  attack);
        if (attack.attackId > 0) foeMove3Attack.setText(attack.attackName);
        else foeMove3Attack.setText("No Move");
        calculateDamage();
    }

    public void changeFoePokemonAttack3Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack3Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack3Category() {
        calculateDamage();
    }

    public void changeFoePokemonAttack4() {
        Attack attack = FoePokemonAttack4.getValue();
        setTrainerAttackValues(4,  attack);
        if (attack.attackId > 0) foeMove4Attack.setText(attack.attackName);
        else foeMove4Attack.setText("No Move");
        calculateDamage();
    }

    public void changeFoePokemonAttack4Damage() {
        calculateDamage();
    }

    public void changeFoePokemonAttack4Type() {
        calculateDamage();
    }

    public void changeFoePokemonAttack4Category() {
        calculateDamage();
    }

    // -------------------------------------------- Own Functions --------------------------------------------
    private void setupBooleans() {
        showFlatDamage = false;
        activeBattleMode = "Single";
        activeWeather = "None";
        // Badges
        badge1 = false;
        badge2 = false;
        badge3 = false;
        badge4 = false;
        badge5 = false;
        badge6 = false;
        badge7 = false;
        badge8 = false;
        // Modes
        singleBattle = true; doubleBattle = false;
        // Terrain
        electricTerrain = false;
        grassyTerrain = false;
        mistyTerain = false;
        psychicTerrain = false;
        // Weather
        noWeather = true;
        sun = false;
        rain = false;
        sand = false;
        hail = false;
        harshSunshine = false;
        heavyRain = false;
        strongWinds = false;
        // Gravity
        gravity = false;
        // other things - User
        userStealthRock = false;
        userSteelsurge = false;
        userVineLash = false; userWildfire = false;
        userCannonade = false; userVolcalith = false;
        userSpikes0 = true; userSpikes1 = false; userSpikes2 = false; userSpikes3 = false;
        userReflect = false; userLightScreen = false;
        userProtect = false;
        userLeechSeed = false;
        userForesight = false;
        userHelpingHand = false;
        userTailwind = false;
        userFriendGuard = false;
        userAuroraVeil = false;
        userBattery = false;
        userSwitchingOut = false;
        // other things - Foe
        foeStealthRock = false;
        foeSteelsurge = false;
        foeVineLash = false; foeWildfire = false;
        foeCannonade = false; foeVolcalith = false;
        foeSpikes0 = true; foeSpikes1 = false; foeSpikes2 = false; foeSpikes3 = false;
        foeReflect = false; foeLightScreen = false;
        foeProtect = false;
        foeLeechSeed = false;
        foeForesight = false;
        foeHelpingHand = false;
        foeTailwind = false;
        foeFriendGuard = false;
        foeAuroraVeil = false;
        foeBattery = false;
        foeSwitchingOut = false;
    }

    private void setTerrainCSS() {
        ElectricTerrain.getStyleClass().clear();
        GrassyTerrain.getStyleClass().clear();
        MistyTerrain.getStyleClass().clear();
        PsychicTerrain.getStyleClass().clear();

        if (electricTerrain) ElectricTerrain.getStyleClass().add("calcButtonInUsage");
        else ElectricTerrain.getStyleClass().add("calcButtonsNotUsed");

        if (grassyTerrain) GrassyTerrain.getStyleClass().add("calcButtonInUsage");
        else GrassyTerrain.getStyleClass().add("calcButtonsNotUsed");

        if (mistyTerain) MistyTerrain.getStyleClass().add("calcButtonInUsage");
        else MistyTerrain.getStyleClass().add("calcButtonsNotUsed");

        if (psychicTerrain) PsychicTerrain.getStyleClass().add("calcButtonInUsage");
        else PsychicTerrain.getStyleClass().add("calcButtonsNotUsed");
    }

    private void setWeatherCss() {
        None.getStyleClass().clear();
        Sun.getStyleClass().clear();
        Rain.getStyleClass().clear();
        Sand.getStyleClass().clear();
        Hail.getStyleClass().clear();
        HarshSunshine.getStyleClass().clear();
        HeavyRain.getStyleClass().clear();
        StrongWinds.getStyleClass().clear();

        if(noWeather) None.getStyleClass().add("calcButtonInUsage");
        else None.getStyleClass().add("calcButtonsNotUsed");

        if(sun) Sun.getStyleClass().add("calcButtonInUsage");
        else Sun.getStyleClass().add("calcButtonsNotUsed");

        if(rain) Rain.getStyleClass().add("calcButtonInUsage");
        else Rain.getStyleClass().add("calcButtonsNotUsed");

        if(sand) Sand.getStyleClass().add("calcButtonInUsage");
        else Sand.getStyleClass().add("calcButtonsNotUsed");

        if(hail) Hail.getStyleClass().add("calcButtonInUsage");
        else Hail.getStyleClass().add("calcButtonsNotUsed");

        if(harshSunshine) HarshSunshine.getStyleClass().add("calcButtonInUsage");
        else HarshSunshine.getStyleClass().add("calcButtonsNotUsed");

        if(heavyRain) HeavyRain.getStyleClass().add("calcButtonInUsage");
        else HeavyRain.getStyleClass().add("calcButtonsNotUsed");

        if(strongWinds) StrongWinds.getStyleClass().add("calcButtonInUsage");
        else StrongWinds.getStyleClass().add("calcButtonsNotUsed");
    }

    private boolean switchButtonOnActiveOrNot(boolean b, Button btn) {
        b = !b;

        btn.getStyleClass().clear();
        if (b) btn.getStyleClass().add("calcButtonInUsage");
        else btn.getStyleClass().add("calcButtonsNotUsed");
        return b;
    }

    private boolean switchBadgeToActiveOrNot(boolean b, ImageView image) {
        b = !b;

        if (b) image.setOpacity(1);
        else image.setOpacity(0.3);

        return b;
    }

    private boolean setSpikeToFalse(Button btn) {
        btn.getStyleClass().clear();
        btn.getStyleClass().add("calcButtonsNotUsed");
        return false;
    }

    // selects The Badge
    private void setBadgeBoolean(int gymOrder, boolean isSet) {
        ArrayList<Badge> badgeList = dbAPI.getAllBadgesFromGame(data.getGameName());
        Badge badge = badgeList.get(gymOrder);

        if (badge.statBoost != null) {
            switch (badge.statBoost) {
                case "Attack" -> {
                    attackBadge = badgeList.get(gymOrder);
                    attackBadgeBoolean = isSet;
                }
                case "Defense" -> {
                    defenseBadge = badgeList.get(gymOrder);
                    defenseBadgeBoolean = isSet;
                }
                case "Special" -> {
                    specialBadge = badgeList.get(gymOrder);
                    specialBadgeBoolean = isSet;
                }
                case "Speed" -> {
                    speedBadge = badgeList.get(gymOrder);
                    speedBadgeBoolean = isSet;
                }
            }
        }

    }

    // User Selects Pokemon
    private void setActiveUserPokemon(Label label, int i) {
        activeUserPokemonIndex = i;
        previousUserPokemonBox.getStyleClass().removeIf(style -> style.equals("pokeballActiveBox"));
        label.getStyleClass().add("pokeballActiveBox");
        previousUserPokemonBox = label;
    }

    // Trainer Selects Pokemon
    private void setActiveTrainerPokemon(Label label, int i) {
        activeTrainerPokemonIndex = i;
        previousTrainerPokemonBox.getStyleClass().removeIf(style -> style.equals("pokeballActiveBox"));
        label.getStyleClass().add("pokeballActiveBox");
        previousTrainerPokemonBox = label;
    }

    // sets userPokemonStats
    private void setUserPokemonStats(Pokemon pokemon) {
        // remove EventHandler otherwise they will triggered when you select Pokemon
        // get the handles to add them later again
        EventHandler<ActionEvent> userPokemonHandlerType1 = UserPokemonType1.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerType2 = UserPokemonType2.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerGender = UserPokemonGender.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerNature = UserPokemonNature.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAbility = UserPokemonAbility.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerItem = UserPokemonItem.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerStatus = UserPokemonStatus.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack1 = UserPokemonAttack1.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack2 = UserPokemonAttack2.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack3 = UserPokemonAttack3.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttack4 = UserPokemonAttack4.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType1 = UserPokemonAttack1Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType2 = UserPokemonAttack2Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType3 = UserPokemonAttack3Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackType4 = UserPokemonAttack4Type.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory1 = UserPokemonAttack1Category.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory2 = UserPokemonAttack2Category.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory3 = UserPokemonAttack3Category.getOnAction();
        EventHandler<ActionEvent> userPokemonHandlerAttackCategory4 = UserPokemonAttack4Category.getOnAction();
        // remove onAction
        UserPokemonType1.setOnAction(null);
        UserPokemonType2.setOnAction(null);
        UserPokemonGender.setOnAction(null);
        UserPokemonNature.setOnAction(null);
        UserPokemonAbility.setOnAction(null);
        UserPokemonItem.setOnAction(null);
        UserPokemonStatus.setOnAction(null);
        UserPokemonAttack1.setOnAction(null);
        UserPokemonAttack2.setOnAction(null);
        UserPokemonAttack3.setOnAction(null);
        UserPokemonAttack4.setOnAction(null);
        UserPokemonAttack1Type.setOnAction(null);
        UserPokemonAttack2Type.setOnAction(null);
        UserPokemonAttack3Type.setOnAction(null);
        UserPokemonAttack4Type.setOnAction(null);
        UserPokemonAttack1Category.setOnAction(null);
        UserPokemonAttack2Category.setOnAction(null);
        UserPokemonAttack3Category.setOnAction(null);
        UserPokemonAttack4Category.setOnAction(null);
        // get all the Data
        PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokemon.pokemonStatsId);
        ArrayList<String> pokemonTypes = dbAPI.getTypesFromPokemonStatsId(pokemon.pokemonStatsId);
        Item item = dbAPI.getItemFromItemId(pokemon.itemId);
        ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);
        Nature nature = dbAPI.getNatureFromNatureName(pokemon.natureName);
        Ability ability = dbAPI.getAbilityFromAbilityId(pokemon.abilityId);
        // sets Main
        selecPokemonType(pokemonTypes, UserPokemonType1, UserPokemonType2); // Type
        UserPokemonForm.setValue(pokeStats.nameOfPokemon);
        UserPokemonGender.setValue(pokemon.gender);
        UserPokemonLevel.setText(Integer.toString(pokemon.level));
        // Stats
        setUserBaseIvEvStats(pokemon, pokeStats);
        // Nature / Ability / Item / Status
        Utilities.selectNature(pokemon.natureName, natureList, UserPokemonNature);
        Utilities.selectAbility(pokemon.abilityId, abilityList, UserPokemonAbility);
        if (item != null) Utilities.selectHeldItem(pokemon.itemId, itemList, UserPokemonItem);
        else UserPokemonItem.getSelectionModel().selectFirst();
        UserPokemonStatus.getSelectionModel().selectFirst();
        // select Boosts
        UserPokemonAttackBoost.getSelectionModel().select(6);
        UserPokemonDefenseBoost.getSelectionModel().select(6);
        UserPokemonSpecialAttackBoost.getSelectionModel().select(6);
        UserPokemonSpecialDefenseBoost.getSelectionModel().select(6);
        UserPokemonSpeedBoost.getSelectionModel().select(6);
        // HP
        int hp = Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level);
        UserPokemonCurrentHPFlat.setText(Integer.toString(hp));
        UserPokemonMaxHp.setText("/" + Integer.toString(hp));
        UserPokemonCurrentHPPercentage.setText("100");
        // Moves
        setUserPokemonAttacks(attackList);
        // Calculate Total Stats / HP / Attack / Defense / SpeAttack / SpeDefense / Speed
        UserPokemonHP.setText(Integer.toString(Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level)));
        UserPokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseAttack, pokemon.ivAttack, pokemon.evAttack, pokemon.level, "Attack", attackBadge, attackBadgeBoolean)));
        UserPokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseDefense, pokemon.ivDefense, pokemon.evDefense, pokemon.level, "Defense", defenseBadge, defenseBadgeBoolean)));
        UserPokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialAttack, pokemon.ivSpecialAttack, pokemon.evSpecialAttack, pokemon.level, "SpecialAttack", specialBadge, specialBadgeBoolean)));
        UserPokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialDefense, pokemon.ivSpecialDefense, pokemon.evSpecialDefense, pokemon.level, "SpecialDefense", specialBadge, specialBadgeBoolean)));
        UserPokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpeed, pokemon.ivSpeed, pokemon.evSpeed, pokemon.level, "Speed", speedBadge, speedBadgeBoolean)));
        // Re-insert EventHandler
        UserPokemonType1.setOnAction(userPokemonHandlerType1);
        UserPokemonType2.setOnAction(userPokemonHandlerType2);
        UserPokemonGender.setOnAction(userPokemonHandlerGender);
        UserPokemonNature.setOnAction(userPokemonHandlerNature);
        UserPokemonAbility.setOnAction(userPokemonHandlerAbility);
        UserPokemonItem.setOnAction(userPokemonHandlerItem);
        UserPokemonStatus.setOnAction(userPokemonHandlerStatus);
        UserPokemonAttack1.setOnAction(userPokemonHandlerAttack1);
        UserPokemonAttack2.setOnAction(userPokemonHandlerAttack2);
        UserPokemonAttack3.setOnAction(userPokemonHandlerAttack3);
        UserPokemonAttack4.setOnAction(userPokemonHandlerAttack4);
        UserPokemonAttack1Type.setOnAction(userPokemonHandlerAttackType1);
        UserPokemonAttack2Type.setOnAction(userPokemonHandlerAttackType2);
        UserPokemonAttack3Type.setOnAction(userPokemonHandlerAttackType3);
        UserPokemonAttack4Type.setOnAction(userPokemonHandlerAttackType4);
        UserPokemonAttack1Category.setOnAction(userPokemonHandlerAttackCategory1);
        UserPokemonAttack2Category.setOnAction(userPokemonHandlerAttackCategory2);
        UserPokemonAttack3Category.setOnAction(userPokemonHandlerAttackCategory3);
        UserPokemonAttack4Category.setOnAction(userPokemonHandlerAttackCategory4);
    }

    private void calcAndSetUserPokemonStats() {
        calcUserPokemonHp();
        calcUserPokemonAttack();
        calcUserPokemonDefense();
        calcUserPokemonSpecialAttack();
        calcUserPokemonSpecialDefense();
        calcUserPokemonSpeed();
    }

    private void calcUserPokemonHp() {
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseHp = Integer.parseInt(UserPokemonBaseHP.getText());
        int ivHp = Integer.parseInt(UserPokemonIVHP.getText());
        int evHp = Integer.parseInt(UserPokemonEVHP.getText());
        UserPokemonHP.setText(Integer.toString(Utilities.calculateHpStat(baseHp, ivHp, evHp, level)));
    }

    private void calcUserPokemonAttack() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseAttack = Integer.parseInt(UserPokemonBaseAttack.getText());
        int ivAttack = Integer.parseInt(UserPokemonIVAttack.getText());
        int evAttack = Integer.parseInt(UserPokemonEVAttack.getText());
        UserPokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseAttack, ivAttack, evAttack, level, "Attack", attackBadge, attackBadgeBoolean)));
    }

    private void calcUserPokemonDefense() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseDefense = Integer.parseInt(UserPokemonBaseDefense.getText());
        int ivDefense = Integer.parseInt(UserPokemonIVDefense.getText());
        int evDefense = Integer.parseInt(UserPokemonEVDefense.getText());
        UserPokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseDefense, ivDefense, evDefense, level, "Defense", defenseBadge, defenseBadgeBoolean)));

    }

    private void calcUserPokemonSpecialAttack() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseSpecialAttack = Integer.parseInt(UserPokemonBaseSpecialAttack.getText());
        int ivSpecialAttack = Integer.parseInt(UserPokemonIVSpecialAttack.getText());
        int evSpecialAttack = Integer.parseInt(UserPokemonEVSpecialAttack.getText());
        UserPokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialAttack, ivSpecialAttack, evSpecialAttack, level, "SpecialAttack", specialBadge, specialBadgeBoolean)));
    }

    private void calcUserPokemonSpecialDefense() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseSpecialDefense = Integer.parseInt(UserPokemonBaseSpecialDefense.getText());
        int ivSpecialDefense = Integer.parseInt(UserPokemonIVSpecialDefense.getText());
        int evSpecialDefense = Integer.parseInt(UserPokemonEVSpecialDefense.getText());
        UserPokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialDefense, ivSpecialDefense, evSpecialDefense, level, "SpecialDefense", specialBadge, specialBadgeBoolean)));
    }

    private void calcUserPokemonSpeed() {
        Nature n = UserPokemonNature.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        int baseSpeed = Integer.parseInt(UserPokemonBaseSpeed.getText());
        int ivSpeed = Integer.parseInt(UserPokemonIVSpeed.getText());
        int evSpeed = Integer.parseInt(UserPokemonEVSpeed.getText());
        UserPokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpeed, ivSpeed, evSpeed, level, "Speed", speedBadge, speedBadgeBoolean)));
    }

    private void selecPokemonType(ArrayList<String> pokemonTypes, ComboBox<String> type1, ComboBox<String> type2) {
        for (int i = 0; i < pokemonTypes.size(); i++) {
            if (i == 0) type1.setValue(pokemonTypes.get(0));
            else type2.setValue(pokemonTypes.get(1));
        }

        for (int i = pokemonTypes.size(); i < 2; i++) {
            if (i == 0) type1.getSelectionModel().selectFirst();
            else type2.getSelectionModel().selectFirst();
        }
    }

    private void setUserBaseIvEvStats(Pokemon pokemon, PokeStats pokeStats) {
        setUserPokemonBaseStats(pokeStats);
        setUserPokemonIvStats(pokemon);
        setUserPokemonEvStats(pokemon);
    }

    private void setUserPokemonBaseStats(PokeStats pokeStats) {
        UserPokemonBaseHP.setText(Integer.toString(pokeStats.baseHp));
        UserPokemonBaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        UserPokemonBaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        UserPokemonBaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        UserPokemonBaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        UserPokemonBaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    private void setUserPokemonIvStats(Pokemon pokemon) {
        UserPokemonIVHP.setText(Integer.toString(pokemon.ivHp));
        UserPokemonIVAttack.setText(Integer.toString(pokemon.ivAttack));
        UserPokemonIVDefense.setText(Integer.toString(pokemon.ivDefense));
        UserPokemonIVSpecialAttack.setText(Integer.toString(pokemon.ivSpecialAttack));
        UserPokemonIVSpecialDefense.setText(Integer.toString(pokemon.ivSpecialDefense));
        UserPokemonIVSpeed.setText(Integer.toString(pokemon.ivSpeed));
    }

    private void setUserPokemonEvStats(Pokemon pokemon) {
        UserPokemonEVHP.setText(Integer.toString(pokemon.evHp));
        UserPokemonEVAttack.setText(Integer.toString(pokemon.evAttack));
        UserPokemonEVDefense.setText(Integer.toString(pokemon.evDefense));
        UserPokemonEVSpecialAttack.setText(Integer.toString(pokemon.evSpecialAttack));
        UserPokemonEVSpecialDefense.setText(Integer.toString(pokemon.evSpecialDefense));
        UserPokemonEVSpeed.setText(Integer.toString(pokemon.evSpeed));
    }

    private void setUserPokemonAttacks(ObservableList<PokemonAttack> at) {
        for (int i = 0; i < at.size(); i++) {
            Attack attack = dbAPI.getAttackFromAttackId(at.get(i).attackId);
            switch(i) {
                case 0 -> {
                    Utilities.selectMove(attack.attackId, attackList, UserPokemonAttack1);
                    userMove1Attack.setText(attack.attackName);
                    setUserAttackValues(1,  attack);
                    if (attack.attackId > 0) userMove1Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
                case 1 -> {
                    Utilities.selectMove(attack.attackId, attackList, UserPokemonAttack2);
                    userMove2Attack.setText(attack.attackName);
                    setUserAttackValues(2,  attack);
                    if (attack.attackId > 0) userMove2Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
                case 2 -> {
                    Utilities.selectMove(attack.attackId, attackList, UserPokemonAttack3);
                    userMove3Attack.setText(attack.attackName);
                    setUserAttackValues(3,  attack);
                    if (attack.attackId > 0) userMove3Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
                case 3 -> {
                    Utilities.selectMove(attack.attackId, attackList, UserPokemonAttack4);
                    userMove4Attack.setText(attack.attackName);
                    setUserAttackValues(4,  attack);
                    if (attack.attackId > 0) userMove4Attack.setText(attack.attackName);
                    else userMove1Attack.setText("No Move");
                }
            }
        }

        // set the rest of the attack to null
        for (int i = at.size(); i < 4; i++) {
            switch(i) {
                case 0 -> {
                    UserPokemonAttack1.getSelectionModel().selectFirst();
                    userMove1Attack.setText("No Move");
                    setUserAttackValues(1,  attackList.get(0));
                }
                case 1 -> {
                    UserPokemonAttack2.getSelectionModel().selectFirst();
                    userMove2Attack.setText("No Move");
                    setUserAttackValues(2,  attackList.get(0));
                }
                case 2 -> {
                    UserPokemonAttack3.getSelectionModel().selectFirst();
                    userMove3Attack.setText("No Move");
                    setUserAttackValues(3,  attackList.get(0));
                }
                case 3 -> {
                    UserPokemonAttack4.getSelectionModel().selectFirst();
                    userMove4Attack.setText("No Move");
                    setUserAttackValues(4,  attackList.get(0));
                }
            }
        }
    }

    private void setUserAttackValues(int index, Attack attack) {
        switch (index) {
            case 1 -> {
                UserPokemonAttack1Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack1Type.setValue(attack.typeName);
                UserPokemonAttack1Category.setValue(attack.category);
            }
            case 2 -> {
                UserPokemonAttack2Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack2Type.setValue(attack.typeName);
                UserPokemonAttack2Category.setValue(attack.category);
            }
            case 3 -> {
                UserPokemonAttack3Damage.setText(Integer.toString(attack.power));
                UserPokemonAttack3Type.setValue(attack.typeName);
                UserPokemonAttack3Category.setValue(attack.category);
            }
             case 4 -> {
                 UserPokemonAttack4Damage.setText(Integer.toString(attack.power));
                 UserPokemonAttack4Type.setValue(attack.typeName);
                 UserPokemonAttack4Category.setValue(attack.category);
             }
        }
    }

    private void setTrainerAttackValues(int index, Attack attack) {
        switch (index) {
            case 1 -> {
                FoePokemonAttack1Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack1Type.setValue(attack.typeName);
                FoePokemonAttack1Category.setValue(attack.category);
            }
            case 2 -> {
                FoePokemonAttack2Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack2Type.setValue(attack.typeName);
                FoePokemonAttack2Category.setValue(attack.category);
            }
            case 3 -> {
                FoePokemonAttack3Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack3Type.setValue(attack.typeName);
                FoePokemonAttack3Category.setValue(attack.category);
            }
            case 4 -> {
                FoePokemonAttack4Damage.setText(Integer.toString(attack.power));
                FoePokemonAttack4Type.setValue(attack.typeName);
                FoePokemonAttack4Category.setValue(attack.category);
            }
        }
    }

    // set trainerPokemonStats
    private void setTrainerPokemonStats(Pokemon pokemon) {
        // remove EventHandler otherwise they will triggered when you select Pokemon
        // get the handles to add them later again
        EventHandler<ActionEvent> trainerPokemonHandlerType1 = FoePokemonType1.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerType2 = FoePokemonType2.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerGender = FoePokemonGender.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerNature = FoePokemonNature.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAbility = FoePokemonAbility.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerItem = FoePokemonItem.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerStatus = FoePokemonStatus.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack1 = FoePokemonAttack1.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack2 = FoePokemonAttack2.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack3 = FoePokemonAttack3.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttack4 = FoePokemonAttack4.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType1 = FoePokemonAttack1Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType2 = FoePokemonAttack2Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType3 = FoePokemonAttack3Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackType4 = FoePokemonAttack4Type.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory1 = FoePokemonAttack1Category.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory2 = FoePokemonAttack2Category.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory3 = FoePokemonAttack3Category.getOnAction();
        EventHandler<ActionEvent> trainerPokemonHandlerAttackCategory4 = FoePokemonAttack4Category.getOnAction();
        // remove onAction
        FoePokemonType1.setOnAction(null);
        FoePokemonType2.setOnAction(null);
        FoePokemonGender.setOnAction(null);
        FoePokemonNature.setOnAction(null);
        FoePokemonAbility.setOnAction(null);
        FoePokemonItem.setOnAction(null);
        FoePokemonStatus.setOnAction(null);
        FoePokemonAttack1.setOnAction(null);
        FoePokemonAttack2.setOnAction(null);
        FoePokemonAttack3.setOnAction(null);
        FoePokemonAttack4.setOnAction(null);
        FoePokemonAttack1Type.setOnAction(null);
        FoePokemonAttack2Type.setOnAction(null);
        FoePokemonAttack3Type.setOnAction(null);
        FoePokemonAttack4Type.setOnAction(null);
        FoePokemonAttack1Category.setOnAction(null);
        FoePokemonAttack2Category.setOnAction(null);
        FoePokemonAttack3Category.setOnAction(null);
        FoePokemonAttack4Category.setOnAction(null);
        // get all the Data
        PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(pokemon.pokemonStatsId);
        ArrayList<String> pokemonTypes = dbAPI.getTypesFromPokemonStatsId(pokemon.pokemonStatsId);
        Item item = dbAPI.getItemFromItemId(pokemon.itemId);
        ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);
        Nature nature = dbAPI.getNatureFromNatureName(pokemon.natureName);
        // sets Main
        selecPokemonType(pokemonTypes, FoePokemonType1, FoePokemonType2); // Type
        FoePokemonForm.setValue(pokeStats.nameOfPokemon);
        FoePokemonGender.setValue(pokemon.gender);
        FoePokemonLevel.setText(Integer.toString(pokemon.level));
        // Stats
        setFoeBaseIvEvStats(pokemon, pokeStats);
        // Nature / Ability / Item / Status
        Utilities.selectNature(pokemon.natureName, natureList, FoePokemonNature);
        Utilities.selectAbility(pokemon.abilityId, abilityList, FoePokemonAbility);
        if (item != null) Utilities.selectHeldItem(pokemon.itemId, itemList, FoePokemonItem);
        else FoePokemonItem.getSelectionModel().selectFirst();
        FoePokemonStatus.getSelectionModel().selectFirst();
        // select Boosts
        FoePokemonAttackBoost.getSelectionModel().select(6);
        FoePokemonDefenseBoost.getSelectionModel().select(6);
        FoePokemonSpecialAttackBoost.getSelectionModel().select(6);
        FoePokemonSpecialDefenseBoost.getSelectionModel().select(6);
        FoePokemonSpeedBoost.getSelectionModel().select(6);
        // HP
        int hp = Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level);
        FoePokemonCurrentHPFlat.setText(Integer.toString(hp));
        FoePokemonMaxHp.setText("/" + Integer.toString(hp));
        FoePokemonCurrentHPPercentage.setText("100");
        // Moves
        setFoePokemonAttacks(attackList);
        // Calculate Total Stats / HP / Attack / Defense / SpeAttack / SpeDefense / Speed
        FoePokemonHP.setText(Integer.toString(Utilities.calculateHpStat(pokeStats.baseHp, pokemon.ivHp, pokemon.evHp, pokemon.level)));
        FoePokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseAttack, pokemon.ivAttack, pokemon.evAttack, pokemon.level, "Attack", attackBadge, false)));
        FoePokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseDefense, pokemon.ivDefense, pokemon.evDefense, pokemon.level, "Defense", defenseBadge, false)));
        FoePokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialAttack, pokemon.ivSpecialAttack, pokemon.evSpecialAttack, pokemon.level, "SpecialAttack", specialBadge, false)));
        FoePokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpecialDefense, pokemon.ivSpecialDefense, pokemon.evSpecialDefense, pokemon.level, "SpecialDefense", specialBadge, false)));
        FoePokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(nature, pokeStats.baseSpeed, pokemon.ivSpeed, pokemon.evSpeed, pokemon.level, "Speed", speedBadge, false)));
        // Re-insert EventHandler
        FoePokemonType1.setOnAction(trainerPokemonHandlerType1);
        FoePokemonType2.setOnAction(trainerPokemonHandlerType2);
        FoePokemonGender.setOnAction(trainerPokemonHandlerGender);
        FoePokemonNature.setOnAction(trainerPokemonHandlerNature);
        FoePokemonAbility.setOnAction(trainerPokemonHandlerAbility);
        FoePokemonItem.setOnAction(trainerPokemonHandlerItem);
        FoePokemonStatus.setOnAction(trainerPokemonHandlerStatus);
        FoePokemonAttack1.setOnAction(trainerPokemonHandlerAttack1);
        FoePokemonAttack2.setOnAction(trainerPokemonHandlerAttack2);
        FoePokemonAttack3.setOnAction(trainerPokemonHandlerAttack3);
        FoePokemonAttack4.setOnAction(trainerPokemonHandlerAttack4);
        FoePokemonAttack1Type.setOnAction(trainerPokemonHandlerAttackType1);
        FoePokemonAttack2Type.setOnAction(trainerPokemonHandlerAttackType2);
        FoePokemonAttack3Type.setOnAction(trainerPokemonHandlerAttackType3);
        FoePokemonAttack4Type.setOnAction(trainerPokemonHandlerAttackType4);
        FoePokemonAttack1Category.setOnAction(trainerPokemonHandlerAttackCategory1);
        FoePokemonAttack2Category.setOnAction(trainerPokemonHandlerAttackCategory2);
        FoePokemonAttack3Category.setOnAction(trainerPokemonHandlerAttackCategory3);
        FoePokemonAttack4Category.setOnAction(trainerPokemonHandlerAttackCategory4);
    }

    private void calcAndSetTrainerPokemonStats() {
        calcTrainerPokemonHp();
        calcTrainerPokemonAttack();
        calcTrainerPokemonDefense();
        calcTrainerPokemonSpecialAttack();
        calcTrainerPokemonSpecialDefense();
        calcTrainerPokemonSpeed();
    }

    private void calcTrainerPokemonAttack() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseAttack = Integer.parseInt(FoePokemonBaseAttack.getText());
        int ivAttack = Integer.parseInt(FoePokemonIVAttack.getText());
        int evAttack = Integer.parseInt(FoePokemonEVAttack.getText());
        FoePokemonAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseAttack, ivAttack, evAttack, level, "Attack", attackBadge, attackBadgeBoolean)));
    }

    private void calcTrainerPokemonDefense() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseDefense = Integer.parseInt(FoePokemonBaseDefense.getText());
        int ivDefense = Integer.parseInt(FoePokemonIVDefense.getText());
        int evDefense = Integer.parseInt(FoePokemonEVDefense.getText());
        FoePokemonDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseDefense, ivDefense, evDefense, level, "Defense", defenseBadge, defenseBadgeBoolean)));

    }

    private void calcTrainerPokemonSpecialAttack() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseSpecialAttack = Integer.parseInt(FoePokemonBaseSpecialAttack.getText());
        int ivSpecialAttack = Integer.parseInt(FoePokemonIVSpecialAttack.getText());
        int evSpecialAttack = Integer.parseInt(FoePokemonEVSpecialAttack.getText());
        FoePokemonSpecialAttack.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialAttack, ivSpecialAttack, evSpecialAttack, level, "SpecialAttack", specialBadge, specialBadgeBoolean)));
    }

    private void calcTrainerPokemonSpecialDefense() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseSpecialDefense = Integer.parseInt(FoePokemonBaseSpecialDefense.getText());
        int ivSpecialDefense = Integer.parseInt(FoePokemonIVSpecialDefense.getText());
        int evSpecialDefense = Integer.parseInt(FoePokemonEVSpecialDefense.getText());
        FoePokemonSpecialDefense.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpecialDefense, ivSpecialDefense, evSpecialDefense, level, "SpecialDefense", specialBadge, specialBadgeBoolean)));
    }

    private void calcTrainerPokemonSpeed() {
        Nature n = FoePokemonNature.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseSpeed = Integer.parseInt(FoePokemonBaseSpeed.getText());
        int ivSpeed = Integer.parseInt(FoePokemonIVSpeed.getText());
        int evSpeed = Integer.parseInt(FoePokemonEVSpeed.getText());
        FoePokemonSpeed.setText(Integer.toString(Utilities.calculateStatWithBadge(n, baseSpeed, ivSpeed, evSpeed, level, "Speed", speedBadge, speedBadgeBoolean)));
    }

    private void calcTrainerPokemonHp() {
        int level = Integer.parseInt(FoePokemonLevel.getText());
        int baseHp = Integer.parseInt(FoePokemonBaseHP.getText());
        int ivHp = Integer.parseInt(FoePokemonIVHP.getText());
        int evHp = Integer.parseInt(FoePokemonEVHP.getText());
        FoePokemonHP.setText(Integer.toString(Utilities.calculateHpStat(baseHp, ivHp, evHp, level)));
    }

    private void setFoeBaseIvEvStats(Pokemon pokemon, PokeStats pokeStats) {
        setFoePokemonBaseStats(pokeStats);
        setFoePokemonIvStats(pokemon);
        setFoePokemonEvStats(pokemon);
    }

    private void setFoePokemonBaseStats(PokeStats pokeStats) {
        FoePokemonBaseHP.setText(Integer.toString(pokeStats.baseHp));
        FoePokemonBaseAttack.setText(Integer.toString(pokeStats.baseAttack));
        FoePokemonBaseDefense.setText(Integer.toString(pokeStats.baseDefense));
        FoePokemonBaseSpecialAttack.setText(Integer.toString(pokeStats.baseSpecialAttack));
        FoePokemonBaseSpecialDefense.setText(Integer.toString(pokeStats.baseSpecialDefense));
        FoePokemonBaseSpeed.setText(Integer.toString(pokeStats.baseSpeed));
    }

    private void setFoePokemonIvStats(Pokemon pokemon) {
        FoePokemonIVHP.setText(Integer.toString(pokemon.ivHp));
        FoePokemonIVAttack.setText(Integer.toString(pokemon.ivAttack));
        FoePokemonIVDefense.setText(Integer.toString(pokemon.ivDefense));
        FoePokemonIVSpecialAttack.setText(Integer.toString(pokemon.ivSpecialAttack));
        FoePokemonIVSpecialDefense.setText(Integer.toString(pokemon.ivSpecialDefense));
        FoePokemonIVSpeed.setText(Integer.toString(pokemon.ivSpeed));
    }

    private void setFoePokemonEvStats(Pokemon pokemon) {
        FoePokemonEVHP.setText(Integer.toString(pokemon.evHp));
        FoePokemonEVAttack.setText(Integer.toString(pokemon.evAttack));
        FoePokemonEVDefense.setText(Integer.toString(pokemon.evDefense));
        FoePokemonEVSpecialAttack.setText(Integer.toString(pokemon.evSpecialAttack));
        FoePokemonEVSpecialDefense.setText(Integer.toString(pokemon.evSpecialDefense));
        FoePokemonEVSpeed.setText(Integer.toString(pokemon.evSpeed));
    }

    private void setFoePokemonAttacks(ObservableList<PokemonAttack> at) {
        for (int i = 0; i < at.size(); i++) {
            Attack attack = dbAPI.getAttackFromAttackId(at.get(i).attackId);
            switch(i) {
                case 0 -> {
                    Utilities.selectMove(attack.attackId, attackList, FoePokemonAttack1);
                    foeMove1Attack.setText(attack.attackName);
                    setTrainerAttackValues(1,  attack);
                }
                case 1 -> {
                    Utilities.selectMove(attack.attackId, attackList, FoePokemonAttack2);
                    foeMove2Attack.setText(attack.attackName);
                    setTrainerAttackValues(2,  attack);
                }
                case 2 -> {
                    Utilities.selectMove(attack.attackId, attackList, FoePokemonAttack3);
                    foeMove3Attack.setText(attack.attackName);
                    setTrainerAttackValues(3,  attack);
                }
                case 3 -> {
                    Utilities.selectMove(attack.attackId, attackList, FoePokemonAttack4);
                    foeMove4Attack.setText(attack.attackName);
                    setTrainerAttackValues(4,  attack);
                }
            }
        }

        // set the rest of the attack to null
        for (int i = at.size(); i < 4; i++) {
            switch(i) {
                case 0 -> {
                    FoePokemonAttack1.getSelectionModel().selectFirst();
                    foeMove1Attack.setText("No Move");
                    setTrainerAttackValues(1,  attackList.get(0));
                }
                case 1 -> {
                    FoePokemonAttack2.getSelectionModel().selectFirst();
                    foeMove2Attack.setText("No Move");
                    setTrainerAttackValues(2,  attackList.get(0));
                }
                case 2 -> {
                    FoePokemonAttack3.getSelectionModel().selectFirst();
                    foeMove3Attack.setText("No Move");
                    setTrainerAttackValues(3,  attackList.get(0));
                }
                case 3 -> {
                    FoePokemonAttack4.getSelectionModel().selectFirst();
                    foeMove4Attack.setText("No Move");
                    setTrainerAttackValues(4,  attackList.get(0));
                }
            }
        }
    }

    // load Dynamic Content
    private void loadContent() {
        dbAPI = new API.Database();
        data = Data.dataSingleton.getInstance();
        showThatTheFirstSlotForUserPokemonIsSelected();
        loadGlobalData(); // Types / Gender / Nature / Ability / Item / Status / Attacks
        loadUserPokemons();
        loadTrainer();
        previousTrainerPokemonBox = foePokemon1;
        selectTrainer(0);
    }

    private void loadGlobalData() {
        // Badges
        ArrayList<Badge> badgeList = dbAPI.getAllBadgesFromGame(data.getGameName());
        attackBadge = findBadgeViaStatBoost(badgeList, "Attack");
        attackBadgeBoolean = attackBadge.ownBadge;
        defenseBadge = findBadgeViaStatBoost(badgeList, "Defense");
        defenseBadgeBoolean = defenseBadge.ownBadge;
        specialBadge = findBadgeViaStatBoost(badgeList, "Special");
        specialBadgeBoolean = specialBadge.ownBadge;
        speedBadge = findBadgeViaStatBoost(badgeList, "Speed");
        speedBadgeBoolean = speedBadge.ownBadge;
        // Attacks
        attackList = FXCollections.observableArrayList();
        attackList.add(new Attack(-1, "", "", "", 0,0,"","",0,"","",false,false,false,false,false,false));
        attackList.addAll(dbAPI.getAllAttacksFromGame(data.getGameName()));
        // Types
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("");
        types.addAll(dbAPI.getAllTypeFromSpecificGame(data.getGameName()));
        UserPokemonType1.setItems(types);
        UserPokemonType2.setItems(types);
        FoePokemonType1.setItems(types);
        FoePokemonType2.setItems(types);
        // Gender
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "None");
        UserPokemonGender.setItems(genders);
        FoePokemonGender.setItems(genders);
        // Boosts
        ObservableList<String> boosts = FXCollections.observableArrayList("+6", "+5", "+4", "+3", "+2", "+1", "--", "-1", "-2", "-3", "-4", "-5", "-6");
        UserPokemonAttackBoost.setItems(boosts);
        UserPokemonDefenseBoost.setItems(boosts);
        UserPokemonSpecialAttackBoost.setItems(boosts);
        UserPokemonSpecialDefenseBoost.setItems(boosts);
        UserPokemonSpeedBoost.setItems(boosts);
        FoePokemonAttackBoost.setItems(boosts);
        FoePokemonDefenseBoost.setItems(boosts);
        FoePokemonSpecialAttackBoost.setItems(boosts);
        FoePokemonSpecialDefenseBoost.setItems(boosts);
        FoePokemonSpeedBoost.setItems(boosts);
        // Nature
        natureList = dbAPI.getAllNature();
        UserPokemonNature.setItems(natureList);
        FoePokemonNature.setItems(natureList);
        // Abilities
        abilityList = dbAPI.getAllAbilitiesFromGame(data.getGameName());
        UserPokemonAbility.setItems(abilityList);
        FoePokemonAbility.setItems(abilityList);
        // Item
        itemList = FXCollections.observableArrayList();
        itemList.add(new Item(-1, -1, null, null, 0, 0, 0));
        itemList.addAll(dbAPI.getAllItemsFromGame(data.getGameName()));
        UserPokemonItem.setItems(itemList);
        FoePokemonItem.setItems(itemList);
        // Status
        ObservableList<String> status = FXCollections.observableArrayList("Healthy", "Poisoned", "Badly Poisoned", "Burned", "Paralyzed", "Asleep", "Frozen");
        UserPokemonStatus.setItems(status);
        FoePokemonStatus.setItems(status);
        // Attacks
        setGlobalPokemonAttack(UserPokemonAttack1, UserPokemonAttack1Type, UserPokemonAttack1Category, attackList, types);
        setGlobalPokemonAttack(UserPokemonAttack2, UserPokemonAttack2Type, UserPokemonAttack2Category, attackList, types);
        setGlobalPokemonAttack(UserPokemonAttack3, UserPokemonAttack3Type, UserPokemonAttack3Category, attackList, types);
        setGlobalPokemonAttack(UserPokemonAttack4, UserPokemonAttack4Type, UserPokemonAttack4Category, attackList, types);
        setGlobalPokemonAttack(FoePokemonAttack1, FoePokemonAttack1Type, FoePokemonAttack1Category, attackList, types);
        setGlobalPokemonAttack(FoePokemonAttack2, FoePokemonAttack2Type, FoePokemonAttack2Category, attackList, types);
        setGlobalPokemonAttack(FoePokemonAttack3, FoePokemonAttack3Type, FoePokemonAttack3Category, attackList, types);
        setGlobalPokemonAttack(FoePokemonAttack4, FoePokemonAttack4Type, FoePokemonAttack4Category, attackList, types);
    }

    private Badge findBadgeViaStatBoost(ArrayList<Badge> badgeList, String stat) {
        for (Badge badge : badgeList) {
            if (badge.statBoost != null && badge.statBoost.equals(stat)) {
                return badge;
            }
        }
        return new Badge(0, 0, "", "", 0, false, "", 0, 0 );
    }

    private void setGlobalPokemonAttack(ComboBox<Attack> userPokemonAttack, ComboBox<String> userPokemonAttackType, ComboBox<String> UserPokemonAttackCategory, ObservableList<Attack> attacks, ObservableList<String> types) {
        userPokemonAttack.setItems(attacks);
        userPokemonAttackType.setItems(types);

        ObservableList<String> category = FXCollections.observableArrayList("Physical", "Special", "Status");
        UserPokemonAttackCategory.setItems(category);
    }

    // loadUserPokemons
    private void showThatTheFirstSlotForUserPokemonIsSelected() {
        activeUserPokemonIndex = 1;
        previousUserPokemonBox = userPokemon1;
        userPokemon1.getStyleClass().add("pokeballActiveBox");
    }

    private void loadUserPokemons() {
        ArrayList<PokemonList> pokemonList = dbAPI.getAllPokemonFromUser(data.getGameName());
        for (PokemonList poke : pokemonList) {
            createPokemonImage(poke);
        }
    }

    private void createPokemonImage(PokemonList poke) {
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(poke.pokemonStatsId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);

        // Pokemon Image
        Label pokemonImage = new Label("");
        pokemonImage.getStyleClass().add("calcPokemonBorder");
        pokemonImage.setStyle("-fx-background-image: url(" + spritePath + ");");
        // Tooltip when hover over Pokemon
        Tooltip tooltip = createTooltipForPokemon(poke);
        pokemonImage.setTooltip(tooltip);
        // add EventListener
        pokemonImage.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Pokemon tempPokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            // set Pokemon in the selected User Pokemon
            setPokemonToTheUser(poke);
            // set Values
            setUserPokemonStats(tempPokemon);
            //
            activeUserPokemon = tempPokemon;
            // calculate damage
            calculateDamage();
        });

        // add To Controller
        UserPokemons.getChildren().add(pokemonImage);
    }

    private Tooltip createTooltipForPokemon(PokemonList poke) {
        try {
            Pokemon pokemon = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
            PokeStats pokeStats = dbAPI.getPokeStatsFromPokemonStatsId(poke.pokemonStatsId);
            Ability ability = dbAPI.getAbilityFromAbilityId(pokemon.abilityId);
            // Item
            Item item = dbAPI.getItemFromItemId(pokemon.itemId);
            if (item == null) item = new Item(0, 0, "", "", 0, 0,0);
            // Attacks
            String attackString = "";
            ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(poke.pokemonId);
            for (PokemonAttack att: attackList) {
                Attack attack = dbAPI.getAttackFromAttackId(att.attackId);
                attackString += "Attack: " + attack.attackName + "\n";
            }

            String tooltip = "Species: " + pokeStats.nameOfPokemon + "\n" +
                    "Nickname: " + poke.nickname + "\n" +
                    "Level: " + pokemon.level + "\n" +
                    "Nature: " + pokemon.natureName + "\n" +
                    "Ability: " + ability.abilityName + "\n" +
                    "Item: " + item.nameOfItem + "\n" +
                    attackString;

            return new Tooltip(tooltip);
        } catch (Exception e) {
            System.out.println("Couldn't make tooltip for pokemon: " + poke.pokemonId + ". Error: " + e);
            return new Tooltip("");
        }
    }

    private void setPokemonToTheUser(PokemonList poke) {
        switch (activeUserPokemonIndex) {
            case 1 -> {
                pokemon1 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                updatePokemonSprite(userPokemon1, poke);
            }
            case 2 -> {
                pokemon2 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                updatePokemonSprite(userPokemon2, poke);
            }
            case 3 -> {
                pokemon3 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                updatePokemonSprite(userPokemon3, poke);
            }
            case 4 -> {
                pokemon4 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                updatePokemonSprite(userPokemon4, poke);
            }
            case 5 -> {
                pokemon5 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                updatePokemonSprite(userPokemon5, poke);
            }
            case 6 -> {
                pokemon6 = dbAPI.getPokemonFromPokemonId(poke.pokemonId);
                updatePokemonSprite(userPokemon6, poke);
            }
        }
    }

    private void updatePokemonSprite(Label label, PokemonList poke) {
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(poke.pokemonStatsId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        label.setStyle("-fx-background-image: url("+ spritePath +");");
    }

    // load Trainer
    private void loadTrainer() {
        trainerList = dbAPI.getAllTrainerFromSpecificGame(data.getGameName());
        for (int i = 0; i < trainerList.size(); i++) {
            createTrainerImage(trainerList.get(i), i);
        }

    }

    private void createTrainerImage(Trainer trainer, int index) {
        int spriteId = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        int spriteIdTrainer = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);
        // Sprite
        String spritePathTrainer = dbAPI.getImagePathFromSpriteID(spriteIdTrainer);
        if (spritePathTrainer == null) spritePathTrainer = "/Img/Trainers/Brendan_Small_E.png";
        String finalSpritePathTrainer = spritePathTrainer;

        VBox trainerBox = new VBox(2);
        trainerBox.setAlignment(Pos.CENTER);
        trainerBox.getStyleClass().add("calcTrainerBorder");
        // Trainer Image
        Label trainerImage = new Label();
        int size = 20;
        trainerImage.setPrefWidth(size);trainerImage.setPrefHeight(size);
        trainerImage.getStyleClass().add("calcTrainerImage");
        trainerImage.setStyle("-fx-background-image: url(" + spritePath + ");");
        // FightNumber
        Label trainerFightNumber = new Label(Integer.toString(trainer.fightNumber));

        // Tooltip when hover over Trainer
        Tooltip tooltip = createTooltipForTrainer(trainer);
        Tooltip.install(trainerBox, tooltip);

        // add EventListener

        trainerBox.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            trainerIndex = index; // which trainer is on the line
            ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);
            try {
                Pokemon tempPokemon = pokemonList.get(0);
                setTrainersFirstPokemon(tempPokemon);  // set Values
                activeTrainerPokemon = tempPokemon;  // set this Pokemon as active
                calculateDamage();
            }
            catch (Exception e) { System.out.println("Trainer has no Pokemons. Error: " + e); }
             // set Pokemon in the selected User Pokemon
            FoeTrainerSprite.setImage( new Image(finalSpritePathTrainer)); // set Trainer Sprite
            setPokemonToTrainer(pokemonList);
        });

        // add To Controller
        trainerBox.getChildren().addAll(trainerImage, trainerFightNumber);
        Trainers.getChildren().add(trainerBox);
    }

    private Tooltip createTooltipForTrainer(Trainer trainer) {
        try {
            ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);
            Route route = dbAPI.getRouteFromRouteId(trainer.routeId);

            String pokemonString = "";
            for (Pokemon pokemon: pokemonList) {
                PokeStats poke = dbAPI.getPokeStatsFromPokemonStatsId(pokemon.pokemonStatsId);
                pokemonString += "Pokemon: " + poke.nameOfPokemon + "\n";
            }

            String tooltip = "Trainer: " + trainer.trainerName + "\n" +
                    "Route: " + route.routeName + "\n" +
                    pokemonString;

            return new Tooltip(tooltip);
        } catch (Exception e) {
            System.out.println("Couldn't make tooltip for pokemon: " + trainer.trainerId + ". Error: " + e);
            return new Tooltip("");
        }
    }

    private void setPokemonToTrainer(ArrayList<Pokemon> pokemonList) {
        for (int i = 0; i < pokemonList.size(); i++) {
            Pokemon pokemon = pokemonList.get(i);
            switch(i) {
                case 0 -> {
                    trainerPokemon1 = pokemon;
                    updatePokemonSprite(foePokemon1, pokemon);
                }
                case 1 -> {
                    trainerPokemon2 = pokemon;
                    updatePokemonSprite(foePokemon2, pokemon);
                }
                case 2 -> {
                    trainerPokemon3 = pokemon;
                    updatePokemonSprite(foePokemon3, pokemon);
                }
                case 3 -> {
                    trainerPokemon4 = pokemon;
                    updatePokemonSprite(foePokemon4, pokemon);
                }
                case 4 -> {
                    trainerPokemon5 = pokemon;
                    updatePokemonSprite(foePokemon5, pokemon);
                }
                case 5 -> {
                    trainerPokemon6 = pokemon;
                    updatePokemonSprite(foePokemon6, pokemon);
                }
            }
        }

        // fill the rest with Pokeballs
        for (int i = pokemonList.size(); i < 6; i++) {
            switch(i) {
                case 0 -> {
                    trainerPokemon1 = null;
                    foePokemon1.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
                }
                case 1 -> {
                    trainerPokemon2 = null;
                    foePokemon2.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
                }
                case 2 -> {
                    trainerPokemon3 = null;
                    foePokemon3.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
                }
                case 3 -> {
                    trainerPokemon4 = null;
                    foePokemon4.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
                }
                case 4 -> {
                    trainerPokemon5 = null;
                    foePokemon5.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
                }
                case 5 -> {
                    trainerPokemon6 = null;
                    foePokemon6.setStyle("-fx-background-image: url(/Img/Pokeballs/000.png);");
                }
            }
        }
    }

    private void updatePokemonSprite(Label label, Pokemon poke) {
        int spriteId = dbAPI.getSpriteIdFromSpecificPokemon(poke.pokemonStatsId);
        String spritePath = dbAPI.getImagePathFromSpriteID(spriteId);
        label.setStyle("-fx-background-image: url("+ spritePath +");");
    }

    private void setTrainersFirstPokemon(Pokemon pokemon) {
        showThatTheFirstTrainerPokemonIsSelected();
        setTrainerPokemonStats(pokemon);
    }

    private void showThatTheFirstTrainerPokemonIsSelected() {
        activeTrainerPokemonIndex = 1;
        previousTrainerPokemonBox.getStyleClass().removeIf(style -> style.equals("pokeballActiveBox"));
        previousTrainerPokemonBox = foePokemon1;
        foePokemon1.getStyleClass().add("pokeballActiveBox");
    }

    // Select Trainer
    private void selectTrainer(int index) {
        try {
            Trainer trainer = trainerList.get(index);
            ArrayList<Pokemon> pokemonList = dbAPI.getAllPokemonFromTrainer(trainer.trainerId);
            // Set Trainer Image
            int spriteIdTrainer = dbAPI.getSpriteIdFromSpecificTrainer(trainer.trainerId);
            String spritePathTrainer = dbAPI.getImagePathFromSpriteID(spriteIdTrainer);
            FoeTrainerSprite.setImage( new Image(spritePathTrainer));

            // set First Pokemon
            try {
                Pokemon tempPokemon = pokemonList.get(0);
                setTrainersFirstPokemon(tempPokemon);  // set Values
                activeTrainerPokemon = tempPokemon;  // set this Pokemon as active
                setTrainerMovesManually(tempPokemon); // Event Handler is not active
                calculateDamage();
            }
            catch (Exception e) {
                System.out.println("Trainer has no Pokemons. Error: " + e);
//                e.printStackTrace();
            }

            setPokemonToTrainer(pokemonList);
        } catch (Exception e) {
            System.out.println("There isn't a Trainer to select. Error: " + e);
//            e.printStackTrace();
        }
    }

    private void setTrainerMovesManually(Pokemon pokemon) {
        ObservableList<PokemonAttack> attackList = dbAPI.getAttacksFromASpecificPokemon(pokemon.pokemonId);

        for (int i = 0; i < attackList.size(); i++) {
            PokemonAttack pkAttack = attackList.get(i);
            Attack attack = dbAPI.getAttackFromAttackId(pkAttack.attackId);
            switch (i) {
                case 0 -> {
                    FoePokemonAttack1Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack1Type.setValue(attack.typeName);
                    FoePokemonAttack1Category.setValue(attack.category);
                }
                case 1 -> {
                    FoePokemonAttack2Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack2Type.setValue(attack.typeName);
                    FoePokemonAttack2Category.setValue(attack.category);
                }
                case 2 -> {
                    FoePokemonAttack3Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack3Type.setValue(attack.typeName);
                    FoePokemonAttack3Category.setValue(attack.category);
                }
                case 3 -> {
                    FoePokemonAttack4Damage.setText(Integer.toString(attack.power));
                    FoePokemonAttack4Type.setValue(attack.typeName);
                    FoePokemonAttack4Category.setValue(attack.category);
                }
            }
        }
    }

    // Damage Calculation
    private void calculateDamage() {
        if (activeUserPokemon != null && activeTrainerPokemon != null) {
            ArrayList<Attack> userPokemonAttacks = getAllUserAttackFromPokemonViaLabel();
            setUserPokemonMoveDamage(userPokemonAttacks);

            ArrayList<Attack> trainerPokemonAttacks = getAllTrainerAttackFromPokemonViaLabel();
            setTrainerPokemonMoveDamage(trainerPokemonAttacks);
        }
    }

    private ArrayList<Attack> getAllUserAttackFromPokemonViaLabel() {
        ArrayList<Attack> userPokemonAttacks = new ArrayList<Attack>();

        Attack attack1 = getAttackThoughLabel(UserPokemonAttack1, UserPokemonAttack1Damage, UserPokemonAttack1Type, UserPokemonAttack1Category);
        Attack attack2 = getAttackThoughLabel(UserPokemonAttack2, UserPokemonAttack2Damage, UserPokemonAttack2Type, UserPokemonAttack2Category);
        Attack attack3 = getAttackThoughLabel(UserPokemonAttack3, UserPokemonAttack3Damage, UserPokemonAttack3Type, UserPokemonAttack3Category);
        Attack attack4 = getAttackThoughLabel(UserPokemonAttack4, UserPokemonAttack4Damage, UserPokemonAttack4Type, UserPokemonAttack4Category);

        userPokemonAttacks.add(attack1);
        userPokemonAttacks.add(attack2);
        userPokemonAttacks.add(attack3);
        userPokemonAttacks.add(attack4);

        return userPokemonAttacks;
    }

    private ArrayList<Attack> getAllTrainerAttackFromPokemonViaLabel() {
        ArrayList<Attack> trainerPokemonAttacks = new ArrayList<Attack>();

        Attack attack1 = getAttackThoughLabel(FoePokemonAttack1, FoePokemonAttack1Damage, FoePokemonAttack1Type, FoePokemonAttack1Category);
        Attack attack2 = getAttackThoughLabel(FoePokemonAttack2, FoePokemonAttack2Damage, FoePokemonAttack2Type, FoePokemonAttack2Category);
        Attack attack3 = getAttackThoughLabel(FoePokemonAttack3, FoePokemonAttack3Damage, FoePokemonAttack3Type, FoePokemonAttack3Category);
        Attack attack4 = getAttackThoughLabel(FoePokemonAttack4, FoePokemonAttack4Damage, FoePokemonAttack4Type, FoePokemonAttack4Category);

        trainerPokemonAttacks.add(attack1);
        trainerPokemonAttacks.add(attack2);
        trainerPokemonAttacks.add(attack3);
        trainerPokemonAttacks.add(attack4);

        return trainerPokemonAttacks;
    }

    private Attack getAttackThoughLabel(ComboBox<Attack> attack, TextField damage, ComboBox<String> attackType, ComboBox<String> category) {
        try {
            Attack ua = attack.getValue();
            return new Attack(ua.attackId, attackType.getValue(), ua.attackName, ua.description, Integer.parseInt(damage.getText()),
                    ua.pp, ua.effect, ua.secondaryEffect, ua.priority, ua.targets, category.getValue(), ua.makesContact, ua.protectAffected,
                    ua.magicCoatAffected, ua.snatchAffected, ua.mirrorMoveAffected, ua.kingsRockAffected);
        } catch (Exception e) {
            return new Attack(-1, "", "", "", 0,0,"","",0,"","",false,false,false,false,false,false);
        }
    }

    private void setUserPokemonMoveDamage(ArrayList<Attack> userPokemonAttacks) {
        boolean critical = false;

        PokemonStats userPokemon = getUserPokemonThroughLabels();
        PokemonStats trainerPokemon = getTrainerPokemonThroughLabels();

        if (userPokemon.abilityName.equals("Guts") && !userPokemon.statusCondition.equals("Healthy")) userPokemon.attackStat *= 2;
        else if (trainerPokemon.abilityName.equals("Marvel Scale") && !trainerPokemon.statusCondition.equals("Healthy")) trainerPokemon.defenseStat *= 1.5;
        else if (userPokemon.abilityName.equals("Huge Power")) userPokemon.attackStat *= 2;
        else if (userPokemon.abilityName.equals("Pure Power")) userPokemon.attackStat *= 2;

        // display Damage
        for (int i = 0; i < userPokemonAttacks.size(); i++) {
            Attack attack = userPokemonAttacks.get(i);
            ArrayList<Integer> damageRange = Utilities.calculateDamageOfAttack(activeBattleMode, activeWeather, critical, userPokemon, trainerPokemon, attack);

            if (foeReflect && attack.category.equals("Physical") && damageRange.get(1) > 0) {
                double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                for (int j = 0; j < damageRange.size(); j++) {
                    damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                }
            }

            if (foeLightScreen && attack.category.equals("Special") && damageRange.get(1) > 0) {
                double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                for (int j = 0; j < damageRange.size(); j++) {
                    damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                }
            }

            String damageRangeText = (showFlatDamage) ?  damageRange.get(0) + " - " + damageRange.get(1) : stringDamageRancePercentage(damageRange);
            setUserPokemonDamage(damageRangeText, i);
        }

        for (int i = userPokemonAttacks.size(); i < 4; i++) {
            String damageRangeText = (showFlatDamage) ?  "0 - 0" : "0 - 0%";
            setUserPokemonDamage(damageRangeText, i);
        }
    }

    private void setTrainerPokemonMoveDamage(ArrayList<Attack> trainerPokemonAttacks) {
        boolean critical = false;

        PokemonStats userPokemon = getUserPokemonThroughLabels();
        PokemonStats trainerPokemon = getTrainerPokemonThroughLabels();

        if (trainerPokemon.abilityName.equals("Guts") && !trainerPokemon.statusCondition.equals("Healthy")) trainerPokemon.attackStat *= 2;
        else if (userPokemon.abilityName.equals("Marvel Scale") && !userPokemon.statusCondition.equals("Healthy")) userPokemon.defenseStat *= 1.5;
        else if (trainerPokemon.abilityName.equals("Huge Power")) trainerPokemon.attackStat *= 2;
        else if (trainerPokemon.abilityName.equals("Pure Power")) trainerPokemon.attackStat *= 2;

        // display Damage
        for (int i = 0; i < trainerPokemonAttacks.size(); i++) {
            if (trainerPokemonAttacks.get(i).attackId > 0) {
                Attack attack = trainerPokemonAttacks.get(i);

                boolean thickFat = trainerPokemon.abilityName.equals("Thick Fat") && (attack.typeName.equals("Fire") || attack.typeName.equals("Ice"));
                if (thickFat) trainerPokemon.attackStat /= 2;
                if (thickFat) trainerPokemon.specialAttackStat /= 2;

                ArrayList<Integer> damageRange = Utilities.calculateDamageOfAttack(activeBattleMode, activeWeather, critical, trainerPokemon, userPokemon, attack);

                if (userReflect && attack.category.equals("Physical") && damageRange.get(1) > 0) {
                    double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                    for (int j = 0; j < damageRange.size(); j++) {
                        damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                    }
                }

                if (userLightScreen && attack.category.equals("Special") && damageRange.get(1) > 0) {
                    double reduce = (activeBattleMode.equals("Single")) ? 0.5 : 0.66;
                    for (int j = 0; j < damageRange.size(); j++) {
                        damageRange.set(j, Math.max(1, (int) (damageRange.get(j) * reduce)));
                    }
                }

                String damageRangeText = (showFlatDamage) ? damageRange.get(0) + " - " + damageRange.get(1) : stringDamageRancePercentage(damageRange);
                setTrainerPokemonDamage(damageRangeText, i);
            } else {
                String damageRangeText = (showFlatDamage) ? "0 - 0" : "0 - 0%";
                setTrainerPokemonDamage(damageRangeText, i);
            }
        }
    }

    private PokemonStats getUserPokemonThroughLabels() {
        int pokemonId = activeUserPokemon.pokemonId;
        int pokemonStatsId = activeUserPokemon.pokemonStatsId;
        String type1 = UserPokemonType1.getValue();
        String type2 = UserPokemonType2.getValue();
        String itemName = UserPokemonItem.getValue().nameOfItem;
        String abilityName = UserPokemonAbility.getValue().abilityName;
        String natureName = UserPokemonNature.getValue().natureName;
        String statusCondition = UserPokemonStatus.getValue();
        int level = Integer.parseInt(UserPokemonLevel.getText());
        String gender = UserPokemonGender.getValue();
        int friendship = activeUserPokemon.friendship;
        int attackStat = calcStatWithBoost(Integer.parseInt(UserPokemonAttack.getText()), UserPokemonAttackBoost.getValue());
        int defenseStat = calcStatWithBoost(Integer.parseInt(UserPokemonDefense.getText()), UserPokemonDefenseBoost.getValue());
        int specialAttackStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpecialAttack.getText()), UserPokemonSpecialAttackBoost.getValue());
        int specialDefenseStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpecialDefense.getText()), UserPokemonSpecialDefenseBoost.getValue());
        int speedStat = calcStatWithBoost(Integer.parseInt(UserPokemonSpeed.getText()), UserPokemonSpeedBoost.getValue());
        return new PokemonStats(pokemonId, pokemonStatsId, type1, type2, itemName, abilityName, natureName, statusCondition, level,
                gender, friendship, attackStat, defenseStat, specialAttackStat, specialDefenseStat, speedStat);
    }

    private PokemonStats getTrainerPokemonThroughLabels() {
        int pokemonId = activeTrainerPokemon.pokemonId;
        int pokemonStatsId = activeTrainerPokemon.pokemonStatsId;
        String type1 = FoePokemonType1.getValue();
        String type2 = FoePokemonType2.getValue();
        String itemName = FoePokemonItem.getValue().nameOfItem;
        String abilityName = FoePokemonAbility.getValue().abilityName;
        String natureName = FoePokemonNature.getValue().natureName;
        String statusCondition = FoePokemonStatus.getValue();
        int level = Integer.parseInt(FoePokemonLevel.getText());
        String gender = FoePokemonGender.getValue();
        int friendship = activeTrainerPokemon.friendship;
        int attackStat = calcStatWithBoost(Integer.parseInt(FoePokemonAttack.getText()), FoePokemonAttackBoost.getValue());
        int defenseStat = calcStatWithBoost(Integer.parseInt(FoePokemonDefense.getText()), FoePokemonDefenseBoost.getValue());
        int specialAttackStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpecialAttack.getText()), FoePokemonSpecialAttackBoost.getValue());
        int specialDefenseStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpecialDefense.getText()), FoePokemonSpecialDefenseBoost.getValue());
        int speedStat = calcStatWithBoost(Integer.parseInt(FoePokemonSpeed.getText()), FoePokemonSpeedBoost.getValue());
        return new PokemonStats(pokemonId, pokemonStatsId, type1, type2, itemName, abilityName, natureName, statusCondition, level,
                gender, friendship, attackStat, defenseStat, specialAttackStat, specialDefenseStat, speedStat);
    }

    private int calcStatWithBoost(int stat, String boost) {
            switch (boost) {
                case "+6" -> {
                    return stat * 4;
                }
                case "+5" -> {
                    return (int) (stat * 3.5);
                }
                case "+4" -> {
                    return (int) (stat * 3);
                }
                case "+3" -> {
                    return (int) (stat * 2.5);
                }
                case "+2" -> {
                    return (int) (stat * 2);
                }
                case "+1" -> {
                    return (int) (stat * 1.5);
                }
                case "-1" -> {
                    return (int) (stat * 0.66);
                }
                case "-2" -> {
                    return (int) (stat * 0.5);
                }
                case "-3" -> {
                    return (int) (stat * 0.40);
                }
                case "-4" -> {
                    return (int) (stat * 0.33);
                }
                case "-5" -> {
                    return (int) (stat * 0.28);
                }
                case "-6" -> {
                    return (int) (stat * 0.25);
                }
                default -> {
                    return stat;
                }
            }

    }

    private void setUserPokemonDamage(String damageRange, int index) {
        switch(index) {
            case 0 -> userMove1Damage.setText(damageRange);
            case 1 -> userMove2Damage.setText(damageRange);
            case 2 -> userMove3Damage.setText(damageRange);
            case 3 -> userMove4Damage.setText(damageRange);
        }
    }

    private void setTrainerPokemonDamage(String damageRange, int index) {
        switch(index) {
            case 0 -> foeMove1Damage.setText(damageRange);
            case 1 -> foeMove2Damage.setText(damageRange);
            case 2 -> foeMove3Damage.setText(damageRange);
            case 3 -> foeMove4Damage.setText(damageRange);
        }
    }

    private String stringDamageRancePercentage(ArrayList<Integer> damageRange) {
        int trainerPokemonHp = Integer.parseInt(FoePokemonHP.getText());

        int lowPercentage = (int) (((damageRange.get(0) * 1.0) / trainerPokemonHp) * 100);
        int highPercentage = (int) (((damageRange.get(1)  * 1.0) / trainerPokemonHp) * 100);

        return (lowPercentage + " - " + highPercentage + "%");
    }
}