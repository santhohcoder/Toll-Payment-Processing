class Vehicle{
	
	String vehicleType;
	int start;
	int destination;
	String vehicleNumber;
	boolean isVIP;
	int totalamountpaid = 0;
	int journeyamount = 0;
	int journeyscount = 0;
	ArrayList<String> journeytolls = new ArrayList<>();
	
	int tollpaid[];
	HashMap<Integer, Integer> journeys = new HashMap<>();
	
	public Vehicles(String vehicleType, String vehicleNumber, int totalpoints, char isVIP){
		this.vehicleType = vehicleType;
		this.vehicleNumber = vehicleNumber;
		tollpaid = new int[totalpoints];
		if(isVIP == 'T')
			this.isVIP = true;
		else
			this.isVIP = false;
	}
	
	public void updateJourney(int tollpaid[], int journeyamount, HashMap<Integer, Integer> journeys, int journeyscount){
		String res = "";
		for(int i = 0; i<tollpaid.length; i++){
			if(tollpaid[i] != 0){
				res += ("---"+Integer.toString(i));
				tollpaid[i] = 0;
			}
		}
		journeytolls.add(res);
		this.totalamountpaid+=journeyamount;
		this.journeyamount = 0;
		this.journeyscount = ++journeyscount;
		journeys.put(journeyscount,journeyamount);
	}
	
	
	
}ś
