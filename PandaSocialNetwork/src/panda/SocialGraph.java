package panda;

import java.util.*;


public class SocialGraph {
    private HashMap<Panda, ArrayList<Panda>> graph = new HashMap<>();
   
    public boolean hasPanda(Panda panda){
        return graph.containsKey(panda);
    }
    public void addPanda(Panda panda){
        if (!this.hasPanda(panda)){
            graph.put(panda, new ArrayList<Panda>());
        }
    }
    public void makeFriends(Panda panda1, Panda panda2){
        if (!this.hasPanda(panda1)){
            graph.put(panda1, new ArrayList<Panda>());
        }
        if (!this.hasPanda(panda2)){
            graph.put(panda2, new ArrayList<Panda>());
        }
       
        graph.get(panda1).add(panda2);
        graph.get(panda2).add(panda1);
 
    }
    public boolean areFriends(Panda panda1, Panda panda2){
        return graph.get(panda1).contains(panda2);
    }
    public ArrayList<Panda> friendsOf(Panda panda){
        return graph.get(panda);
    }
    public int connectionLevel(Panda panda1,Panda panda2){
        if (panda1 == panda2){
            return -1;
        }
        int level = 0;
        int size = 1;
        Queue<Panda> q = new LinkedList<Panda>();
        q.add(panda1);
        //ArrayList<Panda> visited = new ArrayList<>();
        Set<Panda> visited = new HashSet<>();
        while (!q.isEmpty()){
            Panda front = q.poll();
            ArrayList<Panda> list = graph.get(front);
            visited.add(front);
            for (Panda p :list){
                if (p.equals(panda2)){
                    return level+1;
                }
                if (!visited.contains(p)){
                    q.add(p);
                }
            }
            size--;
            if (size == 0){
                level++;
                size = q.size();
            }
        }
        return -1;
    }
    public boolean areConnected(Panda panda1, Panda panda2){
        //ArrayList<Panda> visited = new ArrayList<>();
        Set<Panda> visited = new HashSet<>();
        Stack<Panda> s = new Stack<Panda>();
        s.push(panda1);
        while (!s.empty()){
            Panda panda = s.pop();
            if (visited.contains(panda)){
                continue;
            }
            visited.add(panda);
            for (Panda p: graph.get(panda)){
                if (p.equals(panda2)){
                    return true;
                }
                s.push(p);
            }
        }
       
        return false;
    }
   
    public int howManyGenderInNetwork(int level,Panda panda,String gender){
        int lvl = 0;
        int size = 1;
        //int result = 0;
        Queue<Panda> q = new LinkedList<Panda>();
        q.add(panda);
        //ArrayList<Panda> visited = new ArrayList<>();
        Set<Panda> visited = new HashSet<>();
        while (!q.isEmpty() && level != lvl){
            Panda front = q.poll();
 
            visited.add(front);
            ArrayList<Panda> list = graph.get(front);
            for (Panda p :list){
                if (!visited.contains(p)){
                    q.add(p);
                }
            }
            size--;
            if (size == 0){
                lvl++;
                size = q.size();
            }
        }
        int result = 0;
        for (Panda p:q){
            if (p.gender().equals(gender)){
                System.out.println(p);
                result++;
            }
        }
        return result;
    }
}
