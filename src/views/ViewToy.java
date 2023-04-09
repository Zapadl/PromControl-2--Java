package views;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import controllers.Controller;
import model.Toy;

public class ViewToy {

    Toy toy;
    private Controller controller;

    public ViewToy(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Queue<Toy> prizes = new LinkedList<>();
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду:\n" +
                    "LIST - просмотр игрушек для розыгрыша\n" +
                    "CREATE - добавление новой игрушки\n" +
                    "UPDATE - изменение частоты выпадания\n" +
                    "DELETE - удаление игрушки\n" +
                    "PUT - розыгрыш призов\n" +
                    "GET - выдача приза\n" +
                    "EXIT - выход\n");
            try {
                com = Commands.valueOf(command);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Неопознанная команда");
            }
            if (com == Commands.EXIT)
                return;
            try {
                switch (com) {

                    case CREATE:
                        Toy toy = setToy(false);
                        controller.saveToy(toy);
                        break;
                    case LIST:
                        List<Toy> toyList = controller.readToyList();
                        for (Toy item : toyList) {
                            System.out.println(item);
                            System.out.println();
                        }
                        break;
                    case UPDATE:
                        // String updateId = prompt("Идентификатор игрушки: ");
                        Toy updateToy = setToy(true);
                        controller.updateToy(updateToy);
                        break;
                    case DELETE:
                        String deleteId = prompt("Идентификатор игрушки: ");
                        controller.deleteToy(deleteId);
                        break;
                    case PUT:
                        prizes = controller.putToy();
                        break;
                    case GET:
                        controller.getToy(prizes);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Toy setToy(boolean forUpdate) {
        String idString = null;
        if (forUpdate) {
            idString = prompt("Идентификатор игрушки: ");
        }
        String name = prompt("Название: ");
        String count = prompt("Количество: ");
        String weight = prompt("Частота выпадения: ");
        if (forUpdate) {
            return new Toy(idString, name, count, weight);
        }
        return new Toy(name, count, weight);
    }
}