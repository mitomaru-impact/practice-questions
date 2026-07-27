
public class Wizard extends Character{
	int mp;
	
	//コンストラクタ
	public Wizard(String name, int hp) {
		super(name,hp);
		
		this.mp = 100;
	}

	@Override
	public void attack(Matango m) {
		System.out.println(getName() + "の攻撃!");
		System.out.println("敵に3ポイントのダメージ");
		m.hp -= 3;
	}
	
	public void fireball (Matango m) {
		System.out.println(getName() + "火の玉を放った!");
		System.out.println("敵に20ポイントのダメージ");
		m.hp -= 20;
		this.mp -= 5;
	}
}
