package Main;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.universe.SimpleUniverse;

import maths.Math3d;

public class Player{
	private Vector3d up;
	private Vector3d front;
	private Point3d player;
	private double anglex=0;
	private double angley=0;
	private Transform3D view;
	private SimpleUniverse universe=null;
	public Player(){
		up= new Vector3d(0,1,0);
		front=new Vector3d(0,0,1);
		player=new Point3d(0,0,0);
	}
	public Player(Point3d player){
		up= new Vector3d(0,1,0);
		front=new Vector3d(0,0,1);
		this.player=player;
	}
	public Player(SimpleUniverse u){
		up= new Vector3d(0,1,0);
		front=new Vector3d(0,0,1);
		this.player=new Point3d(0,0,0);
		universe=u;
	}
	public Transform3D getView(){
		view = new Transform3D();
		Point3d eye=new Point3d();
		Vector3d eyev=Math3d.rotateY(Math3d.rotateX(front,angley),anglex);
		eyev.normalize();
		eye.x=player.x+eyev.x;
		eye.y=player.y+eyev.y;
		eye.z=player.z+eyev.z;
		view.lookAt(player,eye,Math3d.rotateY(Math3d.rotateX(up,angley),anglex));
		view.invert();
		return view;
	}
	public void setView(){
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(this.getView());
	}
	public void forward(double speed){
		Vector3d eyev=Math3d.rotateY(front,anglex);
		eyev.normalize();
		player.x+=eyev.x*speed;
		player.y+=eyev.y*speed;
		player.z+=eyev.z*speed;
		setView();
	}
	public void right(double speed){
		Vector3d eyev=Math3d.rotateY(front,anglex-90);
		eyev.normalize();
		player.x+=eyev.x*speed;
		player.y+=eyev.y*speed;
		player.z+=eyev.z*speed;
		setView();
	}
	public void up(double speed){
		player.y+=speed;
		setView();
	}
	public Point3d getPosition(){
		return player;
	}
	public void rotatex(double angle){
		anglex+=angle;
		while(anglex>360){
			anglex-=360;
		}
		while(anglex<0){
			anglex+=360;
		}
		setView();
	}
	public void rotatey(double angle){
		angley+=angle;
		if(angley>90){
			angley=90;
		}
		if(angley<-90){
			angley=-90;
		}
		setView();
	}
	public void move(Point3d pos){
		player=pos;
		setView();
	}
	public double getX(){
		return player.x;
	}
	public double getY(){
		return player.y;
	}
	public double getZ(){
		return player.z;
	}
}
