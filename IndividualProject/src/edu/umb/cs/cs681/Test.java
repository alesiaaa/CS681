package edu.umb.cs.cs681;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main (String args[]){
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
		
		Directory system = new Directory(fileSystem.root, "system");
		File a = new File(system, "a.txt", 10);
		File b = new File(system, "b.txt", 10);
		File c = new File(system, "c.txt", 10);
		
		
		Directory home = new Directory(fileSystem.root, "home");
		File d = new File(home, "d", 10);
		Link x = new Link(home, "x.jpg");
		x.setElement(system);
		
		Directory pictures = new Directory(home, "pictures");
		File e = new File(pictures, "e.jpg", 10);
		File f = new File(pictures, "f.jpg", 10);
		Link y = new Link(pictures, "y.jpg", e);
		Link x2 = new Link(pictures, "x2.jpg", x);
		Link x3 = new Link(pictures, "x3.jpg", x2);
		
		Directory newPics = new Directory(pictures, "new pics");
		File g = new File(pictures, "g.jpg", 10 );
		File h = new File(pictures, "h.jpg", 10);
		
		Directory moreNewPics = new Directory(pictures, "more new pics");
		File i = new File(pictures, "i.jpg", 10);
		File j = new File(pictures, "j.jpg", 10);
		
		Directory evenMoreNewPics = new Directory(pictures, "even more new pics");
		File k = new File(pictures, "k.jpg", 10);
		File l = new File(pictures, "l.jpg", 10);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(home);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);
		pictures.appendChild(x2);
		pictures.appendChild(x3);
		
		newPics.appendChild(g);
		newPics.appendChild(h);
		
		moreNewPics.appendChild(i);
		moreNewPics.appendChild(j);
		
		evenMoreNewPics.appendChild(k);
		evenMoreNewPics.appendChild(l);
		
		home.appendChild(d);
		home.appendChild(pictures);
		home.appendChild(x);
		
		pictures.appendChild(newPics);
		newPics.appendChild(moreNewPics);
		moreNewPics.appendChild(evenMoreNewPics);
		
		fileSystem.showAllElements();
		
		System.out.println("\n\n");
		
		FIFOFileCache fifo1 = new FIFOFileCache();
		FIFOFileCache fifo2 = new FIFOFileCache();
		FIFOFileCache fifo3 = new FIFOFileCache();
		
		System.out.println("\n");
		
		System.out.println("Starting crawlers and indexers... \n");
		
		ElementQueue fq1 = new ElementQueue ();
		ElementQueue fq2 = new ElementQueue ();
		ElementQueue fq3 = new ElementQueue ();
		
		
		FileCrawler fileCrawler1 = new FileCrawler(fq1, fileSystem.root);
        FileIndexer fileIndex1 = new FileIndexer(fq1,fifo1);
        Thread crawlerThread1 = new Thread(fileCrawler1, "Crawler Thread #1,");
        Thread indexThread1 = new Thread(fileIndex1, "Indexer Thread #1,");
        
        FileCrawler fileCrawler2 = new FileCrawler(fq2, evenMoreNewPics);
        FileIndexer fileIndex2 = new FileIndexer(fq2, fifo2);
        Thread crawlerThread2 = new Thread(fileCrawler2, "Crawler Thread #2,");
        Thread indexThread2 = new Thread(fileIndex2, "Indexer Thread #2,");
        
        FileCrawler fileCrawler3 = new FileCrawler(fq3, moreNewPics);
        FileIndexer fileIndex3 = new FileIndexer(fq3, fifo3);
        Thread crawlerThread3 = new Thread(fileCrawler3, "Crawler Thread #3,");
        Thread indexThread3 = new Thread(fileIndex3, "Indexer Thread #3,");
        
        ExecutorService executor = Executors.newWorkStealingPool();

        executor.submit(crawlerThread1);
        executor.submit(indexThread1);
        executor.submit(crawlerThread2);
        executor.submit(indexThread2);
        executor.submit(crawlerThread3);
        executor.submit(indexThread3);
       
       
        try {
        	crawlerThread1.join();
        	indexThread1.join();
        	crawlerThread2.join();
        	indexThread2.join();
        	crawlerThread3.join();
        	indexThread3.join();
        } catch (Exception exept){
        	// do nothing
        } finally {
        	
        	executor.shutdown();
        	
        	try {
				executor.awaitTermination(60, TimeUnit.SECONDS);
			} catch (InterruptedException e1) {
				executor.shutdownNow();
			}
        }
        	
       
		
	}
}
