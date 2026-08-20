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

@WebServlet("/RicercaServlet")
public class RicercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeProdotto = request.getParameter("search");
        Prodotto p = null;
        String nProd = nomeProdotto.toUpperCase();
        HttpSession session= request.getSession();
            String address;
            p = ProdottoDAO.doRetriveBySearch(nProd);
            session.setAttribute("prod", p);
            if (request.getParameter("search").equals("") || p.getNomeProd() == null) {
                address = "/WEB-INF/results/RicercaErrata.jsp";
            } else {
                address = "/WEB-INF/results/Prodotto.jsp";
            }
            request.setAttribute("prodotto", p);
            RequestDispatcher ds = request.getRequestDispatcher(address);
            ds.forward(request, response);
    }
}
