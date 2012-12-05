package psywerx.tanks;

class Vec(var x: Float, var y: Float, var z: Float) {
  def +=(a: Float) = {
    x += a; y += a; z += a;
  }
  def -=(a: Float) = {
    x -= a; y -= a; z -= a;
  }
  def +=(a: Vec) = {
    x += a.x; y += a.y; z += a.z;
  }
  def -=(a: Vec) = {
    x -= a.x; y -= a.y; z -= a.z;
  }
  def +(a: Vec) = {
    new Vec(this.x + a.x, this.y + a.y, this.z + a.z)
  }
  def -(a: Vec) = {
    new Vec(this.x - a.x, this.y - a.y, this.z - a.z)
  }
  def print() = {
    println("x: %.4f y: %.4f z: %.4f".format(x, y, z))
  }
}