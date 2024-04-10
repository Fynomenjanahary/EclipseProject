import wallet.DataBase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class Singup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		
		String user = request.getParameter("user");
        String pass = request.getParameter("anana");
        String mail = request.getParameter("mail");
//
//        try {
//            // Charger le driver MySQL
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Créer la connexion à la base de données
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "123456");
////            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "walletwatch", "walie3313");
//            
//            // Créer un état de connexion
//            Statement st = con.createStatement();
//            
//                PreparedStatement prepa = con.prepareStatement("insert to inscrit (utilisateur,mail,motdepass) values (?,?,?)");
//               
//                prepa.setString(1, user);
//                prepa.setString(2, mail);
//                prepa.setString(1, pass);
//                
//                prepa.executeUpdate();
//        
//                response.sendRedirect("http://localhost:8080/WalletWatch/connexion.html");
//
//            // Fermer les ressources
//            st.close();
//            con.close();
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().println("ERROR : " + e.getMessage());
//        }
		try {
			DataBase db = new DataBase();
			Connection con = db.getConnection();
			PreparedStatement prepa = con.prepareStatement("insert to inscrit (utilisateur,mail,motdepass) values (?,?,?)");
			prepa.setString(1, user);
			prepa.setString(2, mail);
			prepa.setString(1, pass);
	      
			prepa.executeUpdate();
//   
			response.sendRedirect("http://localhost:8080/WalletWatch/connexion.html");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String user = request.getParameter("user");
        String pass = request.getParameter("anana");
        String mail = request.getParameter("mail");
        
//        System.out.println(user+" "+pass+" "+mail);

        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Créer la connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "20050412");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "walletwatch", "walie3313");
            
            // Créer un état de connexion
            Statement st = con.createStatement();
            
                PreparedStatement prepa = con.prepareStatement("insert into inscrit (utilisateur,email,motdepass) values (?,?,?)");
                System.out.println(user+" "+pass+" "+mail);
                prepa.setString(1, user);
                prepa.setString(2, mail);
                prepa.setString(3, pass);
                
                prepa.executeUpdate();
                
                response.sendRedirect("connexion.html");
//                request.getRequestDispatcher("connexion.html").forward(request,response);

            // Fermer les ressources
            st.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR : " + e.getMessage());
        }
	}

}
