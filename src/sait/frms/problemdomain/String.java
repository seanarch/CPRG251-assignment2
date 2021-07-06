package sait.frms.problemdomain;

public class String {
	private String code;
	private String airport;
	
	public String(){
		super();
	}
	
	public String(String code, String airport) {
		super();
		this.code = code;
		this.airport = airport;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getAirport() {
		return airport;
	}
	
	public void setAirport(String airport) {
		this.airport = airport;
	}	
	
	
	public String toString() {
		
	}
}