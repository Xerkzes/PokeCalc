package Classes;

public class PokeStats {
    public int pokemonStatsId;
    public String nameOfPokemon;
    public int dexNr;
    public String expGrowthRate;
    public double weight;
    public double height;
    public int baseHp;
    public int baseAttack;
    public int baseDefense;
    public int baseSpecialAttack;
    public int baseSpecialDefense;
    public int baseSpeed;

    public PokeStats(int pokemonStatsId, String nameOfPokemon, int dexNr, String expGrowthRate, double weight, double height, int baseHp, int baseAttack, int baseDefense, int baseSpecialAttack, int baseSpecialDefense, int baseSpeed) {
        this.pokemonStatsId = pokemonStatsId;
        this.nameOfPokemon = nameOfPokemon;
        this.dexNr = dexNr;
        this.expGrowthRate = expGrowthRate;
        this.weight = weight;
        this.height = height;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpecialAttack = baseSpecialAttack;
        this.baseSpecialDefense = baseSpecialDefense;
        this.baseSpeed = baseSpeed;
    }
}
