import java.util.ArrayList;
import java.util.HashMap;

public class MapClass {
    private HashMap<Integer, String> map = new HashMap<Integer, String>();


    public void store(Integer id, String value){

        try {
            map.put(1, "10");
            map.put(2, "20");
            map.put(3, "30");
            map.put(4, "40");

        } catch (ArrayStoreException e) {
            System.out.println("Chyba id je jiy v mape . " + e.getMessage());
        }
    }

    public String getValue(Integer id){
        try {
            map.get(1);

        } catch (NoSuchFieldException e) {
            System.out.println("Chyba id je jiy v mape . " + e.getMessage());
        }
    }
}
