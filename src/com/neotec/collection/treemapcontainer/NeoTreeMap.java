package com.neotec.collection.treemapcontainer;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * NeoTreeMap
 * 
 * extends TreeMap with ascending end descending iterator (see ListIterator) 
 *  
 * @author pascaz10
 *
 * @param <K>
 * @param <V>
 */
public class NeoTreeMap<K,V> extends TreeMap<K,V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7759562493902643189L;
	
	int modCount=0;
	
/**
 * 
 */
	public NeoTreeMap(){
		super();
	}
	
	public NeoTreeMap(Comparator<K> comparator){
		super(comparator);
	}
	
	@Override
	public V put(K key, V value){
		modCount++;
		return super.put(key,value);
		
	}
	@Override
	public V remove(Object key){
		modCount++;
		return super.remove(key);
	}
	
	@Override
	public void clear(){
		modCount++;
		super.clear();
	}
	
	@Override
	public void putAll(Map<? extends K,? extends V> map){
		modCount++;
		super.putAll(map);
	}
	
	private V privateGet(K key){
		return super.get(key);
	}
	
	private void privateRemove(K key){
		super.remove(key);
	}
	
	public ListIterator<V> valueIterator(){
		return new PrivateValueIterator(null);
	}
	
	public ListIterator<K> keyIterator(){
		return new PrivateKeyIterator(null);
	}

	

class PrivateKeyIterator implements ListIterator<K> {
		
		int expectedModCount;

		K[]	arrayKey;
		int currentIndex=0;
		
		
		
		@SuppressWarnings("unchecked")
		public PrivateKeyIterator(K first) {
	
			expectedModCount=modCount;
			arrayKey=(K[]) keySet().toArray();
			
			boolean found=false;
			if(first==null) currentIndex=0;
			else{
				for(int i=0; i<arrayKey.length;i++){
					if(((Comparable<K>) arrayKey[i]).compareTo(first)>=0){
						found=true;
						break;
					}
				}
			if(!found) throw new NoSuchElementException();				
			}					
		}


		@Override
		public boolean hasNext() {
			return(currentIndex<arrayKey.length);
		}

		@Override
		public K next() {
			K result;
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			if(currentIndex>arrayKey.length) throw new NoSuchElementException();
			result=arrayKey[currentIndex];
			currentIndex++;
			return result;
		}

		@Override
		public boolean hasPrevious() {
			return((arrayKey.length!=0) && (currentIndex>0));
		}

		@Override
		public K previous() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			if(currentIndex <= 0)throw new NoSuchElementException();
			return  arrayKey[--currentIndex];
		}

		@Override
		public int nextIndex() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			return currentIndex;
		}

		@Override
		public int previousIndex() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			return (currentIndex<= 0 ? 0:currentIndex-1);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void remove() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			if(currentIndex <= 0)throw new NoSuchElementException();
			privateRemove(arrayKey[currentIndex]);
			arrayKey=(K[]) keySet().toArray();
			
		}



		@Override
		public void set(K e) {
			throw new UnsupportedOperationException();			
		}

		@Override
		public void add(K e) {
			throw new UnsupportedOperationException();
			
		}
	
	}

	
	
	

class PrivateValueIterator implements ListIterator<V> {
		
		int expectedModCount;

		K[]	arrayKey;
		int currentIndex=0;
		
		
		@SuppressWarnings("unchecked")
		public PrivateValueIterator(K first) {
	
			expectedModCount=modCount;
			arrayKey=(K[]) keySet().toArray();
			
			boolean found=false;
			if(first==null) currentIndex=0;
			else{
				for(int i=0; i<arrayKey.length;i++){
					if(((Comparable<K>) arrayKey[i]).compareTo(first)>=0){
						found=true;
						break;
					}
				}
			if(!found) throw new NoSuchElementException();				
			}					
		}


		@Override
		public boolean hasNext() {
			return(currentIndex<arrayKey.length);
		}

		@Override
		public V next() {
			K result;
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			if(currentIndex>arrayKey.length) throw new NoSuchElementException();
			result=arrayKey[currentIndex];
			currentIndex++;
			return (V) privateGet(result);
		}

		@Override
		public boolean hasPrevious() {
			return((arrayKey.length!=0) && (currentIndex>0));
		}

		@Override
		public V previous() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			if(currentIndex <= 0)throw new NoSuchElementException();
			return  (V) privateGet(arrayKey[--currentIndex]);
		}

		@Override
		public int nextIndex() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			return currentIndex;
		}

		@Override
		public int previousIndex() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			return (currentIndex<= 0 ? 0:currentIndex-1);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void remove() {
			if(expectedModCount!=modCount) throw new ConcurrentModificationException();
			if(currentIndex <= 0)throw new NoSuchElementException();
			privateRemove(arrayKey[currentIndex]);
			arrayKey=(K[]) keySet().toArray();
		}

		@Override
		public void set(V e) {
			throw new UnsupportedOperationException();			
		}

		@Override
		public void add(V e) {
			throw new UnsupportedOperationException();
			
		}
	}
	
}
