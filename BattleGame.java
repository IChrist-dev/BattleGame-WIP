import java.util.Scanner;
import java.util.ArrayList;

public class BattleGame {
    public static void main(String[] args) {
        playGame();
    }

    //Int return type is arbitrary. Only used as a return out of the method
    public static int playGame() {
        ArrayList<Spell> spellList = new ArrayList<Spell>();
        try {
            spellList = FileIO.readSpells("spells.txt");
        } catch (Exception e) {
            System.out.println(e);
        }

        Scanner input = new Scanner(System.in);
        //Initializing players with default values prior to FileIO construction
        Character player = new Character("", 0.0, 0.0, 0);
        Character monster = new Character("", 0.0, 0.0, 0);

        try {
            //Create the player
            player = FileIO.readCharacter("player.txt");
            System.out.println(player.toString());
            //Create the monster
            monster = FileIO.readCharacter("monster.txt");
            System.out.println(monster.toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println();

        //Present all available spells
        System.out.println("Spells:");

        for (int i = 0; i < spellList.size(); i++) {
            System.out.println(spellList.get(i).toString());
        }

        player.setSpells(spellList);
        monster.setSpells(spellList);

        while (player.getHP() > 0 && monster.getHP() > 0) {
            System.out.println("What would you like to do?");
            String action = input.nextLine();
            //Player attacks while monster waits turn
            if (action.equals("attack")) {
                doAttack(player, monster);
                if (monster.getHP() >= 0.0) {
                    FileIO.writeCharacter(player, "player.txt");
                    FileIO.writeCharacter(monster, "monster.txt");
                }
                if (player.getHP() >= 0.0) {
                    FileIO.writeCharacter(player, "player.txt");
                    FileIO.writeCharacter(monster, "monster.txt");
                }
            } else if (action.equals("quit")) {
                //Updating character's save files
                FileIO.writeCharacter(player, "player.txt");
                FileIO.writeCharacter(monster, "monster.txt");

                System.out.println("Have a nice day.");
                return 0;
            } else {
                monster.takeDamage(player.castSpell(action));
                System.out.println(monster.getName() + "'s health is: " + monster.getHP());
                doAttack(monster, player);
            }
        }
        return 0;
    }

    //DoAttack method
    public static void doAttack(Character charA, Character charB) {
        double damageDealt = charA.calcAttack(charA.getAttack());

        String attackStr = String.format("%1$,.2f", damageDealt);

        System.out.println(charA.getName() + " has dealt " + attackStr + " damage.");
        charB.takeDamage(damageDealt);

        if (charB.getHP() > 0.0) {
            System.out.println(charB.getName() + " HP: " + charB.getHP());
        }
        if (charB.getHP() <= 0.0) {
            System.out.println(charB.getName() + " has been knocked out.");
            charA.increaseWins();
            charA.getWinNum();
            System.out.println(charA.getName() + " has won: " + charA.getWinNum() + " times.");
        }
    }
}