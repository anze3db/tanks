package psywerx.tanks

class Text(text:String) {
  var squares:Array[Square] = Array.ofDim(text.length())
  var size = 0.05f
  var position = new Vec(0,0,0)
  var space = 1.25f
  update(text)
  
  def update(text:String) = {
    
    squares = Array.ofDim(text.length())
    
    for ((c,i) <- text.zipWithIndex){
        val s = new Square()
        s.tex.setSpriteFromChar(c)
        s.tex.enabled = 0.0f
        s.size = size
        s.position = new Vec(position.x + i*size*space, position.y, position.z - i*0.000001f)
        squares(i) = s
    }
  }
  
  
  
  def draw() = {
    for((s,i) <- squares.zipWithIndex){
      s.draw()
    }
  }
  
}