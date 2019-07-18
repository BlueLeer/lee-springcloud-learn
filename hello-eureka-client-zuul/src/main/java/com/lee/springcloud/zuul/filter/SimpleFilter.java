package com.lee.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author WangLe
 * @date 2019/7/18 11:00
 * @description
 */
@Component
public class SimpleFilter extends ZuulFilter {

    public static final Logger logger = LoggerFactory.getLogger(SimpleFilter.class);

    /**
     * 配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 配置过滤顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 配置是否需要过滤:true需要,false不需要
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * If this value is true then the response should be sent to the client
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info("请求内容:{}", request.getRequestURL().toString());

        String token = request.getParameter("token");
        if (token == null) {
            // 不对该请求路由
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                // 直接写到请求端
                currentContext.getResponse().getWriter().write("Token is null!");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("拦截:{}", e);
            }
        } else {
            logger.info("OK");
        }
        return null;
    }
}
