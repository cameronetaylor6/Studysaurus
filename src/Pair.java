import java.lang.Object;

public class Pair<X, Y> { 
    public final X x; 
    public final Y y; 
    public Pair(X x, Y y) { 
        this.x = x; 
        this.y = y; 
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Pair)){
          System.out.println("Not a pair.");
            return false;
        }

        Pair<X,Y> pairo = (Pair<X,Y>) o;

        //may not work for String
        System.out.println(pairo.x);
        System.out.println(pairo.y);
        System.out.println(this.x);
        System.out.println(this.y);
        return pairo.x.equals(this.x) && pairo.x.equals(this.y);
    }

    /*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }
    */

    public static void main(String[] args){
      Pair<String, String> strPair = new Pair("chicken", "fingers");
      Pair<String, String> str2Pair = new Pair("chicken", "fingers");
      Pair<String, String> strIntPair = new Pair("1", "2");
      Pair<Integer, Integer> intPair = new Pair(1,2);
      Pair<Integer, Integer> int2Pair = new Pair(1,2);
      System.out.println(strPair.equals(str2Pair));
      System.out.println(strIntPair.equals(intPair));
      System.out.println(strPair.equals(strIntPair));
      System.out.println(intPair.equals(int2Pair));
    }
}