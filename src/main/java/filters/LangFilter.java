package filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
/* фільтр для всіх сторінок для перемикання мов */

public class LangFilter implements Filter {
    private static final String[] LANGS = {"UA", "EN"};
    private final Map<String, Map<String, String>> localizations = new HashMap<>();
    public static final String DEFAULT_LANGUAGE = "UA";
    final static Logger logger = Logger.getLogger(LangFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        for (String lang : LANGS) {
            try (InputStream stream = LangFilter.class.getClassLoader().getResourceAsStream("lang." + lang + ".properties")) {
                if (stream != null) {
                    Map<String, String> phrases = new HashMap<>();
                    Properties properties = new Properties();
                    properties.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
                    properties.forEach((key, value) -> phrases.put((String) key, (String) value));
                    localizations.put(lang, phrases);
                }
            } catch (IOException e) {
                logger.error("failed to load localization " + lang, e);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if (session.getAttribute("lang") == null) session.setAttribute("lang", "UA");
        servletRequest.setAttribute("phrases", localizations.get(session.getAttribute("lang").toString()));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
