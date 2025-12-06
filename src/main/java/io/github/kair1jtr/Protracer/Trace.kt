package io.github.kair1jtr.Protracer

import processing.core.PApplet
import processing.core.PConstants
import processing.core.PImage
import kotlin.math.sqrt

class Trace(var p: PApplet) {
    fun drawImage(): PImage {
        val image = p.createImage(p.width, p.height, PConstants.RGB)
        image.loadPixels()

        val aspect_ratio = (p.width.toDouble()/p.height.toDouble())

        val viewport_height = 2.0
        val viewport_width = aspect_ratio * viewport_height
        val focal_length = 1.0

        val origin = Point3(0.0,0.0,0.0)
        val horizontal = Vector3(viewport_width,0.0,0.0)
        val vertical = Vector3(0.0,viewport_height,0.0)
        val lower_left_corner = origin - horizontal/2.0 - vertical/2.0 - Vector3(0.0,0.0,focal_length)

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val u : Double = x.toDouble() / (p.width-1).toDouble();
                val v : Double = y.toDouble() / (p.height-1).toDouble();

                var r : Ray = Ray(origin,lower_left_corner + horizontal*u + vertical*v - origin)
                val c = ray_color(r)
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

    fun hit_sphere(center : Point3 , radius : Double, r: Ray): Double {
        val oc = r.orig - center
        val a = Utils.dot(r.dir,r.dir)
        val b = Utils.dot(oc,r.dir)*2.0
        val c = Utils.dot(oc,oc) - radius*radius
        val discriminant = b*b - 4*a*c
        if (discriminant < 0) {
            return -1.0
        } else {
            return (-b - sqrt(discriminant)) / (a*2.0)
        }
    }

    fun ray_color(r : Ray) : Color {
        var t = hit_sphere(Point3(0.0,0.0,-1.0),0.5,r)
        if (t > 0.0) {
            val N = (r.at(t) - Vector3(0.0,0.0,-1.0))
            return Color(N.x +1,N.y + 1,N.z + 1)*0.5
        }
        val unit_direction : Vector3 = r.dir.normalize()
        t = (unit_direction.y+1.0)*0.5
        return Color(1.0,1.0,1.0)*(1.0-t) + Color(0.5,0.7,1.0)*t
    }
}