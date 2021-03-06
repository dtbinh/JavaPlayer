/*
 *  Player Java Client - PlayerBumperDefineT.java
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
 * $Id: PlayerBumperDefineT.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient.structures;

/**
 * The geometry of a single bumper. <br />
 * (see the player_bumper_define structure from player.h)
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class PlayerBumperDefineT {                /* the geometry of a single buffer */
    private short xOffset, yOffset, thOffset;     /* the local pose of a single bumper in mm */
    private short length;                         /* length of the sensor in mm */
    private short radius;                         /* radius of curvature in mm - zero for straight lines */
    
    /**
     * 
     * @return the local pose of a single bumper in mm (X)
     */
    public synchronized short getXOffset () {
        return this.xOffset;
    }
    
    /**
     * 
     * @param newXOffset the local pose of a single bumper in mm (X)
     */
    public synchronized void setXOffset (short newXOffset) {
        this.xOffset = newXOffset;
    }

    /**
     * 
     * @return the local pose of a single bumper in mm (Y)
     */
    public synchronized short getYOffset () {
        return this.yOffset;
    }
    
    /**
     * 
     * @param newYOffset the local pose of a single bumper in mm (Y)
     */
    public synchronized void setYOffset (short newYOffset) {
        this.yOffset = newYOffset;
    }

    /**
     * 
     * @return the local pose of a single bumper in mm (Theta)
     */
    public synchronized short getThOffset () {
        return this.thOffset;
    }
    
    /**
     * 
     * @param newThOffset the local pose of a single bumper in mm (Theta)
     */
    public synchronized void setThOffset (short newThOffset) {
        this.thOffset = newThOffset;
    }

    /**
     * 
     * @return length of the sensor in mm
     */
    public synchronized short getLength () {
        return this.length;
    }
    
    /**
     * 
     * @param newLength length of the sensor in mm
     */
    public synchronized void setLength (short newLength) {
        this.length = newLength;
    }

    /**
     * 
     * @return radius of curvature in mm - zero for straight lines
     */
    public synchronized short getRadius () {
        return this.radius;
    }
    
    /**
     * 
     * @param newRadius radius of curvature in mm - zero for straight lines
     */
    public synchronized void setRadius (short newRadius) {
        this.radius = newRadius;
    }
}