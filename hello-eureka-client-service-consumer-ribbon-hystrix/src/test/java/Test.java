/** 牛顿迭代法求算术平方根
 * @author lee
 * @date 2020/4/15 16:25
 */
public class Test {
    public static void main(String[] args) {
        double c = 16;
        double t = c;
        double e = 1e-15;
        while (t - c / t > e * t) {
            t = (c / t + t) / 2.0;
        }

        System.out.println(t);
    }
}
