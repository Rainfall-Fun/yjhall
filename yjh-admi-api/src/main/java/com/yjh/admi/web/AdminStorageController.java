package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.util.Result;
import com.yjh.core.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@RestController
@RequestMapping("/admin/storage")
@Validated
public class AdminStorageController {
    private final Log logger = LogFactory.getLog(AdminStorageController.class);
    @Value("${static.upload.path}")
    private String uploadPath;
    @Value("${static.server.address}")
    private String serverAddress;

//    @Autowired
//    private StorageService storageService;
//    @Autowired
//    private LitemallStorageService litemallStorageService;
//
//    @RequiresPermissions("admin:storage:list")
//    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "查询")
//    @GetMapping("/list")
//    public Object list(String key, String name,
//                       @RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer limit,
//                       @Sort @RequestParam(defaultValue = "add_time") String sort,
//                       @Order @RequestParam(defaultValue = "desc") String order) {
//        List<LitemallStorage> storageList = litemallStorageService.querySelective(key, name, page, limit, sort, order);
//        return ResponseUtil.okList(storageList);
//    }

    @RequiresPermissions("admin:storage:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "上传")
    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = UUID.randomUUID()+file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,null,e.toString());
        }
        return ResponseUtil.ok(new Result(true, serverAddress+fileName,fileName));
    }

//    @RequiresPermissions("admin:storage:read")
//    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "详情")
//    @PostMapping("/read")
//    public Object read(@NotNull Integer id) {
//        LitemallStorage storageInfo = litemallStorageService.findById(id);
//        if (storageInfo == null) {
//            return ResponseUtil.badArgumentValue();
//        }
//        return ResponseUtil.ok(storageInfo);
//    }
//
//    @RequiresPermissions("admin:storage:update")
//    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "编辑")
//    @PostMapping("/update")
//    public Object update(@RequestBody LitemallStorage litemallStorage) {
//        if (litemallStorageService.update(litemallStorage) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//        return ResponseUtil.ok(litemallStorage);
//    }
//

    /**
     * 删除图片根据文件名
     * @param result
     * @return
     */
    @RequiresPermissions("admin:storage:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody Result result) {
        try{
            String fileName = result.getMsg();
            if (StringUtils.isEmpty(fileName)) {
                return ResponseUtil.badArgument();
            }
            deleteFile(uploadPath+"yjh/"+fileName);
            return ResponseUtil.ok();
        }catch (Exception e){
            return ResponseUtil.fail();
        }


    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
            file.delete();// 文件删除
        }
        return true;
    }

}
