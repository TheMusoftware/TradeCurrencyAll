import java.io.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public  class Currencies {
    Scanner scanner = new Scanner(System.in);
    private String code;
   private String path ;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MMMM.yyyy");
    private double totalInWallet;
    private double totalDeposit;
    private double currentPrice; // Planning feature
     double average;
    void createFiles() throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(Main.logPath,true);
            PrintWriter printWriter = new PrintWriter(writer);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MMMM.yyyy");
            printWriter.println("New currency "+ code +" is created on --> "+sdf.format(date));
            printWriter.close();
        }
    }

    public Currencies(String code) throws IOException {
        this.code = code;
        this.path = "src/Datas/"+code+".txt";
        totalInWallet = 0;
        average = 0;
        totalDeposit = 0;
        createFiles();
        update();
    }
    public  void buy() throws IOException {
        System.out.print("Enter the amount to be bought: ");
        double amount = scanner.nextDouble();
        totalInWallet+=amount;
        System.out.println();
        System.out.print("Enter the price to be bought: ");
        double price = scanner.nextDouble();
        totalDeposit+=amount*price;
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Date date = new Date();
        printWriter.println(convertLine(amount,price) + "  Buying on --> "+sdf.format(date));
        printWriter.close();
        fileWriter.close();
        calculateAverage();
    }
    public void sell() throws IOException {
        System.out.print("Enter the amount to be sold: ");
        double amount = scanner.nextDouble();
        totalInWallet-=amount;
        System.out.println();
        System.out.print("Enter the price to be sold: ");
        double price = scanner.nextDouble();
        totalDeposit-=amount*price;
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Date date = new Date();
        printWriter.println(convertLine(amount,price) + " Selling on --> "+sdf.format(date));
        printWriter.close();
        fileWriter.close();
        calculateAverage();
    }
    private String convertLine(double amount, double price){
        String str = "";
        str+="Amount: <#>"+amount+"<#> Price: <#>"+price+"<#>";
        return str;
    }
    private void calculateAverage(){
        if(totalInWallet!=0.0){
            average = totalDeposit/totalInWallet;
        }
        else average = 0;
    }
    private void update() throws IOException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line!=null){
            // index 1 Amount
            //index 3 price
            String [] datas;
            if(line.contains("Buying")){
               datas  = line.split("<#>");
                totalInWallet+=Double.parseDouble(datas[1]);
                totalDeposit+=(Double.parseDouble(datas[1])*Double.parseDouble(datas[3]));
                calculateAverage();
            }
            else if (line.contains("Selling")) {
                datas = line.split("<#>");
                totalInWallet-=Double.parseDouble(datas[1]);
                totalDeposit-=(Double.parseDouble(datas[1])*Double.parseDouble(datas[3]));
                calculateAverage();
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString(){
        String str = "---";
        str+=code.toUpperCase()+"---";
        str+="\nWallet: "+totalInWallet;
        str+="\nAverage: "+average;
        str+="\nDeposited: "+totalDeposit;
        str+="\n---"+code+"---\n";
        return str;
    }

}
