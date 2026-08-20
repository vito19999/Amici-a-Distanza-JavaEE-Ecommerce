# Amici-a-Distanza-JavaEE-Ecommerce
Piattaforma e-commerce Full-Stack (Java EE, Servlet, JSP, MySQL) per l'adozione a distanza di animali in via d'estinzione e la vendita di merchandising solidale. Architettura MVC. Full-Stack e-commerce platform (Java EE, Servlet, JSP, MySQL) for distance adoption of endangered animals and charity merchandising. MVC architecture.  README.md


**Versione Italiana**

"Amici a Distanza" è un progetto universitario (Sviluppo Web) che implementa una piattaforma **e-commerce Full-Stack** in **Java (Jakarta EE)**. Il sito è stato progettato per sensibilizzare l'utenza sulla salvaguardia degli animali in via di estinzione. Gli utenti possono contribuire alla causa in tre modi: effettuando un'adozione a distanza (singola o multipla), acquistando merchandising solidale (peluche, tazze, t-shirt, poster) o scegliendo adozioni "Speciali" legate a storie specifiche.

###  Architettura di Sistema
Il progetto è basato sul design pattern **Model-View-Controller (MVC)** e strutturato tramite **Maven**:

* **Model (Database & DAO):** Il database relazionale è gestito tramite **MySQL**. Le tabelle (`Cliente`, `Prodotto`, `Acquistare`, `CartaDiCredito`) sono interrogate tramite il pattern DAO (Data Access Object) utilizzando JDBC (`mysql-connector-java`) e il Connection Pooling di Tomcat (`tomcat-jdbc`).
* **Controller (Servlet):** Il backend è interamente sviluppato tramite **Java Servlets** (`jakarta.servlet-api`)[cite: 14]. Le servlet gestiscono la logica di business: dall'autenticazione (`LoginServlet`, `RegistrazioneServlet`) alla gestione del carrello (`CarrelloServlet`), fino all'elaborazione asincrona delle ricerche (`FindProductServlet` che ritorna file JSON tramite la libreria **Gson**).
* **View (Frontend):** Pagine dinamiche renderizzate lato server tramite **JSP** (JavaServer Pages) e tag library JSTL. L'interfaccia utente è customizzata con fogli di stile CSS dedicati e organizzata modularmente (es. navbar e footer riutilizzabili).

###  Funzionalità Principali
**Lato Utente:**
* **Navigazione e Ricerca:** Esplorazione del catalogo tramite categorie e barra di ricerca asincrona. I prodotti possono essere filtrati per fascia di prezzo (`FiltraggioServletPrezzo`).
* **Gestione Carrello (Sessioni):** Il carrello è gestito tramite `HttpSession`. Gli utenti possono aggiungere, modificare o rimuovere articoli in tempo reale.
* **Checkout:** Processo di acquisto disponibile sia per gli utenti registrati che per gli ospiti ("Acquisto Veloce" con registrazione automatica contestuale). Le password sono hashate in SHA-1.

**Lato Amministratore:**
* L'accesso alla dashboard di amministrazione è protetto. Gli admin possono gestire l'inventario aggiungendo, modificando prezzi/quantità o eliminando prodotti dal catalogo.
* Monitoraggio in tempo reale degli ordini effettuati, dei prodotti in esaurimento e dell'anagrafica utenti (con possibilità di elevare o rimuovere i privilegi di admin di altri utenti).

---

🇬🇧 **English Version**

"Amici a Distanza" is a university Web Development project that implements a **Full-Stack e-commerce platform** using **Java (Jakarta EE)**. The website aims to raise awareness about the protection of endangered animals. Users can support the cause by making distance adoptions (single or multiple), purchasing charity merchandise (plush toys, mugs, t-shirts, posters), or choosing "Special" adoptions linked to specific backstories.

###  System Architecture
The project follows the **Model-View-Controller (MVC)** design pattern and is built with **Maven**:

* **Model (Database & DAO):** The relational database is managed via **MySQL**. Tables (`Cliente`, `Prodotto`, `Acquistare`, `CartaDiCredito`) are queried using the DAO (Data Access Object) pattern via JDBC (`mysql-connector-java`) and Tomcat Connection Pooling.
* **Controller (Servlets):** The backend relies entirely on **Java Servlets** (`jakarta.servlet-api`)[cite: 14]. They handle the core business logic: authentication (`LoginServlet`, `RegistrazioneServlet`), cart management (`CarrelloServlet`), and asynchronous search requests (`FindProductServlet` which returns JSON data using the **Gson** library).
* **View (Frontend):** Dynamic pages are server-side rendered using **JSP** (JavaServer Pages) and JSTL[cite: 14]. The UI is styled with custom CSS and structured modularly (e.g., reusable navbar and footer).

###  Key Features
**User Side:**
* **Browsing and Search:** Catalog exploration by category and asynchronous search bar. Products can be filtered by price range.
* **Cart Management (Sessions):** The shopping cart relies on `HttpSession`. Users can add, modify, or remove items in real-time.
* **Checkout:** Purchasing is available for both registered users and guests ("Fast Checkout" with on-the-fly registration). Passwords are SHA-1 hashed.

**Admin Side:**
* Protected access to the admin dashboard. Admins can manage inventory by adding, modifying prices/quantities, or deleting products.
* Real-time monitoring of placed orders, out-of-stock items, and user registry (with the ability to grant or revoke admin privileges).
