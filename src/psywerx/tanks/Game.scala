package psywerx.tanks

import javax.media.opengl.{GL2ES2 => GL}

object Game {
  
  val program = Main.program
  
  val topleft = new Square()
  topleft.position = new Vec(-1,1,0)
  topleft.color.r = 0.5f
  
  val topright = new Square()
  topright.position = new Vec(1,1,0)
  topright.color.b = 100
  
  val bottomleft = new Square()
  bottomleft.position = new Vec(-1,-1,0)
  bottomleft.color.g = 100
  
  val bottomright = new Square()
  bottomright.position = new Vec(1,-1,0)
  
  init()
  
  val t = new Text("ABCadbcsajkf")
  
  def init() = {
  }
  def tick(theta:Float) = {
    
    Fps.update(theta)
  }
  def draw() = {
    Fps.draw()
    topleft.draw()
    topleft.position.z = 0f
    topleft.position.x = -1f
    topright.draw()
    bottomleft.draw()
    bottomright.draw()
  }
}