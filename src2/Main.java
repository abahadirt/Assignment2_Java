import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	
	
	
	public static void main(String[] args) throws IOException {
		PropertyJsonReader reader1 = new PropertyJsonReader();
		ListJsonReader reader2 = new ListJsonReader();
		reader1.PropertyJsonReader2();
		reader2.ListJsonReader2();
		Squares[] squares = new Squares[41];
		PropertySquare[] property = new PropertySquare[reader1.arrLand.size()+reader1.arrRail.size()+reader1.arrCompany.size()/3];
		
		
		
		int temp=0;
		
		for(int i=0; i<reader1.arrLand.size(); i++) {
			squares[Integer.parseInt(reader1.arrLand.get(i))] = new Squares(Integer.parseInt(reader1.arrLand.get(i)),"land");
			property[temp]= new PropertySquare(Integer.parseInt(reader1.arrLand.get(i)),"land",
					reader1.arrLand.get(++i),Integer.parseInt(reader1.arrLand.get(++i)));
			
			temp++;
		}
		for(int i=0; i<reader1.arrRail.size(); i++) {
			squares[Integer.parseInt(reader1.arrRail.get(i))] = new Squares(Integer.parseInt(reader1.arrRail.get(i)),"railroad");
			property[temp]= new PropertySquare(Integer.parseInt(reader1.arrRail.get(i)),"railroad",
					reader1.arrRail.get(++i),Integer.parseInt(reader1.arrRail.get(++i)));

			temp++;
		}
		
		for(int i=0; i<reader1.arrCompany.size(); i++) {
			squares[Integer.parseInt(reader1.arrCompany.get(i))] = new Squares(Integer.parseInt(reader1.arrCompany.get(i)),"company");
			property[temp]= new PropertySquare(Integer.parseInt(reader1.arrCompany.get(i)),"company",
					reader1.arrCompany.get(++i),Integer.parseInt(reader1.arrCompany.get(++i)));
			
			temp++;
		}
		int[] specialSquares = new int[] {1,3,5,8,11,18,21,23,31,34,37,39};
		for(int i: specialSquares) {
			squares[i] = new Squares(i,"special");
		}



		



Player player1 = new Player("Player 1", 15000, "", 0);
Player player2 = new Player("Player 2", 15000, "", 0);
int dice;




int dontend=0;   //this variable for if player 1 bankrupts: method will not end and wait for player 2's turn,

try {
      File myObj = new File(args[0]);
      Scanner myReader = new Scanner(myObj);
      File txt = new File("output.txt");
      FileWriter fw = new FileWriter(txt);
      try (PrintWriter pw = new PrintWriter(fw)) {
		while (myReader.hasNextLine()) {
		    ++dontend;
			String data = myReader.nextLine();
		   
			if ((player1.money<1 || player2.money<1) && dontend%2==0) {
		    	pw.println(player1.Show(player2));
		    	break;
		    }
		    else {
		    	
		    
		    
		    if(data.substring(0,1).equals("P")) {  //detecting command do say dice
		    		dice =	Integer.parseInt(data.substring(9));
		    		if(data.substring(7,8).equals("1")) {          //dice for player 1
		        		player1.NewPosition(dice);
		        		if(squares[player1.currentPoz].type.equals("special")) {
		        			
		        			pw.print(player1.ActionSquareMethod(reader2.arrChance,reader2.arrCommunity,player1
		        					,player2,dice,player1, player2));
		        			
		        			
		        			if(squares[player1.currentPoz].type.equals("land")){
			        			for(int i=0; i<reader1.arrLand.size()/3;i++) {
			        				if(property[i].id == squares[player1.currentPoz].id) {
			        					pw.print(player1.BuyOrRents(player1,player2,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
			        					

			        				}
			        			}
			        			
			        		}
		        			else if(squares[player1.currentPoz].type.equals("company")) {
		        				for(int i=(reader1.arrLand.size()+reader1.arrRail.size())/3; i<(reader1.arrRail.size()
		        						+reader1.arrCompany.size()+reader1.arrLand.size())/3;i++) {
		        					if(property[i].id == squares[player1.currentPoz].id) {
		        						pw.print(player1.BuyOrRentCompanys(player1,player2,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
		        					}
		        				}
		        			}
		        			else if(squares[player1.currentPoz].type.equals("railroad")) {
			        			for(int i=reader1.arrLand.size()/3; i<(reader1.arrRail.size()+reader1.arrLand.size())/3;i++) {
			        				if(property[i].id == squares[player1.currentPoz].id) {
			        					pw.print(player1.BuyOrRentRails(player1,player2,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
			        					

			        				}
			        			}
		        			}
		        			
		        			
		        			
		        			
		        		}
		        		else {
		        			//land rail company
		        			if(squares[player1.currentPoz].type.equals("land")){
			        			for(int i=0; i<reader1.arrLand.size()/3;i++) {
			        				if(property[i].id == squares[player1.currentPoz].id) {
			        					pw.println(player1.BuyOrRent(player1,player2,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
			        					

			        				}
			        			}
			        			
			        		}
		        			else if(squares[player1.currentPoz].type.equals("company")) {
		        				for(int i=(reader1.arrLand.size()+reader1.arrRail.size())/3; i<(reader1.arrRail.size()
		        						+reader1.arrCompany.size()+reader1.arrLand.size())/3;i++) {
		        					if(property[i].id == squares[player1.currentPoz].id) {
		        						pw.println(player1.BuyOrRentCompany(player1,player2,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
		        					}
		        				}
		        			}
		        			else if(squares[player1.currentPoz].type.equals("railroad")) {
			        			for(int i=reader1.arrLand.size()/3; i<(reader1.arrRail.size()+reader1.arrLand.size())/3;i++) {
			        				if(property[i].id == squares[player1.currentPoz].id) {
			        					pw.println(player1.BuyOrRentRail(player1,player2,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
			        					

			        				}
			        			}
		        			}
		        			

		        			
		        		
		        		
		        		}
		        	}
		        	
		    		
		    		
		    		
		    		else {          //dice for player 2
		        		player2.NewPosition(dice);
		        		if(squares[player2.currentPoz].type.equals("special")) {
		        			pw.print(player2.ActionSquareMethod(reader2.arrChance,reader2.arrCommunity,player2
		        					,player1,dice,player1, player2));
		        				
		        			if(squares[player2.currentPoz].type.equals("land")){

			        			for(int i=0; i<reader1.arrLand.size()/3;i++) {
			        				if(property[i].id == squares[player2.currentPoz].id) {
			        					pw.print(player2.BuyOrRents(player2,player1,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
			        					

			        				}
			        			}
			        			
			        		}
		        			else if(squares[player2.currentPoz].type.equals("company")) {
		        				for(int i=(reader1.arrLand.size()+reader1.arrRail.size())/3; i<(reader1.arrRail.size()
		        						+reader1.arrCompany.size()+reader1.arrLand.size())/3;i++) {
		        					if(property[i].id == squares[player2.currentPoz].id) {
		        						pw.print(player2.BuyOrRentCompanys(player2,player1,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
		        					}
		        				}
		        			}
		        			else if(squares[player2.currentPoz].type.equals("railroad")) {
		        				for(int i=reader1.arrLand.size()/3; i<(reader1.arrRail.size()+reader1.arrLand.size())/3;i++) {
		        					if(property[i].id == squares[player2.currentPoz].id) {
		        						pw.print(player2.BuyOrRentRails(player2,player1,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
		        						
		        					}

		        				}
		        				
		        			}
		        		
		        		
		        		
		        		
		        		
		        		
		        		
		        		}
		        		else {
		        			//land rail company
		       
		        			if(squares[player2.currentPoz].type.equals("land")){

			        			for(int i=0; i<reader1.arrLand.size()/3;i++) {
			        				if(property[i].id == squares[player2.currentPoz].id) {
			        					pw.println(player2.BuyOrRent(player2,player1,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
			        					


			        				}
			        			}
			        			
			        		}
		        			else if(squares[player2.currentPoz].type.equals("company")) {
		        				for(int i=(reader1.arrLand.size()+reader1.arrRail.size())/3; i<(reader1.arrRail.size()
		        						+reader1.arrCompany.size()+reader1.arrLand.size())/3;i++) {
		        					if(property[i].id == squares[player2.currentPoz].id) {
		        						pw.println(player2.BuyOrRentCompany(player2,player1,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
		        					}
		        				}
		        			}
		        			else if(squares[player2.currentPoz].type.equals("railroad")) {
		        				for(int i=reader1.arrLand.size()/3; i<(reader1.arrRail.size()+reader1.arrLand.size())/3;i++) {
		        					if(property[i].id == squares[player2.currentPoz].id) {
		        						pw.println(player2.BuyOrRentRail(player2,player1,property[i].name,
			        							property[i].cost, property[i].rent,dice,player1,player2));
		        						
		        					}

		        				}
		        				
		        			}
		        			
		        		
		        			
		        		
		        		
		        		}
		        		
		        		
		        		
		        		
		        	}
		    }
		    else {      
		    	pw.println(player1.Show(player2));
		    }
		}
			if(!myReader.hasNextLine()) {
		    	pw.println(player1.Show(player2));
		    	
		    }
		}
		
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

}
		





		

		
		


		
		
		
		
		
	}

