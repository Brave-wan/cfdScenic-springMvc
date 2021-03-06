package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.service.PictureLibraryService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.Json;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import static com.htkj.cfdScenic.app.controller.ImageController.imagePath;
import static com.htkj.cfdScenic.app.controller.ImageController.requestImgPath;

/**
 * @author wangfenglong
 * @date 2016/10/17 001716:11.
 */
@RequestMapping("/pictureLibrary")
@Controller
public class PictureLibraryController extends BaseController {

    @Autowired
    private PictureLibraryService pictureLibraryService;

    @RequestMapping("/uploadPicture")
    @ResponseBody
    public Json uploadPicture(@RequestParam("imgFile") CommonsMultipartFile[] files){
        String imageURL = "";
        PictureLibrary pl = new PictureLibrary();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        Json json = new Json();
        try {
            for(int i = 0;i<files.length;i++){
                System.out.println("fileName---------->" + files[i].getOriginalFilename());
                String newAddress = imagePath;
                if(!files[i].isEmpty()){
                    int pre = (int) System.currentTimeMillis();
                    File file1 = new File(newAddress);
                    //如果文件夹不存在则创建
                    if(!file1.exists()  && !file1.isDirectory()){
                        file1.mkdir();
                    }
                    String[] split = files[i].getOriginalFilename().split("\\.");
                    String name = split[split.length-1];
                    //时间戳+文件名
                    String newName = new Date().getTime() + GenerateSequenceUtil.getUniqueId() +"."+ name;
                    //上传的地址
                    String imgrul = newAddress+ "\\" + newName;
                    //存数据的地址
                    imageURL = requestImgPath +newName;
                    //拿到输出流，同时重命名上传的文件
                    FileOutputStream os = new FileOutputStream(imgrul);
                    //拿到上传文件的输入流
                    FileInputStream in = (FileInputStream) files[i].getInputStream();
                    //以写字节的方式写文件
                    int b = 0;
                    while((b=in.read()) != -1){
                        os.write(b);
                    }
                    os.flush();
                    os.close();
                    in.close();
                    int finaltime = (int) System.currentTimeMillis();
                    System.out.println(finaltime - pre);

                    System.out.println(imageURL);
                    Map<String,String> map=new HashMap<String,String>();
                    map.put("imageURL"+i, imageURL);
                    list.add(map);
                }
            }
            json.setSuccess(true);
            json.setObj(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


}
