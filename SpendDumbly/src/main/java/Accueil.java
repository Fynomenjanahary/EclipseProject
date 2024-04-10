import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Driver;
import wallet.Info;

@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        
        String page = request.getRequestURI();
        
//        System.out.println(" url : "+page);
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("pass", pass);

        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        	// Driver driver = new com.mysql.cj.jdbc.Driver();
        	// DriverManager.registerDriver(driver);
            // Créer la connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "20050412");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "walletwatch", "walie3313");
            
            // Créer un état de connexion
            Statement st = con.createStatement();

            // Créer une requête de sélection
            ResultSet res = st.executeQuery("SELECT * FROM inscrit WHERE utilisateur='" + user + "' AND motdepass='" + pass + "'");

            if (res.next()) {
                PreparedStatement prepa = con.prepareStatement("SELECT * FROM depense WHERE id_inscrit =?");
                int id = res.getInt("id_inscrit");
                prepa.setInt(1, id);
                
                ResultSet info = prepa.executeQuery();
                
                // Créer une liste pour stocker les données de la base de données
                ArrayList<String[]> depense = new ArrayList<>();
                while (info.next()) {
                    // Stocker chaque ligne de données dans un tableau
                    String[] row = new String[5]; // ajustez la taille du tableau en fonction du nombre de colonnes
                    row[0] = info.getString(1);
                    row[1] = info.getString(3);
                    row[2] = info.getString(4);
                    row[3] = info.getString(5);
                    row[4] = info.getString(6);
                    
                    // Ajouter le tableau à la liste
//                    System.out.println(row[1]+row[2]+row[3]);
                    depense.add(row);
                }
                
                // Convertir la liste en tableau 2D
                String[][] depenseArray = new String[depense.size()][5]; // ajustez la taille en fonction du nombre de lignes et de colonnes
                for (int i = 0; i < depense.size(); i++) {
                    depenseArray[i] = depense.get(i);
                }
                
                // Passer le tableau 2D à la JSP
//                request.setAttribute("user", user);
                request.setAttribute("info", depenseArray);
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            } else {
                // L'utilisateur n'est pas inscrit, rediriger vers une page HTML
                response.sendRedirect("./connexion.html");
            }

            // Fermer les ressources
            res.close();
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR : " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
