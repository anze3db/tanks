package psywerx.tanks;

import javax.media.opengl.GL._
import javax.media.opengl.GLProfile
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.util.Animator
import javax.media.opengl.GLEventListener
import javax.media.opengl.GLAutoDrawable
import com.jogamp.newt.event.KeyEvent
import com.jogamp.newt.event.KeyListener;
import javax.media.opengl.GLCapabilities

object Main extends App with GLEventListener {
  var WIDTH = 600
  var HEIGHT = 600
  var program:GlProgram = null
  
  private val caps = new GLCapabilities(GLProfile.get(GLProfile.GL2ES2))
  caps.setBackgroundOpaque(false)
  
  private val glWindow = GLWindow.create(caps)
  glWindow.setSize(WIDTH, HEIGHT)
  glWindow.setTitle("Tanks")
  glWindow.setUndecorated(false)
  glWindow.setPointerVisible(true)
  glWindow.setVisible(true)
  glWindow.addGLEventListener(Main)
  glWindow.addKeyListener(new KeyHandler());
  
  private val animator = new Animator(glWindow);
  animator.add(glWindow);
  animator.start();

  private var t0 = System.currentTimeMillis()
  
  override def init(drawable:GLAutoDrawable) = {
    val gl = drawable.getGL().getGL2ES2();
    program = new GlProgram(gl)
  }
  
  override def display(drawable:GLAutoDrawable) = {
    
    val gl = drawable.getGL().getGL2ES2();
    import gl._
    
    glEnable(GL_DEPTH_TEST)
    glClearColor(0, 0f, 0, 1f);
    glClear(GL_STENCIL_BUFFER_BIT | GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    glUseProgram(program.program)
    glUniform1i(program.samplerLoc, 0)
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, program.texture)
    
    val ratio = WIDTH / HEIGHT;
    var model_view_projection = new Array[Float](16); // Gets sent to the
    var model_projection = new Array[Float](16);
    
    Matrix.setLookAtM(model_projection, 0, 0, 0, -4, 0, 0, 0, 0, 1, 0);
    Matrix.frustumM(model_view_projection, 0, -ratio, ratio, -1, 1, 3, 7);
    
    //Matrix.rotateM(model_projection, 0, -smoothDirection / 1.5f, 0, 1f, 0f);

    var projection = new Array[Float](16);
    Matrix.multiplyMM(projection, 0, model_view_projection, 0, model_projection, 0);
    glUniformMatrix4fv(program.projectionMatrixLoc, 1, false, projection, 0);

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    
    Game.tick(System.currentTimeMillis() - t0)
    Game.draw()
    t0 = System.currentTimeMillis()
    
  }
  override def reshape(drawable:GLAutoDrawable, x:Int, y:Int, w:Int, h:Int) = {
    
  }
  override def dispose(drawable:GLAutoDrawable) = {
    program.dispose()
    System.exit(0);
  }
}

class KeyHandler extends KeyListener{
  override def keyTyped(key:KeyEvent) = {
    
  }
  override def keyPressed(key:KeyEvent) = {

  }
  override def keyReleased(key:KeyEvent) = {
    
  }
}