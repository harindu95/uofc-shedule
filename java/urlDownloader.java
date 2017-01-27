import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;


import java.io.IOException;
import java.util.Map;
import java.util.List;

public class urlDownloader{

    /*
      urlDownloader object has getShedule(String username,String password) method  which return a Document object containing the weekly shedule
    */

    public Document getShedule(String username,String password){
        String url = "http://www.google.ca";
        String authentication_url = "https://cas.ucalgary.ca/cas/login?service=https://"
            +"my.ucalgary.ca/psp/paprd/?cmd=start&ca.ucalgary.authent.ucid=true";

       
        String token = getToken();
        Document shedule = null;
        try
            {
                Response loginForm = Jsoup.connect(authentication_url)
                    .data("username",username)
                    .data("password",password)
                    .data("lt",token)
                    .method(Connection.Method.POST)
                    .execute();
               
                Connection cnct = submitForm(loginForm.parse());
                Response authentication = cnct.execute();
                shedule = downloadShedule(authentication.cookies());
                System.out.println(shedule);
            }catch(Exception e)
            {
                System.out.println("IOERROR:" + e.toString());
            }
        
        
        return shedule;
    }
    public static void main(String[] args) {

        urlDownloader dwnloader = new urlDownloader();
        String username = "username";
        String password = "password";
        dwnloader.getShedule(username,password);
    }

    String getToken()
    {
        String url = "https://cas.ucalgary.ca/cas/login";
        try{
            Document document = Jsoup.connect(url).get();
            Element token =document.select("input[name=lt]").first();
            return token.attr("value");
        }catch(IOException e){
            return "";
        }
    }

    Connection submitForm(Document document)
    {

        FormElement form = (FormElement)document.select("form").first();
        
        return form.submit();
    }

    Document downloadShedule(Map<String,String> cookies) throws IOException{

        String document_url = "https://csprd.ucalgary.ca/psc/csprd/EMPLOYEE/CAMPUS/c/SA_LEARNER_SERVICES.SSR_SSENRL_SCHD_W.GBL?Page=SSR_SS_WEEK&Action=A&ExactKeys=Y&EMPLID=30009665&TargetFrameName=None";
        Document document = Jsoup.connect(document_url)
            .data("cookieexists", "true")
            .cookies(cookies)
            .get();
        return document;
    }
}
