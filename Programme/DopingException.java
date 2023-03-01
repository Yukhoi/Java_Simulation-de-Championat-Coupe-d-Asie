public class DopingException extends Exception{
	
	private Team t;
	
	
	
	public DopingException(Team t,String message){
		
		super(message);
		this.t = t;
	}
	
	public String getNation(){
		return t.getNation();
	}
}