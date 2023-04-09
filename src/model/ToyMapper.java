package model;

public class ToyMapper {
    public String map(Toy toy) {
        return String.format("Id:%s;Название:%s;Количество:%s;Частота выпадения:%s", toy.getId(), toy.getName(), toy.getCount(), toy.getWeight());
    }

    public Toy map(String line) {
        String[] lines = line.split(";");
        for(int i = 0; i<lines.length; i++){
            String[] temp = lines[i].split(":");
            lines[i] = temp[1];
        }
        return new Toy(lines[0], lines[1], lines[2], lines[3]);
    }
}