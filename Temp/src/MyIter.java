import java.util.Iterator;
import java.util.List;

public class MyIter  <T> implements Iterator<T> {

    private int index;
    private List<T> list;

    public MyIter(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }

    @Override
    public void remove() {
        list.remove(index-1);
    }
}
