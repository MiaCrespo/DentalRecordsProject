import java.util.Scanner;

public class DentalRecordsProject {
    public static void main(String[] args) {
        int numberOfPeople;
        String uppers = "";
        String lowers = "";
        String name = "";

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
        System.out.print("Please enter number of people in the family: ");
        Scanner scanner = new Scanner(System.in);
        numberOfPeople = Integer.parseInt(scanner.nextLine());
        while (numberOfPeople > 6 || numberOfPeople < 1) {
            System.out.print("Invalid number of people, try again: ");
            numberOfPeople = Integer.parseInt(scanner.nextLine());
        }
        String[][][] DentalRecords = new String[numberOfPeople + 1][3][9];
        int cycles = 0;
        while ((cycles + 1) <= numberOfPeople) {
            System.out.print("Please enter the name for family member " + (cycles + 1) + ": ");
            name = scanner.nextLine();
            boolean validTeeth = false;
            System.out.print("Please enter the uppers for " + name + ": ");
            while (!validTeeth) {
                DentalRecordsProject check = new DentalRecordsProject();
                uppers = scanner.nextLine().toUpperCase();
                Boolean upperValid = check.TeethValidity(uppers);
                if (!upperValid) {
                    continue;
                }

                System.out.print("Please enter the lowers for " + name + ": ");
                lowers = scanner.nextLine().toUpperCase();
                Boolean lowerValid = check.TeethValidity(lowers);
                if (!lowerValid) {
                    continue;
                }
                if (upperValid && lowerValid) {
                    validTeeth = true;
                }
            }
            DentalRecords[cycles][0][0] = name;  // to store the persons name

            // Store the teeth data with position numbers
            for (int row = 0; row < 2; row++) {
                String insertTeeth = "";
                if (row == 0) {
                    insertTeeth = uppers.toUpperCase();  // user inputs upper teeth
                } else if (row == 1) {
                    insertTeeth = lowers.toUpperCase();  // user inputs lower teeth
                }

                // store the teeth with tooth number
                for (int column = 0; column < insertTeeth.length(); column++) {
                    DentalRecords[cycles][row + 1][column + 1] = String.valueOf(insertTeeth.charAt(column));
                }
            }
            cycles += 1;
        }
        boolean exit = false;
        System.out.println();
        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: ");
        while (!exit) {

            String input = scanner.nextLine().toUpperCase();
            if (!(input.equals("P") || input.equals("R") || input.equals("E") || input.equals("X"))) {
                System.out.print("Invalid menu option, try again: ");
                continue;
            }
            if (input.equals("P")) {
                System.out.println();
                for (int i = 0; i < DentalRecords.length - 1; i++) { // 1st dimension (the layers)
                    System.out.println(DentalRecords[i][0][0]);
                    for (int j = 1; j < DentalRecords[i].length; j++) { // 2nd dimension (the rows)
                        if (j == 1) {
                            System.out.print("Uppers: ");
                        } else if (j == 2) {
                            System.out.print("Lowers: ");
                        }
                        for (int k = 1; k < DentalRecords[i][j].length; k++) { // 3rd dimension (the columns)
                            if (DentalRecords[i][j][k] != null) {
                                System.out.print(k + ":" + DentalRecords[i][j][k] + " ");
                            } else if (DentalRecords[i][j][k] == null) {
                                System.out.print(" ");
                            }
                        }
                        System.out.println();
                    }
                }
                System.out.println();
                System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: ");
            } else if (input.equals("E")) {
                String member = "";
                boolean validmember = false;
                int attempts = 0;
                int memberID = 0;
                System.out.print("Which family member?: ");
                while (!validmember) {
                    member = scanner.nextLine().toUpperCase();
                    for (int i = 0; i < DentalRecords.length - 1; i++) {
                        String checkname = DentalRecords[i][0][0].toUpperCase();
                        if (member.equals(checkname)) {
                            validmember = true;
                            memberID = i;
                        } else attempts++;
                    }
                    if (attempts >= numberOfPeople && !validmember) {
                        System.out.print("Invalid family member, try again: ");
                        continue;
                    }
                    attempts = 0;
                }
                String layer = "";

                System.out.print("Which tooth layer (U)pper or (L)ower: ");
                while (!(layer.equals("U") || layer.equals("L"))) {
                    layer = scanner.nextLine().toUpperCase();
                    if (!(layer.equals("U") || layer.equals("L"))) {
                        System.out.print("Invalid layer, try again: ");
                    }
                }
                int toothnum = 0;
                boolean replacedtooth = false;
                System.out.print("Which tooth number: ");
                while (!replacedtooth) {
                    boolean validtooth = false;
                    while (!validtooth) {
                        toothnum = Integer.parseInt(scanner.next()) - 1;
                        scanner.nextLine();
                        if (layer.equals("U") && (toothnum < 0 || toothnum >= 8) || layer.equals("L") && (toothnum < 0 || toothnum >= 8)) {
                            System.out.print("Invalid tooth number, try again: ");
                            continue;
                        }
                        validtooth = true;

                    }
                    int l = 2;
                    if (layer.equals("U")) {
                        l = 0;
                    } else if (layer.equals("L")) {
                        l = 1;
                    }

                    String replaceTooth = DentalRecords[memberID][l + 1][toothnum + 1]; // adjusted to +1 to match the index
                    if (replaceTooth.equals("M")) {
                        System.out.print("Missing tooth, try again: ");
                        continue;
                    } else {
                        DentalRecords[memberID][l + 1][toothnum + 1] = "M"; // also adjusted to +1 to match the index
                        replacedtooth = true; // replaced once done
                    }
                    System.out.println();
                    System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: ");
                }
            } else if (input.equals("R")) {
                int mCount = 0;
                int iCount = 0;
                int bCount = 0;
                for (int i = 0; i < DentalRecords.length; i++) {
                    for (int j = 0; j < DentalRecords[i].length; j++) {
                        for (int k = 0; k < DentalRecords[i][j].length; k++) {
                            if (DentalRecords[i][j][k] == null) {
                                continue;
                            } else if (DentalRecords[i][j][k] != null) {
                                if (DentalRecords[i][j][k].equals("M")) {
                                    mCount++;
                                } else if (DentalRecords[i][j][k].equals("I")) {
                                    iCount++;
                                } else if (DentalRecords[i][j][k].equals("B")) {
                                    bCount++;
                                }
                            }
                        }
                    }
                }
                // Quadratic equation = (-b Â± sqrt(D)) / 2a
                // D == b^2 - 4ac
                // Ix^2 + Bx - M
                int D = (bCount * bCount) - 4 * (iCount) * (-mCount);
                double posRootIndice = ((-bCount) + Math.sqrt(Math.abs(D))) / (2 * iCount);
                double negRootIndice = ((-bCount) - Math.sqrt(Math.abs(D))) / (2 * iCount);
                System.out.println("One root canal at " + String.format("%.2f", posRootIndice));
                System.out.println("Another root canal at " + String.format("%.2f", negRootIndice));
                System.out.println();
                System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it: ");


            } else if (input.equals("X")) {
                System.out.println();
                System.out.println("Exiting the Floridian Tooth Records :-)");
                exit = true;

            }
        }
    }

    public Boolean TeethValidity(String teeth) {
        if (teeth.length() > 8) {
            System.out.print("Too many teeth, try again: ");
            return false;
        }
        for (int i = 0; i < teeth.length(); i++) {
            char Char = teeth.charAt(i);
            if (Char != 'I' && Char != 'B' && Char != 'M') {
                System.out.print("Invalid teeth types, try again: ");
                return false;

            }

        }
        return true;
    }
}