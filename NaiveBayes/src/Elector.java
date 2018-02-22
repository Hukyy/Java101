public class Elector{
    private String[] attributes;

    public Elector(String input){
        attributes = input.split(",");
    }

    public String getClassName(){
        return attributes[0];
    }

    public String getAttribute(int index){
        return attributes[index];
    }
}