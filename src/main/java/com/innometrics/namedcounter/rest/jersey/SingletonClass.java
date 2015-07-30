package com.innometrics.namedcounter.rest.jersey;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;

public class SingletonClass {
	/*
	 * If instance is not made volatile, another thread can see a half
	 * initialized instance of singletonclass
	 */
	private volatile static SingletonClass _instance = null;
	/*
	 * variable is volatile so that multiple threads when access do not see the
	 * locally cached stale value. guarantees that threads see the recent value
	 * volatile static to force the thread to see global value each time
	 * volatile is not substitute for synchronization hence atomic integer
	 * 
	 * atomicInteger here for thread safety else we might need to use
	 * synchronized while incrementing the value
	 */
	private volatile static AtomicInteger atomicInteger = null;
	/*
	 * concurrentHashmap for thread safety volatile so that if one thread alters
	 * the value, other threads will see it
	 */
	private volatile ConcurrentHashMap<String, AtomicInteger> namedCounterMap = null;

	private SingletonClass() {
		/*
		 * This private constructor also to prevent creating another instance of
		 * class
		 */
		atomicInteger = new AtomicInteger();
		namedCounterMap = new ConcurrentHashMap<String, AtomicInteger>();
	}

	/*
	 * Singleton pattern gives the flexiblity an Object, while static class
	 * provide static methods.
	 */

	/*
	 * Using lazy loading here so that singleton instance is initialized when
	 * the below method is called for first time Also, The below implementation
	 * is double checked locking of singleton This is to minimize the cost of
	 * synchronization and improve performance by locking only the critical
	 * section of the code
	 */
	public static SingletonClass getInstance() {
		if (_instance == null) {
			synchronized (SingletonClass.class) {
				{
					if (_instance == null) {
						_instance = new SingletonClass();
					}

				}
			}
		}
		return _instance;
	}

	public int increment() {
		return atomicInteger.getAndIncrement();
	}

	public int getValue() {
		return atomicInteger.get();
	}

	public String getValueFromMap(String namedCounter) {
		// TODO Auto-generated method stub
		String response = String.valueOf(namedCounterMap.get(namedCounter));
		
		if (response.equals("null"))
			return "NA";
		else
			return response;

	}

	public String incrementValueInMap(String namedCounter) {
		AtomicInteger response = namedCounterMap.get(namedCounter);
		if (response==null)
			return "NA";
		else
			return String.valueOf(response.incrementAndGet());

	}

	public String addNamedCounter(String namedCounter) {
		namedCounterMap.put(namedCounter, new AtomicInteger());
		return namedCounter + " Successfully added";
	}

	public String getAllNamedParamaters() {
		String s = "";
		JSONObject js = new JSONObject();
		String d = new JSONObject(namedCounterMap).toString();
		for (ConcurrentHashMap.Entry<String, AtomicInteger> entry : namedCounterMap.entrySet())
			js.put(entry.getKey(), entry.getValue());
		return js.toString();
	}
}