package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
/* фільтр для всіх сторінок для налаштування кодування*/

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
        filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest) servletRequest;
        servletRequest.setCharacterEncoding("utf-8");
        String timeNow = LocalDate.now()+"T"+ LocalTime.now().getHour()+":"+LocalTime.now().getMinute();
        String timePlusOneDay = (LocalDate.now().plusDays(1))+"T"+ LocalTime.now().getHour()+":"+LocalTime.now().getMinute();
        httpServletRequest.getSession().setAttribute("timeNow",timeNow );
        httpServletRequest.getSession().setAttribute("timePlusDay",timePlusOneDay );
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
