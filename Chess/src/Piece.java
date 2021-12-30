
public class Piece {
	
	private String name;
	private String color;
	private double rank;
	
	public Piece() {
		super();
		name="--";
		color="--";
		rank=0;
	}
	
	public Piece(String name, String color, double rank) {
		super();
		this.name = name;
		this.color = color;
		this.rank = rank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getRank() {
		return rank;
	}
	public void setRank(double rank) {
		this.rank = rank;
	}

}
