package bullscows;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean startGame = false;
        int turn = 1;
        int codeLength = getCodeLength();
        char[] secretCode = new char[codeLength];
        if (codeLength == 101) {
            System.out.println("Error: it is not a valid number.");
        } else if (codeLength == 0) {
            System.out.println("Error: code length cant be 0.");
        } else if (codeLength > 37) {
            System.out.println("Error: code too long");
        } else {
            int numOfSymbols = getNumOfSymbols();
            if (numOfSymbols == 101) {
                System.out.println("Error, it is not a valid number");
            } else if (numOfSymbols > 36) {
                System.out.println("Error, too many symbols");
            }else if (codeLength > numOfSymbols){
                System.out.println("Error: Its not possible to generate a code");
            }else{
                secretCode = generateSecretCode(codeLength, numOfSymbols);
                startGame = true;
            }
        }
        while (startGame) {
            char[] userInput = getUserInput();
            if (userInput.length != secretCode.length) {
                System.out.println("Error");
                break;
            }
            System.out.println("Turn " + turn++ + ":");
            if (secretCode.length == checkCowsAndBulls(userInput, secretCode)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }

    static char[] generateSecretCode(int codeLength, int numOfSymbols) {
        Random rand = new Random(System.nanoTime());
        char[] secretCode = new char[codeLength];

        if (codeLength > 36) {
            System.out.println("Error");
        } else {
            for (int i = 0; i < secretCode.length; ) {
                if (rand.nextBoolean() || numOfSymbols <= 10) {
                    char tempChar = (char) (rand.nextInt(Math.min(10, numOfSymbols)) + '0');
                    if (checkIfCodeContainsElement(tempChar, secretCode)) {
                        secretCode[i] = tempChar;
                        i++;
                    }
                } else {
                    char tempChar = (char) (rand.nextInt(numOfSymbols - 10) + 'a');
                    if (checkIfCodeContainsElement(tempChar, secretCode)) {
                        secretCode[i] = tempChar;
                        i++;
                    }
                }
            }
        }
        System.out.println("The secret is prepared: ");
        for (int i = 0; i < secretCode.length; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-");
        if (numOfSymbols > 10) System.out.print(((char) ('0' + 9)) + ",a-" + ((char) ('a' + numOfSymbols - 11)));
        else System.out.print(((char) ('0' + numOfSymbols - 1)));
        System.out.println(").");
        System.out.println("Okay, let's start a game!");
        return secretCode;
    }

    static int getCodeLength() {
        System.out.println("Please, enter the secret code's length:");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            return 101;
        }
    }

    static int getNumOfSymbols() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the number of possible symbols in the code:");
        return sc.nextInt();

    }

    static boolean checkIfCodeContainsElement(char element, char[] arr) {
        for (char c : arr) {
            if (element == c) {
                return false;
            }
        }
        return true;
    }

    static char[] getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next().toCharArray();

    }

    static int checkCowsAndBulls(char[] userInput, char[] secretCode) {
        int bulls = 0;
        int cows = 0;
        for (int userInputPos = 0; userInputPos < secretCode.length; userInputPos++) {
            if (userInput[userInputPos] == secretCode[userInputPos]) {
                bulls++;
            } else {
                for (char c : secretCode) {
                    if (userInput[userInputPos] == c) {
                        cows++;
                    }
                }

            }
        }
        System.out.print("Grade: ");
        if (bulls == 0 && cows == 0) {
            System.out.print("None");
        } else if (bulls > 0) {
            System.out.print(bulls + " bull(s)");
            if (cows > 0) {
                System.out.print(" and ");
            }
        }
        if (cows > 0) {
            System.out.print(cows + " cow(s)");
        }
        return bulls;
    }

}
