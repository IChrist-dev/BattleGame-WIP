import java.util.Random;

public class Spell {
    private String name;
    private double minDam;
    private double maxDam;
    private double successProb;
    Random randomNumGen = new Random();

    //Constructor
    public Spell(String name, double minDam, double maxDam, double successProb) {
        if (minDam < 0 || minDam > maxDam || successProb < 0 || successProb > 1) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.minDam = minDam;
        this.maxDam = maxDam;
        this.successProb = successProb;
    }

    //Getters
    public String getName() {
        return name;
    }

    public double getDamage() {
        double randNum = randomNumGen.nextDouble();
        if (randNum > successProb) {
            return 0;
        }
        double damReturn = randNum * ((this.maxDam - this.minDam) + this.minDam);
        return damReturn;
    }

    //ToString method
    public String toString() {
        String info = "Name: " + this.name + " Damage: " + this.minDam + "-" +
                this.maxDam + " Chance: " + (this.successProb * 100) + "%";
        return info;
    }
}