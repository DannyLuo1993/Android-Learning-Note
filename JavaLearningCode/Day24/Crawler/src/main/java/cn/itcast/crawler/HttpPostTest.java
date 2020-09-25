package cn.itcast.crawler;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpPostTest {

    public static void main(String[] args){
        //Create httpclient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //Create httpPost object
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");

        CloseableHttpResponse httpResponse = null;
        
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
