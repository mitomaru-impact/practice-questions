public class Dancer extends Character {

	public Dancer(String name, int hp) {
		super(name, hp);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	@Override
	public void attack(Matango m) {
		System.out.println(getName() + "の攻撃");
		System.out.println("敵は3ポイントのダメージ");
		m.hp -= 3;
	}


	public void dance() {
		System.out.println(getName() + "は、情熱的に踊った！");
	}
}
