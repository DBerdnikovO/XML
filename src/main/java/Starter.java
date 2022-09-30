import java.util.Scanner;

public class Starter {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input a input file name: ");
        String fileIntPut = in.nextLine();

        Scanner out = new Scanner(System.in);
        System.out.print("Input a outputfile name: ");
        String fileOutPut = out.nextLine();

        new XMLReader(fileIntPut);
        new XMLCreator(fileOutPut);


    }

}
