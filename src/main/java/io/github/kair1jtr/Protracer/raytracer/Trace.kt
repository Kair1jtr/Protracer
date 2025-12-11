package io.github.kair1jtr.Protracer.raytracer

import io.github.kair1jtr.Protracer.Color
import io.github.kair1jtr.Protracer.Point3
import io.github.kair1jtr.Protracer.Ray
import io.github.kair1jtr.Protracer.Sphere
import io.github.kair1jtr.Protracer.Utils
import io.github.kair1jtr.Protracer.Vector3
import io.github.kair1jtr.Protracer.infinity
import io.github.kair1jtr.Protracer.pi
import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import kotlin.math.cos

class Trace(var p: PApplet) {
    fun drawImage(): PImage {
        val image = p.createImage(p.width, p.height, PConstants.RGB)
        image.loadPixels()

        val samples_per_pixel : Int = 10
        val max_depth = 5

        var world : hittable_list = hittable_list()
        world.add(Sphere(
            Point3(1.0,-100.5,-1.0),
            100.0,
            Lambertian(Color(0.8, 0.6, 0.2)))
        )
        world.add(Sphere(
            Point3(-1.0,0.0,-1.0),
            0.5,
            Lambertian(Color(0.8, 0.8, 0.8)))
        )
        world.add(Sphere(
            Point3(0.0,0.0,-1.0),
            0.5,
            Lambertian(Color(0.7, 0.3, 0.3)))
        )
        world.add(Sphere(
            Point3(1.0,0.0,-1.0),
            0.5,
            Metal(Color(0.7, 0.3, 0.3),0.3))
        )


        var cam : Camera = Camera(Point3(-2.0,2.0,1.0), Point3(0.0,0.0,-1.0), Vector3(0.0,1.0,0.0),90.0,p.width.toDouble()/p.height)

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                var pixel_color : Color = Color(0.0,0.0,0.0)

                for (s in 0 until samples_per_pixel) {
                    val u : Double = (x.toDouble() + Utils.random_double()) / (p.width-1).toDouble();
                    val v : Double = (y.toDouble() + Utils.random_double()) / (p.height-1).toDouble();

                    var r = cam.get_ray(u,v)
                    pixel_color += ray_color(r,world,max_depth)
                }
                val c = write_color(pixel_color,samples_per_pixel)

                val red = (c.x * 255).toInt().coerceIn(0, 255)
                val green = (c.y * 255).toInt().coerceIn(0, 255)
                val blue = (c.z * 255).toInt().coerceIn(0, 255)

                p.set(x,p.height-1-y,p.color(red,green,blue))
                //image.pixels[y * image.width + x] = p.color(red, green, blue)
            }
        }
        image.updatePixels()

        return image
    }

    fun write_color(pixel_color : Color,samples_per_pixel : Int) : Color {
        var r = pixel_color.x
        var g = pixel_color.y
        var b = pixel_color.z

        //色の和をサンプル数で割る
        val scale = 1.0 / samples_per_pixel
        r *= scale
        g *= scale
        b *= scale

        return Color(r,g,b)
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