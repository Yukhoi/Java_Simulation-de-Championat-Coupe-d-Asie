import java.util.ArrayList;

public class GroupMatch implements Match{
	private static GroupMatch INSTANCE = null;
	private ArrayList<Groupe> tabGroups;   //all the groups
	private ArrayList<Team> thirdPlace;   //the third place of all groups
	private Team[] qualified;          //all the teams qualified
	
	private GroupMatch(){
		tabGroups = new ArrayList<Groupe>(); 
		thirdPlace = new ArrayList<Team>();
		qualified = new Team[16];
	}
	
	private synchronized static void creatInstance(){
		if (INSTANCE == null){
			INSTANCE = new GroupMatch();
		}
	}
	
	public static GroupMatch getInstance(){
		if (INSTANCE == null) creatInstance();
		return INSTANCE;
	}
	
	public void setTabGroups (ArrayList<Groupe> tabGroups){
		this.tabGroups = tabGroups;
	}
	
	public ArrayList<Groupe> getTabGroups(){
		return tabGroups;
	}
	
	public Team[] getQualified(){
		return qualified;
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
			System.out.println(t1.getNation() + " " + butTeam1 + "-" + butTeam2 + " " + t2.getNation());	//Print results
		}
		
		else if (t2.getDoping()){
			butTeam2 = 0;
			butTeam1 = 3;
			System.out.println(t1.getNation() + " " + butTeam1 + "-" + butTeam2 + " " + t2.getNation());	//Print results
		}else{
		
			String infoGoal1 = t1.getNation() + "'s goal players:\n";          //infomations of goals
			String infoGoal2 = t2.getNation() + "'s goal players:\n";
		
			System.out.println(t1.getNation() + " " + butTeam1 + "-" + butTeam2 + " " + t2.getNation());	//Print results
		
			int time1 = 0;       //the time of goals
			for(int i = 0;i < butTeam1; i++){
				time1 += (int)(Math.random()*(90-time1));      //randomly chose a time
				int nbPlayer1 = (int)(Math.random()*23);       //randomly chose a player
				Player p = t1.getTabPlayers().get(nbPlayer1);
				p.addNbGoal();
				infoGoal1 += p.getName() + " " + time1 + "'\n";  //show the player and the time in info
			}
		
			int time2 = 0;
			for(int i = 0;i < butTeam2; i++){
				time2 += (int)(Math.random()*(90-time2));
				int nbPlayer2 = (int)(Math.random()*23);
				Player p = t2.getTabPlayers().get(nbPlayer2);
				p.addNbGoal();
				infoGoal2 += p.getName() + " " + time2 + "'\n";
			}
			
			System.out.println(infoGoal1);
			System.out.println(infoGoal2);
		}
			
		if(butTeam1 > butTeam2){                         //the winning team gets 3 points and each teams get 1 point if it's a drew
			t1.addPoints(3);
		}else if(butTeam1 == butTeam2){
			t1.addPoints(1);
			t2.addPoints(1);
		}
		else{
			t2.addPoints(3);
		}
		
		int gd = butTeam1 - butTeam2;	//note the goals difference of two teams
		t1.addGoalD(gd);	
		t2.addGoalD((-gd));
		
		t1.addGoalGM(butTeam1);          //note the goals
		t2.addGoalGM(butTeam2);
		
