package dto;

public class AccountDto {

	private String date;
	private String title;
	private String type;
	private int price;
	private String detail;

	public AccountDto() {
	}

	public AccountDto(String date, String title, String type, int price, String detail) {
		super();
		this.date = date;
		this.title = title;
		this.type = type;
		this.price = price;
		this.detail = detail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "* [date=" + date + ", title=" + title + ", type=" + type + ", price=" + price + ", detail="
				+ detail + "]";
	}

	public String print() {
		return date + "-" + title + "-" + type + "-" + price + "-" + detail;
	}

}
