import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    static String logPath = "src/Datas/logs.txt";
   static void CreateAndCheckLogFiles() throws IOException {
        File file = new File(logPath);
        if(!file.exists()){
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(writer);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - dd.MMMM.yyyy");
            printWriter.println("This file created on --> "+sdf.format(date));
            printWriter.close();
        }

    }
    public static void main(String[] args) throws IOException {
        CreateAndCheckLogFiles();
        BaseManager baseManager = new BaseManager();
        baseManager.updateCurrencies();
        baseManager.getMenu();

    }
}