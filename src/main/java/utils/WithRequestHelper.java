package utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WithRequestHelper {
    public String getPhrase(HttpServletRequest request, String key) {
        return ((Map<?, ?>) request.getAttribute("phrases")).get(key).toString();
    }

    public void setError(HttpServletRequest request, String error) {
        request.setAttribute("error_message", getPhrase(request, error));
    }
}
