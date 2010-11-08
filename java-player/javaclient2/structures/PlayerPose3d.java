/*
 *  Player Java Client 2 - PlayerPose3d.java
 *  Copyright (C) 2006 Radu Bogdan Rusu
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
 * $Id: PlayerPose3d.java 39 2006-02-20 22:44:57Z veedee $
 *
 */
package javaclient2.structures;

/**
 * A pose in space.
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerPose3d {
    private float px;		// X [m]
    private float py;		// Y [m]
    private float pz;		// Z [m]
    private float proll;	// roll  [rad]
    private float ppitch;	// pitch [rad]
    private float pyaw;		// yaw   [rad]
    
    /**
     * 
     * @return X [m]
     */
    public synchronized float getPx () {
    	return this.px;
    }
    
    /**
     * 
     * @param newPx X [m] 
     */
    public synchronized void setPx (float newPx) {
    	this.px = newPx;
    }
    
    /**
     * 
     * @return Y [m] 
     */
    public synchronized float getPy () {
        return this.py;
    }
    
    /**
     * 
     * @param newPy Y [m] 
     */
    public synchronized void setPy (float newPy) {
        this.py = newPy;
    }

    /**
     * 
     * @return Z [m] 
     */
    public synchronized float getPz () {
        return this.pz;
    }
    
    /**
     * 
     * @param newPz Z [m] 
     */
    public synchronized void setPz (float newPz) {
        this.pz = newPz;
    }

    /**
     * 
     * @return roll [rad] 
     */
    public synchronized float getProll () {
        return this.proll;
    }
    
    /**
     * 
     * @param newProll roll [rad] 
     */
    public synchronized void setProll (float newProll) {
        this.proll = newProll;
    }
    
    /**
     * 
     * @return pitch [rad] 
     */
    public synchronized float getPpitch () {
        return this.ppitch;
    }
    
    /**
     * 
     * @param newPpitch pitch [rad] 
     */
    public synchronized void setPpitch (float newPpitch) {
        this.ppitch = newPpitch;
    }

    /**
     * 
     * @return yaw [rad] 
     */
    public synchronized float getPyaw () {
        return this.pyaw;
    }
    
    /**
     * 
     * @param newPyaw yaw [rad] 
     */
    public synchronized void setPyaw (float newPyaw) {
        this.pyaw = newPyaw;
    }
}