package cs.ualberta.octoaskt12.ES;

import java.util.Collection;

public class ESHits<T> {

	int total;
	double max_score;
	Collection<ESResponse<T>> hits;

	public Collection<ESResponse<T>> getHits() {
		return hits;
	}

	public String toString() {
		return (super.toString() + "," + total + "," + max_score + "," + hits);
	}

}
