package psywerx.tanks

import java.nio.Buffer
import com.jogamp.common.nio.Buffers
import javax.media.opengl.GL._
import javax.media.opengl.{GL2ES2 => GL}

class Square {
  val program = Game.program

  var modelMatrix = new Array[Float](16)
  Matrix.setIdentityM(modelMatrix, 0)
  val vertices = Array[Float](1.0f, -1.0f, 0.0f, // Bottom Right
                -1.0f, -1.0f, 0.0f, // Bottom Left
                1.0f, 1.0f, 0.0f, // Top Right
                -1.0f, 1.0f, 0.0f)
  val colors = Array[Float](0.0f, 0.0f, 0.0f, 1.0f,
    0.0f, 1.0f, 0.0f, 0.0f,
    0.0f, 1.0f, 0.0f, 1.0f,
    0.0f, 1.0f, 0.0f, 1.0f)
  val textures = Array[Float](0.0f, 0.0f,
    0.0f, 0.0f,
    0.0f, 0.0f,
    0.0f, 0.0f)

  def tick(theta: Long) = {

  }

  def draw(gl:GL) = {
    gl.glUniformMatrix4fv(program.modelMatrixLoc, 1, false, modelMatrix, 0)
    
    var fb = Buffers.newDirectFloatBuffer(vertices);
    
    gl.glVertexAttribPointer(program.positionLoc, 3, GL_FLOAT, false, 0, fb)
    gl.glEnableVertexAttribArray(program.positionLoc)
    
    fb = Buffers.newDirectFloatBuffer(colors);
    
    gl.glVertexAttribPointer(program.colorLoc, 4, GL_FLOAT, false, 0, fb);
    gl.glEnableVertexAttribArray(program.colorLoc);
    
    fb = Buffers.newDirectFloatBuffer(textures);
    
    gl.glVertexAttribPointer(program.texLoc, 2, GL_FLOAT, false, 0, fb);
    gl.glEnableVertexAttribArray(program.texLoc);

    gl.glActiveTexture(GL_TEXTURE0);
    gl.glBindTexture(GL_TEXTURE_2D, program.texture);
    
    gl.glUniform1f(program.isTextLoc, 1.0f);
    
    gl.glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
    
    gl.glDisableVertexAttribArray(program.positionLoc);
    gl.glDisableVertexAttribArray(program.colorLoc);
    gl.glDisableVertexAttribArray(program.texLoc);
  }
}