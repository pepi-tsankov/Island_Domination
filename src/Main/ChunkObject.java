package Main;

import java.io.File;

import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;

import J3DBool.Solid;

public class ChunkObject {
	private Solid s;
	private Shape3D obj;
	public ChunkObject(String str){
		s=new Solid(new File(str+"obj.solid"),new Color3f(0,0,0));
		obj=new Shape3D(s.getGeometry());
	}
	public Shape3D getObject(){
		return obj;
	}
}
