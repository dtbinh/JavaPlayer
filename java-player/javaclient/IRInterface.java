/*
 *  Player Java Client - IRInterface.java
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
 * $Id: IRInterface.java 21 2005-06-01 19:05:51Z veedee $
 *
 */
package javaclient;

import javaclient.structures.PlayerIRPoseReqT;

/**
 * The ir interface provides access to an array of infrared (IR) range sensors. This interface 
 * accepts no commands. 
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class IRInterface extends PlayerDevice {

    private static final boolean isDebugging = PlayerClient.isDebugging;

    private final short PLAYER_IR_CODE = PlayerClient.PLAYER_IR_CODE; /* IR array */
    
    /** maximum number of IR samples */
    public static final short PLAYER_IR_MAX_SAMPLES = 32;
    
    /* request types */
    protected final short PLAYER_IR_POSE_REQ  = 1;
    protected final short PLAYER_IR_POWER_REQ = 2;
    
    /* the player message types (see player.h) */
    private final short PLAYER_MSGTYPE_REQ = PlayerClient.PLAYER_MSGTYPE_REQ;
    
    private int rangeCount;                                     /* number of samples */
    private int[] voltages = new int[PLAYER_IR_MAX_SAMPLES];    /* voltages (units?) */
    private int[] ranges   = new int[PLAYER_IR_MAX_SAMPLES];    /* ranges (mm) */
    
    /* object containing player_ir_pose_req in case of a threaded call */
    private PlayerIRPoseReqT piprt;
    private boolean          readyPIPRT = false;

    /**
     * Constructor for IRInterface.
     * @param pc a reference to the PlayerClient object
     * @param indexOfDevice the index of the device
     */
    public IRInterface (PlayerClient pc, short indexOfDevice) {
        super(pc);
        device    = PLAYER_IR_CODE;
        index     = indexOfDevice;
    }
    
    /**
     * Read the IR values.
     */
    public synchronized void readData () {
        readHeader ();
        try {
            rangeCount = is.readUnsignedShort ();       /* the number of valid IR readings */
            for (int i = 0; i < PLAYER_IR_MAX_SAMPLES; i++)
                voltages[i] = is.readUnsignedShort ();  /* voltages (units?) */
            for (int i = 0; i < PLAYER_IR_MAX_SAMPLES; i++)
                ranges[i] = is.readUnsignedShort ();    /* ranges (mm) */
        } catch (Exception e) {
            System.err.println ("[IR] : Error when reading payload: " + e.toString ());
        }
    }
    
    /**
     * Returns the IR array data voltages up to PLAYER_IR_MAX_SAMPLES.
     * @return an array filled with the IR voltages
     */
    public synchronized int[] getVoltages   () { return voltages;   }
    
    /**
     * Returns the IR array data ranges up to PLAYER_IR_MAX_SAMPLES.
     * @return an array filled with the IR ranges
     */
    public synchronized int[] getRanges     () { return ranges;     }

    /**
     * Returns the number of IR sensors specified in the Player world file
     * (the number of valid range readings).
     * @return the number of IR sensors specified in the Player world file as an integer
     */
    public synchronized int   getRangeCount () { return rangeCount; }

    /**
     * Configuration request: Query pose.
     * <br><br>
     * See the player_ir_pose structure from player.h
     */
    public void queryPose () {
        try {
            sendHeader (PLAYER_MSGTYPE_REQ, 1);     /* 1 byte payload */
            os.writeByte (PLAYER_IR_POSE_REQ);
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[IR] : Couldn't send PLAYER_IR_POSE_REQ command: " + 
                    e.toString ());
        }
    }
    
    /**
     * Configuration request: IR power.
     * @param value 0 for power off, 1 for power on
     */
    public void setIRPower (int value) {
        try {
            sendHeader (PLAYER_MSGTYPE_REQ, 2);     /* 2 bytes payload */
            os.writeByte (PLAYER_IR_POWER_REQ);
            os.writeByte ((byte)value);             /* 0 for power off, 1 for power on */
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[IR] : Couldn't send PLAYER_IR_POWER_REQ command: " + 
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
                System.err.println ("[IR][Debug] : Unexpected response of size 0!");
            return;
        }
        try {
            /* each reply begins with a uint8_t subtype field */
            byte subtype = is.readByte ();
            switch (subtype) {
                case PLAYER_IR_POSE_REQ: {
                    piprt = new PlayerIRPoseReqT ();
                    
                    piprt.setPoseCount (is.readShort ());
                    short IRPoses[][] = new short[PLAYER_IR_MAX_SAMPLES][3];
                    for (int i = 0; i < PLAYER_IR_MAX_SAMPLES; i++) {
                        IRPoses[i][0] = is.readShort ();     /* X pos in mm */
                        IRPoses[i][1] = is.readShort ();     /* Y pos in mm */
                        IRPoses[i][2] = is.readShort ();     /* Yaw in degrees */
                    }
                    piprt.setPoses(IRPoses);
                    
                    readyPIPRT = true;
                    break;
                }
                case PLAYER_IR_POWER_REQ: {
                    break;
                }
                default:{
                    System.err.println ("[IR] : Unexpected response " + subtype + 
                            " of size = " + size);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println ("[IR] : Error when reading payload " + e.toString ());
        }
    }

    /**
     * Check if geometry data is available.
     * @return true if ready, false if not ready 
     */
    public boolean isPIPRTReady () {
        if (readyPIPRT) {
            readyPIPRT = false;
            return true;
        }
        return false;
    }
    
    /**
     * Get the geometry data.
     * @return an object of type PlayerIRPoseReqT containing the required geometry data 
     */
    public PlayerIRPoseReqT getPIPRT () { return piprt; }

}
