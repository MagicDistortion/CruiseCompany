package services;

import dao.CruisesDAO;
import dao.ShipsDAO;
import models.Cruise;
import models.Ship;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/* Клас пагінатор*/
public class Paginator {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    /* метод повертає у сервлет лист лайнерів*/
    public List<Ship> paginationShipsList(HttpServletRequest req) {
        String sort = "name";
        if (req.getParameter("sort") != null) sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "name":
                sort = "ORDER BY name";
                break;
            case "capacity":
                sort = "ORDER BY capacity";
        }
        int shipsCount = shipsDAO.shipsCount();
        int pagination = getPagination(req);
        int page = getPage(req);
        getPages(req, shipsCount, pagination);
        return shipsDAO.findShipsWithLimit(sort, page, pagination);
    }

    /* метод повертає у сервлет лист круїзів*/
    public List<Cruise> paginationCruisesList(HttpServletRequest req) {
        String sort = "name";
        if (req.getParameter("sort") != null) sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "name":
                sort = "ORDER BY cruise_name";
                break;
            case "price":
                sort = "ORDER BY price";
                break;
            case "start_time":
                sort = "ORDER BY start_time";
                break;
            case "duration":
                sort = "ORDER BY duration";
        }
        int cruisesCount = cruisesDAO.CruisesCount();
        int pagination = getPagination(req);
        int page = getPage(req);
        getPages(req, cruisesCount, pagination);
        return cruisesDAO.findCruisesWithLimit(sort, page, pagination);
    }


    /* метод повертає номер сторінки, та передає його у запит*/
    private int getPage(HttpServletRequest req) {
        int page = 1;
        if (req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
        req.setAttribute("page", page);
        return page;
    }

    /* метод повертає кількість відповідей які отримає користувач на одній сторінці,також передає у запит */
    private int getPagination(HttpServletRequest req) {
        int pagination;
        if (req.getParameter("pagination") == null || Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);
        return pagination;
    }

    /* метод повертає загальну кількість сторінок, також передає її у запит*/
    private void getPages(HttpServletRequest req, int size, int pagination) {
        int pages = size / pagination;
        if (size % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);
    }
}

