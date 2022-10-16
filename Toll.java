import java.util.*;
import java.io.*;

class Toll{
	
	static int totaltolls;
	static int totalvehiclescreated;
	static boolean pointsinhighway[];
	
	static File f = new File("C:\\Users\\Administrator\\Desktop\\tolls.txt");
	
	
	public static void printTollDetails(TollGate tolls[]){
		
		try{
			
			FileWriter fw = new FileWriter(f,true);
			
			Date d = new Date();
			fw.write("Modified Time: "+d.toString());
			fw.write("\n");
			
			for(int i = 0; i<pointsinhighway.length; i++){
				if(pointsinhighway[i]){
					System.out.println("Toll No: "+tolls[i].tollId);
					fw.write("Toll No: "+tolls[i].tollId);
					fw.write("\n");
					
					for(int j = 0; j<tolls[i].totalvehiclespassed; j++){
						System.out.println("Vechicle Number: "+tolls[i].vehiclespassed[j].vehicleNumber);
						fw.write("Vechicle Number: "+tolls[i].vehiclespassed[j].vehicleNumber);
						fw.write("\n");
						System.out.println("Vechicle Type: "+tolls[i].vehiclespassed[j].vehicleType);
						fw.write("Vechicle Type: "+tolls[i].vehiclespassed[j].vehicleType);
						fw.write("\n");
						System.out.println("Amount Paid: "+tolls[i].vehiclespassed[j].tollpaid[i]);
						fw.write("Amount Paid: "+tolls[i].vehiclespassed[j].tollpaid[i]);
						fw.write("\n");
						System.out.println();
						fw.write("\n");
						fw.write("\n");
					}
					// System.out.println();
					System.out.println("Total Amount Collected by Toll: "+ tolls[i].totalamountcollected);
					fw.write("Total Amount Collected by Toll: "+ tolls[i].totalamountcollected);
					fw.write("\n");
					fw.write("\n");
					fw.write("\n");
				}
				System.out.println();
			}
			fw.close();
		}
		catch(Exception e){
			System.out.println("Error");
		}
		
	}
	
	public static void startjourney(String vNumber, int start, int destination, Vehicles vehicles[], TollGate tolls[]){
		
		for(int i = 0; i<vehicles.length; i++){
			
			// System.out.println(vNumber+" "+vehicles[i].vehicleNumber);
			if(vNumber.equalsIgnoreCase(vehicles[i].vehicleNumber)){
				System.out.println("Vehicle Number Matched is: "+vehicles[i].vehicleNumber);
				if(start < destination){
					
					for(int j = start; j<=destination; j++){
						
						if(pointsinhighway[j]){
							System.out.println(j);
							vehicles[i].tollpaid[j] = vehiclecost(vehicles[i].vehicleType,tolls[j], vehicles[i].isVIP);
							System.out.println("Sum Amount: "+vehicles[i].tollpaid[j]);
							
							vehicles[i].journeyamount += vehicles[i].tollpaid[j];
							
							tolls[j].vehiclespassed[tolls[j].totalvehiclespassed++] = vehicles[i];
							System.out.println("Passed Vehicle: "+tolls[j].vehiclespassed[tolls[j].totalvehiclespassed-1].vehicleNumber);
							tolls[j].totalamountcollected+= vehicles[i].tollpaid[j];
							System.out.println("Tolls amount: " + tolls[j].totalamountcollected);
						}
					}
					System.out.println("Journey AMount: "+vehicles[i].journeyamount);
					vehicles[i].updateJourney(vehicles[i].tollpaid,vehicles[i].journeyamount, vehicles[i].journeys, vehicles[i].journeyscount);
					return;
				}
				else{
					int frontdistance = 0;
					for(int j = start; j<=destination; j++)
						frontdistance++;
					System.out.println(frontdistance);
					int reversedistance = 0;
					for(int j = 0; j<=start; j++)
						reversedistance++;
					for(int j = pointsinhighway.length; j>destination; j--)
						reversedistance++;
					System.out.println(reversedistance);
					
					if(Math.min(frontdistance, reversedistance) == frontdistance){
						System.out.println("Fonrt");
					}
					else
						System.out.println("End");
					
					return;
					
				}
				
			}
		}
		
	}
	
