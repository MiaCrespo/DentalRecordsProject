import java.util.Scanner;
/**
 * This program the "Floridian Tooth Records" which allows users to input and manage dental records for family members
 * It prints, extracts teeth, and calculates root canals based on the dental records inputted
 * @author Mia
 */
public class DentalRecordsProject {
    /**
     * The main method
     * It allows users to input dental info and manage records
     *
     * @param args unused in this application
     */
    public static void main(String[] args) {
        //initialized variables
        int numberOfPeople;
        String uppers = "";
        String lowers = "";
        String name = "";

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
        System.out.print("Please enter number of people in the family: ");
        Scanner scanner = new Scanner(System.in);
        numberOfPeople = Integer.parseInt(scanner.nextLine()); //changes users input to int
        while (numberOfPeople > 6 || numberOfPeople < 1) { //checks if the number is outside of 1 to 6
            System.out.print("Invalid number of people, try again: ");
            numberOfPeople = Integer.parseInt(scanner.nextLine()); //changes users input again to int
        }
        String[][][] DentalRecords = new String[numberOfPeople + 1][3][9]; //3d array for names and teeth
        int cycles = 0; //keeps track of how many family members
        while ((cycles + 1) <= numberOfPeople) { //loop through each family member
            System.out.print("Please enter the name for family member " + (cycles + 1) + ": ");
            name = scanner.nextLine();
            boolean validTeeth = false; //checks if uppers and lowers are valid
            System.out.print("Please enter the uppers for " + name + ": ");
            while (!validTeeth) { //loops until teeth are valid
                DentalRecordsProject check = new DentalRecordsProject();
                uppers = scanner.nextLine().toUpperCase(); //changes uppers to uppercase
                Boolean upperValid = check.TeethValidity(uppers); //checks if valid
                if (!upperValid) { //prompts again if invalid
                    continue;
                }

                System.out.print("Please enter the lowers for " + name + ": ");
                lowers = scanner.nextLine().toUpperCase(); //changes lowers to uppercase
                Boolean lowerValid = check.TeethValidity(lowers); //checks if valid
                if (!lowerValid) { //prompts again if invalid
                    continue;
                }
                if (upperValid && lowerValid) { //checks if both are valid
                    validTeeth = true; //if true exits loop
                }
            }
            DentalRecords[cycles][0][0] = name;  //store the persons name

            //store the teeth with position numbers
            for (int row = 0; row < 2; row++) {
                String insertTeeth = "";
                if (row == 0) { //if 0, processes uppers
                    insertTeeth = uppers.toUpperCase();  //user inputs upper teeth
                } else if (row == 1) {
                    insertTeeth = lowers.toUpperCase();  //user inputs lower teeth
                }

                // store the teeth with tooth number
                for (int column = 0; column < insertTeeth.length(); column++) {
                    DentalRecords[cycles][row + 1][column + 1] = String.valueOf(insertTeeth.charAt(column)); //store each tooth in the array
                }
            }
            cycles += 1; //moves to next family member
        }
        boolean exit = false;
        System.out.println();
        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: "); //menu prompt
        while (!exit) {

            String input = scanner.nextLine().toUpperCase(); //changes menu input to uppercase
            if (!(input.equals("P") || input.equals("R") || input.equals("E") || input.equals("X"))) { //checks if valid
                System.out.print("Invalid menu option, try again: ");
                continue;
            }
            if (input.equals("P")) { //if user chooses P, print the dental records
                System.out.println();
                for (int i = 0; i < DentalRecords.length - 1; i++) { // 1st dimension (the layers)
                    System.out.println(DentalRecords[i][0][0]); //print the family members name (1st row/1st column)
                    for (int j = 1; j < DentalRecords[i].length; j++) { // 2nd dimension (the rows)
                        if (j == 1) {
                            System.out.print("Uppers: "); //prints uppers in first row
                        } else if (j == 2) {
                            System.out.print("Lowers: "); //prints lowers in 2nd row
                        }
                        for (int k = 1; k < DentalRecords[i][j].length; k++) { // 3rd dimension (the columns)
                            if (DentalRecords[i][j][k] != null) { //checks validity of tooth choice
                                System.out.print(k + ":" + DentalRecords[i][j][k] + " "); //prints tooth position and I,B, or M
                            } else if (DentalRecords[i][j][k] == null) { //if no tooth entry leave a space
                                System.out.print(" "); //prints the empty space^^
                            }
                        }
                        System.out.println();
                    }
                }
                System.out.println();
                System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: "); //menu prompt
            } else if (input.equals("E")) { //if extract
                String member = ""; //stores family members name
                boolean validmember = false; //boolean to check if its valid ^^
                int attempts = 0; //tracks invalid attempts
                int memberID = 0; //store valid family member
                System.out.print("Which family member?: ");

                while (!validmember) { //loops until valid family member is found
                    member = scanner.nextLine().toUpperCase(); //changes to uppercase

                    for (int i = 0; i < DentalRecords.length - 1; i++) { //finds the name
                        String checkname = DentalRecords[i][0][0].toUpperCase(); //^^changes to uppercase
                        if (member.equals(checkname)) { //checks if name is a stored name
                            validmember = true; //^^ if yes, valid
                            memberID = i; //stores index of family member
                        } else attempts++;
                    }
                    if (attempts >= numberOfPeople && !validmember) {
                        System.out.print("Invalid family member, try again: "); // tells user try again if not a valid/stored member
                        continue;
                    }
                    attempts = 0; //resets attempts to 0
                }
                String layer = ""; //stores tooth layer

                System.out.print("Which tooth layer (U)pper or (L)ower: ");
                while (!(layer.equals("U") || layer.equals("L"))) { //loops until receives U or L
                    layer = scanner.nextLine().toUpperCase();
                    if (!(layer.equals("U") || layer.equals("L"))) { //if not U or L, makes user try again
                        System.out.print("Invalid layer, try again: ");
                    }
                }
                int toothnum = 0; //stores tooth number
                boolean replacedtooth = false; //checks if replaced
                System.out.print("Which tooth number: ");
                while (!replacedtooth) { //loops until replaced
                    boolean validtooth = false;
                    while (!validtooth) { //loops until valid tooth
                        toothnum = Integer.parseInt(scanner.next()) - 1; //converts to int and adjusts to 0 base index
                        scanner.nextLine();
                        //checks if the input is within valid bounds for the selected layer
                        if (layer.equals("U") && (toothnum < 0 || toothnum >= 8) || layer.equals("L") && (toothnum < 0 || toothnum >= 8)) {
                            System.out.print("Invalid tooth number, try again: ");
                            continue;
                        }
                        validtooth = true; //marks tooth as valid

                    }
                    int l = 2; //initialize layer index for lowers
                    if (layer.equals("U")) { //if uppers
                        l = 0;
                    } else if (layer.equals("L")) { //if lowers
                        l = 1;
                    }

                    String replaceTooth = DentalRecords[memberID][l + 1][toothnum + 1]; // adjusted to +1 to match the index
                    if (replaceTooth.equals("M")) { //if tooth is missing
                        System.out.print("Missing tooth, try again: ");
                        continue; //restarts loop
                    } else {
                        DentalRecords[memberID][l + 1][toothnum + 1] = "M"; // also adjusted to +1 to match the index
                        replacedtooth = true; // replaced once done
                    }
                    System.out.println();
                    System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: "); //menu prompt
                }
                //if chose R for root canals
            } else if (input.equals("R")) {
                int mCount = 0; //count for missing teeth
                int iCount = 0; //count for intact teeth
                int bCount = 0; //count for broken teeth

                //loop through the dental records to count the types of teeth
                for (int i = 0; i < DentalRecords.length; i++) {
                    for (int j = 0; j < DentalRecords[i].length; j++) {
                        for (int k = 0; k < DentalRecords[i][j].length; k++) {
                            if (DentalRecords[i][j][k] == null) { //if no record, skip
                                continue;
                            } else if (DentalRecords[i][j][k] != null) {
                                //increment counters based on tooth types
                                if (DentalRecords[i][j][k].equals("M")) {
                                    mCount++; //missing tooth
                                } else if (DentalRecords[i][j][k].equals("I")) {
                                    iCount++; //intact tooth
                                } else if (DentalRecords[i][j][k].equals("B")) {
                                    bCount++; //broken tooth
                                }
                            }
                        }
                    }
                }
                // Quadratic equation = (-b Â± sqrt(D)) / 2a
                // D == b^2 - 4ac
                // Ix^2 + Bx - M
                //solves the quadratic formula^^ for root canals
                int D = (bCount * bCount) - 4 * (iCount) * (-mCount);
                double posRootIndice = ((-bCount) + Math.sqrt(Math.abs(D))) / (2 * iCount); //calculate positive root
                double negRootIndice = ((-bCount) - Math.sqrt(Math.abs(D))) / (2 * iCount); //calculate negative root
                System.out.println("One root canal at " + String.format("%.2f", posRootIndice)); //display positive oot
                System.out.println("Another root canal at " + String.format("%.2f", negRootIndice)); //display negative root
                System.out.println();
                System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: "); //returns to menu prompt


            } else if (input.equals("X")) { //if X was chosen to exit
                System.out.println();
                System.out.println("Exiting the Floridian Tooth Records :-)");
                exit = true; //end the program

            }
        }
    }

    /**
     * Checks the validity of the entered teeth string
     *
     * @param teeth the string representing the teeth to validate
     * @return true if the string is valid and false if not
     */

    //method to validate teeth input I, B, or M only
    public Boolean TeethValidity(String teeth) {
        if (teeth.length() > 8) { //check if input is more than 8 characters
            System.out.print("Too many teeth, try again: ");
            return false; //invalid input
        }
        //loop through each character in teeth string
        for (int i = 0; i < teeth.length(); i++) {
            char Char = teeth.charAt(i); //get the current character
            if (Char != 'I' && Char != 'B' && Char != 'M') { //if not I, B, or M
                System.out.print("Invalid teeth types, try again: ");
                return false; //invalid input

            }

        }
        return true; //valid input
    }
}