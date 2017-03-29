package breakfast;

public class Kotlon implements Comparable<Kotlon>{
	public Integer power;
	public Integer count;	
	public Kotlon(Integer power, Integer count){
		this.power = power;
		this.count = count;
	}
	
	@Override
	public int compareTo(Kotlon other) {
		
		Integer summaryPower = (count+1)*power;
		Integer otherPower = (other.count+1) * other.power;
		return summaryPower.compareTo(otherPower);
	}
}