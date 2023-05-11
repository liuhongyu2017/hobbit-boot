package org.hobbit.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hobbit.core.tool.api.R;
import org.hobbit.core.tool.node.ForestNodeMerger;
import org.hobbit.core.tool.node.TreeNode;
import org.hobbit.core.tool.utils.Func;
import org.hobbit.user.api.entity.Region;
import org.hobbit.user.api.vo.RegionVO;
import org.hobbit.user.service.IRegionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @version 1.0.0 2023/5/11
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
@Api("行政区划表")
public class RegionController {

  private final IRegionService regionService;

  @ApiOperation("树")
  @GetMapping("/tree")
  public R<List<TreeNode>> tree() {
    return R.data(ForestNodeMerger.merge(regionService.tree()));
  }

  @ApiOperation("详情")
  @GetMapping("/detail")
  public R<RegionVO> detail(@RequestParam String code) {
    return R.data(regionService.detail(code));
  }

  @ApiOperation("修改/提交")
  @PostMapping("/submit")
  public R<Boolean> submit(@Valid @RequestBody Region region) {
    return R.data(regionService.submit(region));
  }

  @ApiOperation("删除")
  @GetMapping("/remove")
  public R<Boolean> remove(@RequestParam String ids) {
    return R.data(regionService.removeBatchByIds(Func.toLongList(ids)));
  }
}
