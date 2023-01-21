package utils;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

/* Клас з кастомним тегом */
public class DateNowTag extends TagSupport {
    Logger logger = Logger.getLogger(DateNowTag.class);
    /* Видає поточну дату */
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(LocalDate.now());
        } catch (IOException e) {
            logger.error(e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
