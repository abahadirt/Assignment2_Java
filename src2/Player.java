import java.util.ArrayList;
import java.util.Arrays;

public class Player extends People {
	String name;
	int currentPoz=1;
	int money;
	String proporties;
	int railRoadnum;
	int jailcount=-1;
	int freeparking=-1;
	public Player(String name, int money, String proporties, int railRoadnum) {
		this.name= name;
		this.money = money;
		this.proporties = proporties;
		this.railRoadnum = railRoadnum;
	}

	public void NewPosition(int a) {       
		if ((this.jailcount==-1||this.jailcount==3)&& (this.freeparking==1 || this.freeparking==-1)) {
			this.jailcount=-1;
			this.freeparking=-1;
			if(this.currentPoz+a>40) { 
				this.currentPoz= this.currentPoz +a -40;
				this.money+=200;
				banker-=200;
			}
			else if(this.currentPoz<1) { 
				this.currentPoz= this.currentPoz +a +40;
			}
			else {
				this.currentPoz= this.currentPoz + a;
			}
		}
		else {
			this.currentPoz=this.currentPoz;
		}
		
		
	}
	

	
	
	
	
	
	public void addProporties(String a) {
		this.proporties += a+", ";
	}

	public String Winner(Player a) {
		if(a.money>this.money) {
			return "Winner\t"+ a.name;
		}
		else {
			return "Winner\t"+ this.name;
		}
	}
	public String Show(Player a) {
		String[] proarr = this.proporties.split(", ");
		
		
		return line+"\n"+this.name+"\t"+this.money+"\thave: "+Arrays.toString(proarr).replace("[", "").replace("]", "")+"\n"
		+a.name +"\t"+a.money+"\thave: "+a.proporties+"\nBanker\t"+banker+"\n"+
		Winner(a)+"\n"+line;
	}	
	String line= "----------------------------------------------------------------------------------------------------------------------------";
	




	
	public String Buy(Player self,Player opp,String pro,int cost,int dice,Player a1, Player a2) {
		self.addProporties(pro);
		banker+=cost;
		self.money-=cost;
		return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" bought "+pro;
	}
	public String Rent(Player self,Player opp,int rent,String pro,int dice,Player a1,Player a2){
		self.money-=rent;
		opp.money+=rent;
		return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" paid rent for "+pro;
	}
	public String Has(Player self,Player opp,String pro,int dice,Player a1, Player a2) {
		return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" has "+pro;
	}

	
	
	
	public String BuyOrRent(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		String x1[] = self.proporties.split(", ");
		String x2[] = opp.proporties.split(", ");
		for(String i: x2) {
			if(pro.equals(i)) {
				return self.Rent(self,opp,rent,pro,dice,a1,a2);
				//return baskasinin kira ver
			}
			
		}
		for(String i: x1) {
			if( pro.equals(i)) {
				return Has(self,opp, pro,dice, a1,  a2);
				//return ZATEN ONUN
			}	
		}
		
		return Buy(self, opp, pro,cost,dice, a1, a2);
		
		//return Kimsenin degil satin al
	}
	
	
	public String BuyOrRentRail(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		String x1[] = self.proporties.split(", ");
		String x2[] = opp.proporties.split(", ");
		for(String i: x2) {
			if(pro.equals(i)) {
				return RentRail(self,opp,pro,cost,rent,dice, a1, a2);
				//return baskasinin kira ver
			}
			
		}
		for(String i: x1) {
			if( pro.equals(i)) {
				return Has(self,opp,pro,dice, a1, a2);
				//return ZATEN ONUN
			}	
		}
		return BuyRail(self,opp,pro,cost, rent,dice, a1,  a2);
		//return Kimsenin degil satin al
	}
	
	public String BuyRail(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		self.addProporties(pro);
		self.railRoadnum+=1;
		banker+=cost;
		self.money-=cost;
		return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" bought "+pro;
	}
	public String RentRail(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2){
		self.money-=25*opp.railRoadnum;
		opp.money+=25*opp.railRoadnum;
		return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" paid rent for "+pro;
	}
	public String RentCompany(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2){
		self.money-=25*dice;
		opp.money+=25*dice;
		return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" paid rent for "+pro;
	}
	
