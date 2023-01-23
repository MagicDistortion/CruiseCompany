package filters;

import services.CheckCruiseStatus;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/* слухач сеансу встановлює мову за замовчуванням і запускає перевірку статусу круїзів  */
public class Listener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("lang", LangFilter.DEFAULT_LANGUAGE);
        CheckCruiseStatus.getInstance().checkStatus();
    }
}