import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public  class Currencies {
    Scanner scanner = new Scanner(System.in);
    private String code;
   private String path = "src/Datas/"+code+".txt";
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MMMM.yyyy");
    private double totalInWallet;
    private double totalDeposit;
    private double currentPrice; // Planning feature
    private double average;
    void createFiles() throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(Main.logPath,true);
            PrintWriter printWriter = new PrintWriter(writer);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MMMM.yyyy");
            printWriter.println("New currency "+ code +" is created on: "+sdf.format(date));
            printWriter.close();
        }
    }

    public Currencies(String code) throws IOException {
        this.code = code;
        totalInWallet = 0;
        average = 0;
        totalDeposit = 0;
        createFiles();
    }
    public  void buy() throws IOException {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        totalInWallet+=amount;
        System.out.println();
        System.out.print("Enter buying price: ");
        double price = scanner.nextDouble();
        totalDeposit+=amount*price;
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Date date = new Date();
        printWriter.println(convertLine(amount,price) + "Buying on :"+sdf.format(date));
        printWriter.close();
    }
    public void sell(){

    }
    private String convertLine(double amount, double price){
        String str = "";
        str+="Amount: <#>"+amount+"<#> Price: <#>"+price+"<#>";
        return str;
    }
}
