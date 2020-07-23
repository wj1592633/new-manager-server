package com.wj.manager.security.social.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GithubAuthenticateController {

    String url = "https://github.com/login/oauth/access_token";

    @GetMapping("/authorization_code")
    public Object applyToken(@RequestParam("code") String code,
                             @RequestParam("state") String state) throws JsonProcessingException {
        String token = apply(code, state);
        Map user = getGithubUser(token);
        return user;
    }

    //换取github的token
    private String apply(String code, String state) throws JsonProcessingException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        HashMap<String, String> map = new HashMap<>();
        map.put("client_id","bf4b55764c68e9eee987");
        map.put("client_secret","c5cf0cfc16bd38a9313973365ef31a3029280611");
        map.put("code",code);
        map.put("redirect_uri","http://localhost:8080/authorization_code");
        map.put("state",state);
        ObjectMapper mapper = new ObjectMapper();
        //将accessTokenDto转为json字符串传入参数
        RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(map));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            //得到的是类似这样的字符串，我们需要将它分割，只要access_token部分
            //access_token=bcd6f317da5fec54c8626e16f0374fc1ae8d70a9&scope=&token_type=bearer
            //access_token=9566ba3483a556c610be42d44338f3fd16a3b8d1&scope=&token_type=bearer
            return str.split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据access_token获取用户信息
     */
    public Map getGithubUser(String access_token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + access_token)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        try (Response response = client.newCall(request).execute()) {
            //得到的是json字符串，因此需要转为GithubUser对象
            return mapper.readValue(response.body().string(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
