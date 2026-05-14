package gh2;

import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

public class GuitarHero {

    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/'";

    public static void main(String[] args) {
        // 1. 创建 37 根吉他弦
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];

        // 2. 初始化频率：从 261.63 Hz (C4) 开始，每次乘以 2^(1/12)
        double frequency = 261.63;  // C4
        for (int i = 0; i < KEYBOARD.length(); i++) {
            strings[i] = new GuitarString(frequency);
            frequency *= Math.pow(2, 1.0 / 12.0);  // 乘 2 的 1/12 次方，得到下一个半音
        }

        // 3. 主循环
        while (true) {
            // 检查是否有按键被按下
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            // 计算当前时刻所有弦的采样值之和
            double sample = 0;
            for (GuitarString s : strings) {
                s.tic();
                sample += s.sample();
            }

            // 播放采样值
            StdAudio.play(sample);
        }
    }
}
