package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

private const val PI_IN_DEGREES = 180

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.sqrt(this.dx * this.dx + this.dy * this.dy)

  val radiant: Double
    get() {
      var theta = Math.atan(this.dy / this.dx)
      if (this.dx < 0 && this.dy >= 0) {
        theta += Math.PI
      } else if (this.dx < 0 && this.dy < 0) {
        theta -= Math.PI
      }
      return theta
    }

  val degree: Double
    get() {
      return this.radiant * PI_IN_DEGREES / Math.PI
    }

  val unit: Vector2D
    get() = Vector2D(this.dx / this.magnitude, this.dy / this.magnitude)

  val normal: Vector2D
    get() {
      val unitVector = this.unit
      return Vector2D(unitVector.dy, -unitVector.dx)
    }

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(this.dx * scalar, this.dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(this.dx / scalar, this.dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return this.dx * v.dx + this.dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(this.dx + v.dx, this.dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(this.dx + p.x, this.dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-this.dx, -this.dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(this.dx - v.dx, this.dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return this.times(target.unit)
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return this.scalarProject(target) * target.unit
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(this * v.dx, this * v.dy)
}