	public static void printVehicleDetails(Vehicles vehicles[]){
		try{
			FileWriter fw = new FileWriter(f,true);
			
			Date d = new Date();
			fw.write("Modified Time: "+d.toString());
			fw.write("\n");
			
			for(int i = 0; i<vehicles.length; i++){
				
				System.out.println("Vehicle Number: "+vehicles[i].vehicleNumber);
				fw.write("Vehicle Number: "+vehicles[i].vehicleNumber);
				fw.write("\n");
				System.out.println("Vehicle Type: "+vehicles[i].vehicleType);
				fw.write("Vehicle Type: "+vehicles[i].vehicleType);
				fw.write("\n");
				System.out.println("VIP Status: "+ vehicles[i].isVIP);
				fw.write("VIP Status: "+ vehicles[i].isVIP);
				fw.write("\n");
				System.out.println("Total Journeys: "+vehicles[i].journeyscount);
				fw.write("Total Journeys: "+vehicles[i].journeyscount);
				fw.write("\n");
				
				System.out.println("Below Are the Journeys it took and total Amount it paid: ");
				if(vehicles[i].journeys.isEmpty())
					System.out.println("No Journeys Yet!!");
				else{
					int journeycnt = 0;
					for(Map.Entry<Integer,Integer> mp : vehicles[i].journeys.entrySet()){
						System.out.println("Journey "+mp.getKey()+": Amount Paid: "+mp.getValue());
						fw.write("Journey "+mp.getKey()+": Amount Paid: "+mp.getValue());
						fw.write("\n");
						// System.out.println(vehicles[i].journeyscount);
						System.out.println("Tolls crossed: "+vehicles[i].journeytolls.get(journeycnt));
						fw.write("Tolls crossed: "+vehicles[i].journeytolls.get(journeycnt));
						fw.write("\n");
						journeycnt++;
					}
				}
				System.out.println();
				System.out.println("Total Amount Paid By the Vehicle: "+vehicles[i].totalamountpaid);
				fw.write("\nTotal Amount Paid By the Vehicle: "+vehicles[i].totalamountpaid +"\n");
				fw.write("\n");
				System.out.println();
			}
			fw.close();
		}
		catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
		}
		
		
	}
	
	public static int vehiclecost(String vehicleType, TollGate tollgate, boolean isVIP){
		if(vehicleType.equalsIgnoreCase("car")){
			if(isVIP)
				return (int)(tollgate.carrate*0.2);
			return tollgate.carrate;
		}
		else if(vehicleType.equalsIgnoreCase("van")){
			if(isVIP)
				return (int)(tollgate.vanrate*0.2);
			return tollgate.vanrate;
		}
		else if(vehicleType.equalsIgnoreCase("bike")){
			if(isVIP)
				return (int)(tollgate.bikerate*0.2);
			return tollgate.bikerate;
		}
		else if(vehicleType.equalsIgnoreCase("bus")){
			if(isVIP)
				return(int)(tollgate.busrate*0.2);
			return tollgate.busrate;
		}
		else
			if(isVIP)
				return (int)(tollgate.heavyrate*0.2);
		return tollgate.heavyrate;
	}
	
	
	
	
	public static void main(String args[]){
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the Total Number of Points in Highway: ");
		int totalpoints = sc.nextInt();
		
		System.out.println("Enter the Number of Tolls: ");
		totaltolls = sc.nextInt();
		
		pointsinhighway = new boolean[totalpoints];
		TollGate tolls[] = new TollGate[totalpoints];
		
			
		
		for(int i = 0; i<totaltolls; i++){
			
			System.out.println("Enter the point in which toll should be placed (0 - "+(totalpoints-1)+"): ");
			int whichtoll = sc.nextInt();
			pointsinhighway[whichtoll] = true;
			System.out.println("Enter the Rates of");
			System.out.print("Car: ");
			int carrate = sc.nextInt();
			System.out.print("\nVan: ");
			int vanrate = sc.nextInt();
			System.out.print("\nBike: ");
			int bikerate = sc.nextInt();
			System.out.print("\nBus: ");
			int busrate = sc.nextInt();
			System.out.print("\nHeavy Vehicles: ");
			int heavyrate = sc.nextInt();
			tolls[whichtoll] = new TollGate(i,carrate,vanrate,bikerate,busrate,heavyrate);
			
		}
		
		// for(boolean f: pointsinhighway)
			// System.out.println(f);
		// System.exit(0);
		
		System.out.println("Enter the Number of Vehicles to be Created: ");
		totalvehiclescreated = sc.nextInt();
		
		Vehicles vehicles[] = new Vehicles[totalvehiclescreated];
		
		System.out.println();
		
		for(int i = 0; i<totalvehiclescreated; i++){
			System.out.println("Enter the Vehicle Type (Car, Van, Bike, Bus, Heavy): ");
			String vehicleType = sc.next();
			System.out.println("Enter the Vehicle Number (TN 10 TY 1234): ");
			sc.nextLine();
			String vehicleNumber = sc.nextLine();
			System.out.println("Enter if the Vehicle is VIP (T/F): ");
			char isVIP = sc.next().charAt(0);
			vehicles[i] = new Vehicles(vehicleType, vehicleNumber, totalpoints,isVIP);
		}
		
		loop:
		while(true){
			System.out.println("\n\nMENU");
			System.out.println();
			
			System.out.println("1. Start a Journey");
			System.out.println("2. Display Toll Details");
			System.out.println("3. Display Vehicle Details");
			System.out.println("4. Exit");
			
			System.out.print("\nEnter Choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice){
				case 1: 
						System.out.println("Enter the Vehicle Number: ");
						String vNumber = sc.nextLine();
						System.out.println("Enter the start Point (0 - "+(totalpoints-1)+"): ");
						int start = sc.nextInt();
						System.out.println("Enter the Destination Point(0 - "+(totalpoints-1)+"): ");
						int destination = sc.nextInt();
						startjourney(vNumber,start,destination,vehicles,tolls);
						break;
				case 2: 
						printTollDetails(tolls);
						break;
				
				case 3:	printVehicleDetails(vehicles);
						break;
				
				case 4: break loop;
			}
			
			
		}
		
	}
	
}

