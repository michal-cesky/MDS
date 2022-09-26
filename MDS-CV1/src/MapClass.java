import java.util.ArrayList;
import java.util.HashMap;

public class MapClass {
    private HashMap<Integer, String> map = new HashMap<Integer, String>();


    public void store(Integer id, String value){
        map.put(id, value);
    }

    public String getValue(Integer id) {
        String value = map.get(id);
        return value;
    }

    public void deleteKey(Integer id){
        map.remove(id);
    }

    public int getSize(){
       int size = map.size();
       return size;
    }

    public void print(){
        for (Integer key: map.keySet()){
            System.out.println(key +"->"+map.get(key));
        }
    }
}
