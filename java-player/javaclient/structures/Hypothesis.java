/*
 *  Player Java Client - Hypothesis.java
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
 * $Id: Hypothesis.java 10 2005-05-10 12:10:24Z veedee $
 *
 */
package javaclient.structures;

/**
 * Hypothesis format.<br />
 * Since the robot pose may be ambiguous (i.e., the robot may at any of a number of widely 
 * spaced locations), the localize interface is capable of returning more that one hypothesis.<br />
 * (see the player_localize_hypoth structure from player.h)
 * @author Maxim A. Batalin & Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v1.6.3 - Player 1.6.3 (all interfaces) supported
 *      <li>v1.6.2 - Player 1.6.2 supported, Javadoc documentation, several bugfixes  
 *      <li>v1.5a &nbsp;- Player 1.5 supported (most popular devices)
 * </ul>
 */
public class Hypothesis {
    private int[] mean = new int [3];       /* the mean value of the pose estimate (mm, mm, arc-seconds) */
    
    /* the covariance matrix pose estimate (mm$^2$, arc-seconds$^2$) */
    private long[][] cov = new long[3][3];
    
    private int alpha;                      /* the weight coefficient for linear combination (alpha * 1e6) */
    
    /**
     * 
     * @return the mean value of the pose estimate (mm, mm, arc-seconds)
     */
    public synchronized int[] getMean () {
    	return this.mean;
    }
    
    /**
     * 
     * @param newmean the mean value of the pose estimate (mm, mm, arc-seconds)
     */
    public synchronized void setMean (int[] newmean) {
    	this.mean = newmean;
    }
    
    /**
     * 
     * @return the covariance matrix pose estimate (mm$^2$, arc-seconds$^2$)
     */
    public synchronized long[][] getCov () {
    	return this.cov;
    }
    
    /**
     * 
     * @param newcov the covariance matrix pose estimate (mm$^2$, arc-seconds$^2$)
     */
    public synchronized void setCov (long[][] newcov) {
    	this.cov = newcov;
    }
    
    /**
     * 
     * @return the weight coefficient for linear combination (alpha * 1e6)
     */
    public synchronized int getAlpha () {
    	return this.alpha;
    }
    
    /**
     * 
     * @param newalpha the weight coefficient for linear combination (alpha * 1e6)
     */
    public synchronized void setAlpha (int newalpha) {
    	this.alpha = newalpha;
    }
}