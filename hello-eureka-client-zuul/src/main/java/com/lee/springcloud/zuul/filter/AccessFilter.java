package com.lee.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author WangLe
 * @date 2019/7/18 11:00
 * @description
 */
@Component
public class AccessFilter extends ZuulFilter {

    public static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

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
     * 当请求在一个阶段中存在多个过滤器时,需要根据该方法的返回值来依次执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * 配置是否需要过滤:true需要,false不需要
     * 实际运用中,我们可以利用该函数来指定过滤器的有效范围
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        
        String url = currentContext.getRequest().getRequestURL().toString();
        logger.info("#####请求的url为:" + url);
        if (url.contains("/api/a/") || url.contains("/client")) {
            return false;
        }
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked
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
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
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
