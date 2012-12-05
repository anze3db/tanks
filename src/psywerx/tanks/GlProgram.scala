package psywerx.tanks

import javax.media.opengl.GL.GL_RGBA
import javax.media.opengl.GL.GL_TEXTURE_2D
import javax.media.opengl.GL.GL_TEXTURE_MAG_FILTER
import javax.media.opengl.GL.GL_TEXTURE_MIN_FILTER
import javax.media.opengl.GL.GL_UNPACK_ALIGNMENT
import javax.media.opengl.GL.GL_UNSIGNED_BYTE
import javax.media.opengl.GL.GL_LINEAR
import javax.media.opengl.GL.GL_LINEAR_MIPMAP_NEAREST
import javax.media.opengl.{GL2ES2 => GL}
import javax.imageio.ImageIO
import java.nio.ByteBuffer
import java.awt.image.Raster

class GlProgram (val gl:GL) {
  
  val program = gl.glCreateProgram()
  
  private val vertShader = gl.glCreateShader(GL.GL_VERTEX_SHADER);
  private val fragShader = gl.glCreateShader(GL.GL_FRAGMENT_SHADER);
  
  createShader(vertShader, Shaders.vertex)
  createShader(fragShader, Shaders.fragment)
  
  val positionLoc = gl.glGetAttribLocation(program, "attribute_Position")
  val colorLoc    = gl.glGetAttribLocation(program, "attribute_Color")
  val texLoc      = gl.glGetAttribLocation(program, "a_texCoord")
  
  val projectionMatrixLoc = gl.glGetUniformLocation(program, "uniform_Projection")
  val modelMatrixLoc      = gl.glGetUniformLocation(program, "uniform_Model")
  val samplerLoc          = gl.glGetUniformLocation(program, "s_texture")
  val isTextLoc           = gl.glGetUniformLocation(program, "u_isText")
  
  val texture = loadTexture("/res/text.png")
  
  link()
  
  private def loadTexture(tex:String) = {
    var texture = new Array[Int](1)
    
    val image = ImageIO.read(this.getClass().getResource(tex))
    val data:Raster = image.getData()
    
    val t:Array[Int] = null
    
    val pixels = data.getPixels(0,0,image.getWidth(), image.getHeight(),t)
    val pixelBuffer = ByteBuffer.wrap(pixels.map { p => p.asInstanceOf[Byte] })
    
    gl.glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
    gl.glGenTextures(1, texture, 0)
    gl.glBindTexture(GL_TEXTURE_2D, texture(0))
    gl.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getHeight(), image.getWidth(), 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelBuffer)
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST)
    gl.glGenerateMipmap(GL_TEXTURE_2D)
    
    texture(0)
  }
  
  private def createShader(shader:Int, source:String) = {
    val srcArr = Array(source)
    gl.glShaderSource(shader, srcArr.length, srcArr, Array(source.length()), 0)
    gl.glCompileShader(shader)
    
    var compiled = Array[Int](0)
    gl.glGetShaderiv(shader, GL.GL_COMPILE_STATUS, compiled, 0)
    if (compiled(0) == 0){
      var logLength = Array[Int](0);
      gl.glGetShaderiv(shader, GL.GL_INFO_LOG_LENGTH, logLength, 0);
        
      var log = Array[Byte](0);
      gl.glGetShaderInfoLog(shader, logLength(0), null, 0, log, 0);
        
        println("Error compiling the shader: " + new String(log));
        System.exit(1);
    }
    gl.glAttachShader(program, shader)
  }
  def link() = {
    gl.glLinkProgram(program)
  }
  
  def dispose() = {
    gl.glUseProgram(program);
    gl.glDetachShader(program, vertShader);
    gl.glDeleteShader(vertShader);
    gl.glDetachShader(program, fragShader);
    gl.glDeleteShader(fragShader);
    gl.glDeleteProgram(program);
  }
  
  
}