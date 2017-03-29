package breakfast;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<Kotlon> pq = new PriorityQueue<>(21);
		Scanner scanner = new Scanner(System.in);
		int requests = scanner.nextInt();
		for (int j =0;j<requests;j++){
			int mekici = scanner.nextInt();
			int broi_kotloni = scanner.nextInt();
			int power_kotlon;
			for (int i=0;i<broi_kotloni;i++){
				power_kotlon = scanner.nextInt();
				Kotlon kotlon = new Kotlon(power_kotlon,0);
				pq.add(kotlon);
			}
			
			int max = 0;
			for (int i=0;i<mekici;i++){
				Kotlon kotlon = pq.poll();
				int max_potential = kotlon.power * (kotlon.count+1);
				if (max<max_potential){
					max = max_potential;
				}
				kotlon.count +=1;
				pq.add(kotlon);
			}
			System.out.println(max);
			pq.clear();
		}
		scanner.close();
}

}
