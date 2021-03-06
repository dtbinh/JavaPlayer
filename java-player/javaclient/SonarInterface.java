/*
 *  Player Java Client - SonarInterface.java
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
 * $Id: SonarInterface.java 17 2005-05-23 13:13:22Z veedee $
 *
 */
package javaclient;

import javaclient.structures.PlayerSonarGeomT;
/**
 * The sonar interface provides access to a collection of fixed range sensors, such as a sonar
 * array. This interface accepts no commands.
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 * 	<li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 * 	<li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class SonarInterface extends PlayerDevice {
	
    private static final boolean isDebugging = PlayerClient.isDebugging;

    /* fixed range-finder */
    private final short PLAYER_SONAR_CODE = PlayerClient.PLAYER_SONAR_CODE;
    
    /* request types */
    protected final short PLAYER_SONAR_GET_GEOM_REQ = 1;
    protected final short PLAYER_SONAR_POWER_REQ    = 2;
	
    /* the player message types (see player.h) */
    private final short PLAYER_MSGTYPE_REQ = PlayerClient.PLAYER_MSGTYPE_REQ;
	
    /** maximum number of sonar samples */
    public static final short PLAYER_SONAR_MAX_SAMPLES = 64;
	
    private int range[]      = new int[PLAYER_SONAR_MAX_SAMPLES];
    private int samplesCount = PLAYER_SONAR_MAX_SAMPLES;
	
    /* object containing player_sonar_geom in case of a threaded call */
    private PlayerSonarGeomT psgt;
    private boolean          readyPSGT = false;

    /**
     * Constructor for SonarInterface.
     * @param pc a reference to the PlayerClient object
     * @param indexOfDevice the index of the device
     */
    public SonarInterface (PlayerClient pc, short indexOfDevice) {
        super(pc);
        device    = PLAYER_SONAR_CODE;
        index     = indexOfDevice;
    }
	
    /**
     * Read the sonar values.
     */
    public synchronized void readData () {
        readHeader ();
        try {
            samplesCount = is.readUnsignedShort ();   /* the number of valid range readings */
            for (int i = 0; i < PLAYER_SONAR_MAX_SAMPLES; i++)
                range[i] = is.readUnsignedShort ();   /* the range readings */
        } catch (Exception e) {
            System.err.println ("[Sonar] : Error when reading payload: " + e.toString ());
        }
    }
	
    /**
     * Returns the sonar array data values up to PLAYER_SONAR_MAX_SAMPLES.
     * @return an array filled with the sonar values
     */
    public synchronized int[] getRanges       () { return range;        }
    
    /**
     * Returns the number of ultrasonic sensors specified in the Player world file
     * (the number of valid range readings).
     * @return the number of ultrasonic sensors specified in the Player world file as an integer
     */
    public synchronized int   getSamplesCount () { return samplesCount; }
	
    /**
     * Configuration request: Query geometry.
     * <br><br>
     * See the player_sonar_geom structure from player.h
     */
    public void queryGeometry () {
        try {
            sendHeader (PLAYER_MSGTYPE_REQ, 1);		/* 1 byte payload */
            os.writeByte (PLAYER_SONAR_GET_GEOM_REQ);
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[Sonar] : Couldn't send PLAYER_SONAR_GET_GEOM_REQ command: " + 
                    e.toString ());
        }
    }
	
    /**
     * Configuration request: Sonar power. ("available only on real robots" (TM))
     * <br><br>
     * See the player_sonar_power_config structure from player.h
     * @param value turn power off (0) or on (>0)
     */
    public void setSonarPower (int value) {
        try {
            sendHeader (PLAYER_MSGTYPE_REQ, 2);     /* 2 bytes payload */
            os.writeByte (PLAYER_SONAR_POWER_REQ);
            os.writeByte ((byte)value);             /* turn power off (0) or on (>0) */
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[Sonar] : Couldn't send PLAYER_SONAR_POWER_REQ command: " + 
                    e.toString ());
        }
    }
	
    /**
     * Handle acknowledgement response messages (threaded mode).
     * @param size size of the payload
     */
    public void handleResponse (int size) {
        if (size == 0) {
            if (isDebugging)
            	System.err.println ("[Sonar][Debug] : Unexpected response of size 0!");
            return;
        }
        try {
            /* each reply begins with a uint8_t subtype field */
            byte subtype = is.readByte ();
            switch (subtype) {
                case PLAYER_SONAR_GET_GEOM_REQ: {
                    psgt = new PlayerSonarGeomT ();
                    
                    psgt.setPoseCount (is.readShort ());
                    short sonarPoses[][] = new short[PLAYER_SONAR_MAX_SAMPLES][3];
                    for (int i = 0; i < PLAYER_SONAR_MAX_SAMPLES; i++) {
                        sonarPoses[i][0] = is.readShort ();		/* X pos in mm */
                        sonarPoses[i][1] = is.readShort ();		/* Y pos in mm */
                        sonarPoses[i][2] = is.readShort ();		/* Yaw in degrees */
                    }
                    psgt.setPoses(sonarPoses);
                    
                    readyPSGT = true;
                    break;
                }
                case PLAYER_SONAR_POWER_REQ: {
                 	break;
                }
                default:{
                    System.err.println ("[Sonar] : Unexpected response " + subtype + 
                            " of size = " + size);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println ("[Sonar] : Error when reading payload " + e.toString ());
        }
    }
    
    /**
     * Check if geometry data is available.
     * @return true if ready, false if not ready 
     */
    public boolean isPSGTReady () {
        if (readyPSGT) {
            readyPSGT = false;
            return true;
        }
        return false;
    }
    
    /**
     * Get the geometry data.
     * @return an object of type PlayerSonarGeomT containing the required geometry data 
     */
    public PlayerSonarGeomT getPSGT () { return psgt; }
}

