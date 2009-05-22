package solution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jacob Evans, Bodaniel Jeanes
 */

public class ContainerLabelTest {
    private ContainerLabel labelOne;

    @Before
    public void setUp() throws LabelException {
        labelOne = new ContainerLabel(0, 1, 1, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(new Integer(0), labelOne.getKind());
        assertEquals(new Integer(1), labelOne.getIdentifier());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIllegalArgumentExceptionWhenFirstArgumentIsNull()
            throws LabelException {
        new ContainerLabel(null, 1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIllegalArgumentExceptionWhenSecondArgumentIsNull()
            throws LabelException {
        new ContainerLabel(1, null, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIllegalArgumentExceptionWhenThirdArgumentIsNull()
            throws LabelException {
        new ContainerLabel(1, 1, null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsIllegalArgumentExceptionWhenFourthArgumentIsNull()
            throws LabelException {
        new ContainerLabel(1, 1, 1, null);
    }

    // if the length of the kind or identifier numbers is not positive; or if
    // either the kind or identifier numbers is too long to fit into the number
    // of available digits

    @Test(expected = LabelException.class)
    public void testConstructorThrowsLabelExceptionIfKindLengthIsGreaterThanThree()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(9999, 4, 0, 1);
    }

    @Test(expected = LabelException.class)
    public void testConstructorThrowsLabelExceptionIfIdentifierLengthIsGreaterThanFive()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(0, 1, 999999, 6);
    }

    @Test
    public void testConstructorDoesNotThrowLabelExceptionIfKindLengthIsThree()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(999, 3, 0, 1);
    }

    @Test
    public void testConstructorDoesNotThrowLabelExceptionIfIdentifierLengthIsFive()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(0, 1, 99999, 5);
    }

    @Test(expected = LabelException.class)
    public void testConstructorThrowsLabelExceptionIfKindLengthIsZero()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(0, 0, 0, 1);
    }

    @Test(expected = LabelException.class)
    public void testConstructorThrowsLabelExceptionIfIdentifierLengthIsZero()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(0, 1, 0, 0);
    }

    @Test(expected = LabelException.class)
    public void testConstructorThrowsLabelExceptionIfKindLengthIsNegative()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(0, -1, 0, 1);
    }

    @Test(expected = LabelException.class)
    public void testConstructorThrowsLabelExceptionIfIdentifierLengthIsNegative()
            throws IllegalArgumentException, LabelException {
        new ContainerLabel(0, 1, 0, -1);
    }

    public void testToString() {
        assertEquals("00000001", labelOne.toString());
    }

    @Test
    public void testToStringTwo() throws LabelException {
        ContainerLabel labelTwo = new ContainerLabel(12, 2, 432, 3);
        assertEquals("01200432", labelTwo.toString());
    }

    @Test(expected = LabelException.class)
    public void testIllegalConstructorNegativeValues() throws LabelException {
        new ContainerLabel(-1, -1, -1, -1);
    }

    @Test(expected = LabelException.class)
    public void testIllegalConstructorBadRange() throws LabelException {
        new ContainerLabel(0, 7, 1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatchesThrowsIllegalArgumentExceptionWhenGivenNullParameter() {
        labelOne.matches(null);
    }

    @Test
    public void testMatchesReturnsTrueForSameObject() {
        assertTrue(labelOne.matches(labelOne));
    }

    @Test
    public void testMatchesReturnsTrueForDifferentObjectWithSameConstructorParameters()
            throws LabelException {
        ContainerLabel labelTwo = new ContainerLabel(0, 1, 1, 1);

        assertTrue(labelOne.matches(labelTwo));
        assertTrue(labelTwo.matches(labelOne));
    }

    @Test
    public void testMatchesReturnsFalseForDifferentObjectWithDifferentParameters()
            throws LabelException {
        ContainerLabel labelTwo = new ContainerLabel(0, 1, 5, 1);

        assertFalse(labelOne.matches(labelTwo));
        assertFalse(labelTwo.matches(labelOne));
    }
}
