package com.javapandeng.controller;

import com.javapandeng.Servlet.ItemCategoryDao;
import com.javapandeng.base.BaseController;
import com.javapandeng.po.Charts;
import com.javapandeng.po.ItemCategory;
import com.javapandeng.po.Meals;
import com.javapandeng.service.ItemCategoryService;
import com.javapandeng.service.MealsService;
import com.javapandeng.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javapandeng.controller.LoginforselController.m;

/**
 * 类目c层
 */
@Controller
@RequestMapping("/itemCategory")
public class ItemCategoryController extends BaseController {
    @Autowired
    private ItemCategoryService itemCategoryService;
    /**
     * 分页查询类目列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model,ItemCategory itemCategory){
        String sql = "select * from item_category where isDelete = 0 and pid is null order by id";
        /*Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);*/
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemCategory/itemCategory";
    }

    /**
     * 转向到新增一级类目页面
     */
    @RequestMapping(value = "/add")
    public String add(){
        return "itemCategory/add";
    }

    /**
     * 新增一级类目保存功能
     */
    @RequestMapping("/exAdd")
    public String exAdd(ItemCategory itemCategory){
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 转向到修改一级类目页面
     */
    @RequestMapping(value = "/update")
    public String update(Integer id,Model model){
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);
        return "itemCategory/update";
    }

    /**
     * 修改一级类目
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(ItemCategory itemCategory){
        itemCategoryService.updateById(itemCategory);
        return "redirect:/itemCategory/findBySql.action";
    }



    /**
     * 删除类目
     */
    @RequestMapping("/delete")
    public String delete(Integer id){
        //删除本身
        ItemCategory load = itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);
        //将下级也删除
        String sql = "update item_category set isDelete=1 where pid="+id;
        itemCategoryService.updateBysql(sql);
        return "redirect:/itemCategory/findBySql.action";
    }

    /**
     * 查看二级类目
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(ItemCategory itemCategory,Model model){
        String sql = "select * from item_category where isDelete=0 and pid="+itemCategory.getPid()+" order by id";
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemCategory/itemCategory2";
    }

    /**
     * 转向到新增二级类目页面
     */
    @RequestMapping(value = "/add2")
    public String add2(int pid,Model model){
        model.addAttribute("pid",pid);
        return "itemCategory/add2";
    }

    /**
     * 新增二级类目保存功能
     */
    @RequestMapping("/exAdd2")
    public String exAdd2(ItemCategory itemCategory){
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql2.action?pid="+itemCategory.getPid();
    }

    /**
     * 转向到修改二级类目页面
     */
    @RequestMapping(value = "/update2")
    public String update2(Integer id,Model model){
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);
        return "itemCategory/update2";
    }

    /**
     * 修改
     */
    @RequestMapping("/exUpdatepass")
    public String exUpdatepass(Integer id,Model model){
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);
        return "itemCategory/update2";
    }

    /**
     * 修改二级类目
     */
    @RequestMapping("/exUpdate2")
    public String exUpdate2(ItemCategory itemCategory){
        itemCategoryService.updateById(itemCategory);
        return "redirect:/itemCategory/findBySql2.action?pid="+itemCategory.getPid();
    }

    /**
     * 删除二级类目
     */
    @RequestMapping("/delete2")
    public String delete2(Integer id,Integer pid){
        //删除本身
        ItemCategory load = itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);
        return "redirect:/itemCategory/findBySql2.action?pid="+pid;
    }

    @Autowired
    private MealsService mealsService;

    @RequestMapping(value = "/charts")
    public String tj(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
        //获取店铺名称
        String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid is null";
        sql += " ORDER BY ID DESC ";
        List<ItemCategory> list = itemCategoryService.listBySqlReturnEntity(sql);
        List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
        List<Charts> res = new ArrayList<Charts>();
        if (!CollectionUtils.isEmpty(list)){
            for (ItemCategory c : list){
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


    @RequestMapping(value = "/charts2")
    public String tj2(ItemCategory itemCategory, Model model, HttpServletRequest request, HttpServletResponse response) {
       //商家ID
        String uid = String.valueOf(m.get("id"));
        //查询销售额
        String sql = "SELECT * FROM meals WHERE 1=1 and isDelete =0 and category_id_one=" + uid;
        List<Meals> list = mealsService.listBySqlReturnEntity(sql);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Meals c : list) {
                String name = (String) c.getName();
                int quantity = (int) c.getGmNum();
                double price = Double.valueOf(c.getPrice());
                double total = quantity * price;
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", name);
                map.put("value", total);
                maps.add(map);
            }
        }
//存储查询结果
        model.addAttribute("maps", maps);
        return "charts/charts2";
    }
       /* //分页查询
        String sql = "SELECT * FROM item_category WHERE isDelete = 0 and pid ="+uid;
        sql += " ORDER BY ID DESC ";
        List<ItemCategory> list = itemCategoryService.listBySqlReturnEntity(sql);
        List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
        List<Charts> res = new ArrayList<Charts>();
        if (!CollectionUtils.isEmpty(list)){
            for (ItemCategory c : list){
                Charts td = new Charts();
                int tot = 0;
                List<Meals> listBySqlReturnEntity = mealsService.listBySqlReturnEntity("SELECT * FROM meals WHERE 1=1 and isDelete =0 and category_id_one="+uid);

                if (!CollectionUtils.isEmpty(listBySqlReturnEntity)){
                    for (Meals i : listBySqlReturnEntity){
                        tot= i.getGmNum();
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
    */
    /**
     * 商品下架
     */
    @RequestMapping("/delete4")
    public String delete4(){
        ItemCategoryDao itemCategoryDao=new ItemCategoryDao();//操作
        itemCategoryDao.upall();   // 获取搜索栏的食物数据
        return "itemCategory/itemCategory";
    }
}
