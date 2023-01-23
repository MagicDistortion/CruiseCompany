package utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/* Клас допомагає працювати із запитами */
public class RequestAssistant {
    /* метод полегшує отримання фраз з мапи локалізації за ключем  */
    public String getPhrase(HttpServletRequest request, String key) {
        return ((Map<?, ?>) request.getAttribute("phrases")).get(key).toString();
    }
    /* метод полегшує додавання помилок у запит  */
    public void setError(HttpServletRequest request, String error) {
        request.setAttribute("error_message", getPhrase(request, error));
    }
}
