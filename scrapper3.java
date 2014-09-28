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
				//System.out.println(doc.toString());
				/*for (Entry<String, String> cookie : cookies.entrySet()) {
				    res.cookie(cookie.getKey(), cookie.getValue());
				    //System.out.println(cookie.getKey()+cookie.getValue());
				}*/
				/*Elements links = doc.select("a[href]");
				String acSec="";
		        for (Element link : links) {
		        	if(link.attr("href").contains("Academic"))
		        		acSec=link.attr("href");
		        	 //System.out.println(acSec);
		        //System.out.println("\nlink : " + link.attr("href"));

		        }
		        //System.out.println(acSec);
		        String acLk="http://websismit.manipal.edu/websis/control"+acSec;
		        //System.out.println(acLk);*/
		        cookies=res.cookies();
		      
		        Document acdoc = Jsoup.connect("http://websismit.manipal.edu/websis/control/StudentAcademicProfile")
		        		.cookie("JSESSIONID",cookies.get("JSESSIONID"))
		        		.cookie("OFBiz.Visitor", cookies.get("OFBiz.Visitor"))
		        		.timeout(6000)
		        		.get();
				Element tile =acdoc.select("div.screenlet-body").get(1);
				Element s =tile.getElementById("ListAttendanceSummary_table");
				String sub[]=new String[6];
				for(int i=0;i<6;i++)
				{
					sub[i]=s.getElementsByTag("tr").eq(i+1).text();
					System.out.println(sub[i]);
				}
		}
		catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}

}
