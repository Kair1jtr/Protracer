package io.github.kair1jtr.Protracer.game

import io.github.kair1jtr.Protracer.Sphere
import io.github.kair1jtr.Protracer.raytracer.*
import processing.core.PApplet
import kotlin.math.cos
import kotlin.math.sin


class Game(val p: PApplet,val aspect_ratio : Double) {
    val img_width = 400.0
    val img_height= img_width / aspect_ratio

    val trace = Trace(p,img_width.toInt(),img_height.toInt())
    var player = Player(p,Point3(-2.0,0.0,0.0),0.0)
    var enemies = mutableListOf<Enemy>()

    fun add_obj() {
        /*trace.world.add(Sphere(
            Point3(0.0,0.0,0.0),
            1.0,
            Metal(Color(0.8, 0.8, 0.8),0.3)
        ))*/

        for (enemy in enemies) {
            trace.world.add(Sphere(
                enemy.point,
                enemy.size,
                Lambertian(Color(/*0.835294, 0.917647, 0.847058*/Utils.random_double(),Utils.random_double(),Utils.random_double()))
            ))

            for (ballet in enemy.ballets) {
                trace.world.add(Sphere(
                    ballet.point,
                    ballet.size,
                    Lambertian(Color(0.698, 0.176, 0.207))
                ))
            }
        }

        for (ballet in player.ballets) {
            trace.world.add(Sphere(
                ballet.point,
                ballet.size,
                Lambertian(Color(0.345098, 0.325490, 0.368627))
            ))
        }
    }

    var last: Long = 0
    val interval: Long = 1000
    var hit_count = 0

    fun update() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - last >= interval) {
            val x = player.point.x+Utils.random_double(-10.0,10.0)
            val y = player.point.y+Utils.random_double(-10.0,10.0)
            val z = player.point.z+Utils.random_double(-10.0,10.0)

            enemies.add(NormalEnemy(Point3(x,y,z),player.point - Point3(x,y,z),0.50))
            last = currentTime
        }

        for (i in enemies.size -1 downTo  0) {
            /*for (ballet in player.ballets) {
                if (enemies[i].hit_box(ballet.point,ballet.size))  {
                    enemies[i].alive = !enemies[i].alive

                    println("hit")
                }
            }*/

            for (k in player.ballets.size - 1 downTo 0) {
                if (enemies[i].hit_box(player.ballets[k].point,player.ballets[k].size))  {
                    enemies[i].alive = !enemies[i].alive
                    player.ballets[k].lifetime = 20.0;

                    hit_count++
                    println("hit count: " + hit_count)
                }
            }
            enemies[i].move()
        }

        player.move()

        delete_enemies()
        render()

        trace.world.clear()
    }

    fun player_attack() {
        player.attack()
    }
    fun player_rotateX(rot : Double) {
        player.rotateX(rot)
    }
    fun player_rotateY(rot : Double) {
        player.rotateY(rot)
    }

    fun enemy_attack() {
        for (enemy in enemies) {
            enemy.attack(player.point)
        }
    }

    fun delete_enemies() {
        for (i in enemies.size -1 downTo 0) {
            if (!enemies[i].alive) enemies.removeAt(i)
        }
    }

    fun render() {
        val img = trace.render(player.cam)
        val scaled = img.copy()
        scaled.resize(p.width,p.height)
        p.image(scaled,0.0F,0.0F)
    }

}