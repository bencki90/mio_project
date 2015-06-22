package functionParsing;

public enum Operation {
	ADD("+", 1, Associativity.LEFT){
		public double apply(double... args) {
			double sum = 0.0;
			for(double arg : args) sum += arg;
			return sum;
		}
	},
	SUBSTRACT("-", 1, Associativity.LEFT){
		public double apply(double... args) {
			double sum = 0.0;
			for(double arg : args) sum -= arg;
			return sum;
		}
	},
	MULTIPLY("*", 2, Associativity.LEFT){
		public double apply(double... args) {
			double sum = 0.0;
			for(double arg : args) sum *= arg;
			return sum;
		}
	},
	DIVISION("/", 2, Associativity.LEFT){
		public double apply(double... args) {
			double sum = 0.0;
			for(double arg : args) sum /= arg;
			return sum;
		}
	},
	POWER("^", 3, Associativity.RIGHT){
		public double apply(double... args) {
			return Math.pow(args[0], args[1]);
		}
	},
	SIN("sin", 2, Associativity.NONE){
		public double apply(double... args) {
			return Math.sin(args[0]);
		}
	},
	COS("cos", 2, Associativity.NONE){
		public double apply(double... args) {
			return Math.cos(args[0]);
		}
	},
	NONE("", 0, Associativity.NONE){
		public double apply(double... args) {
			return 0.0;
		}
	},
	OPENING_BRACKET("(", 0, Associativity.NONE){
		public double apply(double... args) {
			return 0.0;
		}
	},
	CLOSING_BRACKET(")", 0, Associativity.NONE){
		public double apply(double... args) {
			return 0.0;
		}
	};
	
	public enum Associativity{
		RIGHT,
		LEFT,
		NONE;
	};
	

	
	private final String symbol;
	public final int precendence;
	public final Associativity associative;
	
	private Operation(String symbol, int precendence, Associativity associativity){ 
		this.symbol = symbol;
		this.precendence = precendence;
		this.associative = associativity;
	}
	
	public String toString(){
		return symbol;
	}
	
	public static Operation matchOperation(String symbol){
		for(Operation operation : Operation.values()){
			if(operation.toString().equals(symbol)) return operation;
		}
		return Operation.NONE;
	}
	
	public abstract double apply(double... args);
}
