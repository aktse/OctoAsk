package cs.ualberta.octoaskt12.ES;

// Model of the ElasticSearch Response
public class ESResponse<T> {
	String _index;
	String _type;
	String _id;
	int _version;
	boolean exists;
	T _source;
	double max_score;

	public String getId() {
		return _id;
	}

	public T getSource() {
		return _source;
	}
}
