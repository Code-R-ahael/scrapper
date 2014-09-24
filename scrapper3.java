import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.JSONArray;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;
public class scrapper3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		/*Document doc = Jsoup.connect("http://websismit.manipal.edu/websis/control/createAnonSession")
							.data("idValue", "120905110", "birthDate_i18n", "1994-02-05", "birthDate", "1994-02-05")
							.cookie("auth", "token")
							.timeout(9000)
				            .ignoreContentType(true)
				            .userAgent("Google Chrome")
				            .post();*/
			Map<String, String> cookies = new HashMap<String, String>();
			Connection.Response res = Jsoup.connect("http://websismit.manipal.edu/websis/control/createAnonSession")
				    .data("idValue", "120905110", "birthDate", "1994-02-05")
				    .method(Connection.Method.POST)
				    .execute();
			
				Document doc = Jsoup.connect("http://websismit.manipal.edu/websis/control/createAnonSession")
					    .data("idValue", "120905110")
					    .data("birthDate_i18n", "1994-02-05")
					    .data("birthDate", "1994-02-05")
					    .ignoreContentType(true)
					    .timeout(11000)
					    .post();
				for (Entry<String, String> cookie : cookies.entrySet()) {
				    res.cookie(cookie.getKey(), cookie.getValue());
				    //System.out.println(cookie.getKey()+cookie.getValue());
				}
				Elements links = doc.select("a[href]");
				String acSec="";
		        for (Element link : links) {
		        	if(link.attr("href").contains("Academic"))
		        		acSec=link.attr("href");
		        	 //System.out.println(acSec);
		        //System.out.println("\nlink : " + link.attr("href"));

		        }
		       
		        String acLk="http://websismit.manipal.edu/websis/control"+acSec;
		        //System.out.println(acLk);
		        Connection.Response res2 = Jsoup.connect(acLk)
					    .execute();
		        
		        cookies.putAll(res2.cookies());
		        Document acdoc = res2.parse();
					    /*.ignoreContentType(true)
					    .timeout(11000)
					    .post();*/
		        System.out.println(acdoc.toString());
				/*Element tile =doc.select("div.tile").first();
				Element s =tile.select("table.basic-table").first();
				String add=s.getElementsByTag("div").eq(1).text();
				String eid=s.getElementsByTag("div").eq(3).text();
				System.out.println(add);
				System.out.println(eid);*/
		}
		catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}

}
