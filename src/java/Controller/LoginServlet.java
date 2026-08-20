package Controller;

import Model.AcquistoProdotti;
import Model.AcquistoProdottiDAO;
import Model.Utente;
import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametri;
        HttpSession session = request.getSession();
        Utente u = UtenteDAO.doLogin(request.getParameter("Email"), request.getParameter("Password"));
        if(request.getParameter("action")==null)
        {
            if (u == null) {
                parametri = "Email o password errati!";
                request.setAttribute("parametri", parametri);
                RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/results/Login.jsp");
                rs.include(request, response);
            } else if (u != null && !u.isAmministratore()) {
                session.setAttribute("Utente", u);
                RequestDispatcher ds = request.getRequestDispatcher("HomePage");
                ds.forward(request, response);
            } else if (u != null && u.isAmministratore()) {
                session.setAttribute("Amministratore", u);
                RequestDispatcher ds = request.getRequestDispatcher("HomeServletAmministratore");
                ds.forward(request, response);
            }
        }
        else if(request.getParameter("action").equals("logout"))
        {
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage");
            dispatcher.forward(request, response);
        }
        else if(request.getParameter("action").equals("carrello"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/Carrello.jsp");
            dispatcher.forward(request, response);
        }
        else if(request.getParameter("action").equals("riepilogo"))
        {
            Utente utente= (Utente) session.getAttribute("Utente");
            ArrayList<AcquistoProdotti> riepilogoProdotti= AcquistoProdottiDAO.doRetriveAcquistoUtente(utente.getEmail());
            request.setAttribute("riepiloOrdineUtente",riepilogoProdotti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/RiepilogoAcquisti.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}