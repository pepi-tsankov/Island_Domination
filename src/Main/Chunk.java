package Main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Chunk {
	ArrayList<ChunkObject> objs;
	private double x;
	private double y;
	private double z;
	private String location;
	public Chunk(double x,double y,double z,String location){
		this.x=x;
		this.y=y;
		this.z=z;
		objs=new ArrayList<ChunkObject>();
		this.location=location;
	}
	public boolean equals(double x, double y, double z){
		return((this.x==x)&&(this.y==y)&&(this.z==z));
	}
	public void addObjects(){
		File dir = new File(location+"/"+x+"/"+y+"/"+z+"/");
		String[] objects = dir.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		for(int i=0;i<objects.length;i++){
			objs.add(new ChunkObject(location+"/"+x+"/"+y+"/"+z+"/"+objects[i]));
		}
	}
}
