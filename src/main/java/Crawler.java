import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crawler {
//    public static String domainFull = "http://www.gansulamot.co.il";
//    public static String domain = "gansulamot.co.il";
//    public static String researchString = "gane";

//    public static String domainFull = "http://www.mit.edu/";
//    public static String domain = "mit.edu";
//    public static String researchString = "research";

    public static String domainFull = "https://en.wikipedia.org/wiki/Main_Page";     //"http://www.wikipedia.org";
//    public static String domain = "wikipedia.org";
//    public static String domain = "/wiki/";
    public static String domain = "en.wikipedia.org";
    public static String researchString = "Early life";  //Early life";

    public static DB db = new DB();

    public static void main(String[] args) throws SQLException , IOException{
        db.runSql2("Truncate records;");
        processPage(domainFull);
    }

    private static void processPage(String URL) throws SQLException , IOException {
        String sql = "select * from records where URL = '" + URL + "'";
        ResultSet rs = db.runSql(sql);
        if(rs.next()){

        } else{
            //sql = "Insert into 'records' ('URL') Values " + "(?);";
//            sql = "INSERT INTO  `records` " + "(`URL`) VALUES " + "(?);";
            sql = "INSERT INTO  records (URL) VALUES " + "(?);";
            PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,URL);
            stmt.execute();

            System.out.println("JSoap: " + URL);
            Document doc = Jsoup.connect(URL).get();

            if(doc.text().contains(researchString)){
                System.out.println("Contains research String: " + URL);
            }

            Elements questions = doc.select("a[href]");
            for(Element link: questions){
                if (link.attr("href").contains(domain) )  // use by Gan  || link.attr("href").contains("./") )
                    if( !link.attr("href").contains(".pdf"))
                        if( !link.attr("href").contains("mailto:"))
                            if( !link.attr("href").contains("purge"))
                                if( !link.attr("href").contains("donate"))
                                    if( !link.attr("href").contains("tools.wmflabs.org"))
                                      processPage(link.attr("abs:href"));
            }
        }
    }
}
