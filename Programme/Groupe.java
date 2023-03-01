import java.util.ArrayList;

public class Groupe {
	ArrayList<Team> team;
	private int nb;
	private final int pos1;
	private final int pos2;
	
	public Groupe (int nb, int pos1, int pos2){
		team = new ArrayList<Team>();
		this.nb = nb;
		this.pos1 = pos1;
		this.pos2 = pos2;
	}
	
	public int getNb(){
		return nb;
	}
	
	public ArrayList<Team> getTeam(){
		return team;
	}
	
	public int getPos1(){
		return pos1;
	}
	
	public int getPos2(){
		return pos2;
	}
	
	public void comparer(){
		for(int j=0; j<3; j++){
			for(int i = 0; i < 3; i++){
				if(team.get(i).inferieur(team.get(i+1))){
					Team r = team.set(i+1,team.get(i));
					Team t1 = team.set(i,r);
				}
			}
		}
		for (int i=0; i<2; i++)
			team.get(i).setIsQualified(true);
	}
				
	
	public String toString (){
		String s = "";
		s += "Group" + nb + "\n";
		for(int i = 0; i< team.size(); i++)
			s += team.get(i).getNation() + "\n";
		return s;
	}
}