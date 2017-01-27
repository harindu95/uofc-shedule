import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;


import java.io.IOException;
import java.util.Map;
import java.util.List;

class DataExtractor {

    DataExtractor(){
        urlDownloader dwnloader = urlDownloader();
        Documet page = dwnloader.getShedule();
    }
    
}
