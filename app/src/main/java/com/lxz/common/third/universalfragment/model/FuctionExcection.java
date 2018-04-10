package com.lxz.common.third.universalfragment.model;

import com.lxz.common.third.commontools.L;

/**
 * Created by lxz on 2017/11/6 0006.
 */
public class FuctionExcection extends Throwable {
    public FuctionExcection(String s) {
        L.e(s);
    }
}
