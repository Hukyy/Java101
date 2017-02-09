package threads;

public class Threads {
	
	private static class Task implements Runnable{

		@Override
		public void run() {
			int counter = 100;
			while (counter>0){
				System.out.println("Running: " + counter);
				counter--;
			}
			
		}
		
	}

	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.start();
		//task.run();
		System.out.println("Done!");

	}

}
