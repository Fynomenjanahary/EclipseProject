
import java.sql.*;

public class ManageDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 1- charger le driver mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2- créer la connexion
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "walletwatch", "walie3313");
			
			// 3- créer un état de connexion
			Statement st = con.createStatement();
			
			// 4- Créer une requete de séléction
			ResultSet res = st.executeQuery("select * from inscrit");
			
			// 5- affichage et parcourt des données
			while(res.next()) {
				System.out.println("Nom :"+res.getString(2));
			}
			
		}catch(Exception e){
			System.out.println("ERROR : "+e.getMessage());
		}
	}

}
