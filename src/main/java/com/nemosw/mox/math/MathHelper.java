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

package com.nemosw.mox.math;

public final class MathHelper
{

    public static final float PI = (float) Math.PI;

    private static final float[] SIN_TABLE = new float[65536];

    static
    {
        for (int i = 0; i < 65536; i++)
        {
            SIN_TABLE[i] = ((float) Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D));
        }
    }

    public static float sin(float radians)
    {
        return SIN_TABLE[((int) (radians * 10430.378F) & 0xFFFF)];
    }

    public static float sin(double radians)
    {
        return SIN_TABLE[((int) (radians * 10430.378D) & 0xFFFF)];
    }

    public static float cos(float radians)
    {
        return SIN_TABLE[((int) (radians * 10430.378F + 16384.0F) & 0xFFFF)];
    }

    public static float cos(double radians)
    {
        return SIN_TABLE[((int) (radians * 10430.378D + 16384.0D) & 0xFFFF)];
    }

    public static float toRadians(float degrees)
    {
        return degrees / 180.0F * PI;
    }

    public static double toRadians(double degrees)
    {
        return degrees / 180.0D * Math.PI;
    }

    public static float toDegrees(float radians)
    {
        return radians * 180.0F / PI;
    }

    public static double toDegrees(double radians) { return radians * 180.0D / Math.PI;}

    public static int square(int i)
    {
        return i * i;
    }

    public static float square(float f)
    {
        return f * f;
    }

    public static double square(double d)
    {
        return d * d;
    }

    public static int clamp(int i, int min, int max)
    {
        if (i < min)
            return min;

        if (i > max)
            return max;

        return i;
    }

    public static long clamp(long i, long min, long max)
    {
        if (i < min)
            return min;

        if (i > max)
            return max;

        return i;
    }

    public static float clamp(float i, float min, float max)
    {
        if (i < min)
            return min;

        if (i > max)
            return max;

        return i;
    }

    public static double clamp(double i, double min, double max)
    {
        if (i < min)
            return min;

        if (i > max)
            return max;
        return i;
    }

    public static int round(float f)
    {
        return (int) (f + 0.5F);
    }

    public static int round(double d)
    {
        return (int) (d + 0.5D);
    }

    public static int ceil(float f)
    {
        int i = (int) f;

        return f > i ? i + 1 : i;
    }

    public static int ceil(double d)
    {
        int i = (int) d;

        return d > i ? i + 1 : i;
    }

    public static int gcd(int a, int b)
    {
        int temp = 0;
        while (a != 0)
        {
            if (a < b)
            {
                temp = a;
                a = b;
                b = temp;
            }
            a -= b;
        }
        return b;
    }

}
