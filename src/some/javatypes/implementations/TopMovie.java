package some.javatypes.implementations;

import java.util.*;

/*
* List of cd given Cd : id , price , movieId , shopId
* 1 shop has less than or equal to 1 movie cd.
* Support operations:
* 1. Rent : given movieId give top 5 least priced shopId; Rent operation should mark CD unavailable
* 2. Return : return given movieId to the shop
* 3. Rental history: Return the top 5 least-priced movies that have ever been rented till now.

* 1 shop---> 0 or 1 movie of particular type
* Rent: return shop ids
*
*Map<MovieId,TreeMap<Price,Shop>>
* shop id--> list of movies with price
*
*
* Lower price first
If prices are same, smaller movieId
If still same, smaller shopId
* */
public class TopMovie {
    //map movie--->Cd
    Map<Integer, TreeSet<Cd>> availableByMovie = new HashMap<>();

    List<Integer> rentMovie(Integer movieId) {
        TreeSet<Cd> cds = availableByMovie.get(movieId);
        if (cds == null)
            return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        List<Cd> rented = new ArrayList<>();
        for (Cd cd : cds) {
            result.add(cd.id);
            rented.add(cd);
        }
        //remove the cd from the list
        cds.removeAll(rented);
        return result;
    }

    void returnCd(Integer movieId,Integer shopId){
        //add the movie in the shop
        TreeSet<Cd> cds = availableByMovie.get(movieId);
        if(cds==null)
            return;

    }
}

class Cd implements Comparable<Cd> {
    Integer id;
    Double price;
    Integer movieId;
    Integer shopId;

    Cd(Integer id, Double price, Integer movieId, Integer shopId) {
        this.id = id;
        this.price = price;
        this.movieId = movieId;
        this.shopId = shopId;
    }


    @Override
    public int compareTo(Cd o) {
        int cmp = this.price.compareTo(o.price);
        if (cmp != 0) return cmp;

        return this.shopId.compareTo(o.shopId);
    }
}
