import java.util.Iterator;

public interface ListI<E> extends Iterable<E>{

    public void addFirst(E obj);
    public void addLast(E obj);
    public E removeFirst();
    public E removeLast();
    public E remove(E obj);
    public boolean contains(E obj);
    public E peekFirst();
    public E peekLast();
    public Iterator<E> iterator();

}