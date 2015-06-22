package functionParsing;

public enum Operations {
	ADD("+"),
	SUBSTRACT("-"),
	MULTIPLY("*"),
	POWER("^"),
	SIN("sin"),
	COS("cos");
	
	
	private final String symbol;
	
	private Operations(String symbol){ 
		this.symbol = symbol;
	}
	
	public String toString(){
		return symbol;
	}	
}
