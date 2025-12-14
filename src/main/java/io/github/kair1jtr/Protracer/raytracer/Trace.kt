package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.raytracer.Ray
import io.github.kair1jtr.Protracer.Sphere
import io.github.kair1jtr.Protracer.raytracer.Utils
import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.*

class Trace(var p: PApplet,val width : Int,val height: Int) {
    public val world = hittable_list()

    fun render(cam : Camera): PImage = runBlocking {
        //world.clear()

        val image = p.createImage(width, height, PConstants.RGB)
        image.loadPixels()

        val samples = 2
        val maxDepth = 3

        /*val cam = Camera(
            Point3(-2.0,2.0,1.0),
            Point3(0.0,0.0,0.0),
            Vector3(0.0,1.0,0.0),
            pi/180 * 90,
            width.toDouble() / height
        )*/

        val h = image.height
        val w = image.width

        val time = measureTimeMillis {
            for (y in 0 until h) {
                for (x in 0 until w) {
                    var col = Color(0.0,0.0,0.0)

                    repeat(samples) {
                        val u = (x + Utils.random_double()) / (w - 1)
                        val v = (y + Utils.random_double()) / (h - 1)

                        col += ray_color(cam.get_ray(u,v), world, maxDepth)
                    }

                    val c = write_color(col, samples)

                    val r = (c.x * 255).toInt().coerceIn(0,255)
                    val g = (c.y * 255).toInt().coerceIn(0,255)
                    val b = (c.z * 255).toInt().coerceIn(0,255)

                    image.pixels[(image.height - 1 - y) * image.width + x] = 0xFF000000.toInt() or (r.toInt() shl 16) or (g.toInt() shl 8) or b.toInt()
                }
            }
        }

        //println("Render time = $time ms")

        image.updatePixels()
        return@runBlocking image
    }

    fun add_obj(obj: Sphere) {
        world.add(obj)
    }

    fun clear_obj() {
        world.clear()
    }

    fun write_color(pixel_color : Color,samples_per_pixel : Int) : Color {
        //色の和をサンプル数で割る
        val scale = 1.0 / samples_per_pixel

        return Color(
            pixel_color.x * scale,
            pixel_color.y * scale,
            pixel_color.z * scale
        )
    }

    fun ray_color(r : Ray, world: hittable_list,depth : Int) : Color {
        //ワールドのどれかの物体にヒットするかチェックする
        var rec : hit_record = world.hit(r,0.001, infinity)
        //深さを検知して終了させる
        if (depth <= 0) return Color(0.0,0.0,0.0)
        //当たった場合に反射や反射した場合のベクトルを計算させる
        if (rec.j) {
            val (color,ray,s) = rec.mat_ptr.scatter(r,rec)
            if (s == true) {
                return color * ray_color(ray,world,depth -1)
            }
            return Color(0.0,0.0,0.0)
        }
        //空の描画
        val unit_direction : Vector3 = r.dir.normalize()
        val t = (unit_direction.y+1.0)*0.5
        return Color(1.0, 1.0, 1.0) *(1.0-t) + Color(0.5, 0.7, 1.0) *t
    }
}