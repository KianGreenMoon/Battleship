/*
 * Copyleft KianGreenMoon (@) 2019. All Rights destroyed. Sorry for that ;(
 */

package bones;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TelegramAPI {
    private final String USER_AGENT = "Mozilla/5.0";
    private String token;
    private String chat_id;

    public TelegramAPI(String token, String chat_id) {
        System.setProperty("java.net.useSystemProxies", "true");
        this.token = token;
        this.chat_id = "chat_id=" + chat_id;
    }

    public void sendMessage(String text) throws IOException {
        String method = "/sendmessage";
        text = "text=" + text;

        String url = "https://api.telegram.org/bot" + token + method;
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        //Параметры по=хорошему сюда надо вставлять
        String urlParameters = chat_id + "&" + text;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }
}
