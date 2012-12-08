package psywerx.tanks
import javax.media.opengl.{GL2ES2 => GL}
import javax.imageio.ImageIO
import java.nio.ByteBuffer
import java.awt.image.Raster

class GlProgram (val gl:GL) {
  
  import gl._
  import GL._
  
  val program = glCreateProgram()
  
  private val vertShader = glCreateShader(GL_VERTEX_SHADER)
  private val fragShader = glCreateShader(GL_FRAGMENT_SHADER)
  
  createShader(vertShader, Shaders.vertex)
  createShader(fragShader, Shaders.fragment)
  link()
  
  val positionLoc = glGetAttribLocation(program, "attribute_Position")
  val colorLoc    = glGetAttribLocation(program, "attribute_Color")
  val texLoc      = glGetAttribLocation(program, "a_texCoord")

  val projectionMatrixLoc = glGetUniformLocation(program, "uniform_Projection")
  val modelMatrixLoc      = glGetUniformLocation(program, "uniform_Model")
  val samplerLoc          = glGetUniformLocation(program, "s_texture")
  val isTextLoc           = glGetUniformLocation(program, "u_isText")
  val texture = loadTexture("/res/text.png")
  
  
  private def loadTexture(tex:String) = {
    var texture = new Array[Int](1)
    
    val image = ImageIO.read(this.getClass().getResource(tex))
    val data:Raster = image.getData()
    
    val t:Array[Int] = null
    
    val pixels = data.getPixels(0,0,image.getWidth(), image.getHeight(),t)
    val pixelBuffer = ByteBuffer.wrap(pixels.map { p => p.asInstanceOf[Byte] })
    
    import javax.media.opengl.GL._
    
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
    glGenTextures(1, texture, 0)
    glBindTexture(GL_TEXTURE_2D, texture(0))
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getHeight(), image.getWidth(), 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelBuffer)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST)
    glGenerateMipmap(GL_TEXTURE_2D)
    
    texture(0)
  }
  
  private def createShader(shader:Int, source:String) = {
    val srcArr = Array(source)
    glShaderSource(shader, srcArr.length, srcArr, Array(source.length()), 0)
    glCompileShader(shader)
    
    var compiled = Array[Int](0)
    glGetShaderiv(shader, GL_COMPILE_STATUS, compiled, 0)
    if (compiled(0) == 0){
      var logLength = Array[Int](0);
      glGetShaderiv(shader, GL_INFO_LOG_LENGTH, logLength, 0)
        
      var log = Array[Byte](0);
      glGetShaderInfoLog(shader, logLength(0), null, 0, log, 0)
        
        println("Error compiling the shader: " + new String(log))
        System.exit(1);
    }
    glAttachShader(program, shader)
  }
  def link() = {
    glLinkProgram(program)
  }
  
  def dispose() = {
    glUseProgram(program)
    glDetachShader(program, vertShader)
    glDeleteShader(vertShader)
    glDetachShader(program, fragShader)
    glDeleteShader(fragShader)
    glDeleteProgram(program)
  }
  
  
}