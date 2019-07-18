package com.lee.springcloud.zuul.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangLe
 * @date 2019/7/18 10:35
 * @description 服务service-consumer失败时候的回调
 */
@Component
public class ServiceConsumerFallbackProvider implements FallbackProvider {
    /**
     * 返回 serviceId,如果支持所有的服务都支持此回调,则 return "*" 或 return null;
     *
     * @return
     */
    @Override
    public String getRoute() {
        return "service-consumer";
    }

    /**
     * 如果请求服务失败,则返回指定的信息给调用者
     *
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        ClientHttpResponse httpResponse = new ClientHttpResponse() {

            /**
             * 网关向 api 服务请求失败了，但是消费者客户端向网关发起的请求是成功的，
             * 不应该把 api 的 404,500 等问题抛给客户端
             * 网关和 api 服务集群对于客户端来说是黑盒
             * @return
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            /**
             * 响应体
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                Map<String, String> result = new HashMap<>();
                result.put("code", "200");
                result.put("message", "无法连接,请检查您的网络");
                ObjectMapper objectMapper = new ObjectMapper();
                return new ByteArrayInputStream(objectMapper.writeValueAsString(result).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
        return httpResponse;
    }
}
