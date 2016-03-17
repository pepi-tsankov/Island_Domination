package Main;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Game extends JFrame implements MouseMotionListener,KeyListener{
	private static final long serialVersionUID = -3427361626363632819L;
	private Player mainp;
	private Robot r;
	private SimpleUniverse u;
	private JFrame frame;
	private Canvas3D canvas=new Canvas3D(SimpleUniverse.getPreferredConfiguration());
	private BranchGroup universe;
	private static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	private static Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	private void setup(){
		frame=this;
		super.addWindowListener(new WindowAdapter() {
	          public void windowClosing(WindowEvent winEvent) {
	        	  frame.getContentPane().setCursor(Cursor.getDefaultCursor());
	        	  System.exit(0);
	          }	
	    });
		try {
			r=new Robot();
		} catch (AWTException e) {}
		super.addMouseMotionListener(this);
		canvas.addMouseMotionListener(this);
		this.addMouseMotionListener(this);
		super.addKeyListener(this);
		canvas.addKeyListener(this);
		this.addKeyListener(this);
		canvas.setSize(400, 400);
		super.getContentPane().add(canvas);
		u=new SimpleUniverse(canvas);
		super.pack();
		super.setVisible(true);
		u.getViewingPlatform().setNominalViewingTransform();
		mainp=new Player(u);
		mainp.move(new Point3d(0,2,0));
		mainp.rotatey(90);
		frame.getContentPane().setCursor(blankCursor);
		universe=new BranchGroup();
		u.addBranchGraph(universe);
	}
	Game(){
		super("Island Domination");
		setup();
		BranchGroup chunksrc=new BranchGroup();
		TransformGroup chunk=new TransformGroup();
		chunk.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		chunk.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		chunksrc.addChild(chunk);
		//chunks.addChild(chunksrc);
	}
	public void addChunk(TransformGroup chunk){
		BranchGroup b=new BranchGroup();
		chunk.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		chunk.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		b.addChild(chunk);
		u.addBranchGraph(b);
	}
	public void removeChunk(TransformGroup chunk){
		universe.removeChild(chunk);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		frame.getContentPane().setCursor(Cursor.getDefaultCursor());
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		frame.getContentPane().setCursor(blankCursor);
		mainp.rotatex(-(e.getXOnScreen() - (this.getLocationOnScreen().x+super.getWidth()/2))*0.05);
		mainp.rotatey((e.getYOnScreen() - (this.getLocationOnScreen().y+super.getHeight()/2))*0.03);
		r.mouseMove(this.getLocationOnScreen().x + super.getWidth()/2, this.getLocationOnScreen().y + super.getHeight()/2);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()=='d'||e.getKeyChar()=='D'){
			mainp.right(0.1);
		}
		if(e.getKeyChar()=='a'||e.getKeyChar()=='A'){
			mainp.right(-0.1);
		}
		if(e.getKeyChar()=='w'||e.getKeyChar()=='W'){
			mainp.forward(0.1);
		}
		if(e.getKeyChar()=='s'||e.getKeyChar()=='S'){
			mainp.forward(-0.1);
		}
		if(e.getKeyChar()=='q'||e.getKeyChar()=='Q'){
			mainp.up(0.1);
		}
		if(e.getKeyChar()=='z'||e.getKeyChar()=='Z'){
			mainp.up(-0.1);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
