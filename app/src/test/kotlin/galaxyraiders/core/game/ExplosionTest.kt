package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@DisplayName("Given an Explosion")
class ExplosionTest {
  private val explosion = Explosion(
    initialPosition = Point2D(1.0, 1.0),
    radius = 1.0
  )

  @Test
  fun `it has a type Explosion `() {
    assertEquals("Explosion", explosion.type)
  }

  @Test
  fun `it has a symbol X `() {
    assertEquals('X', explosion.symbol)
  }

  @Test
  fun `it shows the type Explosion when converted to String `() {
    assertTrue(explosion.toString().contains("Explosion"))
  }

  @Test
  fun `it has no mass`() {
    assertEquals(explosion.mass, 0.0)
  }

  @Test
  fun `it has no velocity`() {
    assertEquals(explosion.velocity, Vector2D(0.0, 0.0))
  }

  @Test
  fun `it shouldnt be removed when created`() {
    assertFalse(explosion.shouldBeRemoved())
  }

  @Test
  fun `it shouldn be removed after the limit of frames`() {
    repeat(20) {
      explosion.rendered()
    }
    assertTrue(explosion.shouldBeRemoved())
  }
}
