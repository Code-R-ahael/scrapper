package check;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.text.ParseException;
//import org.json.simple.parser.JSONParser;

import java.io.*;

public class scraper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		Document doc = Jsoup.connect("http://mitfiles.org/att/a.php?u=120905110&p=1994-02-05").timeout(0).get();
		print(doc.toString());
		//JSONObject jsonObject = (JSONObject) jsonParser.parse(doc.toString());

		}
		catch(Exception e)
		{
			System.out.println("ERROR"+e);
		}
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

}
