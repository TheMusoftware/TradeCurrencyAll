import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Currencies {
    private String code;
    private double totalInWallet;
    private double currentPrice; // Planning feature
    void createFiles() throws IOException {
        String path = "src/Datas/"+code+".txt";
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

        createFiles();
    }
}
