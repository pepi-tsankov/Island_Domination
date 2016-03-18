package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;

import J3DBool.BooleanModeller;
import J3DBool.Solid;

public class ChunkObject {
	private Solid s;
	private Shape3D obj;
	private BranchGroup mebr;
	private TransformGroup metr;
	private TransformGroup tr;
	private String location;
	public ChunkObject(String str,TransformGroup tr){
		location=str;
		s=new Solid(new File(str+"/obj.solid"),new Color3f(1,0,0));
		obj=new Shape3D(s.getGeometry());
		this.tr=tr;
		metr=new TransformGroup();
		mebr=new BranchGroup();
		metr.addChild(obj);
		mebr.addChild(metr);
		mebr.setCapability(BranchGroup.ALLOW_DETACH);
		tr.addChild(mebr);
	}
	public void remove(Solid r){
		BooleanModeller m=new BooleanModeller(s,r);
		s=m.getDifference();
		tr.removeChild(mebr);
		obj=new Shape3D(s.getGeometry());
		tr.addChild(mebr);
	}
	public void save(){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(location+"/obj.solid", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		NumberFormat f = new DecimalFormat("0.00000000000000E0000");
		writer.println(""+s.getVertices().length);
		for(int i=0;i<s.getVertices().length;i++){
			writer.println(""+i+" "+f.format(s.getVertices()[i].x)+" "+f.format(s.getVertices()[i].y)+" "+f.format(s.getVertices()[i].z));
		}
		writer.println("");
		writer.println(""+s.getIndices().length/3);
		for(int i=0;i<s.getIndices().length;i+=3){
			writer.println(""+i/3+" "+s.getIndices()[i]+" "+s.getIndices()[i+1]+" "+s.getIndices()[i+2]);
		}
		writer.close();
	}
}
