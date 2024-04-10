

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Add
 */
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String user = (String) request.getParameter("user");
		String pass = (String) request.getParameter("pass");
		
//		System.out.println("session : "+ user +" y  "+ pass);
		
		String date = request.getParameter("date");
        String montant = request.getParameter("montant");
        String categorie = request.getParameter("categorie");
        String description = request.getParameter("description");
        
//        System.out.println("date : "+ date +" montant  "+ montant);
        
        String page = request.getRequestURI();
        
        try {
      
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "walletwatch", "walie3313");
            
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM inscrit WHERE utilisateur='" + user + "' AND motdepass='" + pass + "'");
            PreparedStatement prepared;
            String idiana ="";
            if(page.contains("/add.jsp")) {
            	prepared = con.prepareStatement("insert into depense (id_inscrit, date, montant, categorie, description) values (?,?,?,?,?)");
            }
            else {
            	prepared = con.prepareStatement("update defense set id_inscrit=?, date=?, montant=?, categorie=?, description=? where id_depense = ?");
            	prepared.setInt(4,Integer.parseInt(idiana));
            }

            if (res.next()) {
            int id = res.getInt("id_inscrit");
            prepared.setInt(1,id );
            prepared.setString(2,date );
            prepared.setInt(3,Integer.parseInt(montant));
            prepared.setString(4,categorie );
            prepared.setString(5,description);
            
            prepared.executeUpdate();
            
            System.out.println("date : "+ date +" desc  "+ description);
            
                PreparedStatement prepa = con.prepareStatement("SELECT * FROM depense WHERE id_inscrit =?");
                
                prepa.setInt(1, id);
                
                ResultSet info = prepa.executeQuery();
                
                ArrayList<String[]> depense = new ArrayList<>();
                while (info.next()) {
                    String[] row = new String[5];
                    row[0] = info.getString(1);
                    row[1] = info.getString(3);
                    row[2] = info.getString(4);
                    row[3] = info.getString(5);
                    row[4] = info.getString(6);
                   
                    depense.add(row);
                }
                
                String[][] depenseArray = new String[depense.size()][5];
                for (int i = 0; i < depense.size(); i++) {
                    depenseArray[i] = depense.get(i);
                }
                
                request.setAttribute("user", user);
                request.setAttribute("info", depenseArray);
                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            } else {
                response.sendRedirect("./connexion.html");
            }
            res.close();
            st.close();
            con.close();
        
    	} catch (Exception e) {
    		e.printStackTrace();
    		response.getWriter().println("ERROR : " + e.getMessage());
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
