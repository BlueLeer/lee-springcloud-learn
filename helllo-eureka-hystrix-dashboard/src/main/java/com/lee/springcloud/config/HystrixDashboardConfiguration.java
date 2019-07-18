package com.lee.springcloud.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangLe
 * @date 2019/7/17 18:48
 * @description
 */
@Configuration
public class HystrixDashboardConfiguration {
    /**
     * Spring Boot 2.x 版本开启 Hystrix Dashboard 与 Spring Boot 1.x 的方式略有不同，需要增加一个 HystrixMetricsStreamServlet 的配置
     * @return
     */
    @Bean
    public ServletRegistrationBean hystrixDashboardServlet() {
        HystrixMetricsStreamServlet servlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet);

        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("hystrixServlet");
        return servletRegistrationBean;
    }
}
