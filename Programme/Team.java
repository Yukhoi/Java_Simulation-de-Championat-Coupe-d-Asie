import java.util.ArrayList;
import java.io.*;


public class Team{
	protected String nation;
	protected int groupe = 0;
	protected int grade;
	protected ArrayList<Player> tabPlayers;
	protected int points = 0;
	protected int goalD = 0;		//Goal difference in the group match
	protected int goalGM =0;		//Goals scored in the group	match
	protected boolean isQualified = false;	//Determine if the team advances to the knockout round
	protected int positionKnockout = -1;	//position in the knockout stage
	protected final double posibilityDoping = 0.01;
	protected boolean doping = false;
	
		
	public Team(String nation){
		this.nation = nation;
		tabPlayers = new ArrayList<Player>();
	}
	
	public String getNation(){
		return nation;
	}
	
	public int getGroupe(){
		return groupe;
	}
	
	public void setGroupe(int i){
		groupe = i;
	}
	
	public ArrayList<Player> getTabPlayers() {
        return tabPlayers;
    }
	
	public int getPoints(){
		return points;
	}
	
	public void addPoints(int i){	
		points += i;
	}
	
	public int getGoalD(){
		return goalD;
	}
	
	public void addGoalD(int i){	
		goalD += i;
	}
	
	public int getGoalGM(){
		return goalGM;
	}
	
	public void addGoalGM(int i){
		goalGM += i;
	}
	
	public void setIsQualified(boolean i){
		isQualified = i;
	}
	
	public boolean getIsQualified(){
		return isQualified;
	}
	
	public void setPositionKnockout(int i){
		positionKnockout = i;
	}
	
	public int getPositionKnockout(){
		return positionKnockout;
	}
	
	public double getPosibilityDoping(){
		return posibilityDoping;
	}
	
	public boolean getDoping(){
		return doping;
	}
	
	public void setDoping(){
		doping = true;
	}
	
	public void cheating(){
		points = -1;
		for(Player p : tabPlayers){
			p.setNbGoal(0);
		}
	}
		
	public boolean inferieur(Team t){		//Determine which team is ranked higher
		if (this.points < t.getPoints()) return true;
		
		else if (this.points == t.getPoints()){
			if (this.goalD < t.getGoalD()) return true;
			
			else if (this.goalD == t.getGoalD()){
				if (this.goalGM < t.getGoalGM()) return true;
				
				else if (this.goalGM == t.getGoalGM()){  //If the scores, goal difference, and goals of the two teams are equal, 
															//then the draw is used to determine who is ranked higher
					double i = Math.random();
					double j = Math.random();
					while(i == j){
						i = Math.random();
						j = Math.random();
					}
					return (i<j);
				}
			}
		}
		return false;
	}
	
	public String setFilename(){
		return "Files/AllThePlayers/" + nation + ".txt";
	}
	
	public void callUp() {								//Read players in the file with the same name as the team
		File f = new File(setFilename());
		String tempString = null;
         int i = 1;
         BufferedReader reader = null;
        try { 
			reader = new BufferedReader(new FileReader(f));
			while ((tempString = reader.readLine()) != null) {
				Player s = new Player(i, tempString, nation);
				tabPlayers.add(s);
				i++;
			}
			reader.close();
		}
		catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	
	public String toString(){
		String s = nation + "\n";
		for(int i = 0; i< tabPlayers.size(); i++)
			s += tabPlayers.get(i).toString() + "\n";
		return s;
	}
}