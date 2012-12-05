package psywerx.tanks

import javax.media.opengl.{GL2ES2 => GL}

object Game {
  val program = Main.program
  val gl = program.gl
  init()
  val s = new Square()
  
  
  def init() = {
    
  }
  def tick(theta:Double) = {
    
  }
  def draw(gl:GL) = {
    s.draw(gl)
  }
}