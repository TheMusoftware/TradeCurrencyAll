import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.plaf.ColorUIResource;
import java.io.IOException;

public class parseData {
    private static String USD = "https://kur.doviz.com/serbest-piyasa/amerikan-dolari";
    private static String EUR = "https://kur.doviz.com/serbest-piyasa/euro";
    private static  String XAU = "https://altin.doviz.com/gram-altin";
    private static  String GBP = "https://kur.doviz.com/serbest-piyasa/sterlin";

    public static void getPrice(String code) throws IOException {
        String page = "";
        switch (code){
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
        //Element element = getElementByXPath("/html/body/div[3]/div[2]/div[1]/div[6]/div[1]/div/div/table/tbody/tr[3]/td[3]",d);
       Elements e = d.selectXpath("/html/body/div[3]/div[2]/div[1]/div[6]/div[1]/div/div/table/tbody/tr[3]/td[3]");
        System.out.println(e.text());
    }
    public static Element getElementByXPath(String xpath, Document doc) {

        String[] tags = xpath.substring(11, xpath.length()).split("/"); // ommiting /html/body/
        Element clt = doc.body();

        int index;
        int divc = 0;
        boolean done = false;
        for (String tag : tags) {
            if (tag.startsWith("div"))
                divc++;//  ww w  .java  2 s. c o m

            index = 0;
            int bindex = tag.indexOf('[');
            int eindex = tag.indexOf(']');
            if (bindex != -1) {
                index = Integer.parseInt(tag.substring(bindex + 1, eindex)) - 1;
                tag = tag.substring(0, bindex);
            }

            try {
                clt = clt.select(">" + tag).get(index);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(xpath + " is not valid.");
                clt = null;
                break;
            }

            if (tag.equals("table"))
                clt = clt.select(">tbody").get(0);

        }

        return clt;
    }
}
