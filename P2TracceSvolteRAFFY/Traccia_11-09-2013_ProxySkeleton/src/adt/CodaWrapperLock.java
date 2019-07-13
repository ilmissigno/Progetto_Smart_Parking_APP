package adt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper{
	
	Lock lock;
	Condition scrivi;
	Condition preleva;
	
	public CodaWrapperLock(ICoda coda){
		super(coda);
		lock = new ReentrantLock();
		scrivi = lock.newCondition();
		preleva = lock.newCondition();
	}
	
	public void add(int id){
		try{
			lock.lock(); //Acquisisco il lock
			while(coda.is_full()){
				scrivi.await();
			}
			coda.add(id);
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			preleva.signal();
			lock.unlock();
		}
	}
	
	public int delete(){
		int val = 0;
		lock.lock();
		try{
			while(coda.is_empty()){
				preleva.await();
			}
			val = coda.delete();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			scrivi.signal();
			lock.unlock();
		}
		return val;
	}
	
}
