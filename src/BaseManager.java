import javax.swing.plaf.ColorUIResource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
}
