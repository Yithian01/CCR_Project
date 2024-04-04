package dto;


public class IceBoxDTO {
		private int boxNum; 
		private int quantity; 
		private int leftDays; 
		private String stuffName;
		private int isFrozen; 
		private int stType;
		/**
		 * @param boxNum
		 * @param quantity
		 * @param leftDays
		 * @param stuffName
		 */
		public IceBoxDTO(int boxNum, int quantity, int leftDays, String stuffName) {
			super();
			this.boxNum = boxNum;
			this.quantity = quantity;
			this.leftDays = leftDays;
			this.stuffName = stuffName;
		}
		
		public IceBoxDTO(int boxNum, int quantity, int leftDays, String stuffName, int isFrozen) {
			this(boxNum, quantity, leftDays, stuffName);
			this.isFrozen = isFrozen;
		}
		public IceBoxDTO(int boxNum, int quantity, int leftDays, String stuffName, int isFrozen, int stType) {
			this(boxNum, quantity, leftDays, stuffName, isFrozen);
			this.stType = stType;
		}
		
		
		public int getBoxNum() {
			return boxNum;
		}
		public void setBoxNum(int boxNum) {
			this.boxNum = boxNum;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public int getLeftDays() {
			return leftDays;
		}
		public void setLeftDays(int leftDays) {
			this.leftDays = leftDays;
		}
		public String getStuffName() {
			return stuffName;
		}
		public void setStuffName(String stuffName) {
			this.stuffName = stuffName;
		}
		
		public int getIsFrozen() {
			return isFrozen;
		}

		public void setIsFrozen(int isFrozen) {
			this.isFrozen = isFrozen;
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
			builder.append("IceBoxDTO [boxNum=");
			builder.append(boxNum);
			builder.append(", quantity=");
			builder.append(quantity);
			builder.append(", leftDays=");
			builder.append(leftDays);
			builder.append(", stuffName=");
			builder.append(stuffName);
			builder.append(", isFrozen=");
			builder.append(isFrozen);
			builder.append(", stType=");
			builder.append(stType);
			builder.append("]");
			return builder.toString();
		}



	
}
