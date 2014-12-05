package sw7.cornfield;

/**
 * Created by Morten on 05-12-2014.
 */
public class Pair {
    private Integer Id;
    private String Name;

    public Pair(Integer id, String name) {
        this.Id = id;
        this.Name = name;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
