import javax.media.opengl.GLCapabilities
import javax.media.opengl.GLProfile
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.util.Animator
import javax.media.opengl.GLEventListener
import javax.media.opengl.GLAutoDrawable

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
  
  val animator = new Animator(glWindow);
  animator.add(glWindow);
  animator.start();
  
  def init(drawable:GLAutoDrawable) = {
    val gl = drawable.getGL().getGL2ES2();
    println("init")
    
    
  }
  def display(drawable:GLAutoDrawable) = {
  }
  def reshape(drawable:GLAutoDrawable, x:Int, y:Int, w:Int, h:Int) = {
    
  }
  def dispose(drawable:GLAutoDrawable) = {
    println("Exit")
    System.exit(0);
  }
}