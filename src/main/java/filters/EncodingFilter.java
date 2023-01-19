package filters;

import javax.servlet.*;
import java.io.IOException;

/* фільтр для всіх сторінок для налаштування кодування*/

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
