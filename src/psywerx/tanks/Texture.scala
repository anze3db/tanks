package psywerx.tanks

class Texture {
  val NUM_SPRITES:Int = 16
  var enabled = 0.0f
  var sprite  = (0, 0)
  var size    = (1, 1)
  def setSpriteFromChar(c:Char) = {
    val charIndex = c.charValue() - 32
    sprite = ((charIndex % NUM_SPRITES), math.floor(charIndex.toDouble / NUM_SPRITES.toDouble).toInt)
  }
  def getTextureUV:Array[Float] = {
    val charWidth:(Float,Float) = (0.0625f * size._1, 0.0625f * size._2)
    val u = (sprite._1/size._1).floatValue
    val v = (sprite._2/size._2).floatValue
    Array[Float]((u + 1f)*charWidth._1, v*charWidth._2, 
                  u*charWidth._1, v*charWidth._2, 
                  (u + 1)*charWidth._1, (v + 1)*charWidth._2, 
                  u*charWidth._1, (v + 1)*charWidth._2 )
  }
}