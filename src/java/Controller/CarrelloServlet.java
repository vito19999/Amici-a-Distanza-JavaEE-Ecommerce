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

@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("Utente");
        ArrayList<Prodotto> cartList = new ArrayList<Prodotto>();
        ArrayList<Integer> qList = new ArrayList<Integer>();
        if(u!=null)
        {
            ArrayList<Prodotto> cart_list = (ArrayList<Prodotto>) session.getAttribute("cart-list");
            ArrayList<Integer> q_List = (ArrayList<Integer>)session.getAttribute("quantitaArticoli");
            Prodotto p = (Prodotto) session.getAttribute("prod");
            String id = p.getIdProdotto();
            if (cart_list == null && q_List==null)
            {
                cartList.add(p);
                qList.add(Integer.valueOf(request.getParameter("quantita")));
                session.setAttribute("cart-list", cartList);
                session.setAttribute("quantitaArticoli",qList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage");
                dispatcher.forward(request, response);
            }
            else
            {
                cartList = cart_list;
                qList=q_List;
                boolean exist = false;
                for(int i=0;i<cart_list.size();i++)
                {
                    if (cart_list.get(i).getIdProdotto().equals(id) && request.getParameter("quantita")!=null)
                    {
                        exist = true;
                        int qval = qList.get(i);
                        qval+=Integer.valueOf((request.getParameter("quantita")));
                        qList.set(i,qval);
                    }
                }
                if (!exist && request.getParameter("quantita")!=null)
                {
                    cartList.add(p);
                    qList.add(Integer.valueOf(request.getParameter("quantita")));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage");
                    dispatcher.forward(request, response);
                }
                else
                {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("HomePage");
                    dispatcher.forward(request, response);
                }
            }
        }
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/Login.jsp");
            dispatcher.forward(request, response);
        }

    }
}