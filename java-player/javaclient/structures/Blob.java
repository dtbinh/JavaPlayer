/*
 *  Player Java Client - Blob.java
 *  Copyright (C) 2003-2005 Maxim A. Batalin & Radu Bogdan Rusu
 *
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * $Id: Blob.java 10 2005-05-10 12:10:24Z veedee $
 *
 */

package javaclient.structures;

/**
 * Structure describing a single blob. <br />
 * (see the player_blobfinder_blob structure from player.h)
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class Blob {
    private short id;                           /* blob ID */

    /* a descriptive color for the blob (useful for gui's). The color is stored as packed 32-bit 
     * RGB, i.e., 0x00RRGGBB. 
     */
    private int   color;
    
    private int   area;                         /* the blob area (pixels) */
    private short x, y;                         /* the blob centroid (image coords) */
    private short left, right, top, bottom;     /* bounding box for the blob (image coords) */
    private short range;                        /* range (mm) to the blob center */
    
    
    /**
     * 
     * @return blob ID
     */
    public synchronized short  getID () {
        return this.id;
    }
    
    /**
     * 
     * @param newid blob ID
     */
    public synchronized void setID (short newid) {
        this.id = newid;
    }
    
    /**
     * 
     * @return a descriptive color for the blob (useful for gui's). The color is stored as packed 32-bit 
     * RGB, i.e., 0x00RRGGBB.
     */
    public synchronized int getColor () {
        return this.color;
    }
    
    /**
     * 
     * @param newcolor a descriptive color for the blob (useful for gui's). The color must be packed 
     * as a 32-bit RGB, i.e., 0x00RRGGBB.
     */
    public synchronized void setColor (int newcolor) {
        this.color = newcolor;
    }
    
    /**
     * 
     * @return the blob area (pixels)
     */
    public synchronized int getArea() {
    	return this.area;
    }
    
    /**
     * 
     * @param newarea the blob area (pixels)
     */
    public synchronized void setArea(int newarea) {
        this.area = newarea;
    }
    
    /**
     * 
     * @return the blob centroid (image coords, X)
     */
    public synchronized short getX () {
        return this.x;
    }
    
    /**
     * 
     * @param newx the blob centroid (image coords, X)
     */
    public synchronized void setX (short newx) {
        this.x = newx;
    }
    
    /**
     * 
     * @return the blob centroid (image coords, Y)
     */
    public synchronized short getY () {
        return this.y;
    }
    
    /**
     * 
     * @param newy the blob centroid (image coords, Y)
     */
    public synchronized void setY (short newy) {
        this.y = newy;
    }
    
    /**
     * 
     * @return bounding box for the blob (image coords, Left)
     */
    public synchronized short getLeft () {
        return this.left;
    }

    /**
     * 
     * @param newleft bounding box for the blob (image coords, Left)
     */
    public synchronized void setLeft (short newleft) {
        this.left = newleft;
    }

    /**
     * 
     * @return bounding box for the blob (image coords, Right)
     */
    public synchronized short getRight () {
        return this.right;
    }
    
    /**
     * 
     * @param newright bounding box for the blob (image coords, Right)
     */
    public synchronized void setRight (short newright) {
        this.right = newright;
    }
    
    /**
     * 
     * @return bounding box for the blob (image coords, Top)
     */
    public synchronized short getTop () {
        return this.top;
    }
    
    /**
     * 
     * @param newtop bounding box for the blob (image coords, Top)
     */
    public synchronized void setTop (short newtop) {
        this.top = newtop;
    }
    
    /**
     * 
     * @return bounding box for the blob (image coords, Bottom)
     */
    public synchronized short getBottom () {
        return this.bottom;
    }
    
    /**
     * 
     * @param newbottom bounding box for the blob (image coords, Bottom)
     */
    public synchronized void setBottom (short newbottom) {
        this.bottom = newbottom;
    }

    /**
     * 
     * @return range (mm) to the blob center
     */
    public synchronized short getRange () {
    	return this.range;
    }
    
    /**
     * 
     * @param newrange range (mm) to the blob center
     */
    public synchronized void setRange (short newrange) {
        this.range = newrange;
    }
}
