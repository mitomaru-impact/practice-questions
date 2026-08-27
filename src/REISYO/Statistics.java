package REISYO;

public class Statistics {

    private int total;
    private int ssr;
    private int sr;
    private int normal;

    public void add(GachaResult result) {

        total++;

        if (result.isSSR()) {
            ssr++;
        } else if (result.isSR()) {
            sr++;
        } else {
            normal++;
        }
    }

    public int getTotal() {
        return total;
    }

    public int getSSR() {
        return ssr;
    }

    public int getSR() {
        return sr;
    }

    public int getNormal() {
        return normal;
    }

    public double getSSRRate() {
        return getRate(ssr);
    }

    public double getSRRate() {
        return getRate(sr);
    }

    public double getRareRate() {
        return getRate(ssr + sr);
    }

    private double getRate(int count) {

        if (total == 0) {
            return 0;
        }

        return (double) count / total * 100;
    }
}