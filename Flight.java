public class Flight {
   private String FlightNumber;
   private String[] Cities = {"Dammam", "Jeddah", "Yanbu", "Abha", "Hail", "Tabuk", "Taif"};
   private int Destination;
   private int Gate;
   private String Date;
   private String DepartureTime;
   private String ArrivalTime;
   public static int TotalFlights = 0;

 //Constructors
   public Flight(){
      TotalFlights++;
      FlightNumber ="";
      Destination =0;
      Gate = 0;
      Date = "";
      DepartureTime="";
      ArrivalTime="";
      
   }
   public Flight(int destination, String date, int gate, String departure){
      TotalFlights++;
      Destination=destination;
      Date=date;
      Gate=gate;
      DepartureTime=departure;
      calculateArrivalTime();
      generateFlightNumber();
      
   }

//setters
   public void setDestination(int destination ) {
      Destination = destination;
   }
   public void setDate(String date){
      Date=date;
   }
   public void setGate(int gate){
      Gate = gate;
   }
   public void setDepartureTime(String dep){
      DepartureTime = dep;
   }
   public void setArrivalTime(String arr){
      ArrivalTime = arr;
   }


//getters
   public String getFlightNumber(){
      return FlightNumber;
   }
   public int getDestination(){
      return Destination;
   }

   public String getDate(){
      return Date;
   }
   public int getGate(){
      return Gate;
   }
   public String getDepartureTime(){
      return DepartureTime;
   }
   public String getArrivalTime(){
      return ArrivalTime;
   }

//generate flight number
   public void generateFlightNumber(){
      FlightNumber = Cities[Destination-1].substring(0,3).toUpperCase()+"00"+TotalFlights;
   }

//calculate arrival time
   public void calculateArrivalTime(){
      int hour = Integer.parseInt(DepartureTime.substring(0,DepartureTime.indexOf(':')));
      int min = Integer.parseInt(DepartureTime.substring(DepartureTime.indexOf(':')+1));
      hour++;
      switch(Destination){
         case 1: 
            min += 5; 
            break;
         case 2: case 3: case 4:
            min += 45; 
            break;
         case 5:
            min += 15; 
            break;
         case 6:
            min += 20; 
            break;
         case 7:
            min += 35;
      }//end switch
      if (min>=60){
         hour++;
         min -= 60;
      }
      if (hour>=24){
         hour -= 24;
         if (hour<10 && min<10) ArrivalTime= "0"+hour+":0"+min+" +1";
         else if (hour<10 && min>=10) ArrivalTime= "0"+hour+":"+min+" +1";
         else if (hour>=10 && min<10) ArrivalTime= hour+":0"+min+" +1";
         else ArrivalTime= hour+":"+min+" +1";
      } else{
         if (hour<10 && min<10) ArrivalTime= "0"+hour+":0"+min;
         else if (hour<10 && min>=10) ArrivalTime= "0"+hour+":"+min;
         else if (hour>=10 && min<10) ArrivalTime= hour+":0"+min;
         else ArrivalTime= hour+":"+min;
      } 
   }

//print flight info
   public void printFlightInfo(){
      System.out.printf("Flight Number: %-15s Gate: %-15d %n",FlightNumber, Gate );
      System.out.printf("Destination: %-15s Date: %-15s %n",Cities[Destination-1],Date);
      System.out.printf("Depature Time: %-15s Arrival Time: %-15s %n", DepartureTime,ArrivalTime);
      System.out.println();
   }


}
