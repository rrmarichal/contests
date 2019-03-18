import static java.lang.System.out;

import java.util.concurrent.Executors;

public class TestReentrantLock {

	public static void main(String[] args) throws InterruptedException {
		final ReentrantLock l = new ReentrantLock();

		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				try {
					l.lock(1);
					Thread.sleep(5000);
					l.unlock(1);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread.sleep(1000);
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				try {
					l.lock(2);
					l.unlock(2);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		out.println("runing...");
	}

	public static class ReentrantLock {
	    private int count = 0;
	    private boolean isLocked = false;
	    private Thread lockedByThread = null;
	    
	    public synchronized void lock(int id) throws InterruptedException{
	    	out.println(String.format("%d locking...", id));
	    	while (isLocked && Thread.currentThread() != lockedByThread) {
	            this.wait();
	        }
	        isLocked = true;
	        lockedByThread = Thread.currentThread();
	        count++;
	        out.println(String.format("%d locked...", id));
	    }

	    public synchronized void unlock(int id){
	        out.println(String.format("%d unlocking...", id));
	    	if (!isLocked || lockedByThread != Thread.currentThread()) {
	            return;
	        }
	        count--;
	        if(count == 0) {
	            isLocked = false;
	            this.notify();
	        }
	        out.println(String.format("%d unlocked...", id));
	   }
	}
}
