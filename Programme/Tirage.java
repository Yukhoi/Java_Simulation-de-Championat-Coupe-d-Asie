import java.util.ArrayList;
import java.io.*;

public class Tirage extends Grade {
	private ArrayList<Integer> deja; //Indiquez si le groupe a déjà dessiné dans une équipe de ce grade
	private ArrayList<Groupe> groupes;
	
	public Tirage(int nb, ArrayList<Groupe> groupes){
		
		super(nb);
		this.groupes = groupes;
		deja =  new ArrayList<Integer>();
	}
	public ArrayList<Team> getEquipe(){
		return equipe;
	}

	
	public ArrayList<Groupe> getGroupes(){
		return groupes;
	}
	
	public String setFilename(){								
		return "Files/AllTheTeams/Grade" + nb +".txt";
	}
	
	public void buildHost() {							//read the teams in the file with the same name
		File f = new File("Files/AllTheTeams/Host.txt");
		String tempString = null;
        BufferedReader reader = null;
        try { 
			reader = new BufferedReader(new FileReader(f));			
			while ((tempString = reader.readLine()) != null) {
				Host t = new Host(tempString);
				t.callUp();										//call up the players
				equipe.add(t);
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
	
	public void buildSeededTeam() {							//read the teams in the file with the same name
		File f = new File("Files/AllTheTeams/Grade1.txt");
		String tempString = null;
        BufferedReader reader = null;
        try { 
			reader = new BufferedReader(new FileReader(f));			
			while ((tempString = reader.readLine()) != null) {
				SeededTeam t = new SeededTeam(tempString);
				t.callUp();										//call up the players
				equipe.add(t);
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
	
	public void buildTeam() {							//read the teams in the file with the same name
		File f = new File(setFilename());
		String tempString = null;
        BufferedReader reader = null;
        try { 
			reader = new BufferedReader(new FileReader(f));			
			while ((tempString = reader.readLine()) != null) {
				Team t = new Team(tempString);
				t.callUp();										//call up the players
				equipe.add(t);
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
	
	public void draw(){
		for(int i = 0; i< equipe.size(); i++){
			if (equipe.get(i) instanceof Host){
				groupes.get(0).getTeam().add(equipe.get(i));
				deja.add(0);
			}
			while (equipe.get(i).getGroupe() == 0){
				int j=(int)(Math.random()*(groupes.size()));
				if (!(deja.contains(j))){   //Si le groupe sélectionné n'a pas d'équipe de ce niveau
					equipe.get(i).setGroupe(j+1);    
					groupes.get(j).getTeam().add(equipe.get(i));     
					deja.add(j);
				}
			}
		
		}
		deja.clear();  
		
	}	
}