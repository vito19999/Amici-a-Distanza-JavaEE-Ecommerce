package Controller;

import Model.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/DatiAcquistoVeloceServlet")
public class DatiAcquistoVeloceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = new Utente();
        u.setEmail(request.getParameter("email"));
        u.setPassword(request.getParameter("passwordEmail"));
        u.setNome(request.getParameter("nome"));
        u.setCognome(request.getParameter("cognome"));
        u.setDataDiNascita(request.getParameter("datadiNascita"));
        u.setNumeroTelefono(request.getParameter("numeroTelefono"));
        u.setCodiceFiscale(request.getParameter("codiceFiscale"));
        u.setVia(request.getParameter("via"));
        u.setCitta(request.getParameter("citta"));
        u.setCap(request.getParameter("cap"));
        u.setProvincia(request.getParameter("provincia"));
        u.setNazione(request.getParameter("nazione"));
        u.setAmministratore(false);

        if (UtenteDAO.controlloEmail(request.getParameter("email"))) {
            request.setAttribute("controllo", "Email già presente");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/AcquistoVeloce.jsp");
            dispatcher.forward(request, response);
        } else
        {
            UtenteDAO.doRegistrazione(u);

            ArrayList<Prodotto> cart_list = (ArrayList<Prodotto>) session.getAttribute("cart-list");
            ArrayList<Integer> qList = (ArrayList<Integer>) session.getAttribute("quantitaArticoli");
            int x;
            for (int i = 0; i < cart_list.size(); i++) {
                AcquistoProdottiDAO.acquistaProdotto(u.getEmail(), cart_list.get(i).getIdProdotto(), qList.get(i));
                x = cart_list.get(i).getQuantita() - qList.get(i);
                ProdottoDAO.doUpdateQuantita(x, cart_list.get(i).getIdProdotto());
            }
            Carta c = new Carta(u);
            c.setNumeroCarta(request.getParameter("NCarta"));
            c.setNomeIntestario(request.getParameter("credenziali"));
            c.setDataScadenza(request.getParameter("dataScadenza"));
            c.setCVV(request.getParameter("cvv"));
            c.setEmailProprietario(u.getEmail());
            CartaDAO.aggiuntaCredenzialiPagamento(c, u.getEmail());
            cart_list.clear();
            qList.clear();
            session.setAttribute("cart-list", cart_list);
            session.setAttribute("quantitaArticoli", qList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage");
            dispatcher.forward(request, response);
        }
    }
}
