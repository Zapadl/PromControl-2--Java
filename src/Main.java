import controllers.Controller;
import model.FileOperation;
import model.FileOperationImpl;
import model.Repository;
import model.RepozitoryFileNew;
import views.ViewToy;

public class Main {
    public static void main(String[] args) {

        FileOperation fileOperation = new FileOperationImpl("toys.txt");
        Repository repository = new RepozitoryFileNew(fileOperation);
        Controller controller = new Controller(repository);
        ViewToy view = new ViewToy(controller);
        view.run();
    }
}