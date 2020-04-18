package cn.sitedev.demo.service.impl;

import cn.sitedev.demo.service.IModifyService;

/**
 * 增删改业务
 */
public class ModifyService implements IModifyService {
    @Override
    public String add(String name, String addr) {
        return "ModifyService add: name = " + name + ", addr = " + addr;
    }

    @Override
    public String edit(Integer id, String name) {
        return "ModifyService edit: id = " + id + ", name = " + name;
    }

    @Override
    public String remove(Integer id) {
        return "ModifyService remove: id = " + id;
    }
}
