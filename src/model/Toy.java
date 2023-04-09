
package model;

public class Toy {
    private String id = "";
    private String name;
    private String count;
    private String weight;

    public Toy (String name, String count, String weight){
        this.name = name;
        this.count = count;
        this.weight = weight;
    }

    public Toy (String id, String name, String count, String weight){
        this(name, count, weight);
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    public String getWeight() {
        return weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Id: %s,\nНазвание: %s,\nКоличество: %s,\nЧастота выпадения: %s", id, name, count, weight);
    }
}