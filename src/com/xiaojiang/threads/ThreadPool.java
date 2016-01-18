package com.xiaojiang.threads;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThreadPool {

	private Logger logger = LoggerFactory.getLogger(ThreadPool.class);
	
	private int maxThreads = 10;
	
	private int curThreads;
	
	private List<SpiderThread> threads = new ArrayList<SpiderThread>();
	
	public void addThread(SpiderThread t){
		
		if(curThreads+1 >= maxThreads)
			return ;
		
		synchronized (threads) {
			threads.add(t);
			curThreads += 1;
		}
	}
	
	public void startAll(){
		
		if(threads.size() == 0)
			return ;
		
		for(SpiderThread t : threads){
			t.start();
		}
	}
	
	public void allDestroy(){
		
		for(int i=0;i<threads.size();i++){
			Thread t = threads.get(i);
			if(!t.isInterrupted())
				t.interrupt();
		}
	}
	
	public void allWait(){
		
		for(int i=0;i<threads.size();i++){
			
			logger.error("******Thread allWait "+i+"******");
			
			SpiderThread t = threads.get(i);
			
			if(t.getState().compareTo(Thread.State.WAITING) != 0)
				t.waitTimes();
			
		}
	}
	
	public boolean allHouseSpiderAndCleanIsWait(){
		
		for(int i=1;i<threads.size();i++){
			if(threads.get(i).getState().compareTo(Thread.State.WAITING) !=0 )
				return false;
		}
		return true;
	}
	
	public boolean allHouseSpiderIsWait(){
		
		for(int i=2;i<threads.size();i++){
			
			if(threads.get(i).getState().compareTo(Thread.State.WAITING) != 0)
				return false;
		}
		
		return true;
	}
	
	public boolean allHouseCleanIsWait(){
		
		if(threads.get(1).getState().compareTo(Thread.State.WAITING) != 0)
			return false;

		return true;
	}
	
}
