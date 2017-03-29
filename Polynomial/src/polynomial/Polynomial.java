package polynomial;

public class Polynomial
{
	private int[] polynom = new int[1];
	private int power = 0;

	private void resize(int index){
		int[] temp = new int[index+1];
		for (int i=0;i<power;i++){
			temp[i] = polynom[i];
		}
		for (int i=power;i<=index;i++){
			temp[i] = 0;
		}
		polynom=temp;
		power = index+1;
	}

	private void adding(int coef, int index){
		if (index >= power){
			resize(index);
		}
		polynom[index] += coef;
	}


	public Polynomial(){}
	public Polynomial(String input){
		String[] helper;
		String[] mononoms = input.split("(?=-)|(\\+)");
		for (int i=0; i<mononoms.length;i++){
			mononoms[i] = mononoms[i].replaceAll("\\s+","");
			if (mononoms[i].contains("^")){
				mononoms[i]=mononoms[i].replace("x^", " ");
				helper= mononoms[i].split(" ");
				int coef = Integer.parseInt(helper[0]);
				int pow = Integer.parseInt(helper[1]);
				this.add(coef, pow);
			}else if (mononoms[i].contains("x")){
				int coef = Integer.parseInt(mononoms[i].replace("x",""));
				this.add(coef, 1);
			}else{
				int coef = Integer.parseInt(mononoms[i]);
				this.add(coef,0);
			}
		}
	}


	public Polynomial(Polynomial other){
		this.power = other.power;
		polynom = new int[power];
		for (int i=0;i<power;i++){
			polynom[i] = other.polynom[i];
		}
	}

	public void add(int coef, int index){
		if (index >= power){
			resize(index);
		}
		polynom[index] = coef;
	}

	public Polynomial add(Polynomial other){
		Polynomial result = new Polynomial(other);
		for(int i=power-1;i>=0;i--){
			result.adding(polynom[i], i);
		}
		return result;
	}

	public Polynomial substract(Polynomial other){
		Polynomial result = new Polynomial(other);
		for(int i=power-1;i>=0;i--){
			result.adding(-polynom[i], i);
		}
		return result;
	}

	public Polynomial multiplicate(Polynomial other){
		Polynomial result = new Polynomial();
		result.resize(power+other.power -2); //max power = power1*power2  (we have int[power+1]) example 10*10 = 20; we need int[21]  resize(20) -> int[21], 20=11+11-2;
		for (int i=0;i<power;i++){
			for (int j=0; j<other.power;j++){
				result.adding(polynom[i] * other.polynom[j], i+j); 
			}
		}
		return result;
	}

	public int evaluate(int x){
		int result = 0;
		int pow = x;
		for (int i=1; i<power; i++){
			result += pow * polynom[i];
			pow *= x;
		}
		result += polynom[0];
		return result;
	}



	public Polynomial multiplicate(int coef){
		Polynomial result = new Polynomial(this);
		for (int i=0; i<power; i++){
			result.polynom[i] *= coef;
		}
		return result;
	}

	public Polynomial derivative(){
		Polynomial result = new Polynomial();
		for (int i=power-1; i>0; i--){
			result.add(i*polynom[i], i-1);
		}
		return result;
	}
	public String toString(){
		String result = "";
		if (polynom[power-1] != 1) result += polynom[power-1];
		result +=  "x^" + (power-1) + " ";
		for(int i = power-2;i>1;i--){
			if (polynom[i] > 0){
				result += "+";
			}
			if (polynom[i] !=0){
				if (polynom[i] != 1) result+= " " + polynom[i];
				result += "x^" +i;
			}
		}
		if (polynom[1] != 0){
			if (polynom[1] > 0){
				result += " +";
			}
			else result+=" ";
			if (polynom[1] == -1) result+= "-";
			else if (polynom[1] != 1) result+= polynom[1];

			result += "x";
		}
		if (polynom[0] != 0){
			if (polynom[0] > 0){
				result += " +";
			}
			result += " "+ polynom[0];
		}
		return result;
	}
}