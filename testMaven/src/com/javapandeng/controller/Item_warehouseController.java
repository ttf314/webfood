package com.javapandeng.controller;

import com.javapandeng.base.BaseController;
import com.javapandeng.po.Charts;
import com.javapandeng.po.Item_warehouse;
import com.javapandeng.po.Item_warehouse;
import com.javapandeng.po.Meals;
import com.javapandeng.service.Item_warehouseService;
import com.javapandeng.service.Item_warehouseService;
import com.javapandeng.service.MealsService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目c层
 */
@Controller
@RequestMapping("/Item_warehouse2")
public class Item_warehouseController extends BaseController {
    @Autowired
    private Item_warehouseService Item_warehouseService;
    /**
     * 分页查询类目列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model,Item_warehouse Item_warehouse){
        String sql = "select * from item_warehouse where isDelete = 0 and pid is null order by id";
        /*Pager<Item_warehouse> pagers = Item_warehouseService.findBySqlRerturnEntity(sql);*/
        Pager<Item_warehouse> pagers = Item_warehouseService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",Item_warehouse);
        return "Item_warehouse/Item_warehouse";
    }

    /**
     * 转向到新增一级类目页面
     */
    @RequestMapping(value = "/add")
    public String add(){
        return "Item_warehouse/add";
    }

    /**
     * 新增一级类目保存功能
     */
    @RequestMapping("/exAdd")
    public String exAdd(Item_warehouse Item_warehouse){
        Item_warehouse.setIsDelete(0);
        Item_warehouseService.insert(Item_warehouse);
        return "redirect:/Item_warehouse/findBySql.action";
    }

    /**
     * 转向到修改一级类目页面
     */
    @RequestMapping(value = "/update")
    public String update(Integer id,Model model){
        Item_warehouse obj = Item_warehouseService.load(id);
        model.addAttribute("obj",obj);
        return "Item_warehouse/update";
    }

    /**
     * 修改一级类目
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Item_warehouse Item_warehouse){
        Item_warehouseService.updateById(Item_warehouse);
        return "redirect:/Item_warehouse/findBySql.action";
    }

    /**
     * 删除类目
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        //删除本身
        Item_warehouse load = Item_warehouseService.load(id);
        load.setIsDelete(1);
        Item_warehouseService.updateById(load);
        //将下级也删除
        String sql = "update item_warehouse set isDelete=1 where pid="+id;
        Item_warehouseService.updateBysql(sql);
        return "redirect:/Item_warehouse/findBySql.action";
    }

    /**
     * 查看二级类目
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(Item_warehouse item_warehouse, Model model){
        String sql = "select * from item_warehouse where isDelete=0 and pid="+item_warehouse.getPid()+" order by id";
        Pager<Item_warehouse> pagers = Item_warehouseService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",item_warehouse);
        return "Item_warehouse/Item_warehouse";
    }

    /**
     * 转向到新增二级类目页面
     */
    @RequestMapping(value = "/add2")
    public String add2(int pid,Model model){
        model.addAttribute("pid",pid);
        return "Item_warehouse/add2";
    }

    /**
     * 新增二级类目保存功能
     */
    @RequestMapping("/exAdd2")
    public String exAdd2(Item_warehouse item_warehouse){
        item_warehouse.setIsDelete(0);
        Item_warehouseService.insert(item_warehouse);
        return "redirect:/Item_warehouse/findBySql2.action?pid="+item_warehouse.getPid();
    }

    /**
     * 转向到修改二级类目页面
     */
    @RequestMapping(value = "/update2")
    public String update2(Integer id,Model model){
        Item_warehouse obj = Item_warehouseService.load(id);
        model.addAttribute("obj",obj);
        return "Item_warehouse/update2";
    }

    /**
     * 修改二级类目
     */
    @RequestMapping("/exUpdate2")
    public String exUpdate2(Item_warehouse Item_warehouse){
        Item_warehouseService.updateById(Item_warehouse);
        return "redirect:/Item_warehouse/findBySql2.action?pid="+Item_warehouse.getPid();
    }

    /**
     * 删除二级类目
     */
    @RequestMapping("/delete2")
    public String delete2(Integer id,Integer pid){
        //删除本身
        Item_warehouse load = Item_warehouseService.load(id);
        load.setIsDelete(1);
        Item_warehouseService.updateById(load);
        return "redirect:/Item_warehouse/findBySql2.action?pid="+pid;
    }

    @Autowired
    private MealsService mealsService;

    @RequestMapping(value = "/charts")
    public String tj(Item_warehouse Item_warehouse, Model model, HttpServletRequest request, HttpServletResponse response) {
        //分页查询
        String sql = "SELECT * FROM item_warehouse WHERE isDelete = 0 and pid is null";
        sql += " ORDER BY ID DESC ";
        List<Item_warehouse> list = Item_warehouseService.listBySqlReturnEntity(sql);
        List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
        List<Charts> res = new ArrayList<Charts>();

        if (!CollectionUtils.isEmpty(list)){
            for (Item_warehouse c : list){
                Charts td = new Charts();
                int tot = 0;
                List<Meals> listBySqlReturnEntity = mealsService.listBySqlReturnEntity("SELECT * FROM meals WHERE 1=1 and isDelete =0 and category_id_one="+c.getId());
                if (!CollectionUtils.isEmpty(listBySqlReturnEntity)){
                    for (Meals i : listBySqlReturnEntity){
                        tot+= i.getGmNum();
                    }
                }
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("name", c.getName());
                map.put("value", tot);
                maps.add(map);
            }
        }
        //存储查询条件
        model.addAttribute("maps", maps);
        return "charts/charts";
    }
}
