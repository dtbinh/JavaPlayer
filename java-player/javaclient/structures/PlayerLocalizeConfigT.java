/*
 *  Player Java Client - PlayerLocalizeConfigT.java
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
 * $Id: PlayerLocalizeConfigT.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient.structures;

/**
 * To retrieve the configuration, set the subtype to PLAYER_LOCALIZE_GET_CONFIG_REQ and leave 
 * the other fields empty. The server will reply with the following configuration fields filled
 * in.  To change the current configuration, set the subtype to PLAYER_LOCALIZE_SET_CONFIG_REQ 
 * and fill the configuration fields. <br />
 * (see the player_localize_config structure from player.h)
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class PlayerLocalizeConfigT {
    private int numParticles;        /* maximum number of particles (for drivers using particle filters) */
   
    /**
     * 
     * @return the maximum number of particles (for drivers using particle filters)
     */
    public synchronized int getNumParticles () {
    	return this.numParticles;
    }
    
    /**
     * 
     * @param newnumparticles maximum number of particles (for drivers using particle filters)
     */
    public synchronized void setNumParticles (int newnumparticles) {
    	this.numParticles = newnumparticles;
    }
}