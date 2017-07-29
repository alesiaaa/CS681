package edu.umb.cs.cs681;

import java.nio.file.Path;

public abstract class FileCache {
	
	public abstract String fetch (Path path);
	protected abstract String cacheFile (Path path); // cannot be private
	protected abstract String replace (Path path);
    
}
