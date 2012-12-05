package psywerx.tanks

import java.nio.Buffer
import com.jogamp.common.nio.Buffers
import javax.media.opengl.GL._
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Square {
  
  import Game.program._
  import gl._

  var modelMatrix = new Array[Float](16)
  Matrix.setIdentityM(modelMatrix, 0)
  val vertices = Array[Float](1.0f, -1.0f, 0.0f,
                -1.0f, -1.0f, 0.0f,
                1.0f, 1.0f, 0.0f, 
                -1.0f, 1.0f, 0.0f)
  val colors = Array[Float](0.0f, 0.0f, 0.0f, 1.0f,
    0.0f, 1.0f, 0.0f, 0.0f,
    0.0f, 1.0f, 0.0f, 1.0f,
    0.0f, 1.0f, 0.0f, 1.0f)
  val textures = Array[Float](0.0f, 0.0f,
    0.0f, 0.0f,
    0.0f, 0.0f,
    0.0f, 0.0f)
    
  val vertBuffer  = Buffers.newDirectFloatBuffer(vertices)
  val colorBuffer  = Buffers.newDirectFloatBuffer(colors)
  val texBuffer  = Buffers.newDirectFloatBuffer(textures)

  def tick(theta:Double) = {
  }

  def draw() = {
    glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrix, 0)
    Matrix.rotateM(modelMatrix, 0, 0.01f, 0, 0, 1)
    
    
    glVertexAttribPointer(positionLoc, 3, GL_FLOAT, false, 0, vertBuffer)
    glEnableVertexAttribArray(positionLoc)
    
    glVertexAttribPointer(colorLoc, 4, GL_FLOAT, false, 0, colorBuffer);
    glEnableVertexAttribArray(colorLoc);
    
    glVertexAttribPointer(texLoc, 2, GL_FLOAT, false, 0, texBuffer);
    glEnableVertexAttribArray(texLoc);

    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, texture);
    
    glUniform1f(isTextLoc, 1.0f);
    
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    
    glDisableVertexAttribArray(positionLoc);
    glDisableVertexAttribArray(colorLoc);
    glDisableVertexAttribArray(texLoc);
  }
}