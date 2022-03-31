package Logic;
import static org.junit.Assert.*;
import org.junit.Test;



public class RoomTest {

	// Testing constructor
		@Test
		public void test_Constructor_1AndTrue() {
			Room r = new Room(1, true);
			assertEquals("Created room with difficulty 1 and nemesis appearance true - testing difficulty", 1, r.getDifficulty());
			assertEquals("Created room with difficulty 1 and nemesis appearance true - testing nemesis appearnce true", true, r.isNemesisRoom());
		}
		
		@Test
		public void test_Constructor_1AndFalse() {
			Room r = new Room(3, false);
			assertEquals("Created room with difficulty 3 and nemesis appearance true - testing difficulty", 3, r.getDifficulty());
			assertEquals("Created room with difficulty 3 and nemesis appearance true - testing nemesis appearnce false", false, r.isNemesisRoom());
		}
		
		// team ask
		@Test
		public void test_Constructor_0AndFalse() {
			Room r = new Room(0, false);
			assertEquals("Created room with difficulty 0 and nemesis appearance true - testing difficulty", 0, r.getDifficulty());
			assertEquals("Created room with difficulty 0 and nemesis appearance false - testing nemesis appearnce false", false, r.isNemesisRoom());
		}
		
		@Test
		public void test_Constructor_NegativeAndFalse() {
			Room r = new Room(-4, true);
			assertEquals("Created room with difficulty -4 and nemesis appearance true - testing difficulty", -4, r.getDifficulty());
			assertEquals("Created room with difficulty -4 and nemesis appearance false - testing nemesis appearnce true", true, r.isNemesisRoom());
		}
		
		@Test
		public void test_Constructor_10AndFalse() {
			Room r = new Room(10, true);
			assertEquals("Created room with difficulty 10 and nemesis appearance true - testing difficulty", 10, r.getDifficulty());
			assertEquals("Created room with difficulty 10 and nemesis appearance true - testing nemesis appearnce true", true, r.isNemesisRoom());
		}
}