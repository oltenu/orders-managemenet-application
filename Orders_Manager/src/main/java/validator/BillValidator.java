package validator;

public class BillValidator {
    private long currentId = 0;

    public long getCurrentId(){
        return currentId++;
    }
}
