package controllers;

import java.util.List;
import java.util.Queue;

import model.Repository;
import model.Toy;

public class Controller {

    private final Repository repository;


    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void saveToy(Toy toy) throws Exception {
        validateToy(toy);
        repository.CreateToy(toy);
    }

    public List<Toy> readToyList() {
        return repository.getAllToys();
    }

    public Toy updateToy(Toy toy) throws Exception{
        validateToy(toy);
        return repository.updateToy(toy);
    }

    public void deleteToy(String deleteId) throws Exception {
        repository.deleteToy(deleteId);
    }

    public Queue<Toy> putToy() {
        return repository.putToy();
    }

    public void getToy(Queue<Toy> prizes) throws Exception {
        if(!prizes.isEmpty()){
            repository.getToy(prizes);
        }
        else {
            throw new Exception("Отсутствуют призы. Проведите розыгрыш.");
        }
    }

    private void validateToy(Toy toy) throws Exception{
        if (toy.getName().isEmpty()) {
            throw new Exception("Отсутствует название");
        }
        if (toy.getCount().isEmpty()) {
            throw new Exception("Отсутствует количество");
        }
        if (toy.getWeight().isEmpty()) {
            throw new Exception("Отсутствует частота выпадения");
        }
    }
}