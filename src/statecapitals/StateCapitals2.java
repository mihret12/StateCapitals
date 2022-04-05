package statecapitals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class StateCapitals2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String filename = "/Users/mihrettadesse/NetBeansProjects/StateCapitals/src/StateCapitals.txt";
        Map<String, String> map = new HashMap<>();
        String delimiter = "::";
        // read data from stateCapitals.txt file 
        readDataFromFile(map, filename, delimiter);

        int guess;
        final int MAP_SIZE = map.size();
        Random rand = new Random();
        guess = rand.nextInt(MAP_SIZE) + 1;
        System.out.println(map.size() + " STATES & CAPITALS ARE LOADED.");
        System.out.println("****************************");
        Set<String> states = map.keySet();
        String statesString = "";
        String randomState = "";
        int y = 1;
        for (String state : states) {
            if (y == guess) {
                // to select the random state. much guess state and random state
                randomState = state;
            }
            // display state 
            statesString = statesString + state + ", ";
            y++;

        }

        System.out.println(statesString.substring(0, statesString.length() - 2));

        System.out.println("READY TO USE YOUR KNOWLEDGE? WHAT IS THE CAPITAL OF '" + randomState + "'?");
        String capital = scan.nextLine();
        if (map.get(randomState).equalsIgnoreCase(capital)) {
            System.out.println("GOOD JOB! " + capital + " IS RIGHT!");
        } else {
            System.out.println(capital + " IS WRONG!");
        }
// state  guess
        System.out.print("HOW MANY STATES YOU WANT TO GUESS ? ");
        int guessCount = scan.nextInt();
        scan.nextLine();
        List<String> statesList = new ArrayList<>();
        int i = 1;
        int score = 0;
        while (guessCount != 0) {
            System.out.print("Enter State " + i + ":");
            String gussedState = scan.nextLine();
            if (!statesList.contains(gussedState.toLowerCase())) {
                statesList.add(gussedState.toLowerCase());
                Set<String> keys = map.keySet();
                boolean found = false;
                for (String state : keys) {
                    if (state.equalsIgnoreCase(gussedState)) {
                        score++;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    score--;
                }
                i++;
                guessCount--;
            } else {
                System.out.println(gussedState + " is Duplicate! please try again");
            }
        }

        System.out.println("TOTAL SCORE :" + score);
        scan.close();

    }
    /**
     * This method is used to read state and capitals from txt file and put to hashmap
     * @param map
     * @param filename
     * @param delimiter
     * @throws FileNotFoundException
     * @throws NoSuchElementException 
     */

    public static void readDataFromFile(Map<String, String> map, String filename, String delimiter)
            throws FileNotFoundException, NoSuchElementException {
        File file = new File(filename);
        try ( Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                StringTokenizer st = new StringTokenizer(sc.nextLine(), delimiter);
                while (st.hasMoreTokens()) {
                    map.put(st.nextToken(), st.nextToken());
                }
            }
        } catch (FileNotFoundException | NoSuchElementException ex) {
            System.err.println("Error opening file.");
            throw ex;
        }
    }

}
