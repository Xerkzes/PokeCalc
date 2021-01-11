package Classes;

public class Trainer {
    public int trainerId;
    public int routeId;
    public String trainerName;
    public int fightNumber;
    public boolean foughtAlready;

    public Trainer(int trainerId, int routeId, String trainerName, int fightNumber, boolean foughtAlready) {
        this.trainerId = trainerId;
        this.routeId = routeId;
        this.trainerName = trainerName;
        this.fightNumber = fightNumber;
        this.foughtAlready = foughtAlready;
    }

    @Override
    public String toString() {
        return trainerName;
    }
}
