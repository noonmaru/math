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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public final class VectorParser
{

    public static VectorSpace parseImage(BufferedImage image, double pixelSize)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        double halfWidth = (width - 1) / 2D;
        double halfHeight = (height - 1) / 2D;

        ArrayList<Vector> vectors = new ArrayList<>(width * height);

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                int dot = image.getRGB(x, y);

                if (dot != 0 && dot != -1)
                {
                    vectors.add(new Vector(-(x - halfWidth) * pixelSize, -(y - halfHeight) * pixelSize, 0));
                }
            }
        }

        vectors.trimToSize();

        return new VectorSpace(vectors);
    }

    public static VectorSpace parseText(String s, String fontName, int fontStyle, int fontSize, double pixelSize)
    {
        return parseText(s, new Font(fontName, fontStyle, fontSize), pixelSize);
    }

    private static JLabel jLabel;

    private static FontMetrics getFontMetrics(Font font)
    {
        if (jLabel == null)
            jLabel = new JLabel();

        return jLabel.getFontMetrics(font);
    }

    public static VectorSpace parseText(String s, Font font, double pixelSize)
    {
        FontMetrics metrics = getFontMetrics(font);
        int width = metrics.stringWidth(s);
        int height = metrics.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // Drawing text
        Graphics2D graphic = image.createGraphics();
        graphic.setFont(font);
        graphic.setColor(Color.BLACK);
        graphic.drawString(s, 0, metrics.getMaxAscent());
        graphic.dispose();

        return parseImage(image, pixelSize);
    }

    private VectorParser()
    {}
}