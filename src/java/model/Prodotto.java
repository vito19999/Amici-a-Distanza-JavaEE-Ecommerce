package Model;

public class Prodotto {
    private String idProdotto, nomeProd, descrizione, nomeCategoria;
    private double  prezzo;
    private int quantita;

    public String getIdProdotto() {
        return idProdotto;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public String getDescrizione() {
        return descrizione;
    }



    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setIdProdotto(String idProdotto) {
        this.idProdotto = idProdotto;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }


    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }


    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }


    @Override
    public String toString() {
        return "Prodotto{" +
                "idProdotto='" + idProdotto + '\'' +
                ", nomeProd='" + nomeProd + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", prezzo=" + prezzo +
                ", quantita=" + quantita +
                '}';
    }
}