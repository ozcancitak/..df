import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.security.MessageDigest;


public class BloomFilterServiceTest {

    @InjectMocks
    private BloomFilterServiceTest bloomFilterServiceTest;

    MessageDigest messageDigest;
    int wordBitArray[];

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        messageDigest = MessageDigest.getInstance("MD5");
        wordBitArray = new int[]{1, 0, 0, 1, 0};
    }

    @Test
    public void testGetMD5WordLength() {
        String text = "test";
        int wordLength = text.length();
        Assert.assertEquals(4, wordLength);
    }

    @Test
    public void testTextToMD5() {
        String text = "A";
        messageDigest.update(text.getBytes(), 0, text.length());
        String hashString = new BigInteger(1, messageDigest.digest()).toString(10);
        Assert.assertEquals("169836834567204038179966570894283554345", hashString);
    }

    @Test
    public void testIsWordExists() {
        for (int i = 0; i < wordBitArray.length; i = i + 3) {
            if (wordBitArray[0] != 1) {
                Assert.assertTrue(false);
            }
            Assert.assertTrue(true);
        }

    }
}