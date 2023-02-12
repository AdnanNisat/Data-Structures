import java.util.Iterator;

public class Hash<K,V> implements HashI<K,V> {

    class HashElement<K,V> implements Comparable<HashElement<K,V>>{
        K key;
        V value;
        
        public HashElement(K key, V value){
            this.key = key;
            this.value = value;
        }

        public int compareTo(HashElement<K,V> o){
            return (((Comparable<K>)this.key).compareTo(o.key));
        }
    }

    int numElements, tableSize;
    double maxLoadFactor;
    LinkedList<HashElement<K,V>>[] harray;

    public Hash(int tableSize){   //HashTable Constructor
        this.tableSize = tableSize;
        harray = (LinkedList<HashElement<K,V>>[]) new LinkedList[tableSize];
        
        for(int i=0; i < tableSize; i++){
            harray[i] = new LinkedList<HashElement<K,V>>();
        }

        maxLoadFactor = 0.75;
        numElements = 0;
    }

    public boolean add(K key, V value){
        double loadFactor = numElements / tableSize ;

        if(loadFactor > maxLoadFactor){
            resize(2 * tableSize);
        }

        HashElement<K,V> he = new HashElement(key, value);

        int hashval = key.hashCode();
        hashval = hashval & 0x7FFFFFFF;
        hashval = hashval % tableSize;

        harray[hashval].addLast(he);
        numElements++;
        return true;
    }

    public boolean remove(K key){
        int hashval = key.hashCode();
        hashval = hashval & 0x7FFFFFFF;
        hashval = hashval % tableSize;

        harray[hashval].removeLast();
        numElements--;
        return true;
    }

    public V getValue(K key){
        int hashval = key.hashCode();
        hashval = hashval & 0x7FFFFFFF;
        hashval = hashval % tableSize;

        for(HashElement<K,V> he : harray[hashval]){
            if(((Comparable<K>)key).compareTo(he.key) == 0){
                return he.value;
            }
        }
        return null;
    }

    class IteratorHelper<T> implements Iterator<T>{

        T[] keys;
        int position;

        public IteratorHelper(){
            keys = (T[]) new Object[numElements];
            int position = 0;
            for(int i=0; i < tableSize; i++){
                LinkedList<HashElement<K,V>> list = harray[i];
                for(HashElement<K,V> h : list){
                    keys[position++] = (T) h.key;
                }
            }
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < keys.length;
        }

        @Override
        public T next() {
            if(!hasNext()) return null;
            return keys[position++];
        }      
    }
    
    public Iterator<K> iterator(){
        return new IteratorHelper();
     }
    
     public void resize(int newSize){
        LinkedList<HashElement<K,V>>[] new_array = 
        (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];

        for(int i=0; i < newSize; i++){
            new_array[i] = new LinkedList<HashElement<K,V>>();
        }

        for(K key : this){
            V val = getValue(key);
            HashElement<K,V> he = new HashElement<K,V>(key, val);
            int hashVal = (key.hashCode() & 0x7FFFFFFF) % newSize;
            new_array[hashVal].addLast(he);
        }

        harray = new_array;
        tableSize = newSize;
     }
}