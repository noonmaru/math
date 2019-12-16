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

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Predicate;

public final class VectorSpace implements Iterable<Vector>
{

    private final ArrayList<Vector> vectors;

    public VectorSpace(Collection<Vector> c)
    {
        vectors = new ArrayList<>(c);
    }

    public VectorSpace(Vector... vectors)
    {
        this(Arrays.asList(vectors));
    }

    public VectorSpace(int initCapacity)
    {
        this.vectors = new ArrayList<>(initCapacity);
    }

    private VectorSpace(VectorSpace vectorSpace)
    {
        vectors = new ArrayList<>(vectorSpace.vectors.size());

        for (Vector vector : vectorSpace)
            vectors.add(vector.copy());
    }

    public void addVector(Vector vector)
    {
        vectors.add(vector);
        clearTemp();
    }

    public boolean removeVector(Vector vector)
    {
        if (vectors.remove(vector))
        {
            clearTemp();
            return true;
        }

        return false;
    }

    public boolean removeVectorIf(Predicate<Vector> filter)
    {
        if (vectors.removeIf(filter))
        {
            clearTemp();
            return true;
        }

        return false;
    }

    public void trimToSize()
    {
        vectors.trimToSize();
    }

    public VectorSpace add(double x, double y, double z)
    {
        for (Vector vector : vectors)
            vector.add(x, y, z);

        return this;
    }

    public VectorSpace add(double v)
    {
        return add(v, v, v);
    }

    public VectorSpace add(Vector v)
    {
        return add(v.x, v.y, v.z);
    }

    public VectorSpace subtract(double x, double y, double z)
    {
        for (Vector vector : vectors)
            vector.subtract(x, y, z);

        return this;
    }

    public VectorSpace subtract(double v)
    {
        return subtract(v, v, v);
    }

    public VectorSpace subtract(Vector v)
    {
        return subtract(v.x, v.y, v.z);
    }

    public VectorSpace multiply(double x, double y, double z)
    {
        for (Vector vector : vectors)
            vector.multiply(x, y, z);

        return this;
    }

    public VectorSpace multiply(double v)
    {
        return multiply(v, v, v);
    }

    public VectorSpace multiply(Vector v)
    {
        return multiply(v.x, v.y, v.z);
    }

    public VectorSpace devide(double x, double y, double z)
    {
        for (Vector vector : vectors)
        {
            vector.x /= x;
            vector.y /= y;
            vector.z /= z;
        }

        return this;
    }

    public VectorSpace devide(double v)
    {
        return devide(v, v, v);
    }

    public VectorSpace devide(Vector v)
    {
        return devide(v.x, v.y, v.z);
    }

    public VectorSpace rotateAxisX(double pitch)
    {
        Vector.rotateAxisX(vectors, pitch);

        return this;
    }

    public VectorSpace rotateAxisY(double yaw)
    {
        Vector.rotateAxisY(vectors, yaw);

        return this;
    }

    public VectorSpace rotateAxisZ(double roll)
    {
        Vector.rotateAxisZ(vectors, roll);

        return this;
    }

    @Override
    public Iterator<Vector> iterator()
    {
        return this.vectors.iterator();
    }

    private SoftReference<VectorSpace> temp;

    public VectorSpace temp()
    {
        VectorSpace mirror = temp == null ? null : temp.get();

        if (mirror == null)
            temp = new SoftReference<>(mirror = new VectorSpace(this));
        else
        {
            ArrayList<Vector> src = vectors;
            ArrayList<Vector> dst = mirror.vectors;

            for (int i = 0, size = src.size(); i < size; i++)
                dst.get(i).set(src.get(i));
        }

        return mirror;
    }

    public void clearTemp()
    {
        temp = null;
    }

    private List<Vector> unmodifiableVectors;

    public List<Vector> getVectors()
    {
        List<Vector> vectors = this.unmodifiableVectors;

        return vectors == null ? this.unmodifiableVectors = Collections.unmodifiableList(this.vectors) : vectors;
    }

    public int size()
    {
        return vectors.size();
    }

    public VectorSpace copy()
    {
        return new VectorSpace(this);
    }

}
