/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.isel.csee.jchecker2_0.diagram.utils;

/**
 * Class for diagram box
 */
public class Box {
    private int x;
    private int y;
    private int width;
    private int height;
    /**
     * The number of line for box
     */
    public int lineCount;

    /**
     * Constructor for Box class
     */
    public Box() { }

    /**
     * Method for setting x of diagram box
     * @param x x value
     */
    public void setX(int x) { this.x = x; }

    /**
     * Method for return x of diagram box
     * @return x
     */
    public int getX() { return this.x; }

    /**
     * Method for setting y of diagram box
     * @param y y value
     */
    public void setY(int y) { this.y = y; }

    /**
     * Method for return y of diagram box
     * @return y
     */
    public int getY() { return this.y; }

    /**
     * Method for setting width of diagram box
     * @param width width value
     */
    public void setWidth(int width) { this.width = width; }

    /**
     * Method for return width of diagram box
     * @return width
     */
    public int getWidth() { return this.width; }

    /**
     * Method for setting height of diagram box
     * @param height height value
     */
    public void setHeight(int height) { this.height = height; }

    /**
     * Method for return height of diagram box
     * @return height
     */
    public int getHeight() { return this.height; }

    /**
     * Method for setting the number of line in diagram box
     * @param lineCount the number of line
     */
    public void setLineCount(int lineCount) { this.lineCount = lineCount; }

    /**
     * Method for return the number of line in diagram box
     * @return lineCount
     */
    public int getLineCount() { return this.lineCount; }
}
