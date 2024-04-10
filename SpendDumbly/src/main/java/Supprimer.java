

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
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class Supprimer
 */
public class Supprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supprimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String id = request.getParameter("id");
        
        System.out.println("date : "+ user +" montant  "+ pass+" id "+id);
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("pass", pass);
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "20050412");
            
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM inscrit WHERE utilisateur='" + user + "' AND motdepass='" + pass + "'");
            
            PreparedStatement prepared = con.prepareStatement("delet from depense where id_depense == ?");

            if (res.next()) {
            int idy = Integer.parseInt(id);
            prepared.setInt(1,idy );
            
            
            prepared.executeUpdate();
            
                PreparedStatement prepa = con.prepareStatement("SELECT * FROM depense WHERE id_inscrit =?");
                
                prepa.setInt(1, idy);
                
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
