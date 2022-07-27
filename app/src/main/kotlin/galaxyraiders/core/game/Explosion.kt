package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D

const val EXPLOSION_RENDER_DURATION = 20

class Explosion(
  initialPosition: Point2D,
  radius: Double
) :
  SpaceObject("Explosion", 'X', initialPosition, Vector2D(0.0, 0.0), radius, 0.0) {
  var framesRendered: Int = 0

  fun rendered() {
    this.framesRendered += 1
  }

  fun shouldBeRemoved(): Boolean {
    return this.framesRendered >= EXPLOSION_RENDER_DURATION
  }
}
