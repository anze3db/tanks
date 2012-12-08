package psywerx.tanks

import javax.media.opengl.{ GL2ES2 => GL }

object Game {

  val program = Main.program
  val fps = new Fps()
  val sq = new Square()
  init()

  def init() = {

  }
  def tick(theta: Float) = {
    fps.update(theta)
  }
  def draw() = {
    fps.draw()
    sq.draw()
  }
}