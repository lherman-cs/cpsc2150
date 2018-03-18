package cpsc2150.labs.lab4;

/**
 * Created by Lukas Herman (lukash) and Jacob Sonshein (jsonshe) on 2/12/2018.
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionalSpeedsterTest {
  private static final double EPSILON = .00000001;

  @Test
  public void testConstructorDistance() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();

    assertEquals(0.0, d.getTotalDistance(), EPSILON);
  }

  @Test
  public void testConstructorTime() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();

    assertEquals(0.0, d.getTotalTime(), EPSILON);
  }

  @Test
  public void testConstructorPosition() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();

    assertEquals(0.0, d.getNetDistance(), EPSILON);
  }

  // This is a basic test function that test whether or not the addTravel function is working at all
  @Test
  public void testAddTravel_1() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // x = 3 and y = 4
    // Expected totalDistance to be 5 and time to be 1

    d.addTravel(3, 4, 1);
    assertEquals(5.0, d.getTotalDistance(), EPSILON);
    assertEquals(1.0, d.getTotalTime(), EPSILON);
  }

  // This test case is testing whether or not addTravel can handle a large number on the y axis
  @Test
  public void testAddTravel_2() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // x = 3 and y = 256789
    // Expected totalDistance to be 256789.00001752 and time to be 1

    d.addTravel(3, 256789, 1);
    assertEquals(256789.00001752, d.getTotalDistance(), EPSILON);
    assertEquals(1.0, d.getTotalTime(), EPSILON);
  }

  // This test case is testing whether or not addTravel can handle a large number on the x axis and on the y axis
  @Test
  public void testAddTravel_3() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // x = 2876309 and y = 7845467
    // Expected totalDistance to be 8356105.90595703 and time to be 1

    d.addTravel(2876309, 7845467, 1);
    assertEquals(8356105.90595703, d.getTotalDistance(), EPSILON);
    assertEquals(1.0, d.getTotalTime(), EPSILON);
  }

  // This test case is testing whether or not addTravel can handle a large numner for the time added
  @Test
  public void testAddTravel_4() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // x = 3 and y = 4
    // Expected totalDistance to be 5 and time to be 8756304

    d.addTravel(3, 4, 8756304);
    assertEquals(5.0, d.getTotalDistance(), EPSILON);
    assertEquals(8756304.0, d.getTotalTime(), EPSILON);
  }

  // This test case is testing whether or not addTravel can handle a large number on the x axis, on the y axis, and for the time
  @Test
  public void testAddTravel_5() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // x = 5673459 and y = 4387832
    // and then x = 3 and y = 4
    // Expected totalDistance to be 7172257.55306204 and time to be 3459184

    d.addTravel(5673459, 4387832, 3459183);
    d.addTravel(3, 4, 1);
    assertEquals(7172257.55306204, d.getTotalDistance(), EPSILON);
    assertEquals(3459184, d.getTotalTime(), EPSILON);
  }

  // This test case is to simply test if the getNetDistance function is working at all by using basic values
  @Test
  public void testGetNetDistance_1() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // Expected net distance is 26.40075756

    d.addTravel(3, 4, 1);
    d.addTravel(5, 8, 3);
    d.addTravel(8, 9, 4);
    assertEquals(26.40075756, d.getNetDistance(), EPSILON);
  }

  // This test case is to test whether or not getNetDistance works with various sizes of distance being travelled
  @Test
  public void testGetNetDistance_2() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // Expected net distance is 9967.91512805,

    d.addTravel(3485, 8634, 5);
    d.addTravel(4, 5, 2);
    d.addTravel(745, 385, 3);
    assertEquals(9967.91512805, d.getNetDistance(), EPSILON);
  }

  // This test case is to test if getNetDistance will work with various sizes of large numbers being travelled
  @Test
  public void testGetNetDistance_3() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // Expected netDistance is 93771461.8496137

    d.addTravel(3485, 8634, 5);
    d.addTravel(444555, 888834, 8);
    d.addTravel(6667389, 8884356, 4);
    d.addTravel(77777777, 30045687, 6);
    assertEquals(93771461.8496137, d.getNetDistance(), EPSILON);
  }

  // This test case is to test if getNetDistance will work when the same distance is travelled over and over again
  @Test
  public void testGetNetDistance_4() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // Expected netDistance is 38.41874542

    d.addTravel(4, 5, 5);
    d.addTravel(4, 5, 5);
    d.addTravel(4, 5, 5);
    d.addTravel(4, 5, 5);
    d.addTravel(4, 5, 5);
    d.addTravel(4, 5, 5);
    assertEquals(38.41874542, d.getNetDistance(), EPSILON);
  }

  // This test case is to test if getNetDistance will work when only one order of large numbers is being travelled
  @Test
  public void testGetNetDistance_5() {
    // Calls the constructor
    DirectionalSpeedster d = new DirectionalSpeedster();
    // Expected netDistance is 2618631406.6960626 

    d.addTravel(888647934, 678453865, 3);
    d.addTravel(718343782, 347963439, 2);
    d.addTravel(300278394, 767893475, 3);
    assertEquals(2618631406.6960626, d.getNetDistance(), EPSILON);
  }

  @Test
  public void testGetAverageVelocityWithMovingToXOnly() {
    // Test with moving to the x axis only. Check if the calculation works
    // if y is 0
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(5.0, 0, 1);

    assertEquals(5.0, d.getAverageVelocity(), EPSILON);
  }

  @Test
  public void testGetAverageVelocityWithMovingToYOnly() {
    // Test with moving to the y axis only. Check if the calculation works
    // if x is 0
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(0, -10.0, 1);

    assertEquals(10.0, d.getAverageVelocity(), EPSILON);
  }

  @Test
  public void testGetAverageVelocityWithMovingBackAndForward() {
    // Test with moving back and forward. Check if the average velocity
    // is not being confused with net velocity
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(6, 8, 5);
    d.addTravel(-3, -4, 5);

    assertEquals(1.5, d.getAverageVelocity(), EPSILON);
  }

  @Test
  public void testGetAverageVelocityWithBigNumbers() {
    // Test if there's a precision error with big numbers
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(3000000000000.0, 4000000000000.0, 1);

    assertEquals(5000000000000.0, d.getAverageVelocity(), EPSILON);
  }

  @Test
  public void testGetAverageVelocityWithSmallNumbers() {
    // Test if there's a precision error with small numbers
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(0.000000000003, 0.000000000004, 1);

    assertEquals(0.000000000005, d.getAverageVelocity(), EPSILON);
  }

  @Test
  public void testGetNetVelocityWithMovingToXOnly() {
    // Test with moving to the x axis only. Check if the calculation works
    // if y is 0
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(5.0, 0, 1);

    assertEquals(5.0, d.getNetVelocity(), EPSILON);
  }

  @Test
  public void testGetNetVelocityWithMovingToYOnly() {
    // Test with moving to the y axis only. Check if the calculation works
    // if x is 0
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(0, -10.0, 1);

    assertEquals(10.0, d.getNetVelocity(), EPSILON);
  }

  @Test
  public void testGetNetVelocityWithMovingBackAndForward() {
    // Test with moving back and forward. Check if the net velocity
    // is not being confused with average velocity
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(6, 8, 5);
    d.addTravel(-3, -4, 5);

    assertEquals(0.5, d.getNetVelocity(), EPSILON);
  }

  @Test
  public void testGetNetVelocityWithBigNumbers() {
    // Test if there's a precision error with big numbers
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(3000000000000.0, 4000000000000.0, 1);

    assertEquals(5000000000000.0, d.getNetVelocity(), EPSILON);
  }

  @Test
  public void testGetNetVelocityWithSmallNumbers() {
    // Test if there's a precision error with small numbers
    DirectionalSpeedster d = new DirectionalSpeedster();
    d.addTravel(0.000000000003, 0.000000000004, 1);

    assertEquals(0.000000000005, d.getNetVelocity(), EPSILON);
  }
}
