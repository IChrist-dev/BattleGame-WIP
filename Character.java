import java.util.Random;
import java.util.ArrayList;

public class Character {
    private String name;
    private double attackVal;
    private double maxHP;
    private double currHP;
    private int numWin;
    private static ArrayList<Spell> spellList = new ArrayList<Spell>();
    private Random numberGenerator = new Random();

    //Constructor
    public Character(String name, double attackVal, double maxHP, int numWin) {
        this.name = name;
        this.attackVal = attackVal;
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.numWin = numWin;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public double getAttack() {
        return this.attackVal;
    }

    public double getMaxHP() {
        return this.maxHP;
    }

    public double getHP() {
        return this.currHP;
    }

    public int getWinNum() {
        return this.numWin;
    }

    //Setters
    public void setName(String n) {
        this.name = n;
    }

    public void setSpells(ArrayList<Spell> inputSpells) {
        for (int i = 0; i < inputSpells.size(); i++) {
            spellList = inputSpells;
        }
    }

    //CastSpell method
    public double castSpell(String spellName) {
        //iterating through the Spell ArrayList
        for (int i = 0; i < spellList.size(); i++) {
            //Comparing the input spell name to the list
            if (spellList.get(i).getName().toLowerCase().equals(spellName.toLowerCase())) { //Can I get a high-five for that?
                this.currHP = currHP - (spellList.get(i).getDamage());
                //If the spell was ineffective
                if (spellList.get(i).getDamage() == 0.0) {
                    System.out.println(this.name + " failed to cast the " + spellList.get(i).getName() + " spell.");
                    return 0.0;
                }
                //If the spell was effective
                if (spellList.get(i).getDamage() > 0.0) {
                    System.out.println(this.name + " cast the " + spellList.get(i).getName() + " spell.");
                    return spellList.get(i).getDamage();
                }
            }
        }
        //If the spell name was never found and the method makes it this far
        System.out.println(spellName + " is a spell unknown to magic. Please go back to charms class.");
        return 0.0;
    }

    //toString method
    public String toString() {
        String info = ("Name: " + this.name + " HP: " + this.currHP + "\n" +
                " Attack Value: " + this.attackVal + "\n" + " Number of Wins: " + this.numWin);
        return info;
    }

    //calcAttack method
    public double calcAttack(double attackVal) {
        double damageVal = attackVal * (numberGenerator.nextDouble() * (0.7 - 0.3) + 0.3);
        return damageVal;
    }

    //takeDamage method
    public void takeDamage(double damageVal) {
        double newHP = currHP - damageVal;
        this.currHP = Double.parseDouble(String.format("%1$,.2f", newHP));
    }

    //increaseWins method
    public void increaseWins() {
        this.numWin++;
    }
}