import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


// currently not in use

public class ReadFromWeb {
    public static void main(String[] args)  throws IOException {
//        String url = "http://gansulamot.co.il/index.php";
        String url = "https://en.wikipedia.org/wiki/Main_Page";
        readFromWeb(url);
//        readWeb2(url);
    }

    private static void readFromWeb(String webUrl) throws  IOException {
        URL url = new URL(webUrl);
        InputStream is = url.openStream();
        try(BufferedReader br = new BufferedReader(new InputStreamReader((is)))) {
            String line;
            while ((line=br.readLine()) != null){
                System.out.println(line);
            }
        }
        catch(MalformedURLException e){
            e.printStackTrace();
            throw new MalformedURLException("URL is malformed!!");
        }
        catch(IOException e){
            e.printStackTrace();
            throw new IOException();
        }
    }

    private static void readWeb2(String webUrl) throws IOException{
        URL url = new URL(webUrl);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while( (line = br.readLine()) != null) {
            System.out.println(line);
        }


    }
}
