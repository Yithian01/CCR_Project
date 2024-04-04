package dto;

public class StuffDTO {
	
	private String stuffName;
	private int stType;
	private int stDate;
	/**
	 * @param stuffName
	 * @param stType
	 * @param stDate
	 */
	public StuffDTO(String stuffName, int stType, int stDate) {
		super();
		this.stuffName = stuffName;
		this.stType = stType;
		this.stDate = stDate;
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
	public int getStDate() {
		return stDate;
	}
	public void setStDate(int stDate) {
		this.stDate = stDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StuffDTO [stuffName=");
		builder.append(stuffName);
		builder.append(", stType=");
		builder.append(stType);
		builder.append(", stDate=");
		builder.append(stDate);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
}
