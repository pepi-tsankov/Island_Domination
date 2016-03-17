package maths;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import J3DBool.Solid;
public class Math3d {
	public static double SignedVolumeOfTriangle(Point3d ps, Point3d ps2, Point3d ps3) {
	    double v321 = ps3.x*ps2.y*ps.z;
	    double v231 = ps2.x*ps3.y*ps.z;
	    double v312 = ps3.x*ps.y*ps2.z;
	    double v132 = ps.x*ps3.y*ps2.z;
	    double v213 = ps2.x*ps.y*ps3.z;
	    double v123 = ps.x*ps2.y*ps3.z;
	    return (1.0f/6.0f)*(-v321 + v231 + v312 - v132 - v213 + v123);
	}
	public static double VolumeOfMesh(Solid mesh) {
		Point3d[] ps=mesh.getVertices();
		int[] inds=mesh.getIndices();
		double volume=0;
		for(int i=0;i<inds.length;i+=3){
			volume+=SignedVolumeOfTriangle(ps[inds[i]],ps[inds[i+1]],ps[inds[i+2]]);
		}
	    return Math.abs(volume);
	}
	public static Vector3d rotateZ(Vector3d vec,double angle){
		angle=Math.toRadians(angle);
		Vector3d result=new Vector3d(vec.x*Math.cos(angle)-vec.y*Math.sin(angle),vec.x*Math.sin(angle)+vec.y*Math.cos(angle),vec.z);
		result.normalize();
		return result;
	}
	public static Vector3d rotateX(Vector3d vec,double angle){
		angle=Math.toRadians(angle);
		Vector3d result=new Vector3d(vec.x,vec.y*Math.cos(angle)-vec.z*Math.sin(angle),vec.y*Math.sin(angle)+vec.z*Math.cos(angle));
		result.normalize();
		return result;
	}
	public static Vector3d rotateY(Vector3d vec,double angle){
		angle=Math.toRadians(angle);
		Vector3d result=new Vector3d(vec.x*Math.cos(angle)+vec.z*Math.sin(angle),vec.y,vec.z*Math.cos(angle)-vec.x*Math.sin(angle));
		result.normalize();
		return result;
	}
}
