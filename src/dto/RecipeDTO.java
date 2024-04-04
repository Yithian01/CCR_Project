package dto;

public class RecipeDTO {
	private String recipeName;
	private String mainStuff;
	private String sideStuff;
	private String stuffName;
	

	/**
	 * @param recipeName
	 * @param mainStuff
	 * @param sideStuff
	 * @param stuffName
	 */
	public RecipeDTO(String recipeName, String mainStuff, String sideStuff, String stuffName) {
		super();
		this.recipeName = recipeName;
		this.mainStuff = mainStuff;
		this.sideStuff = sideStuff;
		this.stuffName = stuffName;
	}
	
	
	
	
	
	
	
	
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getMainStuff() {
		return mainStuff;
	}
	public void setMainStuff(String mainStuff) {
		this.mainStuff = mainStuff;
	}
	public String getSideStuff() {
		return sideStuff;
	}
	public void setSideStuff(String sideStuff) {
		this.sideStuff = sideStuff;
	}
	public String getStuffName() {
		return stuffName;
	}
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipeDTO [recipeName=");
		builder.append(recipeName);
		builder.append(", mainStuff=");
		builder.append(mainStuff);
		builder.append(", sideStuff=");
		builder.append(sideStuff);
		builder.append(", stuffName=");
		builder.append(stuffName);
		builder.append("]");
		return builder.toString();
	}
	

	
}
