package psywerx.tanks

import javax.media.opengl.{GL2ES2 => GL}

object Game {
  
  val program = Main.program
  
  init()
  val s = new Square()
  
  
  def init() = {
    
  }
  def tick(theta:Double) = {
    s.tick(theta)
  }
  def draw() = {
    s.draw()
  }
}