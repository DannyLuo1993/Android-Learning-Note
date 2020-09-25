package cn.itcast.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetParaTest {

    public static void main(String[] args) throws URISyntaxException {
        //new HttpClient Object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //Create URI builder
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        //Set URI para
        uriBuilder.setParameter("keys", "java");

        //send http request
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        System.out.println("info of the request" + httpGet);
        CloseableHttpResponse response = null;
        //handle http request
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
