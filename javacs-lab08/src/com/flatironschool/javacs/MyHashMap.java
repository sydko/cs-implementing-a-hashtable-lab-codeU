/**
 * 
 */
package com.flatironschool.javacs;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 * 
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
	
	// average number of entries per map before we rehash
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);
		
		System.out.println("Put " + key + " in " + maps + " size now " + maps.size());
		
		// check if the number of elements per map exceeds the threshold
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 */
	/**
	 * 
	 */
	protected void rehash() {
       	//(1) collect the entries in the table
       	//I just made a local copy of the old thing bc 
       	// I assume it just gets over written anyway when
       	// makeMaps is called -- so why bother
		List<MyLinearMap<K, V>> oldMaps = maps;
     	
     	//(2) resize the table,
     	Integer k = maps.size();
     	Integer doubled = 2 * k;
     	makeMaps(doubled);

     	System.out.println("Size of maps: " + maps.size());
		System.out.println("Size of Oldmaps: " + oldMaps.size());
 		
 		//(3) and then put the entries back in. 
     	for (MyLinearMap<K, V> oldMap: oldMaps){
     		for (Entry oldEntry: oldMap.getEntries()){
     			put((K)oldEntry.getKey(), (V)oldEntry.getValue());
     		}
     	}
     	
				
		
     	

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
