
public class PropertySquare extends Squares{
	String name;
	int cost;
	int rent;
	int ownby;
	public PropertySquare(int id, String type, String name, int cost) {
		super(id,type);
		this.name = name;
		this.cost = cost;
		if(this.type.equals("land")) {
			if(cost<2001) {
				this.rent= cost*4/10;
			}
			else if(cost<3001) {
				this.rent= cost*3/10;
			}
			else {
				this.rent=cost*35/100;
			}
		}
		else {
			this.rent= 31;
		}
	}
	
	
	
	
}

