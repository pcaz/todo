/**
 * 
 */
package com.neotec.collection.treemapcontainer;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;

import com.neotec.todo.controler.Task;


/**
 *
 * 
 * Generic class containing records in a tree structure.
 * It uses a TreeMap to containt the keys an a TCCell to store 
 * the records
 * the records are sorted
 * Keys can be multiples.
 * Key must implements Comparable interface.
 *  
 * 
 * 
 * @author pascaz10
 *
 */

public class TreeMapContainer<K,V>  implements Cloneable, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313789019499223872L;

	/**
     * The comparator used to maintain order in the tree, or
     * null if it uses the natural ordering of its keys.
     **
     * Not use
     *
     **
     * @serial
     */
    private final Comparator<K> comparator;
    
    /**
     * The number of entries in the tree
     */
    
    private int size = 0;
    
    	 
    /**
     * The number of structural modifications to the tree.
     */
    private transient int modCount = 0;
    
    /**
     * The TreeMap used by the container
     */
    
    protected NeoTreeMap<K,Cell<V>> TreeStructure;	

    /**
     * Constructs a new, empty tree map, using the natural ordering of its
     * keys.  All keys inserted into the map must implement the {@link
     * Comparable} interface.  Furthermore, all such keys must be
     * <em>mutually comparable</em>: {@code k1.compareTo(k2)} must not throw
     * a {@code ClassCastException} for any keys {@code k1} and
     * {@code k2} in the map.  If the user attempts to put a key into the
     * map that violates this constraint (for example, the user attempts to
     * put a string key into a map whose keys are integers), the
     * {@code put(Object key, Object value)} call will throw a
     * {@code ClassCastException}.
     */
    
    public TreeMapContainer(){
    	comparator=null;
    	Initialize();
    }
    
    /**
     * Constructs a new, empty tree map, ordered according to the given
     * comparator.  All keys inserted into the map must be <em>mutually
     * comparable</em> by the given comparator: {@code comparator.compare(k1,
     * k2)} must not throw a {@code ClassCastException} for any keys
     * {@code k1} and {@code k2} in the map.  If the user attempts to put
     * a key into the map that violates this constraint, the {@code put(Object
     * key, Object value)} call will throw a
     * {@code ClassCastException}.
     *
     * @param comparator the comparator that will be used to order this map.
     *        If {@code null}, the {@linkplain Comparable natural
     *        ordering} of the keys will be used.
     */
    
    public TreeMapContainer(Comparator<K> comparator) {
        this.comparator = comparator;
        Initialize();
    }
    /**
     * Private function; create the structure
     * 
     */
    
    private void Initialize(){
    	TreeStructure = new NeoTreeMap<K,Cell<V>>(comparator);
    }
    
    /**
     * 
     * returrn the comparator used
     * 
     * @return Comparator1
     */

	public Comparator<? super K> comparator() {
		
		return comparator;
	}

	/**
	 * 
	 * Insert a record ine the structure
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key ,V value){
		
		Cell<V> cell;
		
		if((cell=TreeStructure.get(key))== null ){
			cell=new Cell<V>();
			 cell.add(value);
			TreeStructure.put(key, cell);
		}
		else{
			cell.add(value);
        }
		size++;
		modCount++;
		
		return value;
	}
	
/**
 * 
 * return the cell linked with the key
 * 
 * @param key
 * @return
 * @throws NoSuchElementException
 */
	
	public Cell<V> getCell(K key){		
		Cell<V> cell;
		
		cell=TreeStructure.get(key);
		if(cell==null) throw new NoSuchElementException();
		else return cell;
						
	}
/**
 * 
 * remove the record associated with the pair (Key/Value)
 * 	
 * @param key
 * @param value
 */
	public void remove(K key, V value){
		
		Cell<V> cell;
	
		if((cell=TreeStructure.get(key))!=null){
			Iterator<V> it= cell.iterator();
			while(it.hasNext()){
				V vv;
				vv=it.next();
				if(vv.equals(value)){
					cell.remove(vv);
					if(cell.isEmpty()) TreeStructure.remove(key); 
					size--;
					modCount++;
					return;
					}
			  }
			
			}
		throw new NoSuchElementException();
		}
