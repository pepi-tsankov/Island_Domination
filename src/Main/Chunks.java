package Main;

import java.util.ArrayList;

import javax.media.j3d.TransformGroup;

public class Chunks {
	ArrayList<Chunk> chunks;
	private String gameName;
	private TransformGroup tr;
	Chunks(String s,TransformGroup tr){
		gameName = s;
		this.tr=tr;
		chunks=new ArrayList<Chunk>();
	}
	public Chunk getChunk(long x, long y, long z){
		for(int i=0;i<chunks.size();i++){
			if(chunks.get(i).equals(new Chunk(x,y,z)))return chunks.get(i);
		}
		return null;
	}
	public void importChunk(long x,long y,long z){
		chunks.add(new Chunk(x, y, z, "./saves/"+gameName, tr));
	}
	public boolean hasChunk(long x,long y,long z){
		return chunks.contains(new Chunk(x,y,z));
	}
	public void add(long x,long y,long z,String saveName,long d){
		for(long i=-d;i<d+1;i++){
			for(long j=-d;j<d+1;j++){
				for(long k=-d;k<d+1;k++){
					if(Chunk.CheckChunk(x+i,y+j,z+k,saveName)){
						if(!this.hasChunk(x+i,y+j,z+k)){
							this.importChunk(x+i,y+j,z+k);
						}
					}else{
						Chunk.CreateChunk(x+i,y+j,z+k, saveName);
						this.importChunk(x+i,y+j,z+k);
					}
				}
			}
		}
	}
	public void save(){
		for(int i=0;i<chunks.size();i++){
			chunks.get(i).save();
		}
	}
}
