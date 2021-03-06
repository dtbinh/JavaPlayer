/*
 *  Player Java Client - PlayerLaserGeomT.java
 *  Copyright (C) 2002-2005 Maxim A. Batalin & Radu Bogdan Rusu
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
 * $Id: PlayerLaserGeomT.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient.structures;

/**
 * The laser geometry (position and size) can be queried using the PLAYER_LASER_GET_GEOM 
 * request. The request and reply packets have the same format. <br />
 * (see the player_laser_geom structure from player.h)
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class PlayerLaserGeomT {
    private short pose[] = new short[2];    /* laser pose, in robot cs (mm, mm, degrees) */
    private short size[] = new short[3];    /* laser dimensions (mm, mm) */
    
    /**
     * 
     * @return the laser pose, in robot cs (mm, mm, degrees)
     */
    public synchronized short[] getPose () {
        return this.pose;
    }

    /**
     * 
     * @param newpose laser pose, in robot cs (mm, mm, degrees)
     */
    public synchronized void setPose (short[] newpose) {
        this.pose = newpose;
    }
    
    /**
     * 
     * @return the laser dimensions (mm, mm) 
     */
    public synchronized short[] getSize () {
        return this.size;
    }
    
    /**
     * 
     * @param newsize laser dimensions (mm, mm) 
     */
    public synchronized void setSize (short[] newsize) {
        this.pose = newsize;
    }

}