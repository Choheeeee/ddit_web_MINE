package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS('+', (l,r)->l+r), 
	MINUS('-', (l,r)->l-r), 
	MULTIPLY('*', (l,r)->l*r), 
	DIVIDE('/', new BiOperandOperator() {
		public double operate(double leftOp, double rightOp) {
			return leftOp / rightOp;
		}
	}); //이 4개의 객체는 OperatorType의 상수객체이고, 각 4개의 상수객체는 sign & realOperator &  getSign() & operate() & getExpression()을 속성으로 갖음.
	
	
	//생성자메서드
	private OperatorType(char sign, BiOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}//end 생성자메서드
	
	
	//OperatorType의 인스턴스들이 갖는 속성들 (멤버변수 2개, 메서드 3개)
	private BiOperandOperator realOperator; //멤버변수
	private char sign;						//멤버변수
	public char getSign() {					//메서드
		return sign;
	}
	
	public double operate(double leftOp, double rightOp) { //메서드
		return realOperator.operate(leftOp, rightOp);
	}
	
	public String getExpression(double leftOp, double rightOp) { //메서드
		return String.format("%f %c %f = %f", leftOp, sign, rightOp, operate(leftOp, rightOp));
	}
	
	//행위는 없지만 그 행위를 어떻게 실행할지 시그니처를 정의
	@FunctionalInterface
	public static interface BiOperandOperator{
		public double operate(double leftOp, double rightOp);
	}//end 인터페이스
}




