package studysaurus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Study_Pair")
public class Pair{
    @Id
    @GeneratedValue Integer id;

    private String term;
    private String value;
    private String ownerSet;
     
    public Pair(String trm, String val, String owner){
        this.term = trm;
        this.value = val;
        this.ownerSet = owner;
    }

    public String getTerm() {
        return this.term;
    }
    public void setTerm(String trm) {
        term = trm;
    }

    public String getValue() {
        return this.value;
    }
    public void setValue(String val) {
        value = val;
    }
 
    public String getOwnerSet(){
    	return this.ownerSet;
    }
    public void setOwnerSet(String owner){
    	ownerSet = owner;
    }
    
    public String toString(){
 	   String ret = "";
 	   ret += ownerSet + ": " + term + ", " + value;
 	   return ret;
    }
}