public class Player{

	private String name;
	private String nationality;
	private int number;
	private int nbGoal = 0;
	private static int nbGoalTotal = 0;
	
	public Player(int number, String name, String nationality){
		this.name = name;
		this.number = number;
		this.nationality = nationality;
	}
	
	public Player(Player p){
		this.name = p.getName();
		this.nationality = p.getNation();
		this.number = p.getNB();
	}
	
	public int getNbGoal(){
		return nbGoal;
	}
	
	public int getNB(){
		return number;
	}
	
	public String getNation(){
		return nationality;
	}
	
	public void addNbGoal(){
		nbGoal += 1;
		nbGoalTotal += 1;
	}
	
	public void setNbGoal(int i){
		nbGoal = i;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNbGoalTotal(){
		return nbGoalTotal;
	}
	
	public Player clone(Player p){
		return new Player(p.getNB(), p.getName(), p.getNation());
	}
	
	public String toString(){
		return number +" "+ name + " " + nationality;
	}
}