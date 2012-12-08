package psywerx.tanks

import java.nio.Buffer
import com.jogamp.common.nio.Buffers
import javax.media.opengl.GL._
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Square {
  
  import Game.program._
  import gl._

  val tex = new Texture()
  var color = new Color(0,0,0,1)
  var size = 0.1f
  var position = new Vec(0,0,0)

  val vertices = Array[Float](1.0f, -1.0f, 0.0f,
          -1.0f, -1.0f, 0.0f,
           1.0f, 1.0f, 0.0f, 
          -1.0f, 1.0f, 0.0f)
  private var modelMatrix = new Array[Float](16)
  Matrix.setIdentityM(modelMatrix, 0)
  
  private var vbb = ByteBuffer.allocateDirect(3 * 4 * 4)
  vbb.order(ByteOrder.nativeOrder())
  private val vertBuffer  = vbb.asFloatBuffer()
  
  vbb = ByteBuffer.allocateDirect(4 * 4 * 4)
  vbb.order(ByteOrder.nativeOrder())
  private val colorBuffer  = vbb.asFloatBuffer()
  
  vbb = ByteBuffer.allocateDirect(2 * 4 * 4) // 2 = for u and v, 4 for 4 edges
  vbb.order(ByteOrder.nativeOrder())
  private val texBuffer  = vbb.asFloatBuffer()

  def tick(theta:Float) = {
  }

  def draw() = {
    glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrix, 0)
    Matrix.setIdentityM(modelMatrix, 0)
    Matrix.translateM(modelMatrix, 0, modelMatrix, 0, position.x, position.y, position.z)
    
    vertBuffer.put(vertices.map(_ * size))
    vertBuffer.flip()
    
    glVertexAttribPointer(positionLoc, 3, GL_FLOAT, false, 0, vertBuffer)
    glEnableVertexAttribArray(positionLoc)
    
    colorBuffer.put(Array[Float](color.r, color.g, color.b, color.a,
    color.r, color.g, color.b, color.a,
    color.r, color.g, color.b, color.a,
    color.r, color.g, color.b, color.a))
    colorBuffer.flip()
    
    glVertexAttribPointer(colorLoc, 4, GL_FLOAT, false, 0, colorBuffer)
    glEnableVertexAttribArray(colorLoc)
    
    texBuffer.put(tex.getTextureUV)
    texBuffer.flip()
    
    glVertexAttribPointer(texLoc, 2, GL_FLOAT, false, 0, texBuffer)
    glEnableVertexAttribArray(texLoc)

    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, texture)
    
    glUniform1f(isTextLoc, tex.enabled)
    
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 4)
    
    glDisableVertexAttribArray(positionLoc)
    glDisableVertexAttribArray(colorLoc)
    glDisableVertexAttribArray(texLoc)
  }
}