/**
 * 
 * return the number of records of the structure 
 * 	
 * @return int
 */
	public int size(){
		
		return size;
		
	}
/**
 * 
 * return the number of cell of the tree
 * 		
 * @return int
 */
	public int nbCell(){
		return TreeStructure.size();
	}
	
/**
 * return the size of the cell linked to the key parameter
 * 
 * @param key
 * @return int	
 */
	public int cellSize(K key){	
		Cell<V> cell=TreeStructure.get(key);
		return cell.size();
				
		}
/**
 * 
 * return the set of keys used in the TreeMapContainer
 * 
 * @return Set<K>
 */
	public Set<K> keySet(){
		return TreeStructure.keySet();
	}
	
		 
	public java.util.Iterator<V> iterator() {
	        return new PrivateIterator(null);
	}
	
	public java.util.Iterator<V> iterator(K key){
        return new PrivateIterator(key);
    }

	public java.util.Iterator<V> cellIterator(Cell<V> cell){
		return(cell.iterator());
	}
	 /**
     * Base class for TreeMapContainer Iterators
     */
	class PrivateIterator implements Iterator<V>{

	    K currentKey;
	    V currentValue=null;
	    Task nextValue;
	    Cell<V>  currentCell;
	    int expectedModCount;
	    ListIterator<V>	cellIterator;
        ListIterator<K> treeIterator;     
	    
	    
	@SuppressWarnings("unchecked")
	public PrivateIterator(K key){
		
		expectedModCount = modCount;
        if (size==0) return;
        
        treeIterator=TreeStructure.keyIterator();
        Boolean found=false;
        if(key!=null){
        	while (treeIterator.hasNext()){
        		currentKey= treeIterator.next();
        		if( ((Comparable<K>) currentKey).compareTo(key) >= 0) 
        			{
        				found=true;
        				break;      		
        			}
            	}
    		if(! found)  throw new NoSuchElementException();
        }
        else{
            currentKey=treeIterator.next();
        }
        currentCell=(Cell<V>) TreeStructure.get(currentKey);
        currentValue=currentCell.get(0);
        cellIterator=currentCell.listIterator();

    }
	
	public boolean hasNext(){
	  	
		if(expectedModCount!=modCount) throw new ConcurrentModificationException();
    	if(size==0) return false;
    	if(cellIterator.hasNext()) return true;
        return(treeIterator.hasNext());     			
	}
	

	public V next(){
		if(expectedModCount!=modCount) throw new ConcurrentModificationException();
        if (!hasNext())
            throw new NoSuchElementException();
    	if(cellIterator.hasNext())	return cellIterator.next();
    	if (treeIterator.hasNext()){
    		currentKey=treeIterator.next();
    		currentCell=(Cell<V>)TreeStructure.get(currentKey);
    		cellIterator=currentCell.listIterator();
    		currentValue=cellIterator.next();
    		return currentValue;
    	}
    	else throw new NoSuchElementException();
    		
    	}
	
	public boolean hasPrevious(){
		if(expectedModCount!=modCount) throw new ConcurrentModificationException();
		if(cellIterator.hasPrevious()) return true;
		return treeIterator.hasPrevious();
		
	}
	
	public V previous(){
		if(expectedModCount!=modCount) throw new ConcurrentModificationException();
        if (!hasPrevious())
            throw new NoSuchElementException();
    	if(cellIterator.hasPrevious())	return cellIterator.previous();
    	if (treeIterator.hasPrevious()){
    		currentKey=treeIterator.previous();
    		currentCell=(Cell<V>)TreeStructure.get(currentKey);
    		cellIterator=currentCell.listIterator();
    		currentValue=cellIterator.previous();
    		return currentValue;
    	}
    	else throw new NoSuchElementException();
    		
    	}

	public void remove(){
						
		if(expectedModCount!=modCount) throw new ConcurrentModificationException();
		if(cellIterator==null) throw new NoSuchElementException(); 
		cellIterator.remove();
		if(currentCell.isEmpty())
			TreeStructure.remove(currentKey);
		expectedModCount=modCount;
		size--;
	    }
	}
}
	
