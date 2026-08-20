package Controller;

import Model.Utente;
import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RendiAmministratoreServlet", value = "/RendiAmministratoreServlet")
public class RendiAmministratoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((request.getParameter("action").contains("amministratore"))) {
            String x[] = request.getParameter("action").split("amministratore");
            String val = x[1];
            UtenteDAO.rendiAmministratore(val);
            ArrayList<Utente> u=UtenteDAO.doRetriveUtente();
            request.setAttribute("riepilogoUtente",u);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/amministratore/VisualizzaUtenti.jsp");
            dispatcher.forward(request, response);
        }
        else if ((request.getParameter("action").contains("rimuovipermessi")))
        {
            String x[] = request.getParameter("action").split("rimuovipermessi");
            String val = x[1];
            UtenteDAO.rimuoviAmministratore(val);
            ArrayList<Utente> u=UtenteDAO.doRetriveUtente();
            request.setAttribute("riepilogoUtente",u);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/amministratore/VisualizzaUtenti.jsp");
            dispatcher.forward(request, response);
        }
    }
}
