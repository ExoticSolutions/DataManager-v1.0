
//Allows you to login
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class Main{
    static ArrayList<String> dataImported = new ArrayList<String>();

    public static void main(String[] args)throws IOException{
        importData();

        System.out.print("\nWELCOME TO DATAFINDER v1.0\n\n");
        showMenu();
        int menuInput = getMenuInput();
        checkInput(menuInput);
        updateData();
    }

    public static void updateData()throws IOException{
        int i;
        FileOutputStream newDataFile = new FileOutputStream("Emails.txt");
        PrintWriter inFs = new PrintWriter(newDataFile);

        for(i = 0; i < dataImported.size(); ++i){
            inFs.println(dataImported.get(i));
        }
        inFs.close();
        newDataFile.close();
    }

    public static void checkInput(int userInput)throws IOException{
        switch(userInput){
            
            case 1:
            getTarget();
            break;

            case 2:
            runReplacer();
            break;

            case 3:
            runAppender();
            break;

            default:
            main(null);
            break;

        }
    }
    

    public static int getMenuInput(){
        Scanner menuInput = new Scanner(System.in);
        System.out.print("\n\nSELECT:");
        int input = menuInput.nextInt();

        return input;
    }

    public static void showMenu(){
        System.out.print("1. Search Data\n\n2. Replace Data\n\n3.Add Data");
    }

    public static void importData()throws IOException{
        FileInputStream dataFile = new FileInputStream("Emails.txt");
        Scanner scnr = new Scanner(dataFile);
        int instance = 0;

        while (scnr.hasNextLine()){
            String temp = scnr.nextLine();
            dataImported.add(instance, temp);
        }
        dataFile.close();

    }

    public static void getTarget(){
        int i;
        Scanner scnr = new Scanner(System.in);
        System.out.print("\n\nENTER TARGET DATA:");
        String target;
        int found = 0;
        target = scnr.next();

        for (i = 0; i < dataImported.size(); ++i){
            if (dataImported.get(i).contains(target)){
                System.out.println("SUCCESS DATA FOUND: " + dataImported.get(i));
                ++found;
            }
        }

        System.out.print("TOTAL FOUND: " + found);
    }

    public static void runReplacer(){
        int i;
        Scanner scnr = new Scanner(System.in);
        System.out.print("\n\nENTER DATA TO REPLACE: ");
        boolean isFound = false;

        String target = scnr.next();

        System.out.print("\n\nENTER NEW DATA:");
        String replacor = scnr.next();

        for (i = 0; i < dataImported.size(); ++i){
            if (dataImported.get(i).contains(target)){
                dataImported.set(i, replacor);
            }
        }  
    }

    public static void runAppender(){
        Scanner scnr = new Scanner(System.in);
        System.out.print("\n\nENTER DATA TO APPEND: ");
        String newData = scnr.next();

        dataImported.add(newData);
    }
}