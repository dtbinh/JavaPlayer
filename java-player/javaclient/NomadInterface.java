/*
 *  Player Java Client - NomadInterface.java
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
 * $Id: NomadInterface.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient;

/**
 * The nomad interface affords control of Nomadics Nomad robots and relatives.<br /><br />
 * Warning: some of the command and data variables are specified in native Nomad units; they 
 * don't follow Player's conventions. This will be changed once someone figures out exactly 
 * what the Nomad units are. 
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 * </ul>
 */
public class NomadInterface extends PlayerDevice {

    /* nomad robot */
    private final short PLAYER_NOMAD_CODE = PlayerClient.PLAYER_NOMAD_CODE;

    /* the player message types (see player.h) */
    private static final short PLAYER_MSGTYPE_CMD = PlayerClient.PLAYER_MSGTYPE_CMD;

    public static final short PLAYER_NOMAD_SONAR_COUNT  = 16;
    public static final short PLAYER_NOMAD_BUMPER_COUNT = 16;
    public static final short PLAYER_NOMAD_IR_COUNT     = 16;

    private int x, y;           /* X and Y position, in mm */
    private int a;              /* heading, in degrees */
    private int vel_trans;      /* translation velocity (in native Nomad units) */
    private int vel_steer;      /* steering velocity (in native Nomad units) */
    private int vel_turret;     /* turret rotation velocity (in native Nomad units) */
    /* sonar range sensors: range in mm */
    private int[] sonar  = new int[PLAYER_NOMAD_SONAR_COUNT];
    /* infrared range sensors: range in mm */
    private int[] ir     = new int[PLAYER_NOMAD_IR_COUNT];
    /* bump sensors: zero - no contact, non-zero - contact */
    private int[] bumper = new int[PLAYER_NOMAD_BUMPER_COUNT];
    
    /**
     * Constructor for NomadInterface.
     * @param pc a reference to the PlayerClient object
     * @param indexOfDevice the index of the device
     */
    public NomadInterface (PlayerClient pc, short indexOfDevice) {
        super(pc);
        device    = PLAYER_NOMAD_CODE;
        index     = indexOfDevice;
    }

    /**
     * Read the NOMAD data.<br />
     * The nomad data packet provides a pose estimate, sonar and infrared range 
     * readings, and bumper contact readings.
     */
    public synchronized void readData () {
        readHeader ();
        try {
            x          = is.readInt ();     /* X and Y position, in mm */
            y          = is.readInt ();
            a          = is.readInt ();     /* heading, in degrees */
            vel_trans  = is.readInt ();     /* translation velocity (in native Nomad units) */
            vel_steer  = is.readInt ();     /* steering velocity (in native Nomad units) */
            vel_turret = is.readInt ();     /* turret rotation velocity (in native Nomad units) */
            for (int i = 0; i < PLAYER_NOMAD_SONAR_COUNT; i++)
                sonar[i] = is.readUnsignedShort (); /* sonar range sensors: range in mm */
            for (int i = 0; i < PLAYER_NOMAD_IR_COUNT; i++)
                ir[i] = is.readUnsignedShort ();    /* infrared range sensors: range in mm */ 
            for (int i = 0; i < PLAYER_NOMAD_BUMPER_COUNT; i++)
                bumper[i] = is.readUnsignedShort ();    /* bump sensors: 0 - no contact */
        } catch (Exception e) {
            System.err.println ("[Nomad] : Error when reading payload: " + e.toString ());
        }
    }
    
    /**
     * Return the X position in mm.
     * @return the X position in mm as an integer
     */
    public synchronized int getX () { return this.x; }

    /**
     * Return the Y position in mm.
     * @return the Y position in mm as an integer
     */
    public synchronized int getY () { return this.y; }

    /**
     * Return the heading in degrees.
     * @return the heading in degrees as an integer
     */
    public synchronized int getA () { return this.a; }

    /**
     * Return the translation velocity (in native Nomad units).
     * @return the translation velocity (in native Nomad units) as an integer
     */
    public synchronized int getVelTrans () { return this.vel_trans; }
    
    /**
     * Return the steering velocity (in native Nomad units).
     * @return the steering velocity (in native Nomad units) as an integer
     */
    public synchronized int getVelSteer () { return this.vel_steer; }
    
    /**
     * Return the turret rotation velocity (in native Nomad units)
     * @return the turret rotation velocity (in native Nomad units) as an integer
     */
    public synchronized int getVelTurret () { return this.vel_turret; }
    
    /**
     * Return the sonar range sensors range in mm.
     * @return the sonar range sensors range in mm as an array of integers
     */
    public synchronized int[] getSonars () { return this.sonar; }

    /**
     * Return the infrared range sensors range in mm.
     * @return the infrared range sensors range in mm as an array of integers
     */
    public synchronized int[] getIRs () { return this.ir; }

    /**
     * Return the bump sensors status.
     * @return the bump sensors status (zero - no contact, non-zero - contact) as 
     * an array of integers
     */
    public synchronized int[] getBumpers () { return this.bumper; }

    /**
     * The Nomad command packet lets you set independent velocities for translation, 
     * steering and turret rotation. These are specified in native Nomad units.<br /><br />
     * Todo:<br />
     * This should change in future to match normal Player style (mm/sec), once someone 
     * figures out exactly what the Nomad units are.
     * @param newveltrans translation velocity (in native Nomad units)
     * @param newvelsteer steering velocity (in native Nomad units)
     * @param newvelturret turret rotation velocity (in native Nomad units)
     */
    public void setVelocities (int newveltrans, int newvelsteer, int newvelturret) {
        try {
            sendHeader (PLAYER_MSGTYPE_CMD, 12);   /* 12 byte payload */
            os.writeInt (newveltrans);             /* translation velocity */
            os.writeInt (newvelsteer);             /* steering velocity */ 
            os.writeInt (newvelturret);            /* turret rotation velocity */ 
            os.flush ();
        } catch (Exception e) {
            System.err.println ("[Nomad] : Couldn't send set new goals command: " +
                    e.toString ());
        }
    }
 
}
