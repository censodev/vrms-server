package io.github.censodev.vrms.vrmsserver.web.filters;

import io.github.censodev.vrms.vrmsserver.utils.I18nUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class I18nFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        I18nUtil.setLocale(((HttpServletRequest) request).getHeader("Content-Language"));
        chain.doFilter(request, response);
    }
}
