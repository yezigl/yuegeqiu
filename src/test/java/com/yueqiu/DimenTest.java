/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月21日
 */
public class DimenTest {

    static Pattern pattern = Pattern.compile(">(\\d+)");
    
    static float scale = 0.888888888888888f;
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/yezi/Workspace/yezigl/www/ball/src/test/java/com/yueqiu/origin.xml")));
            String line = null;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int dp = Integer.parseInt(matcher.group(1));
                    line = matcher.replaceAll(">" + Math.round(dp * scale + 0.5));
                }
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
