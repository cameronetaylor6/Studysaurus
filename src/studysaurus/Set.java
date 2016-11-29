package studysaurus;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Study_Set")
public class Set{
   @Id
   @GeneratedValue Integer id;
   
   private String name;
   @Transient
   private ArrayList<Pair> termValue;  

   public Set() {}
   public Set(String name) {
      this.name = name;
      this.termValue = new ArrayList<Pair>();
   }
   public String getName() {
      return name;
   }
   public void setName(String newName) {
      //handle name errors
      this.name = newName;
   }
   
   public ArrayList<Pair> getPairs() {
      return termValue;
   }
   public void setPairs(ArrayList<Pair> newPairs){
      //handle pairs errors
      this.termValue = newPairs;
   }
   
   
   public void addPair(Pair newPair) {
      termValue.add(newPair);
   }
   public void deletePair(Pair oldPair) {
      Iterator<Pair> it = termValue.iterator();
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
   public void editPair(Pair oldPair, Pair newPair) {
      deletePair(oldPair);
      addPair(newPair);
      return;
   }
   
   public String toString(){
	   String ret = "";
	   ret += "Set: " + name + "\n";
	   Iterator<Pair> it = this.termValue.iterator();
	   while(it.hasNext()) {
		   Pair pair = it.next();
		   ret += pair + "\n";
	   }
	   ret += "End of set.\n";
	   return ret;
   }
}