	public String BuyOrRentCompany(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		String x1[] = self.proporties.split(", ");
		String x2[] = opp.proporties.split(", ");
		for(String i: x2) {
			if(pro.equals(i)) {
				return RentCompany(self,opp,pro,cost,rent,dice, a1, a2);
				//return opp has... give rent...
			}
			
		}
		for(String i: x1) {
			if( pro.equals(i)) {
				return Has(self,opp,pro,dice, a1, a2);
				//return already has
			}	
		}
		return Buy(self,opp,pro,cost,dice, a1,  a2);
		//return no one has... buy it...
	}
	
	
	
	
	
	
	
	
	static int ComChest = -1;
	static int Chance =-1;
	
	
	
	
	
	
	
	
	
	
	public String ActionSquareMethod(ArrayList<String>chance,ArrayList<String>community,
			Player self,Player opp,int dice,Player a1, Player a2) {
		if(this.currentPoz==1) {
			return "go";
		}
		else if(this.currentPoz==3||this.currentPoz==18||this.currentPoz==34) { //com chest
			ComChest++;
			if(ComChest==0) {
				self.currentPoz =1;
				banker-=200;
				self.money+=200;
			}
			else if(ComChest==1) {
				banker-=75;
				self.money+=75;
			}
			else if(ComChest==2) {
				banker+=50;
				self.money-=50;
			}
			else if(ComChest==3) {
				opp.money-=10;
				self.money+=10;
			}
			else if(ComChest==4) {
				opp.money-=50;
				self.money+=50;
			}
			else if(ComChest==5) {
				banker-=20;
				self.money+=20;
			}
			else if(ComChest==6) {
				banker-=100;
				self.money+=100;
			}
			else if(ComChest==7) {
				banker+=100;
				self.money-=100;
			}
			else if(ComChest==8) {
				banker-=50;
				self.money+=50;
			}
			else if(ComChest==9) {
				banker-=100;
				self.money+=100;
			}
			else if(ComChest==10) {
				banker-=50;
				self.money+=50;
			}
			return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" draw "+community.get(ComChest++);
		}
		else if(this.currentPoz==8||this.currentPoz==23||this.currentPoz==37) { //chance
			Chance++;
			if(Chance==0) {
				self.currentPoz =1;
				banker-=200;
				self.money+=200;
			}
			else if(Chance==1){
				self.currentPoz=27;
			}
			else if(Chance==2){
				self.currentPoz-=3;
			}
			else if(Chance==3){
				banker+=15;
				self.money-=15;
			}
			else if(Chance==4){
				banker-=150;
				self.money+=150;
			}
			else if(Chance==5){
				banker-=100;
				self.money+=100;
			}
			return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" draw "+chance.get(Chance)+"\n";
		}
		else if(this.currentPoz==5||this.currentPoz==39) { //tax
			banker+=100;
			self.money-=100;
			return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" paid Tax"+"\n";
		}
		else if(this.currentPoz==11||this.currentPoz==31) { //31 i 11 e firlat (jail
			this.currentPoz=11;
			if(this.jailcount==-1) {
				jail();
				return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" went to jail"+"\n";
			}
			else {
				jail();
				return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" in jail (count="+this.jailcount+")"+"\n";
			}
			
		}
		else if(this.currentPoz==21) { //free parking 1 el
			if(this.freeparking==-1) {
				parking();
				return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" is in Free Parking"+"\n";
			}
			else {
				parking();
				return self.name+"\t"+dice+"\t"+self.currentPoz+"\t"+a1.money+"\t"+a2.money+"\t"+self.name+" is in Free Parking (count="+this.jailcount+")"+"\n";
			}
		}
		else {
			return "hata";
		}
	}	
	
	
	
	
	public void jail() {
		if (this.jailcount==3) {
			this.jailcount=0;
		}
		else {
			this.jailcount+=1;
		}
	}
	
	public void parking() {
		if (this.freeparking==1) {
			this.freeparking=0;
		}
		else {
			this.freeparking+=1;
		}
	}
	
	
	public String BuyOrRents(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		String x1[] = self.proporties.split(", ");
		String x2[] = opp.proporties.split(", ");
		for(String i: x2) {
			if(pro.equals(i)) {
				return self.Rents(self,opp,rent,pro,dice,a1,a2);
				//return baskasinin kira ver
			}
			
		}
		for(String i: x1) {
			if( pro.equals(i)) {
				return Hass(self,opp, pro,dice, a1,  a2);
				//return ZATEN ONUN
			}	
		}
		
		return Buys(self, opp, pro,cost,dice, a1, a2);
		
		//return Kimsenin degil satin al
	}
	public String Buys(Player self,Player opp,String pro,int cost,int dice,Player a1, Player a2) {
		self.addProporties(pro);
		banker+=cost;
		self.money-=cost;
		return " "+self.name+" bought "+pro+"\n";
	}
	public String Rents(Player self,Player opp,int rent,String pro,int dice,Player a1,Player a2){
		self.money-=rent;
		opp.money+=rent;
		return self.name+" paid rent for "+pro+"\n";
	}
	public String Hass(Player self,Player opp,String pro,int dice,Player a1, Player a2) {
		return " "+self.name+" has "+pro+"\n";
	}
	
	public String BuyOrRentCompanys(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		String x1[] = self.proporties.split(", ");
		String x2[] = opp.proporties.split(", ");
		for(String i: x2) {
			if(pro.equals(i)) {
				return RentCompanys(self,opp,pro,cost,rent,dice, a1, a2);
				//return opp has... give rent...
			}
			
		}
		for(String i: x1) {
			if( pro.equals(i)) {
				return Hass(self,opp,pro,dice, a1, a2);
				//return already has
			}	
		}
		return Buys(self,opp,pro,cost,dice, a1,  a2);
		//return no one has... buy it...
	}
	public String RentCompanys(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2){
		self.money-=25*dice;
		opp.money+=25*dice;
		return " "+self.name+" paid rent for "+pro+"\n";
	}
	public String BuyOrRentRails(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		String x1[] = self.proporties.split(", ");
		String x2[] = opp.proporties.split(", ");
		for(String i: x2) {
			if(pro.equals(i)) {
				return RentRails(self,opp,pro,cost,rent,dice, a1, a2);
				//return baskasinin kira ver
			}
			
		}
		for(String i: x1) {
			if( pro.equals(i)) {
				return Hass(self,opp,pro,dice, a1, a2);
				//return ZATEN ONUN
			}	
		}
		return BuyRails(self,opp,pro,cost, rent,dice, a1,  a2);
		//return Kimsenin degil satin al
	}
	public String BuyRails(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2) {
		self.addProporties(pro);
		self.railRoadnum+=1;
		banker+=cost;
		self.money-=cost;
		return " "+self.name+" bought "+pro+"\n";
	}
	public String RentRails(Player self,Player opp,String pro,int cost, int rent,int dice,Player a1, Player a2){
		self.money-=25*opp.railRoadnum;
		opp.money+=25*opp.railRoadnum;
		return " "+self.name+" paid rent for "+pro+"\n";
	}
	
	
}
