package model;

import java.util.*;

public class AutoLinkedHashMap<T extends Automobile> {
    // properties
    private T auto;
    private LinkedHashMap<String, Automobile> autoLinkedHashMap = null;

    // constructor
    public AutoLinkedHashMap() {
        this.autoLinkedHashMap = new LinkedHashMap<String, Automobile>();
    }

    // getters
    public T getAuto() {
        return this.auto;
    }

    // setters
    public void setAuto(T auto) {
        this.auto = auto;
    }

    // generate a key
    private String generateKey(T autoObject) {
        StringBuffer buff = new StringBuffer();
        buff.append(autoObject.getMake());
        buff.append(autoObject.getModel()).append(" ");
        buff.append(autoObject.getYear());
        return buff.toString();
    }

    // insert
    public void insert(T newAutoObject) {
        // generate a create for the object that the user wants to insert
        String key = generateKey(newAutoObject);
        // insert the autoObject along with that newly created key into the
        // LinkeddHashMap
        this.autoLinkedHashMap.put(key, newAutoObject);
    }

    // delete
    public void delete(String key) {
        this.autoLinkedHashMap.remove(key);
    }

    // display
    public void display() {
        Set keySet = this.autoLinkedHashMap.keySet();
        Collection collectionSet = this.autoLinkedHashMap.values();

        Iterator keyIterator = keySet.iterator();
        Iterator collectionIterator = collectionSet.iterator();

        while (keyIterator.hasNext() && collectionIterator.hasNext()) {
            System.out.println("key = " + keyIterator.next());
            Automobile car = (Automobile)(collectionIterator.next());   // instead of displaying the whole Automobile Object
            System.out.println("value = " + car.getName());             // I made it displays just the name of that Automobile 
            System.out.println();
        }
    }
    // returns all the keys inside the linkedHashMap
    public String getAllModels() {
    	StringBuffer sbuff = new StringBuffer();
    	Set keySet = this.autoLinkedHashMap.keySet();
    	Iterator keyIterator = keySet.iterator();

        while (keyIterator.hasNext()) {
        	sbuff.append(keyIterator.next()).append('\n');
        }
        return sbuff.toString();
    }
    // print value given key
    public void printValueGivenKey(String key){
        System.out.println(this.autoLinkedHashMap.get(key));
    }
    public Automobile getValueGivenKey(String key) {
    	return this.autoLinkedHashMap.get(key);
    }
}