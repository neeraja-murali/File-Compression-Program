/*Author: Neeraja Murali Dharan 
 *File Name: Dictionary.java
 *Last Modified: 21 October 2015
 *Description: Class implements a dictionary using a hash table with seperate chaining 
 *(implemented using an array of Linked List DictEntry objects) 
 */
import java.util.LinkedList; 
public class Dictionary implements DictionaryADT {
	
	private LinkedList<DictEntry>[] hash_table;//array of Linked List containing DictEntry objects
	private int num_elements;//keeps track of number of entries in the dictionary
	
	@SuppressWarnings("unchecked")
	//constructor method returns a Dictionary object of a specified size sent in as a parameter
	public Dictionary (int size){
		hash_table=new LinkedList[size];
		num_elements=0; 
	}
	
	/*Inserts the given DictEntry in the dictionary
	 * throws a DictionaryException if DictEntry pair is already
	 * in the dictionary
	 */
	public int insert(DictEntry pair)throws DictionaryException{
		//find the position to insert the entry using the hash function
		int position=hashFunction(pair.getKey());
		
		//if the linked list at the position in the dictionary contains elements (collision)
		if (hash_table[position]!=null){
			//checks if entry is already in the dictionary
			if (find(pair.getKey())!=null){
				throw new DictionaryException("Key is already in the hash table");
			} 
			
			//if entry is not in dictionary, adds it to the dictionary to the end of the Linked List at position specified by key
			hash_table[position].add(pair);
			num_elements++; 
			return 1;//returns 1 to indicate collision
		}
		
		//if there is no Linked List at the position, creates a new Linked List with element as the first entry
		//No collision
		else{
			LinkedList<DictEntry>list=new LinkedList<DictEntry>();
			list.addFirst(pair);
			hash_table[position]=list;
			num_elements++;
			return 0;//returns 0 to indicate no collision 
		}
	}
	
	
	/*Removes the entry with the given key from the dictionary
	 * throws a DictionaryException if the key cannot be found in the dictionary
	 */
	public void remove(String key)throws DictionaryException{
		//finds the position of the key in the dictionary using the hash function
		int position=hashFunction(key);
		
		//if the position is not null, checks if key is in the Linked List 
		if (hash_table[position]!=null){
			//if the key is in the LinkedList, searches through the Linked List at position
			if (find(key)!=null){
				for (int i=0; i<hash_table[position].size();i++){
					//If entry is found at position, entry is removed 
					if (hash_table[position].get(i).getKey()==key){
						hash_table[position].remove(i);
						num_elements--;
					}
				}
			}
		}
		else{
			throw new DictionaryException("Element is not in the hash table"); 
		}
		
	} 
	
	//Returns the number of DictEntry objects stored in the dictionary
	public int numElements(){ 
		return num_elements;
	}
	
	/*Method returns the DictEntry object stored in the dictionary with the given key
	 * or null if there is no entry in the dictionary with the given key
	 */
	
	public DictEntry find(String key){
		//finds the position of key in the dictionary with the hash Function
		int position=hashFunction(key); 
		
		//Check if there is a Linked List of DictEntry at position in dictionary
		if (hash_table[position]==null){
			return null;
		}
		
		//Goes through all the entries in the LinkedList to find entry
		for (int i=0;i<hash_table[position].size();i++){
			if (key.compareTo(hash_table[position].get(i).getKey())==0)
				return hash_table[position].get(i);//returns entry if found
			}
		
		return null;//returns null if entry not found 
			}
	//private method that returns the position of a key in the dictionary calculated using a hash function
	private int hashFunction(String key){
		 
			int hash=0;
			for (int i=1; i<key.length();i++){
				hash=key.charAt(i)+(31*hash);//hash function calculation using the key
				//the hash valus is value of each character in key added up with 31 times the hash value of previous characters
			}
			return (Math.abs(hash) % hash_table.length);
	}


	


}

