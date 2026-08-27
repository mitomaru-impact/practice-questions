public abstract class Character {

	private String name;
	private int hp;

	public Character(String name,int hp) {
		this.name = name;
		this.hp = hp;
	}


	public void run() {
		System.out.println(this.name + "は、逃げ出した。");
	}

	public abstract void attack(Matango m);

	private void die () {
		System.out.println(this.name + "は死亡した");
		System.out.println("GameOver");
	}
	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		try {
			if(!isValidPlayerName(name)) {
				throw new IllegalArgumentException("チェックに引っ掛かる");
			}
//			if(name.length() <= 1) {
//				throw new IllegalArgumentException("名前が短すぎる");
//			}
//			if(name .length() >= 8) {
//				throw new IllegalArgumentException("名前が長すぎる");
//			}

			this.name = name;
		}catch(Exception e) {
			System.out.println("エラーだよ");
		}
	}
	
	public boolean isValidPlayerName(String name) {
		if(name.length() != 8) {
			return false;
		}
		char first = name.charAt(0);
		if(!(first >= 'A' && first <= 'Z')) {
			return false;
		}
		for(int i =1; i < 8; i++) {
			char c = name.charAt(i);
			if(!(
					(c >= 'A' && c <= 'Z') || 
					(c >= '0' && c <= '9') 
				)) {
				return false;
			}
		}
		return true;
	}
}
