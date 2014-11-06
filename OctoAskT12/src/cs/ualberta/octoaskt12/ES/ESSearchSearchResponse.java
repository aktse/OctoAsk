package cs.ualberta.octoaskt12.ES;

import java.util.ArrayList;
import java.util.Collection;

public class ESSearchSearchResponse<T> {
    int took;
    boolean timed_out;
    transient Object _shards;
    ESHits<T> hits;
    boolean exists;    
    public Collection<ESResponse<T>> getHits() {
        return hits.getHits();        
    }
    public Collection<T> getSources() {
        Collection<T> out = new ArrayList<T>();
        for (ESResponse<T> essrt : getHits()) {
            out.add( essrt.getSource() );
        }
        return out;
    }
    public String toString() {
        return (super.toString() + ":" + took + "," + _shards + "," + exists + ","  + hits);     
    }
}
