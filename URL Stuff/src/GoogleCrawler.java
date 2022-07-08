import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;
import java.lang.String;

public class GoogleCrawler {
  /**
   * Method to convert the {@link InputStream} to {@link String}
   *
   * @param is the {@link InputStream} object
   * @return the {@link String} object returned
   */
	private static ArrayList<String> list = new ArrayList<String>();
	private static int key = 0;
	private static int startofCount = 0;
	private static int endofCount = 4;
	private static int wrapAround = -2;
	
  public static String getString(InputStream is) {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line;
    try {
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      /** finally block to close the {@link BufferedReader} */
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return sb.toString();
  }
  /**
   * The method will return the search page result in a {@link String} object
   *
   * @param googleSearchQuery the google search query
   * @return the content as {@link String} object
   * @throws Exception
   */
  public static String getSearchContent(String googleSearchQuery) throws Exception {
    final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    URL url = new URL(googleSearchQuery);
    final URLConnection connection = url.openConnection();
    connection.setRequestProperty("User-Agent", agent);
    final InputStream stream = connection.getInputStream();
    return getString(stream);
  }
  /**
   * Parse all links
   *
   * @param html the page
   * @return the list with all URLSs
   * @throws Exception
   */
  public static List<String> parseLinks(final String html) throws Exception {
    List<String> result = new ArrayList<String>();
    Document doc = Jsoup.parse(html);
    Elements results = doc.select("a > h3");
    for (Element link : results) {
      Elements parent = link.parent().getAllElements();
      String relHref = parent.attr("href");
      if (relHref.startsWith("/url?q=")) {
        relHref = relHref.replace("/url?q=", "");
      }
      String[] splittedString = relHref.split("&sa=");
      if (splittedString.length > 1) {
        relHref = splittedString[0];
      }
      //System.out.println(relHref);
      result.add(relHref);
    }
    return result;
  }
  
  public static List<String> searchGoogle(String searchTerm, Integer resultCount) throws Exception {
    if (resultCount == null) {
      resultCount = 10;
    }
    System.out.println("Searching for: " + searchTerm);
    String query = "https://www.google.com/search?q=" + searchTerm + "&num=" + resultCount;
    System.out.println(query);
    String page = getSearchContent(query);
    List<String> links = parseLinks(page);
    return links;
  }
  
  public static void createList(String a)
  {
	String b = "";
	int i = 0;
	while (i < a.length())
	{
		if (a.charAt(i) == ' ')
		{
			list.add(b);
			b = "";
		}
		else
		{
			b = b + a.charAt(i);
		}
		i++;
	}
	list.add(b);
  }
  
  public static String nextPage()
  {
	  String newsearchTerm = list.get(key);
		if (key == list.size() - 1)
		{
			key = 0;
			startofCount = 0;
			endofCount = 4;
		}
		if (list.size() < 6)
		{
			for (int i = 1; i < list.size();i++)
			{
				newsearchTerm += " " + list.get(i);
			}
		}
		else if (wrapAround == key || (key == 0 && endofCount == list.size() + 1))
		{
				key++;
				wrapAround = -1;
				startofCount = 0;
				endofCount = 4;
				newsearchTerm = list.get(key);
				for (int i = startofCount; i < endofCount;i++)
				{
					if (i == key)
					{
						i++;

					}
					newsearchTerm += " " + list.get(i);
				}
		}
		else if (endofCount > list.size())
		{
			wrapAround = endofCount - list.size();
			
			
			for (int i = startofCount; i < list.size();i++)
			{
				if (i == key)
				{
					i++;

				}
				newsearchTerm += " " + list.get(i);
			}
			for (int i = 0; i < wrapAround;i++)
			{
				if (i == key)
				{
					i++;
					wrapAround++;
				}
				newsearchTerm += " " + list.get(i);
			}
		}
		else
		{
			for (int i = startofCount; i < endofCount;i++)
			{
				if (i == key)
				{
					i++;
					
				}
				newsearchTerm += " " + list.get(i);
			}

		}
		startofCount++;
		endofCount++;
		return newsearchTerm;
  }
  
  
  public static void main(String[] args) throws Exception {
	Scanner scan = new Scanner(System.in);
    String searchTerm = scan.nextLine();
	System.out.println("Press Enter for the next page and stop if you would like to stop at a page");
    String pageRifle = scan.nextLine();
    while (pageRifle != "stop")
    {
    	theActualDoThing(searchTerm);
    	System.out.println("Press Enter for the next page and stop if you would like to stop at a page");
    	pageRifle = scan.nextLine();
    	
    }
    
  }
  
  public static void theActualDoThing(String a) throws Exception
  {
	  	String searchTerm = a;
	  	createList(searchTerm);
	    String newsearchTerm = nextPage();
	    System.out.println("Searching for: recipe " + newsearchTerm);
	    String query = "https://www.google.com/search?q=" + URLEncoder.encode(searchTerm, StandardCharsets.UTF_8.toString()) + "&num=10";
	    System.out.println(query);
	    String page = getSearchContent(query);
	    List<String> links = parseLinks(page);
	    System.out.println();
	    System.out.println("Results:");
	    for (int i = 0; i < links.size(); i++) {
	      System.out.println(links.get(i));
	    }
	  
  }
}