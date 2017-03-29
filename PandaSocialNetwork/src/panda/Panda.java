package panda;

public class Panda {
	private String Name ="";
	private String E_mail= "";
	private String Gender="";
	
	Panda(String Name, String E_mail, String Gender){
		this.Name = Name;
		setEmail(E_mail);
		this.Gender = Gender;
	}
	
	public void setEmail(String new_email){
		if (new_email.matches("\\S+@\\S+\\.\\S+") && new_email.length() >= 5){
			this.E_mail=new_email;
		}
	}
	
	public String name(){
		return this.Name;
	}
	public String email(){
		return this.E_mail;
	}
	public String gender(){
		return this.Gender;
	}
	public boolean isMale(){
		return this.gender().equals("male");
	}
	public boolean isFemale(){
		return this.gender().equals("female");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((E_mail == null) ? 0 : E_mail.hashCode());
		result = prime * result + ((Gender == null) ? 0 : Gender.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Panda other = (Panda) obj;
		if (E_mail == null) {
			if (other.E_mail != null)
				return false;
		} else if (!E_mail.equals(other.E_mail))
			return false;
		if (Gender == null) {
			if (other.Gender != null)
				return false;
		} else if (!Gender.equals(other.Gender))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}
	public String toString(){
		String result = this.Name+" "+this.E_mail + " "+this.Gender;
		return result;
	}
	
	
}