package com.cdc.screen.land;

/**
 * 568,569,617,640,680,720,731,736,740,748,749,757,760,770,771,772,780,800
 * ,812,820,853,896,1080,1024,1112,1280,1294,1336
 */
public enum DimenTypes{
    //适配Android 3.2以上   大部分手机的w值集中在  568-1336之间
    DP_w__568(568),  // values-w568
    DP_w__617(617),
    DP_w__640(640),
    DP_w__680(680),
    DP_w__720(720),
    DP_w__731(731),
    DP_w__736(736),
    DP_w__740(740),
    DP_w__748(748),
    DP_w__760(760),
    DP_w__770(770),
    DP_w__780(780),
    DP_w__800(800),
    DP_w__812(812),
    DP_w__820(820),
    DP_w__853(853),
    DP_w__896(896),
    DP_w__1080(1080),
    DP_w__1112(1112),
    DP_w__1280(1280),
    DP_w__1294(1294),
    DP_w__1336(1336);
    // 想生成多少自己以此类推


    /**
     * 屏幕宽度
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
