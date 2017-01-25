import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class urlDownloader{

    /*
      An example url downloder using jsoup. Coz I couldnt
      get jar loading to work, I simply extracted it.
    */
    public static void main(String[] args) {
        String url = "http://www.google.ca";
        try
            {
                Document doc = Jsoup.connect(url).get();
                System.out.println(doc.toString());
            }catch(IOException e)
            {
                System.out.println("IOERROR:");
            }
        System.out.println("Main");
    }
}
