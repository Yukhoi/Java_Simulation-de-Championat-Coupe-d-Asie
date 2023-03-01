public class TestSimulation{
	public static void main(String[] args){
		Simulation s = new Simulation();
		s.buildUp();
		
		
		s.groupMatchAction();
		
		s.knockoutAction();
		s.conclusion();
		s.bestShooter();
		
	}
}
		