package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/FiltraggioServletPrezzo")
public class FiltraggioServletPrezzo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String richiesta = (String) session.getAttribute("filtri");
        ArrayList<Prodotto> filterList = null;
        double valoreMin, valoreMax;
        if (request.getParameter("prezzomin").equals("") && request.getParameter("prezzomax").equals("")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/RicercaErrata.jsp");
            dispatcher.forward(request, response);
        }
        else if (request.getParameter("prezzomin").equals("")) {
            valoreMax = Double.parseDouble(request.getParameter("prezzomax"));
            filterList = ProdottoDAO.doRetriveByFilter(richiesta, valoreMax, 0);
        } else if (request.getParameter("prezzomax").equals("")) {
            valoreMin = Double.parseDouble(request.getParameter("prezzomin"));
            filterList = ProdottoDAO.doRetriveByFilter(richiesta, 5000, valoreMin);
        }
        else{
            valoreMin = Double.parseDouble(request.getParameter("prezzomin"));
            valoreMax = Double.parseDouble(request.getParameter("prezzomax"));
            filterList = ProdottoDAO.doRetriveByFilter(richiesta, valoreMax, valoreMin);
        }
        request.setAttribute("filtra", filterList);
        request.setAttribute("filtraggio", richiesta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/Prodotti.jsp");
        dispatcher.forward(request, response);
    }
}