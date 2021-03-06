/*
 *  Player Java Client - AbstractPositionDevice.java
 *  Copyright (C) 2005 Radu Bogdan Rusu
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
 * $Id: AbstractPositionDevice.java 23 2005-06-14 09:44:32Z veedee $
 *
 */
package javaclient;

/**
 * Abstract class for all Player Position* interfaces. Used for 
 * PositionControl and HeadingControl.
 * @author Radu Bogdan Rusu
 */
public abstract class AbstractPositionDevice extends PlayerDevice {

    /**
     * Abstract constructor for each AbstractPositionDevice.
     * @param plc a reference to the PlayerClient object
     */
    public AbstractPositionDevice (PlayerClient plc) {
        super (plc);
    }
    
    public abstract int  getX     ();
    public abstract int  getY     ();
    public abstract int  getYaw   ();
    public abstract void setSpeed (int speed, int turnrate);
}
