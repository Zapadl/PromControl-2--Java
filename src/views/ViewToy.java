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
            String command = prompt("������� �������:\n" +
                    "LIST - �������� ������� ��� ���������\n" +
                    "CREATE - ���������� ����� �������\n" +
                    "UPDATE - ��������� ������� ���������\n" +
                    "DELETE - �������� �������\n" +
                    "PUT - �������� ������\n" +
                    "GET - ������ �����\n" +
                    "EXIT - �����\n");
            try {
                com = Commands.valueOf(command);
            }
            catch (IllegalArgumentException e) {
                System.out.println("������������ �������");
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
                        // String updateId = prompt("������������� �������: ");
                        Toy updateToy = setToy(true);
                        controller.updateToy(updateToy);
                        break;
                    case DELETE:
                        String deleteId = prompt("������������� �������: ");
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
            idString = prompt("������������� �������: ");
        }
        String name = prompt("��������: ");
        String count = prompt("����������: ");
        String weight = prompt("������� ���������: ");
        if (forUpdate) {
            return new Toy(idString, name, count, weight);
        }
        return new Toy(name, count, weight);
    }
}