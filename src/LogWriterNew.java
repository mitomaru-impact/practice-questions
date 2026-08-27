import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class LogWriterNew{

    public static SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

    public static synchronized void writeLog(String text){
        Calendar calendar = Calendar.getInstance();
        // 出力先
        String OUTPUT_DIR= "C:\\pleiades\\2026-03\\workspace\\Test1\\log";

        //日時取得
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        Date date = calendar.getTime();

        String yearStr = String.format("%04d", year);
        String monthStr = String.format("%02d", month);

        //　ログ出力
        String file_name = OUTPUT_DIR + File.separator + yearStr + "_" + monthStr + ".log";
        File file = new File(file_name);
        FileWriter fw = null;
        String line = sdf.format(date) + "," +  text;
        try{
            fw = new FileWriter(file, true);
            fw.write(line + "\n");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fw != null) {
                try {
                    fw.close();
                }catch(Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    public void write(String text ) {
    	writeLog(text);
    }
    public void Info() {}
    public void Error() {}
    public void Debug() {}
}