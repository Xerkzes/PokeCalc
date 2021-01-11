package Classes;

public class PokemonList  {
    public int pokemonId;
    public String nickname;
    public int pokemonStatsId;
    public int dexNr;

    public PokemonList(int pokemonId, String nickname, int pokemonStatsId, int dexNr) {
        this.pokemonId = pokemonId;
        this.nickname = nickname;
        this.pokemonStatsId = pokemonStatsId;
        this.dexNr = dexNr;
    }

    @Override
    public String toString() {
        return nickname;
    }
}
