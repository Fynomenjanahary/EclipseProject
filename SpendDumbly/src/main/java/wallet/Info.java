package wallet;

public class Info {
	private String id;
	private String date;
	private String montant;
	private String categorie;
	private String description;
	
	public Info(String id, String date, String montant , String categorie, String description) {
		this.id = id;
		this.date = date;
		this.montant = montant;
		this.categorie = categorie;
		this.description = description;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getMontant() {
		return this.montant;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getCategorie() {
		return this.categorie;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
