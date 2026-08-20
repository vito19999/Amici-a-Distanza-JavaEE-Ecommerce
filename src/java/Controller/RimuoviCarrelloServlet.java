package Controller;

import Model.Prodotto;
import Model.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
@WebServlet("/RimuoviCarrelloServlet")
public class RimuoviCarrelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("Utente");
        ArrayList<Prodotto> cartList = (ArrayList<Prodotto>) session.getAttribute("cart-list");
        ArrayList<Integer> qList = (ArrayList<Integer>)session.getAttribute("quantitaArticoli");

        if ((request.getParameter("action").contains("rimuovi"))) {
            String x[] = request.getParameter("action").split("rimuovi");
            String val = x[1];
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getIdProdotto().equalsIgnoreCase(val)) {
                    cartList.remove(i);
                    qList.remove(i);
                    session.setAttribute("cart-list", cartList);
                    session.setAttribute("quantitaArticoli", qList);
                }
            }
            RequestDispatcher dispatcher1 = request.getRequestDispatcher("HomePage");
            dispatcher1.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/Login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
