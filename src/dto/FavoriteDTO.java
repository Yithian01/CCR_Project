package dto;

public class FavoriteDTO {
	private int favoritesNum;
	private int current;
	private String stuffName;
	private int stType;
	/**
	 * @param favoritesNum
	 * @param current
	 * @param stuffName
	 */
	public FavoriteDTO(int favoritesNum, int current, String stuffName) {
		super();
		this.favoritesNum = favoritesNum;
		this.current = current;
		this.stuffName = stuffName;
	}
	
	
	
	
	/**
	 * @param favoritesNum
	 * @param current
	 * @param stuffName
	 * @param stType
	 */
	public FavoriteDTO(int favoritesNum, int current, String stuffName, int stType) {
		this(favoritesNum,current ,stuffName  );
		this.stType = stType;
	}




	public int getFavoritesNum() {
		return favoritesNum;
	}
	public void setFavoritesNum(int favoritesNum) {
		this.favoritesNum = favoritesNum;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public String getStuffName() {
		return stuffName;
	}
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}




	public int getStType() {
		return stType;
	}




	public void setStType(int stType) {
		this.stType = stType;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FavoriteDTO [favoritesNum=");
		builder.append(favoritesNum);
		builder.append(", current=");
		builder.append(current);
		builder.append(", stuffName=");
		builder.append(stuffName);
		builder.append(", stType=");
		builder.append(stType);
		builder.append("]");
		return builder.toString();
	}

	
	
}
