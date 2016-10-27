import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Set{
   private int id;
   private String name;
   private ArrayList<Pair<String, String>> termValue;  

   public Set() {}
   public Set(String name) {
      this.name = name;
      this.termValue = new ArrayList<Pair<String, String>>();
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String newName) {
      //handle name errors
      this.name = newName;
   }
   public ArrayList<Pair<String, String>> getPairs() {
      return termValue;
   }
   public void setPairs(ArrayList<Pair<String, String>> newPairs){
      //handle pairs errors
      this.termValue = newPairs;
      return;
   }
   public void addPair(Pair<String, String> newPair) {
      termValue.add(newPair);
   }
   public void deletePair(Pair<String, String> oldPair) {
      Iterator<Pair<String, String>> it = termValue.iterator();
      while(it.hasNext()) {
         Pair pair = it.next();
         if (pair.equals(oldPair)) {
            it.remove();
            return;
         }
      }
      System.out.println("failure");
      //insert error code here
   }
   public void editPair(Pair<String, String> oldPair, Pair<String, String> newPair) {
      deletePair(oldPair);
      addPair(newPair);
      return;
   }

   public static void main(String[] args) {
      Set s = new Set("dong");
      System.out.println(s.getName());
      Pair<String, String> dp = new Pair("doggo", "pupper");
      s.addPair(dp);
      System.out.println(dp);
      s.deletePair(dp);
   }
}