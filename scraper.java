package check;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.JSONArray;


//import net.sf.json.JSONObject;
import java.text.ParseException;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class scraper {
	class MitFiles
	{
		String name;
		String subject[][]=new String[6][6];
		double gpa[]=new double[4];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		/*Document doc = Jsoup.connect("http://mitfiles.org/att/a.php?u=120905110&p=1994-02-05").timeout(6000).get();
		//print(doc.toString());*/
			scraper sc=new scraper();
			MitFiles m1=sc.new MitFiles();
			String json = Jsoup.connect("http://mitfiles.org/att/a.php?u=120905110&p=1994-02-05").timeout(6000).ignoreContentType(true).execute().body();
			print(json);
			JSONParser jp=new JSONParser();
			JSONObject jsonObject = (JSONObject)jp.parse(json);
			m1.name = (String) jsonObject.get("n");
			System.out.println("Name: " +m1.name);
			JSONArray sub= (JSONArray) jsonObject.get("att");
			Iterator<JSONArray> i1 = sub.iterator();int j=0;
			while (i1.hasNext()) {
				JSONArray innerArr = (JSONArray) i1.next();
				m1.subject[j][0]=(String)innerArr.get(0);
				m1.subject[j][1]=(String)innerArr.get(1);
				m1.subject[j][2]=(String)innerArr.get(2);
				m1.subject[j][3]=(String)innerArr.get(3);
				m1.subject[j][4]=(String)innerArr.get(4);
				m1.subject[j][5]=(String)innerArr.get(5);
				print("Subject:"+m1.subject[j][0]);
				print("No. of classes:"+m1.subject[j][1]+" Classes attended:"+m1.subject[j][2]+" Absent:"+m1.subject[j][3]+" Percentage:"+m1.subject[j][4]+" Updated on: "+m1.subject[j][5]);
			    j++;
			}
			JSONArray gpa= (JSONArray) jsonObject.get("gpa");
			print("GPA:");
			for(j=0;j<gpa.size();j++)
			{
				m1.gpa[j]=Double.parseDouble((String)gpa.get(j));
				System.out.print(m1.gpa[j]+", ");
			}
			



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
