import java.io.PrintStream;

public class Main
{
  public Main() {}
  
  public static void main(String[] args) {
    String token = "YOUR_DUCK_DNS_TOKEN";
    String domain = "YOURDOMAIN";
    
    System.out.println("DuckDNS IP Updater Script by Bunny - 20180922");
    System.out.println("\r\nFetching your public IP address...");
    
    updateDuckDNS(domain, token);
    System.exit(0);
  }
  
  static void updateDuckDNS(String domain, String token) {
    try {
      java.net.URL url = new java.net.URL("https://www.duckdns.org/update?domains=" + domain + "&token=" + token + "&ip=" + getIP());
      
      System.out.println("Sending request to DuckDNS...");
      
      java.net.HttpURLConnection con = (java.net.HttpURLConnection)url.openConnection();
      con.setRequestMethod("GET");
      
      if (con.getResponseCode() == 200) {
        System.out.println("Success! Your IP was updated on DuckDNS!");
      } else {
        System.out.println("Error. Try running the application again or checking your internet connection.");
      }
      
      con.disconnect();
    }
    catch (Exception e) {
      System.out.println(e);
      System.out.println("Error. Try running the application again or checking your internet connection.");
      System.exit(1);
    }
  }
  
  static String getIP() {
    String publicIP;
    try {
      java.net.URL myPublicIP = new java.net.URL("http://checkip.amazonaws.com");
      java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(myPublicIP.openStream()));
      publicIP = in.readLine();
    } catch (Exception e) { String publicIP;
      System.out.println(e);
      publicIP = "Invalid. Try running the application again or checking your internet connection.";
    }
    
    System.out.println("Your public IP is: " + publicIP);
    if (publicIP.contains("Invalid")) {
      System.exit(1);
    }
    
    return publicIP;
  }
}