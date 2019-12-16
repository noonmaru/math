/*
 * Copyright (c) 2019 Noonmaru
 *
 * Licensed under the General Public License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/gpl-2.0.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.github.noonmaru.math;

public final class Vector
{

    public static void rotateAxisX(Iterable<Vector> vectors, double pitch)
    {
        double radians = MathHelper.toRadians(pitch);
        double cos = MathHelper.cos(radians);
        double sin = MathHelper.sin(radians);

        for (Vector vector : vectors)
            vector.rotateAxisX(cos, sin);
    }

    public static void rotateAxisY(Iterable<Vector> vectors, double yaw)
    {
        double radians = MathHelper.toRadians(yaw);
        double cos = MathHelper.cos(radians);
        double sin = MathHelper.sin(radians);

        for (Vector vector : vectors)
            vector.rotateAxisY(cos, sin);
    }

    public static void rotateAxisZ(Iterable<Vector> vectors, double roll)
    {
        double radians = MathHelper.toRadians(roll);
        double cos = MathHelper.cos(radians);
        double sin = MathHelper.sin(radians);

        for (Vector vector : vectors)
            vector.rotateAxisZ(cos, sin);
    }

    public double x, y, z;

    public Vector()
    {}

    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector set(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        return this;
    }

    public Vector set(Vector p)
    {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;

        return this;
    }

    public Vector add(double x, double y, double z)
    {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public Vector add(Vector p)
    {
        return add(p.x, p.y, p.z);
    }

    public Vector subtract(double x, double y, double z)
    {
        this.x -= x;
        this.y -= y;
        this.z -= z;

        return this;
    }

    public Vector subtract(Vector p)
    {
        return subtract(p.x, p.y, p.z);
    }

    public Vector multiply(double x, double y, double z)
    {
        this.x *= x;
        this.y *= y;
        this.z *= z;

        return this;
    }

    public Vector multiply(double m)
    {
        x *= m;
        y *= m;
        z *= m;

        return this;
    }

    public Vector multiply(Vector p)
    {
        return multiply(p.x, p.y, p.z);
    }

    public Vector devide(double x, double y, double z)
    {
        this.x /= x;
        this.y /= y;
        this.z /= z;

        return this;
    }

    public Vector devide(double d)
    {
        return devide(d, d, d);
    }

    public Vector devide(Vector p)
    {
        return devide(p.x, p.y, p.z);
    }

    public Vector rotateAxisX(double pitch)
    {
        double radians = MathHelper.toRadians(pitch);
        rotateAxisX(MathHelper.cos(radians), MathHelper.sin(radians));

        return this;
    }

    private void rotateAxisX(double cos, double sin)
    {
        double y = this.y;
        double z = this.z;

        this.y = y * cos - z * sin;
        this.z = z * cos + y * sin;
    }

    public Vector rotateAxisY(double yaw)
    {
        double radians = MathHelper.toRadians(yaw);
        rotateAxisY(MathHelper.cos(radians), MathHelper.sin(radians));

        return this;
    }

    private void rotateAxisY(double cos, double sin)
    {
        double x = this.x;
        double z = this.z;

        this.x = x * cos - z * sin;
        this.z = z * cos + x * sin;
    }

    public Vector rotateAxisZ(double roll)
    {
        double radians = MathHelper.toRadians(roll);
        rotateAxisZ(MathHelper.cos(radians), MathHelper.sin(radians));

        return this;
    }

    private void rotateAxisZ(double cos, double sin)
    {
        double x = this.x;
        double y = this.y;

        this.x = x * cos - y * sin;
        this.y = y * cos + x * sin;
    }

    public Vector zero()
    {
        this.x = this.y = this.z = 0.0D;

        return this;
    }

    public Vector random()
    {
        this.x = Math.random();
        this.y = Math.random();
        this.z = Math.random();

        return this;
    }

    public Vector normalize()
    {
        double length = length();

        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    public double lengthSquared()
    {
        return MathHelper.square(x) + MathHelper.square(y) + MathHelper.square(z);
    }

    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public double distanceSquared(double x, double y, double z)
    {
        return MathHelper.square(x - this.x) + MathHelper.square(y - this.y) + MathHelper.square(z - this.z);
    }

    public double distanceSquared(Vector p)
    {
        return distanceSquared(p.x, p.y, p.z);
    }

    public double distance(double x, double y, double z)
    {
        return Math.sqrt(distanceSquared(x, y, z));
    }

    public double distance(Vector p)
    {
        return distance(p.x, p.y, p.z);
    }

    public Vector copy()
    {
        return new Vector(x, y, z);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;

        hash = 79 * hash + (int) (Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32);

        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Vector)
        {
            Vector other = (Vector) obj;

            return x == other.x && y == other.y && z == other.z;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "vector[" + this.x + ", " + this.y + ", " + this.z + ']';
    }

}
