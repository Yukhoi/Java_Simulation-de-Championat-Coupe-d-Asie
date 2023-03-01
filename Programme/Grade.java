import java.util.ArrayList;
import java.io.*;

public abstract class Grade{
	protected int nb;
	protected ArrayList<Team> equipe;
	
	
	public Grade(int nb){
		equipe = new ArrayList<Team>();
		this.nb = nb;
	}
	
	public abstract String setFilename();
	
	public abstract ArrayList<Team> getEquipe();
	
	public abstract void buildHost();
	
	public abstract void buildSeededTeam();
	
	public abstract void buildTeam();
	
	public abstract void draw();
}