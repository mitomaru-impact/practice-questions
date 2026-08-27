public class Fool extends Character implements Human{

	public Fool(String name, int hp) {
		super(name, hp);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void attack(Matango m) {
		System.out.println(getName() + "は戦わず、遊んでいる");
	}

	public void watch() {
		
	}
	public void hear() {
		
	}
	public void run() {
		
	}

}
