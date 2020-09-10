import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {

    public static void main(String[] args){
        HashMap<String, String> group_member = new HashMap<>();
        ArrayList<String> member = new ArrayList<>();
        member.add("Walter");
        member.add("Alex");
        member.add("Jane");
        ArrayList<String> leader = new ArrayList<>();
        leader.add("Danny");
        leader.add("Enya");
        leader.add("Andy");
        for (String string:member) {
            for (String l:leader) {
                group_member.put(string,l);
            }
        }
        
    }
}
