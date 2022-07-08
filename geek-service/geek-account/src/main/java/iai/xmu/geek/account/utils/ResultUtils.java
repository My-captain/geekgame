package iai.xmu.geek.account.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import iai.xmu.geek.commom.utils.BeanUtils;
import iai.xmu.geek.commom.web.PageModel;

/**
 * 分页工具类
 *
 * @Author: iai.xmu.edu.cn

 */
public class ResultUtils extends BeanUtils{

    public static <T> PageModel<T> toPageModel(IPage<?> iPage, Class<T> clazz) {
        PageModel<T> pageModel = new PageModel<>();
        pageModel.setList(toList(iPage.getRecords(), clazz));
        pageModel.setTotal((int) iPage.getTotal());
        pageModel.setCurrentPage((int) iPage.getCurrent());
        pageModel.setPageSize((int) iPage.getSize());
        pageModel.setTotalPage((int) iPage.getPages());
        return pageModel;
    }

    public static <T> PageModel<T> toPageModel(PageInfo<?> pageInfo, Class<T> clazz) {
        PageModel<T> pageModel = new PageModel<>();
        pageModel.setList(toList(pageInfo.getList(), clazz));
        pageModel.setTotal((int) pageInfo.getTotal());
        pageModel.setCurrentPage(pageInfo.getPageNum());
        pageModel.setPageSize(pageInfo.getPageSize());
        pageModel.setTotalPage(pageInfo.getPages());
        return pageModel;
    }

}
