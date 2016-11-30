package studysaurus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

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
   private String type;
   @Transient
   private ArrayList<Pair> termValue;  

   public Set() {}
   public Set(String name, String type) {
      this.name = name;
      this.type = type;
      this.termValue = new ArrayList<Pair>();
   }
   public String getName() {
      return name;
   }
   public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
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
   
   public Iterator<Pair> randomizeSet() {
	   long seed = System.nanoTime();
	   Collections.shuffle(termValue, new Random(seed));
	   return termValue.iterator();
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
   
   public ArrayList<String> getTerms(){
	   ArrayList<String> terms = new ArrayList<String>();
	   for(Pair pair: termValue){
		   terms.add(pair.getTerm());
	   }
	   return terms;
   }
   public ArrayList<String> getValues(){
	   ArrayList<String> values = new ArrayList<String>();
	   for(Pair pair: termValue){
		   values.add(pair.getValue());
	   }
	   return values;
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