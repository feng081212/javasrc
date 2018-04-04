package test;


public class CustomThreadImpl extends CustomThread{

	@Override
	public void runSingle() {
		//while(true) ;
			//System.out.println("CustomThreadImpl start");
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}

	@Override
	public boolean isCycle() {
		return false  ;
	}
	
	public static void main(String[] args) {
		CustomThreadImpl customThreadImpl = new CustomThreadImpl() ;
		customThreadImpl.start();
//		try {
//			Class cla = Class.forName(CustomThreadImpl.class.getName()) ;
//			Method method = cla.getMethod("start", null) ;
//			Object object = cla.newInstance() ;
//			method.invoke(object, null) ;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		while(customThreadImpl.isAlive()){
			try {
				currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(customThreadImpl.getId()) ;
			customThreadImpl.interrupt();
			System.out.println(customThreadImpl.isInterrupted());
		}
	}
}
