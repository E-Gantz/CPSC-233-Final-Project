package Logic;

public class PlayerTest {
	
	public void test1() {
		Player p1 = new Player();
		Item i1 = new Item(1, "key");
		p1.addItem(i1);
		if (p1.hasItem(i1)) {
			System.out.println("test1 pass");
		}
		else {
			System.out.println("test1 fail");
		}
	}
	
	public void test2() {
		Player p1 = new Player();
		p1.startHealth(1);
		if (p1.getHealth() == 5) {
			System.out.println("test2 pass");
		}
		else {
			System.out.println("test2 fail");
		}
	}
	
	public void test3() {
		Player p1 = new Player();
		p1.startItems(1);
		Item i1 = new Item(1, "key");
		if (p1.hasItem (i1)) {
			System.out.println("test3 pass");
		}
		else {
			System.out.println("test3 fail");
		}
	}
	
	public void test4() {
		Player p1 = new Player();
		p1.startHealth(1);
		p1.updateHealth(2);
		if (p1.getHealth() == 3) {
			System.out.println("test4 pass");
		}
		else {
			System.out.println("test4 fail");
		}
	}
	
	public void test5() {
		Player p1 = new Player();
		p1.startHealth(1);
		p1.updateHealth(4);
		if (p1.isAlive()) {
			System.out.println("test5 pass");
		}
		else {
			System.out.println("test5 fail");
		}
	}
	
	public void test6() {
		Player p1 = new Player();
		p1.startHealth(1);
		p1.updateHealth(5);
		if (!(p1.isAlive())) {
			System.out.println("test6 pass");
		}
		else {
			System.out.println("test5 fail");
		}
	}
}