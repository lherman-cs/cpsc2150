package cpsc2150.labs.lab4;

/**
 * Created by Lukas Herman (lukash) and Jacob Sonshein (jsonshe) on 2/12/2018.
 */

/**
 * @invariant totalDistance >= 0.0
 *            and totalTime >= 0.0
 *            and netDistance >= 0.0
 *            and averageVelocity >= 0.0
 *            and netVelocity >= 0.0
 *            and curX within double range
 *            and curY within double range
 */
public class DirectionalSpeedster {
    private double totalDistance;
    private double totalTime;
    private double netDistance;
    private double averageVelocity;
    private double netVelocity;

    private double curX, curY;

    /**
     * Construct a DirectionalSpeedster instance and set all the properties to zero.
     *
     * @ensures totalDistance = 0.0
     *          and totalTime = 0.0
     *          and netDistance = 0.0
     *          and averageVelocity = 0.0
     *          and netVelocity = 0.0
     *          and curX = 0.0
     *          and curY = 0.0
     */
    DirectionalSpeedster(){
        totalDistance = 0.0;
        totalTime = 0.0;
        netDistance = 0.0;
        averageVelocity = 0.0;
        netVelocity = 0.0;

        curX = 0.0;
        curY = 0.0;
    }

    /**
     * This method takes in the change in x and y position and the time taken to
     * complete the travel
     *
     * @param xChange is the distance in x coordinate from the previous x coordinate
     * @param yChange is the distance in y coordinate from the previous y coordinate
     * @param time is the time taken to complete the travel
     * @requires xChange and yChange within double range
     *           and time > 0.0
     * @ensures totalDistance += sqrt(xChange ^ 2 + yChange ^ 2)
     *          and totalTime += time
     *          and curX += xChange
     *          and curY += yChange
     *          and netDistance = sqrt(curX ^ 2 + curY ^ 2)
     *          and averageVelocity = totalDistance / totalTime
     *          and netVelocity = netDistance / totalTime
     */
    public void addTravel(double xChange, double yChange, double time){
        totalDistance += Math.sqrt(Math.pow(xChange, 2.0) + Math.pow(yChange, 2.0));
        totalTime += time;

        curX += xChange;
        curY += yChange;
        netDistance = Math.sqrt(Math.pow(curX, 2.0) + Math.pow(curY, 2.0));

        averageVelocity = totalDistance / totalTime;
        netVelocity = netDistance / totalTime;
    }


    /**
     * This method returns the total distance travelled
     *
     * @ensures all the properties will stay the same
     *          and returns totalDistance
     * @return totalDistance
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * This method returns the total time elapsed during travel
     *
     * @ensures all the properties will stay the same
     *          and returns totalTime
     * @return totalTime
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * This method returns the net distance travelled
     *
     * @ensures all the properties will stay the same
     *          and returns netDistance
     * @return netDistance
     */
    public double getNetDistance() {
        return netDistance;
    }

    /**
     * This method returns the average velocity of all travel
     *
     * @ensures all the properties will stay the same
     *          and returns averageVelocity
     * @return averageVelocity
     */
    public double getAverageVelocity() {
        return averageVelocity;
    }

    /**
     * This method returns the net velocity for all travel
     *
     * @ensures all the properties will stay the same
     *          and returns netVelocity
     * @return netVelocity
     */
    public double getNetVelocity() {
        return netVelocity;
    }
}
