package model;

import java.util.List;
import java.util.Queue;

public interface Repository {
    List<Toy> getAllToys();
    String CreateToy(Toy toy);
    Toy updateToy(Toy toy) throws Exception;
    void deleteToy(String deleteId) throws Exception;
    Queue<Toy> putToy();
    void getToy(Queue<Toy> prizes) throws Exception;
}