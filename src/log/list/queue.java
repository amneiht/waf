package log.list;

import java.util.LinkedList;
import java.util.List;

public class queue<E> {
	// List<E> arr = new ArrayList<E>(10000);
	List<E> arr = new LinkedList<E>();
	int size = 0;

	public void add(E d) {
		arr.add(d);
		size++;
	}

	synchronized public E get() throws Exception 
	{
		return arr.remove(0);
	}

	public synchronized boolean Nrong() {
		return size > 0;
	}
}
