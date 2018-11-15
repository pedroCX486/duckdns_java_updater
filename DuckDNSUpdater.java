import java.net.*;
import java.io.*;

public class DuckDNSUpdater {

    public static void main(String[] args) {

        String token = "YOUR_DUCK_DNS_TOKEN";
        String domain = "YOURDOMAIN";

        System.out.println("DuckDNS IP Updater Script\r\n");
		
        updateDuckDNS(domain, token);
		
        System.exit(0);
    }

    static void updateDuckDNS(String domain, String token){
        try {
            URL url = new URL("https://www.duckdns.org/update?domains="+ domain +"&token="+ token +"&ip="+ getIP());

            System.out.println("Sending request to DuckDNS...");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            if(con.getResponseCode() == 200){
                System.out.println("Success! Your IP was updated on DuckDNS!");
            }else{
                System.out.println("DuckDNS returned HTTP Error " + con.getResponseCode() + "! Try running the application again or checking your internet connection.");
            }

            con.disconnect();

        }catch (Exception e) {
            System.out.println(e);
            System.out.println("Error. Try running the application again or checking your internet connection.");
            System.exit(1);
        }
    }

    static String getIP(){
		
        String publicIP;
		
        System.out.println("Fetching your public IP address...");
		
        try{
            URL myPublicIP = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(myPublicIP.openStream()));
            publicIP = in.readLine();
        }catch (Exception e){
            System.out.println(e);
            publicIP = "Couldn't fetch IP address. Try running the application again or checking your internet connection.";
            System.out.println("Error: " + publicIP);
            System.exit(1);
        }

        System.out.println("Your public IP is: " + publicIP);
      
        return publicIP;
    }
}
