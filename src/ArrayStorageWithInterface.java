import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.UUID;

public class ArrayStorageWithInterface {
    private static String[] resumes = new String[10000];
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static boolean isRunning = true;

    public static void main(String[] args) throws IOException {
        resumes[0] = "resume" + UUID.randomUUID().toString();
        resumes[1] = "resume" + UUID.randomUUID().toString();
        resumes[2] = "resume" + UUID.randomUUID().toString();

        while (isRunning) {
            printAllowedActions();
            userInteraction();
        }
    }
    public static void printAllowedActions() {
        System.out.println("Choose an option: ");
        System.out.println();
        System.out.println("To add a resume, press 1 ");
        System.out.println("To get a resume by id, press 2 ");
        System.out.println("To delete a resume by id, press 3");
        System.out.println("To get a size of filled base, press 4");
        System.out.println("To clear base, press 5");
        System.out.println("To show all resumes, press 6");
        System.out.println("To exit, write \"exit\"");
        System.out.println();
        System.out.println();
    }
    public static void userInteraction() throws IOException {
        System.out.println("Enter an option: ");
        String action = reader.readLine();
        try {
            if (Integer.parseInt(action) < 1 || Integer.parseInt(action) > 7) {
                System.out.println("Incorrect input, try again");
                System.out.println();
            } else {
                switch (action) {
                    case "1":
                        save();
                        System.out.println();
                        System.out.println();
                        break;
                    case "2":
                        get();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        break;
                    case "3":
                        delete();
                        System.out.println();
                        System.out.println();
                        break;
                    case "4":
                        System.out.println(size());
                        System.out.println();
                        System.out.println();
                        break;
                    case "5":
                        clear();
                        System.out.println();
                        System.out.println();
                        break;
                    case "6":
                        getAll();
                        System.out.println();
                        System.out.println();
                        break;
                    case "7":
                        exit();
                        break;
                }
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Incorrect input, try again");
            System.out.println();
        }
    }
    public static void save() throws IOException {
        System.out.println("Enter a new resume: ");
        String resume = reader.readLine();
        resumes[size()] = resume + UUID.randomUUID().toString();
        System.out.println("Successfully added");

    }
    public static void get() {
        System.out.println("Enter an index to get");
        String indexLine;
        boolean isGetFunctionRunning = true;

        while (isGetFunctionRunning) {
            try {
                indexLine = reader.readLine();
                int index = Integer.parseInt(indexLine);
                if (resumes[index] != null) {
                    System.out.println(resumes[index]);
                    isGetFunctionRunning = false;
                } else
                    System.out.println("There is no resume, try again");

            } catch(IOException e){
                e.printStackTrace();
            } catch(NumberFormatException e){
                System.out.println("Enter a digit, not string");
            }
        }
    }
    public static void delete() {
        System.out.println("Enter an index to delete");
        String indexLine;
        boolean isGetFunctionRunning = true;

        while (isGetFunctionRunning) {
            try {
                indexLine = reader.readLine();
                int index = Integer.parseInt(indexLine);
                if (resumes[index] != null) {
                    resumes[index] = null;
                    for (int i = index; i < resumes.length - 1; i++) {
                        resumes[i] = resumes[i + 1];
                    }
                    resumes = Arrays.copyOf(resumes, resumes.length - 1);
                    System.out.println("successfully deleted");
                    isGetFunctionRunning = false;
                } else
                    System.out.println("There is no resume, try again");

            } catch(IOException e){
                e.printStackTrace();
            } catch(NumberFormatException e){
                System.out.println("Enter a digit, not string");
            }
        }
    }
    public static int size() {
        int sizeCounter = 0;
        for (String resume : resumes) {
            if (resume != null ) {
                sizeCounter++;
            }
            else break;
        }
        return sizeCounter;
    }
    public static void clear() {
        Arrays.fill(resumes, null);
        System.out.println("successfully cleared");
    }
    public static void getAll() {
        if (size() == 0) {
            System.out.println("There are no resumes");
        }
        else
            // System.out.println(Arrays.toString(resumes));
            for (int i = 0; i < size(); i++) {
                if (resumes[i] != null) {
                    System.out.println(resumes[i]);
                }
            }
    }
    public static void exit() {
        isRunning = false;
    }
}

