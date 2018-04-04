package test;

public class JVM {
	
	public void printRunningParam(){
		Runtime run = Runtime.getRuntime() ;
		System.out.println(run.maxMemory());
	}
	
	public static void main(String[] args) {
		System.setProperty("Xmx", "10m") ;
		new JVM().printRunningParam();
	}
}
