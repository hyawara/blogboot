/** 
 * 格式化时间
 * 2018-07-03 14:55:17
 * -----------------------------------------------------------------------------
 * 对Date的扩展，将 Date 转化为指定格式的String 
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)可以用 1-2 个占位符 
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
 * eg: 
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */
Date.prototype.pattern = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份         
        "d+": this.getDate(), //日         
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时         
        "H+": this.getHours(), //小时         
        "m+": this.getMinutes(), //分         
        "s+": this.getSeconds(), //秒         
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度         
        "S": this.getMilliseconds() //毫秒         
    };
    var week = {
        "0": "日", // 日        
        "1": "一", // 一     
        "2": "二", // 二        
        "3": "三", // 三
        "4": "四", // 四        
        "5": "五", // 五        
        "6": "六" // 六    
        /*  
            "0" : "/u65e5", // 日        
            "1" : "/u4e00", // 一     
            "2" : "/u4e8c", // 二        
            "3" : "/u4e09", // 三
            "4" : "/u56db", // 四        
            "5" : "/u4e94", // 五        
            "6" : "/u516d"  // 六      
        */
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        /*        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
         */
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

/**
 * @author Hy
 * @description 
 *     js字符工具
 *     目前功能如下：
 *     1. 验证变量是否"为空"           strUtil.isBank(str)
 *     2. 验证变量是否"不为空"         strUtil.isNonBank(str)
 *     3. 去除传入的字符串"所有"空格    strUtil.allTrim(str)
 *     4. 去除传入的字符串"左右两边"空格 strUtil.LRTrim(str)
 *     5. 去除传入的字符串"左边"空格    strUtil.LTrim(str)
 *     6. 去除传入的字符串"右边"空格    strUtil.RTrim(str)
 *     7. 验证"手机号码"              strUtil.isPoneAvailable(str)
 *     8. 验证"电话号码"              strUtil.isTelAvailable(str)
 *     9. 验证"身份证号码"            strUtil.isCardNo(str)
 *     10.验证"邮箱"                 strUtil.isEmail(str)
 * @date 2018-06-26 09:27:21
 */
var strUtil = {
    /**
     * @description 
     *     为"空"
     * @date 2018-06-26 04:53:58
     */
    isBlank: function(data) {
        return typeof(data) == "undefined" // var a;
            ||
            data == null // var a = null;
            ||
            data == "" // var a = "";
            ||
            data.length == 0 // var a = [];
            ||
            JSON.stringify(data) == "{}"; // var a = {};
    },
    /**
     * @description 
     *     不为"空"
     * @date 2018-06-26 04:56:04
     */
    isNonBlank: function(data) {
        return !this.isBlank(data);
    },
    /**
     * 去除"所有"空格
     */
    allTrim: function(str) {
        str = str.replace(/\s+/g, "");
        return str;
    },
    /**
     * 去除"左右两边"的空格
     */
    LRTrim: function(str) {
        str = str.replace(/^\s+|\s+$/g, "");
        return str;
    },
    /**
     * 去除"左边"空格
     */
    LTrim: function(str) {
        str = str.replace(/^\s*/, "");
        return str;
    },
    /**
     * 去除"右边"空格
     */
    RTrim: function(str) {
        str = str.replace(/(\s*$)/g, "");
        return str;
    },
    /**
     * 是否为手机号码
     */
    isPoneAvailable: function(phone) {
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        /*
         * 如果为空返回false
         */
        if (this.isBlank(phone)) {
            return false;
        }
        if (!myreg.test(phone)) {
            return false;
        }
        return true;
        
    },
    /**
     * 是否为电话号码
     */
    isTelAvailable: function(tel) {
        var myreg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
        /*
         * 如果为空返回false
         */
        if (this.isBlank(tel)) {
            return false;
        }
        if (!myreg.test(tel)) {
            return false;
        }
        return true;
    },
    /**
     * 验证身份证号码
     */
    isCardNo: function(card) {
        /*
         * 如果为空返回false
         */
        if (this.isBlank(card)) {
            return false;
        }
        /* 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X */ 
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(card) === false) {
            return false;
        }
        return true;
    },
    /**
     * 验证邮箱
     */
    isEmail: function(maile) {
        /* 为空返回false */
        if (this.isBlank(maile)){
            return false;
        }
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
        /* 正则验证不通过，格式不对 */
        if (!reg.test(maile)) { 
            return false;　　 
        }
        return true;　　
    }
}

/**
 * @author Hy
 * @description 
 *     date工具，用于获取“年月日 时分秒”等时间格式
 *     目前功能如下：
 *     1. 返回"年-月-日"                                           dateUtil.yMd();
 *     2. 返回"年-月-日 时:分:秒"(12小时制)                          dateUtil.yMdh();
 *     3. 返回"年-月-日 时:分:秒"(24小时制)                          dateUtil.yMdH();
 *     4. 返回"年-月-日 时:分:秒 周*" (由用户自定义"周*显示在何处")     dateUtil.hasWeek("yyyy-MM-dd HH:mm:ss EE")
 *     5. 返回"年-月-日 时:分:秒 星期*" (由用户自定义"星期*"显示在何处") dateUtil.hasWeek("yyyy-MM-dd HH:mm:ss EEE")
 * @date 2018-07-03 09:57:27
 */
var dateUtil = {
    /**
     * 年月日
     */
    yMd: function() {
        return new Date().pattern("yyyy-MM-dd");
    },
    /**
     * 年月日 时分秒(12小时制)
     */
    yMdh: function() {
        return new Date().pattern("yyyy-MM-dd hh:mm:ss");
    },
    /**
     * 年月日 时分秒(24小时制)
     */
    yMdH: function() {
        return new Date().pattern("yyyy-MM-dd HH:mm:ss");
    },
    /**
     * 带有"周"或者"星期"的日期, 用户可自定义位置
     * 格式：
     * 1. yyyy-MM-dd EE HH:mm:ss (EE-->周*)
     * 2. yyyy-MM-dd EEE HH:mm:ss (EEE--->星期*)
     * "E"可以放在前后任意位置。
     */
    hasWeek: function(str) {
        return new Date().pattern(str);
    }

}