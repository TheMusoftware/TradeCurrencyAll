import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class parseData {
    private static String USD = "https://kur.doviz.com/serbest-piyasa/amerikan-dolari";
    private static String EUR = "https://kur.doviz.com/serbest-piyasa/euro";
    private static  String XAU = "https://altin.doviz.com/gram-altin";
    private static  String GBP = "https://kur.doviz.com/serbest-piyasa/sterlin";

    public static void getPrice(Currencies currency) throws IOException {
        String page = "";
        switch (currency.getCode()){
            case "USD":
                page = USD;
                break;
            case "EUR":
                page = EUR;
                break;
            case "XAU":
                page = XAU;
                break;
            case "GBP":
                page = GBP;
                break;
            default:
                break;

        }
        Document d =  Jsoup.connect(page).get();
        Elements e = d.selectXpath("/html/body/div[3]/div[2]/div[1]/div[6]/div[1]/div/div/table/tbody/tr[3]/td[3]");
        String price = e.text();
        price = price.replace(".","");
        price = price.replace(",",".");
        currency.setCurrentPrice(Double.parseDouble(price));
    }

}
