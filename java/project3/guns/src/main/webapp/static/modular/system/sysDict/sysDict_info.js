/**
 * 初始化字典管理详情对话框
 */
var SysDictInfoDlg = {
    sysDictInfoData : {}
};

/**
 * 清除数据
 */
SysDictInfoDlg.clearData = function() {
    this.sysDictInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysDictInfoDlg.set = function(key, val) {
    this.sysDictInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysDictInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SysDictInfoDlg.close = function() {
    parent.layer.close(window.parent.SysDict.layerIndex);
}

/**
 * 收集数据
 */
SysDictInfoDlg.collectData = function() {
    this
    .set('uuid')
    .set('actorName')
    .set('actorImg');
}

/**
 * 提交添加
 */
SysDictInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sysDict/add", function(data){
        Feng.success("添加成功!");
        window.parent.SysDict.table.refresh();
        SysDictInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysDictInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SysDictInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sysDict/update", function(data){
        Feng.success("修改成功!");
        window.parent.SysDict.table.refresh();
        SysDictInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysDictInfoData);
    ajax.start();
}

$(function() {

});
