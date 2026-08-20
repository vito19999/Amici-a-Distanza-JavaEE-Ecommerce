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

@WebServlet(name = "DatiPagamentoServlet", value = "/DatiPagamentoServlet")
public class DatiPagamentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        Utente u= (Utente) session.getAttribute("Utente");
        ArrayList<Prodotto> cart_list = (ArrayList<Prodotto>) session.getAttribute("cart-list");
        ArrayList<Integer> qList = (ArrayList<Integer>) session.getAttribute("quantitaArticoli");
        int x;
        for(int i=0;i<cart_list.size();i++)
        {
            AcquistoProdottiDAO.acquistaProdotto(u.getEmail(),cart_list.get(i).getIdProdotto(),qList.get(i));
            x= cart_list.get(i).getQuantita()-qList.get(i);
            ProdottoDAO.doUpdateQuantita(x,cart_list.get(i).getIdProdotto());
        }
        Carta c = new Carta(u);
        c.setNumeroCarta(request.getParameter("NCarta"));
        c.setNomeIntestario(request.getParameter("credenziali"));
        c.setDataScadenza(request.getParameter("dataScadenza"));
        c.setCVV(request.getParameter("cvv"));
        c.setEmailProprietario(u.getEmail());
        CartaDAO.aggiuntaCredenzialiPagamento(c,u.getEmail());
        cart_list.clear();
        qList.clear();
        session.setAttribute("cart-list", cart_list);
        session.setAttribute("quantitaArticoli", qList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage");
        dispatcher.forward(request, response);
    }
}
