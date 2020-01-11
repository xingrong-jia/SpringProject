package com.stylefeng.guns.admin.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.admin.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.admin.modular.system.model.SysDict;
import com.stylefeng.guns.admin.modular.system.service.ISysDictService;

/**
 * 字典管理控制器
 *
 * @author fengshuonan
 * @Date 2020-01-08 09:28:39
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseController {

    private String PREFIX = "/system/sysDict/";

    @Autowired
    private ISysDictService sysDictService;

    /**
     * 跳转到字典管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysDict.html";
    }

    /**
     * 跳转到添加字典管理
     */
    @RequestMapping("/sysDict_add")
    public String sysDictAdd() {
        return PREFIX + "sysDict_add.html";
    }

    /**
     * 跳转到修改字典管理
     */
    @RequestMapping("/sysDict_update/{sysDictId}")
    public String sysDictUpdate(@PathVariable Integer sysDictId, Model model) {
        SysDict sysDict = sysDictService.selectById(sysDictId);
        model.addAttribute("item",sysDict);
        LogObjectHolder.me().set(sysDict);
        return PREFIX + "sysDict_edit.html";
    }

    /**
     * 获取字典管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return sysDictService.selectList(null);
    }

    /**
     * 新增字典管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SysDict sysDict) {
        sysDictService.insert(sysDict);
        return SUCCESS_TIP;
    }

    /**
     * 删除字典管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer sysDictId) {
        sysDictService.deleteById(sysDictId);
        return SUCCESS_TIP;
    }

    /**
     * 修改字典管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SysDict sysDict) {
        sysDictService.updateById(sysDict);
        return SUCCESS_TIP;
    }

    /**
     * 字典管理详情
     */
    @RequestMapping(value = "/detail/{sysDictId}")
    @ResponseBody
    public Object detail(@PathVariable("sysDictId") Integer sysDictId) {
        return sysDictService.selectById(sysDictId);
    }
}
