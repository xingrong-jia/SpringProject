package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.*;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CateGoryMapper cateGoryMapper;

    @Autowired
    CateGoryService cateGoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    Goods_specificationMapper goods_specificationMapper;

    @Autowired
    Goods_productMapper goods_productMapper;

    @Autowired
    Goods_attributeMapper goods_attributeMapper;

    @Autowired
    Groupon_rulesMapper groupon_rulesMapper;

    @Autowired
    IssueMapper issueMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameLike("%" + laypage.getName() + "%");
        }
        if (StringTool.isNotNull(laypage.getGoodsSn())) {
            criteria.andGoodsSnEqualTo(laypage.getGoodsSn());
        }
        criteria.andDeletedEqualTo(false);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        long total = goodsPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", goods);
        return map;
    }

    /**
     * 获取分类名和品牌商名
     *
     * @return
     */
    @Override
    public Map<String, Object> catAndBrand() {
        HashMap<String, Object> map = new HashMap<>();
        List<CateGory> cateGories = cateGoryService.list();
        ArrayList<Map> list = new ArrayList<>();
        for (CateGory cateGory : cateGories) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("value", cateGory.getId());
            map1.put("label", cateGory.getName());
            List<CateGory> children = cateGory.getChildren();
            ArrayList<Map> list11 = new ArrayList<>();
            for (CateGory child : children) {
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("value", child.getId());
                map2.put("label", child.getName());
                list11.add(map2);
            }
            map1.put("children", list11);
            list.add(map1);
        }
        map.put("categoryList", list);

        List<Brand> brands = brandService.selectBrands();
        ArrayList<Map> list1 = new ArrayList<>();
        for (Brand brand : brands) {
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("value", brand.getId());
            map2.put("label", brand.getName());
            list1.add(map2);
        }
        map.put("brandList", list1);
        return map;
    }

    @Transactional
    @Override
    public boolean addGoods(AddGoods addGoods) {
        Goods goods = addGoods.getGoods();
        goods.setSortOrder((short) 100);
        goods.setAddTime(new Date());
        goods.setUpdateTime(new Date());
        goods.setDeleted(false);
        int insert = goodsMapper.insertSelective(goods);
        List<Goods_specification> specifications = addGoods.getSpecifications();
        for (Goods_specification specification : specifications) {
            specification.setGoodsId(goods.getId());
            specification.setAddTime(new Date());
            specification.setUpdateTime(new Date());
            specification.setDeleted(false);
            goods_specificationMapper.insertSelective(specification);
        }
        List<Goods_product> products = addGoods.getProducts();
        for (Goods_product product : products) {
            product.setGoodsId(goods.getId());
            product.setId(null);
            product.setAddTime(new Date());
            product.setUpdateTime(new Date());
            product.setDeleted(false);
            goods_productMapper.insertSelective(product);
        }
        List<Goods_attribute> attributes = addGoods.getAttributes();
        for (Goods_attribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attribute.setAddTime(new Date());
            attribute.setUpdateTime(new Date());
            attribute.setDeleted(false);
            goods_attributeMapper.insertSelective(attribute);
        }
        return true;
    }

    @Override
    public Map queryGoodsById(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        map.put("goods", goods);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(goods.getCategoryId());
        Integer integer = cateGoryMapper.selectPidById(goods.getCategoryId());
        integers.add(integer);
        map.put("categoryIds", integers);

        Goods_attributeExample goods_attributeExample = new Goods_attributeExample();
        Goods_attributeExample.Criteria criteria = goods_attributeExample.createCriteria();
        criteria.andGoodsIdEqualTo(id);
        criteria.andDeletedEqualTo(false);
        List<Goods_attribute> goods_attributes = goods_attributeMapper.selectByExample(goods_attributeExample);
        map.put("attributes", goods_attributes);

        Goods_productExample goods_productExample = new Goods_productExample();
        Goods_productExample.Criteria criteria1 = goods_productExample.createCriteria();
        criteria1.andGoodsIdEqualTo(id);
        criteria1.andDeletedEqualTo(false);
        List<Goods_product> goods_products = goods_productMapper.selectByExample(goods_productExample);
        map.put("products", goods_products);

        Goods_specificationExample goods_specificationExample = new Goods_specificationExample();
        Goods_specificationExample.Criteria criteria2 = goods_specificationExample.createCriteria();
        criteria2.andGoodsIdEqualTo(id);
        criteria2.andDeletedEqualTo(false);
        List<Goods_specification> goods_specifications = goods_specificationMapper.selectByExample(goods_specificationExample);
        map.put("specifications", goods_specifications);
        return map;
    }


    @Override
    public boolean updateGoods(AddGoods addGoods) {
        Goods goods = addGoods.getGoods();
        int update = goodsMapper.updateByPrimaryKeySelective(goods);

        List<Goods_specification> specifications = addGoods.getSpecifications();
        goods_specificationMapper.updateDeletdByGoodsId(goods.getId(), true);
        for (Goods_specification specification : specifications) {
            specification.setId(null);

            specification.setGoodsId(goods.getId());
            specification.setAddTime(new Date());
            specification.setUpdateTime(new Date());
            specification.setDeleted(false);
            goods_specificationMapper.insertSelective(specification);

        }

        goods_attributeMapper.updateDeletdByGoodsId(goods.getId(), true);
        for (Goods_attribute attribute : addGoods.getAttributes()) {

            attribute.setId(null);

            attribute.setGoodsId(goods.getId());
            attribute.setAddTime(new Date());
            attribute.setUpdateTime(new Date());
            attribute.setDeleted(false);
            goods_attributeMapper.insertSelective(attribute);

        }

        for (Goods_product product : addGoods.getProducts()) {
            if (product.getId() == null || product.getId() == 0) {
                product.setGoodsId(goods.getId());
                product.setAddTime(new Date());
                product.setUpdateTime(new Date());
                product.setDeleted(false);
                goods_productMapper.insertSelective(product);
            } else {
                goods_productMapper.updateByPrimaryKeySelective(product);
            }
        }
        return true;
    }

    /**
     * 删除商品
     *
     * @param goods
     * @return
     */
    @Override
    public boolean deleteGoods(Goods goods) {
        goods.setDeleted(true);
        int update = goodsMapper.updateByPrimaryKeySelective(goods);
        return true;
    }


    @Override
    public Map count() {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        long l = goodsMapper.countByExample(goodsExample);
        HashMap<String, Long> map = new HashMap<>();
        map.put("goodsCount",l);
        return map;
    }

    @Override
    public Map wxList(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getSize());
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        //criteria.andCategoryIdEqualTo(laypage.getCategoryId());
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        long total = goodsPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", total);
        map.put("goodsList", goods);
        CateGoryExample cateGoryExample = new CateGoryExample();
        CateGoryExample.Criteria criteria1 = cateGoryExample.createCriteria();
        criteria1.andDeletedEqualTo(false);
        criteria1.andLevelEqualTo("L2");
        criteria1.andPidNotEqualTo(0);
        List<CateGory> cateGories = cateGoryMapper.selectByExample(cateGoryExample);
        map.put("filterCategoryList",cateGories);
        return map;
    }

    @Override
    public Map detail(Integer id) {
        HashMap<String, Object> map = new HashMap<>();

        Goods goods = goodsMapper.selectByPrimaryKey(id);
        map.put("info",goods);

        Goods_specificationExample goods_specificationExample = new Goods_specificationExample();
        Goods_specificationExample.Criteria criteria = goods_specificationExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andGoodsIdEqualTo(id);
        List<Goods_specification> goods_specifications = goods_specificationMapper.selectByExample(goods_specificationExample);
        ArrayList<Map> maps = new ArrayList<>();
        for (Goods_specification goods_specification : goods_specifications) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name",goods_specification.getSpecification());
            ArrayList<Goods_specification> list = new ArrayList<>();
            list.add(goods_specification);
            map1.put("valueList",list);
            maps.add(map1);
        }
        map.put("specificationList",maps);

        Groupon_rulesExample groupon_rulesExample = new Groupon_rulesExample();
        Groupon_rulesExample.Criteria criteria1 = groupon_rulesExample.createCriteria();
        criteria1.andDeletedEqualTo(false);
        criteria1.andGoodsIdEqualTo(id);
        List<Groupon_rules> groupon_rules = groupon_rulesMapper.selectByExample(groupon_rulesExample);
        map.put("groupon",groupon_rules);

        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andDeletedEqualTo(false);

        List<Issue> issues = issueMapper.selectByExample(issueExample);

        map.put("issue",issues);

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria2 = collectExample.createCriteria();
        criteria2.andDeletedEqualTo(false);
        criteria2.andValueIdEqualTo(id);
        long l = collectMapper.countByExample(collectExample);
        map.put("userHasCollect",l);

        map.put("shareImage","");

        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria3 = commentExample.createCriteria();
        criteria3.andDeletedEqualTo(false);
        criteria3.andValueIdEqualTo(id);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("count",comments.size());
        map1.put("data",comments);

        Goods_attributeExample goods_attributeExample = new Goods_attributeExample();
        Goods_attributeExample.Criteria criteria4 = goods_attributeExample.createCriteria();
        criteria4.andDeletedEqualTo(false);
        criteria4.andGoodsIdEqualTo(id);
        List<Goods_attribute> goods_attributes = goods_attributeMapper.selectByExample(goods_attributeExample);
        map.put("attribute",goods_attributes);

        Brand brand = new Brand();
        brand = brandMapper.selectByPrimaryKey(goods.getBrandId());

        map.put("brand",brand==null?null:brand);


        Goods_productExample goods_productExample = new Goods_productExample();
        Goods_productExample.Criteria criteria5 = goods_productExample.createCriteria();
        criteria5.andDeletedEqualTo(false);
        criteria5.andGoodsIdEqualTo(id);
        List<Goods_product> goods_products = goods_productMapper.selectByExample(goods_productExample);
        map.put("productList",goods_products);

        return map;
    }

    @Override
    public Map related(Integer id) {
        HashMap<String, List> map = new HashMap<>();
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        ArrayList<Object> list = new ArrayList<>();
        while (list.size()<11){
            int a = (int) Math.floor(Math.random()*goods.size());
            if (!list.contains(a)){
                list.add(a);
                list.add(0,goods.get(a));
            }
        }
        while (list.size()>6){
            list.remove(list.size()-1);
        }
        map.put("goodsList",list);
        return map;
    }
}
