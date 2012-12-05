package psywerx.tanks;

import javax.media.opengl.GLCapabilities
import javax.media.opengl.GLProfile
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.util.Animator
import javax.media.opengl.GLEventListener
import javax.media.opengl.GLAutoDrawable
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

object Main extends App with GLEventListener {
  var WIDTH = 600
  var HEIGHT = 600
  
  val caps = new GLCapabilities(GLProfile.get(GLProfile.GL2ES2))
  caps.setBackgroundOpaque(false)
  
  val glWindow = GLWindow.create(caps)
  glWindow.setSize(WIDTH, HEIGHT)
  glWindow.setTitle("Tanks")
  glWindow.setUndecorated(false)
  glWindow.setPointerVisible(true)
  glWindow.setVisible(true)
  glWindow.addGLEventListener(Main)
  glWindow.addKeyListener(new KeyHandler());
  
  val animator = new Animator(glWindow);
  animator.add(glWindow);
  animator.start();
  
  override def init(drawable:GLAutoDrawable) = {
      
    
  }
  override def display(drawable:GLAutoDrawable) = {
  }
  override def reshape(drawable:GLAutoDrawable, x:Int, y:Int, w:Int, h:Int) = {
    
  }
  override def dispose(drawable:GLAutoDrawable) = {
    System.exit(0);
  }
}

class KeyHandler extends KeyListener{
  override def keyTyped(key:KeyEvent) = {
    
  }
  override def keyPressed(key:KeyEvent) = {
    println(key.getKeyChar())
  }
  override def keyReleased(key:KeyEvent) = {
    
  }
}