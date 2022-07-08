package iai.xmu.geek.account.controller;

import iai.xmu.geek.account.model.param.AccountParam;
import iai.xmu.geek.account.model.param.TransferParam;
import iai.xmu.geek.account.model.query.AccountQuery;
import iai.xmu.geek.account.model.vo.AccountVO;
import iai.xmu.geek.account.service.AccountService;
import iai.xmu.geek.commom.web.PageModel;
import iai.xmu.geek.commom.web.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: iai.xmu.edu.cn
 */
@Slf4j
@RestController
@Api(tags = "账户接口")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageModel<AccountVO>> pageAccount(@Validated AccountQuery param) {
        PageModel<AccountVO> rst = accountService.page(param);
        return Result.success(rst);
    }

    @GetMapping("/{account}")
    @ApiOperation("根据账户查询")
    public Result<AccountVO> findAccount(@PathVariable String account) {
        AccountVO rst = accountService.find(account);
        return Result.success(rst);
    }

    @PostMapping("/add")
    @ApiOperation("新增账户")
    public Result addAccount(@Validated @RequestBody AccountParam param) {
        AccountVO rst = accountService.addAccount(param);
        return Result.success(rst, "新增成功");
    }

    @PutMapping("/transfer")
    @ApiOperation("转账操作")
    public Result transfer(@Validated @RequestBody TransferParam param) {
        accountService.transfer(param);
        return Result.success(null, "转账成功");
    }

}
