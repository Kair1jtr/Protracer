package io.github.kair1jtr.Protracer.game

import io.github.kair1jtr.Protracer.raytracer.Point3
import io.github.kair1jtr.Protracer.raytracer.Utils
import io.github.kair1jtr.Protracer.raytracer.Vector3
import kotlin.math.sin


//プレイヤーのいる方向に飛んでくる
class NormalEnemy(point : Point3,dir : Vector3,size : Double) : Enemy(point,dir,size) {
    var lifetime = 0

    override fun move() {

        for (i in ballets.size - 1 downTo 0) {
            if (ballets[i].lifetime >= 20) {
                ballets.removeAt(i)
            }else {
                ballets[i].move()
            }
        }

        point = (point + dir.normalize()*0.25)/*+ sin(System.currentTimeMillis() * 0.001)*/

        lifetime++

        if (lifetime >= 80) {
            alive = !alive
        }
    }

    override fun attack(player: Point3) {
        ballets.add(Ballet(point, 0.1,player - point))
    }

    override fun hit_box(center: Vector3, player_size: Double): Boolean {
        val distance = (point - center).length()

        // 距離が（相手のサイズ + 自分のサイズ）以下なら衝突
        return distance <= (player_size + size)
    }
}