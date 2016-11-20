import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContentMapper {

    static String[] contenents = {"Asia", "Europe"};
    static String[] counties = {"Canada", "Israel", "USA", "Spain"};
    static String[] states = {"Quebec", "New York"};
    static String[] towns = {"Westmount", "Roslyn"};

//    static String[] contenents = ("Asia", "Europe");
//    static String[] counties = ("Canada", "Israel","USA","Spain");
//    static String[] states=  ("Quebec","New York");
//    static String[] towns = ("Westmount", "Roslyn");

    static String local_url = "https://en.wikipedia.org/wiki/Leonard_Cohen";
    public static DB db = new DB();

    public static void main(String[] args) throws IOException, SQLException {
        processPage(local_url);
    }


    private static void processPage(String URL) throws SQLException, IOException {

        Document doc = Jsoup.connect(URL).get();
        //System.out.print(doc.body() );

        if (doc.text().contains("Leonard")) {
            System.out.println(URL);
        }


        Elements paragraphs = doc.select("p");
        for (Element paragraph : paragraphs) {
            if (containsListedArea(paragraph.toString())) {
                System.out.println("Line::: " + paragraph);
            }
        }


    }

    static boolean containsListedArea(String phrase) {
        if (ForEachArea(phrase,contenents)) return true;
        if (ForEachArea(phrase,counties)) return true;
        if (ForEachArea(phrase,states)) return true;
        if (ForEachArea(phrase,towns)) return true;
        return false;
    }

    private static boolean ForEachArea(String phrase, String[] array) {
        for(String a: array){
            if(phrase.contains(a)) {
                return true;
            }
        }
        return false;
    }
}
