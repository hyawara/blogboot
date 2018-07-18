/**
 * @author Hy
 * @description 
 *     依赖于jQuery的弹出窗插件
 * @date 2018-06-26 09:26:30
 * -------------------------
 * @author Hy
 * @description
 *     无
 * @2018-07-02 09:55:34
 */
(function() {
    var alertMsg = {
        _boxId: "#alertBox",
        /* 
         *   弹窗标题
         */
        title: {
            error: "错误",
            info: "提示",
            warn: "警示",
            correct: "成功",
            confirm: "确认提示"
        },
        /* 
         * 按钮
         */
        buttonMsg: {
            ok: "确定",
            yes: "是",
            no: "否",
            cancel: "取消"
        },
        /* 
         * 弹窗html
         */
        _boxHtml: '<div id="alertBox" class="alert-body" style="display: block;">\
                    <div class="alert-title">#title#</div>\
                    <div class="alert-content">#contents#</div>\
                    <div class="alert-button">#buttonContent#</div>\
                   </div>',
        /* 
         * 按钮html
         */
        _buttonHtml: '<button rel="#callBack#" onClick="alertMsg.close()">#buttonMsg#</button>',
        /**
         * @description 弹出窗口方法
         * @date 2018-06-26 05:26:19
         */
        _open: function(type, msg, buttons) {
            /* 
             * 删除已有的弹出窗, 确当前页面没有弹出窗
             */
            $(this._boxId).remove();
            var butsHtml = "";
            /* 
             * 根据传入的按钮个数，添加按钮和按钮名称;
             * 如果按钮初始化了方法，比如okCall就为按钮加上rel=callBack，
             * 在后面根据这个rel=callBack的个数来实现
             * 各自按钮在被点击后应该执行的方法(比如初始化了okCall)
             */
            if (buttons) {
                for (var i = 0; i < buttons.length; i++) {
                    var butRel = buttons[i].call ? "callBack" : "";
                    var buts = this._buttonHtml
                        .replace("#buttonMsg#", buttons[i].name)
                        .replace("#callBack#", butRel);
                    butsHtml += buts;
                }
            };
            /* 
             * animate显示弹出窗
             */
            var alertHtml = this._boxHtml
                .replace("#title#", type)
                .replace("#contents#", msg)
                .replace("#buttonContent#", butsHtml);
            $(alertHtml).appendTo("body").css({
                /* 
                 * 利用css将弹出窗隐藏在可见区域之外
                 */
                top: -$(this._boxId).height() + "px"
            }).animate({
                /* 
                 * 显示弹出窗
                 */
                top: "0px"
            }, 500);
            /* 
             * 利用jQuery为按钮绑定okCall或者cancellCall方法
             * 先找到在前面代码中替换后的rel=callBack
             */
            var callFnc = $(this._boxId).find("[rel=callBack]");
            /* 
             * 遍历"按钮"
             */
            for (var i = 0; i < buttons.length; i++) {
                /* 
                 * 如果当前的按钮有绑定方法
                 * 为按钮绑定okCall或者okCancel方法
                 */
                if (buttons[i].call) {
                    callFnc.eq(i).click(buttons[i].call);
                }
            }
        },
        close: function() {
            $(this._boxId).animate({
                /* 
                 * 隐藏在界面可见范围之外
                 */
                top: -$(this._boxId).height() + "px"
            }, 500, function() {
                /* 
                 * 隐藏后删除这个弹出窗
                 */
                $(this).remove();
            });
        },
        error: function(msg) {
            this.alert(this.title.error, msg);
        },
        correct: function(msg) {
            this.alert(this.title.correct, msg);
        },
        warn: function(msg) {
            this.alert(this.title.warn, msg);
        },
        info: function(msg) {
            this.alert(this.title.info, msg);
        },
        alert: function(type, msg, options) {
            var op = {
                butName: this.buttonMsg.ok,
                okCall: null
            };
            /* 
             * 将options与op合并
             */
            $.extend(op, options);
            var buttons = [{
                name: op.butName,
                call: op.okCall
            }];
            this._open(type, msg, buttons);
        },
        confirm: function(msg, options) {
            var op = {
                okName: this.buttonMsg.ok,
                okCall: null,
                cancelName: this.buttonMsg.cancel,
                cancelCall: null
            };
            /* 
             * 将options与op合并
             */
            $.extend(op, options);
            var buttons = [{
                name: op.okName,
                call: op.okCall
            }, {
                name: op.cancelName,
                call: op.cancelCall
            }];
            this._open(this.title.confirm, msg, buttons);
        }
    };
    /*
     * 将alertMsg对象添加到window对象中。
     */
    window.alertMsg = alertMsg;
})(jQuery)
