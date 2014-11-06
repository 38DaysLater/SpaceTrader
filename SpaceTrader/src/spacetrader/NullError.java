package spacetrader;
import java.io.Serializable;

/**
 *  This class represents an error for errors
 * @author aazaibi
 */

public class NullError implements Serializable {
    private String s;

    NullError(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }


}
