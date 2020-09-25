package cn.itcast.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

public class CrawlerTest {

    public static void main(String[] args) throws IOException {

        //open browser
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //input url
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        //send request, get response
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //analyze response, get data
        if(response.getStatusLine().getStatusCode() == 200){
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf8");
            //System.out.println(content);
        }

    }
}