		System.out.println();
		
	}		
			
	
	
	public void playGroupMatch(){
		
		System.out.println("Round1");				//round 1
		for(Groupe g : tabGroups){
			System.out.println("Group"+g.getNb());
			playMatch(g.getTeam().get(0),g.getTeam().get(3));
			playMatch(g.getTeam().get(1),g.getTeam().get(2));
			
		}
		System.out.println();
		
		System.out.println("Round2");				//round 2
		for(Groupe g : tabGroups){
			System.out.println("Group"+g.getNb());
			playMatch(g.getTeam().get(0),g.getTeam().get(2));
			playMatch(g.getTeam().get(1),g.getTeam().get(3));
		}
		System.out.println();
		
		System.out.println("Round3");				//round 3
		for(Groupe g : tabGroups){
			System.out.println("Group"+g.getNb());
			playMatch(g.getTeam().get(0),g.getTeam().get(1));
			playMatch(g.getTeam().get(2),g.getTeam().get(3));
		}
	}
	
	public void qualification(){		//select the teams which is qualified 
		
		for (Groupe g: tabGroups){           
			g.comparer();				//Rank within the group
			
			thirdPlace.add(g.getTeam().get(2)); 		//put the third place in the list    
			
			g.getTeam().get(0).setPositionKnockout(g.getPos1());      //get the first place in position
			qualified[(g.getPos1())] = g.getTeam().get(0);          
			
			g.getTeam().get(1).setPositionKnockout(g.getPos2());       //get the second place in position
			qualified[(g.getPos2())] = g.getTeam().get(1);         
		}
		
		for(int j=0; j<5; j++){             //compare all the third places
			for(int i = 0; i < 5; i++){
				if(thirdPlace.get(i).inferieur(thirdPlace.get(i+1))){
					Team r = thirdPlace.set(i+1,thirdPlace.get(i));
					Team t1 = thirdPlace.set(i,r);
				}
			}
		}
		
		ArrayList<Integer> a = new ArrayList<Integer>();   //记录出线的四支小组第三球队的小组
		for(int i=0; i<4; i++){
			thirdPlace.get(i).setIsQualified(true);
			a.add(thirdPlace.get(i).getGroupe());
		}		
		
		//As the groups of the 4 third plce team qualified are different, the position of the teams are different.
		//There are 15 posibilities, we will do it one by one.
		
		if(a.contains(1)){		//1,_,_,_
			if(a.contains(2)){	//1,2,_,_
				if(a.contains(3)){	//1,2,3,_
					if(a.contains(4)){	//1,2,3,4
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;			//put the team of group 3 to position 12.
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[0] = t;
							}
						}
					}else if(a.contains(5)){	//1,2,3,5
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}else if(a.contains(6)){	//1,2,3,6
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
				else if(a.contains(4)){	//1,2,4,_
					if(a.contains(5)){	//1,2,4,5
						for(Team t:thirdPlace){
							if(t.getGroupe() == 4){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}
					else if(a.contains(6)){ //	1.2.4.6
						for(Team t:thirdPlace){
							if(t.getGroupe() == 4){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
				else if(a.contains(5)){	//1,2,5,6
					if(a.contains(6)){
						for(Team t:thirdPlace){
							if(t.getGroupe() == 5){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
							qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
			}
			else if (a.contains(3)){	//1,3,_,_
				if(a.contains(4)){	//1,3,4,_
					if(a.contains(5)){	//1,3,4,5
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}
					else if(a.contains(6)){	//1,3,4,6
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
				else if (a.contains(5)){	//1,3,5,6
					if(a.contains(6)){	
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}
				}
			}
			else if(a.contains(4)){	//1,3,4,5
				if (a.contains(5)){
					if(a.contains(6)){
						for(Team t:thirdPlace){
							if(t.getGroupe() == 4){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 1){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}
				}
			}
			
		}
		else if(a.contains(2)){	//2,_,_,_
			if(a.contains(3)){	//2,3,_,_
				if(a.contains(4)){	//1,3,4,_
					if(a.contains(5)){	//2,3,4,5
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}
					
					else if(a.contains(6)){	//2,3,4,6
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
				else if(a.contains(5)){	//2,3,5,6
					if(a.contains(6)){
						for(Team t:thirdPlace){
							if(t.getGroupe() == 5){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 3){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
			}
			else if(a.contains(4)){	//2,4,5,6
				if(a.contains(5)){
					if(a.contains(6)){
						for(Team t:thirdPlace){
							if(t.getGroupe() == 5){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 2){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[0] = t;
							}
						}
					}
				}
			}
			
		}
		else if(a.contains(3)){		//3,4.5.6
			if(a.contains(4)){	
				if(a.contains(5)){	
					if(a.contains(6)){
						for(Team t:thirdPlace){
							if(t.getGroupe() == 3){
								qualified[12] = t;
							}
							else if(t.getGroupe() == 4){
								qualified[4] = t;
							}
							else if(t.getGroupe() == 6){
								qualified[8] = t;
							}
							else if(t.getGroupe() == 5){
								qualified[0] = t;
							}
						}
					}
				}
			}
		}
		
	}
			
		
		
		
	
	public void printClassment(){
		for(Groupe g: tabGroups){
			System.out.println("Group"+g.getNb());
			System.out.println("  Team         GD  Point");
			int i = 1;
			for(Team t: g.getTeam()){
				String s =i + " " + t.getNation() + " " + t.getGoalD() + " " + t.getPoints();
				if (t.getIsQualified())
					s += "(Q)";
				System.out.println(s);
				i++;
			}
			System.out.println();
		}
	}
	
	public String toString(){
		String s ="";
		for(Groupe g: tabGroups){
			s += "Group" + g.getNb() + "\n";
			for(Team t: g.getTeam()){
				s +=  t.toString() + "\n";
			}
		}
		return s;
	}
}