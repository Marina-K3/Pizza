package pizza.util.exseptions;

public class NotFoundExseption extends RuntimeException{
    public NotFoundExseption(String message) {
        super(message);
    }
}
