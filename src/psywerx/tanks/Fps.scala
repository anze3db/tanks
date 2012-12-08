package psywerx.tanks

object Fps {
  
  val counter = new Text("0000")
  var timediff = 0.0f
  var cnt = 0
  
  def update(theta:Float) = {
    timediff += theta
    
    
    if(timediff > 1000){
      counter.update("%6d".format(cnt))
      timediff = 0
      cnt = 0
    }
  }
  def draw() = {
    cnt += 1
    counter.draw()
  }
}