package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/FindProductServlet")
public class FindProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchQuery = request.getParameter("search");
        ArrayList<Prodotto> prodottiRicerca = new ArrayList<>();
        ArrayList<Prodotto> prodotti = ProdottoDAO.doRetriveAll();
        for(Prodotto prod : prodotti){
            if(prod.getNomeProd().toLowerCase().contains(searchQuery.toLowerCase())){
                prodottiRicerca.add(prod);
            }
        }

        Gson gson = new Gson();
        String risultatiJson =  gson.toJson(prodottiRicerca);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(risultatiJson);



    }

}


