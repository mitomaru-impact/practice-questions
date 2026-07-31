class FileConnection implements AutoCloseable {

    public FileConnection(String fileName) {
        System.out.println(fileName + " への接続をオープンしました");
    }

    public void read() {
        System.out.println("データを読み込み中...");
        throw new RuntimeException("読み込み中に予期しないエラーが発生しました");
    }

    @Override
    public void close() {
        System.out.println("接続をクローズしました");
    }
}