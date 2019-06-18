package scale;

import adapter.*;
import model.*;

public class EditOptions extends ProxyAutomobile implements Runnable {
	private Automobile car;
	private boolean debug = false;
	private int operationNumber;
	private int threadNumber;
	private String newData;

	public EditOptions(int operationNumber, int threadNumber, String key, String newData) {
		this.operationNumber = operationNumber;
		this.threadNumber = threadNumber;
		this.newData = newData;
		this.car = super.getValueGivenKey(key);
	}

	@Override
	public void run() {
		switch (this.operationNumber) {
		case 1:
			if (debug) {
				System.out.println("Thread " + this.threadNumber + " Update vechical Name");
				break;
			}
		case 2:
			if (debug) {
				System.out.println("Thread " + this.threadNumber + " Update first OptionSet's name");
				break;
			}
		case 3:
			if (debug) {
				System.out.println("Thread " + this.threadNumber + " Update Base price (unsafe)");
				break;
			}
		}
		operation();
	}

	private void operation() {
		Helper helper = new Helper();

		switch (this.operationNumber) {
		case 1: {
			synchronized (this.car) {
				helper.helper1(car, newData);
			}
			break;
		}
		case 2: {
			// update option set name
			synchronized (this.car) {
				helper.helper2(car, newData);
			}
			break;
		}
		case 3: {
			// increment base price
			// show data corruption
			//synchronized(this.car) {
			helper.helper3(car, newData);
			System.out.println(Thread.currentThread().getName() + " is having the lock");
			System.out.println("Current price when thread " + Thread.currentThread().getName() + " is running");
			System.out.println(this.car.getBasePrice());
			break;
			//}
		}
		default: {
			System.out.println("Invalid Data");
		}
		}
	}
}
