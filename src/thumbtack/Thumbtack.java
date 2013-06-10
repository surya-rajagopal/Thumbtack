package thumbtack;

import java.util.*;

public class Thumbtack {
	private static HashMap<String, LinkedList<String>> dbStore = new HashMap<String,LinkedList<String>>();
	private static HashMap<String, LinkedList<String>> unSetData = new HashMap<String,LinkedList<String>>();
	private static HashSet<String> rollBackStack = new HashSet<String>();
	
	private String[] input;
	
	public Thumbtack(String[] input) {
		this.input = input;
	}
	
	public void processBegin() {
		return;
	}

	public void processSet() {
		LinkedList<String> value = new LinkedList<String>();
		if(!dbStore.containsKey(this.input[1])) {
			value.addFirst(input[2]);
			dbStore.put(this.input[1], value);
		}
		else {
			value = dbStore.get(input[1]);
			value.addFirst(input[2]);
			dbStore.put(this.input[1], value);
		}
		rollBackStack.add(input[1]);
	}
	
	public void processGet() {
	    if(dbStore.containsKey(this.input[1])) {
	    	LinkedList<String> value = dbStore.get(input[1]);
	    	if(value.size() != 0) {
	    		System.out.println(value.getFirst());
	    	}
	    	else {
	    		System.out.println("NULL");
	    	}
	    }	
	    else {
	    	System.out.println("NULL");
	    }
	}

	public void processUnSet() {
		if(dbStore.containsKey(this.input[1])) {
			LinkedList<String> value = dbStore.get(input[1]);
			unSetData.put(this.input[1],value);
			dbStore.remove(input[1]);
		}
		else{
			System.out.println("NULL");
		}
	}
	
	public void processNumEqualTo() {
		int count = 0;
		for (Map.Entry<String, LinkedList<String>> entry : dbStore.entrySet()) {
			LinkedList<String> result = entry.getValue();
			String value = result.getFirst();
		    if(value.equals(this.input[1])){
			    count++;
			}
		}
		System.out.println(count);
	}
	
	public void processRollBack() {
		if(rollBackStack.isEmpty()){
			System.out.println("INVALID ROLLBACK");
		}
		Iterator<String> itr = rollBackStack.iterator();
		while(itr.hasNext()){
			String key = itr.next();
			if(dbStore.containsKey(key)){
				LinkedList<String> value = dbStore.get(key);
				value.removeFirst();
				dbStore.put(key, value);
			}
		}
		if(!unSetData.isEmpty()){
			for (Map.Entry<String, LinkedList<String>> entry : unSetData.entrySet()) {
				LinkedList<String> result = entry.getValue();
				dbStore.put(entry.getKey(),result);
			}	
		}
	}
	
	public void processCommit() {
		for (Map.Entry<String, LinkedList<String>> entry : dbStore.entrySet()) {
			String key = entry.getKey();
			LinkedList<String> result = entry.getValue();
			String value = result.getFirst();
			result.clear();
			result.addFirst(value);
			dbStore.put(key, result);
		}
		rollBackStack.clear();
		unSetData.clear();
	}
}
