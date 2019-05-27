package xin.shaonianyou.forum.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UploadController {
    /**
     * 个人信息上传
     * @return {Result}
     */
    @RequestMapping(value = "/user/upload", method = {RequestMethod.POST})
    @ResponseBody
    public Object headImg(@RequestParam(value="file",required=false) MultipartFile file) throws Exception {

        String suffix="";
        String uploadDir="D:\\ZW\\Workspace\\upload\\";
        String filename ="";
        String filepath="";
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> map2=new HashMap<>();
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        //当前时间戳
        long currentTime = System.currentTimeMillis();
        try{
            if(file!=null){

                //得到原文件名
                String originalName = file.getOriginalFilename();
                //得到源文件后缀名
                suffix=originalName.substring(originalName.lastIndexOf("."));
                // 新文件名 = 当前系统时间戳 + 6位随机数 + 扩展名
                filename = currentTime + new Random().nextInt(1000000) + suffix;
                //设置文件保存路径
                filepath = uploadDir + filename;
                filepath = filepath.replace("\\", "/");
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        }catch (Exception e){
            map.put("msg",e.toString());
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
                map.put("msg",e.toString());
            }
        }
        map.put("code",0);
        map.put("msg","");
        map.put("data",map2);
        map2.put("src","/avators/"+filename);
        return map;
    }
}
