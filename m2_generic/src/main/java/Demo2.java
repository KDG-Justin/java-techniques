import be.kdg.java2.gameproject.data.Data;
import be.kdg.java2.gameproject.generics.PriorityQueue;
import be.kdg.java2.gameproject.model.Game;

import java.util.List;

public class Demo2 {
    public static void main(String[] args){
        PriorityQueue<String> myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));

        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();

        System.out.println("Size na dequeue: " + myQueue.getSize());

        myQueue.enqueue(String.valueOf(Data.getData().get(0)), 2);
        myQueue.enqueue(String.valueOf(Data.getData().get(1)), 1);
        myQueue.enqueue(String.valueOf(Data.getData().get(5)), 3);
        myQueue.enqueue(String.valueOf(Data.getData().get(6)), 4);
        myQueue.enqueue(String.valueOf(Data.getData().get(4)), 5);
        System.out.println(myQueue.toString());
    }
}
