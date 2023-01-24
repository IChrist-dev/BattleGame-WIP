import java.io.*;
import java.util.ArrayList;

public class FileIO {
    public static Character readCharacter(String fileName) throws FileNotFoundException, IOException {
        Character aCharacter;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String currentLine = br.readLine();
            String name = currentLine;
            currentLine = br.readLine();

            double attackVal = Double.parseDouble(currentLine);
            currentLine = br.readLine();

            double maxHP = Double.parseDouble(currentLine);
            currentLine = br.readLine();

            int numWins = Integer.parseInt(currentLine);

            aCharacter = new Character(name, attackVal, maxHP, numWins);

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The specified file name was not found.");
        } catch (IOException e) {
            throw new IOException("Input/Output error has occured.");
        }
        return aCharacter;
    }

    public static ArrayList<Spell> readSpells(String fileName) throws FileNotFoundException, IOException {
        ArrayList<Spell> spellsArr = new ArrayList<Spell>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String currentLine = br.readLine();

            String[] spellCurrentLine = new String[4];

            spellCurrentLine = currentLine.split(" ");

            String name = spellCurrentLine[0];
            double minDam = Double.parseDouble(spellCurrentLine[1]);
            double maxDam = Double.parseDouble(spellCurrentLine[2]);
            double successProb = Double.parseDouble(spellCurrentLine[3]);

            Spell newSpell = new Spell(name, minDam, maxDam, successProb);
            spellsArr.add(newSpell);

            currentLine = br.readLine();

            spellCurrentLine = currentLine.split(" ");

            name = spellCurrentLine[0];
            minDam = Double.parseDouble(spellCurrentLine[1]);
            maxDam = Double.parseDouble(spellCurrentLine[2]);
            successProb = Double.parseDouble(spellCurrentLine[3]);

            newSpell = new Spell(name, minDam, maxDam, successProb);
            spellsArr.add(newSpell);

            currentLine = br.readLine();

            spellCurrentLine = currentLine.split(" ");

            name = spellCurrentLine[0];
            minDam = Double.parseDouble(spellCurrentLine[1]);
            maxDam = Double.parseDouble(spellCurrentLine[2]);
            successProb = Double.parseDouble(spellCurrentLine[3]);

            newSpell = new Spell(name, minDam, maxDam, successProb);
            spellsArr.add(newSpell);

            currentLine = br.readLine();

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The specified file name was not found.");
        } catch (IOException e) {
            throw new IOException("Input/Output error has occured in the readSpells method of the FileIO class.");
        }
        Spell newerSpell = new Spell("fireball", 5.0, 10.0, 0.5);
        newerSpell.toString();

        return spellsArr;
    }

    //Write the character to a file
    public static void writeCharacter(Character character, String fileName) throws IllegalArgumentException {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(character.getName());
            bw.newLine();
            bw.write(Double.toString(character.getAttack()));
            bw.newLine();
            bw.write(Double.toString(character.getMaxHP()));
            bw.newLine();
            bw.write(Integer.toString(character.getWinNum()));

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new IllegalArgumentException
                    ("An Input/Output error has occured in the writeCharacter method of the FileIO class.");
        }
    }
}