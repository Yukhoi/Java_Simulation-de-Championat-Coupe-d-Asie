import java.util.ArrayList;

public class Knockout implements Match{
	private static Knockout INSTANCE = null;
	private ArrayList <Team> listTeam;
	
	private Knockout(){
		listTeam = new ArrayList <Team>();
	}
	
	private synchronized static void creatInstance(){
		if (INSTANCE == null){
			INSTANCE = new Knockout();
		}
	}
	
	public static Knockout getInstance(){
		if (INSTANCE == null) creatInstance();
		return INSTANCE;
	}
	
	public void setListTeam(Team[] team){
		for(Team t: team){
			listTeam.add(t);
		}
	}
	
	public void urineTest(Team t1, Team t2) throws DopingException{
		double i = Math.random();
		double j = Math.random();
		
		if (i < t1. getPosibilityDoping()){
			t1.setDoping();
			t1.cheating();
			throw new DopingException(t1, "DopingException");			
		}
		if (j < t2. getPosibilityDoping()){
			t2.setDoping();
			t2.cheating();
			throw new DopingException(t2, "DopingException");
		}
	}
	
	public void playMatch(Team t1, Team t2){
		
		try{
			urineTest(t1,t2);
		}catch(DopingException e){
			System.out.println(e.getNation() + " tested positive for doping. All its results will be cancled");
			System.out.println(e.getMessage());
		}
		
		int butTeam1 = (int)(Math.random()*4);			//Randomly generate the number of goals scored by both teams
		int butTeam2 = (int)(Math.random()*4);
		
		if (t1.getDoping()){
			butTeam1 = 0;
			butTeam2 = 3;
			System.out.println(t1.getNation() + " " + butTeam1 + "-" + butTeam2 + " " + t2.getNation()+ "\n");
		}else if (t2.getDoping()){
			butTeam2 = 0;
			butTeam1 = 3;
			System.out.println(t1.getNation() + " " + butTeam1 + "-" + butTeam2 + " " + t2.getNation()+ "\n");
		}else{
				
			String s = ""; 
			String infoGoal1 = t1.getNation() + "'s goal players:\n";
			String infoGoal2 = t2.getNation() + "'s goal players:\n";
		
			
			int time1 = 0;							//chose the player in the time 1 who scored, and the time
			for(int i = 0;i < butTeam1; i++){
				time1 += (int)(Math.random()*(90-time1));
				int nbPlayer1 = (int)(Math.random()*23);
				Player p = t1.getTabPlayers().get(nbPlayer1);
				p.addNbGoal();
				infoGoal1 += p.getName() + " " + time1 + "'\n";
			}
		
			int time2 = 0;							//chose the player in the time 2 who scored, and the time
			for(int i = 0;i < butTeam2; i++){
				time2 += (int)(Math.random()*(90-time2));
				int nbPlayer2 = (int)(Math.random()*23);
				Player p = t2.getTabPlayers().get(nbPlayer2);
				p.addNbGoal();
				infoGoal2 += p.getName() + " " + time2 + "'\n";
			}
		
		
			if(butTeam1 == butTeam2){				//If the game is not divided within 90 minutes, the game enters extra time
				s = "\n" + "   (90')" + butTeam1 + " - " + butTeam2;
				int butTeamExtra1 = (int)(Math.random()*3);			//Randomly generate the number of goals scored by both teams in extra time
				int butTeamExtra2 = (int)(Math.random()*3);
			
				time1 = 90;						//chose the player in the time 1 who scored in the extra time, and the time
				for(int i = 0;i < butTeamExtra1; i++){
					time1 += (int)(Math.random()*(120-time1));
					int nbPlayer1 = (int)(Math.random()*22);
					Player p = t1.getTabPlayers().get(nbPlayer1);
					p.addNbGoal();
					infoGoal1 += p.getName() + " " + time1 + "'" + "\n";
				}
			
				time2 = 90;						//chose the player in the time 2 who scored in the extra time, and the time
				for(int i = 0;i < butTeamExtra2; i++){
					time2 += (int)(Math.random()*(120-time2));
					int nbPlayer2 = (int)(Math.random()*22);
					Player p = t2.getTabPlayers().get(nbPlayer2);
					p.addNbGoal();
					infoGoal2 += p.getName() + " " + time2 + "'" + "\n";
				}
			
				if(butTeamExtra1 == butTeamExtra2){						//If the game is not divided yet, the game enters pennalty shoot out
					s += "\n"+"   (120')" + butTeamExtra1 + " - " + butTeamExtra2;
					int butTeamPen1 = 0;
					int butTeamPen2 = 0;
					do{
						butTeamPen1 = (int)(Math.random()*6);
						butTeamPen2 = (int)(Math.random()*6);
					}while((Math.abs(butTeamPen1 - butTeamPen2) >= 2) || (butTeamPen1 == butTeamPen2)) ;
					s += "\n"+"   (pen)" + butTeamPen1 + " - " + butTeamPen2;
					butTeam1 += butTeamExtra1;
					butTeam2 += butTeamExtra2;
					s = t1.getNation() + " " + (butTeam1+butTeamPen1)  + "-" + (butTeam2+ butTeamPen2) + " " + t2.getNation() + s;
				}else{
					butTeam1 += butTeamExtra1;
					butTeam2 += butTeamExtra2;
					s = t1.getNation() + " " + butTeam1  + "-" + butTeam2 + " " + t2.getNation() + s;
				}
			}else{
				s = t1.getNation() + " " + butTeam1  + "-" + butTeam2 + " " + t2.getNation();
			}
			System.out.println(s);
			System.out.println(infoGoal1);
			System.out.println(infoGoal2);
		}
		
		if(butTeam1 > butTeam2)
			listTeam.remove(t2);
		if(butTeam1 < butTeam2)
			listTeam.remove(t1);
		
	}
	
	public void roundOf16(){
		System.out.println("Round of 16");
			for(int j=0; j < 8; j++){
				playMatch(listTeam.get(j),listTeam.get(j+1));
			}
			System.out.println();
	}
	
	public void quaterfinal(){
		System.out.println("Quaterfinal");
			for(int j=0; j < 4; j++){
				playMatch(listTeam.get(j),listTeam.get(j+1));
			}
			System.out.println();
	}
	
	public void semifinal(){
		System.out.println("Semifinal");
			for(int j=0; j < 2; j++){
				playMatch(listTeam.get(j),listTeam.get(j+1));
			}
			System.out.println();
	}
	
	public void roundFinal(){
		System.out.println("Final");
		playMatch(listTeam.get(0),listTeam.get(1));
		System.out.println();
		System.out.println(listTeam.get(0).getNation() + " is Champion!!!");
	}
	
	
}