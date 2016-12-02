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
  private boolean custom;
  @Transient
  private ArrayList<Pair> termValue;

  public Set() {}
  public Set(String name, boolean cust) {
    this.name = name;
    this.custom = cust;
    this.termValue = new ArrayList<Pair>();
  }

  public String getName() {
    return name;
  }
  public void setName(String newName) {
    this.name = newName;
  }
  
  public boolean getCustom() {
    return custom;
  }
  public void setCustom(boolean cust) {
    this.custom = cust;
  }

  public ArrayList<Pair> getPairs() {
    return termValue;
  }
  public void setPairs(ArrayList<Pair> newPairs){
    this.termValue = newPairs;
  }

  public boolean addPair(Pair newPair) {
    if(!termValue.contains(newPair)){
      termValue.add(newPair);
      return true;
    }
    System.out.println("Failed to add. Pair already exists.");
    return false;
  }
  public boolean deletePair(Pair oldPair) {
    boolean rem = termValue.remove(oldPair);
    if(!rem){
      System.out.println("Failed to remove. Pair does not exist.");
      return false;
    }
     return true;
  }

  public boolean editPair(Pair oldPair, Pair newPair) {
    if(deletePair(oldPair)){
      addPair(newPair);
      return true;
    }
    System.out.println("Failed to add. Old pair was not removed.");
    return false;
  }

  public boolean contains(Pair pair) {
    return termValue.contains(pair);
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

  public Iterator<Pair> randomizeSet() {
    long seed = System.nanoTime();
    Collections.shuffle(termValue, new Random(seed));
    return termValue.iterator();
  }

  public String toString(){
    String ret = "";
    ret += "Set: " + name + "\n";
    for(Pair pair: termValue){
      ret += pair + "\n";
    }
    ret += "End of set.\n";
    return ret;
  }

  public static void main(String[] args){
  
  }
}