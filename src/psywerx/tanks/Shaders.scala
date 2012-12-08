package psywerx.tanks

object Shaders {
  val fragment = """
    precision mediump float; 
    precision mediump int; 
    
    varying   vec4    varying_Color;  //incomming varying data to the
                                           //frament shader
    varying vec2 v_texCoord; 
    uniform sampler2D s_texture; 
    uniform float u_isText; 
    void main (void) { 
      if(u_isText < 0.5){
        vec4 texture = texture2D(s_texture, v_texCoord.st); 
        gl_FragColor = texture * varying_Color[3]; 
      } else { 
        gl_FragColor = varying_Color; 
      }
    }
  """

  val vertex = """
    precision mediump float;  // Precision Qualifiers
    precision mediump int;    // GLSL ES section 4.5.2
    
    uniform mat4    uniform_Projection;  // Incomming data used by
    uniform mat4    uniform_Model;  // Incomming data used by
    
    attribute vec4  attribute_Position;  // the vertex shader
    attribute vec4  attribute_Color;     // uniform and attributes
    attribute vec2 a_texCoord; 
    
    varying vec4    varying_Color;  // Outgoing varying data
                                         // sent to the fragment shader
    varying vec2 v_texCoord; 
    
    
    void main(void) {
      varying_Color = attribute_Color; 
      gl_Position = uniform_Projection * uniform_Model * attribute_Position; 
      v_texCoord = a_texCoord; 
      gl_PointSize = 1.5;
    }  
  """
}