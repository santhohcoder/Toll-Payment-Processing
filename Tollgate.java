class TollGate{
	
	int carrate;
	int vanrate;
	int bikerate;
	int busrate;
	int heavyrate;
	int totalvehiclespassed = 0;
	int tollId;
	
	int totalamountcollected = 0;
	
	Vehicles[] vehiclespassed = new Vehicles[20];
	
	public TollGate(){
		carrate = 0;
		vanrate = 0;
		bikerate = 0;
		busrate = 0;
		heavyrate = 0;
		
	}
	
	public TollGate(int tollId, int carrate, int vanrate, int bikerate, int busrate, int heavyrate){
		this.carrate = carrate;
		this.vanrate = vanrate;
		this.bikerate = bikerate;
		this.busrate = busrate;
		this.heavyrate = heavyrate;
		this.tollId = tollId;
	}
	
}