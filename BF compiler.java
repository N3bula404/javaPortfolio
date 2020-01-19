import java.util.*;
import java.io.*;

public class main {
    public static Scanner inputGet = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nenter the file extension to compile/run:");
        String filePath = inputGet.nextLine();
        File myFile = new File(filePath);
        String runString = "";

        if (filePath.contains(".b") || filePath.contains(".bf")) {
            try {
                Scanner fileReader = new Scanner(myFile);
                runString = getFileInfo(fileReader);
            } catch (Exception e) {
                System.out.printf("following exception was thrown: %s", e);
            } finally {
                if (!runString.equals("")) {
                    if (compile(runString)) {
                        System.out.println("\n=========================== COMPILE SUCCESS ===========================\n");
                        runtime(runString);
                    }
                    else{
                        System.out.println("\n=========================== COMPILE FAILURE ===========================\n");
                    }
                }
            }
        }
        else{System.out.println("not a valid brainfuck file (extension must be .b or .bf)");}
    }

    public static boolean compile(String StringToCheck){
        for (int i = 0; i < StringToCheck.length(); i++) {
            if (!",.+-><[]".contains(Character.toString(StringToCheck.charAt(i)))){
                System.out.printf("%s at pos %s not valid",StringToCheck.charAt(i),i+1);
                return false;
            }
        }
        return true;
    }

    public static byte[] runTimeInit(){
        byte[] returnArray = new byte[1000000];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = 0;
        }
        return returnArray;
    }

    public static void runtime(String ComString){
        byte[] runArray = runTimeInit();
        int pointer = 0;
        int i = 0;

        do{
            if (ComString.charAt(i) == ','){
                byte val = (byte)((int)(inputGet.nextLine().charAt(0)));
                runArray[pointer] = val;
                i++;
            }
            else if (ComString.charAt(i) == '.'){
                System.out.println(runArray[pointer]);
                i++;
            }
            else if (ComString.charAt(i) == '>'){
                pointer++;
                i++;
            }
            else if (ComString.charAt(i) == '<'){
                pointer--;
                i++;
            }
            else if (ComString.charAt(i) == '+'){
                runArray[pointer]++;
                i++;
            }
            else if (ComString.charAt(i) == '-'){
                runArray[pointer]--;
                i++;
            }
        }while(i < ComString.length());
    }

    public static String getFileInfo(Scanner filR){
        String runVal = "";

        while (filR.hasNextLine()){
            runVal += filR.nextLine();
        }
        return runVal;
    }
}
