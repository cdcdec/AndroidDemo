package com.cdc.screen.port;

public enum DimenTypes{
    //适配Android 3.2以上   dp值 手机/pad 宽 320,360,375,392,411,414,480,720,768,810,834,900,1024
  /*  DP_sw__300(300),  // values-sw300
    DP_sw__310(310),
    DP_sw__320(320),
    DP_sw__330(330),
    DP_sw__340(340),
    DP_sw__350(350),
    DP_sw__360(360),
    DP_sw__370(370),
    DP_sw__380(380),
    DP_sw__390(390),
    DP_sw__410(410),
    DP_sw__420(420),
    DP_sw__430(430),
    DP_sw__440(440),
    DP_sw__450(450),
    DP_sw__460(460),
    DP_sw__470(470),
    DP_sw__480(480),
    DP_sw__490(490),

    DP_sw__400(400);*/
    DP_sw__300(300),
    DP_sw__320(320),  // values-sw300
    DP_sw__360(360),
    DP_sw__375(375),
    DP_sw__392(392),
    DP_sw__411(411),
    DP_sw__414(414),
    DP_sw__480(480),
    DP_sw__600(600),
    DP_sw__640(640),
    DP_sw__700(700),
    DP_sw__720(720),
    DP_sw__768(768),
    DP_sw__810(810),
    DP_sw__834(834),
    DP_sw__900(900);
    // 想生成多少自己以此类推


    /**
     * 屏幕最小宽度
     */
    private int swWidthDp;




    DimenTypes(int swWidthDp) {

        this.swWidthDp = swWidthDp;
    }

    public int getSwWidthDp() {
        return swWidthDp;
    }

    public void setSwWidthDp(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }
}
