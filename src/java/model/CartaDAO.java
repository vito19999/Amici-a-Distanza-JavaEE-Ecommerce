package Model;

import java.sql.*;
import java.util.ArrayList;

public class CartaDAO {
    public static void aggiuntaCredenzialiPagamento(Carta p,String email) {
        ArrayList<String> cartaCredito = new ArrayList<String>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT numeroCarta FROM CartaDiCredito");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carta ap = new Carta(new Utente());
                ap.setNumeroCarta(rs.getString(1));
                cartaCredito.add(ap.getNumeroCarta());
            }
            int f=0;
            for(int i=0;i<cartaCredito.size();i++)
            {
                if(cartaCredito.get(i).equals(p.getNumeroCarta()))
                {
                    f=1;
                    break;
                }
            }
            if(f==0)
            {
                PreparedStatement ps1 = con.prepareStatement("INSERT INTO CartaDiCredito (numeroCarta,nomeIntestatario,dataScadenza,CVV,emailProprietario) VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps1.setString(1, p.getNumeroCarta());
                ps1.setString(2, p.getNomeIntestario());
                ps1.setString(3, p.getDataScadenza());
                ps1.setString(4, p.getCVV());
                ps1.setString(5, email);
                if (ps1.executeUpdate() != 1)
                    throw new RuntimeException("Errore nel definire il metodo di pagamento");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
