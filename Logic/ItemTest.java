package Logic;

public class ItemTest {
	
	public void test1() {
		Item i1 = new Item(1,"key");
		if (i1.getId() == 1) {
			System.out.println("Test 1 pass");
		}
		else {
			System.out.println("test1 fail");
		}
	}
	
	public void test2() {
		Item i1 = new Item(1,"key");
		if (i1.getName() == "key") {
			System.out.println("Test 2 pass");
		}
		else {
			System.out.println("test2 fail");
		}
	}
	
	public void test3() {
		Item i1 = new Item(1,"key");
		Item i2 = new Item(i1);
		if (i2.getId() == 1) {
			System.out.println("Test 3 pass");
		}
		else {
			System.out.println("test3 fail");
		}
	}
	
	public void test4() {
		Item i1 = new Item(1,"key");
		Item i2 = new Item(i1);
		if (i2.getName() == "key") {
			System.out.println("Test 4 pass");
		}
		else {
			System.out.println("test4 fail");
		}
	}
	
	public void test5() {
		Item i1 = new Item();
		if (i1.getId() == 1 && i1.getName() == "key") {
			System.out.println("test5 pass");
		}
		else if (i1.getId() == 2 && i1.getName() == "key2") {
			System.out.println("test5 pass");
		}
		else if (i1.getId() == 3 && i1.getName() == "key3") {
			System.out.println("test5 pass");
		}
		else if (i1.getId() == 4 && i1.getName() == "key4") {
			System.out.println("test5 pass");
		}
		else if (i1.getId() == 5 && i1.getName() == "key5") {
			System.out.println("test5 pass");
		}
		else {
			System.out.println("test5 fail");
		}
	}
	
}