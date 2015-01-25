package com.geeksaga.common.util;

/**
 * 참조 : http://doc.ddart.net/hangul/hangulcode.html, http://blog.sumin.us/archives/353
 *
 * 완성형 한글 범위 : 0xAC00 ~ oxD7A3 자음 모음 범위 : 0x3131 ~ 0x318E
 * 
 * @author geeksaga
 * @since 0.1
 */
public class HangleParser
{
    /**
     * 초성 : ㄱ ㄲ ㄴ ㄷ ㄸ ㄹ ㅁ ㅂ ㅃ ㅅ ㅆ ㅇ ㅈ ㅉ ㅊ ㅋ ㅌ ㅍ ㅎ
     */
    private static int[] CHO_SEONG = new int[] { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146,
            0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

    /**
     * 중성 : ㅏ ㅐ ㅑ ㅒ ㅓ ㅔ ㅕ ㅖ ㅗ ㅘ ㅙ ㅚ ㅛ ㅜ ㅝ ㅞ ㅟ ㅠ ㅡ ㅢ ㅣ
     */
    private static int[] JUNG_SEONG = new int[] { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159,
            0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };

    /**
     * 종성 : ㄱ ㄲ ㄳ ㄴ ㄵ ㄶ ㄷ ㄹ ㄺ ㄻ ㄼ ㄽ ㄾ ㄿ ㅀ ㅁ ㅂ ㅄ ㅅ ㅆ ㅇ ㅈ ㅊ ㅋ ㅌ ㅍ ㅎ
     */
    private static int[] JONG_SEONG = new int[] { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b,
            0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d,
            0x314e };

    /**
     * 한글을 초성, 중성, 종성 자소로 분리 한다.
     * 
     * ex) 한글 => ㅎㅏ ㄴ ㄱ ㅡ ㄹ
     * 
     * @param hangle
     * @return
     */
    public static String parse(String hangle)
    {
        if (hangle != null)
        {
            StringBuilder sb = new StringBuilder();
            int choSeongJaso, jungSeongJaso, jongSeongJaso;
            for (int i = 0, length = hangle.length(); i < length; i++)
            {
                int jaso = (int) hangle.charAt(i);

                if (jaso >= 0xAC00 && jaso <= 0xD7A3)
                {
                    jongSeongJaso = jaso - 0xAC00;
                    choSeongJaso = jongSeongJaso / (21 * 28);
                    jongSeongJaso = jongSeongJaso % (21 * 28);
                    jungSeongJaso = jongSeongJaso / 28;
                    jongSeongJaso = jongSeongJaso % 28;

                    sb.append((char) (CHO_SEONG[choSeongJaso]));
                    sb.append((char) (JUNG_SEONG[jungSeongJaso]));

                    if (jongSeongJaso != 0x0000)
                    {
                        sb.append((char) JONG_SEONG[jongSeongJaso]);
                    }
                }
                else
                {
                    sb.append((char) jaso);
                }
            }

            return sb.toString().trim();
        }

        return null;
    }
}