import javax.swing.plaf.ColorUIResource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseManager {
    List <Currencies> currenciesList = new ArrayList<>();
    public void updateCurrencies() throws IOException {
        File file = new File(Main.logPath);
       if(file.exists()){
           FileReader reader = new FileReader(file);
           BufferedReader bufferedReader = new BufferedReader(reader);
           String line = bufferedReader.readLine();
           while (line!=null){
               if(line.contains("New currency")){
                   int started = line.indexOf("New currency");
                   int finished = line.indexOf("is");
                   String code ="";
                   for (int i = 0; i < finished-started-12 ; i++) {
                    code+=line.charAt(i+12);
                   }
                  code = code.trim();
                  Currencies currencies = new Currencies(code);
                  currenciesList.add(currencies);
               }
               line = bufferedReader.readLine();
           }
           bufferedReader.close();
           reader.close();
       }
    }
    Scanner scanner = new Scanner(System.in);
    private void printCurrencies(){
        int i = 1;
        System.out.println("Select your currency");
        for (Currencies c : currenciesList){
            System.out.println(i+++"- "+c.getCode());
        }
    }
public void getMenu() throws IOException {
    System.out.println("Select your operation");
    System.out.println("1-Add new currency");
    System.out.println("2-Buy");
    System.out.println("3-Sell");
    System.out.println("4-Investment proposal");
    System.out.println("5-Show all info");
    System.out.println("0-Exit");
    int selection = scanner.nextInt();
    switch (selection){
        case 1:
            System.out.println("Enter the code of the new currency");
            String code = scanner.nextLine();
            Currencies currencies = new Currencies(code);
            currenciesList.add(currencies);
            System.out.println("Successfully added.");
            getMenu();
            break;
        case 2:
            printCurrencies();
            int index = scanner.nextInt();
            System.out.println("-+-"+currenciesList.get(index-1).getCode()+"-+-");
            currenciesList.get(index-1).buy();
            getMenu();
            break;
        case 3:
            printCurrencies();
             index = scanner.nextInt();
            System.out.println("-+-"+currenciesList.get(index-1).getCode()+"-+-");
            currenciesList.get(index-1).sell();
            getMenu();
            break;
        case 4:
            printCurrencies();
            index = scanner.nextInt();
            double average = currenciesList.get(index-1).average;
            System.out.println("Safe zone: "+average*0.95 +" - "+average*1.05);
            System.out.println("The beginning of an affordable price to sell: "+average*1.20);
            break;
        case  5:
            toString();
            getMenu();
            break;
        default:
            break;


    }
}

    @Override
    public String toString(){
        String str ="";
        for (Currencies c : currenciesList){
            str+=c.toString();
            str+="\n";
        }
        return str;
    }
}
