package 練習問題5;
import java.io.IOException;

public class Problem5 {
	static LogWriterNew log = new LogWriterNew();
	// 最下層：例外発生元
    static void repository() throws IOException {
        throw new IOException("データベース接続に失敗しました");
    }

    // 中間層：ログを記録してから再スロー
    static void service() throws IOException {
        try {
            repository();
        } catch (IOException e) {
        	log.write("[LOG] サービス層で例外を検知: " + e.getMessage());
            throw e; // 呼び出し元へ伝播させる
        }
    }

    // 最上層：ユーザー向けにハンドリング
    static void controller() {
        try {
            service();
        } catch (IOException e) {
            System.out.println("処理中にエラーが発生しました。しばらくしてから再度お試しください。");
        }
    }

    public static void main(String[] args) {
        controller();
    }
}
//
//### 実行結果
//```
//[LOG] サービス層で例外を検知: データベース接続に失敗しました
//処理中にエラーが発生しました。しばらくしてから再度お試しください。
//```
//
//### 解説
//- `repository()` で発生した例外は、`service()` で一度捕捉されますが、
//  `throw e;` によって再度スローされ、呼び出し元の `controller()` まで伝播します。
//- このように「途中でログだけ記録して、処理自体は上位に委ねる」パターンはよく使われます。
//- 内部的な原因（DB接続失敗）をそのままユーザーに見せず、
//  分かりやすいメッセージに変換して表示している点もポイントです。
