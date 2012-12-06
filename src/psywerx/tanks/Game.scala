package psywerx.tanks

import javax.media.opengl.{GL2ES2 => GL}

object Game {
  
  val program = Main.program
  
  init()
  val s = new Square()
  s.tex.setSpriteFromChar('A')
  
  
  def init() = {
  }
  def tick(theta:Float) = {
    s.tex.setSpriteFromChar('Z')
    s.tick(theta)
  }
  def draw() = {
    s.draw()
  }
}