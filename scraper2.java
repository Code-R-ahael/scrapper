import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.JSONArray;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
public class scraper2 {

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
			Connection.Response res = Jsoup.connect("http://websismit.manipal.edu/websis/control/createAnonSession")
				    .data("idValue", "120905110", "birthDate", "1994-02-05")
				    .method(Connection.Method.POST)
				    .execute();

				Document doc = Jsoup.connect("http://websismit.manipal.edu/websis/control/createAnonSession")
					    .data("idValue", "120905110")
					    .data("birthDate_i18n", "1994-02-05")
					    .data("birthDate", "1994-02-05")
					    .cookies(res.cookies())
					    .ignoreContentType(true)
					    .timeout(11000)
					    .post();
				Element tile =doc.select("div.tile").first();
				Element s =tile.select("table.basic-table").first();
				String add=s.getElementsByTag("div").eq(1).text();
				String eid=s.getElementsByTag("div").eq(3).text();
				System.out.println(add);
				System.out.println(eid);
		}
		catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}

}
