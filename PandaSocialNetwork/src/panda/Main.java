package panda;

import java.util.ArrayList;

public class Main {
 
    public static void main(String[] args) {
        Panda pandichka = new Panda("Gosho", "gsdf@abv.bg", "male");
        Panda new_pandichka = new Panda("Pesho", "gsdf@abv.bg","female");
        Panda panda = new Panda("0", "gsdf@abv.bg", "male");
        Panda panda1 = new Panda("1", "gsdf@abv.bg", "male");
        Panda panda2 = new Panda("2", "gsdf@abv.bg", "male");
        Panda panda3 = new Panda("3", "gsdf@abv.bg", "male");
        Panda panda4 = new Panda("4", "gsdf@abv.bg", "male");
        Panda panda5 = new Panda("5", "gsdf@abv.bg", "male");
        Panda panda8 = new Panda("8", "gsdf@abv.bg", "male");
        Panda panda9 = new Panda("9", "gsdf@abv.bg", "male");
        Panda panda10 = new Panda("0", "gsdf@abv.bg", "male");
       
        SocialGraph graph = new SocialGraph();
//      graph.addPanda(pandichka);
//      graph.makeFriends(pandichka, new_pandichka);
//      graph.makeFriends(new_pandichka, panda);
//      System.out.println(graph.hasPanda(new_pandichka));
//      System.out.println(graph.areFriends(pandichka, new_pandichka));
//      ArrayList<Panda> pandas = graph.friendsOf(pandichka);
       
        graph.addPanda(panda);
        graph.addPanda(panda1);
        graph.addPanda(panda2);
        graph.addPanda(panda3);
        graph.addPanda(panda4);
        graph.addPanda(panda5);
        graph.addPanda(panda8);
        graph.addPanda(panda9);
        graph.makeFriends(panda, panda1);
        graph.makeFriends(panda1, panda2);
        //graph.makeFriends(panda2, panda);
        graph.makeFriends(panda2, panda3);
        graph.makeFriends(panda3, panda8);
        //graph.makeFriends(panda2, panda8);
        graph.makeFriends(panda8, panda10);
       
        System.out.println(graph.connectionLevel(panda, panda10));
       // System.out.println(graph.areConnected(panda10, pandichka));
 
    }
 
}