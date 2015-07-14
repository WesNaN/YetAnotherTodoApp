package test;

import javafx.scene.paint.Color;
import model.GitObjects.Repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * RepositoryClass tests
 */
public class RepositoryTest {
    private Repository repository;

    /**
     * is run before all tests
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        repository = new Repository("name", "description", Color.AQUA, LocalDate.now(), LocalDate.now().plusDays(1));
    }

    /**
     * is R after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    /**
     * Tests for setting DescriptionLength
     * @throws Exception
     */
    @Test
    public void testSetDBMaxDescriptionlength() throws Exception {
        short number = 32;
        repository.setDBMaxDescriptionlength(number);
        assertEquals(number, repository.getDBMaxDescriptionlength());
    }

    /**
     * Test for setting a decriptionString that is to long
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxDescriptionlength() throws IllegalArgumentException {
        String tolong = "dansklfnasdkfxxkoqpgnrognrt3q4ngonlganprmgaafgmkapævfindøngrjegør";
        repository.setDBMaxDescriptionlength((short) 23);
        repository.setDescription(tolong);
    }
}