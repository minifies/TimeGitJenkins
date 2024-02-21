package time;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() 
	{
		int seconds = Time.getTotalSeconds("12:05:05");
		assertTrue("The seconds were not calculated properly", seconds==43505);
	}
	
	 @Test
	 public void testGetTotalSecondsBad() 
	 {
	    assertThrows(StringIndexOutOfBoundsException.class, 
	    ()-> {Time.getTotalSeconds("10:00");});
	 }

	@Test
	void testGetTotalSecondsBoundary() 
	{
		int seconds = Time.getTotalSeconds("00:00:00");
		assertTrue("The seconds were not calculated properly", seconds==0);
	}
	
	@Test
    void testGetTotalMinutesGood() {
        assertEquals(59, Time.getTotalMinutes("00:59:00"));
        assertEquals(0, Time.getTotalMinutes("00:00:00"));
    }
	
	@Test
	public void testGetTotalMinutesBad() {
	    assertThrows(StringIndexOutOfBoundsException.class, 
	                 () -> Time.getTotalMinutes("10:"));
	}

	
	@Test
    void testGetTotalMinutesBoundary() {
        assertEquals(0, Time.getTotalMinutes("00:00:00"));
        assertEquals(59, Time.getTotalMinutes("23:59:59"));
    }
	
	@Test
    void testGetTotalHoursGood() {
        assertEquals(23, Time.getTotalHours("23:00:00"));
        assertEquals(1, Time.getTotalHours("01:02:03"));
    }
	
	@Test
    void testGetTotalHoursBoundary() {
        assertEquals(0, Time.getTotalHours("00:00:00"));
        assertEquals(23, Time.getTotalHours("23:59:59"));
	}
	
	@Test
	public void testGetTotalHoursBad() {
	    assertThrows(StringIndexOutOfBoundsException.class, 
	                 () -> Time.getTotalHours("1"));
	}
	
    @Test
    void testGetSecondsGood() {
        assertEquals(30, Time.getSeconds("01:02:30"));
        assertEquals(59, Time.getSeconds("00:00:59"));
    }

    @Test
    public void testGetSecondsBad() {
        assertThrows(StringIndexOutOfBoundsException.class, 
                     () -> Time.getSeconds("10:00:"));
    }

    @Test
    void testGetSecondsBoundary() {
        assertEquals(0, Time.getSeconds("00:00:00"));
        assertEquals(59, Time.getSeconds("23:59:59"));
    }
    
    @Test
    void testGetMillisecondsGood() {
        assertEquals(500, Time.getMilliseconds("01:23:45.500"));
        assertEquals(0, Time.getMilliseconds("00:00:00.000"));
        assertEquals(999, Time.getMilliseconds("23:59:59.999"));
    }
    
    @Test
    void testGetMillisecondsBad() {
        assertThrows(NumberFormatException.class, () -> Time.getMilliseconds("01:23:45")); // No milliseconds part
        assertThrows(NumberFormatException.class, () -> Time.getMilliseconds("01:23")); // Incomplete time
        assertThrows(NumberFormatException.class, () -> Time.getMilliseconds("invalid input")); // Completely invalid format
    }
    
    @Test
    void testGetMillisecondsBoundary() {
        assertEquals(1, Time.getMilliseconds("00:00:00.001"));
        assertEquals(999, Time.getMilliseconds("23:59:59.999")); // Max milliseconds
        assertThrows(NumberFormatException.class, () -> Time.getMilliseconds("23:59:59.1000")); // More than 3 digits in milliseconds
    }
    
	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalHours(String candidate) 
	{
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours ==5);
	}
}

