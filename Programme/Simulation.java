import java.util.ArrayList;

public class Simulation{

	private GroupMatch gm;
	private Knockout k;
	
	public Simulation (){
		gm = gm.getInstance();
		k = k.getInstance();
	}
	
	public void buildUp(){
		Groupe g1 = new Groupe(1,13,2);
		Groupe g2 = new Groupe(2,5,14);
		Groupe g3 = new Groupe(3,9,3);
		Groupe g4 = new Groupe(4,1,11);
		Groupe g5 = new Groupe(5,10,7);
		Groupe g6 = new Groupe(6,6,15);
		
		ArrayList<Groupe> listGroupe = new ArrayList<Groupe>();
		listGroupe.add(g1);
		listGroupe.add(g2);
		listGroupe.add(g3);
		listGroupe.add(g4);
		listGroupe.add(g5);
		listGroupe.add(g6);
		
		
		Tirage grade1 = new Tirage(1, listGroupe);
		grade1.buildHost();
		for(Team t: grade1.getEquipe()){
			System.out.println(t.getNation());
		}
		grade1.buildSeededTeam();
		grade1.draw();
		
		Tirage grade2 = new Tirage(2, listGroupe);
		grade2.buildTeam();
		grade2.draw();
		
		Tirage grade3 = new Tirage(3, listGroupe);
		grade3.buildTeam();
		grade3.draw();
		
		Tirage grade4 = new Tirage(4, listGroupe);
		grade4.buildTeam();
		grade4.draw();
		
		gm.setTabGroups(listGroupe);
		System.out.println("Everyone is ready!");
		System.out.println(gm.toString());
	}
	
	public void groupMatchAction(){
		gm.playGroupMatch();
		gm.qualification();
		gm.printClassment();
		
		k.setListTeam(gm.getQualified());
	}
	
	public void knockoutAction(){
		k.roundOf16();
		k.quaterfinal();
		k.semifinal();
		k.roundFinal();
	}
	
	public void bestShooter(){                    //choose the best shooter whithin all the matches
		ArrayList<Player> shooter = new ArrayList<Player>();
		
		for(Groupe g: gm.getTabGroups()){
			for(Team t: g.getTeam()){
				for(Player p:t.getTabPlayers()){
					if(shooter.isEmpty()){
						shooter.add(p);
					}else{
						if(p.getNbGoal() > shooter.get(0).getNbGoal()){
							shooter.clear();
							shooter.add(p);
						}else if (p.getNbGoal() == shooter.get(0).getNbGoal()){
							shooter.add(p);
						}
					}
				}
			}
		}
		String s = "The Best Shooter:" + "\n";
		for(Player p: shooter){
			s += p.toString() + "\n";
		}
		s += "scored " + shooter.get(0).getNbGoal() + " goals";
		System.out.println(s);
	}
	
	public void conclusion(){
		System.out.println("\n" + gm.getTabGroups().get(1).getTeam().get(1).getTabPlayers().get(1).getNbGoalTotal() + " goals have been scored in this championship." + "\n");
	}
							
}