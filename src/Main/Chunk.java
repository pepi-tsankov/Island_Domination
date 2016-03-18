package Main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import javax.media.j3d.TransformGroup;

public class Chunk {
	ArrayList<ChunkObject> objs;
	private long x;
	private long y;
	private long z;
	private String location;
	private TransformGroup tr;
	public Chunk(long x,long y,long z,String location,TransformGroup tr){
		this.x=x;
		this.y=y;
		this.z=z;
		objs=new ArrayList<ChunkObject>();
		this.location=location;
		this.tr=tr;
		addObjects();
	}
	public Chunk(long x,long y,long z){
	//Fake Chunk Object used in the Chunks object's hasChunk
		this.x=x;
		this.y=y;
		this.z=z;
	}
	@Override
	public boolean equals(Object o){
	  if(o instanceof Chunk){
		Chunk c = (Chunk) o;
	    return ((this.x==c.x)&&(this.y==c.y)&&(this.z==c.z));
	  }
	  return false;
	}
	@Override
	public int hashCode(){
		return 1;
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
			objs.add(new ChunkObject(location+"/"+x+"/"+y+"/"+z+"/"+objects[i],tr));
		}
	}
	public static boolean CheckChunk(long x,long y,long z,String saveName){
		return new File("./saves/"+saveName+"/"+x+"/"+y+"/"+z+"/").exists();
	}
	public static void CreateChunk(long x,long y,long z,String saveName){
		new File("./saves/"+saveName+"/"+x+"/"+y+"/"+z+"/").mkdirs();
	}
	public void save(){
		for(int i=0;i<objs.size();i++){
			objs.get(i).save();
		}
	}
}
