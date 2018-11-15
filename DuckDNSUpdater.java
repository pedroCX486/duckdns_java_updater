import java.io.PrintStream;

public class DuckDNSUpdater{
 
  public static void main(String[] args) {
        String token = "YOUR_DUCK_DNS_TOKEN"; //Your DuckDNS token, present on your profile
        String domain = "YOURDOMAIN"; //Your domain name, eg "myhome" instead of "myhome.duckdns.org"
        
        System.out.println("DuckDNS IP Updater Script");
        
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
        
        System.out.println("\r\nFetching your public IP address...");

        try {
            java.net.URL myPublicIP = new java.net.URL("http://checkip.amazonaws.com");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(myPublicIP.openStream()));
            publicIP = in.readLine();
        } catch (Exception e) {
            System.out.println(e);
            publicIP = "Error. Couldn't fetch your public IP address. Try running the application again or checking your internet connection.";
        }

        if (publicIP.contains("Error")) {
            System.out.println(publicIP);
            System.exit(1);
        }else{
            System.out.println("Your public IP is: " + publicIP);
        }
        
        return publicIP;
    }
}
