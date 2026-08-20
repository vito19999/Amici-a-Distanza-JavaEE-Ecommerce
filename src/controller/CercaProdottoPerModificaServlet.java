package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CercaProdottoPerModificaServlet")
public class CercaProdottoPerModificaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String x = request.getParameter("search");
            Prodotto pmod = ProdottoDAO.doRetriveBySearch(x);
            request.setAttribute("prodottoModifica", pmod);
            RequestDispatcher ds = request.getRequestDispatcher("/WEB-INF/amministratore/ModificaProdotto.jsp");
            ds.forward(request, response);
    }
}
