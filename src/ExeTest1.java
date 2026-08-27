import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ExeTest1 {
	
	public static void main(String[] args) throws IOException {

		
		//　配列
		String[] test = new String[5];
		//　連想配列
		Map<String, String> map = new HashMap<>();
		map.put("test","1");



		// Object型の宣言
		//		
		//		Object o1 = new Hero("勇者", 100);
		//		Hero h = new Hero("勇者", 100);
		//		Object o2 = "こんにちは";
		//
		//		System.out.println(o1.toString());

		//　等値と等価
		//		Hero h1 = new Hero("勇者", 100);
		//		Hero h2 = new Hero("勇者", 100);
		//		
		//		if(h1.equals(h2) == true) {
		//			System.out.println("true");
		//		}else {
		//			System.out.println("false");
		//		}

		// 静的フィールド
		//		Hero h1 = new Hero("勇者", 100, 100);
		//		Hero h2 = new Hero("勇者", 100, 200);
		//
		//		System.out.println(h1.getName());
		//		System.out.println(Hero.money);

		// 静的メソッド
		//		Hero.setRandomMoney();
		//		System.out.println(Hero.money);
		//		Hero h1 = new Hero("勇者", 100, Hero.money);
		//		System.out.println(h1.money);

		// 文字列操作
		//		String s1 = "スッキリJava";
		//		String s2 = "Java";
		//		String s3 = "java";
		//		
		//		if(s2.equals(s3)) {
		//			System.out.println("S2とS3は等しい");
		//		}
		//		if(s2.equalsIgnoreCase(s3)) {
		//			System.out.println("S2とS3はケースを区別しなければ等しい");
		//		}
		//		System.out.println("s1の長さは" + s1.length() + "です");
		//		if(s1.isEmpty()) {
		//			System.out.println("S1は空文字です");
		//		}

		//文字列検索
		//		String s1 = "Gensin and GensinStarRail";
		//		
		//		if(s1.contains("Gensin")) {
		//			System.out.println("s1は、Gensinを含んでいます");
		//		}
		//		if(s1.endsWith("Gensin")) {
		//			System.out.println("s1は、Gensinが末尾にあります");
		//		}
		//
		//		System.out.println("s1で最初にGensinが登場する位置は" + s1.indexOf("Gensin"));
		//		System.out.println("s1で最後にGensinが登場する位置は" + s1.lastIndexOf("Gensin"));

		// 文字列の切り抜き
		//		String s1 = "Java Programming";
		//		System.out.println("文字列s1の4文字目以降は"+s1.substring(3));
		//		System.out.println("文字列s1の4 ~ 8文字目以降は"+s1.substring(3, 8));


		//文字列変換
		//		String s1 = "Java Programming";
		//
		//		System.out.println("小文字変換/" + s1.toLowerCase());
		//		System.out.println("大文字変換/" + s1.toUpperCase());
		//
		//		System.out.println("空白削除/" + s1.trim());
		//
		//		System.out.println("文字列置き換え/" + s1.replace("Java", "PHP"));

		//文字列の正規表現
		//		Hero h1 = new Hero("勇者", 100, 100);
		//		h1.setName("A1234567");

		//正規表現の基本文法
		//		String s = "Java";
		//		System.out.println(s.matches("Java"));
		//		System.out.println(s.matches("JavaJava"));
		//		System.out.println(s.matches("java"));
		//		
		//		//任意の文字列
		//		System.out.println("Jova".matches("J.va"));
		//		//直前文字を0回以上繰り返す
		//		System.out.println("Jaaaaaaaava".matches("Ja*va"));
		//		System.out.println("あいうxx019".matches(".*"));
		//		
		//		//指定回数繰り返す,範囲指定
		//		System.out.println("url".matches("[a-z]{3}"));
		//		

		//練習問題
		//		String[] inputs = {
		//				"tanaka@example.com",
		//				"not-an-email",
		//				"suzuki@mail.co.jp",
		//				"12345",
		//				"yamada@test.org"
		//		};
		//
		//		// メールアドレスの正規表現パターン
		//		Pattern pattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		//
		//		for (String input : inputs) {
		//			Matcher matcher =pattern.matcher(input);
		//			if (matcher.matches()) {
		//				System.out.println(input);
		//			}
		//		}
		//		
		//		//がばがば条件式
		//		for (String input : inputs) {
		//			if(input.matches(".*@.*")) {
		//				System.out.println(input);
		//			}
		//		}

		//		//文字列分割
		//		String s = "abc,def:ghi";
		//		String[] words = s.split("[,:]");
		//		
		//		for(String w : words) {
		//			System.out.println(w + "->");
		//		}

		//文字列置換
		//		String s = "abc,def:ghi";
		//		String w = s.replaceFirst("[beh]", "X");
		//		System.out.println(w);

		//		//練習問題2
		//
		//		String text = "連絡先は090-1234-5678です。予備番号は080-9876-5432、固定電話は03-1234-5678です。";
		//
		//		// 090または080,070,060で始まる11桁の携帯番号パターン
		//		Pattern pattern = Pattern.compile("0[6789]0-\\d{4}-\\d{4}");
		//		Matcher matcher = pattern.matcher(text);
		//
		//		while (matcher.find()) {
		//			System.out.println(matcher.group());
		//		}

		//文字列の書式整形
		//		String name = "コーヒー";
		//		double price = 480.0;
		//
		//		String line = String.format("%-10s : %8.2f 円%n", name, price);
		//		System.out.printf("合計金額: %,d 円%n", 12000);
		//		
		//		final String FORMAT = "%-9s %-13s 所持金%,6d %b";
		//		String s = String.format(FORMAT, "taro","農民",100,true);
		//		System.out.print(s);

		// Date型の現在日次の取得
		//		Date now = new Date();
		//		System.out.println(now);
		//		System.out.println(now.getTime());
		//		
		//		Date past = new Date(1694984000000L);
		//		System.out.println(past);

		//Calendarクラスを用いた日時の取得
		//		Calendar c = Calendar.getInstance();
		//		//6つのint値を指定
		//		c.set(2023, 8, 18, 5, 53, 20);
		//		c.set(Calendar.MONTH, 9);
		//		Date d = c.getTime();
		//		System.out.println(d);
		//		
		//		//Dateインスタンスからint値を生成
		//		Date now = new Date();
		//		c.setTime(now);
		//		int y = c.get(Calendar.YEAR);
		//		System.out.println("今年の年は" + y + "年です");

		//SimpleDateFormatの使い方
		//		try {
		//			SimpleDateFormat f = new SimpleDateFormat("y/M/d (E) a K:m:s ");
		//			//SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
		//			Date d = f.parse("2023/09/18 05:53:20");
		//			System.out.println(d);
		//
		//			Date now = new Date();
		//			String s = f.format(now);
		//			System.out.println("現在は" + s + "です");
		//		}catch(Exception e) {
		//		}

		// 練習問題3
		//		// ① 現在の日時を指定フォーマットで表示
		//		Date now = new Date();
		//		SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		//		System.out.println("現在: " + f.format(now));
		//
		//		// ② 30日後の日付を計算
		//		Calendar cal = Calendar.getInstance();
		//		// 30日後を加算
		//		cal.add(Calendar.DAY_OF_MONTH, 30);
		//		Date date = cal.getTime();
		//		System.out.println(date);
		//
		//		// ③ 文字列 → LocalDate に変換
		//		String dateStr = "2000-01-15";
		//		LocalDate parsed = LocalDate.parse(dateStr);
		//		System.out.println("変換後: " + parsed);

		//		//Instant
		//		Instant i1 = Instant.now();
		//		
		// Instantとlong値との相互変換
		//		Instant i2 = Instant.ofEpochMilli(1600705425827L);
		//		long l = i2.toEpochMilli();
		//		
		//		//ZonedDateTimeの生成
		//		ZonedDateTime z1 = ZonedDateTime.now();
		//		ZonedDateTime z2 = ZonedDateTime.of(2023,1,2,3,4,5,6,ZoneId.of("Asia/Tokyo"));
		//		
		//		//InstantとZonedDateTimeとの相互変換
		//		Instant i3 = z2.toInstant();
		//		ZonedDateTime z3 = i3.atZone(ZoneId.of("Europe/London"));
		//		
		//		if(z2.isEqual(z3)) {
		//			System.out.println("等しい");
		//		}

		//		//あいまいな日時を表すクラス
		//		LocalDateTime l1 = LocalDateTime.now();
		//		LocalDateTime l2 = LocalDateTime.of(2024, 1,1,9,5,0,0);
		//		
		//		//LocalDateTimeとZonedDateTimeの相互変換
		//		ZonedDateTime z1 = l2.atZone(ZoneId.of("Europe/London"));
		//		LocalDateTime l3 = z1.toLocalDateTime();
		//		
		//
		//		System.out.println(ZoneId.of("Europe/London"));
		//		System.out.println(ZoneId.of("Asia/Tokyo"));

		//		//1000日後を計算する
		//		LocalDate ld1 = LocalDate.now();
		//		LocalDate ld2 = ld1.plusDays(1000);
		//		String s = ld2.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		//		System.out.println(s);

		//		//Periodクラスの利用
		//		LocalDate d1 = LocalDate.of(2023, 1, 1);
		//		LocalDate d2 = LocalDate.of(2023, 1, 4);
		//		
		//		Period p1 = Period.ofDays(3);
		//		Period p2 = Period.between(d1, d2);
		//		
		//		LocalDate d3 = d2.plus(p2);

		//		//ArrayListについて
		//		ArrayList<Integer> points = new ArrayList<Integer>();
		//		points.add(10);
		//		points.add(20);
		//		points.add(30);
		//		
		//		for (int i : points) {
		//			System.out.println(i);
		//		}


		//Setについて
		//		Set<String> colors = new TreeSet<String>();
		//		colors.add("アカ");
		//		colors.add("アオ");
		//		colors.add("キイ");
		//		
		//		for(String s : colors) {
		//			System.out.println(s + "→");
		//		}
		//
		//		Map<String, Integer> prefs = new HashMap<String,Integer>();
		//		prefs.put("東京", 1261);
		//		prefs.put("京都", 255);
		//		prefs.put("熊本", 182);
		//		for(String key : prefs.keySet()) {
		//			int value = prefs.get(key);
		//			System.out.println(key + "の人口は" + value);
		//		}

		//練習問題１
		//		String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
		//		Map<String, Integer> countMap = new HashMap<>();
		//
		//        for (String word : words) {
		//            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
		//        }
		//        // sort1 TreeMap仕様
		//        Map<String, Integer> sortedMap = new TreeMap<>(Comparator.naturalOrder());
		//        sortedMap.putAll(countMap);
		//        
		//        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
		//            System.out.println(entry.getKey() + ": " + entry.getValue());
		//        }
		//
		//        // sort2 出現回数の多い順にソートするため、entrySetをListに変換
		//        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
		//        entryList.sort((a, b) -> b.getValue() - a.getValue());
		//
		//
		//        for (Map.Entry<String, Integer> entry : entryList) {
		//            System.out.println(entry.getKey() + ": " + entry.getValue());
		//        }
		//
		//↑ポイント:
		//
		//        getOrDefaultを使うと、キーが存在しない場合のnullチェックを省略できる
		//        Map.Entryのリストを作ってsortすることで、値によるソートが可能になる（Mapは本来ソート不可）

		//練習問題２
		//		List<Integer> input = Arrays.asList(3, 1, 2, 3, 4, 1, 5);
		//		
		//		//回答１
		//		List<Integer> result = new ArrayList<>();
		//        Set<Integer> seen = new HashSet<>();
		//
		//        for (Integer num : input) {
		//            if (!seen.contains(num)) {
		//                seen.add(num);
		//                result.add(num);
		//            }
		//        }
		//		System.out.println(result);
		//		
		//		//回答２
		//		System.out.println(new ArrayList<>(new LinkedHashSet<>(input)));
		//
		//		↑ポイント:
		//
		//			HashSetは順序を保証しないが、LinkedHashSetは挿入順を保持するのでこのケースに最適
		//			1問目の解と組み合わせて「Setで重複チェック＋Listで順序保持」というパターンは頻出

		//エラー処理の例
		//		FileWriter fw = new FileWriter("test.txt");
		//		try {
		//			fw.write("test");
		//		}catch(IOException e ) {
		//			System.out.println("エラーが発生");
		//		}finally {
		//			fw.close();
		//		}

		//例外処理の練習問題１
		// System.out.println(divide(10, 2));  // 5
		// System.out.println(divide(10, 0));  // -1

		//例外処理の練習問題2
		//		String[] data = {"10", "abc", "30"};
		//
		//		parseValueDetailed(data, 1); // NumberFormatException
		//		parseValueDetailed(data, 5); // ArrayIndexOutOfBoundsException
		//
		//		parseValueSimple(data, 1);
		//		parseValueSimple(data, 5);
		
		//例外処理の練習問題3
//		BankAccount ba = new BankAccount(1000);
//		try {
//			ba.withdraw(500);
//			ba.withdraw(800);
//		}catch(InsufficientBalanceException e ) {
//			System.out.println("エラー : " + e.getMessage());
//		}
		
		//練習問題4
//		try (FileConnection conn = new FileConnection("sample.txt")) {
//            conn.read();
//        } catch (RuntimeException e) {
//            System.out.println("エラー処理: " + e.getMessage());
//        }
//        System.out.println("プログラムは正常に継続しています");
//
		// ログ出力
//		LogWriterNew log = new LogWriterNew();
//		log.write("このような正解");
		
		System.out.println("End");
	}
	
	
	// 2-A: 複数のcatchブロックで個別に処理
	public static Integer parseValueDetailed(String[] data, int index) {
		try {
			return Integer.parseInt(data[index]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("指定したインデックスは存在しません: " + index);
		} catch (NumberFormatException e) {
			System.out.println("数値に変換できません: " + data[index]);
		}
		return null;
	}
	// 2-B: マルチキャッチでまとめて処理
	public static Integer parseValueSimple(String[] data, int index) {
		try {
			return Integer.parseInt(data[index]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("入力データが不正です: " + e.getMessage());
		}
		return null;
	}

	//例外処理の練習問題１
	public static int divide(int a, int b) {
		int result;
		try {
			result = a / b;
		} catch (ArithmeticException e) {
			System.out.println("0で割ることはできません");
			result = -1;
		} finally {
			System.out.println("処理を終了します");
		}
		return result;
	}
}





