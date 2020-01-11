/**
 * 字典管理管理初始化
 */
var SysDict = {
    id: "SysDictTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SysDict.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键编号', field: 'uuid', visible: true, align: 'center', valign: 'middle'},
            {title: '演员名称', field: 'actorName', visible: true, align: 'center', valign: 'middle'},
            {title: '演员图片位置', field: 'actorImg', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SysDict.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SysDict.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加字典管理
 */
SysDict.openAddSysDict = function () {
    var index = layer.open({
        type: 2,
        title: '添加字典管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sysDict/sysDict_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看字典管理详情
 */
SysDict.openSysDictDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '字典管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sysDict/sysDict_update/' + SysDict.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除字典管理
 */
SysDict.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sysDict/delete", function (data) {
            Feng.success("删除成功!");
            SysDict.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sysDictId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询字典管理列表
 */
SysDict.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SysDict.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SysDict.initColumn();
    var table = new BSTable(SysDict.id, "/sysDict/list", defaultColunms);
    table.setPaginationType("client");
    SysDict.table = table.init();
});
