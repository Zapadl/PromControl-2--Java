package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class RepozitoryFileNew implements Repository {

    ToyMapper mapper = new ToyMapper();
    FileOperation fileOperation;

    public RepozitoryFileNew(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Toy> getAllToys() {
        List<String> lines = fileOperation.readAllLines();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public String CreateToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy item : toys) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        toy.setId(id);
        toys.add(toy);
        saveUsers(toys);
        return id;
    }

    void saveUsers(List<Toy> toys) {
        List<String> lines = new ArrayList<>();
        for (Toy item : toys) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public Toy updateToy(Toy toy) throws Exception {
        List<Toy> toys = getAllToys();
        Toy foundedToy = findToyById(toys, toy.getId());
        foundedToy.setWeight(toy.getWeight());
        saveUsers(toys);
        return foundedToy;
    }

    public void updateCount(Toy toy) throws Exception {
        List<Toy> toys = getAllToys();
        Toy foundedToy = findToyById(toys, toy.getId());
        foundedToy.setCount(Integer. toString(Integer.parseInt(foundedToy.getCount()) - 1));
        saveUsers(toys);
    }

    private Toy findToyById(List<Toy> toys, String toyId) throws Exception {
        for (Toy toy : toys) {
            if (toy.getId().equals(toyId)) {
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    @Override
    public void deleteToy(String deleteId) throws Exception {
        List<Toy> toys = getAllToys();
        Toy deleteToy = findToyById(toys, deleteId);
        toys.remove(deleteToy);
        saveUsers(toys);
    }

    @Override
    public Queue<Toy> putToy() {
        List<Toy> toys = getAllToys();
        List<Toy> temp = new ArrayList<>();
        Queue<Toy> prizes = new LinkedList<>();
        for (Toy toy : toys) {
            int count = Integer.parseInt(toy.getWeight())/10;
            for (int i = 0; i < count; i++) {
                temp.add(toy);
            }
        }
        if (temp.size() < 10) {
            int correct = 10 - temp.size();
            Random random = new Random();
            for (int i = 0; i < correct; i++){
                int randomIndex = random.nextInt(temp.size());
                temp.add(temp.get(randomIndex));
            }
        }
        Collections.shuffle(temp);
        for (Toy toy : temp) {
            prizes.offer(toy);
        }
        return prizes;
    }

    @Override
    public void getToy(Queue<Toy> prizes) throws Exception {
        Toy toysPriz = prizes.poll();
        System.out.println(prizes);
        String line = mapper.map(toysPriz);
        fileOperation.savePriz(line);
        List<Toy> toys = getAllToys();
        Toy updateToy = findToyById(toys, toysPriz.getId());
        updateCount(updateToy);
    }




}